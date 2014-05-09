package foldr.utility;

/**
 * This is a jReality tool to aid in the selection of objects in a scene.
 * 
 * @author vogtb
 */

import java.awt.Color;

import de.jreality.geometry.Primitives;
import de.jreality.math.Matrix;
import de.jreality.math.MatrixBuilder;
import de.jreality.math.Pn;
import de.jreality.math.Rn;
import de.jreality.scene.Appearance;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.SceneGraphNode;
import de.jreality.scene.SceneGraphPath;
import de.jreality.scene.pick.PickResult;
import de.jreality.scene.tool.AbstractTool;
import de.jreality.scene.tool.InputSlot;
import de.jreality.scene.tool.ToolContext;
import de.jreality.shader.CommonAttributes;
import foldr.shape.Shape;
import foldr.shape.ShapeCollection;

public class SelectTool extends AbstractTool {

	private transient SceneGraphComponent c = new SceneGraphComponent();
	private transient Appearance a = new Appearance();

	// Holding the last valid pick, even when the user is hovering over nothing.
	private PickResult prSave;

	private transient boolean attached;
	private double radius = 0.005;

	// Holding information about the type of pick
	public enum SelectionType {
		POINT, LINE, FACE
	}

	private SelectionType type;

	public SelectTool(String activationAxis, double radius) {
		super(activationAxis == null ? null : InputSlot
				.getDevice(activationAxis));
		this.radius = radius;
		init();
	}

	private void init() {
		addCurrentSlot(InputSlot.getDevice("PointerTransformation"));
		c.addChild(Primitives.sphere(3, 0, 0, 0));
		c.setName("pick display");
		c.setAppearance(a);
		c.setPickable(false);
		a.setAttribute(CommonAttributes.FACE_DRAW, true);
		a.setAttribute(CommonAttributes.PICKABLE, false);
	}

	public SelectTool() {
		super();
		type = null;
		init();
	}

	public void activate(ToolContext tc) {
		perform(tc);
	}

	public void perform(ToolContext tc) {
		PickResult pr = tc.getCurrentPick();
		// If the pick is null, don't go any further
		if (pr == null) {
			assureDetached(tc);
			return;
		}
		prSave = tc.getCurrentPick();
		assureAttached(tc);
		switch (pr.getPickType()) {
		case PickResult.PICK_TYPE_FACE:
			c.getAppearance().setAttribute("diffuseColor", Color.yellow);
			type = SelectionType.FACE;
			break;
		case PickResult.PICK_TYPE_LINE:
			c.getAppearance().setAttribute("diffuseColor", Color.green);
			type = SelectionType.LINE;
			break;
		case PickResult.PICK_TYPE_POINT:
			c.getAppearance().setAttribute("diffuseColor", Color.magenta);
			type = SelectionType.POINT;
			break;
		default:
			c.getAppearance().setAttribute("diffuseColor", Color.black);
			type = null;
		}

		// Placing the dot to indicate selection type
		double[] worldCoordinates = pr.getWorldCoordinates();
		if (!Pn.isValidCoordinate(worldCoordinates, 3, Pn.EUCLIDEAN))
			return;
		double[] cp = new Matrix(tc.getViewer().getCameraPath().getMatrix(null))
				.getColumn(3);
		double scale = Rn.euclideanDistance(cp, worldCoordinates);
		MatrixBuilder.euclidean().translate(worldCoordinates).assignTo(c);
		MatrixBuilder.euclidean().scale(scale * radius)
				.assignTo(c.getChildComponent(0));
	}

	public void deactivate(ToolContext tc) {
		assureDetached(tc);
	}

	private void assureAttached(ToolContext tc) {
		if (!attached)
			tc.getViewer().getSceneRoot().addChild(c);
		attached = true;
		c.setVisible(true);
	}

	private void assureDetached(ToolContext tc) {
		c.setVisible(false);
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Gets the pick from the scene
	 * */
	public SceneGraphPath getPick() {
		return prSave.getPickPath();
	}

	/**
	 * Returns the Shape that has been selected, regardless of selection type
	 * (Point, Line, Face)
	 * */
	public Shape getSelectedShape(ShapeCollection allShapes) {
		return allShapes.getShapeByHashcode(prSave.getPickPath()
				.get(prSave.getPickPath().getLength() - 2).hashCode());
	}

	/**
	 * Gets the selection type (Point, Line, Face)
	 * */
	public SelectionType getSelectionType() {
		return type;
	}
	
	/**
	 * Gets the index of the point selected.
	 * 
	 * WARNING: Check selection type first or you're going to get a bad index (928463989).
	 * */
	public int getPointSelection() {
		if (type == SelectionType.POINT) {
			return prSave.getIndex();
		}
		return 928463989; // A very unlikely index number to help you debug.
	}

}
