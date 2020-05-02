package model.motion;

import model.shape.IShape;
import model.shape.MyEllipse;
import model.shape.MyRectangle;

/**
 * The motion class that holds initial shape, ending shape, initial tick and ending tick. A motion
 * animates the initial shape changing to its final shape, from the initial tick to the final tick.
 * Every motion holds an ID, so that every motion is unique, even thought if might be exactly the
 * same. This is so that multiple of the same motions can be animated at once.
 */
public class Motion implements IMotion {

  private IShape initShape;
  private IShape finalShape;
  private int t1;
  private int t2;
  private String motionID;

  /**
   * Constructs a motion with the starting and ending tick, starting and ending shape, and the ID
   * for the motion as a String.
   *
   * @param t1         the initial tick of the motion
   * @param t2         the final tick of the motion
   * @param initShape  the initial shape of the motion
   * @param finalShape the final shape of the motion
   * @throws IllegalArgumentException if the given tick is not a positive integer or 0. Or if the
   *                                  starting shape's shape name and the ending shape's shape name
   *                                  are not the same.
   */
  public Motion(int t1, int t2, IShape initShape, IShape finalShape)
          throws IllegalArgumentException {
    if (t1 < 0 || t2 < 0) {
      throw new IllegalArgumentException("A tick has to be a positive integer!");
    }
    if (!initShape.getShapeName().equals(finalShape.getShapeName())) {
      throw new IllegalArgumentException(
              "A motion requires the initial shape and the ending shape to be the same!");
    }
    this.initShape = initShape;
    this.t1 = t1;

    this.finalShape = finalShape;
    this.t2 = t2;
  }

  @Override
  public int getStartTick() {
    return this.t1;
  }

  @Override
  public int getEndTick() {
    return this.t2;
  }

  @Override
  public String getMotionID() {
    return this.motionID;
  }

  @Override
  public IShape getStartShape() {
    return this.initShape;
  }

  @Override
  public IShape getEndShape() {
    return this.finalShape;
  }

  @Override
  public void setMotionID(String motionID) throws IllegalArgumentException {
    if (motionID.equals("")) {
      throw new IllegalArgumentException("A motion ID cannot be empty!");
    }
    this.motionID = motionID;
  }

  @Override
  public void setStartShape(IShape initShape) throws IllegalArgumentException {
    if (!initShape.getShapeName().equals(this.getEndingShapeName())) {
      throw new IllegalArgumentException(
              "A motion requires the initial shape and the ending shape to be the same!");
    }
    this.initShape = initShape;
  }

  @Override
  public void setEndShape(IShape endShape) throws IllegalArgumentException {
    if (!endShape.getShapeName().equals(this.getStartingShapeName())) {
      throw new IllegalArgumentException(
              "A motion requires the initial shape and the ending shape to be the same!");
    }
    this.finalShape = endShape;
  }

  @Override
  public void setStartTick(int t1) throws IllegalArgumentException {
    if (t1 < 1) {
      throw new IllegalArgumentException("A tick has to be a positive integer!");
    }
    this.t1 = t1;
  }

  @Override
  public void setEndTick(int t2) throws IllegalArgumentException {
    if (t2 < 1) {
      throw new IllegalArgumentException("A tick has to be a positive integer!");
    }
    this.t2 = t2;
  }

  @Override
  public void setInitialPosition(int x, int y) {
    this.initShape.setPosition(x, y);
  }

