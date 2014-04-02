package foldr.shape;

import de.jreality.geometry.Primitives;
import de.jreality.math.MatrixBuilder;
import de.jreality.scene.IndexedFaceSet;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.data.Attribute;
import de.jreality.scene.tool.AbstractTool;
import de.jreality.scene.tool.InputSlot;
import de.jreality.scene.tool.ToolContext;
import de.jreality.tools.DragEventTool;
import de.jreality.tools.FaceDragEvent;
import de.jreality.tools.FaceDragListener;
import de.jreality.tools.LineDragEvent;
import de.jreality.tools.LineDragListener;
import de.jreality.tools.PointDragEvent;
import de.jreality.tools.PointDragListener;
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
	private ShapeCollection allShapes = ShapeCollection.getInstance();
	private ShapeGroup group;
	public SceneGraphComponent shapeSGC;
	// make a new instance of the animation tool
	private AnimationTool animateShape = new AnimationTool();
	// make a new instance of the rotate tool
	private RotateTool rotateShape = new RotateTool();


	private DragEventTool shapeClicked = new DragEventTool();
	public static int selectedVertex;

	public boolean inMotion = false;

	private double currentX = 0;
	private double currentY = 0;
	private double currentZ = 0;
	
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

		// create the polygon
		IndexedFaceSet shapeGeometry = Primitives.regularPolygon(numSides);
		shapeSGC = SceneGraphUtility.createFullSceneGraphComponent();
		shapeSGC.setGeometry(shapeGeometry);
		parentScene.addChild(shapeSGC);
		//add it to the collection of all shapes on screen
		allShapes.addShapeToCollection(this);
		//make a new shape group and add this shape into it
		group = new ShapeGroup();
		group.shapesInGroup.add(this);
		
		/**
		 * Experimenting around with pre-made listeners. 
		 * This tells you how many shapes are in a shapegroup when you click on a face.
		 */
		shapeClicked.addFaceDragListener(new FaceDragListener() {


			@Override
			public void faceDragged(FaceDragEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void faceDragEnd(FaceDragEvent e) {
				System.out.println("This shape group has " + group.shapesInGroup.size() + " shapes in it");
			}

			@Override
			public void faceDragStart(FaceDragEvent e) {
				// TODO Auto-generated method stub
				
			}			
		});
		shapeSGC.addTool(shapeClicked);
		
	}

	
	

	/**
	 * Method that simplifies translation of shapes. Just using it to test animation.
	 */
	public void translate(double x, double y, double z) {
		MatrixBuilder.euclidean().translate(x, y, z).assignTo(this.shapeSGC);
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
		this.group.shapesInGroup.add(this);
	}

	/**
	 * Returns the current coordinates of a vertex
	 * 
	 * @param vertex
	 *            Which vertex to get the coordinates of
	 * @return An array of doubles in the format [x,y,z]
	 */
	public double[] getCurrentVertexCoordinates(int vertex) {

		// the original vertex coordinates as strings
		String vertexCoorX = shapeSGC.getGeometry().getAttributes("VERTEX")
				.get(Attribute.COORDINATES, vertex).get(0).toString();
		String vertexCoorY = shapeSGC.getGeometry().getAttributes("VERTEX")
				.get(Attribute.COORDINATES, vertex).get(1).toString();
		String vertexCoorZ = shapeSGC.getGeometry().getAttributes("VERTEX")
				.get(Attribute.COORDINATES, vertex).get(2).toString();

		// the original vertex coordinates as doubles
		double originalVertexX = Double.parseDouble(vertexCoorX.substring(1,
				vertexCoorX.length() - 1));
		double originalVertexY = Double.parseDouble(vertexCoorY.substring(1,
				vertexCoorY.length() - 1));
		double originalVertexZ = Double.parseDouble(vertexCoorZ.substring(1,
				vertexCoorZ.length() - 1));

		// the current translation
		double currentTranslationX = shapeSGC.getTransformation().getMatrix()[3];
		double currentTranslationY = shapeSGC.getTransformation().getMatrix()[7];
		double currentTranslationZ = shapeSGC.getTransformation().getMatrix()[11];

		// the array with the current coordinates of the selected vertex
		double[] allCurrentVertexCoor = {
				originalVertexX + currentTranslationX,
				originalVertexY + currentTranslationY,
				originalVertexZ + currentTranslationZ };
		return allCurrentVertexCoor;

	}

	/**
	 * 
	 * @return
	 */
	public boolean rotateShape(double angleToRotate, char planeOfRotation) {
		if (inMotion) {
			return false;
		} else {
			inMotion = true;
			// attach rotate shape tool
			shapeSGC.addTool(rotateShape);
			rotateShape.setEndPoints(this, angleToRotate, planeOfRotation);
		}
		return true;
	}

	/**
	 * 
	 * @author elleryaddington-white
	 * 
	 */
	class RotateTool extends AbstractTool {
		double currentFrame = 0.0;
		// this variable determines the number of frames the animation will
		// occur in
		int totalFramesForAnimation = 100;

		// the shape and scg that are being moved
		Shape shapeToMove;
		SceneGraphComponent sgcToMove;

		// the interval the shape will rotate every frame
		double intervalToRotate;
		// Which plane the shape is being rotated in
		char planeOfRotation;

		// the new coordinates to translate the shape to. These start as equal
		// to the current coordinates.
		double newX;
		double newY;
		double newZ;

		private final InputSlot TIME = InputSlot.SYSTEM_TIME;

		public RotateTool() {
			addCurrentSlot(TIME);

		}

		public void setEndPoints( Shape newShapeToMove, double angleToRotate, char planeOfRotation){
			shapeToMove = newShapeToMove;
			sgcToMove = shapeToMove.getShapeSGC();
			
			intervalToRotate = angleToRotate/totalFramesForAnimation;
			
			currentFrame = 0;
			
			
		}
		@Override
		public void perform(ToolContext tc) {

			// check if we've looped through the correct number of frames
			if (currentFrame == totalFramesForAnimation) {
				System.out.println("goal reached!");
				shapeToMove.inMotion = false;
				// animation is done, so get remove this tool from the shape
				sgcToMove.removeTool(this);
			} else {
				//TODO Do I need this?
				// update the new coordinates
//				newX = intervalToMoveX * currentFrame;
//				newY = intervalToMoveY * currentFrame;
//				newZ = intervalToMoveZ * currentFrame;

				// Rotate the shape
				if(planeOfRotation == 'x'){
					MatrixBuilder.euclidean().rotateX(intervalToRotate*currentFrame).assignTo(sgcToMove);
				}else if (planeOfRotation == 'y'){
					MatrixBuilder.euclidean().rotateY(intervalToRotate*currentFrame).assignTo(sgcToMove);
				}else{
					MatrixBuilder.euclidean().rotateZ(intervalToRotate*currentFrame).assignTo(sgcToMove);
				}

				currentFrame++;
			}

		}

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
			// attach the animation tool to this sgc
			shapeSGC.addTool(animateShape);
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

		int currentFrame;
		// this variable determines the number of frames the animation will
		// occur in
		int totalFramesForAnimation = 200;

		// the shape and scg that are being moved
		Shape shapeToMove;
		SceneGraphComponent sgcToMove;


		// the target coordinates
		double distanceToMoveX;
		double distanceToMoveY;
		double distanceToMoveZ;


		// the interval each coordinate will change every frame
		double intervalToMoveX;
		double intervalToMoveY;
		double intervalToMoveZ;


		// the new coordinates to translate the shape to. These start as equal
		// to the current coordinates.

		// the new coordinates to translate the shape to.

		// the new coordinates to translate the shape to.
		double newX;
		double newY;
		double newZ;

		// the original coordinates of the shape
		double originalX;
		double originalY;
		double originalZ;
		
		private final InputSlot TIME = InputSlot.SYSTEM_TIME;

		public AnimationTool() {
			addCurrentSlot(TIME);

		}

		public void setEndPoints(Shape newShapeToMove, double[] newEndPoints) {
			
			shapeToMove = newShapeToMove;
			sgcToMove = shapeToMove.getShapeSGC();

			//reset the current frame
			currentFrame = 0;
			
			//store the original distance
			originalX = shapeToMove.currentX;
			originalY = shapeToMove.currentY;
			originalZ = shapeToMove.currentZ;
			
			// set the distance to move for each coordinate
			distanceToMoveX = newEndPoints[0];
			distanceToMoveY = newEndPoints[1];
			distanceToMoveZ = newEndPoints[2];

			// calculate the interval to change each coordinate every frame
			intervalToMoveX = distanceToMoveX
					/ (double) totalFramesForAnimation;
			intervalToMoveY = distanceToMoveY
					/ (double) totalFramesForAnimation;
			intervalToMoveZ = distanceToMoveZ
					/ (double) totalFramesForAnimation;
		}

		@Override
		public void perform(ToolContext tc) {
			// check if we've looped through the correct number of frames
			if (currentFrame == totalFramesForAnimation) {
				System.out.println("goal reached!");
				shapeToMove.inMotion = false;
				// reset current distance TODO is this necessary?
				shapeToMove.currentX += distanceToMoveX;
				shapeToMove.currentY += distanceToMoveY;
				shapeToMove.currentZ += distanceToMoveZ;
				// animation is done, so get remove this tool from the shape
				sgcToMove.removeTool(this);
			} else {
				// update the new coordinates
				newX = intervalToMoveX * currentFrame;
				newY = intervalToMoveY * currentFrame;
				newZ = intervalToMoveZ * currentFrame;

				// translate the shape
				MatrixBuilder.euclidean().translate(originalX + newX, originalY + newY, originalZ + newZ)
						.assignTo(sgcToMove);

				currentFrame++;
			}

		}

	}

	
	
}
