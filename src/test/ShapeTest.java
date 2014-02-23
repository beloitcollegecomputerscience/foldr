package foldr.shape;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ShapeTest {
    private Shape shape;
    double [][] vertices  = new double[][] {
        {0, 0, 0}, {1, 0, 0}, {1, 1, 0}, {0, 1, 0},
        {0, 0, 1}, {1, 0, 1}, {1, 1, 1}, {0, 1, 1}
      };
      
      int [][] faceIndices = new int [][] {
        {0, 1, 2, 3}, {7, 6, 5, 4}, {0, 1, 5, 4}, 
        {1, 2, 6, 5}, {2, 3, 7, 6}, {3, 0, 4, 7} 
      };
      ShapeGroup sg;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	    
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	    shape = new Shape(vertices, faceIndices);
	    sg = new ShapeGroup();
	    shape.setGroup(sg);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		
	}
	
	@Test
	public void testVertexCount() {
	    assertEquals(vertices.length, shape.getVertexCount());
	}
	
	@Test
	public void testShapeGroup() {
	    assertEquals(sg, shape.getGroup());
	}
	
	@Test
	public void testHighlighted() {
	    assertFalse(shape.isHighlight());
	}
	
	@Test
	public void testFaceSet() {
	    assertEquals(6, shape.getFaceSet().getFaceAttributes().getListLength());
	}

}
