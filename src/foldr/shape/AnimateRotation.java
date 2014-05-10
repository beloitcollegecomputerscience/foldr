package foldr.shape;

import de.jreality.math.MatrixBuilder;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.tool.AbstractTool;
import de.jreality.scene.tool.InputSlot;
import de.jreality.scene.tool.ToolContext;

/**
 * A Jreality tool, which animates a shape an inputed degree of rotation in an
 * inputed plane over a set amount of frames.
 * 
 * @author Ellery Addington-White
 * 
 *         Bugs: Is it possibly the rotate methods are using raidians as the
 *         degree to rotate? No Documentation.
 * 
 */
public class AnimateRotation extends AbstractTool {
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

	// the new coordinates to translate the shape to. These start as equal
	// to the current coordinates.
	double newX;
	double newY;
	double newZ;

	private final InputSlot TIME = InputSlot.SYSTEM_TIME;

	public AnimateRotation() {
		addCurrentSlot(TIME);

	}

	/**
	 * Rotates a shape on a given axis a given amount.
	 * 
	 * @param newShapeToMove
	 *            the shape to rotate
	 * @param angleToRotate
	 *            the amount to rotate
	 * @param plane
	 *            the plane in which to rotate the shape.
	 */
	public void setEndPoints(Shape newShapeToMove, double angleToRotate,
			char plane) {
		shapeToMove = newShapeToMove;
		sgcToMove = shapeToMove.getShapeSGC();

		planeOfRotation = plane;
		intervalToRotate = angleToRotate / (double) totalFramesForAnimation;
		System.out.println(intervalToRotate);
		currentFrame = 0;

	}

	/**
	 * Performs the action on the shape this tool was applied to.
	 */
	@Override
	public void perform(ToolContext tc) {

		// check if we've looped through the correct number of frames
		if (currentFrame == totalFramesForAnimation) {
			System.out.println("goal reached!");
			shapeToMove.inMotion = false;
			// animation is done, so get remove this tool from the shape
			sgcToMove.removeTool(this);
		} else {

			// Rotate the shape
			if (planeOfRotation == 'x') {
				MatrixBuilder
						.euclidean()
						.rotateX(intervalToRotate * currentFrame)
						.translate(shapeToMove.translationTransformation[0],
								shapeToMove.translationTransformation[1],
								shapeToMove.translationTransformation[2])
						.assignTo(sgcToMove);
			} else if (planeOfRotation == 'y') {
				MatrixBuilder
						.euclidean()
						.rotateY(intervalToRotate * currentFrame)
						.translate(shapeToMove.translationTransformation[0],
								shapeToMove.translationTransformation[1],
								shapeToMove.translationTransformation[2])
						.assignTo(sgcToMove);
			} else {
				MatrixBuilder
						.euclidean()
						.rotateZ(intervalToRotate * currentFrame)
						.translate(shapeToMove.translationTransformation[0],
								shapeToMove.translationTransformation[1],
								shapeToMove.translationTransformation[2])
						.assignTo(sgcToMove);
			}

			currentFrame++;
		}

	}

}
