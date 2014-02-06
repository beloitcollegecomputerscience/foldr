package foldr.shape;

import java.util.Collection;

/**
 * <p>
 * <b>Liz :</b> The ShapeGroup class exists so that when edges of instances of
 * the {@link Shape} class are joined together, the resulting shape can be
 * manipulated as if it were a single Shape instance.<br/>
 * A new ShapeGroup instance will typically be created when the edges of two
 * Shape objects (which are not already in a ShapeGroup) are joined together.
 * Each instance of ShapeGroup keeps a list which stores references to all Shape
 * objects which are part of the group.
 * </p>
 * 
 * @author Liz
 * 
 */
public class ShapeGroup {
	Collection<Shape> shapes;

	/**
	 * <p>
	 * <b>Nath :</b> seems that my entries have not been recorded, will add
	 * them.
	 * </p>
	 */
	public ShapeGroup() {
	}

	/**
	 * <p>
	 * <b>Dan :</b> Updates the internal location values of the shapes in the
	 * shape group. Should only be done upon successful movements via the
	 * AnimateMovement.
	 * </p>
	 * 
	 */
	public void move() {

	}

	/**
	 * <p>
	 * <b>Nick :</b> Merges two selected ShapeGroups into one larger ShapeGroup
	 * that can then be interacted with as if it was one shape rather than two
	 * separate entities. This method will typically be invoked after the two
	 * ShapeGroups have been moved together via the move() method and need to be
	 * combined.
	 * </p>
	 */
	public void mergeGroup() {

	}

}
