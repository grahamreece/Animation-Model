import cs3500.animator.controller.Controller;
import cs3500.animator.view.IView;
import cs3500.animator.view.VisualView;
import model.keyframe.KeyFrame;
import model.shape.MyRectangle;
import model.shape.ShapeType;

import org.junit.Test;

import java.awt.event.ActionListener;


/**
 * The test class for the VisualView. The animations cannot be tested since they open up a window
 * for an animation. This test class mainly tests for the constructor of the VisualView.
 */
public class VisualViewTest {

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorBad() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt", 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorBad3() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt", -32);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testChangeLoopState() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.changeLoopState();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetButtonListener() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    ActionListener controller = new Controller(view);
    view.setButtonListener(controller);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRestartTick() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.restartTick();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetTicksPerSec() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.setTicksPerSec(1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetTicksPerSec() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getTicksPerSec();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextT() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextT();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextX() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextX();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextY() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextY();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextW() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextW();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextH() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextH();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextR() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextR();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextG() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextG();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModTextB() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModTextB();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetTTextKey() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getTTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetXTextKey() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getXTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetYTextKey() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getYTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetWTextKey() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getWTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetHTextKey() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getHTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetRTextKey() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getRTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetGTextKey() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getGTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetBTextKey() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getBTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetNameTextKey() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getNameTextKey();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetXText() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getXText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetYText() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getYText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetWText() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getWText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetHText() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getHText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetRText() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getRText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetGText() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getGText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetBText() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getBText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetNameText() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getNameText();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddKeyFrame() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.addKeyFrame(new KeyFrame("R", 1, 1, 1,1,1,1,1,1, ShapeType.RECTANGLE), "F");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testDeleteShape() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.deleteShape("R");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetShapeList() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getShapeList();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testDeleteKF() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.deleteKF(new KeyFrame("R", 1, 1, 1,1,1,1,1,1, ShapeType.RECTANGLE));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetRectButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getRectButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetEllipseButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getEllipseButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddShape() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.addShape(new MyRectangle(1, 1, 1, 1, 1, 1, 1, "R"), "R1");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testModifyKF() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.modifyKF(1, 1, 1, 1,1 , 1, 1, 1);
  }


  @Test(expected = UnsupportedOperationException.class)
  public void testGetShapeListModel() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getShapeListModel();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetKeyFrameList() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getKFList();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveKF() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.removeKeyFrame(0);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveShape() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.removeShape(0);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetShapeArrayList() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getShapeArrayList();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetPlayButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getPlayButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetPauseButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getPauseButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetRestartButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getRestartButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetIncreaseSpeedButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getIncreaseSpeedButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetDecreaseSpeedButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getDecreaseSpeedButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetLoopButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getLoopCheckBox();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetModifyButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getModifyKFButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetAddKeyFrameButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getAddKFButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetDeleteKeyFrameButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getRemoveKFButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetAddShapeButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getAddShapeButton();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetDeleteShapeButton() {
    IView view = new VisualView("resources/givenCodeForTest/smalldemo.txt");
    view.getRemoveShapeButton();
  }

}
