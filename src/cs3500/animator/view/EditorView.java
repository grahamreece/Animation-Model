package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import model.keyframe.IKeyFrame;
import model.keyframe.KeyFrame;
import model.shape.IShape;
import model.shape.ShapeType;

/**
 * The view that shows and plays the animation and lets the user edit the animation. This view has
 * the same functionality to show the animation as the VisualView. It shows the animation in a new
 * window, which can be exited by the user by closing the window. This view has the functionality
 * to play, pause, restart, loop, increase the speed, and/or decrease the speed of the animation as
 * playback controls. This view also has the functionality to add a shape, add a KeyFrame, delete a
 * shape, delete a KeyFrame, and/or modify a KeyFrame. The user should be able to easily identify
 * how to work these functionalities by looking at the UI. The default for the animation is not
 * looped. If the increase speed or decrease speed button is clicked when the animation has not
 * started yet or is paused, the animation will start playing. If the user tries to go lower than 1
 * tick per second, the view will throw an popup error message. The window is not set to be
 * resizable to avoid situations where the user cannot view some of the buttons.
 */
public class EditorView extends VisualView {

  private JPanel playbackPanel;
  private JButton playButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton increaseSpeedButton;
  private JButton decreaseSpeedButton;
  private JCheckBox loopCheckbox;
  private JRadioButton rectangleRadio;
  private JRadioButton ellipseRadio;

  private JPanel deletePanel;
  private JList shapeList;
  private ArrayList<IShape> shapeArrayList = new ArrayList<IShape>();
  private JButton modifyKFButton;
  private JButton removeKFButton;
  private JButton addKeyFrameButton;
  private JButton addShape;
  private JButton removeShape;

  private JTextField modTextT = new JTextField(4);
  private JTextField modTextX = new JTextField(4);
  private JTextField modTextY = new JTextField(4);
  private JTextField modTextW = new JTextField(4);
  private JTextField modTextH = new JTextField(4);
  private JTextField modTextR = new JTextField(4);
  private JTextField modTextG = new JTextField(4);
  private JTextField modTextB = new JTextField(4);

  private JTextField nameTextKey = new JTextField(4);
  private JTextField tTextKey = new JTextField(4);
  private JTextField xTextKey = new JTextField(4);
  private JTextField yTextKey = new JTextField(4);
  private JTextField wTextKey = new JTextField(4);
  private JTextField hTextKey = new JTextField(4);
  private JTextField rTextKey = new JTextField(4);
  private JTextField gTextKey = new JTextField(4);
  private JTextField bTextKey = new JTextField(4);

  private JTextField nameText = new JTextField(4);
  private JTextField xText = new JTextField(4);
  private JTextField yText = new JTextField(4);
  private JTextField wText = new JTextField(4);
  private JTextField hText = new JTextField(4);
  private JTextField rText = new JTextField(4);
  private JTextField gText = new JTextField(4);
  private JTextField bText = new JTextField(4);

  private JPanel addKeyFramePanel;
  private JList kfList;
  private ArrayList<IKeyFrame> kfArrayList;
  private DefaultListModel kfListModel;
  private DefaultListModel shapeListModel;
  private JPanel rightPanel;
  final private JPanel popUp = new JPanel();
  private int ticksPerSecInput;
  private boolean isLooping;

  /**
   * Constructs an EditorView with a String for the file name for the model information.
   *
   * @param fileName the file name with the model information in String form
   */
  public EditorView(String fileName) {
    super(fileName);
    ticksPerSecInput = 1;
    initialize();
  }

  /**
   * Constructs an EditorView with  a String for the file name for the model information and the
   * given speed (in ticks per second).
   *
   * @param fileName    the file name with the model information in String form
   * @param ticksPerSec the speed of the animation in ticks per sec
   * @throws IllegalArgumentException if the given speed is 0 or less
   */
  public EditorView(String fileName, int ticksPerSec) throws IllegalArgumentException {
    super(fileName, ticksPerSec);
    ticksPerSecInput = ticksPerSec;
    initialize();
  }

