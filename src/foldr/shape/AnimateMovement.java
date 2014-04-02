package foldr.shape;

import de.jreality.math.MatrixBuilder;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.tool.AbstractTool;
import de.jreality.scene.tool.InputSlot;
import de.jreality.scene.tool.ToolContext;

/**
 * A Jreality tool, which animates a shape an input distance over a set
 * amount of frames. Currently will only work for translation.
 * @author lizlevy
 *
 */
public class AnimateMovement extends AbstractTool {

	int currentFrame;
	// this variable determines the number of frames the animation will
	// occur in
	static int totalFramesForAnimation = 200;

	// the shape and scg that are being moved
	Shape shapeToMove;
	SceneGraphComponent sgcToMove;

	// the target coordinates
	double distanceToMoveX;
	double distanceToMoveY;
	double distanceToMoveZ;

	// the interval each coordinate will change every frame
	double intervalToMoveX;
	double intervalToMoveY;
	double intervalToMoveZ;

	// the new coordinates to translate the shape to.
	double newX;
	double newY;
	double newZ;

	// the original coordinates of the shape
	double originalX;
	double originalY;
	double originalZ;
	
	private final InputSlot TIME = InputSlot.SYSTEM_TIME;

	public AnimateMovement() {
		addCurrentSlot(TIME);

	}
	
	public void setEndPoints(Shape newShapeToMove, double[] newEndPoints) {
		
		shapeToMove = newShapeToMove;
		sgcToMove = shapeToMove.getShapeSGC();

		//reset the current frame
		currentFrame = 0;
		
		//store the original coordinates
		originalX = sgcToMove.getTransformation().getMatrix()[3];
		originalY = sgcToMove.getTransformation().getMatrix()[7];
		originalZ = sgcToMove.getTransformation().getMatrix()[11];
		
		// set the distance to move for each coordinate
		distanceToMoveX = newEndPoints[0];
		distanceToMoveY = newEndPoints[1];
		distanceToMoveZ = newEndPoints[2];

		// calculate the interval to change each coordinate every frame
		intervalToMoveX = distanceToMoveX
				/ (double) totalFramesForAnimation;
		intervalToMoveY = distanceToMoveY
				/ (double) totalFramesForAnimation;
		intervalToMoveZ = distanceToMoveZ
				/ (double) totalFramesForAnimation;
	}

	@Override
	public void perform(ToolContext tc) {
		// check if we've looped through the correct number of frames
		if (currentFrame == totalFramesForAnimation) {
			double errorX = sgcToMove.getTransformation().getMatrix()[3] - (originalX + distanceToMoveX);
			double errorY = sgcToMove.getTransformation().getMatrix()[7] - (originalY + distanceToMoveY);
			double errorZ = sgcToMove.getTransformation().getMatrix()[11] - (originalZ + distanceToMoveZ);
			System.out.println("X error: " + errorX + ", Y error: " + errorY + ", Z error: " + errorZ);
			
			//MatrixBuilder.euclidean().translate(1, 1, 1).assignTo(sgcToMove);
			
			System.out.println("goal reached!");
			shapeToMove.inMotion = false;
			// animation is done, so remove this tool from the shape
			sgcToMove.removeTool(this);
		} else {
			// update the new coordinates
			newX = intervalToMoveX * currentFrame;
			newY = intervalToMoveY * currentFrame;
			newZ = intervalToMoveZ * currentFrame;

			// translate the shape
			MatrixBuilder.euclidean().translate(originalX + newX, originalY + newY, originalZ + newZ)
					.assignTo(sgcToMove);

			currentFrame++;
		}

	}

}