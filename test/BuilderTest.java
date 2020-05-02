import static org.junit.Assert.assertEquals;

import model.IModel;
import model.Model;
import model.Model.Builder;
import model.motion.IMotion;
import model.motion.Motion;
import model.shape.IShape;
import model.shape.MyEllipse;
import model.shape.MyRectangle;

import org.junit.Before;
import org.junit.Test;

/**
 * The test for the builder class in the Model class. Tests for the public methods in the builder
 * class.
 */
public class BuilderTest {

  IModel model;
  Builder builder;

  IShape rectInitTo10 = new MyRectangle(50, 100, 200, 200, 255, 0, 0, "R");
  IShape ell6To20 = new MyEllipse(120, 60, 440, 70, 0, 0, 255, "C");
  IShape ell20To50 = new MyEllipse(120, 60, 440, 250, 0, 0, 255, "C");
  IShape ell50To80 = new MyEllipse(120, 60, 440, 370, 0, 170, 85, "C");

  IMotion motionRect1 = new Motion(1, 10, rectInitTo10, rectInitTo10);
  IMotion motionEll1 = new Motion(6, 20, ell6To20, ell6To20);
  IMotion motionEll2 = new Motion(20, 50, ell6To20, ell20To50);

  @Before
  public void initModelBuilder() {
    this.model = new Model();
    this.builder = new Builder();
  }

  @Test
  public void testBuild() {
    initModelBuilder();
    assertEquals(this.model.getShapeList(), builder.build().getShapeList());
    assertEquals(this.model.getMotionList(), builder.build().getMotionList());
  }

  @Test
  public void testSetBounds() {
    initModelBuilder();
    builder.setBounds(30, 30, 20, 20);
    model.setBorderH(20);
    model.setBorderW(20);
    model.setUpMostY(30);
    model.setLeftMostX(30);
    assertEquals(this.model.getUpMostY(), builder.build().getUpMostY());
    assertEquals(this.model.getLeftMostX(), builder.build().getLeftMostX());
  }

  @Test
  public void testDeclareShape() {
    initModelBuilder();
    builder.declareShape("R", "rectangle");

    model.addShape(rectInitTo10, "R1");

    assertEquals(model.getShapeList().size(), builder.build().getShapeList().size());
  }

  @Test
  public void testDeclareShape2() {
    initModelBuilder();
    builder.declareShape("R", "rectangle");
    builder.declareShape("C", "ellipse");
    builder.declareShape("C", "ellipse");
    builder.declareShape("C", "ellipse");

    model.addShape(rectInitTo10, "R1");
    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");

    assertEquals(model.getShapeList().size(), builder.build().getShapeList().size());
  }

  @Test
  public void testAddMotion() {
    initModelBuilder();
    builder.declareShape("R", "rectangle");
    builder.addMotion("R", 1, 200, 255, 50, 100, 255, 0, 0, 10,
            200, 255, 50, 100, 255, 0, 0);

    model.addShape(rectInitTo10, "R1");
    model.addMotion(motionRect1, "motion1");

    assertEquals(model.getMotionList().size(), builder.build().getMotionList().size());
  }

  @Test
  public void testAddMotion2() {
    initModelBuilder();
    builder.declareShape("R", "rectangle");
    builder.declareShape("C", "ellipse");
    builder.declareShape("C", "ellipse");
    builder.addMotion("R", 1, 200, 255, 50, 100, 255, 0, 0, 10,
            200, 255, 50, 100, 255, 0, 0);
    builder.addMotion("C", 6, 440, 70, 120, 60, 0, 0, 255, 20,
            440, 250, 120, 60, 0, 0, 255);
    builder.addMotion("C", 20, 440, 250, 120, 60, 0, 0, 255, 50,
            200, 370, 50, 100, 0, 170, 85);

    model.addShape(rectInitTo10, "R1");
    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addMotion(motionRect1, "motion1");
    model.addMotion(motionEll1, "motion2");
    model.addMotion(motionEll2, "motion3");

    assertEquals(model.getMotionList().size(), builder.build().getMotionList().size());
  }

  @Test
  public void testAddKeyFrame() {
    initModelBuilder();
    builder.addKeyframe("R", 3, 3, 4, 4, 5, 6, 7, 8);

    assertEquals(model.getShapeList(), builder.build().getShapeList());
    assertEquals(model.getMotionList(), builder.build().getMotionList());
  }

}
