package cs3500.animator.controller;

import cs3500.animator.view.IView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.keyframe.IKeyFrame;
import model.keyframe.KeyFrame;
import model.shape.IShape;
import model.shape.MyEllipse;
import model.shape.MyRectangle;
import model.shape.ShapeType;

/**
 * The controller class for the view. The controller modifies the view and the model depending on
 * the user interactions and inputs. This controller has the playback functionality and the input
 * functionality for the EditorView. The playback functionality lets the user play, pause, restart,
 * loop, increase the speed, and decrease the speed of the animation. The input functionality for
 * the EditorView includes the functionality for adding a shape, adding a keyframe, deleting a
 * shape, deleting a keyframe, and modifying a keyframe.
 */
public class Controller implements IController, ActionListener {

  private IView view;
  private Timer viewTimer;
  final private JPanel popUp = new JPanel();

  private String name;
  private int t;
  private int x;
  private int y;
  private int w;
  private int h;
  private int r;
  private int g;
  private int b;

  /**
   * The constructor for this controller with a view. The view holds the model for the animation.
   * The view is set to a certain type of view.
   *
   * @param view the view for the animation
   * @throws UnsupportedOperationException if the given view does not support timers
   */
  public Controller(IView view) throws UnsupportedOperationException {
    this.view = view;
    this.viewTimer = view.getTimer();
    this.view.setButtonListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) throws IllegalArgumentException {
    // buttons for the playback.
    switch (e.getActionCommand()) {
      case "Play":
        this.start();
        break;
      case "Pause":
        this.stop();
        break;
      case "Restart":
        this.restart();
        break;
      case "Loop":
        view.changeLoopState();
        break;
      case "Increase Speed":
        view.setTicksPerSec(view.getTicksPerSec() + 1);
        break;
      case "Decrease Speed":
        view.setTicksPerSec(view.getTicksPerSec() - 1);
        break;
      default:
        break;
    }

    // buttons for adding shapes and keyframes, deleting shapes and keyframes, and modifying
    // keyframes.
    switch (e.getActionCommand()) {
      case "Add KeyFrame":
        addKeyFrame();
        break;
      case "Modify KeyFrame":
        modifyKeyFrame();
        view.modifyKF(t, x, y, w, h, r, g, b);
        break;
      case "Remove KeyFrame":
        removeKeyFrame();
        break;
      case "Add Shape":
        addShape();
        break;
      case "Remove Shape":
        removeShape();
        break;
      default:
        break;
    }
  }

