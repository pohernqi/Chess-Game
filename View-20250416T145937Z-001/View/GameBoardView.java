// MVC Design Pattern is applied here
// This class is act as View in the Design pattern
// This class paint the home frame and the frame that contains gameboard and side panel.
// This class initialize all the buttons.
// Observer Design Pattern is applied here
// This class is act as observer to observe the model and repaint when certain change happened in model
/**
 * @author Tan Hui Jeen 121101196
 * @author Koh Jia Jie 1211102879
 * @author Tang Yu Xuan 1211103236
 * @author Teh Yvonne 1211100571
 * @author Poh Ern Qi 1211101398
 */

package View;

import Model.GameBoardModel;
import Model.Observer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameBoardView extends JPanel implements Observer {

  // Data Field
  private JFrame frame = new JFrame();
  private JButton saveBtn, helpBtn2, surrenderBtn;
  private ImageIcon gameOverIcon;
  private JLabel roundLabel;
  private JLabel playerLabel;
  private JLabel messageLabel;
  private JButton startBtn, continueBtn, loadBtn, helpBtn, exitBtn;
  private JPanel buttonPanel;
  private JPanel sidePanel;
  private ImageIcon helpImageIcon;
  private JFrame homeFrame = new JFrame();

  // Custom JPanel class for background image and title logo
  // Poh Ern Qi
  class ImagePanel extends JPanel {

    private Image bgImage;
    private Image iconImage;

    public ImagePanel(String imagePath, String iconImagePath) {
      bgImage = new ImageIcon(imagePath).getImage();
      iconImage = new ImageIcon(iconImagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      // draw the img for both logo n bg img
      g.drawImage(bgImage, 0, 0, this.getWidth(), this.getHeight(), this);
      g.drawImage(iconImage, 120, 30, this);
    }
  }

  // Constructor - initalize all the UI
  // Koh Jia Jie
  // Poh Ern Qi
  public GameBoardView() {
    // set frame for home
    homeFrame.setTitle("talabia chess by LOCO LOCO");
    homeFrame.setBounds(100, 100, 450, 300);

    // Set up the panel with background image
    buttonPanel = new ImagePanel("images/bg.jpg", "images/icon.png");
    buttonPanel.setLayout(null);

    homeFrame.setContentPane(buttonPanel);
    homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    homeFrame.setSize(new Dimension(600, 550));
    homeFrame.setResizable(true);
    homeFrame.setVisible(true);

    // Center the JFrame on the screen
    homeFrame.setLocationRelativeTo(null);
    initHomeButtons();
    addIconToBackground("images/icon.png", 120, 30); // Replace with your icon path and desired position
    initButtons();
  }

  // homebuttons
  // Poh Ern Qi
  private void initHomeButtons() {
    // Calculate center position for buttons
    int centerX = (buttonPanel.getWidth() / 2) - 65; // Adjusted for button width
    Color darkBlue = new Color(42, 69, 103);

    // start button
    startBtn = new JButton("Start");
    startBtn.setBackground(darkBlue);
    startBtn.setForeground(Color.WHITE);
    startBtn.setFocusable(false);
    // set the size of the buttons
    startBtn.setBounds(centerX, 250, 130, 40);
    buttonPanel.add(startBtn);

    // load button
    loadBtn = new JButton("Load");
    loadBtn.setForeground(Color.WHITE);
    loadBtn.setBackground(darkBlue);
    loadBtn.setFocusable(false);
    loadBtn.setBounds(centerX, 300, 130, 40);
    buttonPanel.add(loadBtn);
    // help button
    helpBtn = new JButton("Help");
    helpBtn.setBackground(darkBlue);
    helpBtn.setForeground(Color.WHITE);
    helpBtn.setFocusable(false);
    helpBtn.setBounds(centerX, 350, 130, 40);
    buttonPanel.add(helpBtn);
    // exit button
    exitBtn = new JButton("Exit");
    exitBtn.setBackground(darkBlue);
    exitBtn.setForeground(Color.WHITE);
    exitBtn.setFocusable(false);
    exitBtn.setBounds(centerX, 400, 130, 40);
    buttonPanel.add(exitBtn);
  }

  // Poh Ern Qi
  private void addIconToBackground(String iconPath, int x, int y) {
    // Load the icon image
    ImageIcon icon = new ImageIcon(iconPath);

    // Create a label and set the icon to the label
    JLabel iconLabel = new JLabel(icon);

    // Set the size of the label to the size of the icon
    iconLabel.setSize(icon.getIconWidth(), icon.getIconHeight());

    // Set the position of the label on the panel
    iconLabel.setLocation(x, y);

    // Add the label to the button panel
    buttonPanel.add(iconLabel);
  }

  // show exit dialog
  // Poh Ern Qi
  public int showExitConfirmationDialog() {
    return JOptionPane.showConfirmDialog(
      null,
      "Are you sure you want to exit?",
      "Exit",
      JOptionPane.YES_NO_OPTION
    );
  }

  // show game help
  // Poh Ern Qi
  public void showGameHelp() {
    loadHelpImg();
    JLabel imageLabel = new JLabel(helpImageIcon);
    JOptionPane.showMessageDialog(
      null,
      imageLabel,
      "Game Help",
      JOptionPane.PLAIN_MESSAGE
    );
  }

  // set frame for gameboard
  // Koh Jia Jie
  public void setFrame() {
    GameBoardModel gameBoardModel = GameBoardModel.getGameBoardModel();
    int boardWidth = gameBoardModel.getBoardWidth();
    int boardHeight = gameBoardModel.getBoardHeight();
    int boxSize = gameBoardModel.getBoxSize();

    // frame = new JFrame();
    frame.setSize((boardWidth * boxSize) + 400, (boardHeight * boxSize) + 100);
    frame.add(this);
    frame.add(sidePanel, BorderLayout.EAST); // Put the sidePanel at the right side

    // to close only current window prevent 2 windows from closing when click x
    frame.addWindowListener(
      new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
          frame.setVisible(false); // This will close only the current window
          homeFrame.setVisible(true); // Set homeFrame visible when frame is closed
          startBtn.setText("Continue"); 
        }
      }
    );

    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.setVisible(true);
  }

  // initialize btns
  // Koh Jia Jie
  private void initButtons() {
    GameBoardModel gameBoardModel = GameBoardModel.getGameBoardModel();
    roundLabel =
      new JLabel("Current Round : " + (gameBoardModel.getCurrentRound() + 1));
    playerLabel =
      new JLabel("Current Turn : " + gameBoardModel.getPlayerTurnInString());
    messageLabel = new JLabel("Welcome to the board game!");

    sidePanel = new JPanel();
    sidePanel.setPreferredSize(new Dimension(250, 500));

    sidePanel.add(roundLabel);
    sidePanel.add(playerLabel);
    sidePanel.add(messageLabel);

    // to make btns resizable
    sidePanel.setBorder(new EmptyBorder(100, 10, 100, 30));
    sidePanel.setLayout(new GridLayout(10, 1, 50, 8));

    saveBtn = new JButton("Save");
    saveBtn.setBackground(Color.darkGray); // Grey background
    saveBtn.setForeground(Color.WHITE); // White text
    saveBtn.setFocusable(false);
    sidePanel.add(saveBtn);

    helpBtn2 = new JButton("Help");
    helpBtn2.setBackground(Color.darkGray); // Grey background
    helpBtn2.setForeground(Color.WHITE); // White text
    helpBtn2.setFocusable(false);
    sidePanel.add(helpBtn2);

    surrenderBtn = new JButton("Surrender");
    surrenderBtn.setBackground(Color.darkGray); // Grey background
    surrenderBtn.setForeground(Color.WHITE); // White text
    surrenderBtn.setFocusable(false);
    sidePanel.add(surrenderBtn);
  }

  // Teh Yvonne
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawBoard(g);
    drawAvailableMove(g);
    drawPieces(g);
    changeSideContent();
  }

  // Draw the chess tile
  // Koh Jia Jie
  // Tan Hui Jeen
  private void drawBoard(Graphics g) {
    GameBoardModel gameBoardModel = GameBoardModel.getGameBoardModel();
    boolean isWhite = gameBoardModel.getIsBluePlayerTurn();

    int frameWidth = getWidth(); // Get the current width of the frame
    int frameHeight = getHeight(); // Get the current height of the frame

    // Calculate the new size of each tile based on the frame size
    int newBoxSize = Math.min(frameWidth / 7, (frameHeight - 50) / 6);

    for (int y = 0; y < gameBoardModel.getBoardHeight(); y++) {
      for (int x = 0; x < gameBoardModel.getBoardWidth(); x++) {
        if (isWhite) {
          g.setColor(Color.white);
        } else {
          g.setColor(Color.gray);
        }
        g.fillRect(
          (x * newBoxSize) + 25,
          (y * newBoxSize) + 25,
          newBoxSize,
          newBoxSize
        ); // position of chess
        // board
        isWhite = !isWhite;
      }
    }
  }

  // Draw the chess tile to green for the available move.
  // Only draw if a piece is selected.
  // Koh Jia Jie
  private void drawAvailableMove(Graphics g) {
    GameBoardModel gameBoardModel = GameBoardModel.getGameBoardModel();
    Color availableMoveColor = new Color(0, 255, 0, 128);

    int frameWidth = getWidth(); // Get the current width of the frame
    int frameHeight = getHeight(); // Get the current height of the frame

    // Calculate the new size of each tile based on the frame size
    int newBoxSize = Math.min(frameWidth / 7, (frameHeight - 50) / 6);

    if (gameBoardModel.getPieceSelected()) {
      for (int y = 0; y < gameBoardModel.getBoardHeight(); y++) {
        for (int x = 0; x < gameBoardModel.getBoardWidth(); x++) {
          if (
            gameBoardModel
              .getCanMoveCoordinates()
              .contains(gameBoardModel.getGameBoard()[x][y])
          ) {
            g.setColor(availableMoveColor);
            g.fillRect(
              (x * newBoxSize) + 25,
              (y * newBoxSize) + 25,
              newBoxSize,
              newBoxSize
            ); // position of
            // chess board
          }
        }
      }
    }
  }

  // Draw the image of the chess piece if it exists on the coordinate
  // Koh Jia Jie
  // Tan Hui Jeen
  private void drawPieces(Graphics g) {
    GameBoardModel gameBoardModel = GameBoardModel.getGameBoardModel();

    int frameWidth = getWidth(); // Get the current width of the frame
    int frameHeight = getHeight(); // Get the current height of the frame

    // Calculate the new size of each tile based on the frame size
    int newBoxSize = Math.min(frameWidth / 7, (frameHeight - 50) / 6);

    for (int i = 0; i < gameBoardModel.getBoardWidth(); i++) {
      for (int j = 0; j < gameBoardModel.getBoardHeight(); j++) {
        if (gameBoardModel.getObject(i, j) != null) {
          // Resize image to fit the current frame size
          Image image = gameBoardModel
            .getGameBoard()[i][j].getCoordinateObject()
            .getImage()
            .getScaledInstance(
              newBoxSize,
              newBoxSize,
              BufferedImage.SCALE_SMOOTH
            );

          g.drawImage(
            image,
            (
              gameBoardModel.getGameBoard()[i][j].getCoordinateX() * newBoxSize
            ) +
            25,
            (
              gameBoardModel.getGameBoard()[i][j].getCoordinateY() * newBoxSize
            ) +
            25,
            null
          );
        }
      }
    }
  }

  // pop up msg
  // Tang Yu Xuan
  // Koh Jia Jie
  // Poh Ern Qi
  public boolean showSurrenderMsg() {
    GameBoardModel gameBoardModel = GameBoardModel.getGameBoardModel();
    loadGameOverImg();
    JLabel imageLabel = new JLabel(gameOverIcon);
    // Show a confirmation dialog
    int result = JOptionPane.showConfirmDialog(
      null,
      "Are you sure you want to surrender?",
      "Surrender",
      JOptionPane.YES_NO_OPTION
    );

    // If the user clicks "Yes" (result == JOptionPane.YES_OPTION)
    if (result == JOptionPane.YES_OPTION) {
      JOptionPane.showMessageDialog(
        null,
        imageLabel,
        gameBoardModel.getPlayerTurnInString() + " has surrender !",
        JOptionPane.PLAIN_MESSAGE
      );
      frame.dispose();
      endGame();
      return true;
    } else {
      return false;
    }
  }

  // Tang Yu Xuan
  // Koh Jia Jie
  public void showGameOverMSG() {
    GameBoardModel gameBoardModel = GameBoardModel.getGameBoardModel();
    loadGameOverImg();
    JLabel imageLabel = new JLabel(gameOverIcon);
    JOptionPane.showMessageDialog(
      null,
      imageLabel,
      gameBoardModel.getPlayerTurnInString() + " has Win ! GameOver",
      JOptionPane.PLAIN_MESSAGE
    );
    frame.dispose();
    endGame();
  }

  // display the side msg
  // Koh Jia Jie
  // Poh Ern Qi
  private void changeSideContent() {
    GameBoardModel gameBoardModel = GameBoardModel.getGameBoardModel();
    if (!gameBoardModel.getIsBluePlayerTurn()) playerLabel.setText(
      "Current Turn : yellow"
    ); else playerLabel.setText("Current Turn : blue");
    messageLabel.setText(gameBoardModel.getSideBarMessage());
    messageLabel.setForeground(Color.red);
    roundLabel.setText(
      "Current Round : " + (gameBoardModel.getCurrentRound() + 1)
    );
  }

  // load game over img
  // Poh Ern Qi
  public void loadGameOverImg() {
    try {
      gameOverIcon =
        new ImageIcon(
          ImageIO.read(getClass().getResource("/images/gameover.png"))
        );
    } catch (Exception e) {
      System.out.print("can't load image");
      e.printStackTrace();
    }
  }

  // load help img
  // Poh Ern Qi
  public void loadHelpImg() {
    try {
      helpImageIcon =
        new ImageIcon(ImageIO.read(getClass().getResource("/images/help.png")));
    } catch (Exception e) {
      System.out.print("can't load image");
      e.printStackTrace();
    }
  }

  // Print the chess piece selected. (in the terminal)
  // Koh Jia Jie
  public void selected(int x, int y) {
    GameBoardModel gameBoardModel = GameBoardModel.getGameBoardModel();
    System.out.println(
      gameBoardModel.getGameBoard()[x][y].getCoordinateObject() +
      " is selected."
    );
  }

  // Observer
  @Override
  public void update() {
    this.repaint();
  }

  // set the visibility of home frame to false when the game start, so that the
  // home frame will not show after the game start
  // Koh Jia Jie
  public void startGame() {
    homeFrame.setVisible(false);
  }

  // set the visibility of home frame to true after the end of the game
  // Koh Jia Jie
  public void endGame() {
    homeFrame.setVisible(true);
    startBtn.setText("Start");
  }

  // method to get save button
  public JButton getSaveButton() {
    return saveBtn;
  }

  // method to get help button
  public JButton getHelpButton2() {
    return helpBtn2;
  }

  // method to get surrender button
  public JButton getSurrenderButton() {
    return surrenderBtn;
  }

  // method to get start button
  public JButton getStartButton() {
    return startBtn;
  }

  // method to get load button
  public JButton getLoadButton() {
    return loadBtn;
  }

  // method to get help button
  public JButton getHelpButton() {
    return helpBtn;
  }

  // method to get continue button
  public JButton getContinueButton() {
    return continueBtn;
  }

  // method to get exit button
  public JButton getExitButton() {
    return exitBtn;
  }

  // method to get help image icon
  public ImageIcon getHelpImageIcon() {
    return helpImageIcon;
  }

  // method to get box size of the gameBoard
  public int getBoxSize() {
    return Math.min(getWidth() / 7, (getHeight() - 50) / 6);
  }
}