  /**
   * A private helper to initialize the EditorView with the animation (and it's model) and the UI.
   */
  private void initialize() {
    makePlayBack();
    makeDeletePanel();
    makeAddKeyFrame();
    makeAddPanel();
    makeDeleteKeyFrame();
    makeModifyKF();
    setAnimation();
  }

  /**
   * Makes the modify KeyFrame UI for this view and places it on the right side of the UI. Modify
   * KeyFrame should be able to take in all elements of the KeyFrame.
   */
  private void makeModifyKF() {
    JPanel modKFPanel = new JPanel(new GridLayout(6, 1, 1, 1));
    modifyKFButton = new JButton("Modify KeyFrame");
    JLabel modifyKFLabel = new JLabel("                                                   "
            + "                             MODIFY A KEYFRAME\n");
    JLabel modT = new JLabel("Tick");
    JLabel modX = new JLabel("X position");
    JLabel modY = new JLabel("Y position");
    JLabel modW = new JLabel("Width");
    JLabel modH = new JLabel("Height");
    JLabel modR = new JLabel("Red");
    JLabel modG = new JLabel("Green");
    JLabel modB = new JLabel("Blue");

    JPanel modPanel1 = new JPanel(new FlowLayout());
    modPanel1.add(modT);
    modPanel1.add(modTextT);
    JPanel modPanel2 = new JPanel(new FlowLayout());
    modPanel2.add(modX);
    modPanel2.add(modTextX);
    modPanel2.add(modY);
    modPanel2.add(modTextY);
    JPanel modPanel3 = new JPanel(new FlowLayout());
    modPanel3.add(modW);
    modPanel3.add(modTextW);
    modPanel3.add(modH);
    modPanel3.add(modTextH);
    JPanel modPanel4 = new JPanel(new FlowLayout());
    modPanel4.add(modR);
    modPanel4.add(modTextR);
    modPanel4.add(modG);
    modPanel4.add(modTextG);
    modPanel4.add(modB);
    modPanel4.add(modTextB);

    modKFPanel.add(modifyKFLabel);
    modKFPanel.add(modPanel1);
    modKFPanel.add(modPanel2);
    modKFPanel.add(modPanel3);
    modKFPanel.add(modPanel4);
    modKFPanel.add(modifyKFButton);

    JPanel rightPanelDivided = new JPanel(new GridLayout(2, 1, 0, 0));
    rightPanelDivided.add(rightPanel);
    rightPanelDivided.add(modKFPanel);

    frame.add(rightPanelDivided, BorderLayout.EAST);
  }

  /**
   * Makes the delete KeyFrame UI for this view and places it on the right side of the UI. Delete
   * KeyFrame should be able to click any of the listed KeyFrame in the UI and click the delete
   * KeyFrame button.
   */
  private void makeDeleteKeyFrame() {
    rightPanel = new JPanel(new FlowLayout());
    removeKFButton = new JButton("Remove KeyFrame");
    JLabel deleteKFLabel = new JLabel("EXISTING KEYFRAMES");
    JPanel deleteKFPanel = new JPanel();
    deleteKFPanel.setLayout(new BoxLayout(deleteKFPanel, BoxLayout.Y_AXIS));
    deleteKFPanel.setBackground(Color.LIGHT_GRAY);
    createNewKFList();
    kfList.setVisibleRowCount(1);

    deleteKFPanel.add(deleteKFLabel);
    deleteKFPanel.add(kfList);
    JScrollPane spKFV = new JScrollPane(kfList);
    spKFV.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    spKFV.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    deleteKFPanel.add(spKFV);
    deleteKFPanel.setPreferredSize(new Dimension(550, 200));
    deleteKFPanel.add(removeKFButton);

    rightPanel.add(deletePanel);
    rightPanel.add(deleteKFPanel);
  }

  /**
   * A helper to create a KeyFrame JList for the view according to what KeyFrames are in the
   * model.
   */
  private void createNewKFList() {
    kfArrayList = model.getKeyFrameList();
    kfListModel = new DefaultListModel();
    for (int i = 0; i < kfArrayList.size(); i++) {
      kfListModel.addElement("KeyFrame" + (i + 1) + " \n" + this.getKFInfo(kfArrayList.get(i)));
    }
    kfList = new JList(kfListModel);

  }

