package model.motion;

import model.shape.IShape;

/**
 * The interface for representing a motion of a shape that can be drawn and animated. A motion is
 * determined with its initial shape, ending shape, initial tick and ending tick, and the motion ID.
 * A motion animates the initial shape to change to its ending shape from the initial tick to the
 * end tick. This interface is used in the model to represent, animate, and identify a shape
 * motion.
 */
public interface IMotion {

  /**
   * Returns the starting tick of the motion as an int.
   *
   * @return the start tick of the motion
   */
  int getStartTick();

  /**
   * Returns the ending tick of the motion as an int.
   *
   * @return the end tick of the motion
   */
  int getEndTick();

  /**
   * Returns the motion ID of the motion as a String.
   *
   * @return the motion ID of the motion
   */
  String getMotionID();

  /**
   * Returns the starting shape of the motion as an IShape.
   *
   * @return the starting shape of the motion
   */
  IShape getStartShape();

  /**
   * Returns the ending shape of the motion as an IShape.
   *
   * @return the ending shape of the motion
   */
  IShape getEndShape();

  /**
   * Sets the motion ID of the motion to the given String.
   *
   * @param motionID the ID that the motion will be set to
   * @throws IllegalArgumentException if the given String is empty
   */
  void setMotionID(String motionID) throws IllegalArgumentException;

  /**
   * Sets the initial shape of the motion to the given IShape.
   *
   * @param initShape the shape that the motion's initial shape will be set to
   * @throws IllegalArgumentException if the given starting shape's shape name and the ending
   *                                   shape's shape name are not the same.
   */
  void setStartShape(IShape initShape) throws IllegalArgumentException;

  /**
   * Sets the end shape of the motion to the given IShape.
   *
   * @param endShape the shape that the motion's end shape will be set to
   * @throws IllegalArgumentException if the starting shape's shape name and the given ending
   *                                     shape's shape name are not the same.
   */
  void setEndShape(IShape endShape) throws IllegalArgumentException;

  /**
   * Sets the starting tick of the motion to the given int.
   *
   * @param t1 the tick that the motion's start tick will be set to
   * @throws IllegalArgumentException if the given tick is not a positive integer or 0
   */
  void setStartTick(int t1) throws IllegalArgumentException;

  /**
   * Sets the ending tick of the motion to the given int.
   *
   * @param t2 the tick that the motion's end tick will be set to
   * @throws IllegalArgumentException if the given tick is not a positive integer
   */
  void setEndTick(int t2) throws IllegalArgumentException;

  /**
   * Sets the initial shape's position to the given x and y elements as Point2D.
   *
   * @param x the x element of the position that the starting shape will be set to
   * @param y the y element of the position that the starting shape will be set to
   */
  void setInitialPosition(int x, int y);

  /**
   * Sets the initial shape's position to the given integers as Dimension.
   *
   * @param width the width of the dimension that the starting shape will be set to
   * @param height the height of the dimension that the starting shape will be set to
   * @throws IllegalArgumentException if the width and/or height is less than 0
   */
  void setInitialSize(int width, int height) throws IllegalArgumentException;

  /**
   * Sets the initial shape's color to the given r g b colors.
   *
   * @param r the red element of the RGB color
   * @param g the green element of the RGB color
   * @param b the blue element of the RGB color
   * @throws IllegalArgumentException if r, g, and/or b is not within the range of 0 to 255.
   */
  void setInitialColor(int r, int g, int b) throws IllegalArgumentException;

  /**
   * Sets the final shape's position to the given x and y elements as Point2D.
   *
   * @param x the x element of the position that the final shape will be set to
   * @param y the y element of the position that the final shape will be set to
   */
  void setFinalPosition(int x, int y);

  /**
   * Sets the final shape's position to the given integers as Dimension.
   *
   * @param width the width of the dimension that the final shape will be set to
   * @param height the height of the dimension that the final shape will be set to
   * @throws IllegalArgumentException if the width and/or height is less than 0
   */
  void setFinalSize(int width, int height) throws IllegalArgumentException;

