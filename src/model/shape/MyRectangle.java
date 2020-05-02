package model.shape;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import model.motion.IMotion;

/**
 * The Rectangle shape class with elements that makes a rectangle including dimension, position,
 * color, shape type, and the shape name. The rectangle is drawable, and can be also considered a
 * square. This class can be used to create and modify an rectangle. The class contains an
 * rectangle, which is the 2D representation of the created rectangle. The shape type for this
 * rectangle is initialized and set to RECTANGLE.
 */
public class MyRectangle extends AShape {

  private Rectangle2D rect;

  /**
   * A constructor for a rectangle that sets the dimension, position, color, and the ellipse name
   * according to the inputted arguments of width, height, x, y, r, g, b, and shapeName.
   *
   * @param width     the width element of the dimension
   * @param height    the height element of the dimension
   * @param x         the x element of the position
   * @param y         the y element of the position
   * @param r         the red element of the RGB color
   * @param g         the green element of the RGB color
   * @param b         the blue element of the RGB color
   * @param shapeName the name of the rectangle (declared name)
   * @throws IllegalArgumentException if the width and/or height is less than 0, if r, g, and/or b
   *                                  is not within the range of 0 to 255, or if the shapeIdentifier
   *                                  is empty.
   */
  public MyRectangle(int width, int height, int x, int y, int r, int g, int b, String shapeName)
          throws IllegalArgumentException {
    super(width, height, x, y, r, g, b, shapeName);
    this.shapeType = ShapeType.RECTANGLE;
    this.rect = new Rectangle2D.Double(position.getX(), position.getY(),
            dimension.getWidth(), dimension.getHeight());
  }

  /**
   * A constructor for an rectangle with a name and a shapeType.
   *
   * @param shapeName the name for this rectangle shape
   */
  public MyRectangle(String shapeName) {
    super(shapeName);
    this.shapeType = ShapeType.RECTANGLE;
  }

  @Override
  public Ellipse2D getEllipse() throws IllegalArgumentException {
    throw new IllegalArgumentException("Can't get an ellipse from a shape that is not an ellipse");
  }

  @Override
  public Rectangle2D getRectangle() {
    return this.rect;
  }

  @Override
  public void drawShape(Graphics2D g) {
    g.setColor(this.color);
    g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

  }

  @Override
  public String getShapeSVGText() {
    return "<rect id=\"" + this.shapeName + "\" x=\"" + this.position.getX() + "\" y=\""
            + this.position.getY()
            + "\" width=\"" + this.dimension.getWidth() + "\" height=\"" +
            this.dimension.getHeight() +
            "\" fill=\"rgb(" + this.getRed() + "," + this.getGreen() + "," + this.getBlue()
            + ")\" visibility=\"visible\" >";
  }


  @Override
  public String getShapeClosingSVG() {
    return "</rect>";
  }

  @Override
  public String getXMLAnimation(IShape initShape, IShape finalShape, IMotion motion,
                                int ticksPerSec) throws IllegalArgumentException {
    if (ticksPerSec < 1) {
      throw new IllegalArgumentException("Can't have a speed that is zero or less");
    }
    double startTime = ((motion.getStartTick() / (double) ticksPerSec)) * 1000;
    double endTime = ((motion.getEndTick() / (double) ticksPerSec)) * 1000;
    double duration = endTime - startTime;
    StringBuilder text = new StringBuilder();
    if (!(motion.sameXInMotion(initShape, finalShape))) {
      text.append("<animate attributeType=\"xml\" begin=\"" + startTime + "ms\" dur=\""
              + duration + "ms\" attributeName=\"x\" "
              + "from=\"" + initShape.getX() + "\" to=\""
              + finalShape.getX() + "\" fill=\"freeze\" />\n");
    }
    if (!(motion.sameYInMotion(initShape, finalShape))) {
      text.append("<animate attributeType=\"xml\" begin=\"" + startTime + "ms\" dur=\""
              + duration + "ms\" attributeName=\"y\" "
              + "from=\"" + initShape.getY() + "\" to=\""
              + finalShape.getY() + "\" fill=\"freeze\" />\n");
    }
    if (!(motion.sameWidthInMotion(initShape, finalShape))) {
      text.append("<animate attributeType=\"xml\" begin=\"" + startTime + "ms\" dur=\""
              + duration + "ms\" attributeName=\"width\" "
              + "from=\"" + initShape.getWidth() + "\" to=\""
              + finalShape.getWidth() + "\" fill=\"freeze\" />\n");
    }
    if (!(motion.sameHeightInMotion(initShape, finalShape))) {
      text.append("<animate attributeType=\"xml\" begin=\"" + startTime + "ms\" dur=\""
              + duration + "ms\" attributeName=\"height\" "
              + "from=\"" + initShape.getHeight() + "\" to=\""
              + finalShape.getHeight() + "\" fill=\"freeze\" />\n");
    }
    if (!(motion.sameColorInMotion(initShape, finalShape))) {
      text.append("<animate attributeType=\"xml\" begin=\"" + startTime + "ms\" dur=\""
              + duration + "ms\" attributeName=\"fill\" "
              + "from=\"rgb(" + initShape.getRed() + "," + initShape.getGreen() + "," + initShape
              .getBlue() + ")\" to=\"rgb("
              + finalShape.getRed() + "," + finalShape.getGreen() + "," + finalShape
              .getBlue() + ")\" fill=\"freeze\" />\n");
    }
    return text.toString();
  }


}

