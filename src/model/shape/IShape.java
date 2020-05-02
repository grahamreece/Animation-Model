package model.shape;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import model.motion.IMotion;

/**
 * The interface for representing a shape that can be drawn. A shape is determined with it's shape
 * type, position, dimension, color, shape's name, and the shape ID. All shapes should include a
 * position, dimension and a color so that the shape can be drawn on a 2D canvas. All shapes should
 * include a shape type so that the model can determine what type of shape it is (to draw). All
 * shapes should include a shape name to represent the shape being used in one animation in the
 * model. All shapes should include a shape ID to identify which point of the animation the shape is
 * being represented. Basically, the shape name groups the identifiers together as a motion contains
 * several shapes with the same shape name, but different identifiers which signals different shape
 * states the shape is in (color, position, dimension). This interface is used in the model to
 * represent and draw a shape for the animation.
 */
public interface IShape {

  /**
   * Returns the shape type, in a form of an enum.
   *
   * @return the type of the shape
   */
  ShapeType getShapeType();

  /**
   * Returns the name of the shape as a String.
   *
   * @return the name of the shape
   */
  String getShapeName();

  /**
   * Returns the width of the shape as an integer.
   *
   * @return the width of the shape
   */
  int getWidth();

  /**
   * Returns the height of the shape as an integer.
   *
   * @return the height of the shape
   */
  int getHeight();

  /**
   * Returns the x position of the shape as an integer.
   *
   * @return the x position of the shape
   */
  int getX();

  /**
   * Returns the y position of the shape as an integer.
   *
   * @return the y position of the shape
   */
  int getY();

  /**
   * Returns the red element of the RGB color as an integer.
   *
   * @return the red element of the color
   */
  int getRed();

  /**
   * Returns the green element of the RGB color as an integer.
   *
   * @return the green element of the color
   */
  int getGreen();

  /**
   * Returns the blue element of the RGB color as an integer.
   *
   * @return the blue element of the color
   */
  int getBlue();

  /**
   * Sets the name of the shape to the given String. The shape name cannot be empty.
   *
   * @param shapeName the name of the shape that this shape will be set to
   * @throws IllegalArgumentException if the given shapeName is empty
   */
  void setShapeName(String shapeName) throws IllegalArgumentException;

  /**
   * Sets the shape dimension to the given dimension, which is given as width and height in integer
   * form.
   *
   * @param width  the width of the shape that this shape will be set to
   * @param height the height of the shape that this shape will be set to
   * @throws IllegalArgumentException if the width and/or height is less than 0
   */
  void setDimension(int width, int height) throws IllegalArgumentException;

  /**
   * Sets the shape position to the given position, which is given as x and y in integer form.
   *
   * @param x the x position of the shape that this shape will be set to
   * @param y the y position of the shape that this shape will be set to
   */
  void setPosition(int x, int y);

  /**
   * Sets the shape color to the given position, which is given as RGB in integers.
   *
   * @param r the red element of the given RGB color
   * @param g the green element of the given RGB color
   * @param b the blue element of the given RGB color
   * @throws IllegalArgumentException if r, g, and/or b is not within the range of 0 to 255.
   */
  void setColor(int r, int g, int b) throws IllegalArgumentException;

  /**
   * Draws the shape as a graphic image.
   *
   * @param g the image of the shape that is being drawn
   */
  void drawShape(Graphics2D g);

  /**
   * Returns the text description of the shape. Include the declaration of a shape, the shape name,
   * and the shape type in order.
   *
   * @return the text description of the shape
   */
  String getShapeText();

  /**
   * Return the position, dimension, and the RGB colors of the shape in String format, in order.
   *
   * @return the position, dimension, and the RGB colors of the shape in String format, in order
   */
  String getElements();

  /**
   * Sets the shape identifier to the given shape identifier as a String.
   *
   * @param identifier the new shape identifier the shape will be set
   * @throws IllegalArgumentException if the given identifier is empty
   */
  void setShapeIdentifier(String identifier) throws IllegalArgumentException;

  /**
   * Returns the shape identifier of this shape.
   *
   * @return the shape identifier
   */
  String getShapeIdentifier();

  /**
   * Returns the 2D ellipse shape of this shape, and throws an exception if this shape is not an
   * ellipse and therefore cannot provide a 2D ellipse.
   *
   * @return the shape's 2D ellipse
   * @throws IllegalArgumentException if this shape is not an ellipse
   */
  Ellipse2D getEllipse();

  /**
   * Returns the 2D rectangle shape of this shape, and throws an exception if this shape is not a
   * rectangle and therefore cannot provide a 2D rectangle.
   *
   * @return the shape's 2D rectangle
   * @throws IllegalArgumentException if this shape is not a rectangle
   */
  Rectangle2D getRectangle();

  /**
   * Returns a String for the SVG text of the given shape, using the shape's attributes and shape
   * name to create a String for the starting part of an SVG style animation where the shape is
   * declared with all of its attributes.
   *
   * @return a String containing all of the shape's information needed to declare a shape for the
   *          SVG style animation.
   */
  String getShapeSVGText();

  /**
   * Returns a String that represents the closing text of a shape declaration for the SVG style
   * animation, that differs depending on the shape.
   *
   * @return a String for the closing text of an SVG animation.
   */
  String getShapeClosingSVG();

  /**
   * Returns a String for the animation text for this shape using the given initial and final
   * shapes, and a motion for those shapes. This method will be used in the Motion class to get the
   * animation text for an SVG style animation for the initial shape of a motion in our model, and
   * the XML animation for SVG text needs the given parameters, the shape and ending shape to get
   * the initial and ending attributes, the motion for the start and end time, and the ticks per
   * second for speed as well. Throws an exception if the speed is not a positive integer.
   *
   * @param initShape   the initial shape (from motion) that the motion animation is using
   * @param finalShape  the final shape (from motion) that the motion animation is using
   * @param motion      the motion that the animation applies to, and that the text needs to get
   *                    information for
   * @param ticksPerSec the ticks per second (speed) that the motion and animation proceeds in, and
   *                    that the XML animation text will change depending on what it is
   * @return a String that represent XML animation text for this shape for an animation that is
   *          occurring on it in an SVG style view.
   * @throws IllegalArgumentException if the ticks per second is not a positive integer
   */
  String getXMLAnimation(IShape initShape, IShape finalShape, IMotion motion, int ticksPerSec)
          throws IllegalArgumentException;

}
