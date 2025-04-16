/**
 * @author Teh Yvonne 1211100571
 */

// Defines the image path and movement behaviour.

package Model;

public class Point extends ChessPiece {

    public Point(boolean isBlue, boolean isFlipped) {

        // Pass the isBlue, isFlipped from ChessPiece
        super(isBlue, isFlipped);
        if (isFlipped && this.checkIsBlue() == false) { // yellow flipped
            this.setImage("images/yellow_arrow_rotated.png");
        } else if (!isFlipped && this.checkIsBlue() == false) { // yellow not flipped
            this.setImage("images/yellow_arrow.png");
        } else if (isFlipped && this.checkIsBlue() == true) { // blue not flipped
            this.setImage("images/blue_arrow.png");
        } else if (!isFlipped && this.checkIsBlue() == true) { // blue flipped
            this.setImage("images/blue_arrow_rotated.png");
        }

        // Sets the chess piece movement using the PointMovement
        this.setChessPieceMovement(new PointMovement());
    }

    @Override
    public String toString() {
        return "Point piece";
    }

}
