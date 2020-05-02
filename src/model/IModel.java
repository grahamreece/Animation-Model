package model;

import java.util.ArrayList;
import model.keyframe.IKeyFrame;
import model.motion.IMotion;
import model.shape.IShape;
import model.shape.ShapeType;

/**
 * The interface for representing an animation that includes motions of shapes. The model holds a
 * list of shapes and motions, which can be animated. The model is basically an animation model. The
 * model animates images at every tick. Thus, the model should know which shapes and which motions
 * it is executing at every tick. The details for how the model operates on every tick is not given
 * yet, but we know that the model holds several motions and the shapes for these motions. A motion
 * for a specific shape cannot exist without the specific shape already in the model.
 */
public interface IModel {

  /**
   * Returns an IShape in the model that matches the given shape Identifier.
   *
   * @param shapeIdentifier the shapeIdentifier that serves as the shape ID
   * @return the shape that matches the given shapeName
   * @throws IllegalStateException if the shape does not exist in this model
   * @throws IllegalArgumentException if the shapeName is empty
   */
  IShape getShape(String shapeIdentifier) throws IllegalStateException, IllegalArgumentException;

  /**
   * Returns an IMotion in the model that matches the given motionID.
   *
   * @param motionID the motion ID of the motion
   * @return the motion that matches the given motionID
   * @throws IllegalStateException if the motion does not exist in this model
   * @throws IllegalArgumentException if the motion ID is empty
   */
  IMotion getMotion(String motionID) throws IllegalStateException, IllegalArgumentException;

  /**
   * Adds the given shape to the model. The added shape is set a shape identifier, which serves as
   * the id for the shape.
   *
   * @param shape the shape that is being added to the model
   * @param shapeIdentifier the shapeIdentifier that serves as the shape ID
   * @throws IllegalStateException if the shapeIdentifier is already taken by another shape in the
   *                                  model or if the given shape name is empty
   * @throws IllegalArgumentException if the shapeIdentifier is empty
   */
  void addShape(IShape shape, String shapeIdentifier)
      throws IllegalStateException, IllegalArgumentException;

  /**
   * Adds the given motion to the model. The added motion is set a motionID.
   *
   * @param motion the motion that is being added to the model
   * @param motionID the motion id that is being assigned to the added motion
   * @throws IllegalStateException if the motionID is already taken by another motion in the model
   *                                or if the given motionID is empty. Or if the motion
   *                                is inconsistent to the existing motions. In other words, the
   *                                adding motion's starting shapeName has to be the same as an
   *                                existing motion's end shapeName.
   * @throws IllegalArgumentException if the motionID is empty
   */
  void addMotion(IMotion motion, String motionID)
      throws IllegalStateException, IllegalArgumentException;

  /**
   * Deletes the given shape in the model. If the shape does not exist in the model, nothing is
   * deleted.
   *
   * @param shape the shape that is being deleted in the model
   * @param shapeIdentifier the shapeIdentifier that serves as the shape ID
   * @throws IllegalArgumentException if the given shapeIdentifier is empty
   * @throws IllegalStateException if the given shapeIdentifier does not match the given shape.
   */
  void deleteShape(IShape shape, String shapeIdentifier)
      throws IllegalArgumentException, IllegalStateException;

  /**
   * Deletes the given motion in the model. If the motion does not exist in the model, nothing is
   * deleted.
   *
   * @param motion the motion that is being deleted in the model
   * @param motionID the motion id for the given motion
   * @throws IllegalArgumentException if the given motionID is empty
   * @throws IllegalStateException if the given motionID does not match the given motion. Or if
   *                                  the deleting motion will cause an inconsistency in the
   *                                  animation
   */
  void deleteMotion(IMotion motion, String motionID)
      throws IllegalArgumentException, IllegalStateException;

  /**
   * Deletes all the shapes in this model. Empties the list of shapes of the model.
   */
  void deleteAllShape();

  /**
   * Deletes all the motions in this model. Empties the list of motions of the model.
   */
  void deleteAllMotion();

  /**
   * Determines if a shape that matches the shapeIdentifier/shape ID exists in this model.
   *
   * @param shapeIdentifier the shapeIdentifier that serves as the shape ID
   * @return true if the shape that matches the shapeName/shape ID exists.
   * @throws IllegalArgumentException if the given shapeName is empty
   */
  boolean shapeIdentifierExists(String shapeIdentifier) throws IllegalArgumentException;

  /**
   * Determines if a motion that matches the motion ID exists in this model.
   *
   * @param motionID the motionID to be checked to exist in this model
   * @return true if the motion that matches the motionID exists.
   * @throws IllegalArgumentException if the given motionID is empty
   */
  boolean motionIDExists(String motionID) throws IllegalArgumentException;

  /**
   * Returns the text description of the animation in String format. Returns all the existing
   * shapes, and all the existing motions for those shapes in the animation.
   *
   * @return the text description of the animation (of the model)
   */
  String textRenderOutput();

  /**
   * Returns the list of the shapes in the model in an ArrayList.
   *
   * @return list of shapes in the model
   */
  ArrayList<IShape> getShapeList();

  /**
   * Returns the list of the motions in the model in an ArrayList.
   *
   * @return list of motions in the model
   */
  ArrayList<IMotion> getMotionList();

  /**
   * Determines if the given String for the shape name is an existing shape name for any of the
   * motion's starting or ending shape's shape name.
   *
   * @param shapeName the shapeName that will be compared to to all the existing motion's shape
   *                        names.
   * @return true if given shape name is an existing shape name for any of the motion's starting or
   *                    ending shape's shape name
   */
  boolean motionForShapeNameExists(String shapeName);

