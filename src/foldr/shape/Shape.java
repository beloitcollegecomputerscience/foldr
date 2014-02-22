package foldr.shape;

import de.jreality.geometry.Primitives;
import de.jreality.scene.IndexedFaceSet;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.util.SceneGraphUtility;

/**
 * <p>
 * A group of objects (Vertices, Faces, "Geometries," etc.) that form a closed 3D shape of
 * some thickness.
 * </p>
 * @author vogtb and couretn
 * 
 */
public class Shape {
	/**
	 * <p>
	 * To facilitate the process of finding which ShapeGroup a Shape is apart of.
	 * </p>
	 */
	private ShapeGroup group;

	/**
	 * <p>
	 * Default constructor creates a Shape with no points, edges, or faces. Those are
	 * added once the shape is constructed.
	 * Should also be given an origin.
	 * </p>
	 */
	public Shape() {
		group = new ShapeGroup();
	}

	
	private SceneGraphComponent shapeSGC;
	public Shape(int numSides, SceneGraphComponent parentScene) {
		IndexedFaceSet shapeGeometry = Primitives.regularPolygon(numSides);
		shapeSGC = SceneGraphUtility
				.createFullSceneGraphComponent();
		shapeSGC.setGeometry(shapeGeometry);
		parentScene.addChild(shapeSGC);
	}
	
	/**
	 * <p>
	 * Highlights the edges of the Shape with a bright color.
	 * </p>
	 */
	public void changeAppearance() {

	}

	/**
	 * <p>
	 * Returns the ShapeGroup that the Shape is in.
	 * </p>
	 * 
	 * @return The ShapeGroup the Shape is in.
	 */
	public ShapeGroup getGroup() {
		return group;
	}

	/**
	 * Set the Shape to be apart of a group. 
	 * 
	 * @param group The group that you are adding this Shape to.
	 */
	public void setGroup(ShapeGroup group) {
		this.group = group;
	}

}
