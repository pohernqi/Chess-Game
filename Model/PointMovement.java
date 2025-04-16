/**
 * @author Teh Yvonne 1211100571
 */

// Determines the available coordinates (movements) for the Point chess piece to move.
// Contains the movement logic of the Point piece.

package Model;

import java.util.ArrayList;

public class PointMovement extends Movement {

    @Override
    public ArrayList<Coordinate> availableMove(Coordinate coordinate, GameBoardModel gameBoardModel) {

        // List to store the coordinates of the available movement
        ArrayList<Coordinate> canMoveCoordinates = new ArrayList<>();

        // Get the current coordinates of the Point chess piece
        int coorX = coordinate.getCoordinateX();
        int coorY = coordinate.getCoordinateY();

        // Iterate through the chess board to check for available moves
        for (int x = 0; x <= 6; x++) {
            for (int y = 0; y <= 5; y++) {

                // Checks if the point piece is flipped
                if (gameBoardModel.getObject(coorX, coorY).getFlipped()) {

                    // If flipped, check for valid moves
                    if ((coorY - y == -2 && coorX - x == 0) || (coorY - y == -1 && coorX - x == 0)) {
                        if (isValidMove(coorX, coorY, y, gameBoardModel)) {

                            // Checks if the destination is empty or has an opponent's chess piece
                            if (gameBoardModel.getObject(x, y) == null ||
                                    gameBoardModel.getObject(coorX, coorY).checkIsBlue() != gameBoardModel
                                            .getObject(x, y).checkIsBlue()) {

                                // Add the valid move to the list of available moves
                                for (Coordinate tempCoordinate : gameBoardModel.getAllCoordinate()) {
                                    if (tempCoordinate.toString().equals("(" + x + ", " + y + ")")) {
                                        canMoveCoordinates.add(tempCoordinate);
                                    }
                                }
                            }
                        }
                    }

                } else if (!gameBoardModel.getObject(coorX, coorY).getFlipped()) {

                    // If not flipped, check for valid moves
                    if ((coorY - y == 2 && coorX - x == 0) || (coorY - y == 1 && coorX - x == 0)) {

                        // Checks if the destination is empty or has an opponent's chess piece
                        if (isValidMove(coorX, coorY, y, gameBoardModel)) {
                            if (gameBoardModel.getObject(x, y) == null ||
                                    gameBoardModel.getObject(coorX, coorY).checkIsBlue() != gameBoardModel
                                            .getObject(x, y).checkIsBlue()) {

                                // Add the valid move to the list of available moves
                                for (Coordinate tempCoordinate : gameBoardModel.getAllCoordinate()) {
                                    if (tempCoordinate.toString().equals("(" + x + ", " + y + ")")) {
                                        canMoveCoordinates.add(tempCoordinate);
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }

        return canMoveCoordinates;
    }

    private boolean isValidMove(int coorX, int coorY, int endY, GameBoardModel gameBoardModel) {
        int movementDirection = (endY - coorY) / Math.abs(endY - coorY); // Determine the direction of movement
        for (int i = coorY + movementDirection; i != endY; i += movementDirection) {
            if (gameBoardModel.getObject(coorX, i) != null) {
                return false; // There's a chess piece in the path
            }
        }
        return true; // Path is clear
    }

    @Override
    public String toString() {
        return "point movement";
    }
}
