// MVC Design Pattern is applied here
// This class is act as Controller in the Design pattern
// This class handler the click event and all the action listener of the buttons.
/**
 * @author Tan Hui Jeen 121101196
 * @author Koh Jia Jie 1211102879
 * @author Tang Yu Xuan 1211103236
 * @author Poh Ern Qi 1211101398
 * @author Teh Yvonne 1211100571
 */

package Controller;

import Model.GameBoardModel;
import View.GameBoardView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GameBoardController {

  // Data Field
  private GameBoardModel gameBoardModel;
  private GameBoardView gameBoardView;
  private int isSelected = 0; // Represent nth click, reset to 0 after the second click.

  public GameBoardController(
    GameBoardModel gameBoardModel,
    GameBoardView gameBoardView
  ) {
    this.gameBoardModel = gameBoardModel;
    this.gameBoardView = gameBoardView;
    setHomeViewListener();
    addMouseListener();
    setGameViewListener();
  }

  public void updateGameBoard() {
    gameBoardView.setFrame();
    gameBoardView.update();
  }

  // set home view
  // start, help, load and exit button listener
  // Poh Ern Qi
  public void setHomeViewListener() {
    gameBoardView.getStartButton().addActionListener(startBtnListener);
    gameBoardView.getHelpButton().addActionListener(helpBtnListener);
    gameBoardView.getExitButton().addActionListener(exitBtnListener);
    gameBoardView.getLoadButton().addActionListener(loadBtnListener);
  }

  // set game view
  // Surrender, help and save button listener
  // Poh Ern Qi
  public void setGameViewListener() {
    gameBoardView.getSurrenderButton().addActionListener(surrenderListener);
    gameBoardView.getHelpButton2().addActionListener(helpBtn2Listener);
    gameBoardView.getSaveButton().addActionListener(saveBtnListener);
  }

  // Poh Ern Qi
  ActionListener startBtnListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      gameBoardView.startGame();

      updateGameBoard();
    }
  };

  // help btn listener
  // Poh Ern Qi
  ActionListener helpBtnListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      gameBoardView.showGameHelp();
    }
  };

  // surrender listener
  // Poh Ern Qi
  ActionListener surrenderListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (gameBoardView.showSurrenderMsg()) {
        gameBoardModel.resetGame();
      }
    }
  };

  // help btn listener
  // Poh Ern Qi
  ActionListener helpBtn2Listener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      gameBoardView.showGameHelp();
    }
  };

  // exit btn listener
  // Poh Ern Qi
  ActionListener exitBtnListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      int exitMsg = gameBoardView.showExitConfirmationDialog();
      if (exitMsg == JOptionPane.YES_OPTION) {
        System.exit(0);
      }
    }
  };

  // load btn listener
  // Tan Hui Jeen
  // Teh Yvonne
  ActionListener loadBtnListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      // Create the file chooser dialog with a filter for text files
      JFileChooser file = new JFileChooser();
      file.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
      // Show the open file dialog and takes user's selection
      int output = file.showOpenDialog(gameBoardView);
      // If the user selects a file, get the absolute path of te selected file
      if (output == JFileChooser.APPROVE_OPTION) {
        String fileName = file.getSelectedFile().getAbsolutePath();

        System.out.println("Loading file: " + fileName);

        // When loading a file before starting a new game
        if (gameBoardView == null) {
          gameBoardView = new GameBoardView();
          GameBoardModel.getGameBoardModel().setObserver(gameBoardView);
        }
        // Load the game state form the selected file
        GameBoardModel loadedModel = gameBoardModel.saveLoad("load", fileName);

        if (loadedModel != null) {
          System.out.println("Game loaded successfully.");

          // Update the frame and show the loaded game on the EDT
          SwingUtilities.invokeLater(
            new Runnable() {
              @Override
              public void run() {
                gameBoardView.update();
              }
            }
          );
          // Close the home frame
          gameBoardView.startGame();

          // Update the frame and show the loaded game
          updateGameBoard();
        } else {
          System.out.println("Failed to load the game.");
        }
      }
    }
  };

  // save btn listener
  // Tan Hui Jeen
  // Teh Yvonne
  ActionListener saveBtnListener = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      // If a game is in progress,the gameBoardView is not null
      if (gameBoardView != null) {
        // Lets the user enter a file name with a .txt extensio
        String fileName = JOptionPane.showInputDialog(
          "Enter file name (.txt): "
        );
        if (fileName != null) {
          // To ensure it is a .txt file
          if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
          }
          // Save the current game state to the specified file name
          gameBoardModel.saveLoad("save", fileName);
        }
      }
    }
  };

  // This method handle the clicked event
  // Koh Jia jie
  // Poh Ern Qi
  // Tan Hui Jeen
  // Tang Yu Xuan
  private void addMouseListener() {
    gameBoardView.addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          // find the clicked x and y
          int x = (e.getX() - 25) / gameBoardView.getBoxSize();
          int y = (e.getY() - 25) / gameBoardView.getBoxSize();

          // To make sure the player clicked on the game board
          if (x < 7 && y < 6) {
            // to validate error
            boolean validationError = false;
            // to check if the chessPiece clicked is current player's chessPiece
            if (isSelected == 0 && gameBoardModel.getObject(x, y) != null) {
              if (
                gameBoardModel.getObject(x, y).checkIsBlue() ==
                gameBoardModel.getIsBluePlayerTurn()
              ) {
                validationError = false;
              } else {
                validationError = true;
              }
            }

            // if clicked chessPiece is current player's chessPiece then proceed
            if (!validationError) {
              isSelected++;
              // Check if it is clicked opponent's chess piece, if yes then move, if no then
              // select the chess piece
              if (
                gameBoardModel.getLastChessPiece() != null &&
                gameBoardModel.getObject(x, y) != null
              ) {
                if (
                  gameBoardModel.getLastChessPiece().checkIsBlue() !=
                  gameBoardModel.getObject(x, y).checkIsBlue()
                ) {
                  isSelected = 2;
                } else if (
                  gameBoardModel.getObject(x, y) !=
                  gameBoardModel.getLastChessPiece()
                ) {
                  isSelected = 1;
                } else {
                  gameBoardModel.setSideBarMessage(null);
                  gameBoardModel.setPieceSelected(false);
                }
              }

              // If the coordinate pressed contain a chessPiece
              if (gameBoardModel.getObject(x, y) != null && isSelected != 2) {
                // set the side bar message, coordinate clicked
                gameBoardModel.setSideBarMessage(
                  gameBoardModel.getObject(x, y).toString() + " is Selected."
                );
                clicked(x, y);
                gameBoardModel.setLastCoordinate(
                  gameBoardModel.getGameBoard()[x][y]
                );
                // display the available path and move the chesspiece to the selected coordinate
                gameBoardModel.setCanMoveCoordinates(
                  gameBoardModel
                    .getMovement(x, y)
                    .availableMove(
                      gameBoardModel.getLastCoordinate(),
                      gameBoardModel
                    )
                );
                isSelected = 1;
                gameBoardModel.setPieceSelected(true);
              } else if (isSelected == 2 && gameBoardModel.getPieceSelected()) {
                gameBoardModel.setSideBarMessage(null);
                Map<String, Boolean> resultMap = gameBoardModel
                  .getLastChessPiece()
                  .getChessPieceMovement()
                  .move(
                    gameBoardModel.getLastCoordinate(),
                    gameBoardModel.getGameBoard()[x][y],
                    gameBoardModel.getCanMoveCoordinates(),
                    gameBoardModel.getLastChessPiece()
                  );

                // get map values.
                boolean canMove = resultMap.get("canMove");
                boolean isCapturedSun = resultMap.get("isCapturedSun");
                // If the player does not capture opponent's sun piece.
                if (canMove == true && isCapturedSun == false) {
                  // reset
                  isSelected = 0;
                  gameBoardModel.setIsBluePlayer(
                    !gameBoardModel.getIsBluePlayerTurn()
                  );
                  gameBoardModel.setLastChessPiece(null);
                  gameBoardModel.resetPieces();
                  gameBoardModel.setPieceSelected(false);
                } else if (canMove == true && isCapturedSun == true) { // If the player capture
                  // opponent's sun piece
                  // captured the sun and show dialog
                  gameBoardModel.setPieceSelected(false);
                  gameBoardView.showGameOverMSG();
                  gameBoardModel.resetGame();
                } else {
                  isSelected = 1; // Repeat this until the correct coordinate is selected
                }
              } else {
                // Movement completed
                isSelected = 0;
              }
            }
          }
        }
      }
    );
  }

  // interact with View Object
  private void clicked(int x, int y) {
    gameBoardView.selected(x, y);
  }
}
