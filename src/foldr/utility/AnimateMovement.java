package foldr.utility;

import de.jreality.math.MatrixBuilder;
import foldr.shape.Shape;
import foldr.shape.ShapeGroup;


/**
 *This class is a singleton which will handle the movement of a given shape group to a specified end point.
 * 
 * Team: Liz, Ellery, Dustin
 *
 */

public class AnimateMovement {

	/**
	 * The one and only instance of AnimateMovement.
	 */
	private static AnimateMovement instance = null;

	/**
	 * Private constructor used only by getInstance().
	 */
	private AnimateMovement() {
	}

	/**
	 * Retrieve the instance of AnimateMovement. If this is the first time
	 * retrieving, then create new.
	 * 
	 * @return The instance of AnimateMovement
	 */
	public static AnimateMovement getInstance() {
		// If null instance, create new.
		if (instance == null) {
			instance = new AnimateMovement();
		}
		return instance;
	}
	
    /**
     * 
     * Animates a shape group to an end point.
     * Does not check for potential collisions and instead lets shapes move through each other.
     *
     * There are two reason this method will be called:
     * 1. The user is manually moving a ShapeGroup using the 'move' tool - or should it just automatically move in this case, with no animation? Probably.
     * 2. The user is using the 'join edge' tool. 
     * 
     * Any calculations should probably be done in another class. 
     * For now, AnimateMovement simply takes end coordinates and translates a shape group that much. 
     * 
     * @param toMove
     *            the ShapeGroup you want to animate. This can contain one or more shapes.
     * @param endPoint
     *        The (x, y, z) coordinates of the point where you want the
     *            animation to end.
     *
     */
	 
	public void moveShapeGroup(ShapeGroup shapeGroupToMove, double[] endPoint) {
		// TODO the ShapeGroup class should have either an public list of the Shape objects it stores, or a method to return this list. 
		// for now, I'm pretending that allShapesToMove has a method to return an array of Shape objects in its group.
		Shape[] allShapesToMove = shapeGroupToMove.getShapesInGroup();
		
		/*TODO
		 * 
		 * I think this is going to move each shape in the group one at a time.
		 * What we'll probably want to do is translate each shape a small fraction of the endpoints.
		 * Then we can nest this loop inside another for, which iterates to the scale we used on the endpoints. 
		 * This should make it appear that all shapes are moving together.
		 *  
		 */
		for (Shape currentShape : allShapesToMove) {
			// TODO anything onscreen will have to be a sceneGraphComponent. For now, I'm just pretending that this object will be stored somewhere in Shape and can be returned.
			MatrixBuilder.euclidean().translate(endPoint[0], endPoint[1], endPoint[2]).assignTo(currentShape.getSceneGraphComponent());
		}
	}
	
	
}

	
	

