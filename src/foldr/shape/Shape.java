package foldr.shape;

import de.jreality.geometry.Primitives;
import de.jreality.math.MatrixBuilder;
import de.jreality.scene.IndexedFaceSet;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.data.Attribute;
import de.jreality.scene.tool.AbstractTool;
import de.jreality.scene.tool.InputSlot;
import de.jreality.scene.tool.ToolContext;
import de.jreality.util.SceneGraphUtility;

/**
 * <p>
 * A group of objects (Vertices, Faces, "Geometries," etc.) that form a closed
 * 3D shape of some thickness.
 * </p>
 * 
 * @author vogtb and couretn
 * 
 */
public class Shape {
	/**
	 * <p>
	 * To facilitate the process of finding which ShapeGroup a Shape is apart
	 * of.
	 * </p>
	 */
	private ShapeGroup group;
	private SceneGraphComponent shapeSGC;
	// make a new instance of the animation tool
	private AnimationTool animateShape = new AnimationTool();

	public boolean inMotion = false;

	/**
	 * <p>
	 * Default constructor creates a Shape with no points, edges, or faces.
	 * Those are added once the shape is constructed. Should also be given an
	 * origin.
	 * </p>
	 */
	public Shape() {
		group = new ShapeGroup();
	}

	public SceneGraphComponent getShapeSGC() {
		return shapeSGC;
	}

	/**
	 * A simple constructor to create a regular polygon on the canvas.
	 * 
	 * @param numSides
	 *            The number of sides the polygon should have.
	 * @param parentScene
	 *            The SceneGraphComponent that this shape will be a child of.
	 */
	public Shape(int numSides, SceneGraphComponent parentScene) {

		IndexedFaceSet shapeGeometry = Primitives.regularPolygon(numSides);

		shapeSGC = SceneGraphUtility

		.createFullSceneGraphComponent();

		shapeSGC.setGeometry(shapeGeometry);

		parentScene.addChild(shapeSGC);
		// attach the animation tool to this sgc
		shapeSGC.addTool(animateShape);

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
	 * @param group
	 *            The group that you are adding this Shape to.
	 */
	public void setGroup(ShapeGroup group) {
		this.group = group;
	}

	/**
	 * Returns the current coordinates of a vertex
	 * 
	 * @param vertex
	 *            Which vertex to get the coordinates of
	 * @return An array of doubles in the format [x,y,z]
	 */
	public Double[] getCurrentVertexCoordinates(int vertex) {

		// the original vertex coordinates as strings
		String vertexCoorX = shapeSGC.getGeometry().getAttributes("VERTEX")
				.get(Attribute.COORDINATES, vertex).get(0).toString();
		String vertexCoorY = shapeSGC.getGeometry().getAttributes("VERTEX")
				.get(Attribute.COORDINATES, vertex).get(1).toString();
		String vertexCoorZ = shapeSGC.getGeometry().getAttributes("VERTEX")
				.get(Attribute.COORDINATES, vertex).get(2).toString();

		// the original vertex coordinates as doubles
		Double originalVertexX = Double.parseDouble(vertexCoorX.substring(1,
				vertexCoorX.length() - 1));
		Double originalVertexY = Double.parseDouble(vertexCoorY.substring(1,
				vertexCoorY.length() - 1));
		Double originalVertexZ = Double.parseDouble(vertexCoorZ.substring(1,
				vertexCoorZ.length() - 1));

		// the current translation
		Double currentTranslationX = shapeSGC.getTransformation().getMatrix()[3];
		Double currentTranslationY = shapeSGC.getTransformation().getMatrix()[7];
		Double currentTranslationZ = shapeSGC.getTransformation().getMatrix()[11];

		// the array with the current coordinates of the selected vertex
		Double[] allCurrentVertexCoor = {
				originalVertexX + currentTranslationX,
				originalVertexY + currentTranslationY,
				originalVertexZ + currentTranslationZ };
		return allCurrentVertexCoor;
	}

	/**
	 * The public method used for animation. Will only run an animation if the
	 * shape is not already being animated. If called on a shape in animation,
	 * this method will simply return false.
	 * 
	 * @param endPoints
	 *            The coordinates the shape will be translated to.
	 * @param vertexToTranslate
	 *            The vertex
	 * @return false if the shape is already being animated, true if it is not
	 */
	public boolean animateShape(double[] endPoints) {
		if (inMotion) {
			return false;
		} else {
			inMotion = true;
			animateShape.setEndPoints(this, endPoints);
			return true;
		}
	}

	/**
	 * 
	 * A Jreality tool, which animates a shape an input distance over a set
	 * amount of frames. Currently will only work for translation.
	 * 
	 */
	class AnimationTool extends AbstractTool {

		int currentFrame = 0;
		// this variable determines the number of frames the animation will
		// occur in
		int totalFramesForAnimation = 20;

		// the shape and scg that are being moved
		Shape shapeToMove;
		SceneGraphComponent sgcToMove;

		// the current coordinates of a reference vertex
		double currentX;
		double currentY;
		double currentZ;

		// the interval each coordinate will change every frame
		double intervalToMoveX;
		double intervalToMoveY;
		double intervalToMoveZ;

		// the new coordinates to translate the shape to
		double newX;
		double newY;
		double newZ;

		private final InputSlot TIME = InputSlot.SYSTEM_TIME;

		public AnimationTool() {
			addCurrentSlot(TIME, "triggers perform");
		}

		public void setEndPoints(Shape newShapeToMove, double[] newEndPoints) {

			shapeToMove = newShapeToMove;
			sgcToMove = shapeToMove.getShapeSGC();

			// set the distance to move for each coordinate
			double distanceToMoveX = newEndPoints[0];
			double distanceToMoveY = newEndPoints[1];
			double distanceToMoveZ = newEndPoints[2];

			// calculate the interval to change each coordinate every frame
			intervalToMoveX = distanceToMoveX
					/ (double) totalFramesForAnimation;
			intervalToMoveY = distanceToMoveY
					/ (double) totalFramesForAnimation;
			intervalToMoveZ = distanceToMoveZ
					/ (double) totalFramesForAnimation;

			// make sure there's a current time slot, in case this isn't the
			// first animation being performed on this shape
			addCurrentSlot(TIME, "triggers perform");
		}

		@Override
		public void perform(ToolContext tc) {

			// update the current coordinates every frame, using vertex 0 as a
			// reference
			currentX = getCurrentVertexCoordinates(0)[0];
			currentY = getCurrentVertexCoordinates(0)[1];
			currentZ = getCurrentVertexCoordinates(0)[2];

			// check if we've looped through the correct number of frames
			if (currentFrame == totalFramesForAnimation) {
				System.out.println("goal reached!");
				System.out.println("X: " + currentX);
				System.out.println("Y: " + currentY);
				System.out.println("Z: " + currentZ);
				shapeToMove.inMotion = false;
				// animation is done, so get rid of the current slot
				removeCurrentSlot(TIME);
			} else {
				// update the new coordinates
				newX += intervalToMoveX;
				newY += intervalToMoveY;
				newZ += intervalToMoveZ;
				// translate the shape
				MatrixBuilder.euclidean().translate(newX, newY, newZ)
						.assignTo(sgcToMove);

				currentFrame++;
			}

		}

	}

}
