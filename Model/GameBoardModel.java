// MVC Design Pattern is allpied here.
// This class is act as Model in our MVC Design Pattern.
// Singleton Design Pattern is applied here.
// Singleton DP is applied so that we will have only one instance of Model, so that we will have only one game board.
// Observer Design Pattern is applied here
// The Model class is being observed by the view class.
/**
 * @author Tan Hui Jeen 121101196
 * @author Koh Jia Jie 1211102879
 * @author Tang Yu Xuan 1211103236
 * @author Teh Yvonne 1211100571
 * @author Poh Ern Qi 1211101398
 */

package Model;

import java.util.ArrayList;

public class GameBoardModel {

    // Data Field.
    private int boardWidth = 7; // chess board is set to 7x6.
    private int boardHeight = 6;
    private int boxSize = 80; // each tile's size at the begining.
    private Coordinate[][] gameBoard;
    private ArrayList<Coordinate> canMoveCoordinates = new ArrayList<>();
    private ArrayList<Coordinate> allCoordinates = new ArrayList<>();
    private Coordinate lastCoordinate; // The last coordinate clicked.
    private ChessPiece lastChessPiece; // The last chess piece selected.
    private boolean pieceSelected = true; // To check whether a piece is selected.
    private Observer observer; // The observer, which is our View in MVC DP.
    private int currentRound = 0; // The current round of the game, increased after both player have moved.
    private boolean bluePlayer = false; // Current player, true point blue player and false point to yellow player.
    private String sideBarMessage; // The message to be print on the side panel, such as which piece is selected
    // etc.
    private static GameBoardModel gameBoardModel; // The instance of this class is static since Singleton applied, and

    // instance cannot be created outside the class, so we have to use
    // static method to get the instance.

