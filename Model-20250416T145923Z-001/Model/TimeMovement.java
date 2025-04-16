/**
 * @author Tan Hui Jeen 121101196
 */

// Determines the available coordinates (movements) for the Time chess piece to move.
// Contains the movement logic of the Time piece.

package Model;

import java.util.ArrayList;

public class TimeMovement extends Movement {

  @Override
  public ArrayList<Coordinate> availableMove(
    Coordinate coordinate, // The current coordinate of the chess piece
    GameBoardModel gameBoardModel // The game board model which consists of the info about the board and chess
    // piece
  ) {
    // List to store the coordinates of the available movement
    ArrayList<Coordinate> canMoveCoordinates = new ArrayList<>();

    // Get the current coordinates of the Time chess piece
    int coorX = coordinate.getCoordinateX();
    int coorY = coordinate.getCoordinateY();

    // diagonal down left --> initial(x,y): (2,3) -> available coordinates: (1,4),
    // (0,5)
    for (int i = 1; coorX - i >= 0 && coorY + i <= 5; i++) {
      // Calculate the new coordinates based on iteration
      int x = coorX - i;
      int y = coorY + i;

      // Checks if the available coordinates are empty or not, if yes then add to
      // available coordinates
      if (gameBoardModel.getObject(x, y) == null) {
        canMoveCoordinates.add(gameBoardModel.getGameBoard()[x][y]);
      }
      // Check if there are opponent's chess piece on the coordinates, if yes then add
      // to available coordinates and break since it cannot skip over other pieces
      else if (
        gameBoardModel.getObject(coorX, coorY).checkIsBlue() !=
        gameBoardModel.getObject(x, y).checkIsBlue()
      ) {
        canMoveCoordinates.add(gameBoardModel.getGameBoard()[x][y]);
        break;
        // Else, if same colour, consider it as unavailable coordinates
      } else {
        break;
      }
    }

    // diagonal down right --> initial(x,y): (2,3) -> available coordinates: (3,4),
    // (4,5)
    for (int i = 1; coorX + i <= 6 && coorY + i <= 5; i++) {
      // Calculate the new coordinates based on iteration
      int x = coorX + i;
      int y = coorY + i;

      // Checks if the available coordinates are empty or not, if yes then add to
      // available coordinates
      if (gameBoardModel.getObject(x, y) == null) {
        canMoveCoordinates.add(gameBoardModel.getGameBoard()[x][y]);
      }
      // Check if there are opponent's chess piece on the coordinates, if yes then add
      // to available coordinates and break since it cannot skip over other pieces
      else if (
        gameBoardModel.getObject(coorX, coorY).checkIsBlue() !=
        gameBoardModel.getObject(x, y).checkIsBlue()
      ) {
        canMoveCoordinates.add(gameBoardModel.getGameBoard()[x][y]);
        break;
        // Else, if same colour, consider it as unavailable coordinates
      } else {
        break;
      }
    }

    // diagonal up right --> initial(x,y): (2,3) -> available coordinates: (3,2),
    // (4,1), (5,0)
    for (int i = 1; coorX + i <= 6 && coorY - i >= 0; i++) {
      // Calculate the new coordinates based on iteration
      int x = coorX + i;
      int y = coorY - i;

      // Checks if the available coordinates are empty or not, if yes then add to
      // available coordinates
      if (gameBoardModel.getObject(x, y) == null) {
        canMoveCoordinates.add(gameBoardModel.getGameBoard()[x][y]);
      }
      // Check if there are opponent's chess piece on the coordinates, if yes then add
      // to available coordinates and break since it cannot skip over other pieces
      else if (
        gameBoardModel.getObject(coorX, coorY).checkIsBlue() !=
        gameBoardModel.getObject(x, y).checkIsBlue()
      ) {
        canMoveCoordinates.add(gameBoardModel.getGameBoard()[x][y]);
        break;
        // Else, if same colour, consider it as unavailable coordinates
      } else {
        break;
      }
    }

    // diagonal up left --> initial(x,y): (2,3) -> available coordinates: (1,2),
    // (0,1)
    for (int i = 1; coorX - i >= 0 && coorY - i >= 0; i++) {
      // Calculate the new coordinates based on iteration
      int x = coorX - i;
      int y = coorY - i;

      // Checks if the available coordinates are empty or not, if yes then add to
      // available coordinates
      if (gameBoardModel.getObject(x, y) == null) {
        canMoveCoordinates.add(gameBoardModel.getGameBoard()[x][y]);
      }
      // Check if there are opponent's chess piece on the coordinates, if yes then add
      // to available coordinates and break since it cannot skip over other pieces
      else if (
        gameBoardModel.getObject(coorX, coorY).checkIsBlue() !=
        gameBoardModel.getObject(x, y).checkIsBlue()
      ) {
        canMoveCoordinates.add(gameBoardModel.getGameBoard()[x][y]);
        break;
        // Else, if same colour, consider it as unavailable coordinates
      } else {
        break;
      }
    }

    return canMoveCoordinates;
  }
}
