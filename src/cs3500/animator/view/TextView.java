package cs3500.animator.view;

import cs3500.animator.util.AnimationReader;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
import model.IModel;
import model.Model.Builder;
import model.keyframe.IKeyFrame;
import model.shape.IShape;

/**
 * The abstract class for the View that is considered textual. This includes the TextualView and the
 * SVG View. This class holds the common elements for Views that are considered textual. The model,
 * the text output, and the file that the text is outputted to (if given).
 */
public abstract class TextView implements IView {

  protected IModel model;
  protected String textDescription;
  protected FileWriter newFile;

  /**
   * Constructs the abstract class TextView with a String for the file name. The file should provide
   * information on the model.
   *
   * @param fileName the file name with the model information in String form
   */
  protected TextView(String fileName) {
    this.model = this.readFile(fileName);
  }

  /**
   * Constructs the abstract class TextView with a String for the file name for the model
   * information and the output file (location).
   *
   * @param fileName the file name with the model information in String form
   * @param newFile the file (location) for the output text
   */
  protected TextView(String fileName, FileWriter newFile) {
    this.model = this.readFile(fileName);
    this.newFile = newFile;
  }


  @Override
  public void playAnimation() {
    if (newFile == null) {
      System.out.println(textDescription);
    } else {
      try {
        newFile.write(textDescription);
        newFile.close();
      } catch (IOException e) {
        final JPanel panel = new JPanel();
        JOptionPane
            .showMessageDialog(panel, "Error creating a file!", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  @Override
  public String getTextDescription() throws UnsupportedOperationException {
    return this.textDescription;
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
  public void changeLoopState() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public void setButtonListener(ActionListener event) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public Timer getTimer() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
  }

  @Override
  public void restartTick() throws UnsupportedOperationException {
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
  public IModel getModel() {
    return model;
  }

  @Override
  public String getNameTextKey() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This view does not support this operation!");
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

}
