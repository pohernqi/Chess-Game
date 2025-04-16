/**
 * @author Poh Ern Qi 1211101398
 */

// Defines the image path and movement behaviour.

package Model;

public class Sun extends ChessPiece {
    public Sun(boolean isBlue) {

        // Pass the isBlue from ChessPiece
        super(isBlue);
        if (isBlue) { // Set the image path for blue piece
            this.setImage("images/blue_sun.png");
        } else { // Set the image path for yellow piece
            this.setImage("images/yellow_sun.png");
        }

        // Sets the chess piece movement using the SunMovement
        this.setChessPieceMovement(new SunMovement());
    }

    @Override
    public String toString() {
        return "Sun piece";
    }
}
