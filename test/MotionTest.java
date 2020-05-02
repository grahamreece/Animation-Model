import org.junit.Before;
import org.junit.Test;

import model.Model;
import model.motion.IMotion;
import model.motion.Motion;
import model.shape.IShape;
import model.shape.MyEllipse;
import model.shape.MyRectangle;

import static org.junit.Assert.assertEquals;

/**
 * Represents a test class for the Motion class.
 */
public class MotionTest {

  IShape rectInitTo10;
  IShape rect51;
  IShape rect70;
  IShape rect100;

  IShape ell6To20;
  IShape ell50;
  IShape ell70;
  IShape ell80To100;
  IShape ell101;

  IMotion motionRect1;
  IMotion motionRect2;
  IMotion motionRect3;
  IMotion motionRect4;
  IMotion motionRect5;

  IMotion motionEll1;
  IMotion motionEll2;
  IMotion motionEll3;
  IMotion motionEll4;
  IMotion motionEll5;

  Model model;

  @Before
  public void initMotionData() {

    this.rectInitTo10 = new MyRectangle(50, 100, 200, 200, 255, 0, 0, "R");
    this.rect51 = new MyRectangle(50, 100, 300, 300, 255, 0, 0, "R");
    this.rect70 = new MyRectangle(25, 100, 300, 300, 255, 0, 0, "R");
    this.rect100 = new MyRectangle(25, 100, 200, 200, 255, 0, 0, "R");

    this.ell6To20 = new MyEllipse(120, 60, 440, 70, 0, 0, 255, "C");
    this.ell50 = new MyEllipse(120, 60, 440, 250, 0, 0, 255, "C");
    this.ell70 = new MyEllipse(120, 60, 440, 370, 0, 170, 85, "C");
    this.ell80To100 = new MyEllipse(120, 60, 440, 370, 0, 255, 0, "C");
    this.ell101 = new MyEllipse(120, 66, 440, 370, 0, 255, 0, "C");

    this.motionRect1 = new Motion(1, 10, rectInitTo10, rectInitTo10);
    this.motionRect2 = new Motion(10, 50, rectInitTo10, rect51);
    this.motionRect3 = new Motion(50, 51, rect51, rect51);
    this.motionRect4 = new Motion(51, 70, rect51, rect70);
    this.motionRect5 = new Motion(70, 100, rect70, rect100);

    this.motionEll1 = new Motion(6, 20, ell6To20, ell6To20);
    this.motionEll2 = new Motion(20, 50, ell6To20, ell50);
    this.motionEll3 = new Motion(50, 70, ell50, ell70);
    this.motionEll4 = new Motion(70, 80, ell70, ell80To100);
    this.motionEll5 = new Motion(80, 100, ell80To100, ell80To100);

    this.model = new Model();

  }

