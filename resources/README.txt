The implementation of the animation model is represented in the IModel interface. The IModel
interface represents an animation that includes motions of shapes. The model class represents a
simple implementation of the IModel interface. The model representation includes a list of shapes
and a list of motions. The motions include the motions for the shapes in the model. A motion for a
specific shape cannot exist without the specific shape already in the model. The model animates and
draws the shapes and their motions at every tick to create an animation.

The IMotion interface represents a motion for a shape. The motion class represents a simple
implementation of the IMotion interface. A motion includes the starting and end tick, the starting
shape to represent the state of the shape at the starting tick, and the ending shape to represent
the state of the shape at the ending tick. A motion also includes a motion ID, so that the model
can identify a specific motion at any time and can check if a motion already exists in the model.

The IShape interface represents a shape. A shape is determined with it's shape type, position,
dimension, color, the shape's name, and the shape ID. Every shape in the model requires all these
elements. The position represents the x and y coordinates of the shape. The dimension represents
the width and height of the shape. The color represents the RGB color of the shape. The shape name
groups all the different states of shapes with different shape identifiers together. This is because
the motion includes two shapes, with the same shape name but different shape identifiers (because
the shape will change throughout the motion). The shape identifier makes every shape unique, so that
a shape can go through different states in an animation/motion.

The AShape abstract class represents the abstracted implementation of the IShape interface. AShape
holds the common elements for every shape, including the type of shape, dimension, position, color,
shape name, and a shape ID. It also holds a constructor for all of these elements.

The ShapeType enum is created to identify different extensions of the abstract AShape class. It is
used to identify different (types of) shapes when they are implemented.

The MyRectangle class represents an extension of the AShape abstract class. This class holds a 2D
class representation of a rectangle. The class initializes the shape type to be an enumeration of
a RECTANGLE.

The MyEllipse class represents an extension of the AShape abstract class. This class holds a 2D
class representation of a ellipse. The class initializes the shape type to be an enumeration of
a ELLIPSE.

Each class implementation, including the abstract class, holds getters and setters for every field.
This is done because the model, motion, and shapes should be able to access these elements without
having to call field for field. Additionally, the fields should either private or protected to
avoid alterations of fields and to improve encapsulation. This is also done so that the viewer and
controller can also access these elements (viewer and controller will be implemented in the future).

We were provided with limited knowledge on the animation. Thus, we did not implement the actual
drawings of the shapes, motions, and the model. The drawing requires a controller to be
animated/drawn and to use the graphics. The information we were given is that the model draws
all the shapes and motions at every tick, to build an animation. These are all documented in the
code via javaDoc.

**** CHANGES FOR ASSIGNMENT 6 ****

We've implemented code that denies the user from deleting a motion or shape that causes
inconsistency in the model. Additionally, we made it so that when the user adds a shape or motion,
it should keep the motions in the model/animation consistent. In other words, a motion or shape
cannot be added to the model or deleted from it if it causes time interval gaps, and a motion or
shape must have matching end and start states. This means that for consecutive motions, we now
have implemented code that checks for the model that the end shape of the first motion is the same
Shape as the start shape of the next consecutive motions. This helped to provide funcionality for
our model to ensure that shapes and motions were consistent throughout the model.

A small change to our code also came with the realization that the tick and width and height are
actually bounded by zero and not one, and so we ensured that ticks for our model's motions were
always greater than or equal to zero, and that shapes for our model always have widths and heights
greater than or equal to zero.

We created new fields in the model to get the size of the canvas for the animation. We also created
getters and setters for these fields accordingly.

With the addition of the view interface and its implementations, we needed to add functionality to
our model, motion, and shape interfaces and classes to accommodate some of the functionality we
needed for the views.

Our model class now contains a builder class which implements the utility Animation
Builder class code that we were given. This class adapts the Animation Builder utils class to our
model, and builds a new Model, sets the bounds for animations for our model, and declares shapes and
motions for our model. The addkeyframe method for the Animation Builder utility class was not useful
for our code implementation, and therefore we did not adapt this method for our model.

In order to set the bounds for animations for our model, as described in the above paragraph, we
needed to add new fields to our model class in order to store information for these bounds, and to
set them with the utility builder class. These fields are leftMostX, upMostY, borderW, and borderH,
and they are initialized to zero but are set with the builder class using the setBounds method. To
go along with this addition to our code, we added getters and setters for these fields, as we saw it
useful for other classes to utilize these fields, and set them if they needed to. In addition, we
needed to ensure that the width and height of the animations for our model were not negative, and so
we ensured that this could not be the case for our model's border width and height.

With these additions to our model, and some minor methods added such as the getInitShape method, our
model was able to accommodate functionality for our view interface.

