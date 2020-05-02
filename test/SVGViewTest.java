import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.Controller;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import model.keyframe.KeyFrame;
import model.shape.MyRectangle;
import model.shape.ShapeType;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Test;

/**
 * The test class for the SVGView. Some tests creates new files in the project folder. Some tests
 * returns a popup error that can be exited by pressing "Ok".
 */
public class SVGViewTest {

  @Test
  public void testSVGConstructAndGetTextDescription() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");

    assertEquals("<svg width=\"640\" height=\"570\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n"
        + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" fill=\""
        + "rgb(255,0,0)\" visibility=\"visible\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin=\"9000.0ms\""
        + " dur=\"1000.0ms\" fill=\"remove\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" attributeName=\"x\""
        + " from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" attributeName=\"y"
        + "\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" attributeName=\""
        + "width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" attributeName=\""
        + "x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" attributeName=\""
        + "y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"C\" cx=\"440.0\" cy=\"70.0\" rx=\"60.0\" ry=\"30.0\" fill=\"rgb(0,0,255"
        + ")\" visibility=\"visible\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin=\"14000.0"
        + "ms\" dur=\"6000.0ms\" fill=\"remove\" />\n"
        + "<animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"30000.0ms\" attributeName=\""
        + "cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"20000.0ms\" attributeName=\""
        + "cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"20000.0ms\" attributeName=\"f"
        + "ill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"10000.0ms\" attributeName=\"f"
        + "ill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", view.getTextDescription());
  }

  @Test
  public void testSVGConstruct2() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt", 30);

    assertEquals("<svg width=\"640\" height=\"570\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\""
        + " fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\""
        + " begin=\"300.0ms\" dur=\"33.333333333333336ms\" fill=\"remove\" />\n"
        + "<animate attributeType=\"xml\" begin=\"333.3333333333333ms\" dur=\"13"
        + "33.3333333333335ms\" attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"333.3333333333333ms\" dur=\"133"
        + "3.3333333333335ms\" attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1700.0ms\" dur=\"633.3333333"
        + "333335ms\" attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"2333.3333333333335ms\" dur=\""
        + "1000.0ms\" attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"2333.3333333333335ms\" dur="
        + "\"1000.0ms\" attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"C\" cx=\"440.0\" cy=\"70.0\" rx=\"60.0\" ry=\"30.0\" f"
        + "ill=\"rgb(0,0,255)\" visibility=\"visible\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\""
        + " begin=\"466.66666666666663ms\" dur=\"200.0ms\" fill=\"remove\" />\n"
        + "<animate attributeType=\"xml\" begin=\"666.6666666666666ms\" dur=\"100"
        + "0.0000000000001ms\" attributeName=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1666.6666666666667ms\" dur=\"666"
        + ".6666666666667ms\" attributeName=\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1666.6666666666667ms\" dur=\"666"
        + ".6666666666667ms\" attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,"
        + "170,85)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"2333.3333333333335ms\" dur=\"333"
        + ".33333333333303ms\" attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb"
        + "(0,255,0)\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", view.getTextDescription());
  }

  // Running this test should create a new file "test.txt" in the project folder
  @Test
  public void testSVGConstruct3() {
    try {
      IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt",
          new FileWriter("test.txt"), 30);

      assertEquals("<svg width=\"640\" height=\"570\" version=\"1.1\"\n"
          + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
          + "\n"
          + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"1"
          + "00.0\" fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
          + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidd"
          + "en\" begin=\"300.0ms\" dur=\"33.333333333333336ms\" fill=\"remove\" />\n"
          + "<animate attributeType=\"xml\" begin=\"333.3333333333333ms\" dur="
          + "\"1333.3333333333335ms\" attributeName=\"x\" from=\"2" +
              "00\" to=\"300\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"333.3333333333333ms\" dur"
          + "=\"1333.3333333333335ms\" attributeName=\"y\" from=\"200\" to=\"300\" fill=\"fre"
          + "eze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"1700.0ms\" dur=\"633.3333"
          + "333333335ms\" attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"2333.3333333333335ms\" d"
          + "ur=\"1000.0ms\" attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"2333.3333333333335ms\" d"
          + "ur=\"1000.0ms\" attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
          + "</rect>\n"
          + "\n"
          + "<ellipse id=\"C\" cx=\"440.0\" cy=\"70.0\" rx=\"60.0\" ry=\"30.0"
          + "\" fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n"
          + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hid"
          + "den\" begin=\"466.66666666666663ms\" dur=\"200.0ms\" fill=\"remove\" />\n"
          + "<animate attributeType=\"xml\" begin=\"666.6666666666666ms\" d"
          + "ur=\"1000.0000000000001ms\" attributeName=\"cy\" from=\"70\" to=\"250\" fill=\""
          + "freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"1666.6666666666667ms\" "
          + "dur=\"666.6666666666667ms\" attributeName=\"cy\" from=\"250\" "
          + "to=\"370\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"1666.6666666666667ms\" d"
          + "ur=\"666.6666666666667ms\" attributeName=\"fill\" from=\"rgb(0"
          + ",0,255)\" to=\"rgb(0,170,85)\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"2333.3333333333335ms\" d"
          + "ur=\"333.33333333333303ms\" attributeName=\"fill\" from=\"r"
          + "gb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
          + "</ellipse>\n"
          + "\n"
          + "</svg>", view.getTextDescription());
    } catch (IOException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test should create a new file "test.txt" in the project folder
  @Test
  public void testSVGConstruct4() {
    try {
      IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt",
          new FileWriter("test.txt"));

      assertEquals("<svg width=\"640\" height=\"570\" version=\"1.1\"\n"
          + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
          + "\n"
          + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" fill="
          + "\"rgb(255,0,0)\" visibility=\"visible\" >\n"
          + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin="
          + "\"9000.0ms\" dur=\"1000.0ms\" fill=\"remove\" />\n"
          + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" attribu"
          + "teName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" attribut"
          + "eName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" attributeNa"
          + "me=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" attribute"
          + "Name=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" attributeName"
          + "=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
          + "</rect>\n"
          + "\n"
          + "<ellipse id=\"C\" cx=\"440.0\" cy=\"70.0\" rx=\"60.0\" ry=\"30.0\" fill=\"rgb(0,"
          + "0,255)\" visibility=\"visible\" >\n"
          + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin=\"14"
          + "000.0ms\" dur=\"6000.0ms\" fill=\"remove\" />\n"
          + "<animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"30000.0ms\" attributeNam"
          + "e=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"20000.0ms\" attributeNa"
          + "me=\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"20000.0ms\" attributeNa"
          + "me=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" fill=\"freeze\" />\n"
          + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"10000.0ms\" attributeNam"
          + "e=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
          + "</ellipse>\n"
          + "\n"
          + "</svg>", view.getTextDescription());
    } catch (IOException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test should create a new file "demo.txt" in the project folder
  @Test
  public void testPlayAnimation() {
    try {
      IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt",
          new FileWriter("demo.txt"));
      view.playAnimation();

      try {
        FileReader readFile = new FileReader("demo.txt");
        BufferedReader bufferedFile = new BufferedReader(readFile);
        String line;
        StringBuilder str = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while ((line = bufferedFile.readLine()) != null) {
          str.append(line);
          str.append(ls);
        }
        assertEquals("<svg width=\"640\" height=\"570\" version=\"1.1\"\n"
                + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
                + "\n"
                + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" fill="
                + "\"rgb(255,0,0)\" visibility=\"visible\" >\n"
                + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin="
                + "\"9000.0ms\" dur=\"1000.0ms\" fill=\"remove\" />\n"
                + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" attribu"
                + "teName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" attribu"
                + "teName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" attribut"
                + "eName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" attribut"
                + "eName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" attribut"
                + "eName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
                + "</rect>\n"
                + "\n"
                + "<ellipse id=\"C\" cx=\"440.0\" cy=\"70.0\" rx=\"60.0\" ry=\"30.0\" fill=\"rg"
                + "b(0,0,255)\" visibility=\"visible\" >\n"
                + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin="
                + "\"14000.0ms\" dur=\"6000.0ms\" fill=\"remove\" />\n"
                + "<animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"30000.0ms\" attribut"
                + "eName=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"20000.0ms\" attribut"
                + "eName=\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"20000.0ms\" attribut"
                + "eName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"10000.0ms\" attribute"
                + "Name=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
                + "</ellipse>\n"
                + "\n"
                + "</svg>",
            str.toString().substring(0, str.length() - 1));
      } catch (FileNotFoundException e) {
        throw new IllegalStateException("This test fails!");
      }

    } catch (IOException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test should create a new file "demo.svg" in the project folder
  @Test
  public void testPlayAnimation2() {
    try {
      IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt",
          new FileWriter("demo.svg"));
      view.playAnimation();

      try {
        FileReader readFile = new FileReader("demo.svg");
        BufferedReader bufferedFile = new BufferedReader(readFile);
        String line;
        StringBuilder str = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while ((line = bufferedFile.readLine()) != null) {
          str.append(line);
          str.append(ls);
        }
        assertEquals("<svg width=\"640\" height=\"570\" version=\"1.1\"\n"
                + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
                + "\n"
                + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" fill="
                + "\"rgb(255,0,0)\" visibility=\"visible\" >\n"
                + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin="
                + "\"9000.0ms\" dur=\"1000.0ms\" fill=\"remove\" />\n"
                + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" attribu"
                + "teName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" attribu"
                + "teName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" attribut"
                + "eName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" attribut"
                + "eName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" attribut"
                + "eName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
                + "</rect>\n"
                + "\n"
                + "<ellipse id=\"C\" cx=\"440.0\" cy=\"70.0\" rx=\"60.0\" ry=\"30.0\" fill=\"rg"
                + "b(0,0,255)\" visibility=\"visible\" >\n"
                + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin="
                + "\"14000.0ms\" dur=\"6000.0ms\" fill=\"remove\" />\n"
                + "<animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"30000.0ms\" attribut"
                + "eName=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"20000.0ms\" attribut"
                + "eName=\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"20000.0ms\" attribut"
                + "eName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"10000.0ms\" attribute"
                + "Name=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
                + "</ellipse>\n"
                + "\n"
                + "</svg>",
            str.toString().substring(0, str.length() - 1));
      } catch (FileNotFoundException e) {
        throw new IllegalStateException("This test fails!");
      }

    } catch (IOException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test should create a new file "demoSpeed.txt" in the project folder
  @Test
  public void testPlayAnimation3() {
    try {
      IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt",
          new FileWriter("demoSpeed.txt"), 25);
      view.playAnimation();

      try {
        FileReader readFile = new FileReader("demoSpeed.txt");
        BufferedReader bufferedFile = new BufferedReader(readFile);
        String line;
        StringBuilder str = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while ((line = bufferedFile.readLine()) != null) {
          str.append(line);
          str.append(ls);
        }
        assertEquals("<svg width=\"640\" height=\"570\" version=\"1.1\"\n"
                + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
                + "\n"
                + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0"
                + "\" height=\"100.0\" fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
                + "<set attributeName=\"visibility\" attributeType=\"xm"
                + "l\" to=\"hidden\" begin=\"360.0ms\" dur=\"40.0ms\" fill=\"remove\" />\n"
                + "<animate attributeType=\"xml\" begin=\"400.0ms\" du"
                + "r=\"1600.0ms\" attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"400.0ms\" du"
                + "r=\"1600.0ms\" attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"2040.0ms\" d"
                + "ur=\"760.0ms\" attributeName=\"width\" from=\"50\" "
                + "to=\"25\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"2800.0ms\" d"
                + "ur=\"1200.0ms\" attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"2800.0m"
                + "s\" dur=\"1200.0ms\" attributeName=\"y\" from=\"300\""
                + " to=\"200\" fill=\"freeze\" />\n"
                + "</rect>\n"
                + "\n"
                + "<ellipse id=\"C\" cx=\"440.0\" cy=\"70."
                + "0\" rx=\"60.0\" ry=\"30.0\" fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n"
                + "<set attributeName=\"visibility\""
                + " attributeType=\"xml\" to=\"hidden\" begin=\"560.0ms\" dur=\""
                + "240.0ms\" fill=\"remove\" />\n"
                + "<animate attributeType=\"xml\" begin=\"800.0ms\" dur=\"1200"
                + ".0ms\" attributeName=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"800.0m"
                + "s\" attributeName=\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"800.0"
                + "ms\" attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,"
                + "85)\" fill=\"freeze\" />\n"
                + "<animate attributeType=\"xml\" begin=\"2800.0ms\" dur=\"400.0"
                + "ms\" attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,2"
                + "55,0)\" fill=\"freeze\" />\n"
                + "</ellipse>\n"
                + "\n"
                + "</svg>",
            str.toString().substring(0, str.length() - 1));
      } catch (FileNotFoundException e) {
        throw new IllegalStateException("This test fails!");
      }

    } catch (IOException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test should output the SVG textual view to the system
  @Test
  public void testPlayAnimation4SystemOut() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.playAnimation();
    assertEquals("<svg width=\"640\" height=\"570\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n"
        + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" fill=\""
        + "rgb(255,0,0)\" visibility=\"visible\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin=\"9000.0ms\""
        + " dur=\"1000.0ms\" fill=\"remove\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" attributeName=\"x\""
        + " from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"40000.0ms\" attributeName=\"y"
        + "\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"51000.0ms\" dur=\"19000.0ms\" attributeName=\""
        + "width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" attributeName=\""
        + "x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"30000.0ms\" attributeName=\""
        + "y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"C\" cx=\"440.0\" cy=\"70.0\" rx=\"60.0\" ry=\"30.0\" fill=\"rgb(0,0,255"
        + ")\" visibility=\"visible\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin=\"14000.0"
        + "ms\" dur=\"6000.0ms\" fill=\"remove\" />\n"
        + "<animate attributeType=\"xml\" begin=\"20000.0ms\" dur=\"30000.0ms\" attributeName=\""
        + "cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"20000.0ms\" attributeName=\""
        + "cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"50000.0ms\" dur=\"20000.0ms\" attributeName=\"f"
        + "ill\" from=\"rgb(0,0,255)\" to=\"rgb(0,170,85)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"70000.0ms\" dur=\"10000.0ms\" attributeName=\"f"
        + "ill\" from=\"rgb(0,170,85)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", view.getTextDescription());
  }

  // Running this test should output the SVG textual view to the system
  @Test
  public void testPlayAnimation5SystemOut() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt", 50);
    view.playAnimation();
    assertEquals("<svg width=\"640\" height=\"570\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\""
        + "100.0\" fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hid"
        + "den\" begin=\"180.0ms\" dur=\"20.0ms\" fill=\"remove\" />\n"
        + "<animate attributeType=\"xml\" begin=\"200.0ms\" dur=\"800.0ms\""
        + " attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"200.0ms\" dur=\"800.0ms\""
        + " attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1020.0ms\" dur=\"380.0ms"
        + "\" attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1400.0ms\" dur=\"600.0ms"
        + "\" attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1400.0ms\" dur=\"600.0ms"
        + "\" attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"C\" cx=\"440.0\" cy=\"70.0\" rx=\"60.0\" ry=\"3"
        + "0.0\" fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\""
        + "hidden\" begin=\"280.0ms\" dur=\"120.0ms\" fill=\"remove\" />\n"
        + "<animate attributeType=\"xml\" begin=\"400.0ms\" dur=\"600.0m"
        + "s\" attributeName=\"cy\" from=\"70\" to=\"250\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"400."
        + "0ms\" attributeName=\"cy\" from=\"250\" to=\"370\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"400."
        + "0ms\" attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,17"
        + "0,85)\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"1400.0ms\" dur=\"200"
        + ".0ms\" attributeName=\"fill\" from=\"rgb(0,170,85)\" to=\"rgb(0,"
        + "255,0)\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "\n"
        + "</svg>", view.getTextDescription());
  }

  @Test
  public void testToh3() {
    IView view = new SVGView("resources/givenCodeForTest/toh-3.txt");

    assertEquals("<svg width=\"690\" height=\"440\" version=\"1.1\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\">\n"
        + "\n"
        + "<rect id=\"disk1\" x=\"190.0\" y=\"180.0\" width=\"20.0\" height=\"30.0\" fill=\"rgb("
        + "0,49,90)\" visibility=\"visible\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin=\"0.0ms\""
        + " dur=\"1000.0ms\" fill=\"remove\" />\n"
        + "<animate attributeType=\"xml\" begin=\"25000.0ms\" dur=\"10000.0ms\" attributeName=\"y"
        + "\" from=\"180\" to=\"50\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"36000.0ms\" dur=\"10000.0ms\" attributeName=\"x"
        + "\" from=\"190\" to=\"490\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"47000.0ms\" dur=\"10000.0ms\" attributeName=\"y"
        + "\" from=\"50\" to=\"240\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"89000.0ms\" dur=\"10000.0ms\" attributeName=\"y\""
        + " from=\"240\" to=\"50\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"100000.0ms\" dur=\"10000.0ms\" attributeName=\"x"
        + "\" from=\"490\" to=\"340\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"111000.0ms\" dur=\"10000.0ms\" attributeName=\"y"
        + "\" from=\"50\" to=\"210\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"153000.0ms\" dur=\"10000.0ms\" attributeName=\"y"
        + "\" from=\"210\" to=\"50\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"164000.0ms\" dur=\"10000.0ms\" attributeName=\"x"
        + "\" from=\"340\" to=\"190\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"175000.0ms\" dur=\"10000.0ms\" attributeName=\""
        + "y\" from=\"50\" to=\"240\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"217000.0ms\" dur=\"10000.0ms\" attributeName=\""
        + "y\" from=\"240\" to=\"50\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"228000.0ms\" dur=\"10000.0ms\" attributeName=\""
        + "x\" from=\"190\" to=\"490\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"239000.0ms\" dur=\"10000.0ms\" attributeName=\""
        + "y\" from=\"50\" to=\"180\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"249000.0ms\" dur=\"8000.0ms\" attributeName=\"f"
        + "ill\" from=\"rgb(0,49,90)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "<rect id=\"disk2\" x=\"167.0\" y=\"210.0\" width=\"65.0\" height=\"30.0\" fill=\"rgb(6"
        + ",247,41)\" visibility=\"visible\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin=\"0.0ms\" "
        + "dur=\"1000.0ms\" fill=\"remove\" />\n"
        + "<animate attributeType=\"xml\" begin=\"57000.0ms\" dur=\"10000.0ms\" attributeName=\"y"
        + "\" from=\"210\" to=\"50\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"68000.0ms\" dur=\"10000.0ms\" attributeName=\"x"
        + "\" from=\"167\" to=\"317\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"79000.0ms\" dur=\"10000.0ms\" attributeName=\"y\""
        + " from=\"50\" to=\"240\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"185000.0ms\" dur=\"10000.0ms\" attributeName=\"y"
        + "\" from=\"240\" to=\"50\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"196000.0ms\" dur=\"10000.0ms\" attributeName=\"x"
        + "\" from=\"317\" to=\"467\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"207000.0ms\" dur=\"10000.0ms\" attributeName=\"y"
        + "\" from=\"50\" to=\"210\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"217000.0ms\" dur=\"8000.0ms\" attributeName=\"f"
        + "ill\" from=\"rgb(6,247,41)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "<rect id=\"disk3\" x=\"145.0\" y=\"240.0\" width=\"110.0\" height=\"30.0\" fill=\"rg"
        + "b(11,45,175)\" visibility=\"visible\" >\n"
        + "<set attributeName=\"visibility\" attributeType=\"xml\" to=\"hidden\" begin=\"0.0ms\""
        + " dur=\"1000.0ms\" fill=\"remove\" />\n"
        + "<animate attributeType=\"xml\" begin=\"121000.0ms\" dur=\"10000.0ms\" attributeName="
        + "\"y\" from=\"240\" to=\"50\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"132000.0ms\" dur=\"10000.0ms\" attributeName="
        + "\"x\" from=\"145\" to=\"445\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"143000.0ms\" dur=\"10000.0ms\" attributeName="
        + "\"y\" from=\"50\" to=\"240\" fill=\"freeze\" />\n"
        + "<animate attributeType=\"xml\" begin=\"153000.0ms\" dur=\"8000.0ms\" attributeName=\""
        + "fill\" from=\"rgb(11,45,175)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n"
        + "</rect>\n"
        + "\n"
        + "</svg>", view.getTextDescription());
  }

  // Running this test will pop up an error message
  @Test(expected = IllegalStateException.class)
  public void testConstructorBad() {
    try {
      IView view = new SVGView("badFile.");

    } catch (NullPointerException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test will pop up an error message
  @Test(expected = IllegalStateException.class)
  public void testConstructorBad2() {
    try {
      IView view = new SVGView("boo!", 23);

    } catch (NullPointerException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test will pop up an error message
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorBad3() {
    try {
      IView view = new SVGView("badFile.", 0);

    } catch (NullPointerException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test will pop up an error message
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorBad4() {
    try {
      IView view = new SVGView("badFile.", new FileWriter("mi.txt"), -2);

    } catch (NullPointerException e) {
      throw new IllegalStateException("This test fails!");
    } catch (IOException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testChangeLoopState() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.changeLoopState();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetButtonListener() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    ActionListener controller = new Controller(view);
    view.setButtonListener(controller);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetTimer() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getTimer();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRestartTick() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.restartTick();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetTicksPerSec() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.setTicksPerSec(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetTicksPerSec() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getTicksPerSec();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextT() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextT();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextX() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextX();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextY() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextY();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextW() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextW();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextH() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextH();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextR() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextR();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextG() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextG();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextB() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextB();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetTTextKey() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getTTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetXTextKey() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getXTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetYTextKey() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getYTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetWTextKey() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getWTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetHTextKey() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getHTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetRTextKey() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getRTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetGTextKey() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getGTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetBTextKey() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getBTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetNameTextKey() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getNameTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetXText() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getXText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetYText() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getYText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetWText() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getWText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetHText() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getHText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetRText() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getRText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetGText() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getGText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetBText() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getBText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetNameText() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getNameText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddKeyFrame() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.addKeyFrame(new KeyFrame("R", 1, 1, 1,1,1,1,1,1, ShapeType.RECTANGLE), "F");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testDeleteShape() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.deleteShape("R");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetShapeList() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getShapeList();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testDeleteKF() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.deleteKF(new KeyFrame("R", 1, 1, 1,1,1,1,1,1, ShapeType.RECTANGLE));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetRectButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getRectButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetEllipseButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getEllipseButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddShape() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.addShape(new MyRectangle(1, 1, 1, 1, 1, 1, 1, "R"), "R1");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testModifyKF() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.modifyKF(1, 1, 1, 1,1 , 1, 1, 1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetShapeListModel() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getShapeListModel();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetKeyFrameList() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getKFList();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveKF() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.removeKeyFrame(0);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveShape() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.removeShape(0);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetShapeArrayList() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getShapeArrayList();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetPlayButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getPlayButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetPauseButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getPauseButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetRestartButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getRestartButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetIncreaseSpeedButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getIncreaseSpeedButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetDecreaseSpeedButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getDecreaseSpeedButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetLoopButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getLoopCheckBox();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModifyButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getModifyKFButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetAddKeyFrameButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getAddKFButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetDeleteKeyFrameButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getRemoveKFButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetAddShapeButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getAddShapeButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetDeleteShapeButton() {
    IView view = new SVGView("resources/givenCodeForTest/smalldemo.txt");
    view.getRemoveShapeButton();
  }

}
