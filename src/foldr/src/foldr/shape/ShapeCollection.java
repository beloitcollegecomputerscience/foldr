package foldr.shape;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Holds all Shape objects currently being manipulated on screen.
 * 
 * This class extends the functionality of the LinkedList class and is
 * implemented as a singleton to guarantee only one in existence.
 * 
 * @author Hunter Elbourn, Jared Feldman, & Dan Gonzales
 * 
 */

public class ShapeCollection {

	public Shape currentlySelected;
	private Vector<Shape> shapes = new Vector<Shape>();

	/**
	 * The one and only instance of ShapeCollection.
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

	// return the collection of shapes
	public Shape getShapeFromCollection(int index) {
		return shapes.get(index);
	}

	// add a shape to the end of the Collection
	public void addShapeToCollection(Shape shapeToAdd) {
		shapes.add(shapeToAdd);
	}

	/**
	 * Resets the instance of ShapeCollection.
	 */
	public static void resetList() {
		instance = new ShapeCollection();
	}

	/**
	 * Return the Shape corresponding to a key.
	 * 
	 * @param key
	 *            Key corresponding to a Shape
	 * @return Shape corresponding to a key
	 */
	public static Shape getShape(int key) {
		return null;
	}

	/**
	 * Return the Shape that the user clicked on in one of the four
	 * perspectives.
	 * 
	 * @param e
	 *            MouseEvent corresponding to where the user clicked
	 * @return Shape clicked
	 */
	public static Shape findShapeClicked(MouseEvent e) {
		return null;
	}

	/**
	 * Checks if the Shape (provided by key) is colliding with any other Shape
	 * at a given Point.
	 * 
	 * @param key
	 *            Key corresponding to a Shape
	 * @param p
	 *            Point where checking collision
	 * @return True if collision detected, else False
	 */
	public static boolean isColliding(int key, Point p) {
		return false;
	}

}
