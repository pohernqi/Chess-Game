/**
 * @author Tang Yu Xuan 1211103236
 */

// Defines the image path and movement behaviour.

package Model;

public class Plus extends ChessPiece {

  public Plus(boolean isBlue) {
    // Pass the isBlue from ChessPiece
    super(isBlue);
    if (isBlue) { // Set the image path for blue piece
      this.setImage("images/blue_plus.png");
    } else { // Set the image path for yellow piece
      this.setImage("images/yellow_plus.png");
    }

    // Sets the chess piece movement using the PlusMovement
    this.setChessPieceMovement(new PlusMovement());
  }

  @Override
  public String toString() {
    return "Plus piece";
  }
}
