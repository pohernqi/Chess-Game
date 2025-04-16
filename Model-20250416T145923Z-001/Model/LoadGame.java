/**
 * @author Tan Hui Jeen 121101196
 * @author Teh Yvonne 1211100571
 */

// Provides functionality to load the game from a text file

package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Load the game state from a file
public class LoadGame {

    // Load game state from selected txt file
    // Tan Hui Jeen
    // Teh Yvonne
    public static GameBoardModel loadGame(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Read the content in the txt file into a StringBuilder
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            System.out.println("Loaded data:\n" + stringBuilder.toString()); // Print loaded data

            // Get the GameBoardModel instance
            GameBoardModel gameBoardModel = GameBoardModel.getGameBoardModel();

            // Deserialize the information from the file and populate the GameBoardModel
            deserializeInfo(gameBoardModel, stringBuilder.toString().trim());
            // Return the GameBoardModel with the loaded game state
            return gameBoardModel;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Deserializes information and updates the GameBoardModel
    // Tan Hui Jeen
    // Teh Yvonne
    public static void deserializeInfo(
            GameBoardModel gameBoardModel,
            String serializeInfo) {
        // Split the serialized string into lines
        String[] data = serializeInfo.split("\n");
        int currentRound = 0;
        
        // Process each line to extract and update relevant information in the
        // GameBoardModel
        for (String line : data) {
            if (line.startsWith("Round: ")) {
                // Set the current round information
                String[] roundData = line.split(" ");
                if (roundData.length >= 2) {
                    try {
                        currentRound = Integer.parseInt(line.split(" ")[1]);
                        gameBoardModel.setCurrentRound(currentRound);
                        System.out.println(
                                "Round information found: " + gameBoardModel.getCurrentRound());
                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Error parsing round information: " + e.getMessage());
                        return;
                    }
                }
            } else if (line.startsWith("Current Player: ")) {
                // Set the current player information
                String[] currentPlayer = line.split(" ");
                if (currentPlayer.length >= 3) {
                    String isBluePlayerTurn = (currentPlayer[2]);
                    
                    if (isBluePlayerTurn.equals("Blue")) {
                        gameBoardModel.setIsBluePlayer(true);
                    } else if (isBluePlayerTurn.equals("Yellow")) {
                        gameBoardModel.setIsBluePlayer(false);
                        gameBoardModel.setCurrentRound(currentRound);
                    }
                    
                    System.out.println(
                            "Player information found: " +
                                    (isBluePlayerTurn));
                }
            }
        }

        // Process each line to deserialize and set the chess piece on the
        // GameBoardModel
        for (int i = 1; i < data.length; i++) {
            String[] pieceInfo = data[i].split(",");
            if (pieceInfo.length >= 5) {
                int x = Integer.parseInt(pieceInfo[0]);
                int y = Integer.parseInt(pieceInfo[1]);
                String pieceName = pieceInfo[2];
                boolean isBlue = Boolean.parseBoolean(pieceInfo[3]);
                boolean isFlipped = Boolean.parseBoolean(pieceInfo[4]);

                System.out.println(
                        "Loaded piece: x=" +
                                x +
                                ", y=" +
                                y +
                                ", name=" +
                                pieceName +
                                ", isBlue=" +
                                isBlue +
                                ", isFlipped=" +
                                isFlipped);

                // Set the chess piece on the GameBoardModel based on the loaded information
                switch (pieceName) {
                    case "Point":
                        gameBoardModel
                                .getGameBoard()[x][y].setChessPiece(new Point(isBlue, isFlipped));
                        break;
                    case "Hourglass":
                        gameBoardModel
                                .getGameBoard()[x][y].setChessPiece(new Hourglass(isBlue));
                        break;
                    case "Sun":
                        gameBoardModel.getGameBoard()[x][y].setChessPiece(new Sun(isBlue));
                        break;
                    case "Time":
                        gameBoardModel.getGameBoard()[x][y].setChessPiece(new Time(isBlue));
                        break;
                    case "Plus":
                        gameBoardModel.getGameBoard()[x][y].setChessPiece(new Plus(isBlue));
                        break;
                    default:
                        gameBoardModel.getGameBoard()[x][y].setChessPiece(null);
                }
            } else {
                System.out.println("Incomplete data for chess piece at index " + i);
            }
        }
    }
}
