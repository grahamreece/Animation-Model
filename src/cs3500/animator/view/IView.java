package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import model.IModel;
import model.keyframe.IKeyFrame;
import model.shape.IShape;

/**
 * The view for the animation. The view lets the users see/view the animation, depending on the type
 * of view they are using. The view can be textual, visual, or both (such as the SVGView). The view
 * constructs a model according to the information on the inputted text files. The view outputs the
 * animation to a specified location (file). If the output is not specified, the output is
 * system.out. The view also lets the user input the speed of the animation. The animation will be
 * played with the given speed in ticks per sec. If the speed is not chosen/given, the speed is set
 * to 1 tick per sec. The view is required an input (for the model information).
 */
public interface IView {

  /**
   * Plays the animation according to what type of View the View is. If the view is text based, it
   * will output the text to the system or as a file. If the view is animation based, it plays the
   * animation for the user.
   */
  void playAnimation();

  /**
   * Returns the text for the view animation if it is a text-based view. The SVGView and TextualView
   * are considered text-based views since it returns a text which can be interpreted or seen as an
   * animation.
   *
   * @return the text for the view animation. If not, return null
   * @throws UnsupportedOperationException if the view is not a type of a text view
   */
  String getTextDescription() throws UnsupportedOperationException;

  /**
   * Changes the boolean indicator of whether the animation is looping for this view from its
   * boolean value to the opposite boolean value.
   *
   * @throws UnsupportedOperationException if the view does not support this method or looping
   *                                        functionality in general.
   */
  void changeLoopState() throws UnsupportedOperationException;

  /**
   * Sets the action listener of this view for the various buttons and panels that need an action
   * listener/controller to act on them.
   *
   * @param event the action listener that is being set for the various buttons and panels.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  void setButtonListener(ActionListener event) throws UnsupportedOperationException;

  /**
   * Retrieves the timer field of the view, if the view supports having a timer functionality/field.
   *
   * @return the timer for the view
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  Timer getTimer() throws UnsupportedOperationException;

  /**
   * Resets the tick count of the current view, initializing it to zero to indicate the restarting
   * of the view or animation.
   *
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  void restartTick() throws UnsupportedOperationException;

  /**
   * Sets the speed/ticks per second field of the view to the given int parameter for ticks per
   * second. When this speed is set during the display of the editor (when the increase or decrease
   * speed button is hit) to an invalid speed, a JOptionPane pop-up window will display indicating
   * that the speed cannot be set to that point as well.
   *
   * @param newTicksPerSec the new speed to set for the view
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  void setTicksPerSec(int newTicksPerSec) throws UnsupportedOperationException;

  /**
   * Retrieves the ticks per second, or speed, field for the view, if speed functionality is
   * supported for the view.
   *
   * @return the ticks per second of the view.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getTicksPerSec() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the tick text field of the modify key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the tick number text field of the modify KeyFrame panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getModTextT() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the X position text field of the modify key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the x position in the text field of the modify KeyFrame panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getModTextX() throws UnsupportedOperationException, IllegalStateException;

  /**
   * Retrieves the entered value for the Y position text field of the modify key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the y position in the text field of the modify KeyFrame panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getModTextY() throws UnsupportedOperationException, IllegalStateException;

  /**
   * Retrieves the entered value for width text field of the modify key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the width in the text field of the modify KeyFrame panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getModTextW() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for height text field of the modify key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the height in the text field of the modify KeyFrame panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getModTextH() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for red color text field of the modify key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the red color in the text field of the modify KeyFrame panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getModTextR() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for green color text field of the modify key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the green color in the text field of the modify KeyFrame panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getModTextG() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for blue color text field of the modify key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the blue color in the text field of the modify KeyFrame panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getModTextB() throws UnsupportedOperationException;

  /**
   * Retrieves the entered string for the shape name text field of the add key frame JPanel of the
   * view's set of panels.
   *
   * @return the entered string for the text field of the adding KeyFrames panel's shape name text
   *          field.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  String getNameTextKey() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the tick text field of the add key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the tick number text field of the adding KeyFrames panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getTTextKey() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the X position text field of the add key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the x position int text field of the adding KeyFrames panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getXTextKey() throws UnsupportedOperationException, IllegalStateException;

  /**
   * Retrieves the entered value for the Y position text field of the add key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the y position int text field of the adding KeyFrames panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getYTextKey() throws UnsupportedOperationException, IllegalStateException;

  /**
   * Retrieves the entered value for the width text field of the add key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the width int text field of the adding KeyFrames panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getWTextKey() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the height text field of the add key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the height int text field of the adding KeyFrames panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getHTextKey() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the red color text field of the add key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the red color int text field of the adding KeyFrames panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getRTextKey() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the green color text field of the add key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the green color int text field of the adding KeyFrames panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getGTextKey() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the blue color text field of the add key frame JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the blue color int text field of the adding KeyFrames panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getBTextKey() throws UnsupportedOperationException;

  /**
   * Retrieves the entered string for the shape name text field of the adding shapes JPanel of the
   * view's set of panels.
   *
   * @return the entered string for the text field of the adding adding shapes panel's shape name
   *          text field.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  String getNameText() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the X position text field of the adding shape JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the x position int text field of the adding shapes panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getXText() throws UnsupportedOperationException, IllegalStateException;

  /**
   * Retrieves the entered value for the Y position text field of the adding shape JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the y position int text field of the adding shapes panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getYText() throws UnsupportedOperationException, IllegalStateException;

  /**
   * Retrieves the entered value for the width text field of the adding shape JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the width int text field of the adding shapes panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getWText() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the height text field of the adding shape JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the height int text field of the adding shapes panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getHText() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the red color text field of the adding shape JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the red color int text field of the adding shapes panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getRText() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the green color text field of the adding shape JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the green color int text field of the adding shapes panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getGText() throws UnsupportedOperationException;

  /**
   * Retrieves the entered value for the blue color text field of the adding shape JPanel of the
   * view's set of panels. Catches cases were the entered value is not a number.
   *
   * @return the entered value for the blue color int text field of the adding shapes panel.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  int getBText() throws UnsupportedOperationException;

  /**
   * Retrieves the model that was built using the builder class for the view.
   *
   * @return the model that was built for the view.
   */
  IModel getModel();

