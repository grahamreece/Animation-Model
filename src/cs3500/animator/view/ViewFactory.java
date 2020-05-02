package cs3500.animator.view;

import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;

import java.io.FileWriter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * A factory class that implements a factory of views. Contains a single method that takes in a
 * String name for a view, “text”, “svg”, “visual”, or "edit" and constructs an instance of the
 * appropriate concrete view. The method also takes in the output file location, the speed of the
 * animation, and the input file with the model information for the animation. This class should be
 * used in the Excellence main method to create a view for the given input and view type (and
 * possibly the animation speed and output location).
 */
public class ViewFactory {

  /**
   * Creates a view according to the inputted String “text”, “svg”, or “visual”, the output file
   * location, the speed of the animation, and the input file with the model information.
   *
   * @param str         the String that determines the view type
   * @param newFile     the output file (location) of the animation
   * @param ticksPerSec the speed of the animation in ticks per sec
   * @param inputFile   the input file with the model information for the animation
   */
  public static IView createView(String str, FileWriter newFile, int ticksPerSec,
                                 String inputFile) {
    final JPanel panel = new JPanel();
    IView view = null;

    try {
      switch (str) {
        case "text":
          if (newFile == null) {
            return new TextualView(inputFile);
          } else {
            return new TextualView(inputFile, newFile);
          }
        case "visual":
          if (ticksPerSec == 0) {
            return new VisualView(inputFile);
          } else {
            return new VisualView(inputFile, ticksPerSec);
          }
        case "svg":
          if (newFile == null) {
            if (ticksPerSec == 0) {
              return new SVGView(inputFile);
            } else {
              return new SVGView(inputFile, ticksPerSec);
            }
          } else {
            if (ticksPerSec == 0) {
              return new SVGView(inputFile, newFile);
            } else {
              return new SVGView(inputFile, newFile, ticksPerSec);
            }
          }
        case "edit":
          if (ticksPerSec == 0) {
            IView editorView = new EditorView(inputFile);
            IController cont = new Controller(editorView);
            return editorView;
          } else {
            IView editorView = new EditorView(inputFile, ticksPerSec);
            IController cont = new Controller(editorView);
            return editorView;
          }
        default:
          return view;
      }
    } catch (NullPointerException e) {
      JOptionPane
              .showMessageDialog(panel, "The String name for a view is null!", "Error",
                      JOptionPane.ERROR_MESSAGE);
      return view;
    }
  }

}
