// Indicate the coordinate of chess piece on the game board
/**
 * @author Koh Jia Jie 1211102879
 * @author Poh Ern Qi 1211101398
 */

package Model;

public class Coordinate {

  private int CoordinateX;
  private int CoordinateY;
  private ChessPiece CoordinateObject;
  private ChessPiece RotatedCoordinateObject; // The temporary instance to store the object when rotate the board.

  // Constructor that only brings coordinate
  public Coordinate(int CoordinateX, int CoordinateY) {
    this.CoordinateX = CoordinateX;
    this.CoordinateY = CoordinateY;
  }

  // Constructor to initialize the piece with its properties and add it to the
  // list of piece.
  public Coordinate(int CoordinateX, int CoordinateY, ChessPiece chessPiece) {
    this.CoordinateX = CoordinateX;
    this.CoordinateY = CoordinateY;
    this.CoordinateObject = chessPiece; // Initially, no piece is on this coordinate
  }

  // Constructor to set the coordinate of chess piece
  public void setCoordinate(int CoordinateX, int CoordinateY) {
    this.CoordinateX = CoordinateX;
    this.CoordinateY = CoordinateY;
  }

  // Getter and setter for the chess piece on this coordinate
  public ChessPiece getCoordinateObject() {
    return CoordinateObject;
  }

  // sets the chess piece for a specific coordinate
  public void setChessPiece(ChessPiece chessPiece) {
    this.CoordinateObject = chessPiece;
  }

  // Retrieves the rotated chess piece at this coordinate
  public ChessPiece getRotatedCoordinateObject() {
    return RotatedCoordinateObject;
  }

  // sets the rotated chess piece for a specific coordinate
  public void setRotatedCoordinateObject(ChessPiece chessPiece) {
    this.RotatedCoordinateObject = chessPiece;
  }

  // Get the x-coordinate value of chess piece
  public int getCoordinateX() {
    return CoordinateX;
  }

  // Get the x coordinate relative to other ChessTile.
  public int getCoordinateY() {
    return CoordinateY;
  }

  // Get the coordinate of chess piece associated with this instance
  public Coordinate getCoordinate() {
    return this;
  }

  // method to print Coordinate in a Coordinate format : (x,y)
  @Override
  public String toString() {
    return "(" + CoordinateX + ", " + CoordinateY + ")";
  }
}
