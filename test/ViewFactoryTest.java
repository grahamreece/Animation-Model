import static org.junit.Assert.assertEquals;

import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.ViewFactory;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Test;

/**
 * Represents a test class for the Factory class for creating views.
 */
public class ViewFactoryTest {

  @Test
  public void testViewFactoryText() {
    String inputStr = "resources/givenCodeForTest/smalldemo.txt";
    IView view = new TextualView(inputStr);

    IView otherView = ViewFactory.createView("text", null, 12, inputStr);

    assertEquals(view.getTextDescription(), otherView.getTextDescription());
  }

  @Test
  public void testViewFactoryText2() {
    String inputStr = "resources/givenCodeForTest/smalldemo.txt";
    IView view = new TextualView(inputStr);

    try {
      IView otherView = ViewFactory.createView("text", new FileWriter("test.txt"), 0, inputStr);

      assertEquals(view.getTextDescription(), otherView.getTextDescription());
    } catch (IOException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test returns a popup error message
  @Test (expected = IllegalStateException.class)
  public void testViewFactoryText3() {
    String inputStr = "rhi";
    try {
      try {
        IView view = new TextualView(inputStr);
        IView otherView = ViewFactory.createView("text", new FileWriter("test.txt"), 0, inputStr);
        assertEquals(view.getTextDescription(), otherView.getTextDescription());
      } catch (IOException e) {
        throw new IllegalStateException("This test fails!");
      }
    } catch (NullPointerException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test creates a new file in the project folder
  @Test
  public void testViewFactoryText4() {
    String inputStr = "resources/givenCodeForTest/smalldemo.txt";
    IView view = new TextualView(inputStr);

    try {
      IView otherView = ViewFactory.createView("text", new FileWriter("test.txt"), -123, inputStr);

      assertEquals(view.getTextDescription(), otherView.getTextDescription());
    } catch (IOException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test creates a new file in the project folder
  @Test
  public void testViewFactorySVG() {
    String inputStr = "resources/givenCodeForTest/smalldemo.txt";
    IView view = new SVGView(inputStr);
    try {
      try {
        IView otherView = ViewFactory.createView("svg", new FileWriter("test.txt"), 0, inputStr);
        assertEquals(view.getTextDescription(), otherView.getTextDescription());
      } catch (IOException e) {
        throw new IllegalStateException("This test fails!");
      }
    } catch (NullPointerException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  @Test
  public void testViewFactorySVG2() {
    String inputStr = "resources/givenCodeForTest/smalldemo.txt";
    IView view = new SVGView(inputStr, 12);

    IView otherView = ViewFactory.createView("svg", null, 12, inputStr);

    assertEquals(view.getTextDescription(), otherView.getTextDescription());
  }

  // Running this test creates a new file in the project folder
  @Test
  public void testViewFactorySVG3() {
    String inputStr = "resources/givenCodeForTest/smalldemo.txt";
    try {
      try {
        IView view = new SVGView(inputStr, new FileWriter("svg.txt"), 4);
        IView otherView = ViewFactory.createView("svg", new FileWriter("svg.txt"), 4, inputStr);
        assertEquals(view.getTextDescription(), otherView.getTextDescription());
      } catch (IOException e) {
        throw new IllegalStateException("This test fails!");
      }
    } catch (NullPointerException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test creates a new file in the project folder
  @Test
  public void testViewFactorySVG4() {
    String inputStr = "resources/givenCodeForTest/smalldemo.txt";
    try {
      try {
        IView view = new SVGView(inputStr, new FileWriter("svg.txt"));
        IView otherView = ViewFactory.createView("svg", new FileWriter("svg.txt"), 0, inputStr);
        assertEquals(view.getTextDescription(), otherView.getTextDescription());
      } catch (IOException e) {
        throw new IllegalStateException("This test fails!");
      }
    } catch (NullPointerException e) {
      throw new IllegalStateException("This test fails!");
    }
  }


}
