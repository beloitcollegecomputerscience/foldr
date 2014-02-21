package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import foldr.shape.ShapeGroup;
import foldr.utility.AnimateMovement;

public class AnimateMovementTest {

	AnimateMovement toMove = new AnimateMovement();

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
		ShapeGroup shapeGroupToMove = shapeToMove.getShapeGroup();

		// Set positive test coordinates .
		double[] testCordinates = new double[3];
		testCordinates[0] = 1.1;
		testCordinates[1] = 2.2;
		testCordinates[2] = 3.3;
		
		//Animate ShapeGroup
		toMove.moveShapeGroup(shapeGroupToMove, testCoordinates);
		//TODO Add the getter methods for coordinates.
		//Check shapeGroup coordinates.
		assertEquals(shapeGroupToMove.getX() == 1.1);
		assertEquals(shapeGroupToMove.getY() == 2.2);
		assertEquals(shapeGroupToMove.getZ() == 3.3);

		// Set negative test coordinates .
		testCordinates[0] = -1.1;
		testCordinates[1] = -2.2;
		testCordinates[2] = -3.3;

		//Animate ShapeGroup
		toMove.moveShapeGroup(shapeGroupToMove, testCoordinates);
		//TODO Add the getter methods for coordinates.
		//Check shapeGroup coordinates.
		assertEquals(shapeGroupToMove.getX() == -1.1);
		assertEquals(shapeGroupToMove.getY() == -2.2);
		assertEquals(shapeGroupToMove.getZ() == -3.3);
		
		//Set  all zero test coordinates.
		testCordinates[0] = 0;
		testCordinates[1] = 0;
		testCordinates[2] = 0;

		//Animate ShapeGroup
		toMove.moveShapeGroup(shapeGroupToMove, testCoordinates);
		//TODO Add the getter methods for coordinates.
		//Check shapeGroup coordinates.
		assertEquals(shapeGroupToMove.getX() == 0);
		assertEquals(shapeGroupToMove.getY() == 0);
		assertEquals(shapeGroupToMove.getZ() == 0);
		
	}

}
