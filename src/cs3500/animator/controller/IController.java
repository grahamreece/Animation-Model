package cs3500.animator.controller;

import cs3500.animator.view.IView;
import javax.swing.Timer;

/**
 * The interface for the controller that connects the model and the view. The controller modifies
 * the model depending on what the user is doing in the view. All the actions that occur in the view
 * is applied to the model through the controller. This involves all the buttons and the inputted
 * values for the JTextField.
 */
public interface IController {

  /**
   * Starts the animation using the controller by initializing the view and starting the timer of
   * the animation for the view.
   */
  void start();

  /**
   * Stops the animation using the controller by stopping the timer of the animation for the view.
   */
  void stop();

  /**
   * Restarts the animation using the controller by restarting the timer for the animation for the
   * view, and restarting the count of how many ticks have passed in the animation.
   */
  void restart();

  /**
   * Returns the timer for the view for this controller.
   *
   * @return the timer for the view for this controller.
   */
  Timer getTimer();

  /**
   * Returns the view for the controller.
   *
   * @return the view for the controller.
   */
  IView getView();
}