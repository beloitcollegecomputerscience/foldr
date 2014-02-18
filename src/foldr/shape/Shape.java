package foldr.shape;

import java.util.*;
import de.jreality.geometry.IndexedFaceSetFactory;
import de.jreality.scene.IndexedFaceSet;

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
	private Boolean isHighlighted;
	private IndexedFaceSet set;
	private List<Vertex> vertices;

	/**
	 * <p>
	 * Default constructor creates a Shape with no points, edges, or faces. Those are
	 * added once the shape is constructed.
	 * Should also be given an origin.
	 * </p>
	 */
	public Shape() {
		group = new ShapeGroup();
		set = new IndexedFaceSet();
		vertices =  new ArrayList();
		isHighlighted = false;
	}
	
	/**
	 * <p>
	 * Adds a vertex to the vertex list.
	 * </p>
	 */
	public boolean addVertex(Vertex v) {
		return vertices.add(v);
	}
	
	/**
	 * <p>
	 * Removes a vertex from the vertex list.
	 * </p>
	 */
	public boolean removeVertex(Vertex v) {
		return vertices.remove(v);
	}

	/**
	 * <p>
	 * Turns the highlight appearance on or off.
	 * </p>
	 */
	public void setHighlight(Boolean b) {
		isHighlighted = b;
	}

	public IndexedFaceSet getFaceSet() {
		IndexedFaceSetFactory factory = new IndexedFaceSetFactory();
		return set;
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
