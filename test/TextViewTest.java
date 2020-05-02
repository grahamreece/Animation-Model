import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.Controller;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextualView;
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
 * The test class for the TextView. Some tests creates new files in the project folder. Some tests
 * returns a popup error that can be exited by pressing "Ok".
 */
public class TextViewTest {

  @Test
  public void testTextualViewConstructAndGetTextDescription() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");

    assertEquals("canvas 200 70 360 360\n"
                    + "shape R RECTANGLE\n"
                    + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
                    + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
                    + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
                    + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
                    + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
                    + "shape C ELLIPSE\n"
                    + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
                    + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
                    + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
                    + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
                    + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0",
            view.getTextDescription());
  }

  // Running this test should create a new file "smalldemoOutputTest.txt" in the project folder
  @Test
  public void testTextualViewConstruct2() {
    try {
      IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt",
              new FileWriter("smalldemoOutputTest.txt"));

      assertEquals("canvas 200 70 360 360\n"
                      + "shape R RECTANGLE\n"
                      + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
                      + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
                      + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
                      + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
                      + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
                      + "shape C ELLIPSE\n"
                      + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
                      + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
                      + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
                      + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
                      + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0",
              view.getTextDescription());
    } catch (IOException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test should create a new file "smalldemoOutputTest.txt" in the project folder
  @Test
  public void testPlayAnimation() {
    try {
      IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt",
              new FileWriter("smalldemoOutputTest.txt"));

      view.playAnimation();

      try {
        FileReader readFile = new FileReader("smalldemoOutputTest.txt");
        BufferedReader bufferedFile = new BufferedReader(readFile);
        String line;
        StringBuilder str = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while ((line = bufferedFile.readLine()) != null) {
          str.append(line);
          str.append(ls);
        }
        assertEquals("canvas 200 70 360 360\n"
                        + "shape R RECTANGLE\n"
                        + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
                        + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
                        + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
                        + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
                        + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
                        + "shape C ELLIPSE\n"
                        + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
                        + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
                        + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
                        + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
                        + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0",
                str.toString().substring(0, str.length() - 1));
      } catch (FileNotFoundException e) {
        throw new IllegalStateException("This test fails!");
      }

    } catch (IOException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  // Running this test should output the textual view to the system
  @Test
  public void testPlayAnimationSystemOutput() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");

    view.playAnimation();

    assertEquals("canvas 200 70 360 360\n"
                    + "shape R RECTANGLE\n"
                    + "motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0\n"
                    + "motion R 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0\n"
                    + "motion R 50 300 300 50 100 255 0 0 51 300 300 50 100 255 0 0\n"
                    + "motion R 51 300 300 50 100 255 0 0 70 300 300 25 100 255 0 0\n"
                    + "motion R 70 300 300 25 100 255 0 0 100 200 200 25 100 255 0 0\n"
                    + "shape C ELLIPSE\n"
                    + "motion C 6 440 70 120 60 0 0 255 20 440 70 120 60 0 0 255\n"
                    + "motion C 20 440 70 120 60 0 0 255 50 440 250 120 60 0 0 255\n"
                    + "motion C 50 440 250 120 60 0 0 255 70 440 370 120 60 0 170 85\n"
                    + "motion C 70 440 370 120 60 0 170 85 80 440 370 120 60 0 255 0\n"
                    + "motion C 80 440 370 120 60 0 255 0 100 440 370 120 60 0 255 0",
            view.getTextDescription());

  }

  // Running this test will pop up an error message
  @Test(expected = IllegalStateException.class)
  public void testConstructorBad() {
    try {
      IView view = new TextualView("fakeFile");

    } catch (NullPointerException e) {
      throw new IllegalStateException("This test fails!");
    }
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testChangeLoopState() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.changeLoopState();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetButtonListener() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    ActionListener controller = new Controller(view);
    view.setButtonListener(controller);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetTimer() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getTimer();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRestartTick() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.restartTick();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetTicksPerSec() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.setTicksPerSec(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetTicksPerSec() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getTicksPerSec();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextT() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextT();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextX() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextX();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextY() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextY();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextW() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextW();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextH() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextH();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextR() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextR();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextG() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextG();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextB() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextB();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetTTextKey() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getTTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetXTextKey() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getXTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetYTextKey() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getYTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetWTextKey() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getWTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetHTextKey() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getHTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetRTextKey() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getRTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetGTextKey() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getGTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetBTextKey() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getBTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetNameTextKey() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getNameTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetXText() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getXText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetYText() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getYText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetWText() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getWText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetHText() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getHText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetRText() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getRText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetGText() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getGText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetBText() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getBText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetNameText() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getNameText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddKeyFrame() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.addKeyFrame(new KeyFrame("R", 1, 1, 1, 1, 1, 1, 1, 1, ShapeType.RECTANGLE), "F");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testDeleteShape() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.deleteShape("R");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetShapeList() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getShapeList();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testDeleteKF() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.deleteKF(new KeyFrame("R", 1, 1, 1, 1, 1, 1, 1, 1, ShapeType.RECTANGLE));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetRectButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getRectButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetEllipseButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getEllipseButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddShape() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.addShape(new MyRectangle(1, 1, 1, 1, 1, 1, 1, "R"), "R1");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testModifyKF() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.modifyKF(1, 1, 1, 1, 1, 1, 1, 1);
  }


  @Test(expected = UnsupportedOperationException.class)
  public void testGetShapeListModel() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getShapeListModel();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetKeyFrameList() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getKFList();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveKF() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.removeKeyFrame(0);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveShape() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.removeShape(0);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetShapeArrayList() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getShapeArrayList();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetPlayButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getPlayButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetPauseButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getPauseButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetRestartButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getRestartButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetIncreaseSpeedButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getIncreaseSpeedButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetDecreaseSpeedButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getDecreaseSpeedButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetLoopButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getLoopCheckBox();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModifyButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModifyKFButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetAddKeyFrameButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getAddKFButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetDeleteKeyFrameButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getRemoveKFButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetAddShapeButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getAddShapeButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetDeleteShapeButton() {
    IView view = new TextualView("resources/givenCodeForTest/smalldemo.txt");
    view.getRemoveShapeButton();
  }


}