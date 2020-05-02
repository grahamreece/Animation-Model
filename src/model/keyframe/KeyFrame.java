package model.keyframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import model.shape.IShape;
import model.shape.MyEllipse;
import model.shape.MyRectangle;
import model.shape.ShapeType;

/**
 * A KeyFrame class that holds the tick, shape, shape type, shape name, shape position, shape size,
 * shape color, and the KeyFrame ID. Every KeyFrame holds an ID, so that every KeyFrame is unique,
 * even thought if might be exactly the same. This is so that multiple of the same motions/KeyFrames
 * can be animated at once.
 */
public class KeyFrame implements IKeyFrame {

  private String shapeName;
  private int t;
  private Dimension dimension;
  private Point2D position;
  private Color color;
  private String keyFrameID;
  private IShape shape;
  private ShapeType shapeType;

  /**
   * Constructs a KeyFrame with the tick, shape, shape type, shape name, shape position, shape size,
   * shape color, and the KeyFrame ID.
   *
   * @param name the name for the shape for the keyframe
   * @param t the tick for the keyframe
   * @param x the x position for the shape for the keyframe
   * @param y the y position for the shape for the keyframe
   * @param w the width for the shape for the keyframe
   * @param h the height for the shape for the keyframe
   * @param r the red color for the shape for the keyframe
   * @param g the green color for the shape for the keyframe
   * @param b the blue color for the shape for the keyframe
   * @param shapeType the shape type for the shape for the keyframe
   * @throws IllegalArgumentException if the tick is not a positive integer, if the shape name is
   *                                  empty, if the height and width is not greater than 0, and if
   *                                  the RGB color range is not in between 0 and 255.
   */
  public KeyFrame(String name, int t, int x, int y, int w, int h,
      int r, int g, int b, ShapeType shapeType) throws IllegalArgumentException {
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("The height and width has to be greater than 0!");
    }
    if ((r < 0 || r > 255) || (g < 0 || g > 255) || (b < 0 || b > 255)) {
      throw new IllegalArgumentException("An RGB color has to be within the range of 0 to 255!");
    }
    if (name.equals("")) {
      throw new IllegalArgumentException("The shape name cannot be empty!");
    }
    if (t < 0) {
      throw new IllegalArgumentException("A tick has to be a positive integer!");
    }
    this.shapeName = name;
    this.t = t;
    this.dimension = new Dimension(w, h);
    this.position = new Point2D.Double(x, y);
    this.color = new Color(r, g, b);
    this.keyFrameID = name + t + x + y + w + h + r + g + b;
    this.shapeType = shapeType;
    switch (shapeType) {
      case RECTANGLE:
        this.shape = new MyRectangle(w, h, x, y, r, g, b, name);
        break;
      case ELLIPSE:
        this.shape = new MyEllipse(w, h, x, y, r, g, b, name);
        break;
      default:
        break;
    }
  }

  @Override
  public String getKeyFrameID() {
    return keyFrameID;
  }

  @Override
  public void setKeyFrameID(String keyFrameID) {
    if (keyFrameID.equals("")) {
      throw new IllegalArgumentException("KeyFrame ID cannot be an empty string.");
    }
    this.keyFrameID = keyFrameID;
  }

  @Override
  public String getShapeName() {
    return shapeName;
  }

  @Override
  public void setShapeName(String name) {
    if (name.equals("")) {
      throw new IllegalArgumentException("Shape Name cannot be empty.");
    }
    this.shapeName = name;
  }

  @Override
  public int getTick() {
    return t;
  }

  @Override
  public void setTick(int t) {
    if (t < 0) {
      throw new IllegalArgumentException("Cannot set the tick to a negative value.");
    }
    this.t = t;
  }

  @Override
  public int getWidth() {
    return (int) dimension.getWidth();
  }

  @Override
  public int getHeight() {
    return (int) dimension.getHeight();
  }

  @Override
  public void setDimension(int w, int h) throws IllegalArgumentException {
    if (w < 1 || h < 1) {
      throw new IllegalArgumentException(
          "The height and width has to be at least 1 integer wide/long!");
    }
    dimension = new Dimension(w, h);
  }

  @Override
  public int getX() {
    return (int) position.getX();
  }

  @Override
  public int getY() {
    return (int) position.getY();
  }

  @Override
  public void setPosition(int x, int y) {
    position = new Point2D.Double(x, y);
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
  public void setColor(int r, int g, int b) throws IllegalArgumentException {
    if ((r < 0 || r > 255) || (g < 0 || g > 255) || (b < 0 || b > 255)) {
      throw new IllegalArgumentException("An RGB color has to be within the range of 0 to 255!");
    }
    this.color = new Color(r, g, b);
  }

  @Override
  public IShape getShape() {
    return this.shape;
  }

  @Override
  public ShapeType getShapeType() {
    return this.shapeType;
  }


}