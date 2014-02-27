package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import foldr.shape.Shape;
import foldr.shape.ShapeGroup;
import foldr.utility.AnimateMovement;

public class AnimateMovementTest {

	private AnimateMovement toMove;
	
	@Before
	public void setUp() {
	}
	
		
	@BeforeClass
	public static void setUpBeforeClass() {
	}


	@Test
	public void testAnimateMovement() {
		//Create a new shape group.
		Shape shapeToMove = new Shape();
		//TODO Write the getShapeGroup method
		ShapeGroup shapeGroupToMove = shapeToMove.getShapeGroup();

		// Set positive test coordinates .
		double[] testCoordinates = new double[3];
		testCoordinates[0] = 1.1;
		testCoordinates[1] = 2.2;
		testCoordinates[2] = 3.3;
		
		//Animate ShapeGroup
		toMove.moveShapeGroup(shapeGroupToMove, testCoordinates);
		//TODO Add the getter methods for coordinates.
		//Check shapeGroup coordinates.
		assertEquals(1.1,shapeGroupToMove.getX());
		assertEquals(2.2,shapeGroupToMove.getY());
		assertEquals(3.3,shapeGroupToMove.getZ());


		// Set negative test coordinates .
		testCoordinates[0] = -1.1;
		testCoordinates[1] = -2.2;
		testCoordinates[2] = -3.3;

		//Animate ShapeGroup
		toMove.moveShapeGroup(shapeGroupToMove, testCoordinates);
		//TODO Add the getter methods for coordinates.
		//Check shapeGroup coordinates.
		assertEquals(-1.1,shapeGroupToMove.getX());
		assertEquals(-2.2,shapeGroupToMove.getY());
		assertEquals(-3.3,shapeGroupToMove.getZ());
		
		//Set  all zero test coordinates.
		testCoordinates[0] = 0;
		testCoordinates[1] = 0;
		testCoordinates[2] = 0;

		//Animate ShapeGroup
		toMove.moveShapeGroup(shapeGroupToMove, testCoordinates);
		//TODO Add the getter methods for coordinates.
		//Check shapeGroup coordinates.
		assertEquals(0,shapeGroupToMove.getX());
		assertEquals(0,shapeGroupToMove.getY());
		assertEquals(0,shapeGroupToMove.getZ());
		
	}

}