The changes that needed to be made to our shape interface because of the view classes came because
of the addition of the SVG view class. Because of the syntax and style of the SVG view, we needed to
add methods to return Strings for the different shapes based on what type they were and what their
elements are to accommodate declaring a shape in an SVG style animation. Additionally, we needed to
add a method to return a string for the closing SVG annotation for the different shapes. Finally,
we added a method that used the shape's elements and information needed for an SVG animation to
return a string representing an animation on shapes for SVG animations in the shape interface, which
was needed to return the correct text output for the SVG view.

The changes that needed to be made to our motion interface because of the addition of the view
interface and classes came because of the addition of the visual and SVG views. Because the visual
view needed to get the state of a shape when it is in the middle of a motion/animation, we were now
able to implement the getShapeAtTick method that we had not originally fully implemented in the last
assignment for our motion interface and class. To implement it, we had a helper to calculate
"tweened" values for the attributes of the shape, and we returned the new shape with the "tweened"
values when getting the shape at the certain tick in time. Also, our motion interface and
class implementation added a variety of methods to accommodate the SVG view. We added methods to
check which elements changed for the shape in between states of a motion, because this represents
the declaration of what needed to be animated in the SVG view. We also added methods to motion to
output the correct String for the SVG view of an animation occurring in our code, and we added
methods to motion to output the correct String of making motions an shapes hidden until they
actually moved and had motions for our SVG view.

We added the IView interface to represent a view for our Excellence animator. The three classes that
implement this IView interface are SVGView, TextualView, and VisualView. These three classes
represent the three different view functionalities that we wish to provide for now.

The abstract class TextView are extended by SVGView and TextualView. It holds the common elements
for views that are text based. SVGView and TextualView share same code, so the abstract class helps
reduce repeated code and for structure management.

The Main class, Excellence, is able to take in command-line arguments. The given commands must
include an -in for the input (file) and a -view for the view type. The commands may include -speed
and/or -out that determines the animation speed and the output (location/file) for the animation.
If the speed is not given, the speed is set to 1 tick per sec. If the output is not given, the
output is set to system.out.

The textual view shows the textual description of the animation. The textual description of an
animation holds the canvas location and size. It declares shapes with the shape's name and shape
type. It declares motions for the shapes with the starting tick, end tick, and the elements for
the motion's start shape and end shape. The textual view ignores the speed, since the output is
only a text and cannot be played.

The SVG View shows an animation in SVG file format. The view writes the text code for the SVG
file, which can be played by the user through other animation frameworks (such as Flash). The
SVG file format is an XML-based format that can be used to describe images and animations.

The Visual View plays an animation in a new window which can be exited. The animation should play
without any new inputs by the user. When the animation ends, the window doesn't exit, and continues
showing a blank white canvas.

**** CHANGES FOR ASSIGNMENT 7 ****

We first created a new class for a keyframe, and a interface for it. We made it so that a keyframe
holds the tick, shape, shape type, shape name, shape position, shape size, shape color, and the
KeyFrame ID. A keyframe can be a replacement for a motion, since it can be used to describe and
show a motion from a beginning keyframe (for the shape) to the ending keyframe. We then implemented
several getters and setters for this class. We included an ArrayList of keyframes in the model,
just like how we added ArrayLists of shapes and motions. When building a model, we made it so that
for every motion that is being added to the model, 2 keyframes are added to the model, depending
on the motion's start tick, end tick, starting shape, and ending shape. A KeyFrame has the same
ID functionality as motions and shapes.

We created a new view for representing a visual view where the user can modify the animation through
a UI. We created the EditorView class that extends VisualView, since it has several common elements
and functionalities, such as showing a visual animation, holding a timer, being able to change the
speed of the animation, and the fact that they both don't utilize texts for the animation output.
We added different functionalities to this view. The functionalities include a playback, the ability
to add/delete shapes and/or keyframes, and modifying keyframes. A playback includes playing,
pausing, restarting, looping, increasing the speed, and decreasing the speed of the animation.
The user should be able to easily identify the steps on achieving this functionalities by looking
at the UI. The UI throws popup error messages to the user if the input is incorrect, or there is
no inputs for the required elements. For example, if there is no input for the shape name when
trying to add a keyframe, the UI will throw an error. We added buttons for deleting, adding, and
modifying the shapes and keyframes.

We created a controller that mediates the view and the model depending on the user interactions and
inputs. This includes all the buttons and inputs in the JTextField. The controller mediates all
functionalities of the EditorView functionalities. The controller acts as the ActionListener for
the UI components of the EditorView. The EditorView connects all of it's buttons to the controller
as action events.

We did not have to modify the existing views. However, we did add several new methods to the IView
interface that were utilized almost exclusively by the new editor view class that we made, and thus
we had to add these methods to our already implemented views, where they they exclusively threw
Unsupported Operation exceptions if they did not support the method's functionality that they
contained when they were implemented in the editor view class.We added new methods for IModel in
order to accommodate the EditorView and it's new functionalities (of adding, deleting, and
modifying shapes and keyframes).

We added a new command line option for -view (view type) for the EditorView as "edit". This will
create a EditorView as the view type.