package foldr.utility;

import foldr.shape.Shape;

/**
 * 
 * @author Ellery, Hunter, Dan, Dustin
 * 
 */
public class AnimateMovement {

	/**
	 * <p>
	 * <b>Ellery :</b> Animates a shape from one point to another.
	 * </p>
	 * <p>
	 * <b>Nath :</b> note that I put the points as double[] instead of GPoint as
	 * we'll probably use JReality objects later.
	 * </p>
	 * 
	 * @param toMove
	 *            the {@link Shape} you want to animate.
	 * @param startPoint
	 *            The (x, y, z) coordinates of the point where you want the
	 *            animation to start.
	 * @param endPoint
	 */
	public AnimateMovement(Shape toMove, double[] startPoint, double[] endPoint) {
	}

	/**
	 * <b>/!\ signature subject to change /!\</b><br/>
	 * <p>
	 * Apparently moves an entire group
	 * </p>
	 * 
	 * <p>
	 * Should this return anything if it can't move?
	 * </p>
	 * <p>
	 * <b>Dustin :</b> I don't think it should. The name of the method is
	 * "moveGroupTo" which (I believe) suggests that if we are calling it, we
	 * have already determined that we have given it valid coordinates. So I
	 * think we need another method to check for valid coordinates. If it
	 * returns true, then call moveGroupTo. We could certainly roll that check
	 * into this method, but then I would suggest that we rename it, and in that
	 * case, yeah, it should return false on a failed move or something.
	 * </p>
	 * <p>
	 * <b>Hunter :</b> I agree with this, I had not considered the case of
	 * invalid coordinates also to call the collision check method during just
	 * to check if there would be any error in animating the shift.
	 * </p>
	 * <p>
	 * <b>Dan :</b> I think it's worth while to return true if success and false
	 * if not. We may assume that we always know what we're trying to do but it
	 * makes it easier to work with and more flexible a simple return statement.
	 * </p>
	 * 
	 */
	public void moveGroupTo(Object... args) {
	}
}
