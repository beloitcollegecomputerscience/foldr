/**
 * 
 */
package foldr.shape;

import java.util.Collection;

/**
 * A collection of all shapes object that are on the work panel, visible or not.
 * 
 * @author Hunter, Ben
 * 
 */
public class ShapeCollection {
	Collection<Shape> shapes;

	/**
	 * 
	 */
	public ShapeCollection() {
	}

	/**
	 * When given the coordinates of the click, will go through the shapes,
	 * finding which one was clicked.<br/>
	 * Will disregard {@link ShapeGroup} and return {@link Shape} only. Returned
	 * Shape can then be used to access its ShapeGroup.
	 * 
	 * @return the clicked shape. (if it works haha)
	 */
	public Shape findShapeClicked() {
		return null;
	}

	/**
	 * When passed a pair of Shape ad a pair of edges for that Shape, will join
	 * edges together.
	 */
	public void joinEdges() {

	}

	/**
	 * Checks for collision inside the entire collection. Should be used as an
	 * indicator that further steps need to be taken to correct collision.
	 * 
	 * @return true if there is/will be a collision, false otherwise.
	 */
	public boolean collisionCheck() {
		return false;
	}

}
