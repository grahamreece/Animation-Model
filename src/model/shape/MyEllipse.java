package model.shape;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import model.motion.IMotion;

/**
 * The Ellipse shape class with elements that makes an ellipse including dimension, position, color,
 * shape type, and the shape name. The ellipse is drawable, and can be also considered a circle.
 * This class can be used to create and modify an ellipse. The class contains an ellipse, which is
 * the 2D representation of the created ellipse. The shape type for this ellipse is initialized and
 * set to ELLIPSE.
 */
public class MyEllipse extends AShape {

  private Ellipse2D ellipse;

  /**
   * A constructor for an ellipse that sets the dimension, position, color, and the ellipse name
   * according to the inputted arguments of width, height, x, y, r, g, b, and shapeName.
   *
   * @param width     the width element of the dimension
   * @param height    the height element of the dimension
   * @param x         the x element of the position
   * @param y         the y element of the position
   * @param r         the red element of the RGB color
   * @param g         the green element of the RGB color
   * @param b         the blue element of the RGB color
   * @param shapeName the name of the ellipse (declared name)
   * @throws IllegalArgumentException if the width and/or height is less than 0, if r, g, and/or b
   *                                  is not within the range of 0 to 255, or if the shapeIdentifier
   *                                  is empty.
   */
  public MyEllipse(int width, int height, int x, int y, int r, int g, int b, String shapeName)
          throws IllegalArgumentException {
    super(width, height, x, y, r, g, b, shapeName);
    this.shapeType = ShapeType.ELLIPSE;
    this.shapeName = shapeName;

    this.ellipse = new Ellipse2D.Double(position.getX(), position.getY(),
            dimension.getWidth(), dimension.getHeight());
  }

  /**
   * A constructor for an ellipse with a name and a shapeType.
   *
   * @param shapeName the name for this ellipse shape
   */
  public MyEllipse(String shapeName) {
    super(shapeName);
    this.shapeType = ShapeType.ELLIPSE;
  }

  @Override
  public Ellipse2D getEllipse() {
    return this.ellipse;
  }

  @Override
  public Rectangle2D getRectangle() throws IllegalArgumentException {
    throw new IllegalArgumentException("Can't get a rectangle from a shape" +
            " that is not a rectangle");
  }

  @Override
  public void drawShape(Graphics2D g) {
    g.setColor(this.color);
    g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    g.drawOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());

  }

  @Override
  public String getShapeSVGText() {
    return "<ellipse id=\"" + this.shapeName + "\" cx=\"" + this.position.getX() + "\" cy=\""
            + this.position.getY()
            + "\" rx=\"" + (this.dimension.getWidth() / 2) + "\" ry=\"" +
            (this.dimension.getHeight() / 2) +
            "\" fill=\"rgb(" + this.getRed() + "," + this.getGreen() + "," + this.getBlue()
            + ")\" visibility=\"visible\" >";
  }

  @Override
  public String getShapeClosingSVG() {
    return "</ellipse>";
  }

  @Override
  public String getXMLAnimation(IShape initShape, IShape finalShape, IMotion motion,
                                int ticksPerSec) throws IllegalArgumentException {
    if (ticksPerSec < 1) {
      throw new IllegalArgumentException("Can't have a speed that is zero or less");
    }
    double startTime = ((motion.getStartTick() / (double) ticksPerSec)) * 1000.0;
    double endTime = ((motion.getEndTick() / (double) ticksPerSec)) * 1000.0;
    double duration = endTime - startTime;
    StringBuilder text = new StringBuilder();

    if (!(motion.sameXInMotion(initShape, finalShape))) {
      text.append("<animate attributeType=\"xml\" begin=\"" + startTime + "ms\" dur=\""
              + duration + "ms\" attributeName=\"cx\" "
              + "from=\"" + initShape.getX() + "\" to=\""
              + finalShape.getX() + "\" fill=\"freeze\" />\n");
    }
    if (!(motion.sameYInMotion(initShape, finalShape))) {
      text.append("<animate attributeType=\"xml\" begin=\"" + startTime + "ms\" dur=\""
              + duration + "ms\" attributeName=\"cy\" "
              + "from=\"" + initShape.getY() + "\" to=\""
              + finalShape.getY() + "\" fill=\"freeze\" />\n");
    }
    if (!(motion.sameWidthInMotion(initShape, finalShape))) {
      text.append("<animate attributeType=\"xml\" begin=\"" + startTime + "ms\" dur=\""
              + duration + "ms\" attributeName=\"rx\" "
              + "from=\"" + (initShape.getWidth() / 2) + "\" to=\""
              + (finalShape.getWidth() / 2) + "\" fill=\"freeze\" />\n");
    }
    if (!(motion.sameHeightInMotion(initShape, finalShape))) {
      text.append("<animate attributeType=\"xml\" begin=\"" + startTime + "ms\" dur=\""
              + duration + "ms\" attributeName=\"ry\" "
              + "from=\"" + (initShape.getHeight() / 2) + "\" to=\""
              + (finalShape.getHeight() / 2) + "\" fill=\"freeze\" />\n");
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
