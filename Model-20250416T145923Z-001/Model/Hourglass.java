/**
 * @author Koh Jia Jie 1211102879
 */

// Defines the image path and movement behaviour.

package Model;

public class Hourglass extends ChessPiece {
    public Hourglass(boolean isBlue) {

        // Pass the isBlue from ChessPiece
        super(isBlue);
        if (isBlue) { // Set the image path for blue piece
            this.setImage("images/blue_hourglass.png");
        } else { // Set the image path for yellow piece
            this.setImage("images/yellow_hourglass.png");
        }

        // Sets the chess piece movement using the TimeMovement
        this.setChessPieceMovement(new HourglassMovement());
    }

    @Override
    public String toString() {
        return "Hourglass piece";
    }
}
