// This class is an interface class that to be implemented by View class, contains the update method to be overrided
// The Observer Design Pattern is applied here.
// The update method is to update the view whenever the observer observed certain change on model, so that it will automatically
// repaint the board without going through the Controller
/**
 * @author Koh Jia Jie 1211102879
 */

package Model;

public interface Observer {
  public void update();
}
