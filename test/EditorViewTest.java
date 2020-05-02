import org.junit.Before;
import org.junit.Test;

import cs3500.animator.view.IView;
import cs3500.animator.view.EditorView;

import static junit.framework.TestCase.assertEquals;

/**
 * Represents the class for testing the Editor View class that was made.
 */
public class EditorViewTest {

  IView view;

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorBad() {
    view = new EditorView("resources/givenCodeForTest/smalldemo.txt", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorBad2() {
    view = new EditorView("resources/givenCodeForTest/smalldemo.txt", -32);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetTextDescription() {
    view = new EditorView("resources/givenCodeForTest/smalldemo.txt", 1);
    view.getTextDescription();
  }

  @Test
  public void testGetLoopState() {
    view = new EditorView("resources/givenCodeForTest/smalldemo.txt", 1);
    assertEquals(false, view.getLoopState());
  }

  @Test
  public void testChangeLoopState() {
    view = new EditorView("resources/givenCodeForTest/smalldemo.txt", 1);
    assertEquals(false, view.getLoopState());
    view.changeLoopState();
    assertEquals(true, view.getLoopState());
  }

  @Before
  public void initialize() {
    view = new EditorView("resources/givenCodeForTest/smalldemo.txt", 15);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetModTextT() {
    initialize();
    view.getModTextT();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetModTextX() {
    initialize();
    view.getModTextX();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetModTextY() {
    initialize();
    view.getModTextY();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetModTextW() {
    initialize();
    view.getModTextW();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetModTextH() {
    initialize();
    view.getModTextH();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetModTextR() {
    initialize();
    view.getModTextR();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetModTextG() {
    initialize();
    view.getModTextG();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetModTextB() {
    initialize();
    view.getModTextB();
  }

  @Test(expected = IllegalStateException.class)
  public void testXTextKey() {
    initialize();
    view.getXTextKey();
  }

  @Test(expected = IllegalStateException.class)
  public void testYTextKey() {
    initialize();
    view.getYTextKey();
  }

  @Test(expected = IllegalStateException.class)
  public void testWTextKey() {
    initialize();
    view.getWTextKey();
  }

  @Test(expected = IllegalStateException.class)
  public void testHTextKey() {
    initialize();
    view.getHTextKey();
  }

  @Test(expected = IllegalStateException.class)
  public void testRTextKey() {
    initialize();
    view.getRTextKey();
  }

  @Test(expected = IllegalStateException.class)
  public void testGTextKey() {
    initialize();
    view.getGTextKey();
  }

  @Test(expected = IllegalStateException.class)
  public void testBTextKey() {
    initialize();
    view.getBTextKey();
  }

  @Test
  public void testNameTextKey() {
    initialize();
    assertEquals("", view.getNameTextKey());
  }

  @Test
  public void testGetNameText() {
    initialize();
    assertEquals("", view.getNameText());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetXTest() {
    initialize();
    view.getXText();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetYTest() {
    initialize();
    view.getYText();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetWTest() {
    initialize();
    view.getWText();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetHTest() {
    initialize();
    view.getHText();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetRTest() {
    initialize();
    view.getRText();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetGTest() {
    initialize();
    view.getGText();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetBTest() {
    initialize();
    view.getBText();
  }

  @Test
  public void getRectButtonTest() {
    initialize();
    assertEquals("Rectangle", view.getRectButton().getActionCommand());
  }

  @Test
  public void getEllipseButtonTest() {
    initialize();
    assertEquals("Ellipse", view.getEllipseButton().getActionCommand());
  }
}
