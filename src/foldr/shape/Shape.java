
package foldr.shape;

import java.util.*;
import de.jreality.geometry.IndexedFaceSetFactory;
import de.jreality.scene.IndexedFaceSet;

/**
 * <p>
 * A group of objects (Vertices, Faces, "Geometries," etc.) that form a closed
 * 3D shape of some thickness.
 * </p>
 * 
 * @author vogtb and couretn
 */
public class Shape {

    /**
     * <p>
     * To facilitate the process of finding which ShapeGroup a Shape is apart
     * of.
     * </p>
     */
    private ShapeGroup     group;
    private Boolean        isHighlighted;
    private IndexedFaceSet set;

    /**
     * <p>
     * Default constructor creates a Shape with no points, edges, or faces.
     * Those are added once the shape is constructed. Should also be given an
     * origin.
     * </p>
     */
    public Shape() {

        group = new ShapeGroup();
        set = new IndexedFaceSet();
        isHighlighted = false;
    }
    
    public Shape(double[][] v, int[][] f) {
        IndexedFaceSetFactory ifsf = new IndexedFaceSetFactory();
        ifsf.setVertexCount(v.length);
        ifsf.setVertexCoordinates(v);
        ifsf.setFaceCount(f.length);
        ifsf.setFaceIndices(f);
        ifsf.update();
        group = new ShapeGroup();
        set = ifsf.getIndexedFaceSet();
        isHighlighted = false;
    }

    /**
     * <p>
     * Turns the highlight appearance on or off.
     * </p>
     */
    public void setHighlight(Boolean b) {

        isHighlighted = b;
    }
    
    public boolean isHighlight() {
        return isHighlighted;
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
     * 
     * @return
     */
    public int getVertexCount() {
        return set.getVertexAttributes().getListLength();
    }

    /**
     * Set the Shape to be apart of a group.
     * 
     * @param group
     *            The group that you are adding this Shape to.
     */
    public void setGroup(ShapeGroup group) {

        this.group = group;
    }

}
