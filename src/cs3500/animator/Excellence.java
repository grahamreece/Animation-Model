package cs3500.animator;

import static java.lang.Integer.parseInt;

import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The class that holds the main method to play an animation according to the given input, chosen
 * view type, and possibly the animation speed and/or the output location. This is given by the user
 * with command-line arguments. The main method plays the animation or outputs the animation in text
 * format without any additional inputs from the user. If the animation speed is not specified, the
 * animation speed is set to 1 tick per second. If the output is not specified, the output is set to
 * system.out.
 */
public final class Excellence {

  /**
   * The entry point for the animation program. The animation program takes in command-line
   * arguments which determines the animation and the view type. The given commands must include an
   * -in for the input (file) and a -view for the view type. The commands may include -speed and/or
   * -out that determines the animation speed and the output (location/file) for the animation. The
   * view, viewType, builtModel, and newFile which are created as variables in this method can never
   * be null since they are composed of mandatory command-line inputs.
   *
   * @param args the String arguments in a list that determines the animation
   */
  public static void main(String[] args) {

    final JPanel panel = new JPanel();

    boolean containsView = false;
    boolean containsInput = false;

    String inputFile = null;
    FileWriter newFile = null;
    int ticksPerSec = 0;
    IView view = null;

    // check if the args contain view and in
    for (int i = 0; i < args.length; i += 2) {
      if (args[i].equals("-view")) {
        containsView = true;
      }
      if (args[i].equals("-in")) {
        containsInput = true;
      }
    }

    // if the args don't contain view and in, throw an error
    if (!(containsInput && containsView)) {
      JOptionPane
          .showMessageDialog(panel, "Command-line arguments are invalid! It doesn't contain both"
                  + " the view and the input!", "Error",
              JOptionPane.ERROR_MESSAGE);
      return;
    }

    // sets the inputFile
    for (int i = 0; i < args.length; i += 2) {
      if (args[i].equals("-in")) {
        inputFile = args[i + 1];
      }
    }

    // sets the output location as newFile
    for (int i = 0; i < args.length; i += 2) {
      if (args[i].equals("-out")) {
        String outName = args[i + 1];
        try {
          newFile = new FileWriter(outName);
          break;
        } catch (IOException e) {
          JOptionPane
              .showMessageDialog(panel, "Error creating a file!", "Error",
                  JOptionPane.ERROR_MESSAGE);
          return;
        }
      }
    }

    // sets the speed as ticksPerSec
    for (int i = 0; i < args.length; i += 2) {
      if (args[i].equals("-speed")) {
        String speed = args[i + 1];
        try {
          ticksPerSec = parseInt(speed);
          break;
        } catch (NumberFormatException e) {
          JOptionPane
              .showMessageDialog(panel, "Error in reading the given speed!", "Error",
                  JOptionPane.ERROR_MESSAGE);
          return;
        }
      }
    }

    // sets the view with the given view type
    for (int i = 0; i < args.length; i += 2) {
      if (args[i].equals("-view")) {
        String givenViewType = args[i + 1];
        ViewFactory viewFactory = new ViewFactory();
        view = viewFactory.createView(givenViewType, newFile, ticksPerSec, inputFile);
        break;
      }
    }

    // outputs the animation by playing the animation or outputting the text description
    try {
      view.playAnimation();
    } catch (NullPointerException e) {
      JOptionPane
          .showMessageDialog(panel, "The view is not initialized!", "Error",
              JOptionPane.ERROR_MESSAGE);
      return;
    }
  }

}