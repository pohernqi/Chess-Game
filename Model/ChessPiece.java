// This is the parent class of all the chess pieces.
// This class contains the common methods to be use by all the chess piece classes.
/**
 * @author Tan Hui Jeen 121101196
 * @author Koh Jia Jie 1211102879
 * @author Poh Ern Qi 1211101398
 */

package Model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ChessPiece {

    private boolean isBlue; // To differentiate the chess pieces of blue player and yellow player.
    private Image image; // The path of the image of the chess pieces.
    private Movement chessPieceMovement; // The movement of the chess piece
    private boolean isFlipped; // To check if the point piece is flipped

    // Constructor
    public ChessPiece() {
    }

    public ChessPiece(boolean isBlue) {
        this.isBlue = isBlue; // Set the chess piece as blue or yellow, cannot be changed once set.
    }

    public ChessPiece(boolean isBlue, boolean isFlipped) {
        this.isBlue = isBlue;
        this.isFlipped = isFlipped;
    }

    // Return whether the chess piece is blue or yellow
    // Koh Jia Jie
    public boolean checkIsBlue() {
        return isBlue;
    }

    public void setIsBlue(boolean isBlue) {
        this.isBlue = isBlue;
    }

    // Set and scale a new image if the appearance of the chess piece is changed and
    // need to get the new image
    // Tan Hui Jeen
    // Poh Ern Qi
    public void setImage(String imagePath) {
        try {
            // Read the original image from the file
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Get the dimensions of the original image
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            // Nested loops to iterate over the original image in chunks of 200x200 pixels
            for (int y = 0; y < imageHeight; y += 200) {
                for (int x = 0; x < imageWidth; x += 200) {
                    // Calculate the width and height of the subimage, considering image boundaries
                    int subimageWidth = Math.min(200, imageWidth - x);
                    int subimageHeight = Math.min(200, imageHeight - y);

                    // Get the subimage and resize it to 80x80 pixels
                    this.image = image
                            .getSubimage(x, y, subimageWidth, subimageHeight)
                            .getScaledInstance(80, 80, BufferedImage.SCALE_SMOOTH);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Set the movemet for the chess piece
    public void setChessPieceMovement(Movement chessPieceMovement) {
        this.chessPieceMovement = chessPieceMovement;
    }

    // Return the chess movement
    public Movement getChessPieceMovement() {
        return chessPieceMovement;
    }

    // Return the image
    public Image getImage() {
        return this.image;
    }

    public void clearImage() {
        this.image = null;
    }

    // The method to flipped the point piece
    // Koh Jia Jie
    public void setFlipped() {
        if (this instanceof Point) {
            this.isFlipped = !this.isFlipped;

            if (isFlipped && this.checkIsBlue() == false) { // yellow flipped
                this.setImage("images/yellow_arrow_rotated.png");
            } else if (!isFlipped && this.checkIsBlue() == false) { // yellow not flipped
                this.setImage("images/yellow_arrow.png");
            } else if (isFlipped && this.checkIsBlue() == true) { // blue not flipped
                this.setImage("images/blue_arrow.png");
            } else if (!isFlipped && this.checkIsBlue() == true) { // blue flipped
                this.setImage("images/blue_arrow_rotated.png");
            }
        }
    }

    public boolean getFlipped() {
        return isFlipped;
    }
}
