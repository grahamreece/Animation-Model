package model;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import model.keyframe.IKeyFrame;
import model.keyframe.KeyFrame;
import model.motion.IMotion;
import model.motion.Motion;
import model.shape.IShape;
import model.shape.MyEllipse;
import model.shape.MyRectangle;
import model.shape.ShapeType;

/**
 * The model class that holds the list of shapes and the list of motions for the animation. This
 * model class represents an animation and the objects that are in the animation, represented as
 * shapes and the motions of those shapes.
 */
public class Model implements IModel {

  private ArrayList<IShape> shapeList;
  private ArrayList<IMotion> motionList;
  private int leftMostX;
  private int upMostY;
  private int borderWidth;
  private int borderHeight;
  private ArrayList<IKeyFrame> keyFrameList;

  /**
   * Constructs a model for the animation with no shapes and motions. The shapeList and motionList
   * will be initialized as an empty array list. The motions and shapes will be added and
   * initialized by the user. Every time the user adds a shape or a motion, it is set to a shape ID
   * or a motion ID, so that the model can identify specific shapes and motions.
   */
  public Model() {
    this.shapeList = new ArrayList<IShape>();
    this.motionList = new ArrayList<IMotion>();
    this.keyFrameList = new ArrayList<IKeyFrame>();
    this.leftMostX = 0;
    this.upMostY = 0;
    this.borderWidth = 0;
    this.borderHeight = 0;
  }

  @Override
  public IShape getShape(String shapeIdentifier)
      throws IllegalStateException, IllegalArgumentException {
    if (shapeIdentifier.equals("")) {
      throw new IllegalArgumentException("The given shape name cannot be empty!");
    }
    if (this.shapeIdentifierExists(shapeIdentifier)) {
      IShape returningShape = null;
      for (IShape shape : shapeList) {
        if (shape.getShapeIdentifier().equals(shapeIdentifier)) {
          returningShape = shape;
        }
      }
      return returningShape;
    } else {
      throw new IllegalStateException("The shape does not exist!");
    }
  }

  @Override
  public IMotion getMotion(String motionID) throws IllegalStateException, IllegalArgumentException {
    if (motionID.equals("")) {
      throw new IllegalArgumentException("The given motion ID cannot be empty!");
    }
    if (this.motionIDExists(motionID)) {
      IMotion returningMotion = null;
      for (IMotion motion : motionList) {
        if (motion.getMotionID().equals(motionID)) {
          returningMotion = motion;
        }
      }
      return returningMotion;
    } else {
      throw new IllegalStateException("The motion does not exist!");
    }
  }

  @Override
  public void addShape(IShape shape, String shapeIdentifier)
      throws IllegalStateException, IllegalArgumentException {
    if (this.shapeIdentifierExists(shapeIdentifier)) {
      throw new IllegalStateException("The shape ID already exists!");
    } else if (shapeIdentifier.equals("")) {
      throw new IllegalArgumentException("The given shape name cannot be empty!");
    } else {
      shape.setShapeIdentifier(shapeIdentifier);
      shapeList.add(shape);
    }

  }

  @Override
  public void addMotion(IMotion motion, String motionID)
      throws IllegalStateException, IllegalArgumentException {
    if (this.motionIDExists(motionID)) {
      throw new IllegalStateException("The motion ID already exists!");
    } else if (motionID.equals("")) {
      throw new IllegalArgumentException("The given motion ID cannot be empty!");
    } else if (this.motionForShapeNameExists(motion.getStartingShapeName())) {
      if (!this.motionIsConsecutive(motion.getStartTick())) {
        throw new IllegalStateException(
            "The given motion is not consecutive to the other motions!");
      } else {
        motion.setMotionID(motionID);
        motionList.add(motion);
      }
    } else {
      motion.setMotionID(motionID);
      motionList.add(motion);
    }
  }

  @Override
  public void deleteShape(IShape shape, String shapeIdentifier)
      throws IllegalArgumentException, IllegalStateException {
    try {
      if (shapeIdentifier.equals("")) {
        throw new IllegalArgumentException("The given shape name cannot be empty!");
      } else if (!shape.getShapeIdentifier().equals(shapeIdentifier)) {
        throw new IllegalStateException("The given shape id does not match the given shape!");
      } else if (this.shapeIdentifierExists(shapeIdentifier)) {
        shapeList.remove(this.getShape(shapeIdentifier));
      }
    } catch (NullPointerException e) {
      throw new IllegalStateException("The given shape id does not match the given shape!");
    }
  }

