/**
 * @author Tang Yu Xuan 1211103236
 */

// Determines the available coordinates (movements) for the Plus chess piece to move.
// Contains the movement logic of the Plus piece.

package Model;

import java.util.ArrayList;

public class PlusMovement extends Movement {

  @Override
  public ArrayList<Coordinate> availableMove(
      Coordinate coordinate,
      GameBoardModel gameBoardModel) {
    // List to store the coordinates of the available movement
    ArrayList<Coordinate> coordinates = new ArrayList<>();

    // Get the current coordinates of the Plus chess piece
    int coorX = coordinate.getCoordinateX();
    int coorY = coordinate.getCoordinateY();

    // Check for horizontal moves
    for (int x = 0; x <= 6; x++) {
      // if the destination x is diff from starting x
      if (x != coorX) {
        if (isValidMove(coorX, coorY, x, coorY, gameBoardModel)) {

          // if the destination coordinate is null or the color not same (the opponent's
          // chess)
          if (gameBoardModel.getObject(x, coorY) == null ||
              gameBoardModel.getObject(coorX, coorY).checkIsBlue() != gameBoardModel.getObject(x, coorY)
                  .checkIsBlue()) {

            // Iterate through all the coordinates to find the matching one and ass it to
            // the list
            for (Coordinate tempCoordinate : gameBoardModel.getAllCoordinate()) {
              if (tempCoordinate.toString().equals("(" + x + ", " + coorY + ")")) {
                coordinates.add(tempCoordinate);
              }
            }
          }
        }
      }
    }

    // Check for vertical moves
    for (int y = 0; y <= 5; y++) {
      // if the destination y is diff from starting y
      if (y != coorY) {
        if (isValidMove(coorX, coorY, coorX, y, gameBoardModel)) {

          // if the destination coordinate is null or the color not same (the opponent's
          // chess)
          if (gameBoardModel.getObject(coorX, y) == null ||
              gameBoardModel.getObject(coorX, coorY).checkIsBlue() != gameBoardModel.getObject(coorX, y)
                  .checkIsBlue()) {

            // Iterate through all the coordinates to find the matching one and ass it to
            // the list
            for (Coordinate tempCoordinate : gameBoardModel.getAllCoordinate()) {
              if (tempCoordinate.toString().equals("(" + coorX + ", " + y + ")")) {
                coordinates.add(tempCoordinate);
              }
            }
          }
        }
      }
    }

    return coordinates;
  }

  // Determines whether the movement from coordinate (startX, startY) to (endX,
  // endY) is valid
  private boolean isValidMove(
      int startX,
      int startY,
      int endX,
      int endY,
      GameBoardModel gameBoardModel) {

    // Determine the direction of horizontal movement (return 1: down, -1: up, 0: no
    // horizontal movement)
    int movementDirectionX = (endX > startX) ? 1 : (endX < startX) ? -1 : 0;

    // Determine the direction of vertical movement (return 1: down, -1: up, 0: no
    // vertical movement)
    int movementDirectionY = (endY > startY) ? 1 : (endY < startY) ? -1 : 0;

    // The loop continues as long as either i is not equal to endX or j is not equal
    // to endY
    for (int i = startX + movementDirectionX, j = startY + movementDirectionY; i != endX
        || j != endY; i += movementDirectionX, j += movementDirectionY) {
      if (gameBoardModel.getObject(i, j) != null) {
        return false; // There's a chess piece in the path
      }
    }
    return true; // Path is clear
  }

  @Override
  public String toString() {
    return "plus movement";
  }
}
