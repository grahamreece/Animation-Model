import org.junit.Before;
import org.junit.Test;
import java.awt.geom.Ellipse2D;

import model.Model;
import model.motion.IMotion;
import model.motion.Motion;
import model.shape.IShape;
import model.shape.MyEllipse;
import model.shape.ShapeType;

import static org.junit.Assert.assertEquals;

/**
 * Represents a test class for the Ellipse class that was created.
 */
public class MyEllipseTest {

  IShape ell6To20;
  IShape ell50;
  IShape ell70;
  IShape ell80;

  IMotion motionOne;

  Model model;

  @Before
  public void initEllipseData() {
    this.ell6To20 = new MyEllipse(120, 60, 440, 70, 0, 0, 255, "C");
    this.ell50 = new MyEllipse(120, 60, 440, 250, 0, 0, 255, "C");
    this.ell70 = new MyEllipse(120, 60, 440, 370, 0, 170, 85, "C");
    this.ell80 = new MyEllipse(120, 60, 440, 370, 0, 255, 0, "C");
    this.motionOne = new Motion(1, 2, this.ell6To20, this.ell50);
    this.model = new Model();
  }


  @Test(expected = IllegalArgumentException.class)
  public void testWidthConstructorErrors() {
    this.initEllipseData();
    this.ell6To20 = new MyEllipse(-50, 60, 440, 70, 0, 0, 255, "C");
    this.ell6To20 = new MyEllipse(0, 60, 440, 70, 0, 0, 255, "C");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testHeightConstructorErrors() {
    this.initEllipseData();
    this.ell6To20 = new MyEllipse(60, -60, 440, 70, 0, 0, 255, "C");
    this.ell6To20 = new MyEllipse(60, 0, 440, 70, 0, 0, 255, "C");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWidthAndHeightConstructorErrors() {
    this.initEllipseData();
    this.ell6To20 = new MyEllipse(0, -60, 440, 70, 0, 0, 255, "C");
    this.ell6To20 = new MyEllipse(-60, 0, 440, 70, 0, 0, 255, "C");
    this.ell6To20 = new MyEllipse(0, 0, 440, 70, 0, 0, 255, "C");
    this.ell6To20 = new MyEllipse(-60, -70, 440, 70, 0, 0, 255, "C");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorConstructorErrorsNegative() {
    this.initEllipseData();
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, -1, 0, 255, "C");
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, 1, -1, 255, "C");
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, 0, 0, -1, "C");
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, 0, -1, -1, "C");
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, -2, 10, -1, "C");
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, -2, -2, 10, "C");
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, -2, -10, -1, "C");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorConstructorErrorsExceed() {
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, 256, 0, 255, "C");
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, 0, 256, 255, "C");
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, 0, 0, 256, "C");
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, 0, 256, 260, "C");
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, 270, 50, 300, "C");
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, 700,  300, 10, "C");
    this.ell6To20 = new MyEllipse(60, 60, 500, 70, 256, 256, 256, "C");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyShapeNameConstructorError() {
    this.ell6To20 = new MyEllipse(70, 50, 500, 70, 0, 170, 85, "");
    this.ell80 = new MyEllipse(80, 90, 460, 80, 255, 255, 0, "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetDimensionError() {
    this.ell6To20.setDimension(0, 210);
    this.ell6To20.setDimension(30, 0);
    this.ell6To20.setDimension(0, 0);
    this.ell6To20.setDimension(-5, 210);
    this.ell6To20.setDimension(40, -1);
    this.ell6To20.setDimension(-50, -40);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetColorError() {
    this.ell6To20.setColor(-1, 0, 255);
    this.ell6To20.setColor(0, -5, 255);
    this.ell6To20.setColor(5, 0, -10);
    this.ell6To20.setColor(-1, -1, 255);
    this.ell6To20.setColor(0, -5, -1);
    this.ell6To20.setColor(-1, 0, -1);
    this.ell6To20.setColor(-1, -1, -5);
    this.ell6To20.setColor(10, 0, 256);
    this.ell6To20.setColor(0, 256, 255);
    this.ell6To20.setColor(295, 0, 0);
    this.ell6To20.setColor(256, 256, 255);
    this.ell6To20.setColor(0, 256, 256);
    this.ell6To20.setColor(256, 0, 260);
    this.ell6To20.setColor(260, 260,255);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetShapeNameError() {
    this.ell6To20.setShapeName("");
    this.ell50.setShapeName("");
    this.ell70.setShapeName("");
    this.ell80.setShapeName("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetShapeIdentifierError() {
    this.ell6To20.setShapeIdentifier("");
    this.ell50.setShapeIdentifier("");
    this.ell70.setShapeIdentifier("");
    this.ell80.setShapeIdentifier("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetRectangle() {
    this.ell6To20.getRectangle();
    this.ell50.getRectangle();
    this.ell70.getRectangle();
    this.ell80.getRectangle();

  }

  @Test
  public void testGetShapeType() {
    this.initEllipseData();

    assertEquals(ShapeType.ELLIPSE, this.ell6To20.getShapeType());
    assertEquals(ShapeType.ELLIPSE, this.ell50.getShapeType());
    assertEquals(ShapeType.ELLIPSE, this.ell70.getShapeType());
    assertEquals(ShapeType.ELLIPSE, this.ell80.getShapeType());

  }

  @Test
  public void testGetWidth() {

    this.initEllipseData();

    assertEquals(120, this.ell6To20.getWidth());
    assertEquals(120, this.ell50.getWidth());
    assertEquals(120, this.ell70.getWidth());
    assertEquals(120, this.ell80.getWidth());

  }

  @Test
  public void testGetHeight() {

    this.initEllipseData();

    assertEquals(60, this.ell6To20.getHeight());
    assertEquals(60, this.ell50.getHeight());
    assertEquals(60, this.ell70.getHeight());
    assertEquals(60, this.ell80.getHeight());

  }

  @Test
  public void testGetX() {

    this.initEllipseData();

    assertEquals(440, this.ell6To20.getX());
    assertEquals(440, this.ell50.getX());
    assertEquals(440, this.ell70.getX());
    assertEquals(440, this.ell80.getX());

  }

  @Test
  public void testGetY() {
    this.initEllipseData();

    assertEquals(70, this.ell6To20.getY());
    assertEquals(250, this.ell50.getY());
    assertEquals(370, this.ell70.getY());
    assertEquals(370, this.ell80.getY());

  }

  @Test
  public void testGetRed() {
    this.initEllipseData();

    assertEquals(0, this.ell6To20.getRed());
    assertEquals(0, this.ell50.getRed());
    assertEquals(0, this.ell70.getRed());
    assertEquals(0, this.ell80.getRed());

  }

  @Test
  public void testGetGreen() {
    this.initEllipseData();

    assertEquals(0, this.ell6To20.getGreen());
    assertEquals(0, this.ell50.getGreen());
    assertEquals(170, this.ell70.getGreen());
    assertEquals(255, this.ell80.getGreen());

  }

  @Test
  public void testGetBlue() {
    this.initEllipseData();

    assertEquals(255, this.ell6To20.getBlue());
    assertEquals(255, this.ell50.getBlue());
    assertEquals(85, this.ell70.getBlue());
    assertEquals(0, this.ell80.getBlue());

  }

  @Test
  public void testGetShapeName() {
    this.initEllipseData();

    assertEquals("C", this.ell6To20.getShapeName());
    assertEquals("C", this.ell50.getShapeName());
    assertEquals("C", this.ell70.getShapeName());
    assertEquals("C", this.ell80.getShapeName());

  }

  @Test
  public void testGetShapeIdentifier() {
    this.initEllipseData();

    assertEquals(null, this.ell6To20.getShapeIdentifier());
    assertEquals(null, this.ell50.getShapeIdentifier());
    assertEquals(null, this.ell70.getShapeIdentifier());
    assertEquals(null, this.ell80.getShapeIdentifier());

    this.model.addShape(this.ell6To20, "C1");

    assertEquals("C1", this.ell6To20.getShapeIdentifier());

  }

  @Test
  public void testGetEllipse() {
    this.initEllipseData();

    assertEquals(new Ellipse2D.Double(440, 70, 120, 60), this.ell6To20.getEllipse());
    assertEquals(new Ellipse2D.Double(440, 250, 120, 60), this.ell50.getEllipse());
    assertEquals(new Ellipse2D.Double(440, 370, 120, 60), this.ell70.getEllipse());
    assertEquals(new Ellipse2D.Double(440, 370, 120, 60), this.ell80.getEllipse());

  }

  @Test
  public void testSetDimension() {
    this.initEllipseData();

    this.ell6To20.setDimension(1, 1);
    assertEquals(1, this.ell6To20.getWidth());
    assertEquals(1, this.ell6To20.getHeight());

    this.ell6To20.setDimension(50, 60);
    assertEquals(50, this.ell6To20.getWidth());
    assertEquals(60, this.ell6To20.getHeight());

    this.ell50.setDimension(1300, 2500);
    assertEquals(1300, this.ell50.getWidth());
    assertEquals(2500, this.ell50.getHeight());
  }

  @Test
  public void testSetPosition() {
    this.initEllipseData();

    this.ell6To20.setPosition(50, 60);
    assertEquals(50, this.ell6To20.getX());
    assertEquals(60, this.ell6To20.getY());

    this.ell50.setPosition(1300, 2500);
    assertEquals(1300, this.ell50.getX());
    assertEquals(2500, this.ell50.getY());

    this.ell70.setPosition(0, -70);
    assertEquals(0, this.ell70.getX());
    assertEquals(-70, this.ell70.getY());
  }

  @Test
  public void testSetColor() {
    this.initEllipseData();

    this.ell6To20.setColor(0, 0, 0);
    assertEquals(0, this.ell6To20.getRed());
    assertEquals(0, this.ell6To20.getBlue());
    assertEquals(0, this.ell6To20.getGreen());

    this.ell50.setColor(255, 255, 255);
    assertEquals(255, this.ell50.getRed());
    assertEquals(255, this.ell50.getBlue());
    assertEquals(255, this.ell50.getGreen());

    this.ell70.setColor(250, 125, 92);
    assertEquals(250, this.ell70.getRed());
    assertEquals(125, this.ell70.getGreen());
    assertEquals(92, this.ell70.getBlue());
  }

  @Test
  public void testSetShapeName() {
    this.initEllipseData();

    this.ell6To20.setShapeName("lolxD");
    assertEquals("lolxD", this.ell6To20.getShapeName());

    this.ell50.setShapeName("Circle 1");
    assertEquals("Circle 1", this.ell50.getShapeName());
  }

  @Test
  public void testSetShapeIdentifier() {
    this.initEllipseData();

    this.ell6To20.setShapeIdentifier("lolxD");
    assertEquals("lolxD", this.ell6To20.getShapeIdentifier());

    this.ell50.setShapeIdentifier("Circle 1");
    assertEquals("Circle 1", this.ell50.getShapeIdentifier());

    this.ell70.setShapeIdentifier("C3");
    assertEquals("C3", this.ell70.getShapeIdentifier());
  }

  @Test
  public void testGetShapeText() {
    this.initEllipseData();
    assertEquals("shape C ELLIPSE\n", this.ell6To20.getShapeText());
    assertEquals("shape C ELLIPSE\n", this.ell50.getShapeText());
    assertEquals("shape C ELLIPSE\n", this.ell70.getShapeText());
    assertEquals("shape C ELLIPSE\n", this.ell80.getShapeText());
  }

  @Test
  public void testGetElements() {
    this.initEllipseData();
    assertEquals("440 70 120 60 0 0 255", this.ell6To20.getElements());
    assertEquals("440 250 120 60 0 0 255", this.ell50.getElements());
    assertEquals("440 370 120 60 0 170 85", this.ell70.getElements());
    assertEquals("440 370 120 60 0 255 0", this.ell80.getElements());

  }

  @Test
  public void testEqualsForEllipse() {
    this.initEllipseData();
    assertEquals(true, this.ell6To20.equals(this.ell6To20));

    this.ell6To20.setShapeIdentifier("C1");

    assertEquals(true, this.ell6To20.equals(this.ell6To20));

    this.initEllipseData();
    assertEquals(false, this.ell6To20.equals(this.ell50));
    assertEquals(false, this.ell6To20.equals(5));

    this.ell50.setPosition(440, 70);
    assertEquals(true, this.ell6To20.equals(this.ell50));

  }

  @Test
  public void testGetShapeSVGText() {
    this.initEllipseData();

    assertEquals("<ellipse id=\"C\" cx=\"440.0\" cy=\"70.0\" rx=\"60.0\" ry=\"30.0\"" +
            " fill=\"rgb(0,0,255)\" visibility=\"visible\" >", this.ell6To20.getShapeSVGText());
    assertEquals("<ellipse id=\"C\" cx=\"440.0\" cy=\"250.0\" rx=\"60.0\" ry=\"30.0\"" +
            " fill=\"rgb(0,0,255)\" visibility=\"visible\" >", this.ell50.getShapeSVGText());
    assertEquals("<ellipse id=\"C\" cx=\"440.0\" cy=\"370.0\" rx=\"60.0\" ry=\"30.0\"" +
            " fill=\"rgb(0,170,85)\" visibility=\"visible\" >", this.ell70.getShapeSVGText());
    assertEquals("<ellipse id=\"C\" cx=\"440.0\" cy=\"370.0\" rx=\"60.0\" ry=\"30.0\"" +
            " fill=\"rgb(0,255,0)\" visibility=\"visible\" >", this.ell80.getShapeSVGText());
  }

  @Test
  public void testGetShapeClosingSVGText() {
    this.initEllipseData();

    assertEquals("</ellipse>", this.ell6To20.getShapeClosingSVG());
    assertEquals("</ellipse>", this.ell50.getShapeClosingSVG());
    assertEquals("</ellipse>", this.ell70.getShapeClosingSVG());
    assertEquals("</ellipse>", this.ell80.getShapeClosingSVG());
  }

  @Test
  public void testGetXMLAnimation() {
    this.initEllipseData();

    assertEquals("<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"100.0ms\"" +
                    " attributeName=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n",
            this.ell6To20.getXMLAnimation(this.ell6To20, this.ell50, this.motionOne, 10));
    assertEquals("<animate attributeType=\"xml\" begin=\"12.987012987012989ms\"" +
                    " dur=\"12.987012987012989ms\" attributeName=\"cy\" from=\"70\" to=\"250\"" +
                    " fill=\"freeze\" />\n",
            this.ell6To20.getXMLAnimation(this.ell6To20, this.ell50, this.motionOne, 77));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetXMLAnimationNotPos1() {
    this.initEllipseData();

    this.ell6To20.getXMLAnimation(this.ell6To20, this.ell50, this.motionOne, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetXMLAnimationNotPos2() {
    this.initEllipseData();

    this.ell6To20.getXMLAnimation(this.ell6To20, this.ell50, this.motionOne, -1);
  }

}
