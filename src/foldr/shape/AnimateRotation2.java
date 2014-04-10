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
 * @author Ellery Addington-White
 * 
 * 
 */
public class AnimateRotation2 extends AbstractTool {
	int currentFrame = 0;
	// this variable determines the number of frames the animation will
	// occur in
	int totalFramesForAnimation = 200;

	// the shape and scg that are being moved
	Shape shapeToMove;
	SceneGraphComponent sgcToMove;

	// the interval the shape will rotate every frame
	double intervalToRotate;
	// Which plane the shape is being rotated in
	char planeOfRotation;
	double[] vertexToMatch1;
	double[] vertexToMatch2;

	// the new coordinates to translate the shape to. These start as equal
	// to the current coordinates.
	double newX;
	double newY;
	double newZ;

	private final InputSlot TIME = InputSlot.SYSTEM_TIME;

	public AnimateRotation2() {
		addCurrentSlot(TIME);

	}

	/**
	 * This method will rotate the shape along an axis defined by the two points
	 * given.
	 * 
	 * @param newShapeToMove
	 * @param angleToRotate
	 *            Use Radians!
	 * @param vertexToMatch1
	 * @param vertexToMatch2
	 */
	public void setEndPoints(Shape newShapeToMove, double angleToRotate,
			double[] vertexToMatch1, double[] vertexToMatch2) {
		shapeToMove = newShapeToMove;
		sgcToMove = shapeToMove.getShapeSGC();
		this.vertexToMatch1 = vertexToMatch1;
		this.vertexToMatch2 = vertexToMatch2;

		intervalToRotate = angleToRotate / (double) totalFramesForAnimation;
		System.out.println(intervalToRotate);
		currentFrame = 0;

	}

	@Override
	public void perform(ToolContext tc) {

		// check if we've looped through the correct number of frames
		if (currentFrame == totalFramesForAnimation) {
			System.out.println("goal reached!");
			shapeToMove.inMotion = false;
			// animation is done, so get remove this tool from the shape
			sgcToMove.removeTool(this);
		} else {
			MatrixBuilder
					.euclidean()
					.rotate(vertexToMatch1, vertexToMatch2,
							intervalToRotate * currentFrame)
					.assignTo(sgcToMove);

			currentFrame++;
		}

	}

}
