/**
 * 
 */
package foldr.shape;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import de.jreality.scene.SceneGraphComponent;
import foldr.utility.AnimateMovement;

/**
 * A collection of all shapes object that are on the work panel, visible or not.
 * 
 * @author Hunter, Ben
 * 
 */
public class ShapeCollection {
	
	public Shape currentlySelected;
	private Vector<Shape> shapes = new Vector<Shape>();

	 /** The one and only instance of AnimateMovement.
	 */
	private static ShapeCollection instance = null;

	/**
	 * Private constructor used only by getInstance().
	 */
	private ShapeCollection() {
	}

	/**
	 * Retrieve the instance of AnimateMovement. If this is the first time
	 * retrieving, then create new.
	 * 
	 * @return The instance of AnimateMovement
	 */
	public static ShapeCollection getInstance() {
		// If null instance, create new.
		if (instance == null) {
			instance = new ShapeCollection();
		}
		return instance;
	}
	
	
	
	//return the collection of shapes
	public Shape getShapeFromCollection(int index) {
		return shapes.get(index);
	}

	//add a shape to the end of the Collection
		public void addShapeToCollection(Shape shapeToAdd) {
			shapes.add(shapeToAdd);
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