  @Override
  public void setInitialSize(int width, int height) throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("The height and width has to be greater than 0!");
    }
    this.initShape.setDimension(width, height);
  }

  @Override
  public void setInitialColor(int r, int g, int b) throws IllegalArgumentException {
    if ((r < 0 || r > 255) || (g < 0 || g > 255) || (b < 0 || b > 255)) {
      throw new IllegalArgumentException("An RGB color has to be within the range of 0 to 255!");
    }
    this.initShape.setColor(r, g, b);
  }

  @Override
  public void setFinalPosition(int x, int y) {
    this.finalShape.setPosition(x, y);
  }

  @Override
  public void setFinalSize(int width, int height) throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("The height and width has to be greater than 0!");
    }
    this.finalShape.setDimension(width, height);
  }

  @Override
  public void setFinalColor(int r, int g, int b) throws IllegalArgumentException {
    if ((r < 0 || r > 255) || (g < 0 || g > 255) || (b < 0 || b > 255)) {
      throw new IllegalArgumentException("An RGB color has to be within the range of 0 to 255!");
    }
    this.finalShape.setColor(r, g, b);
  }

  @Override
  public IShape getShapeAtTick(int t) throws IllegalArgumentException, IllegalStateException {
    if (t < 0) {
      throw new IllegalArgumentException("A tick has to be a positive integer or 0!");
    }
    if (t1 > t || t2 < t) {
      throw new IllegalArgumentException("The given tick has to be in between the start tick "
              + "and the end tick");
    }

    int newPositionX = this.tweening(initShape.getX(), finalShape.getX(), t);
    int newPositionY = this.tweening(initShape.getY(), finalShape.getY(), t);
    int newDimensionW = this.tweening(initShape.getWidth(), finalShape.getWidth(), t);
    int newDimensionH = this.tweening(initShape.getHeight(), finalShape.getHeight(), t);
    int newColorR = this.tweening(initShape.getRed(), finalShape.getRed(), t);
    int newColorG = this.tweening(initShape.getGreen(), finalShape.getGreen(), t);
    int newColorB = this.tweening(initShape.getBlue(), finalShape.getBlue(), t);

    switch (initShape.getShapeType()) {
      case ELLIPSE:
        return new MyEllipse(newDimensionW, newDimensionH, newPositionX, newPositionY, newColorR,
                newColorG, newColorB, initShape.getShapeName());
      case RECTANGLE:
        return new MyRectangle(newDimensionW, newDimensionH, newPositionX, newPositionY, newColorR,
                newColorG, newColorB, initShape.getShapeName());
      default:
        throw new IllegalStateException("The motion holds a non-existing ShapeType!");
    }
  }

  /**
   * The helper method for computing the tweened value for all of the attributes of a shape for a
   * motion. Uses the start Int as a starting value for the value that we wish to get the "tweened"
   * value for, and the end Int to represent the magnitude of the attribute at the end of the motion
   * that the "tweened" value will be gotten for. Additional, the given tick is used to represent
   * the point in time that the "tweened" value will be gotten for, and will use the start and end
   * tick of a motion to actually mathematically compute the "tweened" value.
   *
   * @param startInt the starting value of the attribute which we want to get the "tweened" value
   *                 of
   * @param endInt   the ending value of the attribute which we want to get the "tweened" value of
   * @param t        the tick in time that we want to get the value for
   * @return a "tweened" value between the given start and ending int for the given tick in time
   */
  private int tweening(int startInt, int endInt, int t) {
    double first = ((double) (t2 - t) / (t2 - t1));
    double second = ((double) (t - t1) / (t2 - t1));
    return (int) Math.round(((startInt * first) + (endInt * second)));
  }

  @Override
  public String getMotionText() {
    return "motion " + this.getStartingShapeName() + " " + this.t1 + " " +
            this.initShape.getElements() + " " + this.t2 + " " + this.finalShape.getElements()
            + "\n";
  }

  @Override
  public String getStartingShapeName() {
    return this.initShape.getShapeName();
  }

  @Override
  public String getEndingShapeName() {
    return this.finalShape.getShapeName();
  }

  @Override
  public String getStartingShapeId() {
    return this.initShape.getShapeIdentifier();
  }

  @Override
  public String getEndingShapeId() {
    return this.finalShape.getShapeIdentifier();
  }

  @Override
  public boolean hasSameStartAndEndShape() {
    return this.initShape.equals(finalShape);
  }

  @Override
  public boolean sameXInMotion(IShape initS, IShape finalS) {
    return initS.getX() == finalS.getX();

  }

  @Override
  public boolean sameYInMotion(IShape initS, IShape finalS) {
    return initS.getY() == finalS.getY();

  }

  @Override
  public boolean sameWidthInMotion(IShape initS, IShape finalS) {
    return initS.getWidth() == finalS.getWidth();

  }

  @Override
  public boolean sameHeightInMotion(IShape initS, IShape finalS) {
    return initS.getHeight() == finalS.getHeight();

  }

  @Override
  public boolean sameColorInMotion(IShape initS, IShape finalS) {
    return initS.getRed() == finalS.getRed() && initS.getGreen() == finalS.getGreen()
            && initS.getBlue() == finalS.getBlue();

  }

  @Override
  public boolean hasSVGMotion(IShape initS, IShape finalS) {
    return !(this.sameXInMotion(initS, finalS)) || !(this.sameYInMotion(initS, finalS)) ||
            !(this.sameWidthInMotion(initS, finalS)) || !(this.sameHeightInMotion(initS, finalS))
            || !(this.sameColorInMotion(initS, finalS));
  }

  @Override
  public String makeMotionHidden(int ticksPerSec) {
    if (ticksPerSec < 1) {
      throw new IllegalArgumentException("Can't have a speed that is zero or less");
    }
    double startTime = (this.getStartTick() / (double) ticksPerSec) * 1000.0;
    double endTime = (this.getEndTick() / (double) ticksPerSec) * 1000.0;
    double duration = endTime - startTime;
    return "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\""
            + " begin=\"" + startTime + "ms\" dur=\"" + duration + "ms\" fill=\"remove\" />";

  }

  @Override
  public String makeMotionHiddenNoSVG(int ticksPerSec) {
    if (ticksPerSec < 1) {
      throw new IllegalArgumentException("Can't have a speed that is zero or less");
    }
    double startTime = (this.getStartTick() / (double) ticksPerSec) * 1000.0;
    double endTime = (this.getEndTick() / (double) ticksPerSec) * 1000.0;
    double duration = endTime - startTime;
    return "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\""
            + " begin=\"" + duration + "ms\" dur=\"" + startTime + "ms\" fill=\"remove\" />";

  }

  @Override
  public String getMotionSVGText(int ticksPerSec) {
    if (ticksPerSec < 1) {
      throw new IllegalArgumentException("Can't have a speed that is zero or less");
    }
    StringBuilder text = new StringBuilder();

    text.append(finalShape.getXMLAnimation(initShape, finalShape, this, ticksPerSec));

    String str = text.toString();
    if (str.length() == 0) {
      return str;
    }
    return str.substring(0, text.length() - 1);
  }

  @Override
  public boolean containsShape(IShape shape) {
    String shapeName = shape.getShapeName();
    return this.initShape.getShapeName().equals(shapeName) || this.finalShape.getShapeName()
            .equals(shapeName);
  }


}

