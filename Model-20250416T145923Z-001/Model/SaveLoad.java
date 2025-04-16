// Facade Design Pattern applied here
// The save and load classes are hid due to its complexity, this class acts as a bridge to the user to save and load
// so that it is easier to use the methods.
/**
 * @author Tan Hui Jeen 121101196
 * @author Teh Yvonne 1211100571
 */

package Model;

//Save and load the game state to and from a file
public class SaveLoad {

  // Based on specified action perform save or load action
  public static GameBoardModel saveLoad(
    String action,
    String fileName,
    GameBoardModel gameBoardModel
  ) {
    // Save the game and return the same GameBoardModel instance
    if ("save".equals(action)) {
      SaveGame.saveGame(fileName, gameBoardModel);
      return gameBoardModel;
    } else if ("load".equals(action)) { // Return the GameBoardModel loaded from the txt file
      return LoadGame.loadGame(fileName);
    }
    return null;
  }
}
