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

//TODO instead of going by # frames, needs to have a target point and rotate till the vertex we're checking reaches that point!!`
public class AnimateEdgeRotation extends AbstractTool {

	int currentFrame;
	double error = .01;
	// the shape and scg that are being moved
	Shape shapeToMove;
	SceneGraphComponent sgcToMove;

	double[] vertexOne;
	double[] vertexTwo;
	int vertexToCheck;
	// the new angle to rotate the shape to.
	double newAngle;

	double intervalAngleToRotate;
	
	double[] goalCoordinates;
	
	private final InputSlot TIME = InputSlot.SYSTEM_TIME;

	public AnimateEdgeRotation() {
		addCurrentSlot(TIME);

	}
	
	public void setInternalVariables(Shape newShapeToMove, double[] vOne, double[] vTwo, double[] goal, int goalVertex) {
		
		
		shapeToMove = newShapeToMove;
		sgcToMove = shapeToMove.getShapeSGC();
		//reset the current frame
		currentFrame = 0;
		vertexToCheck = goalVertex;
		vertexOne = vOne;
		vertexTwo = vTwo;
		// calculate the interval to change each coordinate every frame
		intervalAngleToRotate = .001;

		goalCoordinates = goal;
	}

	@Override
	public void perform(ToolContext tc) {
		// check if we've looped through the correct number of frames
		if (Math.abs(shapeToMove.getCurrentVertexCoordinates(vertexToCheck)[0] - goalCoordinates[0]) < error && Math.abs(shapeToMove.getCurrentVertexCoordinates(vertexToCheck)[1] - goalCoordinates[1]) < error && Math.abs(shapeToMove.getCurrentVertexCoordinates(vertexToCheck)[2] - goalCoordinates[2]) < error) {
				
			System.out.println("goal reached!");
			shapeToMove.inMotion = false;
			// animation is done, so remove this tool from the shape
			sgcToMove.removeTool(this);
		} else {
			// update the new coordinates
			newAngle = intervalAngleToRotate * currentFrame;
			// rotate the shape and reapply any translation
			MatrixBuilder.euclidean().rotate(vertexOne, vertexTwo, newAngle).translate(shapeToMove.translationTransformations[0], shapeToMove.translationTransformations[1], shapeToMove.translationTransformations[2]).assignTo(sgcToMove);
			currentFrame++;
		}

	}

}