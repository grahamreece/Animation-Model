package model.shape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.Objects;

/**
 * An abstract class for a shape. Every shape should hold the type of shape, dimension, position,
 * color, shape name, and a shape ID. All the getter and setter methods for shapes are the same for
 * every shape, except for the getters and setters for getting the actual 2D representation of the
 * shapes. This class is the connector for different shapes, as it holds the common elements and the
 * methods for all shapes.
 */
public abstract class AShape implements IShape {

  protected ShapeType shapeType;
  protected Dimension dimension;
  protected Point2D position;
  protected Color color;
  protected String shapeName;
  protected String shapeIdentifier;

  /**
   * A constructor for an abstract class that sets the shape type, dimension, position, color, and
   * shape name according to the inputted arguments of width, height, x, y, r, g, b, shape name.
   *
   * @param width     the width element of the dimension
   * @param height    the height element of the dimension
   * @param x         the x element of the position
   * @param y         the y element of the position
   * @param r         the red element of the RGB color
   * @param g         the green element of the RGB color
   * @param b         the blue element of the RGB color
   * @param shapeName the name of the shape
   * @throws IllegalArgumentException if the width and/or height is less than 0, if r, g, and/or b
   *                                  is not within the range of 0 to 255.
   */
  protected AShape(int width, int height, int x, int y, int r, int g, int b, String shapeName)
          throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("The height and width has to be greater than 0!");
    }
    if ((r < 0 || r > 255) || (g < 0 || g > 255) || (b < 0 || b > 255)) {
      throw new IllegalArgumentException("An RGB color has to be within the range of 0 to 255!");
    }
    if (shapeName.equals("")) {
      throw new IllegalArgumentException("The shape name cannot be empty!");
    }
    this.dimension = new Dimension(width, height);
    this.position = new Point2D.Double(x, y);
    this.color = new Color(r, g, b);
    this.shapeName = shapeName;
    this.shapeIdentifier = null;
  }

  protected AShape(String shapeName) {
    this.shapeName = shapeName;
  }

  @Override
  public ShapeType getShapeType() {
    return this.shapeType;
  }

  @Override
  public int getWidth() {
    return (int) this.dimension.getWidth();
  }

  @Override
  public int getHeight() {
    return (int) this.dimension.getHeight();
  }

  @Override
  public int getX() {
    return (int) this.position.getX();
  }

  @Override
  public int getY() {
    return (int) this.position.getY();
  }

  @Override
  public int getRed() {
    return this.color.getRed();
  }

  @Override
  public int getGreen() {
    return this.color.getGreen();
  }

  @Override
  public int getBlue() {
    return this.color.getBlue();
  }

  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  @Override
  public String getShapeIdentifier() {
    return this.shapeIdentifier;
  }

  @Override
  public void setDimension(int width, int height) throws IllegalArgumentException {
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException(
              "The height and width has to be at least 1 integer wide/long!");
    }
    this.dimension = new Dimension(width, height);
  }

  @Override
  public void setPosition(int x, int y) {
    this.position.setLocation(x, y);
  }

  @Override
  public void setColor(int r, int g, int b) throws IllegalArgumentException {
    if ((r < 0 || r > 255) || (g < 0 || g > 255) || (b < 0 || b > 255)) {
      throw new IllegalArgumentException("An RGB color has to be within the range of 0 to 255!");
    }
    this.color = new Color(r, g, b);
  }

  @Override
  public void setShapeName(String shapeName) throws IllegalArgumentException {
    if (shapeName.equals("")) {
      throw new IllegalArgumentException("The given shape name cannot be empty!");
    }
    this.shapeName = shapeName;
  }

  @Override
  public void setShapeIdentifier(String identifier) throws IllegalArgumentException {
    if (identifier.equals("")) {
      throw new IllegalArgumentException("The identifier cannot be empty!");
    }
    this.shapeIdentifier = identifier;
  }

  @Override
  public String getShapeText() {
    return "shape " + this.getShapeName() + " " + this.getShapeType() + "\n";
  }

  @Override
  public String getElements() {
    return this.getX() + " " + this.getY() + " " + this.getWidth() + " " + this.getHeight() + " " +
            this.getRed() + " " + this.getGreen() + " " + this.getBlue();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AShape)) {
      return false;
    }
    AShape that = (AShape) o;
    return this.shapeType.equals(that.shapeType) && this.dimension.equals(that.dimension) &&
            this.position.equals(that.position) && this.color.equals(that.color)
            && this.shapeName.equals(that.shapeName) &&
            this.shapeIdentifier == that.shapeIdentifier;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.shapeType, this.dimension, this.position, this.color,
            this.shapeName, this.shapeIdentifier);
  }

}