  @Override
  public void deleteShapeAndMotionForShapeName(String shapeName) throws IllegalStateException {
    if (!(this.shapeNameExists(shapeName))) {
      throw new IllegalStateException("The given shape does not exist in the model!");
    } else {
      Iterator shapeL = shapeList.iterator();
      while (shapeL.hasNext()) {
        IShape s = (IShape) shapeL.next();
        if (s.getShapeName().equals(shapeName)) {
          shapeL.remove();
        }
      }
      Iterator motionL = motionList.iterator();
      while (motionL.hasNext()) {
        IMotion m = (IMotion) motionL.next();
        if (m.getStartingShapeName().equals(shapeName)) {
          motionL.remove();
        }
      }
    }
  }

  /**
   * A helper to determine if the given shapeName exists in the model for a shape.
   *
   * @param name the given shapeName
   * @return true if the given shapeName exists in the model for a shape
   */
  private boolean shapeNameExists(String name) {
    boolean exists = false;
    for (IShape shape : shapeList) {
      if (shape.getShapeName().equals(name)) {
        exists = true;
      }
    }
    return exists;
  }

  @Override
  public ArrayList<IKeyFrame> getKeyFrameList() {
    return this.keyFrameList;
  }


  /**
   * A helper to determine if the motions in this model are consecutive or not. Compares the given
   * tick to the ending tick for every motion to determine if this tick can be the starting tick
   * for the next motion.
   *
   * @param t the tick for the starting tick for a new motion
   * @return true if the motions in this model are consecutive
   */
  private boolean motionIsConsecutive(int t) {
    boolean same = false;
    for (IMotion m : motionList) {
      if (m.getEndTick() == t) {
        same = true;
      }
    }
    return same;
  }

  @Override
  public void deleteMotion(IMotion motion, String motionID)
      throws IllegalArgumentException, IllegalStateException {
    try {
      if (motionID.equals("")) {
        throw new IllegalArgumentException("The given motion ID cannot be empty!");
      } else if (!motion.getMotionID().equals(motionID)) {
        throw new IllegalStateException("The given shape id does not match the given shape!");
      } else if (this.motionIDExists(motionID)) {
        if (this.lessThanAllMotionTicks(motion.getStartTick()) ||
            this.greaterThanAllMotionTicks(motion.getEndTick())) {
          motionList.remove(this.getMotion(motionID));
        } else {
          throw new IllegalStateException(
              "The deleting motion will cause inconsistency in the animation!");
        }
      }
    } catch (NullPointerException e) {
      throw new IllegalStateException("The given motion ID does not match the given motion!");
    }
  }

  @Override
  public void deleteAllShape() {
    this.shapeList = new ArrayList<IShape>();
  }

  @Override
  public void deleteAllMotion() {
    this.motionList = new ArrayList<IMotion>();
  }

  @Override
  public boolean shapeIdentifierExists(String shapeIdentifier) throws IllegalArgumentException {
    if (shapeIdentifier.equals("")) {
      throw new IllegalArgumentException("The given shape name cannot be empty!");
    }
    boolean exists = false;
    for (IShape shape : shapeList) {
      if (shape.getShapeIdentifier().equals(shapeIdentifier)) {
        exists = true;
      }
    }
    return exists;
  }


  @Override
  public boolean motionIDExists(String motionID) throws IllegalArgumentException {
    if (motionID.equals("")) {
      throw new IllegalArgumentException("The given motion ID cannot be empty!");
    }
    boolean exists = false;
    for (IMotion motion : motionList) {
      if (motion.getMotionID().equals(motionID)) {
        exists = true;
      }
    }
    return exists;
  }

  @Override
  public String textRenderOutput() {

    ArrayList<String> shapeDeclarations = new ArrayList<String>();
    ArrayList<String> shapeNames = new ArrayList<String>();
    StringBuilder text = new StringBuilder();

    for (IMotion motion : motionList) {
      String currStartingShapeID = motion.getStartingShapeId();
      String currEndingShapeID = motion.getEndingShapeId();
      String currStartingShapeName = motion.getStartingShapeName();
      IShape currStartShape = motion.getStartShape();

      if (!shapeDeclarations.contains(currStartingShapeID) || !shapeDeclarations
          .contains(currEndingShapeID) || motion.hasSameStartAndEndShape()) {
        shapeDeclarations.add(currStartingShapeID);
        shapeDeclarations.add(currEndingShapeID);
        if (!shapeNames.contains(currStartingShapeName)) {
          shapeNames.add(currStartingShapeName);
          text.append(currStartShape.getShapeText());
        }
        text.append(motion.getMotionText());
      }
    }

    return text.substring(0, text.length() - 1);

  }

