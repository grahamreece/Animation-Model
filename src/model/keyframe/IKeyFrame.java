package model.keyframe;

import model.shape.IShape;
import model.shape.ShapeType;

/**
 * The interface for representing a KeyFrame for a shape that can be animated. A KeyFrame is
 * determined by a tick, shape name, the shape's shape type, and the elements for the shape
 * including it's position, size, and color. A KeyFrame basically holds a shape. An animation can be
 * animated with a minimum of two KeyFrames. A motion can be animated with the KeyFrame's shape's
 * initial shape for the motion being the one KeyFrame and the shape's final shape for the motion
 * being another KeyFrame.
 */
public interface IKeyFrame {

  /**
   * Returns the keyframe ID of the KeyFrame.
   *
   * @return the string representing the ID of the key frame.
   */
  String getKeyFrameID();

  /**
   * Sets the Keyframe Id to the given String. Throws an exception if the given string to set it to
   * is an empty string.
   *
   * @param keyFrameID the string to set the keyframe ID of the keyframe to
   * @throws IllegalArgumentException if the string to set the ID to is an empty string.
   */
  void setKeyFrameID(String keyFrameID) throws IllegalArgumentException;

  /**
   * Returns the string representing the shapeName of the key frame.
   *
   * @return the string representing the shape name for the key frame.
   */
  String getShapeName();

  /**
   * Sets the Keyframe shape name to the given String. Throws an exception if the given string to
   * set it to is an empty string.
   *
   * @param name the string to set the shape name of the keyframe to
   * @throws IllegalArgumentException if the string to set the shape name to is an empty string.
   */
  void setShapeName(String name) throws IllegalArgumentException;

  /**
   * Returns the tick field of the key frame.
   *
   * @return the int value of the keyframe's tick.
   */
  int getTick();

  /**
   * Sets the tick of the keyframe to the given int value. Throws an exception if that value is not
   * a positive integer.
   *
   * @param t the tick to set for the keyframe.
   * @throws IllegalArgumentException if the tick to set for the keyframe is not positive.
   */
  void setTick(int t) throws IllegalArgumentException;

  /**
   * Returns the width of the keyframe.
   *
   * @return the int value for the width value of the keyframe.
   */
  int getWidth();

  /**
   * Returns the height of the keyframe.
   *
   * @return the int value for the height of the keyframe.
   */
  int getHeight();

  /**
   * Sets the dimension of the keyframe to the given int values for the width and height to set the
   * values to. Throws an exception if these values are not greater than zero.
   *
   * @param w the int to set the width of the keyframe to
   * @param h the int to set the height of the keyframe's dimension to.
   * @throws IllegalArgumentException if any of the values to set for the width or height are not
   *                                   greater than zero.
   */
  void setDimension(int w, int h) throws IllegalArgumentException;

  /**
   * Retrieves the x value of the keyframe.
   *
   * @return the x value of the key frame.
   */
  int getX();

  /**
   * Retrieves the y value of the keyframe.
   *
   * @return the y value of the keyframe.
   */
  int getY();

  /**
   * Sets the position of the key frame to the given values for the x and y positions.
   *
   * @param x the x value to set for the key frame's position.
   * @param y the y value to set for the key frame's position.
   */
  void setPosition(int x, int y);

  /**
   * Return the red color value of the key frame.
   *
   * @return the red color int value of the key frame.
   */
  int getRed();

  /**
   * Return the green color value of the key frame.
   *
   * @return the green color int value of the key frame.
   */
  int getGreen();

  /**
   * Return the blue color value of the key frame.
   *
   * @return the blue color int value of the key frame.
   */
  int getBlue();

  /**
   * Sets the color of the key frame to the given values for the red, green, and blue fill color
   * values. Throws an exception if any of these values are not in the acceptable color value range.
   *
   * @param r the red color value to set for the key frame.
   * @param g the green color value to set for the key frame.
   * @param b the blue color value to set for the key frame.
   * @throws IllegalArgumentException if any of the values are not greater than or equal to 0, or
   *                                  less than or equal to 255, the acceptable color value range.
   */
  void setColor(int r, int g, int b) throws IllegalArgumentException;

  /**
   * Returns the shape for this KeyFrame.
   *
   * @return the Shape for the KeyFrame
   */
  IShape getShape();

  /**
   * Returns the shape type for the shape for this KeyFrame.
   *
   * @return the shape type for the shape for this KeyFrame
   */
  ShapeType getShapeType();
}