  /**
   * Returns the information for a KeyFrame to show to the user. This includes the KeyFrame's shape
   * name, tick, position, size, and color.
   *
   * @param kf the KeyFrame that this method is returning the information for
   * @return the information for a KeyFrame to show to the user
   */
  private String getKFInfo(IKeyFrame kf) {
    return "Shape: " + kf.getShapeName() + " Tick:" + kf.getTick() + " Pos: (" + kf.getX() + ", "
            + kf.getY() + ") \nSize: ("
            + kf.getWidth() + ", " + kf.getHeight() + ") \nColor: (" + kf.getRed() + ", " +
            kf.getGreen() + ", " + kf.getBlue() + ")";
  }

  /**
   * Makes the add KeyFrame UI for this view and places it on the bottom of the UI. Add KeyFrame
   * includes the input boxes for the elements of the KeyFrame and a button to add a KeyFrame.
   */
  private void makeAddKeyFrame() {
    JLabel addKeyFrameLabel = new JLabel("ADD A KEYFRAME");
    addKeyFrameButton = new JButton("Add KeyFrame");
    JLabel nameLabelKey = new JLabel("Shape Name");
    JLabel tLabelKey = new JLabel("Tick");
    JLabel xLabelKey = new JLabel("X Position");
    JLabel yLabelKey = new JLabel("Y Position");
    JLabel wLabelKey = new JLabel("Width");
    JLabel hLabelKey = new JLabel("Height");
    JLabel rLabelKey = new JLabel("Red");
    JLabel gLabelKey = new JLabel("Green");
    JLabel bLabelKey = new JLabel("Blue");

    addKeyFramePanel = new JPanel();
    addKeyFramePanel.setLayout(new BoxLayout(addKeyFramePanel, BoxLayout.PAGE_AXIS));
    JPanel addKeyFramePanel1 = new JPanel(new FlowLayout());
    JPanel addKeyFramePanel2 = new JPanel(new FlowLayout());
    JPanel addKeyFramePanel3 = new JPanel(new FlowLayout());
    JPanel addKeyFramePanel0 = new JPanel(new FlowLayout());
    addKeyFramePanel0.add(nameLabelKey);
    addKeyFramePanel0.add(nameTextKey);
    addKeyFramePanel1.add(tLabelKey);
    addKeyFramePanel1.add(tTextKey);
    addKeyFramePanel1.add(xLabelKey);
    addKeyFramePanel1.add(xTextKey);
    addKeyFramePanel1.add(yLabelKey);
    addKeyFramePanel1.add(yTextKey);
    addKeyFramePanel3.add(wLabelKey);
    addKeyFramePanel3.add(wTextKey);
    addKeyFramePanel3.add(hLabelKey);
    addKeyFramePanel3.add(hTextKey);
    addKeyFramePanel2.add(rLabelKey);
    addKeyFramePanel2.add(rTextKey);
    addKeyFramePanel2.add(gLabelKey);
    addKeyFramePanel2.add(gTextKey);
    addKeyFramePanel2.add(bLabelKey);
    addKeyFramePanel2.add(bTextKey);
    addKeyFramePanel.add(addKeyFrameLabel);
    addKeyFramePanel.add(addKeyFramePanel0);
    addKeyFramePanel.add(addKeyFramePanel1);
    addKeyFramePanel.add(addKeyFramePanel3);
    addKeyFramePanel.add(addKeyFramePanel2);
    addKeyFramePanel.add(addKeyFrameButton);
  }

