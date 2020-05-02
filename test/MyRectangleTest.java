import org.junit.Before;
import org.junit.Test;
import java.awt.geom.Rectangle2D;

import model.Model;
import model.motion.IMotion;
import model.motion.Motion;
import model.shape.IShape;
import model.shape.MyRectangle;
import model.shape.ShapeType;

import static org.junit.Assert.assertEquals;

/**
 * Represents a test class for the Rectangle class that was created.
 */
public class MyRectangleTest {

  IShape rectOne;
  IShape rectTwo;
  IShape rectThree;
  IShape rectFour;

  IMotion motionOne;

  Model model;

  @Before
  public void initRectangleData() {
    this.rectOne = new MyRectangle(120, 60, 440, 70, 0, 0, 255, "R");
    this.rectTwo = new MyRectangle(120, 60, 440, 250, 0, 0, 255, "R");
    this.rectThree = new MyRectangle(120, 60, 440, 370, 0, 170, 85, "R");
    this.rectFour = new MyRectangle(120, 60, 440, 370, 0, 255, 0, "R");
    this.motionOne = new Motion(1, 2, this.rectOne, this.rectTwo);
    this.model = new Model();
  }


  @Test(expected = IllegalArgumentException.class)
  public void testWidthConstructorErrors() {
    this.initRectangleData();
    this.rectOne = new MyRectangle(-50, 60, 440, 70, 0, 0, 255, "R");
    this.rectOne = new MyRectangle(0, 60, 440, 70, 0, 0, 255, "R");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testHeightConstructorErrors() {
    this.initRectangleData();
    this.rectOne = new MyRectangle(60, -60, 440, 70, 0, 0, 255, "R");
    this.rectOne = new MyRectangle(60, 0, 440, 70, 0, 0, 255, "R");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWidthAndHeightConstructorErrors() {
    this.initRectangleData();
    this.rectOne = new MyRectangle(0, -60, 440, 70, 0, 0, 255, "R");
    this.rectOne = new MyRectangle(-60, 0, 440, 70, 0, 0, 255, "R");
    this.rectOne = new MyRectangle(0, 0, 440, 70, 0, 0, 255, "R");
    this.rectOne = new MyRectangle(-60, -70, 440, 70, 0, 0, 255, "R");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorConstructorErrorsNegative() {
    this.initRectangleData();
    this.rectOne = new MyRectangle(60, 60, 500, 70, -1, 0, 255, "R");
    this.rectOne = new MyRectangle(60, 60, 500, 70, 1, -1, 255, "R");
    this.rectOne = new MyRectangle(60, 60, 500, 70, 0, 0, -1, "R");
    this.rectOne = new MyRectangle(60, 60, 500, 70, 0, -1, -1, "R");
    this.rectOne = new MyRectangle(60, 60, 500, 70, -2, 10, -1, "R");
    this.rectOne = new MyRectangle(60, 60, 500, 70, -2, -2, 10, "R");
    this.rectOne = new MyRectangle(60, 60, 500, 70, -2, -10, -1, "R");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorConstructorErrorsExceed() {
    this.rectOne = new MyRectangle(60, 60, 500, 70, 256, 0, 255, "R");
    this.rectOne = new MyRectangle(60, 60, 500, 70, 0, 256, 255, "R");
    this.rectOne = new MyRectangle(60, 60, 500, 70, 0, 0, 256, "R");
    this.rectOne = new MyRectangle(60, 60, 500, 70, 0, 256, 260, "R");
    this.rectOne = new MyRectangle(60, 60, 500, 70, 270, 50, 300, "R");
    this.rectOne = new MyRectangle(60, 60, 500, 70, 700,  300, 10, "R");
    this.rectOne = new MyRectangle(60, 60, 500, 70, 256, 256, 256, "R");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyShapeNameConstructorError() {
    this.rectOne = new MyRectangle(70, 50, 500, 70, 0, 170, 85, "");
    this.rectFour = new MyRectangle(80, 90, 460, 80, 255, 255, 0, "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetDimensionError() {
    this.rectOne.setDimension(0, 210);
    this.rectOne.setDimension(30, 0);
    this.rectOne.setDimension(0, 0);
    this.rectOne.setDimension(-5, 210);
    this.rectOne.setDimension(40, -1);
    this.rectOne.setDimension(-50, -40);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetColorError() {
    this.rectOne.setColor(-1, 0, 255);
    this.rectOne.setColor(0, -5, 255);
    this.rectOne.setColor(5, 0, -10);
    this.rectOne.setColor(-1, -1, 255);
    this.rectOne.setColor(0, -5, -1);
    this.rectOne.setColor(-1, 0, -1);
    this.rectOne.setColor(-1, -1, -5);
    this.rectOne.setColor(10, 0, 256);
    this.rectOne.setColor(0, 256, 255);
    this.rectOne.setColor(295, 0, 0);
    this.rectOne.setColor(256, 256, 255);
    this.rectOne.setColor(0, 256, 256);
    this.rectOne.setColor(256, 0, 260);
    this.rectOne.setColor(260, 260,255);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetShapeNameError() {
    this.rectOne.setShapeName("");
    this.rectTwo.setShapeName("");
    this.rectThree.setShapeName("");
    this.rectFour.setShapeName("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetShapeIdentifierError() {
    this.rectOne.setShapeIdentifier("");
    this.rectTwo.setShapeIdentifier("");
    this.rectThree.setShapeIdentifier("");
    this.rectFour.setShapeIdentifier("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetEllipse() {
    this.rectOne.getEllipse();
    this.rectTwo.getEllipse();
    this.rectThree.getEllipse();
    this.rectFour.getEllipse();

  }

  @Test
  public void testGetShapeType() {
    this.initRectangleData();

    assertEquals(ShapeType.RECTANGLE, this.rectOne.getShapeType());
    assertEquals(ShapeType.RECTANGLE, this.rectTwo.getShapeType());
    assertEquals(ShapeType.RECTANGLE, this.rectThree.getShapeType());
    assertEquals(ShapeType.RECTANGLE, this.rectFour.getShapeType());

  }

  @Test
  public void testGetWidth() {

    this.initRectangleData();

    assertEquals(120, this.rectOne.getWidth());
    assertEquals(120, this.rectTwo.getWidth());
    assertEquals(120, this.rectThree.getWidth());
    assertEquals(120, this.rectFour.getWidth());

  }

  @Test
  public void testGetHeight() {

    this.initRectangleData();

    assertEquals(60, this.rectOne.getHeight());
    assertEquals(60, this.rectTwo.getHeight());
    assertEquals(60, this.rectThree.getHeight());
    assertEquals(60, this.rectFour.getHeight());

  }

  @Test
  public void testGetX() {

    this.initRectangleData();

    assertEquals(440, this.rectOne.getX());
    assertEquals(440, this.rectTwo.getX());
    assertEquals(440, this.rectThree.getX());
    assertEquals(440, this.rectFour.getX());

  }

  @Test
  public void testGetY() {
    this.initRectangleData();

    assertEquals(70, this.rectOne.getY());
    assertEquals(250, this.rectTwo.getY());
    assertEquals(370, this.rectThree.getY());
    assertEquals(370, this.rectFour.getY());

  }

  @Test
  public void testGetRed() {
    this.initRectangleData();

    assertEquals(0, this.rectOne.getRed());
    assertEquals(0, this.rectTwo.getRed());
    assertEquals(0, this.rectThree.getRed());
    assertEquals(0, this.rectFour.getRed());

  }

  @Test
  public void testGetGreen() {
    this.initRectangleData();

    assertEquals(0, this.rectOne.getGreen());
    assertEquals(0, this.rectTwo.getGreen());
    assertEquals(170, this.rectThree.getGreen());
    assertEquals(255, this.rectFour.getGreen());

  }

  @Test
  public void testGetBlue() {
    this.initRectangleData();

    assertEquals(255, this.rectOne.getBlue());
    assertEquals(255, this.rectTwo.getBlue());
    assertEquals(85, this.rectThree.getBlue());
    assertEquals(0, this.rectFour.getBlue());

  }

  @Test
  public void testGetShapeName() {
    this.initRectangleData();

    assertEquals("R", this.rectOne.getShapeName());
    assertEquals("R", this.rectTwo.getShapeName());
    assertEquals("R", this.rectThree.getShapeName());
    assertEquals("R", this.rectFour.getShapeName());

  }

  @Test
  public void testGetShapeIdentifier() {
    this.initRectangleData();

    assertEquals(null, this.rectOne.getShapeIdentifier());
    assertEquals(null, this.rectTwo.getShapeIdentifier());
    assertEquals(null, this.rectThree.getShapeIdentifier());
    assertEquals(null, this.rectFour.getShapeIdentifier());

    this.model.addShape(this.rectOne, "R1");

    assertEquals("R1", this.rectOne.getShapeIdentifier());

  }

  @Test
  public void testGetRectangle() {
    this.initRectangleData();

    assertEquals(new Rectangle2D.Double(440, 70, 120, 60), this.rectOne.getRectangle());
    assertEquals(new Rectangle2D.Double(440, 250, 120, 60), this.rectTwo.getRectangle());
    assertEquals(new Rectangle2D.Double(440, 370, 120, 60), this.rectThree.getRectangle());
    assertEquals(new Rectangle2D.Double(440, 370, 120, 60), this.rectFour.getRectangle());

  }

  @Test
  public void testSetDimension() {
    this.initRectangleData();

    this.rectOne.setDimension(1, 1);
    assertEquals(1, this.rectOne.getWidth());
    assertEquals(1, this.rectOne.getHeight());

    this.rectOne.setDimension(50, 60);
    assertEquals(50, this.rectOne.getWidth());
    assertEquals(60, this.rectOne.getHeight());

    this.rectTwo.setDimension(1300, 2500);
    assertEquals(1300, this.rectTwo.getWidth());
    assertEquals(2500, this.rectTwo.getHeight());
  }

  @Test
  public void testSetPosition() {
    this.initRectangleData();

    this.rectOne.setPosition(50, 60);
    assertEquals(50, this.rectOne.getX());
    assertEquals(60, this.rectOne.getY());

    this.rectTwo.setPosition(1300, 2500);
    assertEquals(1300, this.rectTwo.getX());
    assertEquals(2500, this.rectTwo.getY());

    this.rectThree.setPosition(0, -70);
    assertEquals(0, this.rectThree.getX());
    assertEquals(-70, this.rectThree.getY());
  }

  @Test
  public void testSetColor() {
    this.initRectangleData();

    this.rectOne.setColor(0, 0, 0);
    assertEquals(0, this.rectOne.getRed());
    assertEquals(0, this.rectOne.getBlue());
    assertEquals(0, this.rectOne.getGreen());

    this.rectTwo.setColor(255, 255, 255);
    assertEquals(255, this.rectTwo.getRed());
    assertEquals(255, this.rectTwo.getBlue());
    assertEquals(255, this.rectTwo.getGreen());

    this.rectThree.setColor(250, 125, 92);
    assertEquals(250, this.rectThree.getRed());
    assertEquals(125, this.rectThree.getGreen());
    assertEquals(92, this.rectThree.getBlue());
  }

  @Test
  public void testSetShapeName() {
    this.initRectangleData();

    this.rectOne.setShapeName("lolxD");
    assertEquals("lolxD", this.rectOne.getShapeName());

    this.rectTwo.setShapeName("Circle 1");
    assertEquals("Circle 1", this.rectTwo.getShapeName());
  }

  @Test
  public void testSetShapeIdentifier() {
    this.initRectangleData();

    this.rectOne.setShapeIdentifier("lolxD");
    assertEquals("lolxD", this.rectOne.getShapeIdentifier());

    this.rectTwo.setShapeIdentifier("Rectangle 1");
    assertEquals("Rectangle 1", this.rectTwo.getShapeIdentifier());

    this.rectThree.setShapeIdentifier("R3");
    assertEquals("R3", this.rectThree.getShapeIdentifier());
  }

  @Test
  public void testGetShapeText() {
    this.initRectangleData();
    assertEquals("shape R RECTANGLE\n", this.rectOne.getShapeText());
    assertEquals("shape R RECTANGLE\n", this.rectTwo.getShapeText());
    assertEquals("shape R RECTANGLE\n", this.rectThree.getShapeText());
    assertEquals("shape R RECTANGLE\n", this.rectFour.getShapeText());
  }

  @Test
  public void testGetElements() {
    this.initRectangleData();
    assertEquals("440 70 120 60 0 0 255", this.rectOne.getElements());
    assertEquals("440 250 120 60 0 0 255", this.rectTwo.getElements());
    assertEquals("440 370 120 60 0 170 85", this.rectThree.getElements());
    assertEquals("440 370 120 60 0 255 0", this.rectFour.getElements());

  }

  @Test
  public void testEqualsForRectangle() {
    this.initRectangleData();
    assertEquals(true, this.rectOne.equals(this.rectOne));

    this.rectOne.setShapeIdentifier("R1");

    assertEquals(true, this.rectOne.equals(this.rectOne));

    this.initRectangleData();

    assertEquals(false, this.rectOne.equals(this.rectTwo));
    assertEquals(false, this.rectOne.equals(5));

    this.rectTwo.setPosition(440, 70);
    assertEquals(true, this.rectOne.equals(this.rectTwo));

  }

  @Test
  public void testGetShapeSVGText() {
    this.initRectangleData();

    assertEquals("<rect id=\"R\" x=\"440.0\" y=\"70.0\" width=\"120.0\" height=\"60.0\"" +
            " fill=\"rgb(0,0,255)\" visibility=\"visible\" >", this.rectOne.getShapeSVGText());
    assertEquals("<rect id=\"R\" x=\"440.0\" y=\"250.0\" width=\"120.0\" height=\"60.0\"" +
            " fill=\"rgb(0,0,255)\" visibility=\"visible\" >", this.rectTwo.getShapeSVGText());
    assertEquals("<rect id=\"R\" x=\"440.0\" y=\"370.0\" width=\"120.0\" height=\"60.0\"" +
            " fill=\"rgb(0,170,85)\" visibility=\"visible\" >", this.rectThree.getShapeSVGText());
    assertEquals("<rect id=\"R\" x=\"440.0\" y=\"370.0\" width=\"120.0\" height=\"60.0\"" +
            " fill=\"rgb(0,255,0)\" visibility=\"visible\" >", this.rectFour.getShapeSVGText());
  }

  @Test
  public void testGetShapeClosingSVGText() {
    this.initRectangleData();

    assertEquals("</rect>", this.rectOne.getShapeClosingSVG());
    assertEquals("</rect>", this.rectTwo.getShapeClosingSVG());
    assertEquals("</rect>", this.rectThree.getShapeClosingSVG());
    assertEquals("</rect>", this.rectFour.getShapeClosingSVG());
  }

  @Test
  public void testGetXMLAnimation() {
    this.initRectangleData();

    assertEquals("<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"100.0ms\""
            + " attributeName=\"y\" from=\"70\" to=\"250\" fill=\"freeze\" />\n",
            this.rectOne.getXMLAnimation(this.rectOne, this.rectTwo, this.motionOne, 10));
    assertEquals("<animate attributeType=\"xml\" begin=\"12.987012987012989ms\" dur=\""
            + "12.987012987012989ms\" attributeName=\"y\" from=\"70\" to=\"250\" fill=\""
            + "freeze\" />\n",
            this.rectOne.getXMLAnimation(this.rectOne, this.rectTwo, this.motionOne, 77));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetXMLAnimationNotPos1() {
    this.initRectangleData();

    this.rectOne.getXMLAnimation(this.rectOne, this.rectTwo, this.motionOne, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetXMLAnimationNotPos2() {
    this.initRectangleData();

    this.rectOne.getXMLAnimation(this.rectOne, this.rectTwo, this.motionOne, -1);
  }

}