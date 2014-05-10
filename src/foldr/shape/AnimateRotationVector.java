package foldr.shape;

import de.jreality.math.MatrixBuilder;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.tool.AbstractTool;
import de.jreality.scene.tool.InputSlot;
import de.jreality.scene.tool.ToolContext;

/**
 * A Jreality tool, which animates a shape an inputed degree of rotation along
 * an axis defined by the two points given.
 * 
 * Note: Attempt to have rotation working with vectors and animate. Did not end
 * up working as well as I had hoped.
 * 
 * @author Ellery Addington-White
 * 
 * 
 */
public class AnimateRotationVector extends AbstractTool {

	int currentFrame = 0;
	// this variable determines the number of frames the animation will
	// occur in
	int totalFramesForAnimation = 200;

	// the shape and scg that are being moved
	Shape shapeToMove;
	SceneGraphComponent sgcToMove;

	// the interval the shape will rotate every frame
	double intervalToRotate;

	// The vertex's that will create the axis to rotate around
	double[] vectorToMatch1;
	double[] vectorToMatch2;

	// the new coordinates to translate the shape to. These start as equal
	// to the current coordinates.
	double newX;
	double newY;
	double newZ;

	private final InputSlot TIME = InputSlot.SYSTEM_TIME;

	public AnimateRotationVector() {
		addCurrentSlot(TIME);

	}

	public void setEndPoints(Shape newShapeToMove, double[] cordinate1,
			double[] cordinate2, double[] cordinate3, double[] cordinate4) {
		shapeToMove = newShapeToMove;
		sgcToMove = shapeToMove.getShapeSGC();

		// Create converter object
		Converter myConvert = new Converter();

		// Convert given points to usable vectors
		double[] vector1 = myConvert.convertPointsToVectorOnOrigin(cordinate1,
				cordinate2);
		double[] vector2 = myConvert.convertPointsToVectorOnOrigin(cordinate3,
				cordinate4);

		// Set vectors for this instance
		this.vectorToMatch1 = vector1;
		this.vectorToMatch2 = vector2;

		// Set current frame to 0 TODO animation not working
		currentFrame = 0;

	}

	@Override
	public void perform(ToolContext tc) {

		// check if we've looped through the correct number of frames
		if (currentFrame == totalFramesForAnimation) {
			System.out.println("goal reached in vector!");
			shapeToMove.inMotion = false;
			// animation is done, so get remove this tool from the shape
			sgcToMove.removeTool(this);
		} else {
			// Build animation matrix and
			// MatrixBuilder myMatrix;
			// // First Translate object to origin
			// myMatrix = MatrixBuilder.euclidean().translate(
			// -shapeToMove.translationTransformation[0],
			// -shapeToMove.translationTransformation[1],
			// -shapeToMove.translationTransformation[2]);
			//
			// // Loop through and apply vector rotations
			// for (int i = 0; i < shapeToMove.rotationList.size(); i++) {
			// myMatrix = MatrixBuilder.euclidean().rotateFromTo(
			// shapeToMove.rotationList.elementAt(i),
			// shapeToMove.rotationList.elementAt(i + 1));
			// }
			//
			// myMatrix = MatrixBuilder.euclidean().rotateFromTo(
			// shapeToMove.rotationList.elementAt(shapeToMove.rotationList
			// .size()), vectorToMatch2);
			// // move Shape back to location
			// myMatrix = MatrixBuilder.euclidean().translate(
			// shapeToMove.translationTransformation[0],
			// shapeToMove.translationTransformation[1],
			// shapeToMove.translationTransformation[2]);
			//
			// myMatrix.assignTo(sgcToMove);
			//
			// shapeToMove.rotationList.add(vectorToMatch2);

			System.out.println("hit this here!");
			currentFrame++;
		}

	}
}