  /**
   * Determines if the given tick is less than all of the existing motion's start tick.
   *
   * @param t the tick that is being compared to to all the existing motion's start tick
   * @return true if the given tick is less than all of the existing motion's start tick
   */
  boolean lessThanAllMotionTicks(int t);

  /**
   * Determines if the given tick is greater than all of the existing motion's end tick.
   *
   * @param t the tick that is being compared to to all the existing motion's end tick
   * @return true if the given tick is greater than all of the existing motion's end tick
   */
  boolean greaterThanAllMotionTicks(int t);

  /**
   * Returns the ShapeType of the chosen shape, which is chosen by the given shapeName.
   *
   * @param shapeName the shape name of the chosen shape
   * @return the ShapeType for the chosen shape
   * @throws IllegalStateException the chosen shape does not exist in this model
   */
  ShapeType getShapeTypeFromShapeName(String shapeName) throws IllegalStateException;

  /**
   * Sets the leftMostX of the model to the given integer.
   *
   * @param x the integer that will be set as the leftMostX
   */
  void setLeftMostX(int x);

  /**
   * Sets the upMostY of the model to the given integer.
   *
   * @param y the integer that will be set as the upMostY
   */
  void setUpMostY(int y);

  /**
   * Sets the borderWidth of the model to the given integer. Throws an exception if the width for
   * the model is wanted to be set to a negative integer.
   *
   * @param w the integer that will be set as the borderWidth
   * @throws IllegalArgumentException if the integer is a negative integer
   */
  void setBorderW(int w);

  /**
   * Sets the borderHeight of the model to the given integer. Throws an exception if the height for
   * the model is wanted to be set to a negative integer.
   *
   * @param h the integer that will be set as the borderHeight
   * @throws IllegalArgumentException if the integer is a negative integer
   */
  void setBorderH(int h);

  /**
   * Returns the leftMostX of the model as an integer.
   *
   * @return the leftMostX of the model
   */
  int getLeftMostX();

  /**
   * Returns the upMostY of the model as an integer.
   *
   * @return the upMostY of the model
   */
  int getUpMostY();

  /**
   * Returns the borderWidth of the model as an integer.
   *
   * @return the borderWidth of the model
   */
  int getBorderW();

  /**
   * Returns the borderHeight of the model as an integer.
   *
   * @return the borderHeight of the model
   */
  int getBorderH();

  /**
   * Returns the rightMostX value for the model.
   *
   * @return the rightMostX value from the model
   */
  int getRightMostX();

  /**
   * Returns the getDownMostY value for the model.
   *
   * @return the getDownMostY value from the model
   */
  int getDownMostY();

  /**
   * Returns the initial shape state of all motions in the model for the given shapeName. Throws
   * an exception if there is not a motion with a shape with the given shape name in the model.
   *
   * @param shapeName the shapeName for the shape that the initial shape is being returned
   * @return the initial shape state of all motions in the model for the given shapeName
   * @throws IllegalArgumentException if the given shape Name is not used in any of the motions of
   *                                  the model. In other words, there are no motions for that shape
   *                                  in the model.
   */
  IShape getInitShape(String shapeName);

  /**
   * Return the last tick of all motions in the model. In other words, return the last tick of the
   * animation. The returning value should be an int.
   *
   * @return the last tick of the animation as an int
   */
  int getLastTick();

  /**
   * Adds the given keyFrame to the model by adding it to the keyFrameList field.
   *
   * @param keyFrame the keyFrame that is being added
   * @param keyFrameID the keyFrame ID for the keyFrame that is being added
   * @throws IllegalArgumentException if the keyFrameID is empty
   * @throws IllegalStateException if the given keyFrameID already exists in the model
   */
  void addKeyFrame(IKeyFrame keyFrame, String keyFrameID) throws IllegalArgumentException,
          IllegalStateException;

  /**
   * Deletes the given keyFrame from the model.
   *
   * @param keyFrame the keyFrame that will be deleted from the model
   * @throws IllegalStateException if the given keyFrame doesn't exist in the model
   */
  void deleteKeyFrame(IKeyFrame keyFrame) throws IllegalStateException;

  /**
   * Determine if the given keyFrameID exists in the model or not.
   *
   * @param keyFrameID the keyFrame ID in String form
   * @return true if the given keyFrameID already exists in the keyFrameList
   * @throws IllegalArgumentException if the given keyFrameID is an empty String
   */
  boolean keyFrameIDExists(String keyFrameID) throws IllegalArgumentException;

  /**
   * Deletes all the shapes in the model with the same shape name as the given shape name. This will
   * also delete all the motions that is for the given shape.
   *
   * @param shapeName the shapeName for the shape that is being deleted
   * @throws IllegalStateException if the given shape does not exist in the model
   */
  void deleteShapeAndMotionForShapeName(String shapeName) throws IllegalStateException;

  /**
   * Returns the list of the keyFrames in the model in an ArrayList.
   *
   * @return list of keyFrames in the model
   */
  ArrayList<IKeyFrame> getKeyFrameList();

  /**
   * Replaces the KeyFrame for the given ith element of the model in the view.
   *
   * @param i the number for the element location in the KeyFrameList in the model
   * @param kf the KeyFrame that is being added
   */
  void modifyKFHelper(int i, IKeyFrame kf);
}
