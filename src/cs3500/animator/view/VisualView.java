package cs3500.animator.view;

import cs3500.animator.util.AnimationReader;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.IModel;
import model.Model.Builder;
import model.keyframe.IKeyFrame;
import model.motion.IMotion;
import model.shape.IShape;

/**
 * The view that shows and plays the animation. This view draws the animation inside a window,
 * effectively producing the animations. When the animation ends, the window does not close/end. It
 * will continue showing a blank white canvas. The animation ends when the user exits the window.
 * The user will be able to scroll through the canvas using the scrollbars on the window to view the
 * entire animation (if the window is not big enough for the entire animation). The animation starts
 * when the view is loaded, with no additional inputs from the user.
 */
public class VisualView extends JPanel implements IView {

  protected IModel model;
  protected int currTick;
  protected int ticksPerSec;
  protected Timer timer;
  protected JFrame frame;

  /**
   * Constructs a VisualView with a String for the file name for the model information.
   *
   * @param fileName the file name with the model information in String form
   */
  public VisualView(String fileName) {
    super();
    this.model = this.readFile(fileName);
    this.ticksPerSec = 1000;
    this.currTick = 0;
    this.frame = new JFrame();
    this.timer = new Timer(this.ticksPerSec, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        currTick++;
        repaint();
      }
    });
  }

  /**
   * Constructs a VisualView with a String for the file name for the model information and the given
   * speed (in ticks per second).
   *
   * @param fileName the file name with the model information in String form
   * @param ticksPerSec the speed of the animation in ticks per sec
   * @throws IllegalArgumentException if the given speed is 0 or less
   */
  public VisualView(String fileName, int ticksPerSec) throws IllegalArgumentException {
    super();
    if (ticksPerSec < 1) {
      throw new IllegalArgumentException("Ticks per second cannot be 0 or less!");
    }
    this.model = this.readFile(fileName);
    this.ticksPerSec = 1000 / ticksPerSec;
    this.currTick = 0;
    this.frame = new JFrame();
    this.timer = new Timer(this.ticksPerSec, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        currTick++;
        repaint();
      }
    });
  }

  /**
   * Constructs and returns a new model that holds the information of an animation. The information
   * is given in a text file. The inputted String is the name of the text file.
   *
   * @param fileName the name of the file that holds the animation information for the model
   * @return the model constructed by the animation information in the given file
   */
  private IModel readFile(String fileName) {
    final JPanel panel = new JPanel();

    try {
      FileReader fileToRead;
      fileToRead = new FileReader(fileName);
      Builder builder = new Builder();
      AnimationReader animationReader = new AnimationReader();
      animationReader.parseFile(fileToRead, builder);
      this.model = builder.build();
    } catch (FileNotFoundException e) {
      JOptionPane
          .showMessageDialog(panel, "Error opening the given file!", "Error",
              JOptionPane.ERROR_MESSAGE);
      return model;
    }
    return model;
  }

  @Override
  public void playAnimation() {

    JScrollPane scrollPane = new JScrollPane(this);
    scrollPane.setPreferredSize(new Dimension(model.getBorderW(), model.getBorderH()));
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    this.setPreferredSize(new Dimension(model.getRightMostX() + 200, model.getDownMostY() + 200));
    this.setLocation(model.getLeftMostX(), model.getUpMostY());

    timer.start();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setTitle("Animation");
    frame.setPreferredSize(new Dimension(model.getRightMostX(), model.getDownMostY()));

    frame.add(scrollPane);

    frame.pack();

    frame.setVisible(true);
  }

  @Override
  public String getTextDescription() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public void changeLoopState() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public void setButtonListener(ActionListener event) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public Timer getTimer() throws UnsupportedOperationException {
    return this.timer;
  }

  @Override
  public void restartTick()  throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public void setTicksPerSec(int newTicksPerSec) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getTicksPerSec() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getModTextT() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getModTextX() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getModTextY() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getModTextW() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getModTextH() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getModTextR() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getModTextG() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getModTextB() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getTTextKey() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getXTextKey() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getYTextKey() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getWTextKey() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getHTextKey() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getRTextKey() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getGTextKey() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getBTextKey() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public String getNameText() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getXText() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getYText() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getWText() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getHText() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getRText() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getGText() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public int getBText() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public String getNameTextKey() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public IModel getModel() {
    return model;
  }


  @Override
  public void addKeyFrame(IKeyFrame kf, String id) {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public void deleteShape(String shapeName) {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JList getShapeList() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public void deleteKF(IKeyFrame kf) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JRadioButton getRectButton() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JRadioButton getEllipseButton() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public void addShape(IShape shape, String id) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public void modifyKF(int t, int x, int y, int w, int h, int r, int g, int b)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public DefaultListModel getShapeListModel() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JList getKFList() {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public void removeKeyFrame(int i) {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public void removeShape(int i) {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public ArrayList<IShape> getShapeArrayList() {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public boolean getLoopState() {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JButton getPlayButton() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JButton getPauseButton() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JButton getRestartButton() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JButton getIncreaseSpeedButton() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JButton getDecreaseSpeedButton() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JCheckBox getLoopCheckBox() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JButton getModifyKFButton() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JButton getRemoveKFButton() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JButton getAddKFButton() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JButton getAddShapeButton() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public JButton getRemoveShapeButton() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;

    for (IMotion motion : model.getMotionList()) {
      int startTick = motion.getStartTick();
      int endTick = motion.getEndTick();
      if (startTick <= currTick && currTick <= endTick) {
        motion.getShapeAtTick(currTick).drawShape(g2D);
      }
    }
  }

}
