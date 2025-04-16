/**
 * @author Tan Hui Jeen 121101196
 */

// Defines the image path and movement behaviour.

package Model;

public class Time extends ChessPiece {

  public Time(boolean isBlue) {
    // Pass the isBlue from ChessPiece
    super(isBlue);
    if (isBlue) { // Set the image path for blue piece
      this.setImage("images/blue_times.png");
    } else { // Set the image path for yellow piece
      this.setImage("images/yellow_times.png");
    }

    // Sets the chess piece movement using the TimeMovement
    this.setChessPieceMovement(new TimeMovement());
  }

  @Override
  public String toString() {
    return "Time piece";
  }
}
