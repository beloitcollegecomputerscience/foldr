package foldr.utility;

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
	AnimateMovement() {
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
     * @param toMove
     *            the ShapeGroup you want to animate. This can contain one or more shapes.
     * @param endPoint
     *        The (x, y, z) coordinates of the point where you want the
     *            animation to end.
     *
     */
	 
	public void moveShapeGroup(ShapeGroup toMove, double[] endPoint) {
	}

	
}
