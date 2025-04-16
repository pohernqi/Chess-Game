/**
 * @author Poh Ern Qi 1211101398
 */

// Determines the available coordinates (movements) for the Sun chess piece to move.
// Contains the movement logic of the Sun piece.

package Model;

import java.util.ArrayList;

public class SunMovement extends Movement {

  @Override
  public ArrayList<Coordinate> availableMove(
    Coordinate coordinate,
    GameBoardModel gameBoardModel
  ) {
    // List to store the coordinates of the available movement
    ArrayList<Coordinate> canMoveCoordinates = new ArrayList<>();
    int[][] movements = {
      { -1, -1 }, // diagonalUpLeft
      { 1, -1 }, // diagonalUpRight
      { -1, 1 }, // diagonalDownLeft
      { 1, 1 }, // diagonalDownRight
      { 0, -1 }, // up
      { 0, 1 }, // down
      { -1, 0 }, // left
      { 1, 0 }, // right
    };

    for (int[] movement : movements) {
      // Get the current coordinates of the Sun chess piece
      int endCoorX = coordinate.getCoordinateX() + movement[0];
      int endCoorY = coordinate.getCoordinateY() + movement[1];

      // recheck the endcoordinates is in the dimensions of the board
      if (
        endCoorX >= 0 &&
        endCoorX < gameBoardModel.getGameBoard().length &&
        endCoorY >= 0 &&
        endCoorY < gameBoardModel.getGameBoard()[endCoorX].length
      ) {
        // if the target coordinate is not empty & not same color
        if (
          gameBoardModel.getObject(endCoorX, endCoorY) != null &&
          gameBoardModel.getObject(endCoorX, endCoorY).checkIsBlue() !=
          coordinate.getCoordinateObject().checkIsBlue()
        ) {
          // add the coordinates to the list of available movement
          canMoveCoordinates.add(
            gameBoardModel.getGameBoard()[endCoorX][endCoorY]
          );
          // if the target coordinate is empty, also add
        } else if (gameBoardModel.getObject(endCoorX, endCoorY) == null) {
          canMoveCoordinates.add(
            gameBoardModel.getGameBoard()[endCoorX][endCoorY]
          );
        }
      }
    }
    return canMoveCoordinates;
  }
}
