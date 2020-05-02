import static junit.framework.TestCase.assertEquals;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.IView;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class for the controller for the model and the view.
 */
public class ControllerTest {

  IView view;
  IController controller;

  @Before
  public void initialize() {
    view = new EditorView("resources/givenCodeForTest/smalldemo.txt");
    controller = new Controller(view);
  }

  @Before
  public void initialize2() {
    view = new EditorView("resources/givenCodeForTest/smalldemo.txt");
    controller = new Controller(view);
  }

  @Test
  public void testGetTimer() {
    initialize();
    assertEquals(view.getTimer(), controller.getTimer());
  }

  @Test
  public void testStart() {
    initialize();
    controller.start();
    assertEquals(view.getTimer(), controller.getTimer());
  }

  @Test
  public void testStart2() {
    initialize2();
    controller.start();
    assertEquals(view.getTimer(), controller.getTimer());
  }

  @Test
  public void testStop() {
    initialize();
    controller.start();
    controller.stop();
    assertEquals(view.getTimer(), controller.getTimer());
  }

  @Test
  public void testStop2() {
    initialize2();
    controller.start();
    controller.stop();
    assertEquals(view.getTimer(), controller.getTimer());
  }

  @Test
  public void testRestart() {
    initialize();
    controller.restart();
    controller.start();
    assertEquals(view.getTimer(), controller.getTimer());
  }

  @Test
  public void testRestart2() {
    initialize2();
    controller.restart();
    controller.stop();
    assertEquals(view.getTimer(), controller.getTimer());
  }


  // Button Listener tests

  @Test
  public void testPlayButton() {
    initialize();
    JButton b = controller.getView().getPlayButton();
    b.doClick();
    assertEquals("Play", b.getActionCommand());
    assertEquals(controller.getTimer(), controller.getView().getTimer());
  }

  @Test
  public void testPauseButton() {
    initialize();
    JButton b = controller.getView().getPauseButton();
    b.doClick();
    assertEquals("Pause", b.getActionCommand());
    assertEquals(controller.getTimer(), controller.getView().getTimer());
  }

  @Test
  public void testRestartButton() {
    initialize();
    JButton b = controller.getView().getRestartButton();
    b.doClick();
    assertEquals("Restart", b.getActionCommand());
    assertEquals(controller.getTimer(), controller.getView().getTimer());
  }

  @Test
  public void testIncreaseSpeedButton() {
    initialize();
    JButton b = controller.getView().getIncreaseSpeedButton();
    b.doClick();
    assertEquals("Increase Speed", b.getActionCommand());
    assertEquals(controller.getTimer(), controller.getView().getTimer());
  }

  // This test will throw a pop up message since it is decreasing the speed at 1 tick per sec
  @Test
  public void testDecreaseSpeedButton() {
    initialize();
    JButton b = controller.getView().getDecreaseSpeedButton();
    b.doClick();
    assertEquals("Decrease Speed", b.getActionCommand());
    assertEquals(controller.getTimer(), controller.getView().getTimer());
  }

  @Test
  public void testLoopingCheckBox() {
    initialize();
    JCheckBox b = controller.getView().getLoopCheckBox();
    b.doClick();
    assertEquals("Loop", b.getActionCommand());
    assertEquals(controller.getTimer(), controller.getView().getTimer());
  }

  @Test (expected = ArrayIndexOutOfBoundsException.class)
  public void testGetModifyKFButton() {
    initialize();
    JButton b = controller.getView().getModifyKFButton();
    b.doClick();
    assertEquals("Modify KeyFrame", b.getActionCommand());
  }

  @Test
  public void testGetModifyKFButton2() {
    initialize();
    JButton b = controller.getView().getModifyKFButton();
    assertEquals("Modify KeyFrame", b.getActionCommand());
  }

  // This test will throw a pop up message
  @Test
  public void testGetAddKFButton() {
    initialize();
    JButton b = controller.getView().getAddKFButton();
    b.doClick();
    assertEquals("Add KeyFrame", b.getActionCommand());

  }

  @Test (expected = ArrayIndexOutOfBoundsException.class)
  public void testGetRemoveKFButton() {
    initialize();
    JButton b = controller.getView().getRemoveKFButton();
    b.doClick();
    assertEquals("Remove KeyFrame", b.getActionCommand());

  }

  @Test
  public void testGetRemoveKFButto2() {
    initialize();
    JButton b = controller.getView().getRemoveKFButton();
    assertEquals("Remove KeyFrame", b.getActionCommand());

  }

  // This test will throw a pop up message
  @Test
  public void testGetAddShapeButton() {
    initialize();
    JButton b = controller.getView().getAddShapeButton();
    b.doClick();
    assertEquals("Add Shape", b.getActionCommand());

  }

  @Test (expected = ArrayIndexOutOfBoundsException.class)
  public void testGetRemoveShapeButton() {
    initialize();
    JButton b = controller.getView().getRemoveShapeButton();
    b.doClick();
    assertEquals("Remove Shape", b.getActionCommand());

  }

  @Test
  public void testGetRemoveShapeButton2() {
    initialize();
    JButton b = controller.getView().getRemoveShapeButton();
    assertEquals("Remove Shape", b.getActionCommand());

  }
}
