/**
 * @author Koh Jia Jie 1211102879
 */

// Determines the available coordinates (movements) for the Hourglass chess piece to move.
// Contains the movement logic of the Hourglass piece.

package Model;

import java.util.ArrayList;

public class HourglassMovement extends Movement {

    @Override
    public ArrayList<Coordinate> availableMove(Coordinate coordinate, GameBoardModel gameBoardModel) {

        // List to store the coordinates of the available movement
        ArrayList<Coordinate> canMoveCoordinates = new ArrayList<>();

        // Get the current coordinates of the Hourglass chess piece
        int coorX = coordinate.getCoordinateX();
        int coorY = coordinate.getCoordinateY();

        // Check all the available coordinates for the piece to move
        for (int x = 0; x <= 6; x++) {
            for (int y = 0; y <= 5; y++) {

                // Check if its a valid hourglass move
                if (Math.abs(coorX - x) == 2 && Math.abs(coorY - y) == 1) {

                    // Check if there's a chess piece of same col at the coordinate
                    if (gameBoardModel.getObject(x, y) != null &&
                            gameBoardModel.getObject(x, y).checkIsBlue() == gameBoardModel.getObject(coorX, coorY)
                                    .checkIsBlue()) {

                        // Remove the coordinates that occupied by own piece.
                        continue;
                    }

                    // Add the valid move to the list of available moves
                    for (Coordinate tempCoordinate : gameBoardModel.getAllCoordinate()) {
                        if (tempCoordinate.toString().equals("(" + x + ", " + y + ")")) {
                            canMoveCoordinates.add(tempCoordinate);
                        }
                    }
                } else if (Math.abs(coorX - x) == 1 && Math.abs(coorY - y) == 2) {

                    // Check if there's a chess piece of same col at the coordinate
                    if (gameBoardModel.getObject(x, y) != null &&
                            gameBoardModel.getObject(x, y).checkIsBlue() == gameBoardModel.getObject(coorX, coorY)
                                    .checkIsBlue()) {

                        // Remove the coordinates that occupied by own piece.
                        continue;
                    }

                    // Add the valid move to the list of available moves
                    for (Coordinate tempCoordinate : gameBoardModel.getAllCoordinate()) {
                        if (tempCoordinate.toString().equals("(" + x + ", " + y + ")")) {
                            canMoveCoordinates.add(tempCoordinate);
                        }
                    }
                }
            }
        }

        return canMoveCoordinates;
    }

}