  @Override
  public ArrayList<IShape> getShapeList() {
    return this.shapeList;
  }

  @Override
  public ArrayList<IMotion> getMotionList() {
    return this.motionList;
  }

  @Override
  public boolean motionForShapeNameExists(String shapeName) {
    boolean exists = false;
    for (IMotion motion : motionList) {
      if (motion.getStartingShapeName().equals(shapeName)) {
        exists = true;
      }
    }
    return exists;
  }


  @Override
  public boolean lessThanAllMotionTicks(int t) {
    for (IMotion motion : motionList) {
      if (motion.getStartTick() < t) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean greaterThanAllMotionTicks(int t) {
    for (IMotion motion : motionList) {
      if (motion.getEndTick() > t) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int getRightMostX() {
    double currMax = 0;

    for (int i = 0; i < motionList.size(); i++) {
      IMotion currMotion = motionList.get(i);
      IShape currStartShape = currMotion.getStartShape();
      IShape currEndShape = currMotion.getEndShape();
      double currStartShapeX = currStartShape.getX();
      double currEndShapeX = currEndShape.getX();

      if (i == 0) {
        if (currStartShapeX < currEndShapeX) {
          currMax = currEndShapeX;
        } else {
          currMax = currStartShapeX;
        }
      } else if (currMax < currStartShapeX) {
        currMax = currStartShapeX;
      } else if (currMax < currEndShapeX) {
        currMax = currEndShapeX;
      }
    }
    return (int) currMax;
  }

  @Override
  public int getDownMostY() {
    double currMax = 0;

    for (int i = 0; i < motionList.size(); i++) {
      IMotion currMotion = motionList.get(i);
      IShape currStartShape = currMotion.getStartShape();
      IShape currEndShape = currMotion.getEndShape();
      double currStartShapeY = currStartShape.getY();
      double currEndShapeY = currEndShape.getY();

      if (i == 0) {
        if (currStartShapeY < currEndShapeY) {
          currMax = currEndShapeY;
        } else {
          currMax = currStartShapeY;
        }
      } else if (currMax < currStartShapeY) {
        currMax = currStartShapeY;
      } else if (currMax < currEndShapeY) {
        currMax = currEndShapeY;
      }
    }
    return (int) currMax;
  }

  @Override
  public ShapeType getShapeTypeFromShapeName(String shapeName) throws IllegalStateException {
    for (IShape shape : shapeList) {
      if (shape.getShapeName().equals(shapeName)) {
        return shape.getShapeType();
      }
    }
    throw new IllegalStateException("The chosen shape does not exist in the model!");
  }

  @Override
  public void setLeftMostX(int x) {
    this.leftMostX = x;
  }

  @Override
  public void setUpMostY(int y) {
    this.upMostY = y;
  }

  @Override
  public void setBorderW(int w) throws IllegalArgumentException {
    if (w < 0) {
      throw new IllegalArgumentException("Can't set the border width of the model to a negative " +
          "number");
    }
    this.borderWidth = w;
  }

  @Override
  public void setBorderH(int h) throws IllegalArgumentException {
    if (h < 0) {
      throw new IllegalArgumentException("Can't set the border width of the model to a negative " +
          "number");
    }
    this.borderHeight = h;
  }

  @Override
  public int getLeftMostX() {
    return this.leftMostX;
  }

  @Override
  public int getUpMostY() {
    return this.upMostY;
  }

  @Override
  public int getBorderW() {
    return this.borderWidth;
  }

  @Override
  public int getBorderH() {
    return this.borderHeight;
  }

  @Override
  public IShape getInitShape(String shapeName) throws IllegalArgumentException {
    ArrayList<IMotion> motions = new ArrayList<IMotion>();
    for (IMotion motion : motionList) {
      if (motion.getStartingShapeName().equals(shapeName)) {
        motions.add(motion);
      }
    }
    if (motions.size() == 0) {
      throw new IllegalArgumentException("There are no motions in the model with the given" +
          " shapeName");
    }
    IShape initShape = motions.get(0).getStartShape();
    int currTick = motions.get(0).getStartTick();

    for (IMotion motion : motions) {
      if (motion.getStartTick() < currTick) {
        currTick = motion.getStartTick();
        initShape = motion.getStartShape();
      }
    }

    return initShape;
  }

  @Override
  public int getLastTick() {
    int lastTick = 0;
    for (IMotion motion : motionList) {
      if (lastTick < motion.getEndTick()) {
        lastTick = motion.getEndTick();
      }
    }
    return lastTick;
  }

  @Override
  public void addKeyFrame(IKeyFrame keyFrame, String keyFrameID)
      throws IllegalArgumentException, IllegalStateException {
    if (keyFrameID.equals("")) {
      throw new IllegalArgumentException("The given keyFrame ID cannot be empty!");
    } else if (this.keyFrameIDExists(keyFrameID)) {
      throw new IllegalStateException("The given keyFrame ID already exists!!");
    } else {
      keyFrame.setKeyFrameID(keyFrameID);
      keyFrameList.add(keyFrame);
    }
  }

  @Override
  public void modifyKFHelper(int i, IKeyFrame kf) {
    keyFrameList.set(i, kf);
  }

  @Override
  public void deleteKeyFrame(IKeyFrame keyFrame) throws IllegalStateException {
    String keyFrameID = keyFrame.getKeyFrameID();

    Iterator kfL = keyFrameList.iterator();
    while (kfL.hasNext()) {
      IKeyFrame kf = (IKeyFrame) kfL.next();
      if (kf.getKeyFrameID().equals(keyFrameID)) {
        kfL.remove();
      }
    }
  }

  @Override
  public boolean keyFrameIDExists(String keyFrameID) throws IllegalArgumentException {
    if (keyFrameID.equals("")) {
      throw new IllegalArgumentException("The given keyFrame ID cannot be empty!");
    }
    boolean exists = false;
    for (IKeyFrame kf : keyFrameList) {
      if (kf.getKeyFrameID().equals(keyFrameID)) {
        exists = true;
      }
    }
    return exists;
  }

  /**
   * The builder for the model that utilizes AnimationReader and AnimationBuilder. This builder
   * reads a txt file and creates a model based on the information in the given txt file for the
   * animation model. This builder converts the text information into an actual animation model.
   * This builder can set the canvas size, add shapes, add motions, and finally build and construct
   * the model.
   */
  public static final class Builder implements AnimationBuilder<IModel> {

    IModel model = new Model();

    @Override
    public IModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
      model.setLeftMostX(x);
      model.setUpMostY(y);
      model.setBorderW(width);
      model.setBorderH(height);
      return this;
    }

    @Override
    public AnimationBuilder<IModel> declareShape(String name, String type) {
      String initRandomID = name + (new Random().nextInt(999999999));
      String finalRandomID = this.checkForShapeID(initRandomID, name);
      switch (type) {
        case "ellipse":
          model.addShape(new MyEllipse(name), finalRandomID);
          break;
        case "rectangle":
          model.addShape(new MyRectangle(name), finalRandomID);
          break;
        default:
          break;
      }
      return this;
    }

    /**
     * A helper to creating a unique shape identifier. Checks if the randomly generated shape
     * identifier already exists in the model. If it does, it generates a new random shape ID, and
     * checks if it already exists. The helper method returns a unique random shape ID.
     *
     * @param randomID the shape ID that is being checked if it already exists in the model
     * @param name the name of the shape
     * @return a unique shape ID that doesn't exist in the model
     */
    private String checkForShapeID(String randomID, String name) {
      if (model.shapeIdentifierExists(randomID)) {
        String newRandomID = name + new Random().nextInt(999999999);
        return this.checkForShapeID(newRandomID, name);
      } else {
        return randomID;
      }
    }

    @Override
    public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
        int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      ShapeType shapeType = model.getShapeTypeFromShapeName(name);
      IShape initShape = null;
      IShape endShape = null;
      switch (shapeType) {
        case ELLIPSE:
          initShape = new MyEllipse(w1, h1, x1, y1, r1, g1, b1, name);
          endShape = new MyEllipse(w2, h2, x2, y2, r2, g2, b2, name);
          break;
        case RECTANGLE:
          initShape = new MyRectangle(w1, h1, x1, y1, r1, g1, b1, name);
          endShape = new MyRectangle(w2, h2, x2, y2, r2, g2, b2, name);
          break;
        default:
          break;
      }

      String initRandomID = name + (new Random().nextInt(999999999));
      String finalRandomID = this.checkForShapeID(initRandomID, name);
      model.addShape(initShape, finalRandomID);
      initRandomID = name + (new Random().nextInt(999999999));
      finalRandomID = this.checkForShapeID(initRandomID, name);
      model.addShape(endShape, finalRandomID);

      String initRandomMotionID = "motion" + (new Random().nextInt(999999999));
      String finalRandomMotionID = this.checkForMotionID(initRandomMotionID);

      IMotion newMotion = new Motion(t1, t2, initShape, endShape);
      model.addMotion(newMotion, finalRandomMotionID);

      this.addKeyFrameForEachMotion(newMotion);

      return this;
    }

    /**
     * Adds 2 new keyFrames to the building model. The 2 new keyFrames are built based on the given
     * motion. The first keyFrame will take in the motion's shapeName, the starting tick, and the
     * elements of the starting shape. The second keyFrame will take in the motion's shapeName, the
     * end tick, and the elements of the ending shape.
     *
     * @param motion the motion that the new keyFrames will refer to to add
     */
    private void addKeyFrameForEachMotion(IMotion motion) {
      String name = motion.getStartingShapeName();

      IShape shape1 = motion.getStartShape();
      int t1 = motion.getStartTick();
      int x1 = shape1.getX();
      int y1 = shape1.getY();
      int w1 = shape1.getWidth();
      int h1 = shape1.getHeight();
      int r1 = shape1.getRed();
      int g1 = shape1.getGreen();
      int b1 = shape1.getBlue();

      IShape shape2 = motion.getEndShape();
      int t2 = motion.getEndTick();
      int x2 = shape2.getX();
      int y2 = shape2.getY();
      int w2 = shape2.getWidth();
      int h2 = shape2.getHeight();
      int r2 = shape2.getRed();
      int g2 = shape2.getGreen();
      int b2 = shape2.getBlue();

      this.addKeyframe(name, t1, x1, y1, w1, h1, r1, g1, b1);
      this.addKeyframe(name, t2, x2, y2, w2, h2, r2, g2, b2);
    }

    /**
     * A helper to creating a unique motion ID. Checks if the randomly generated motion ID already
     * exists in the model. If it does, it generates a new random motion ID, and checks if it
     * already exists. The helper method returns a unique random motion ID.
     *
     * @param initRandomID the motion ID that is being checked if it already exists in the model
     * @return a unique motion ID that doesn't exist in the model
     */
    private String checkForMotionID(String initRandomID) {
      if (model.motionIDExists(initRandomID)) {
        String newRandomID = "motion" + new Random().nextInt(999999999);
        return this.checkForMotionID(newRandomID);
      } else {
        return initRandomID;
      }
    }

    @Override
    public AnimationBuilder<IModel> addKeyframe(String name, int t, int x, int y, int w, int h,
        int r, int g, int b) {
      String initRandomKeyFramenID = "keyFrame" + (new Random().nextInt(999999999));
      String finalRandomKeyFrameID = this.checkForKeyFrameID(initRandomKeyFramenID);
      IKeyFrame kf = new KeyFrame(name, t, x, y, w, h, r, g, b, ShapeType.ELLIPSE);
      model.addKeyFrame(kf, finalRandomKeyFrameID);
      return this;
    }

    /**
     * A helper to creating a unique keyFrame ID. Checks if the randomly generated keyFrame ID
     * already exists in the model. If it does, it generates a new random keyFrame ID, and checks if
     * it already exists. The helper method returns a unique random keyFrame ID.
     *
     * @param initRandomID the keyFrame ID that is being checked if it already exists in the model
     * @return a unique keyFrame ID that doesn't exist in the model
     */
    private String checkForKeyFrameID(String initRandomID) {
      if (model.keyFrameIDExists(initRandomID)) {
        String newRandomID = "keyFrame" + new Random().nextInt(999999999);
        return this.checkForKeyFrameID(newRandomID);
      } else {
        return initRandomID;
      }
    }

  }
}
