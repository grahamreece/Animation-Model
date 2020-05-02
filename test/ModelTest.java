import static junit.framework.TestCase.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import model.IModel;
import model.Model;
import model.keyframe.IKeyFrame;
import model.keyframe.KeyFrame;
import model.motion.IMotion;
import model.motion.Motion;
import org.junit.Test;
import model.shape.IShape;
import model.shape.MyEllipse;
import model.shape.MyRectangle;
import model.shape.ShapeType;

/**
 * Represents a test class for the model class.
 */
public class ModelTest {

  IShape rectInitTo10 = new MyRectangle(50, 100, 200, 200, 255, 0, 0, "R");
  IShape rect51 = new MyRectangle(50, 100, 300, 300, 255, 0, 0, "R");
  IShape rect70 = new MyRectangle(25, 100, 300, 300, 255, 0, 0, "R");
  IShape rect100 = new MyRectangle(25, 100, 200, 200, 255, 0, 0, "R");

  IShape ell6To20 = new MyEllipse(120, 60, 440, 70, 0, 0, 255, "C");
  IShape ell20To50 = new MyEllipse(120, 60, 440, 250, 0, 0, 255, "C");
  IShape ell50To80 = new MyEllipse(120, 60, 440, 370, 0, 170, 85, "C");
  IShape ell80To100 = new MyEllipse(120, 60, 440, 370, 0, 255, 0, "C");

  IMotion motionRect1 = new Motion(1, 10, rectInitTo10, rectInitTo10);
  IMotion motionRect2 = new Motion(10, 50, rectInitTo10, rect51);
  IMotion motionRect3 = new Motion(50, 51, rect51, rect51);
  IMotion motionRect4 = new Motion(51, 70, rect51, rect70);
  IMotion motionRect5 = new Motion(70, 100, rect70, rect100);

  IMotion motionEll1 = new Motion(6, 20, ell6To20, ell6To20);
  IMotion motionEll2 = new Motion(20, 50, ell6To20, ell20To50);
  IMotion motionEll3 = new Motion(50, 70, ell20To50, ell50To80);
  IMotion motionEll4 = new Motion(70, 80, ell50To80, ell50To80);
  IMotion motionEll5 = new Motion(80, 100, ell50To80, ell80To100);

  IKeyFrame keyFrame1 = new KeyFrame("R1", 1,0,0,1,1,0,0,0, ShapeType.RECTANGLE);
  IKeyFrame keyFrame2 = new KeyFrame("R2", 50,50,50,50,50,50,50, 50, ShapeType.RECTANGLE);

  IModel model;

  @Test
  public void initModel() {
    this.model = new Model();
    ArrayList<IShape> emptyShapeList = new ArrayList<IShape>();
    ArrayList<IMotion> emptyMotionList = new ArrayList<IMotion>();
    assertEquals(emptyShapeList, model.getShapeList());
    assertEquals(emptyMotionList, model.getMotionList());
  }

  @Test
  public void testGetShapeList() {
    this.initModel();
    ArrayList<IShape> emptyShapeList = new ArrayList<IShape>();
    assertEquals(emptyShapeList, model.getShapeList());
  }

  @Test
  public void testGetShapeListAndAddShape() {
    this.initModel();
    ArrayList<IShape> shapeList1 = new ArrayList<IShape>(Arrays.asList(rectInitTo10));
    model.addShape(rectInitTo10, "R1");
    assertEquals(shapeList1, model.getShapeList());
  }

  @Test
  public void testGetMotionList() {
    this.initModel();
    ArrayList<IMotion> emptyMotionList = new ArrayList<IMotion>();
    assertEquals(emptyMotionList, model.getMotionList());
  }

