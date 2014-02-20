package test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.event.MouseEvent;

import org.junit.Before;
import org.junit.Test;

import foldr.shape.Shape;
import foldr.shape.ShapeCollection;

/**
 * JUnit test for ShapeCollection.
 * 
 * @author Hunter Elbourn & Jared Feldman
 *
 */
public class ShapeCollectionTest {

	@Before
	public void setUp() throws Exception {
		// Reset ShapeCollection before every test.
		ShapeCollection.resetList();
	}

	@Test
	public void testGetInstance() {

		// Create a local copy of the ShapeCollection.
		ShapeCollection shapesOnScreen = ShapeCollection.getInstance();

		// Assert local copy is same as getInstance();
		assertSame(shapesOnScreen, ShapeCollection.getInstance());
	}

	@Test
	public void testResetList() {

		// Assert ShapeCollection is empty.
		assertEquals(0, ShapeCollection.getInstance().size());

		// Add 2 new Shapes.
		ShapeCollection.getInstance().add(new Shape());
		ShapeCollection.getInstance().add(new Shape());

		// Assert ShapeCollection is size 2.
		assertEquals(2, ShapeCollection.getInstance().size());

		// Reset ShapeCollection.
		ShapeCollection.resetList();

		// Assert ShapeCollection is empty.
		assertEquals(0, ShapeCollection.getInstance().size());
	}

	@Test
	public void testGetShape() {

		// Create local copy of a new Shape and add to the ShapeCollection.
		Shape randomShape = new Shape();
		ShapeCollection.getInstance().add(randomShape);

		// Assert local copy is same as getShape().
		assertSame(randomShape, ShapeCollection.getShape(1));
	}

	@Test
	public void testFindShapeClicked() {

		// Create local copy of a new Shape and add to the ShapeCollection.
		Shape shapeClicked = new Shape();
		ShapeCollection.getInstance().add(shapeClicked);

		// Assert local copy is same as findShapeClicked().
		assertSame(shapeClicked,
				ShapeCollection.findShapeClicked(new MouseEvent(null, 0, 0, 0,
						0, 0, 0, false)));
	}

	@Test
	public void testIsColliding() {

		// Insert code to setup Shapes on screen with Shape 1 colliding with
		// other shapes.
		assertTrue(ShapeCollection.isColliding(1, new Point()));

		// Insert code to setup Shapes on screen with Shape 1 not colliding with
		// other shapes.
		assertFalse(ShapeCollection.isColliding(1, new Point()));

	}

}