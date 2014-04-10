package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.jreality.scene.SceneGraphComponent;
import de.jreality.util.SceneGraphUtility;
import foldr.shape.Shape;

public class AnimateMovementTest {

	static SceneGraphComponent topScene = SceneGraphUtility
			.createFullSceneGraphComponent("topScene");

	Shape shapeOne = new Shape(3, topScene);
	Shape shapeTwo = new Shape(4, topScene);
	Shape shapeThree = new Shape(5, topScene);

	@Before
	public void setUp() {
	}

	@BeforeClass
	public static void setUpBeforeClass() {
	}

	/**
	 * This test animates a shape and makes sure it ends up where it's supposed
	 * to.
	 */
	@Test
	public void testAnimateShape() {

		// the error that's ok when testing if the shape moved to the right
		// place
		double allowedError = .01;

		double xOriginal;
		double yOriginal;
		double zOriginal;

		double xNew;
		double yNew;
		double zNew;

		double[] testCoordinates = new double[3];

		/*
		 * test moving a single shape with positive coordinates
		 */

		// grab the original coordinates of a vertex
		xOriginal = shapeOne.getCurrentVertexCoordinates(0)[0];
		yOriginal = shapeOne.getCurrentVertexCoordinates(0)[1];
		zOriginal = shapeOne.getCurrentVertexCoordinates(0)[2];

		// set some positive coordinates
		testCoordinates[0] = 1.1;
		testCoordinates[1] = 2.2;
		testCoordinates[2] = 3.3;

		shapeOne.animateShape(testCoordinates);

		// wait for the shape to finish moving
		while (shapeOne.inMotion) {
			// shapeOne.animateShape.perform(null);
			shapeOne.getShapeSGC().getTools().get(1).perform(null);
		}

		xNew = shapeOne.getCurrentVertexCoordinates(0)[0];
		yNew = shapeOne.getCurrentVertexCoordinates(0)[1];
		zNew = shapeOne.getCurrentVertexCoordinates(0)[2];

		// compare new values with what they should be
		assertEquals(xOriginal + testCoordinates[0], xNew, allowedError);
		assertEquals(yOriginal + testCoordinates[1], yNew, allowedError);
		assertEquals(zOriginal + testCoordinates[2], zNew, allowedError);

		/*
		 * test moving a single shape with negative coordinates
		 */

		// grab the original coordinates of a vertex
		xOriginal = shapeOne.getCurrentVertexCoordinates(0)[0];
		yOriginal = shapeOne.getCurrentVertexCoordinates(0)[1];
		zOriginal = shapeOne.getCurrentVertexCoordinates(0)[2];

		// set some negative coordinates
		testCoordinates[0] = -1.1;
		testCoordinates[1] = -2.2;
		testCoordinates[2] = -3.3;

		shapeOne.animateShape(testCoordinates);

		// wait for the shape to finish moving
		while (shapeOne.inMotion) {
			shapeOne.getShapeSGC().getTools().get(1).perform(null);
		}

		xNew = shapeOne.getCurrentVertexCoordinates(0)[0];
		yNew = shapeOne.getCurrentVertexCoordinates(0)[1];
		zNew = shapeOne.getCurrentVertexCoordinates(0)[2];

		// compare new values with what they should be
		assertEquals(xOriginal + testCoordinates[0], xNew, allowedError);
		assertEquals(yOriginal + testCoordinates[1], yNew, allowedError);
		assertEquals(zOriginal + testCoordinates[2], zNew, allowedError);

	}

	/**
	 * This rotates a shape to make sure the method is rotating shapes in the
	 * right direction.
	 * 
	 * @author Ellery Addington-White
	 * 
	 *         TODO Bugs: This does not really accurately test if the shape has
	 *         been rotated properly is only looking at vertices. Ask Darrah
	 *         what he thinks the best way to do this is. possibly look at
	 *         matrix before and after and then compare? Still looking for good
	 *         method to use may require some math instead... :(
	 */
	public void testRotateShape() {
		double xOriginal;
		double yOriginal;
		double zOriginal;

		double xNew;
		double yNew;
		double zNew;

		/*
		 * test rotating a single shape with positive rotation.
		 */

		// grab the original coordinates of a vertex
		xOriginal = shapeOne.getCurrentVertexCoordinates(0)[0];
		yOriginal = shapeOne.getCurrentVertexCoordinates(0)[1];
		zOriginal = shapeOne.getCurrentVertexCoordinates(0)[2];

		shapeOne.rotateShape(30, 'x');
		shapeOne.rotateShape(30, 'y');
		shapeOne.rotateShape(30, 'z');

		xNew = shapeOne.getCurrentVertexCoordinates(0)[0];
		yNew = shapeOne.getCurrentVertexCoordinates(0)[1];
		zNew = shapeOne.getCurrentVertexCoordinates(0)[2];

		assert (xOriginal < xNew);
		assert (yOriginal < yNew);
		assert (zOriginal < zNew);

		/*
		 * Test rotating a single shape with negative rotation.
		 */

		// grab the original coordinates of a vertex
		xOriginal = shapeTwo.getCurrentVertexCoordinates(0)[0];
		yOriginal = shapeTwo.getCurrentVertexCoordinates(0)[1];
		zOriginal = shapeTwo.getCurrentVertexCoordinates(0)[2];

		shapeOne.rotateShape(-30, 'x');
		shapeOne.rotateShape(-30, 'y');
		shapeOne.rotateShape(-30, 'z');

		xNew = shapeOne.getCurrentVertexCoordinates(0)[0];
		yNew = shapeOne.getCurrentVertexCoordinates(0)[1];
		zNew = shapeOne.getCurrentVertexCoordinates(0)[2];

		assert (xOriginal > xNew);
		assert (yOriginal > yNew);
		assert (zOriginal > zNew);
		
	}

}
