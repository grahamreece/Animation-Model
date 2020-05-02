package cs3500.animator.view;

import java.io.FileWriter;
import java.util.ArrayList;

import model.motion.IMotion;
import model.shape.IShape;

/**
 * The view that shows an animation in SVG file format. The view writes the text code for the SVG
 * file, which can be played by the user through other animation frameworks (such as Flash). The SVG
 * file format is an XML-based format that can be used to describe images and animations. This view
 * is purely a text representation, and formats only formatted text.
 */
public class SVGView extends TextView {

  private int ticksPerSec = 1;

  /**
   * Constructs an SVGView with a String for the file name for the model information.
   *
   * @param fileName the file name with the model information in String form
   */
  public SVGView(String fileName) {
    super(fileName);
    this.textDescription = this.textualize();
  }

  /**
   * Constructs an SVGView with  a String for the file name for the model information and the given
   * speed (in ticks per second).
   *
   * @param fileName    the file name with the model information in String form
   * @param ticksPerSec the speed of the animation in ticks per sec
   * @throws IllegalArgumentException if the given speed is 0 or less
   */
  public SVGView(String fileName, int ticksPerSec) throws IllegalArgumentException {
    super(fileName);
    if (ticksPerSec < 1) {
      throw new IllegalArgumentException("Ticks per second cannot 0 or less!");
    }
    this.ticksPerSec = ticksPerSec;
    this.textDescription = this.textualize();
  }

  /**
   * Constructs an SVGView with  a String for the file name for the model information and the output
   * file (location).
   *
   * @param fileName the file name with the model information in String form
   * @param newFile  the file (location) for the output text
   */
  public SVGView(String fileName, FileWriter newFile) {
    super(fileName, newFile);
    this.textDescription = this.textualize();
  }

  /**
   * Constructs an SVGView with  a String for the file name for the model information, the given
   * speed (in ticks per second), and the output file (location).
   *
   * @param fileName    the file name with the model information in String form
   * @param ticksPerSec the speed of the animation in ticks per sec
   * @param newFile     the file (location) for the output text
   * @throws IllegalArgumentException if the given speed is 0 or less
   */
  public SVGView(String fileName, FileWriter newFile, int ticksPerSec) {
    super(fileName, newFile);
    if (ticksPerSec < 1) {
      throw new IllegalArgumentException("Ticks per second cannot be 0 or less!");
    }
    this.ticksPerSec = ticksPerSec;
    this.textDescription = this.textualize();
  }

  /**
   * Returns the text for the animation in SVG file format and in XML-based format. The text should
   * first declare the size of the SVG animation canvas and the version of the SVG. For every
   * declaration of a shape, it include the shape's name, position, size, and color. The shape is
   * set to visible. However, the shape's visibility is set to invisible until the first motion is
   * active. A motion is declared with animate, containing the element of the shape's motion, the
   * start time of the motion, and the duration of the motion.
   *
   * @return the textual description of the animation
   */
  private String textualize() {
    StringBuilder text = new StringBuilder();

    text.append(
            "<svg width=\"" + (model.getRightMostX() + 200) + "\" height=\"" + (model.getDownMostY()
                    + 200)
                    + "\" version=\"1.1\"\n"
                    + "     xmlns=\"http://www.w3.org/2000/svg\">\n\n");

    ArrayList<IMotion> modelMotions = model.getMotionList();

    ArrayList<String> shapeNames = new ArrayList<String>();

    for (IMotion motion : modelMotions) {
      String currInitShapeName = motion.getStartingShapeName();

      if (!(shapeNames.contains(currInitShapeName))) {
        shapeNames.add(currInitShapeName);
      }
    }

    ArrayList<IShape> initShapes = new ArrayList<IShape>();
    for (String shapeName : shapeNames) {
      IShape shape = model.getInitShape(shapeName);
      if (!(initShapes.contains(shape))) {
        initShapes.add(shape);
      }
    }

    int counter = 0;

    for (IShape shape : initShapes) {
      text.append(shape.getShapeSVGText() + "\n");

      for (IMotion motion : modelMotions) {
        if (motion.containsShape(shape)) {
          if (motion.hasSVGMotion(motion.getStartShape(), motion.getEndShape())) {
            if (counter == 0) {
              text.append(motion.makeMotionHidden(this.ticksPerSec));
              text.append(motion.getMotionSVGText(this.ticksPerSec) + "\n");
              counter++;
            } else {
              text.append(motion.getMotionSVGText(this.ticksPerSec) + "\n");
            }
          } else {
            if (counter == 0) {
              text.append(motion.makeMotionHiddenNoSVG(this.ticksPerSec));
              text.append(motion.getMotionSVGText(this.ticksPerSec) + "\n");
              counter++;
            }
          }
        }
      }

      text.append(shape.getShapeClosingSVG() + "\n\n");
      counter = 0;
    }

    text.append("</svg>");

    return text.toString();
  }

}
