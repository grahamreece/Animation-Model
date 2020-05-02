
import cs3500.animator.controller.Controller;
import cs3500.animator.controller.IController;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.IView;
import cs3500.animator.view.VisualView;


public final class testMain {
  public static void main(String[] args) {

    EditorView test = new EditorView("resources/givenCodeForTest/buildings.txt", 20);
    IController cont = new Controller( (IView) test);
    test.playAnimation();
   // System.out.println(test.forTest());
  }
}