  /**
   * Sets the final shape's color to the given r g b colors.
   *
   * @param r the red element of the RGB color
   * @param g the green element of the RGB color
   * @param b the blue element of the RGB color
   * @throws IllegalArgumentException if r, g, and/or b is not within the range of 0 to 255.
   */
  void setFinalColor(int r, int g, int b) throws IllegalArgumentException;

  /**
   * Returns the shape and it's state (position, dimension, color, and shape type) for a given tick
   * in the motion.
   *
   * @param t the tick that the shape will be found and returned
   * @return the shape and it's state at the given tick
   * @throws IllegalArgumentException if the given tick is not a positive integer or 0. Or if the
   *                                    given tick is not in between the start tick and the end tick
   * @throws IllegalStateException if the motion holds a shape that does not exist as a ShapeType
   */
  IShape getShapeAtTick(int t) throws IllegalArgumentException, IllegalStateException;

  /**
   * Returns the text description of the motion. Include the declaration of a motion, the shape that
   * is being applied the motion, the start and end tick, and the starting and ending parameters of
   * the shape.
   *
   * @return the text description of the motion
   */
  String getMotionText();

  /**
   * Return the shape name for the initial shape of the motion.
   *
   * @return the shape name for the initial shape of the motion
   */
  String getStartingShapeName();

  /**
   * Return the shape name for the final shape of the motion.
   *
   * @return the shape name for the final shape of the motion
   */
  String getEndingShapeName();

  /**
   * Return the shape identifier for the initial shape of the motion.
   *
   * @return the shape identifier for the initial shape of the motion
   */
  String getStartingShapeId();

  /**
   * Return the shape identifier for the final shape of the motion.
   *
   * @return the shape identifier for the final shape of the motion
   */
  String getEndingShapeId();

  /**
   * Determines if the start shape and the end shape is the same.
   *
   * @return true if the start shape and the end shape is the same
   */
  boolean hasSameStartAndEndShape();

  /**
   * Returns a String for the SVG style motions, which is, in other words, the text to animate
   * in SVG. Using the given integer value for ticks per second, this animation text will change,
   * but will always overall use this implementation of a motion and convert it to animate text
   * for SVG animations. Throws an exception if the ticks per second is not a positive integer.
   *
   * @param ticksPerSec the ticks per second, or speed that the animation is moving at
   * @return a String which represents an animation in SVG for this motion
   * @throws IllegalArgumentException if the given speed is not a positive integer
   */
  String getMotionSVGText(int ticksPerSec) throws IllegalArgumentException;

  /**
   * Determines if this motion contains the given shape, using the given shape's shape name. Outputs
   * true if either of the two shapes in this motion have the given shape's shape name, and
   * otherwise will return false.
   *
   * @param shape the shape to check whether it's in the motion
   * @return true if the shape is in the motion (has the same shape name as the motion's shapes'
   *                 shape name. Otherwise, false.
   */
  boolean containsShape(IShape shape);

  /**
   * Determines if the given motion has the same value for the beginning and ending shapes'
   * X position. Used to determine if there is a change in X position for this motion.
   *
   * @param initS the initial shape for the motion to check if the X position value changes
   * @param finalS the final shape for the motion to check if the X position value changes.
   * @return true if there is not a change in the X position for this motion, and the X position is
   *            the same at the beginning and end of the motion. Otherwise, false.
   */
  boolean sameXInMotion(IShape initS, IShape finalS);

  /**
   * Determines if the given motion has the same value for the beginning and ending shapes'
   * Y position. Used to determine if there is a change in Y position for this motion.
   *
   * @param initS the initial shape for the motion to check if the Y position value changes
   * @param finalS the final shape for the motion to check if the Y position value changes.
   * @return true if there is not a change in the Y position for this motion, and the Y position is
   *            the same at the beginning and end of the motion. Otherwise, false.
   */
  boolean sameYInMotion(IShape initS, IShape finalS);

