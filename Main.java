import Controller.GameBoardController;
import Model.GameBoardModel;
import View.GameBoardView;

public class Main {

  public static void main(String[] args) {
    GameBoardModel gameBoardModel = GameBoardModel.getGameBoardModel();
    GameBoardView gameBoardView = new GameBoardView();
    gameBoardModel.setObserver(gameBoardView);
    new GameBoardController(gameBoardModel, gameBoardView);
  }
}