  @Test
  public void testGetMotionListAndAddMotion() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    ArrayList<IMotion> motionList1 = new ArrayList<IMotion>(Arrays.asList(motionRect1));
    model.addMotion(motionRect1, "motionR1");
    assertEquals(motionList1, model.getMotionList());
  }

  @Test
  public void testAddShape() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");

    ArrayList<IShape> shapeList1 = new ArrayList<IShape>(Arrays.asList(rectInitTo10));

    assertEquals(shapeList1, model.getShapeList());
  }

  @Test
  public void testAddShape2() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");

    ArrayList<IShape> shapeList1 = new ArrayList<IShape>(Arrays.asList(rectInitTo10, rect51));

    assertEquals(shapeList1, model.getShapeList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeBadEmpty() {
    this.initModel();

    model.addShape(rectInitTo10, "");
  }

  @Test(expected = IllegalStateException.class)
  public void testAddShapeBadExists() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rectInitTo10, "R1");
  }

  @Test
  public void testAddMotion() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addMotion(motionRect1, "motionR1");

    ArrayList<IMotion> motionList1 = new ArrayList<IMotion>(Arrays.asList(motionRect1));

    assertEquals(motionList1, model.getMotionList());
  }

  @Test
  public void testAddMotion2() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");

    ArrayList<IMotion> motionList1 = new ArrayList<IMotion>(
        Arrays.asList(motionRect1, motionRect2));

    assertEquals(motionList1, model.getMotionList());
  }

  @Test(expected = IllegalStateException.class)
  public void testAddMotionBadExists() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect1, "motionR1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionBadEmpty() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addMotion(motionRect1, "");

  }

  @Test
  public void testGetShape() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");

    assertEquals(rectInitTo10, model.getShape("R1"));
  }

  @Test
  public void testGetShape2() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");

    assertEquals(rect51, model.getShape("R2"));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetShapeBadDoesntExist() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");

    assertEquals(rectInitTo10, model.getShape("R2"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeBadEmpty() {
    this.initModel();

    assertEquals(rectInitTo10, model.getShape(""));
  }

  @Test
  public void testGetMotion() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addMotion(motionRect1, "motionR1");

    assertEquals(motionRect1, model.getMotion("motionR1"));
  }

  @Test
  public void testGetMotion2() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");

    assertEquals(motionRect2, model.getMotion("motionR2"));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetMotionBadDoesntExist() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addMotion(motionRect1, "motionR1");

    assertEquals(rectInitTo10, model.getMotion("motionR2"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetMotionBadEmpty() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addMotion(motionRect1, "motionR1");

    assertEquals(rectInitTo10, model.getMotion(""));
  }

  @Test
  public void testDeleteShape() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");

    model.deleteShape(rectInitTo10, "R1");

    ArrayList<IShape> shapeList1 = new ArrayList<IShape>(Arrays.asList(rect51));

    assertEquals(shapeList1, model.getShapeList());
  }

  @Test(expected = IllegalStateException.class)
  public void testDeleteShapeBadNotMatchingIdentifier() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");

    model.deleteShape(rect51, "R1");
  }

  @Test(expected = IllegalStateException.class)
  public void testDeleteShapeUnidentifiedShapeID() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");

    model.deleteShape(rect70, "R3");

    ArrayList<IShape> shapeList1 = new ArrayList<IShape>(Arrays.asList(rectInitTo10, rect51));

    assertEquals(shapeList1, model.getShapeList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteShapeBadEmpty() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");

    model.deleteShape(rectInitTo10, "");
  }

  @Test
  public void testDeleteMotion() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");

    model.deleteMotion(motionRect1, "motionR1");

    ArrayList<IMotion> motionList1 = new ArrayList<IMotion>(Arrays.asList(motionRect2));

    assertEquals(motionList1, model.getMotionList());
  }

  @Test(expected = IllegalStateException.class)
  public void testDeleteMotionBadNotMatchingID() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");

    model.deleteMotion(motionRect2, "motionR1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteMotionEmpty() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");

    model.deleteMotion(motionRect2, "");
  }

  @Test(expected = IllegalStateException.class)
  public void testDeleteMotionUnidentifiedMotionID() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");

    model.deleteMotion(motionRect3, "motionR3");

    ArrayList<IMotion> motionList1 = new ArrayList<IMotion>(
        Arrays.asList(motionRect1, motionRect2));

    assertEquals(motionList1, model.getMotionList());
  }

  @Test
  public void testDeleteAllShape() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");

    model.deleteAllShape();

    ArrayList<IShape> shapeList1 = new ArrayList<IShape>();

    assertEquals(shapeList1, model.getShapeList());
  }

  @Test
  public void testDeleteAllShape2() {
    this.initModel();

    model.deleteAllShape();

    ArrayList<IShape> shapeList1 = new ArrayList<IShape>();

    assertEquals(shapeList1, model.getShapeList());
  }

  @Test
  public void testDeleteAllMotion() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");

    model.deleteAllMotion();

    ArrayList<IMotion> motionList1 = new ArrayList<IMotion>();

    assertEquals(motionList1, model.getMotionList());
  }

  @Test
  public void testDeleteAllMotion2() {
    this.initModel();

    model.deleteAllMotion();

    ArrayList<IMotion> motionList1 = new ArrayList<IMotion>();

    assertEquals(motionList1, model.getMotionList());
  }

  @Test
  public void testShapeIdentifierExists() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");

    assertEquals(true, model.shapeIdentifierExists("R1"));
  }

  @Test
  public void testShapeIdentifierExists2() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");

    assertEquals(false, model.shapeIdentifierExists("R3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShapeIdentifierExistsBadEmpty() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");

    assertEquals(false, model.shapeIdentifierExists(""));
  }

  @Test
  public void testMotionIDExists() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");

    assertEquals(true, model.motionIDExists("motionR1"));
  }

  @Test
  public void testMotionIDExists2() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");

    assertEquals(false, model.motionIDExists("motionR3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMotionIDExistsBadEmpty() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");

    assertEquals(true, model.motionIDExists(""));
  }

  @Test
  public void testTextRenderOutput() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    assertEquals("shape R RECTANGLE\n"
            + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
            + "shape C ELLIPSE\n"
            + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 170 85\n"
            + "motion C 80 440 370 120 60 0 170 85 100 440 370 120 60 0 255 0",
        model.textRenderOutput());
  }


  @Test
  public void testTextRenderOutput2() {
    this.initModel();
    this.testTextRenderOutput();

    IShape newRect = new MyRectangle(50, 40, 400, 200, 255, 255, 0, "M");
    IShape newRect2 = new MyRectangle(50, 40, 400, 300, 255, 255, 0, "M");
    model.addShape(newRect, "M1");
    model.addShape(newRect, "M2");

    IMotion motionRect1 = new Motion(1, 25, newRect, newRect2);
    model.addMotion(motionRect1, "motionM1");

    assertEquals("shape R RECTANGLE\n"
            + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
            + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
            + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
            + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
            + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
            + "shape C ELLIPSE\n"
            + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
            + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
            + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
            + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 170 85\n"
            + "motion C 80 440 370 120 60 0 170 85 100 440 370 120 60 0 255 0\n"
            + "shape M RECTANGLE\n"
            + "motion M 1 400 200 50 40 255 255 0 25 400 300 50 40 255 255 0",
        model.textRenderOutput());
  }

  @Test
  public void testMotionForShapeNameExists() {
    this.initModel();

    model.addShape(rectInitTo10, "R1");

    model.addMotion(motionRect1, "motionR1");

    assertEquals(true, model.motionForShapeNameExists("R"));
    assertEquals(false, model.motionForShapeNameExists("C"));
  }

  @Test
  public void testLessThanAllMotionTicks() {
    this.initModel();

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    assertEquals(true, model.lessThanAllMotionTicks(5));
    assertEquals(false, model.lessThanAllMotionTicks(10));
  }

  @Test
  public void testGreaterThanAllMotionTicks() {
    this.initModel();

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    assertEquals(true, model.greaterThanAllMotionTicks(110));
    assertEquals(false, model.greaterThanAllMotionTicks(80));
  }

  @Test (expected = IllegalStateException.class)
  public void testDeleteMotionBadInconsistent() {
    this.initModel();

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    model.deleteMotion(motionEll3, "motionC1");
  }

  @Test
  public void testGetRightMostX() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    assertEquals(440, model.getRightMostX());
  }

  @Test
  public void testGetRightMostX2() {
    this.initModel();
    model.addShape(rect51, "R2");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect3, "motionR3");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    assertEquals(440, model.getRightMostX());

    this.model.deleteAllMotion();
    assertEquals(0, this.model.getRightMostX());
  }

  @Test
  public void testGetDownMostY() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    assertEquals(370, model.getDownMostY());
  }

  @Test
  public void testGetDownMostY2() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    assertEquals(300, model.getDownMostY());

    this.model.deleteAllMotion();
    assertEquals(0, this.model.getDownMostY());
  }

  @Test
  public void testGetShapeTypeFromShapeName() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    assertEquals(ShapeType.RECTANGLE, this.model.getShapeTypeFromShapeName("R"));
    assertEquals(ShapeType.ELLIPSE, this.model.getShapeTypeFromShapeName("C"));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetShapeTypeFromShapeNameNameNotInModel() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    this.model.getShapeTypeFromShapeName("X");
  }

  @Test
  public void testGetSetLeftMostX() {
    this.initModel();
    assertEquals(0, this.model.getLeftMostX());
    this.model.setLeftMostX(77);
    assertEquals(77, this.model.getLeftMostX());
    this.model.setLeftMostX(-11);
    assertEquals(-11, this.model.getLeftMostX());

  }

  @Test
  public void testGetSetUpMostY() {
    this.initModel();
    assertEquals(0, this.model.getUpMostY());
    this.model.setUpMostY(77);
    assertEquals(77, this.model.getUpMostY());
    this.model.setUpMostY(-11);
    assertEquals(-11, this.model.getUpMostY());

  }

  @Test
  public void testGetSetBorderWidth() {
    this.initModel();
    assertEquals(0, this.model.getBorderW());
    this.model.setBorderW(77);
    assertEquals(77, this.model.getBorderW());

  }

  @Test
  public void testGetSetBorderHeight() {
    this.initModel();
    assertEquals(0, this.model.getBorderH());
    this.model.setBorderH(77);
    assertEquals(77, this.model.getBorderH());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNegativeBorderWError() {
    this.initModel();
    this.model.setBorderW(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetNegativeBorderHError() {
    this.initModel();
    this.model.setBorderH(-1);
  }

  @Test
  public void testGetInitShape() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    assertEquals(this.rectInitTo10, this.model.getInitShape("R"));
    assertEquals(this.ell6To20, this.model.getInitShape("C"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetInitShapeErrorNameNotInMotions() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    this.model.getInitShape("X");
  }

  @Test
  public void testGetLastTick() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    assertEquals(100, this.model.getLastTick());
  }


  @Test
  public void testGetKeyFrameList() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    model.addKeyFrame(keyFrame1, "F1");
    model.addKeyFrame(keyFrame2, "F2");

    ArrayList<IKeyFrame> list = new ArrayList<IKeyFrame>(Arrays.asList(keyFrame1, keyFrame2));

    assertEquals(list, model.getKeyFrameList());
  }

  @Test
  public void testAddKeyFrame() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    model.addKeyFrame(keyFrame1, "F1");
    model.addKeyFrame(keyFrame2, "F2");

    assertEquals(model.getKeyFrameList().get(0), keyFrame1);
    assertEquals(model.getKeyFrameList().get(1), keyFrame2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyFrameEmpty() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    model.addKeyFrame(keyFrame1, "");
  }

  @Test(expected = IllegalStateException.class)
  public void testAddKeyFrameAlreadyIn() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    model.addKeyFrame(keyFrame1, "F1");
    model.addKeyFrame(keyFrame2, "F1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testKeyFrameIDExistsEmpty() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    model.addKeyFrame(keyFrame1, "F1");

    model.keyFrameIDExists("");
  }

  @Test
  public void testKeyFrameIDExists() {
    this.initModel();
    model.addShape(rectInitTo10, "R1");
    model.addShape(rect51, "R2");
    model.addShape(rect70, "R3");
    model.addShape(rect100, "R4");

    model.addShape(ell6To20, "C1");
    model.addShape(ell20To50, "C2");
    model.addShape(ell50To80, "C3");
    model.addShape(ell80To100, "C4");

    model.addMotion(motionRect1, "motionR1");
    model.addMotion(motionRect2, "motionR2");
    model.addMotion(motionRect3, "motionR3");
    model.addMotion(motionRect4, "motionR4");
    model.addMotion(motionRect5, "motionR5");

    model.addMotion(motionEll1, "motionC1");
    model.addMotion(motionEll2, "motionC2");
    model.addMotion(motionEll3, "motionC3");
    model.addMotion(motionEll4, "motionC4");
    model.addMotion(motionEll5, "motionC5");

    model.addKeyFrame(keyFrame1, "F1");

    assertEquals(true, model.keyFrameIDExists("F1"));
    assertEquals(false, model.keyFrameIDExists("F2"));
  }


}
