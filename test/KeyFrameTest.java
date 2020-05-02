import org.junit.Before;
import org.junit.Test;

import model.IModel;
import model.Model;
import model.keyframe.IKeyFrame;
import model.keyframe.KeyFrame;
import model.shape.MyEllipse;
import model.shape.MyRectangle;
import model.shape.ShapeType;

import static junit.framework.TestCase.assertEquals;

/**
 * Represents the test class for key frames.
 */
public class KeyFrameTest {

  IKeyFrame frame1;
  IKeyFrame frame2;

  IModel model;

  @Before
  public void initKeyFrameData() {
    this.frame1 = new KeyFrame("R", 1,0,0,1,1,0,0,0, ShapeType.ELLIPSE);
    this.frame2 = new KeyFrame("R", 50,50,50,50,50,50,50, 50, ShapeType.RECTANGLE);
    this.model = new Model();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testKFConstructorBadHeight() {
    this.frame1 = new KeyFrame("R", 1, 0, 0, 1, 0, 1, 1, 1, ShapeType.RECTANGLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testKFConstructorBadWidth() {
    this.frame1 = new KeyFrame("R", 1, 0, 0, 0, 1, 1, 1, 1, ShapeType.ELLIPSE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testKFConstructorBadWidthHeightNegative() {
    this.frame1 = new KeyFrame("R", 1, 0, 0, -1,-1, 1, 1, 1, ShapeType.RECTANGLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testKFConstructorBadColorNegative() {
    this.frame1 = new KeyFrame("R", 1, 0, 0, 1,1, -1, 1, 1, ShapeType.RECTANGLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testKFConstructorBadColorOver() {
    this.frame1 = new KeyFrame("R", 1, 0, 0, 1,1, 1, 256, 1,  ShapeType.RECTANGLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testKFConstructorBadColorThreeValues() {
    this.frame1 = new KeyFrame("R", 1, 0, 0, 1,1, -5, 256, 270, ShapeType.RECTANGLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testKFConstructorBadNameEmpty() {
    this.frame1 = new KeyFrame("", 1, 0, 0, 1,1, 1, 1, 1, ShapeType.RECTANGLE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testKFConstructorBadTickNegative() {
    this.frame1 = new KeyFrame("R", -1, 0, 0, 1,1, 1, 255, 1, ShapeType.RECTANGLE);
  }

  @Test
  public void testGetKeyFrameID() {
    this.initKeyFrameData();
    model.addKeyFrame(frame1, "F");

    assertEquals("F", frame1.getKeyFrameID());
  }

  @Test
  public void testSetKFID() {
    this.initKeyFrameData();
    frame1.setKeyFrameID("F");

    assertEquals("F", frame1.getKeyFrameID());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetKFIDEmptyString() {
    this.initKeyFrameData();
    frame1.setKeyFrameID("");
  }

  @Test
  public void testGetShapeName() {
    this.initKeyFrameData();
    assertEquals("R", this.frame1.getShapeName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetShapeNameEmptyString() {
    this.initKeyFrameData();
    this.frame1.setShapeName("");

  }

  @Test
  public void testSetShapeName() {
    this.initKeyFrameData();
    this.frame1.setShapeName("ShapeName");

    assertEquals("ShapeName", this.frame1.getShapeName());
  }

  @Test
  public void testGetTick() {
    this.initKeyFrameData();

    assertEquals(1, frame1.getTick());
    assertEquals(50, frame2.getTick());
  }

  @Test
  public void testSetTick() {
    this.initKeyFrameData();

    frame1.setTick(0);
    frame2.setTick(88);

    assertEquals(0, frame1.getTick());
    assertEquals(88, frame2.getTick());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetTickNegative() {
    this.initKeyFrameData();
    this.frame1.setTick(-1);
  }

  @Test
  public void testGetWidth() {
    this.initKeyFrameData();

    assertEquals(1, this.frame1.getWidth());
    assertEquals(50, this.frame2.getWidth());
  }

  @Test
  public void testGetHeight() {
    this.initKeyFrameData();

    assertEquals(1, this.frame1.getHeight());
    assertEquals(50, this.frame2.getHeight());
  }

  @Test
  public void testSetDimension() {
    this.initKeyFrameData();

    frame1.setDimension(1, 88);
    assertEquals(1, frame1.getWidth());
    assertEquals(88, frame1.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetDimensionForBadDimensions() {
    this.initKeyFrameData();

    frame1.setDimension(-1, 0);
  }

  @Test
  public void testGetX() {
    this.initKeyFrameData();

    assertEquals(0, frame1.getX());
    assertEquals(50, frame2.getX());
  }

  @Test
  public void testGetY() {
    this.initKeyFrameData();

    assertEquals(0, frame1.getY());
    assertEquals(50, frame2.getY());
  }

  @Test
  public void testSetPosition() {
    this.initKeyFrameData();

    frame1.setPosition(-11, 90000);

    assertEquals(-11, frame1.getX());
    assertEquals(90000, frame1.getY());
  }

  @Test
  public void testGetRed() {
    this.initKeyFrameData();

    assertEquals(0, frame1.getRed());
    assertEquals(50, frame2.getRed());
  }

  @Test
  public void testGetGreen() {
    this.initKeyFrameData();

    assertEquals(0, frame1.getGreen());
    assertEquals(50, frame2.getGreen());
  }

  @Test
  public void testGetBlue() {
    this.initKeyFrameData();

    assertEquals(0, frame1.getBlue());
    assertEquals(50, frame2.getBlue());
  }

  @Test
  public void testSetColor() {
    this.initKeyFrameData();
    frame1.setColor(0, 67, 255);

    assertEquals(0, frame1.getRed());
    assertEquals(67, frame1.getGreen());
    assertEquals(255, frame1.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetColorBadColorVals() {
    this.initKeyFrameData();

    frame1.setColor(-1, 256, 260);
  }

  @Test
  public void testGetShapeType() {
    this.initKeyFrameData();

    assertEquals(ShapeType.ELLIPSE, frame1.getShapeType());
    assertEquals(ShapeType.RECTANGLE, frame2.getShapeType());
  }

  @Test
  public void testGetShapeForKF() {
    this.initKeyFrameData();

    assertEquals(new MyEllipse(1, 1,0,0,0,0,0,"R"), frame1.getShape());
    assertEquals(new MyRectangle(50, 50,50,50,50,50,50,"R"), frame2.getShape());
  }


}