  /**
   * Adds the given key frame, and the given string for the desired ID for the keyframe to the view
   * if it supports having a list of keyframes for the animation.
   *
   * @param kf the keyframe to add to the view
   * @param id the id to set for the keyframe being added to the view
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  void addKeyFrame(IKeyFrame kf, String id) throws UnsupportedOperationException;

  /**
   * Deletes the shape with the given shape name from the view of the animation. If a shape with the
   * shape name is deleted, all subsequent shape states of the shape with that shape name are
   * deleted, and all of the motions for the shape are deleted as well.
   *
   * @param shapeName the string representing the name of the shape to delete
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  void deleteShape(String shapeName) throws UnsupportedOperationException;

  /**
   * Retrieve the JList holding the list of shapes for the view of the animation.
   *
   * @return the JList of shapes for the view
   * @throws UnsupportedOperationException if the view does not support the functionality.
   */
  JList getShapeList() throws UnsupportedOperationException;

  /**
   * Deletes the given key frame from the view, if it is in the view of the animation and can
   * be deleted from the view.
   *
   * @param kf the key frame to delete from the view for the animation
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  void deleteKF(IKeyFrame kf) throws UnsupportedOperationException;

  /**
   * Retrieves the radio button for adding a rectangle to the animation view.
   *
   * @return the JRadioButton for adding rectangles to the editor view
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  JRadioButton getRectButton() throws UnsupportedOperationException;

  /**
   * Retrieves the radio button for adding an ellipse to the animation view.
   *
   * @return the JRadioButton for adding ellipses to the editor view
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  JRadioButton getEllipseButton() throws UnsupportedOperationException;

  /**
   * Adds the given shape to the animation view, and gives it the given ID string for the shape
   * identifier of the shape when it is added.
   *
   * @param shape the shape to add to the view
   * @param id the id that the shape wants to be given when it is added to the view
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  void addShape(IShape shape, String id) throws UnsupportedOperationException;

  /**
   * Modifies the key frame in the view that is selected in the JList of the animation view, giving
   * it all of the given parameters for the tick of the key frame, x position of the key frame, y
   * position of the key frame, width of the key frame, height of the key frame, red color of the
   * key frame, green color of the key frame, and blue color of the key frame.
   *
   * @param t the new tick to give to the key frame
   * @param x the new x position to give to the key frame
   * @param y the new y position to give to the key frame
   * @param w the width to give to the key frame
   * @param h the height to give to the key frame
   * @param r the red color value to give to the key frame
   * @param g the green color value to give to the key frame
   * @param b the blue color value to give to the key frame.
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  void modifyKF(int t, int x, int y, int w, int h, int r, int g, int b)
      throws UnsupportedOperationException;

  /**
   * Returns the shapeListModel in the view as a DefaultListModel for the JList.
   *
   * @return the shapeListModel in the view
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  DefaultListModel getShapeListModel() throws UnsupportedOperationException;

  /**
   * Returns the KeyFrame JList for the view.
   *
   * @return the KeyFrame JList for the view
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  JList getKFList() throws UnsupportedOperationException;

  /**
   * Removes the KeyFrame for the given ith element of the model in the view.
   *
   * @param i the number for the element location in the keyFrameList in the model
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  void removeKeyFrame(int i) throws UnsupportedOperationException;

  /**
   * Removes the shape for the given ith element of the model in the view.
   *
   * @param i the number for the element location in the shapeList in the model
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  void removeShape(int i) throws UnsupportedOperationException;

  /**
   * Return the shapeArrayList of the model in the view.
   *
   * @return the shapeArrayList for the model in the view
   * @throws UnsupportedOperationException if the view does not support this functionality.
   */
  ArrayList<IShape> getShapeArrayList() throws UnsupportedOperationException;

