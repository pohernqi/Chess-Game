/**
 * @author Tan Hui Jeen 121101196
 * @author Teh Yvonne 1211100571
 */

// Saves the game state into a text file

package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//Save the game state to a file
public class SaveGame {

  // Saves the game state to a txt file
  // Tan Hui Jeen
  // Teh Yvonne
  public static void saveGame(String fileName, GameBoardModel gameBoardModel) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      // Serialize the game state to a string
      String serializedInfo = serializeString(gameBoardModel);
      // Write the serialized info to the file
      writer.write(serializedInfo);
      System.out.println("Game saved to " + fileName);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Serializes the GameBoardModel into a string representation
  // Tan Hui Jeen
  // Teh Yvonne
  public static String serializeString(GameBoardModel gameBoardModel) {
    StringBuilder serializedInfo = new StringBuilder();

    // Append current round information
    serializedInfo
        .append("Round: ")
        .append(gameBoardModel.getCurrentRound())
        .append("\n");

    // Append current player information
    serializedInfo
        .append("Current Player: ")
        .append(gameBoardModel.getIsBluePlayerTurn() ? "Blue" : "Yellow")
        .append("\n");

    // Serialize each chess piece on the game board
    for (int x = 0; x < gameBoardModel.getBoardWidth(); x++) {
      for (int y = 0; y < gameBoardModel.getBoardHeight(); y++) {
        // Get the chess piece at the current coordinate
        ChessPiece piece = gameBoardModel
            .getGameBoard()[x][y].getCoordinateObject();

        // Check if the coordinate contains a chess piece
        if (piece != null) {
          // Append the information about the chess piece is the coordinate contains a
          // chess piece
          serializedInfo
              .append(x)
              .append(",")
              .append(y)
              .append(",")
              .append(piece.getClass().getSimpleName())
              .append(",")
              .append(piece.checkIsBlue())
              .append(",")
              .append(piece.getFlipped())
              .append("\n");
        } else {
          // Append null values if the coordinate is empty
          serializedInfo
              .append(x)
              .append(",")
              .append(y)
              .append(",")
              .append("null")
              .append(",")
              .append(false)
              .append(",")
              .append(false)
              .append("\n");
        }
      }
    }
    return serializedInfo.toString();
  }
}