    // Private constructor due to Singleton applied, so that it cannot be called
    // outside this class.
    // Tan Hui Jeen
    // Koh Jia Jie
    // Poh Ern Qi
    private GameBoardModel() {
        gameBoard = new Coordinate[boardWidth][boardHeight];

        // Create Coordinate object for each tile in board.
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                gameBoard[i][j] = new Coordinate(i, j, null);
            }
        }
        // Place blue and yellow arrow.
        for (int x = 0; x < boardWidth; x++) {
            gameBoard[x][1].setChessPiece(new Point(true, true));
            gameBoard[x][4].setChessPiece(new Point(false, false));
        }

        // Place the remaining pieces.
        gameBoard[1][0].setChessPiece(new Hourglass(true));
        gameBoard[0][0].setChessPiece(new Plus(true));
        gameBoard[3][0].setChessPiece(new Sun(true));
        gameBoard[2][0].setChessPiece(new Time(true));
        gameBoard[5][0].setChessPiece(new Hourglass(true));
        gameBoard[6][0].setChessPiece(new Plus(true));
        gameBoard[4][0].setChessPiece(new Time(true));

        gameBoard[1][5].setChessPiece(new Hourglass(false));
        gameBoard[0][5].setChessPiece(new Plus(false));
        gameBoard[3][5].setChessPiece(new Sun(false));
        gameBoard[2][5].setChessPiece(new Time(false));
        gameBoard[5][5].setChessPiece(new Hourglass(false));
        gameBoard[6][5].setChessPiece(new Plus(false));
        gameBoard[4][5].setChessPiece(new Time(false));

        // Add all the coordinate objects into an arrayList.
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                allCoordinates.add(gameBoard[x][y]);
            }
        }
    }

    // We can only use this method to get the instance of GameBoardModel since
    // Singleton isapplied.
    // To make sure that we use the same instance.
    // Koh Jia Jie
    public static GameBoardModel getGameBoardModel() {
        if (gameBoardModel == null) {
            gameBoardModel = new GameBoardModel();
        }

        return gameBoardModel;
    }

    // Reset the game after surrender / exit the game
    // Have to do this way since we implement singleton design pattern in
    // GameBoardModel
    // Koh Jia Jie
    // Tang Yu Xuan
    public void resetGame() {
        // Reset all the game properties
        allCoordinates.clear();
        canMoveCoordinates.clear();
        pieceSelected = true;
        currentRound = 0;
        bluePlayer = false;
        sideBarMessage = null;
        lastCoordinate = null;
        lastChessPiece = null;

        // Reset Coordinate object for each tile in board
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                gameBoard[i][j] = new Coordinate(i, j, null);
            }
        }
        // Place blue and yellow arrow
        for (int x = 0; x < boardWidth; x++) {
            gameBoard[x][1].setChessPiece(new Point(true, true));
            gameBoard[x][4].setChessPiece(new Point(false, false));
        }

        // Place the remaining pieces
        gameBoard[1][0].setChessPiece(new Hourglass(true));
        gameBoard[0][0].setChessPiece(new Plus(true));
        gameBoard[3][0].setChessPiece(new Sun(true));
        gameBoard[2][0].setChessPiece(new Time(true));
        gameBoard[5][0].setChessPiece(new Hourglass(true));
        gameBoard[6][0].setChessPiece(new Plus(true));
        gameBoard[4][0].setChessPiece(new Time(true));

        gameBoard[1][5].setChessPiece(new Hourglass(false));
        gameBoard[0][5].setChessPiece(new Plus(false));
        gameBoard[3][5].setChessPiece(new Sun(false));
        gameBoard[2][5].setChessPiece(new Time(false));
        gameBoard[5][5].setChessPiece(new Hourglass(false));
        gameBoard[6][5].setChessPiece(new Plus(false));
        gameBoard[4][5].setChessPiece(new Time(false));

        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                gameBoardModel.allCoordinates.add(gameBoard[x][y]);
            }
        }
    }

    // The method to switch time and puls piece every two rounds.
    // Tang Yu Xuan
    public void switchPiece() {
        ArrayList<Coordinate> coordinates = getAllCoordinate();
        for (Coordinate coordinate : coordinates) {
            if (coordinate.getCoordinateObject() instanceof Plus) {
                // isBlue will be temp false first
                if (coordinate.getCoordinateObject().checkIsBlue()) {
                    Time time = new Time(true);
                    coordinate.setChessPiece(time);
                } else if (!coordinate.getCoordinateObject().checkIsBlue()) {
                    Time time = new Time(false);
                    coordinate.setChessPiece(time);
                }

                System.out.println("Changed Plus to time");
            } else if (coordinate.getCoordinateObject() instanceof Time) {
                if (coordinate.getCoordinateObject().checkIsBlue()) {
                    Plus plus = new Plus(true);
                    coordinate.setChessPiece(plus);
                } else if (!coordinate.getCoordinateObject().checkIsBlue()) {
                    Plus plus = new Plus(false);
                    coordinate.setChessPiece(plus);
                }
            }
        }
    }

    // The method to increase the round count after both players have finished their
    // turn.
    // Tang Yu Xuan
    public void incrementGameRound() {
        currentRound++;
        if (currentRound != 0 && currentRound % 2 == 0) {
            switchPiece();
        }
    }

    // reset pieces when rotate
    // Poh Ern Qi
    public void resetPieces() {
        ChessPiece chessPiece;
        int tempX = 0; // temp x
        int tempY = 0; // temp y
        for (int i = 0; i < getBoardWidth(); i++) {
            for (int j = 0; j < getBoardHeight(); j++) {
                chessPiece = getGameBoard()[i][j].getCoordinateObject();
                if (chessPiece != null) {
                    // find the temp x and y coordinates
                    tempX = getBoardWidth() - 1 - getGameBoard()[i][j].getCoordinateX();
                    tempY = getBoardHeight() - 1 - getGameBoard()[i][j].getCoordinateY();
                    // to flip the img when rotate
                    chessPiece.setFlipped();
                    // set the rotated coordinate obj
                    getGameBoard()[tempX][tempY].setRotatedCoordinateObject(chessPiece);
                    // clear chesspieces on coordinate
                    getGameBoard()[i][j].setChessPiece(null);
                }
            }
        }
        for (int i = 0; i < getBoardWidth(); i++) {
            for (int j = 0; j < getBoardHeight(); j++) {
                // get the chesspiece that set earlier
                chessPiece = getGameBoard()[i][j].getRotatedCoordinateObject();
                if (chessPiece != null) {
                    getGameBoard()[i][j].setChessPiece(chessPiece); // reset the chesspieces on the coordinates
                    getGameBoard()[i][j].setRotatedCoordinateObject(null);
                }
            }
        }
    }

    // The method to tell observer to update (repaint).
    // Koh Jia Jie
    public void notifyObserver() {
        observer.update();
    }

    // The method to save and load.
    // Tan Hui Jeen
    public GameBoardModel saveLoad(String saveLoad, String fileName) {
        return SaveLoad.saveLoad(saveLoad, fileName, this);
    }

    // Getter
    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoxSize() {
        return boxSize;
    }

    public Coordinate[][] getGameBoard() {
        return gameBoard;
    }

    public ArrayList<Coordinate> getAllCoordinate() {
        return allCoordinates;
    }

    public int getCoordinateX(int x, int y) {
        return gameBoard[x][y].getCoordinate().getCoordinateX();
    }

    public int getCoordinateY(int x, int y) {
        return gameBoard[x][y].getCoordinate().getCoordinateY();
    }

    public ChessPiece getObject(int x, int y) {
        return gameBoard[x][y].getCoordinateObject();
    }

    public ArrayList<Coordinate> getCanMoveCoordinates() {
        return canMoveCoordinates;
    }

    public Movement getMovement(int x, int y) {
        return gameBoard[x][y].getCoordinateObject().getChessPieceMovement();
    }

    public int getLastCoordinateX() {
        return lastCoordinate.getCoordinateX();
    }

    public int getLastCoordinateY() {
        return lastCoordinate.getCoordinateY();
    }

    public Coordinate getLastCoordinate() {
        return lastCoordinate;
    }

    public ChessPiece getLastChessPiece() {
        return lastChessPiece;
    }

    public boolean getPieceSelected() {
        return pieceSelected;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public boolean getIsBluePlayerTurn() {
        return bluePlayer;
    }

    public String getPlayerTurnInString() {
        if (bluePlayer) {
            return "Blue Player";
        } else {
            return "Yellow Player";
        }
    }

    public String getSideBarMessage() {
        return this.sideBarMessage;
    }

    // Setter
    public void setCanMoveCoordinates(ArrayList<Coordinate> canMoveCoordinates) {
        this.canMoveCoordinates = canMoveCoordinates;
    }

    public void setLastCoordinate(Coordinate lastCoordinate) {
        this.lastCoordinate = lastCoordinate;
        this.lastChessPiece = lastCoordinate.getCoordinateObject();
    }

    public void setPieceSelected(boolean bool) {
        pieceSelected = bool;
        notifyObserver();
    }

    public void setLastChessPiece(ChessPiece lastChessPiece) {
        this.lastChessPiece = lastChessPiece;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void setIsBluePlayer(boolean bluePlayer) {
        this.bluePlayer = bluePlayer;
        if (!bluePlayer) {
            incrementGameRound();
        }
    }

    public void setSideBarMessage(String sideBarMessage) {
        this.sideBarMessage = sideBarMessage;
        notifyObserver();
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }
}
