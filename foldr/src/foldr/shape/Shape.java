package foldr.shape;

/**
 * <p>
 * <b>Hunter :</b> The classifications for our shapes objects, how to manipulate
 * them esthetically and how to check their characteristics for classification.
 * Its is accessed by the {@link ShapeCollection} and is updated to
 * ShapeCollection for certain edges and shapes that are selected.
 * </p>
 * 
 * @author Hunter
 * 
 */
public class Shape {
	/**
	 * <p>
	 * I wonder if that attribute is relevant. A ShapeGroup must know all its
	 * Shapes, but do we need a Shape to know its ShapeGroup? Also consider the
	 * case the Shape has no ShapeGroup.
	 * </p>
	 */
	private ShapeGroup group;

	/**
	 * <p>
	 * Default constructor.
	 * </p>
	 */
	public Shape() {
		group = new ShapeGroup();
	}

	/**
	 * <p>
	 * <b>Ben :</b> Highlights the edges of the shape with a color that's
	 * inverse to the Shape's color.
	 * </p>
	 */
	public void changeAppearance() {

	}

	/**
	 * <p>
	 * <b>Tyler :</b> Will return the {@link ShapeGroup} it belongs to by
	 * accessing the currently selected shape's ShapeGroup object. This means
	 * there should be a setGroup method called when each new shape is added to
	 * set it to either a new or existing group.
	 * </p>
	 * <p>
	 * <b>Hunter :</b> a shape will be set with a reference to a ShapeGroup
	 * object once a join edge tool is used on it. Like wise when we use the
	 * remove edge tool it would check to see if they both become singular
	 * shapes, then classify them as such.
	 * </p>
	 * 
	 * @return The ShapeGroup the Shape can be classified as.
	 */
	public ShapeGroup getGroup() {
		return group;
	}
	
	public void setGroup(ShapeGroup group) {
		this.group = group;
	}

}
