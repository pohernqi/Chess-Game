// This class is created with the methods to be overwritten by the child class (each of the chess pieces movement).
// Strategy Design Pattern is applied here
// Each chess piece has its own movement logic, so this abstract class is created with availableMove abstract method (contains
// movement logic of the chess piece) that to be override by child class.
/**
 * @author Koh Jia Jie 1211102879
 * @author Tang Yu Xuan 1211103236
 */

package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Movement {

    // This is the method to check and return a list of coordinate that the chess
    // piece can move to.
    // This is the move logic of the chess piece.
    // Koh Jia Jie
    public abstract ArrayList<Coordinate> availableMove(
            Coordinate coordinate,
            GameBoardModel gameBoardModel);

    // This is the method to update the new coodrinate of the chess piece.
    // Tang Yu Xuan
    // Koh Jia Jie
    public Map<String, Boolean> move(
            Coordinate initialCoordinate,
            Coordinate clickedCoordinate,
            ArrayList<Coordinate> coordinates,
            ChessPiece chessPiece) {
        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("canMove", false);
        resultMap.put("isCapturedSun", false);

        for (Coordinate coordinate : coordinates) {
            if (clickedCoordinate.equals(coordinate)) {
                if (clickedCoordinate.getCoordinateObject() != null) {
                    capture(clickedCoordinate.getCoordinateObject());

                    // if its sun, put in map. Means captured sun
                    if (clickedCoordinate.getCoordinateObject() instanceof Sun) {
                        resultMap.put("isCapturedSun", true);
                        resultMap.put("canMove", true);
                    }
                }
                initialCoordinate.setChessPiece(null);
                clickedCoordinate.setChessPiece(chessPiece);
                resultMap.put("canMove", true);
            }
        }

        if ((chessPiece instanceof Point) &&
                (clickedCoordinate.getCoordinateY() == 0 ||
                        clickedCoordinate.getCoordinateY() == 5)) {
            chessPiece.setFlipped();
        }

        return resultMap;
    }

    // This is the method for a piece to kill another piece.
    // Koh Jia Jie
    public void capture(ChessPiece chessPiece) {
        System.out.println("You captured opponent's " + chessPiece);
        GameBoardModel
                .getGameBoardModel()
                .setSideBarMessage("You captured opponent's " + chessPiece);
    }
}