  /**
   * Makes the playback UI for this view and places it on the bottom of the UI. The Playback
   * includes play, pause, restart, loop, increasing the speed, and decreasing the speed for the
   * animation.
   */
  private void makePlayBack() {
    frame.setLayout(new BorderLayout());
    JPanel buttonPanel = new JPanel(new FlowLayout());
    JLabel playbackControlLabel = new JLabel("PLAYBACK CONTROLS");

    playButton = new JButton("Play");
    pauseButton = new JButton("Pause");
    restartButton = new JButton("Restart");
    loopCheckbox = new JCheckBox("Loop");
    increaseSpeedButton = new JButton("Increase Speed");
    decreaseSpeedButton = new JButton("Decrease Speed");

    buttonPanel.add(playbackControlLabel);
    buttonPanel.add(playButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(loopCheckbox);
    buttonPanel.add(increaseSpeedButton);
    buttonPanel.add(decreaseSpeedButton);

    playbackPanel = new JPanel();
    playbackPanel.setLayout(new BoxLayout(playbackPanel, BoxLayout.Y_AXIS));
    playbackPanel.add(playbackControlLabel);
    playbackPanel.add(buttonPanel);

    isLooping = false;

    this.timer = new Timer(this.ticksPerSec, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (isLooping && currTick == model.getLastTick()) {
          currTick = 0;
          timer.restart();
        }
        currTick++;
        repaint();
      }
    });
  }

  /**
   * Makes the add shape UI for this view and places it on the bottom of the UI. Add Shape should
   * be able to take in the elements for the shape. There should be a button the user can press to
   * add the shape. There should be a radio button for the user to select the type of shape that is
   * being added.
   */
  private void makeAddPanel() {
    JPanel addShapePanel = new JPanel(new FlowLayout());
    JPanel chooseShapeType = new JPanel(new FlowLayout());
    JPanel describeShape = new JPanel(new FlowLayout());
    JPanel describeShapeColor = new JPanel(new FlowLayout());

    addShape = new JButton("Add Shape");
    addShapePanel.add(addShape);

    rectangleRadio = new JRadioButton("Rectangle");
    ellipseRadio = new JRadioButton("Ellipse");
    chooseShapeType.add(rectangleRadio);
    chooseShapeType.add(ellipseRadio);

    JLabel nameLabel = new JLabel("Shape Name");
    JLabel xLabel = new JLabel("X Position");
    JLabel yLabel = new JLabel("Y Position");
    JLabel wLabel = new JLabel("Width");
    JLabel hLabel = new JLabel("Height");
    JLabel rLabel = new JLabel("Red");
    JLabel gLabel = new JLabel("Green");
    JLabel bLabel = new JLabel("Blue");

    describeShape.add(nameLabel);
    describeShape.add(nameText);
    describeShape.add(xLabel);
    describeShape.add(xText);
    describeShape.add(yLabel);
    describeShape.add(yText);
    describeShapeColor.add(rLabel);
    describeShapeColor.add(rText);
    describeShapeColor.add(gLabel);
    describeShapeColor.add(gText);
    describeShapeColor.add(bLabel);
    describeShapeColor.add(bText);

    JPanel addingShapePanel = new JPanel();
    addingShapePanel.setLayout(new BoxLayout(addingShapePanel, BoxLayout.PAGE_AXIS));
    JLabel addingShapeLabel = new JLabel("ADD A SHAPE");
    addingShapePanel.add(addingShapeLabel);
    addingShapePanel.add(chooseShapeType);
    JPanel describeShape2 = new JPanel();
    describeShape2.add(wLabel);
    describeShape2.add(wText);
    describeShape2.add(hLabel);
    describeShape2.add(hText);
    addingShapePanel.add(describeShape);
    addingShapePanel.add(describeShape2);
    addingShapePanel.add(describeShapeColor);
    addingShapePanel.add(addShapePanel);

    JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 1, 1));
    bottomPanel.add(playbackPanel);
    bottomPanel.add(addingShapePanel);
    bottomPanel.add(addKeyFramePanel);
    frame.add(bottomPanel, BorderLayout.SOUTH);
  }

  /**
   * Makes the panel for deleting KeyFrames and Shapes and places it on the right side of the
   * view.
   */
  private void makeDeletePanel() {
    removeShape = new JButton("Remove Shape");
    JLabel removeShapeLabel = new JLabel("EXISTING SHAPES");

    deletePanel = new JPanel();
    deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
    deletePanel.setBackground(Color.LIGHT_GRAY);

    createNewShapeList();
    shapeList.setFixedCellWidth(100);

    deletePanel.add(removeShapeLabel);
    deletePanel.add(shapeList);
    JScrollPane spShape = new JScrollPane(shapeList);
    spShape.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    spShape.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    deletePanel.add(spShape);
    deletePanel.add(removeShape);
  }

  /**
   * A helper to create a shape JList for the view according to what shapes are in the model.
   */
  private void createNewShapeList() {
    ArrayList<IShape> sList = model.getShapeList();
    ArrayList<String> sNameList = new ArrayList<String>();
    int sLength = 0;
    for (IShape s : sList) {
      if (!(sNameList.contains(s.getShapeName()))) {
        sNameList.add(s.getShapeName());
        sLength++;
      }
    }
    shapeListModel = new DefaultListModel();
    for (int i = 0; i < sLength; i++) {
      shapeListModel.addElement(sList.get(i).getShapeName());
      shapeArrayList.add(sList.get(i));
    }
    shapeList = new JList(shapeListModel);
  }

  /**
   * Finalizes setting the UI by setting the frame elements. Creates the scroll bar for the
   * animation and pack/resize the UI.
   */
  private void setAnimation() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Animation");

    // scrollbars
    JScrollPane scrollPane = new JScrollPane(this);
    scrollPane.setPreferredSize(new Dimension(model.getBorderW(), model.getBorderH()));
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    this.setPreferredSize(new Dimension(model.getRightMostX() + 200, model.getDownMostY() + 200));
    this.setLocation(model.getLeftMostX(), model.getUpMostY());

    frame.add(scrollPane, BorderLayout.CENTER);
    frame.setPreferredSize(new Dimension(2000, 1000));
    frame.pack();
    frame.setResizable(false);
  }

  @Override
  public void playAnimation() {
    frame.setVisible(true);
  }

  @Override
  public void changeLoopState() throws UnsupportedOperationException {
    this.isLooping = !isLooping;
  }

  @Override
  public void setButtonListener(ActionListener event) throws UnsupportedOperationException {
    playButton.addActionListener(event);
    pauseButton.addActionListener(event);
    restartButton.addActionListener(event);
    loopCheckbox.addActionListener(event);
    increaseSpeedButton.addActionListener(event);
    decreaseSpeedButton.addActionListener(event);
    modifyKFButton.addActionListener(event);
    removeKFButton.addActionListener(event);
    addKeyFrameButton.addActionListener(event);
    addShape.addActionListener(event);
    removeShape.addActionListener(event);
  }

  @Override
  public void restartTick() {
    this.currTick = 0;
  }

  @Override
  public void setTicksPerSec(int newTicksPerSec) {
    if (newTicksPerSec < 1) {
      JOptionPane
              .showMessageDialog(popUp, "The speed (in ticks per sec) cannot go low" +
                              "er than 1!", "Error",
                      JOptionPane.ERROR_MESSAGE);
    } else {
      this.ticksPerSecInput = newTicksPerSec;
      this.ticksPerSec = 1000 / newTicksPerSec;
      this.timer.stop();
      this.timer.setDelay(this.ticksPerSec);
      this.timer.start();
    }
  }

  @Override
  public int getTicksPerSec() throws UnsupportedOperationException {
    return this.ticksPerSecInput;
  }


  @Override
  public int getModTextT() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(modTextT.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getModTextX() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(modTextX.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getModTextY() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(modTextY.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getModTextW() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(modTextW.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getModTextH() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(modTextH.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getModTextR() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(modTextR.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getModTextG() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(modTextG.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getModTextB() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(modTextB.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getTTextKey() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(tTextKey.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getXTextKey() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(xTextKey.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getYTextKey() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(yTextKey.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getWTextKey() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(wTextKey.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getHTextKey() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(hTextKey.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getRTextKey() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(rTextKey.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getGTextKey() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(gTextKey.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getBTextKey() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(bTextKey.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public String getNameText() throws UnsupportedOperationException {
    return nameText.getText();
  }

  @Override
  public int getXText() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(xText.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getYText() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(yText.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getWText() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(wText.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getHText() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(hText.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getRText() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(rText.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getGText() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(gText.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public int getBText() throws UnsupportedOperationException {
    try {
      int i = Integer.parseInt(bText.getText());
      return i;
    } catch (NumberFormatException e) {
      throw new IllegalStateException("The input is not a number or there is no input!!");
    }
  }

  @Override
  public String getNameTextKey() throws UnsupportedOperationException {
    return nameTextKey.getText();
  }


  @Override
  public void addKeyFrame(IKeyFrame kf, String id) {
    model.addKeyFrame(kf, id);
    kfArrayList.add(kf);
    kfListModel.addElement(getKFInfo(kf));
  }


  @Override
  public void deleteShape(String shapeName) {
    model.deleteShapeAndMotionForShapeName(shapeName);

    for (int i = 0; i < kfListModel.size(); i++) {
      IKeyFrame kf = kfArrayList.get(i);
      if (kf.getShapeName().equals(shapeName)) {
        kfListModel.removeElementAt(i);
        kfArrayList.remove(kf);
        deleteKF(kf);
      }
    }
  }

  @Override
  public JList getShapeList() throws UnsupportedOperationException {
    return shapeList;
  }

  @Override
  public void deleteKF(IKeyFrame kf) throws UnsupportedOperationException {
    model.deleteKeyFrame(kf);
  }

  @Override
  public JRadioButton getRectButton() throws UnsupportedOperationException {
    return rectangleRadio;
  }

  @Override
  public JRadioButton getEllipseButton() throws UnsupportedOperationException {
    return ellipseRadio;
  }

  @Override
  public void addShape(IShape shape, String id) throws UnsupportedOperationException {
    model.addShape(shape, id);
    shapeListModel.addElement(shape.getShapeName());
    shapeArrayList.add(shape);
  }

  @Override
  public void modifyKF(int t, int x, int y, int w, int h, int r, int g, int b)
          throws UnsupportedOperationException {
    int i = kfList.getSelectedIndex();
    kfListModel.set(i, "KeyFrame" + (i + 1) + " \n" + this.getKFInfo(kfArrayList.get(i)));

    ShapeType shapeType = kfArrayList.get(i).getShapeType();
    String shapeName = kfArrayList.get(i).getShapeName();
    IKeyFrame kf = new KeyFrame(shapeName, t, x, y, w, h, r, g, b, shapeType);
    model.modifyKFHelper(i, kf);
  }

  @Override
  public DefaultListModel getShapeListModel() throws UnsupportedOperationException {
    return shapeListModel;
  }

  @Override
  public JList getKFList() {
    return kfList;
  }

  @Override
  public void removeKeyFrame(int i) {
    kfListModel.removeElementAt(i);
    kfArrayList.remove(i);
    deleteKF(kfArrayList.get(i));
  }

  @Override
  public void removeShape(int i) {
    deleteShape((String) shapeListModel.getElementAt(i));
    shapeListModel.removeElementAt(i);
  }

  @Override
  public ArrayList<IShape> getShapeArrayList() {
    return shapeArrayList;
  }

  @Override
  public boolean getLoopState() {
    return this.isLooping;
  }

  @Override
  public JButton getPlayButton() throws UnsupportedOperationException {
    return playButton;
  }


  @Override
  public JButton getPauseButton() throws UnsupportedOperationException {
    return pauseButton;
  }

  @Override
  public JButton getRestartButton() throws UnsupportedOperationException {
    return restartButton;
  }

  @Override
  public JButton getIncreaseSpeedButton() throws UnsupportedOperationException {
    return increaseSpeedButton;
  }

  @Override
  public JButton getDecreaseSpeedButton() throws UnsupportedOperationException {
    return decreaseSpeedButton;
  }

  @Override
  public JCheckBox getLoopCheckBox() throws UnsupportedOperationException {
    return loopCheckbox;
  }

  @Override
  public JButton getModifyKFButton() throws UnsupportedOperationException {
    return modifyKFButton;
  }

  @Override
  public JButton getRemoveKFButton() throws UnsupportedOperationException {
    return removeKFButton;
  }

  @Override
  public JButton getAddKFButton() throws UnsupportedOperationException {
    return addKeyFrameButton;
  }

  @Override
  public JButton getAddShapeButton() throws UnsupportedOperationException {
    return addShape;
  }

  @Override
  public JButton getRemoveShapeButton() throws UnsupportedOperationException {
    return removeShape;
  }

}