  /**
   * Determines if the given motion has the same value for the beginning and ending shapes'
   * width. Used to determine if there is a change in width for this motion.
   *
   * @param initS the initial shape for the motion to check if the width value changes
   * @param finalS the final shape for the motion to check if the width value changes.
   * @return true if there is not a change in the width for this motion, and the width is
   *            the same at the beginning and end of the motion. Otherwise, false.
   */
  boolean sameWidthInMotion(IShape initS, IShape finalS);

  /**
   * Determines if the given motion has the same value for the beginning and ending shapes'
   * height. Used to determine if there is a change in height for this motion.
   *
   * @param initS the initial shape for the motion to check if the height value changes
   * @param finalS the final shape for the motion to check if the height value changes.
   * @return true if there is not a change in the height for this motion, and the height is
   *            the same at the beginning and end of the motion. Otherwise, false.
   */
  boolean sameHeightInMotion(IShape initS, IShape finalS);

  /**
   * Determines if the given motion has the same value for the beginning and ending shapes'
   * color. Used to determine if there is a change in color for this motion.
   *
   * @param initS the initial shape for the motion to check if the color value changes
   * @param finalS the final shape for the motion to check if the color value changes.
   * @return true if there is not a change in the color for this motion, and the color is
   *            the same at the beginning and end of the motion. Otherwise, false.
   */
  boolean sameColorInMotion(IShape initS, IShape finalS);

  /**
   * Determines whether the given motion constitutes having an SVG motion, which would be if the
   * X, Y, width, height, or color changes between the motion's starting and ending shape. If any
   * of these values change for the motion, then the motion would constitute needing to be animated
   * in an SVG style animation.
   *
   * @param initS the initial shape for the motion to check if any of the five values, X position,
   *              Y position, width, height, or color change in the motion.
   * @param finalS the final shape for the motion to check if any of the five values, X position,
   *               Y position, width, height, or color change in the motion.
   * @return true if any of the five values change in the motion, and thus true if the motion
   *            constitutes having an SVG motion/animation. Otherwise, false.
   */
  boolean hasSVGMotion(IShape initS, IShape finalS);

  /**
   * Returns a String for setting the visibility attribute for an SVG motion/animation to hidden.
   * This String will set this motion's visibility in SVG to hidden for this motion's starting time
   * and until its end time for the motion's whole duration, using the given ticks per second or
   * integer representing the animation speed. This method will be applied for motions where the
   * shapes do undergo motions. where the shape's elements change throughout. Throws an exception
   * if the ticksPerSec is not a non-zero positive integer.
   *
   * @param ticksPerSec the speed of the motion/animation for the SVG motions
   * @return a String for setting the motion's visibility to hidden for SVG style animation for this
   *          motion's start time until its ending time.
   * @throws IllegalArgumentException if the ticks per seconds is not a positive integer above zero
   */
  String makeMotionHidden(int ticksPerSec) throws IllegalArgumentException;

  /**
   * Returns a String for setting the visibility attribute for an SVG motion/animation to hidden.
   * This String will set this motion's visibility in SVG to hidden for this motion's duration
   * and until its starting time for the motion's whole duration, using the given ticks per second
   * or integer representing the animation speed. This method will be applied for motions where the
   * shapes do not undergo motions, or do nothing where none of their elements change. Throws an
   * exception if the ticks per second is not above zero.
   *
   * @param ticksPerSec the speed of the motion/animation for the SVG motions
   * @return a String for setting the motion's visibility to hidden for SVG style animation for this
   *          motion's duration and until its staring time. Used for motion's where the shapes do
   *          not undergo motions or changes in their attributes.
   * @throws IllegalArgumentException if the ticks per seconds is not a positive integer above zero
   */
  String makeMotionHiddenNoSVG(int ticksPerSec) throws IllegalArgumentException;
}

