package cs3500.animator.view;

import java.io.FileWriter;

/**
 * The view that resembles the textual description of the animation. The textual description of an
 * animation holds the canvas location and size. It declares shapes with the shape's name and shape
 * type. It declares motions for the shapes with the starting tick, end tick, and the elements for
 * the motion's start shape and end shape. The user should be able to interpret what the animation
 * is purely based off the textual description.
 */
public class TextualView extends TextView {

  /**
   * Constructs a TextualView with a String for the file name for the model information.
   *
   * @param fileName the file name with the model information in String form
   */
  public TextualView(String fileName) {
    super(fileName);
    this.textDescription = this.textualize();
  }

  /**
   * Constructs a TextualView with a String for the file name for the model information and the
   * output file (location).
   *
   * @param fileName the file name with the model information in String form
   * @param newFile the file (location) for the output text
   */
  public TextualView(String fileName, FileWriter newFile) {
    super(fileName, newFile);
    this.textDescription = this.textualize();
  }

  /**
   * Returns the textual description of the animation. The textual description of an animation
   * contains the canvas width, height, and the canvas x and y positions. The canvas is considered
   * the viewing window that the user can scroll (if viewed by the visual animation). The textual
   * description also holds the declaration of shapes (shape name and shape type), and the
   * declaration of motions (the shape that the motion is being applied to, starting and ending
   * tick, and all the elements for the stating shape and ending shape).
   *
   * @return the textual description of the animation
   */
  private String textualize() {
    return "canvas " + model.getLeftMostX() + " " + model.getUpMostY() + " " +
        model.getBorderW() + " " + model.getBorderH() + "\n" + model.textRenderOutput();
  }

}