  /**
   * A private helper for modifying the keyFrame when the Modify KeyFrame button is pressed. This
   * method firstly gets all the inputted elements for modifying the keyFrame. If the user inputted
   * an incorrect input or did not input anything yet, it throws a popup error to the user. If the
   * input is correct, the view is applied the modifyKF helper to modify the clicked keyFrame to
   * the inputted elements.
   */
  private void modifyKeyFrame() {
    try {
      try {
        t = view.getModTextT();
        x = view.getModTextX();
        y = view.getModTextY();
        w = view.getModTextW();
        h = view.getModTextH();
        r = view.getModTextR();
        g = view.getModTextG();
        b = view.getModTextB();
      } catch (UnsupportedOperationException uoe) {
        return;
      }
      view.modifyKF(t, x, y, w, h, r, g, b);
    } catch (IllegalArgumentException | IllegalStateException iae) {
      JOptionPane
          .showMessageDialog(popUp, iae, "Error",
              JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * A private helper for removing the shape when the Remove Shape button is pressed. This method
   * firstly gets the currently clicked and hovered shape on the UI. Then, the view is applied the
   * removeShape helper to delete the selected shape from the model.
   */
  private void removeShape() {
    int i = view.getShapeList().getSelectedIndex();
    view.removeShape(i);
  }

  /**
   * A private helper for removing the keyFrame when the Remove KeyFrame button is pressed. This
   * method firstly gets the currently clicked and hovered KeyFrame on the UI. Then, the view is
   * applied the removeKeyFrame helper to delete the selected KeyFrame from the model.
   */
  private void removeKeyFrame() {
    int i = view.getKFList().getSelectedIndex();
    view.removeKeyFrame(i);
  }

  /**
   * A private helper for adding a shape when the Add Shape button is pressed. This method gets all
   * the inputted elements for adding a shape. If the user inputted an incorrect input or did not
   * input anything yet, it throws a popup error to the user. If the input is correct, the view is
   * applied the addShape helper to add a shape to the model. Depending on what JRadioButton is
   * clicked for the type of shape being added, it will add the selected shape.
   */
  private void addShape() {
    try {
      try {
        name = view.getNameText();
        x = view.getXText();
        y = view.getYText();
        w = view.getWText();
        h = view.getHText();
        r = view.getRText();
        g = view.getGText();
        b = view.getBText();
      } catch (UnsupportedOperationException uoe) {
        return;
      }
      if (view.getRectButton().isSelected()) {
        IShape rect = new MyRectangle(w, h, x, y, r, g, b, name);
        view.addShape(rect, name + x + y + w + h + r + g + b);
      }
      if (view.getEllipseButton().isSelected()) {
        IShape ellipse = new MyEllipse(w, h, x, y, r, g, b, name);
        view.addShape(ellipse, name + x + y + w + h + r + g + b);
      }
    } catch (IllegalArgumentException | IllegalStateException iae) {
      JOptionPane
          .showMessageDialog(popUp, iae, "Error",
              JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * A private helper for adding a shape when the Add KeyFrame button is pressed. This method gets
   * all the inputted elements for adding a KeyFrame. If the user inputted an incorrect input or did
   * not input anything yet, it throws a popup error to the user. If the input is correct, the view
   * is applied the addKeyFrame helper to add a shape to the model. If the given shape name does not
   * exist in the model, it will throw a popup error to the user as well.
   */
  private void addKeyFrame() {
    try {
      try {
        name = view.getNameTextKey();
        t = view.getTTextKey();
        x = view.getXTextKey();
        y = view.getYTextKey();
        w = view.getWTextKey();
        h = view.getHTextKey();
        r = view.getRTextKey();
        g = view.getGTextKey();
        b = view.getBTextKey();
      } catch (UnsupportedOperationException uoe) {
        return;
      }

      if (!(this.existsInShape(name))) {
        JOptionPane
            .showMessageDialog(popUp, "The shape name doesn't exist!", "Error",
                JOptionPane.ERROR_MESSAGE);
      }

      ShapeType shapeType = null;
      for (int i = 0; i < view.getShapeArrayList().size(); i++) {
        IShape s = view.getShapeArrayList().get(i);
        if (s.getShapeName().equals(name)) {
          shapeType = s.getShapeType();
          IKeyFrame kf = new KeyFrame(name, t, x, y, w, h, r, g, b, shapeType);
          view.addKeyFrame(kf, name + t + x + y + w + h + r + g + b);
        }
      }

    } catch (IllegalArgumentException | IllegalStateException iae) {
      JOptionPane
          .showMessageDialog(popUp, iae, "Error",
              JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Determines if a shape with the given shapeName exists in the model for the animation.
   *
   * @param shapeName the shapeName that will be checked if the model contains a shape with the name
   * @return true if the shape with the given shapeName exists in the model for the animation
   */
  private boolean existsInShape(String shapeName) {
    boolean exists = false;
    for (IShape s : view.getShapeArrayList()) {
      if (s.getShapeName().equals(shapeName)) {
        exists = true;
      }
    }
    return exists;
  }

  @Override
  public void start() {
    viewTimer.start();
  }

  @Override
  public void stop() {
    viewTimer.stop();
  }

  @Override
  public void restart() {
    viewTimer.restart();
    view.restartTick();
  }

  @Override
  public Timer getTimer() {
    return viewTimer;
  }

  @Override
  public IView getView() {
    return view;
  }
}