  /**
   * Return true if the isLooping field is true. Return false otherwise.
   *
   * @return the loop state for the animation
   */
  boolean getLoopState() throws UnsupportedOperationException;

  /**
   * Returns the JButton representing the play button of the playback features of the view's UI.
   *
   * @return the play button of the editor view
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  JButton getPlayButton() throws UnsupportedOperationException;

  /**
   * Returns the JButton representing the pause button of the playback features of the view's UI.
   *
   * @return the pause button of the editor view
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  JButton getPauseButton() throws UnsupportedOperationException;

  /**
   * Returns the JButton representing the restart button of the playback features of the view's UI.
   *
   * @return the restart button of the editor view's UI
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  JButton getRestartButton() throws UnsupportedOperationException;

  /**
   * Returns the JButton representing the incremental increase in speed button of the playback
   * features of the view's UI.
   *
   * @return the increase speed button of the editor view
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  JButton getIncreaseSpeedButton() throws UnsupportedOperationException;

  /**
   * Returns the JButton representing the incremental decrease in speed button of the playback
   * features of the view's UI.
   *
   * @return the increase speed button of the editor view
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  JButton getDecreaseSpeedButton() throws UnsupportedOperationException;

  /**
   * Returns the JCheckBox representing the checked and unchecked button of the looping feature of
   * the playback features of the view's UI.
   *
   * @return the looping checkbox of the editor view
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  JCheckBox getLoopCheckBox() throws UnsupportedOperationException;

  /**
   * Returns the JButton representing the modify keyframe button of the keyframe UI controls of the
   * view.
   *
   * @return the modify keyframe button of the editor view's UI
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  JButton getModifyKFButton() throws UnsupportedOperationException;

  /**
   * Returns the JButton representing the remove keyframe button of the keyframe UI controls of the
   * view.
   *
   * @return the remove keyframe button of the editor view's UI
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  JButton getRemoveKFButton() throws UnsupportedOperationException;

  /**
   * Returns the JButton representing the adding keyframe button of the keyframe UI controls of the
   * view.
   *
   * @return the add keyframe button of the editor view's UI
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  JButton getAddKFButton() throws UnsupportedOperationException;

  /**
   * Returns the JButton representing the adding shape button of the add a shape UI features of the
   * view.
   *
   * @return the add shape button of the editor view's UI
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  JButton getAddShapeButton() throws UnsupportedOperationException;

  /**
   * Returns the JButton representing the remove shape button of the removing a shape UI features
   * of the view.
   *
   * @return the remove shape button of the editor view's UI
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  JButton getRemoveShapeButton() throws UnsupportedOperationException;
}