  @Test(expected = IllegalArgumentException.class)
  public void testDiffShapeNameInConstructor() {
    this.motionRect1 = new Motion(1, 10, this.ell6To20, this.rect51);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTickOneError() {
    this.motionRect1 = new Motion(0, 1, this.ell6To20, this.ell80To100);
    this.motionEll1 = new Motion(-1, 10, this.ell6To20, this.ell80To100);
    this.motionEll1 = new Motion(-33, 10, this.ell6To20, this.ell80To100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTickTwoError() {
    this.motionRect1 = new Motion(1, 0, this.ell6To20, this.ell80To100);
    this.motionEll1 = new Motion(10, -1, this.ell6To20, this.ell80To100);
    this.motionEll1 = new Motion(1, -33, this.ell6To20, this.ell80To100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTickOneAndTwoError() {
    this.motionRect1 = new Motion(0, 0, this.ell6To20, this.ell80To100);
    this.motionEll1 = new Motion(0, -1, this.ell6To20, this.ell80To100);
    this.motionEll1 = new Motion(-1, 0, this.ell6To20, this.ell80To100);
    this.motionEll1 = new Motion(-1, -1, this.ell6To20, this.ell80To100);
    this.motionEll1 = new Motion(-33, -10, this.ell6To20, this.ell80To100);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetMotionIDError() {
    this.initMotionData();
    this.motionRect1.setMotionID("");
    this.motionEll1.setMotionID("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartTickError() {
    this.initMotionData();
    this.motionRect1.setStartTick(-1);
    this.motionEll1.setStartTick(0);
    this.motionRect1.setStartTick(-33);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetEndTickError() {
    this.initMotionData();
    this.motionRect1.setEndTick(-1);
    this.motionEll1.setEndTick(0);
    this.motionRect1.setEndTick(-33);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInitialSizeError() {
    this.motionEll1.setInitialSize(0, 0);
    this.motionEll1.setInitialSize(-1, 50);
    this.motionEll1.setInitialSize(50, -4);
    this.motionEll1.setInitialSize(-10, -1);
    this.motionEll1.setInitialSize(0, 77);
    this.motionEll1.setInitialSize(77, 0);
    this.motionEll1.setInitialSize(-77, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetFinalSizeError() {
    this.motionEll1.setFinalSize(0, 0);
    this.motionEll1.setFinalSize(-1, 50);
    this.motionEll1.setFinalSize(50, -4);
    this.motionEll1.setFinalSize(-10, -1);
    this.motionEll1.setFinalSize(0, 77);
    this.motionEll1.setFinalSize(77, 0);
    this.motionEll1.setFinalSize(-77, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInitialColorError() {
    this.motionRect1.setInitialColor(-1, 0, 255);
    this.motionRect1.setInitialColor(0, -5, 255);
    this.motionRect1.setInitialColor(5, 0, -10);
    this.motionRect1.setInitialColor(-1, -1, 255);
    this.motionRect1.setInitialColor(0, -5, -1);
    this.motionRect1.setInitialColor(-1, 0, -1);
    this.motionRect1.setInitialColor(-1, -1, -5);
    this.motionRect1.setInitialColor(10, 0, 256);
    this.motionRect1.setInitialColor(0, 256, 255);
    this.motionRect1.setInitialColor(295, 0, 0);
    this.motionRect1.setInitialColor(256, 256, 255);
    this.motionRect1.setInitialColor(0, 256, 256);
    this.motionRect1.setInitialColor(256, 0, 260);
    this.motionRect1.setInitialColor(260, 260, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetFinalColorError() {
    this.motionRect1.setFinalColor(-1, 0, 255);
    this.motionRect1.setFinalColor(0, -5, 255);
    this.motionRect1.setFinalColor(5, 0, -10);
    this.motionRect1.setFinalColor(-1, -1, 255);
    this.motionRect1.setFinalColor(0, -5, -1);
    this.motionRect1.setFinalColor(-1, 0, -1);
    this.motionRect1.setFinalColor(-1, -1, -5);
    this.motionRect1.setFinalColor(10, 0, 256);
    this.motionRect1.setFinalColor(0, 256, 255);
    this.motionRect1.setFinalColor(295, 0, 0);
    this.motionRect1.setFinalColor(256, 256, 255);
    this.motionRect1.setFinalColor(0, 256, 256);
    this.motionRect1.setFinalColor(256, 0, 260);
    this.motionRect1.setFinalColor(260, 260, 255);
  }


  @Test
  public void testGetStartTick() {
    this.initMotionData();

    assertEquals(1, this.motionRect1.getStartTick());
    assertEquals(10, this.motionRect2.getStartTick());
    assertEquals(50, this.motionRect3.getStartTick());
    assertEquals(51, this.motionRect4.getStartTick());
    assertEquals(70, this.motionRect5.getStartTick());

  }

  @Test
  public void testGetEndTick() {
    this.initMotionData();

    assertEquals(10, this.motionRect1.getEndTick());
    assertEquals(50, this.motionRect2.getEndTick());
    assertEquals(51, this.motionRect3.getEndTick());
    assertEquals(70, this.motionRect4.getEndTick());
    assertEquals(100, this.motionRect5.getEndTick());
  }

  @Test
  public void testGetMotionID() {
    this.initMotionData();

    assertEquals(null, this.motionRect1.getMotionID());
    assertEquals(null, this.motionRect2.getMotionID());
    assertEquals(null, this.motionRect3.getMotionID());
    assertEquals(null, this.motionRect4.getMotionID());

    this.model.addShape(this.rectInitTo10, "R1");
    this.model.addMotion(this.motionRect1, "M1");
    assertEquals("M1", this.motionRect1.getMotionID());

  }

  @Test
  public void testGetStartShape() {
    this.initMotionData();

    assertEquals(this.rectInitTo10, this.motionRect1.getStartShape());
    assertEquals(this.rectInitTo10, this.motionRect2.getStartShape());
    assertEquals(this.rect51, this.motionRect3.getStartShape());
    assertEquals(this.rect51, this.motionRect4.getStartShape());
    assertEquals(this.rect70, this.motionRect5.getStartShape());

  }

  @Test
  public void testGetEndShape() {
    this.initMotionData();

    assertEquals(this.rectInitTo10, this.motionRect1.getEndShape());
    assertEquals(this.rect51, this.motionRect2.getEndShape());
    assertEquals(this.rect51, this.motionRect3.getEndShape());
    assertEquals(this.rect70, this.motionRect4.getEndShape());
    assertEquals(this.rect100, this.motionRect5.getEndShape());
  }

  @Test
  public void testSetMotionID() {
    this.initMotionData();

    this.motionRect1.setMotionID("Hello");
    assertEquals("Hello", this.motionRect1.getMotionID());

    this.motionEll1.setMotionID("Motion6");
    assertEquals("Motion6", this.motionEll1.getMotionID());

  }

  @Test
  public void testSetStartShape() {
    this.initMotionData();

    this.motionRect1.setStartShape(this.rectInitTo10);
    assertEquals(this.rectInitTo10, this.motionRect1.getStartShape());
    this.motionRect1.setStartShape(this.rect51);
    assertEquals(this.rect51, this.motionRect1.getStartShape());
    this.motionRect1.setStartShape(this.rect70);
    assertEquals(this.rect70, this.motionRect1.getStartShape());
    this.motionRect1.setStartShape(this.rect100);
    assertEquals(this.rect100, this.motionRect1.getStartShape());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartShapeError() {
    this.initMotionData();

    this.motionRect1.setStartShape(this.ell6To20);
  }


  @Test
  public void testSetEndShape() {
    this.initMotionData();

    this.motionRect1.setEndShape(this.rectInitTo10);
    assertEquals(this.rectInitTo10, this.motionRect1.getEndShape());
    this.motionRect1.setEndShape(this.rect51);
    assertEquals(this.rect51, this.motionRect1.getEndShape());
    this.motionRect1.setEndShape(this.rect70);
    assertEquals(this.rect70, this.motionRect1.getEndShape());
    this.motionRect1.setEndShape(this.rect100);
    assertEquals(this.rect100, this.motionRect1.getEndShape());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetEndShapeError() {
    this.initMotionData();

    this.motionRect1.setEndShape(this.ell6To20);
  }


  @Test
  public void testSetStartTick() {
    this.initMotionData();

    this.motionEll1.setStartTick(1);
    assertEquals(1, this.motionEll1.getStartTick());
    this.motionEll1.setStartTick(10);
    assertEquals(10, this.motionEll1.getStartTick());
    this.motionEll1.setStartTick(3333);
    assertEquals(3333, this.motionEll1.getStartTick());
    this.motionEll1.setStartTick(Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, this.motionEll1.getStartTick());

  }

  @Test
  public void testSetEndTick() {
    this.initMotionData();

    this.motionEll1.setEndTick(1);
    assertEquals(1, this.motionEll1.getEndTick());
    this.motionEll1.setEndTick(10);
    assertEquals(10, this.motionEll1.getEndTick());
    this.motionEll1.setEndTick(3333);
    assertEquals(3333, this.motionEll1.getEndTick());
    this.motionEll1.setEndTick(Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, this.motionEll1.getEndTick());

  }

  @Test
  public void testSetInitialPosition() {
    this.initMotionData();

    this.motionRect2.setInitialPosition(50, 60);
    assertEquals(50, this.rectInitTo10.getX());
    assertEquals(60, this.rectInitTo10.getY());

    this.motionRect2.setInitialPosition(1300, 2500);
    assertEquals(1300, this.rectInitTo10.getX());
    assertEquals(2500, this.rectInitTo10.getY());

    this.motionRect2.setInitialPosition(0, -70);
    assertEquals(0, this.rectInitTo10.getX());
    assertEquals(-70, this.rectInitTo10.getY());

  }

  @Test
  public void testSetInitialSize() {
    this.initMotionData();

    this.motionRect2.setInitialSize(1, 1);
    assertEquals(1, this.rectInitTo10.getWidth());
    assertEquals(1, this.rectInitTo10.getHeight());

    this.motionRect2.setInitialSize(50, 61);
    assertEquals(50, this.rectInitTo10.getWidth());
    assertEquals(61, this.rectInitTo10.getHeight());

    this.motionRect2.setInitialSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, this.rectInitTo10.getWidth());
    assertEquals(Integer.MAX_VALUE, this.rectInitTo10.getHeight());

  }

  @Test
  public void testSetInitialColor() {
    this.initMotionData();

    this.motionRect2.setInitialColor(0, 0, 0);
    assertEquals(0, this.rectInitTo10.getRed());
    assertEquals(0, this.rectInitTo10.getGreen());
    assertEquals(0, this.rectInitTo10.getBlue());

    this.motionRect2.setInitialColor(255, 255, 255);
    assertEquals(255, this.rectInitTo10.getRed());
    assertEquals(255, this.rectInitTo10.getGreen());
    assertEquals(255, this.rectInitTo10.getBlue());

    this.motionRect2.setInitialColor(250, 125, 72);
    assertEquals(250, this.rectInitTo10.getRed());
    assertEquals(125, this.rectInitTo10.getGreen());
    assertEquals(72, this.rectInitTo10.getBlue());

  }

  @Test
  public void testSetFinalPosition() {
    this.initMotionData();

    this.motionRect2.setFinalPosition(50, 60);
    assertEquals(50, this.rect51.getX());
    assertEquals(60, this.rect51.getY());

    this.motionRect2.setFinalPosition(1300, 2500);
    assertEquals(1300, this.rect51.getX());
    assertEquals(2500, this.rect51.getY());

    this.motionRect2.setFinalPosition(0, -70);
    assertEquals(0, this.rect51.getX());
    assertEquals(-70, this.rect51.getY());

  }

  @Test
  public void testSetFinalSize() {
    this.initMotionData();

    this.motionRect2.setFinalSize(1, 1);
    assertEquals(1, this.rect51.getWidth());
    assertEquals(1, this.rect51.getHeight());

    this.motionRect2.setFinalSize(50, 61);
    assertEquals(50, this.rect51.getWidth());
    assertEquals(61, this.rect51.getHeight());

    this.motionRect2.setFinalSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
    assertEquals(Integer.MAX_VALUE, this.rect51.getWidth());
    assertEquals(Integer.MAX_VALUE, this.rect51.getHeight());
  }

  @Test
  public void testSetFinalColor() {
    this.initMotionData();

    this.motionRect2.setFinalColor(0, 0, 0);
    assertEquals(0, this.rect51.getRed());
    assertEquals(0, this.rect51.getGreen());
    assertEquals(0, this.rect51.getBlue());

    this.motionRect2.setFinalColor(255, 255, 255);
    assertEquals(255, this.rect51.getRed());
    assertEquals(255, this.rect51.getGreen());
    assertEquals(255, this.rect51.getBlue());

    this.motionRect2.setFinalColor(250, 125, 72);
    assertEquals(250, this.rect51.getRed());
    assertEquals(125, this.rect51.getGreen());
    assertEquals(72, this.rect51.getBlue());

  }

  @Test
  public void testGetStartingShapeName() {
    this.initMotionData();

    assertEquals("R", this.motionRect1.getStartingShapeName());
    assertEquals("R", this.motionRect2.getStartingShapeName());
    assertEquals("R", this.motionRect3.getStartingShapeName());
    assertEquals("R", this.motionRect4.getStartingShapeName());
    assertEquals("R", this.motionRect5.getStartingShapeName());
    assertEquals("C", this.motionEll1.getStartingShapeName());
    assertEquals("C", this.motionEll2.getStartingShapeName());
    assertEquals("C", this.motionEll3.getStartingShapeName());
    assertEquals("C", this.motionEll4.getStartingShapeName());
    assertEquals("C", this.motionEll5.getStartingShapeName());

  }

  @Test
  public void testGetEndingShapeName() {
    this.initMotionData();

    assertEquals("R", this.motionRect1.getEndingShapeName());

    assertEquals("C", this.motionEll1.getEndingShapeName());
  }

  @Test
  public void testGetStartingShapeId() {
    this.initMotionData();

    assertEquals(null, this.motionRect1.getStartingShapeId());
    assertEquals(null, this.motionEll1.getStartingShapeId());

    this.model.addShape(this.rectInitTo10, "R1");
    this.model.addShape(this.ell6To20, "C1");

    assertEquals("R1", this.motionRect1.getStartingShapeId());
    assertEquals("C1", this.motionEll1.getStartingShapeId());

  }

  @Test
  public void testGetEndingShapeId() {
    this.initMotionData();

    assertEquals(null, this.motionRect1.getEndingShapeId());
    assertEquals(null, this.motionEll1.getEndingShapeId());

    this.model.addShape(this.rectInitTo10, "R1");
    this.model.addShape(this.ell6To20, "C1");

    assertEquals("R1", this.motionRect1.getEndingShapeId());
    assertEquals("C1", this.motionEll1.getEndingShapeId());

  }

  @Test
  public void testGetMotionText() {
    this.initMotionData();

    assertEquals("motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n",
        this.motionRect1.getMotionText());
    assertEquals("motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n",
        this.motionRect4.getMotionText());
    assertEquals("motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n",
        this.motionEll1.getMotionText());
    assertEquals("motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n",
        this.motionEll3.getMotionText());


  }

  @Test
  public void testHasSameStartAndEndShape() {
    this.initMotionData();

    assertEquals(true, this.motionRect1.hasSameStartAndEndShape());
    assertEquals(false, this.motionRect2.hasSameStartAndEndShape());
    assertEquals(true, this.motionRect3.hasSameStartAndEndShape());
    assertEquals(false, this.motionRect4.hasSameStartAndEndShape());
    assertEquals(false, this.motionRect5.hasSameStartAndEndShape());
    assertEquals(true, this.motionEll1.hasSameStartAndEndShape());
    assertEquals(false, this.motionEll2.hasSameStartAndEndShape());
    assertEquals(false, this.motionEll3.hasSameStartAndEndShape());
    assertEquals(false, this.motionEll4.hasSameStartAndEndShape());
    assertEquals(true, this.motionEll5.hasSameStartAndEndShape());

  }

  @Test
  public void testGetShapeAtTick1() {
    this.initMotionData();
    assertEquals(new MyRectangle(50, 100, 200, 200, 255, 0, 0, "R"),
        motionRect1.getShapeAtTick(2));
  }

  @Test
  public void testGetShapeAtTick2() {
    this.initMotionData();
    assertEquals(new MyRectangle(50, 100, 250, 250, 255, 0, 0, "R"),
        motionRect2.getShapeAtTick(30));
    assertEquals(new MyRectangle((int) 37.5, 100, 300, 300, 255, 0, 0, "R"),
        motionRect4.getShapeAtTick(61));
    assertEquals(new MyEllipse(120, 60, 440, 310, 0, 85, 170, "C"),
        motionEll3.getShapeAtTick(60));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeAtTickNegativeTickError() {
    this.initMotionData();
    this.motionRect1.getShapeAtTick(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeAtTickNotBetweenTickError() {
    this.initMotionData();
    this.motionRect1.getShapeAtTick(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeAtTickNotBetweenTickError2() {
    this.initMotionData();
    this.motionRect1.getShapeAtTick(800);
  }

  @Test
  public void testGetMotionSVGText() {
    this.initMotionData();
    assertEquals("", this.motionRect1.getMotionSVGText(10));
    assertEquals("<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\"" +
            " attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\"" +
            " attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />",
        this.motionRect2.getMotionSVGText(10));
    assertEquals(
        "<animate attributeType=\"xml\" begin=\"200.0ms\" dur=\"800.0ms\" attributeN"
            + "ame=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"200.0ms\" dur=\"800.0ms\" attributeN"
            + "ame=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />",
        this.motionRect2.getMotionSVGText(50));
  }

  @Test
  public void testContainsShape() {
    this.initMotionData();
    assertEquals(true, this.motionRect1.containsShape(this.rectInitTo10));
    assertEquals(true, this.motionRect1.containsShape(this.rect100));
    assertEquals(false, this.motionRect1.containsShape(this.ell70));
  }

  @Test
  public void testSameXInMotion() {
    this.initMotionData();
    assertEquals(true, this.motionRect1.sameXInMotion(this.rectInitTo10, this.rectInitTo10));
    assertEquals(true, this.motionRect5.sameXInMotion(this.rectInitTo10, this.rectInitTo10));
    assertEquals(false, this.motionRect2.sameXInMotion(this.rectInitTo10, this.rect51));

  }

  @Test
  public void testSameYInMotion() {
    this.initMotionData();
    assertEquals(true, this.motionRect1.sameYInMotion(this.rectInitTo10, this.rectInitTo10));
    assertEquals(true, this.motionRect5.sameYInMotion(this.rectInitTo10, this.rectInitTo10));
    assertEquals(false, this.motionRect2.sameYInMotion(this.rectInitTo10, this.rect51));

  }

  @Test
  public void testSameWidthInMotion() {
    this.initMotionData();
    assertEquals(true, this.motionRect1.sameWidthInMotion(this.rectInitTo10, this.rectInitTo10));
    assertEquals(true, this.motionRect5.sameWidthInMotion(this.rectInitTo10, this.rectInitTo10));
    assertEquals(false, this.motionRect4.sameWidthInMotion(this.rect51, this.rect70));
  }

  @Test
  public void testSameHeightInMotion() {
    this.initMotionData();
    assertEquals(true, this.motionRect1.sameHeightInMotion(this.rectInitTo10, this.rectInitTo10));
    assertEquals(true, this.motionRect5.sameHeightInMotion(this.rectInitTo10, this.rectInitTo10));
    assertEquals(false, this.motionRect4.sameHeightInMotion(this.ell80To100, this.ell101));
  }

  @Test
  public void testSameColorInMotion() {
    this.initMotionData();
    assertEquals(true, this.motionRect1.sameColorInMotion(this.rectInitTo10, this.rectInitTo10));
    assertEquals(true, this.motionRect5.sameColorInMotion(this.rectInitTo10, this.rectInitTo10));
    assertEquals(false, this.motionEll3.sameColorInMotion(this.ell50, this.ell70));
  }

  @Test
  public void testHasSVGMotion() {
    this.initMotionData();
    assertEquals(false, this.motionRect1.hasSVGMotion(this.rectInitTo10, this.rectInitTo10));
    assertEquals(false, this.motionRect5.hasSVGMotion(this.rectInitTo10, this.rectInitTo10));
    assertEquals(true, this.motionEll3.hasSVGMotion(this.ell50, this.ell70));
    assertEquals(false, this.motionRect3.hasSVGMotion(this.rect51, this.rect51));
  }

  @Test
  public void testMakeMotionHidden() {
    this.initMotionData();
    assertEquals("<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hid"
            + "den\" begin=\"1000.0ms\" dur=\"4000.0ms\" fill=\"remove\" />",
        this.motionRect2.makeMotionHidden(10));
    assertEquals("<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hi"
        + "dden\" begin=\"129.87012987012986ms\" dur=\"519.4805194805194ms\" fill=\"remove\" />",
            this.motionRect2.makeMotionHidden(77));

  }

  @Test
  public void testMakeMotionHiddenNoSVG() {
    this.initMotionData();
    assertEquals("<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\""
            + " begin=\"900.0ms\" dur=\"100.0ms\" fill=\"remove\" />",
        this.motionRect1.makeMotionHiddenNoSVG(10));
    assertEquals("<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidde"
            + "n\" begin=\"116.88311688311687ms\" dur=\"12.987012987012989ms\" fill=\"remove\" />",
        this.motionRect1.makeMotionHiddenNoSVG(77));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotPositiveTicksPerSecGetMotionSVGText() {
    this.initMotionData();
    this.motionEll3.getMotionSVGText(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotPositiveTicksPerSecGetMotionSVGText2() {
    this.initMotionData();
    this.motionEll3.getMotionSVGText(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotPositiveTicksPerSecMakeMotionHidden() {
    this.initMotionData();
    this.motionEll3.makeMotionHidden(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotPositiveTicksPerSecMakeMotionHidden2() {
    this.initMotionData();
    this.motionEll3.makeMotionHidden(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotPositiveTicksPerSecMakeMotionHiddenNoSVG() {
    this.initMotionData();
    this.motionEll3.makeMotionHidden(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotPositiveTicksPerSecMakeMotionHiddenNoSVG2() {
    this.initMotionData();
    this.motionEll3.makeMotionHidden(-1);
  }

}
