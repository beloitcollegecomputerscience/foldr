package test;

import static org.junit.Assert.*;

import org.junit.*;
import foldr.utility.Tool;
import foldr.utility.Tool.ToolType;

public class ToolTest {

	static private ToolType defaultTool;
	private Tool testing;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		defaultTool = ToolType.SELECTION;
	}

	@Before
	/** Always start with a fresh new Tool object. */
	public void setUp() throws Exception {
		testing = new Tool();
	}

	@Test
	/** Verify that the constructor sets the default tool type correctly. 
	 *  Also does a first check of "getTool( )" and the default setting.
	 */
	public void testTool() {
		assertEquals( ToolType.SELECTION, testing.getCurrentTool() );
		assertEquals( defaultTool, testing.getCurrentTool() );
	}

	@Test
	/** Basic test on both setTool( ) and getTool( ).       	 */
	public void testSet_And_GetTool() {
		testing.setTool( ToolType.HINGE );
		assertEquals( ToolType.HINGE, testing.getCurrentTool() );
		testing.setTool( ToolType.POLYGON );
		// Check two gets in a row:
		assertEquals( ToolType.POLYGON, testing.getCurrentTool() );
		assertEquals( ToolType.POLYGON, testing.getCurrentTool() );
		// And check two sets in a row:
		testing.setTool( ToolType.ROTATE );
		testing.setTool( ToolType.EDGE_ALIGN );
		assertEquals( ToolType.EDGE_ALIGN, testing.getCurrentTool() );
	}

	@Test
	public void testUndo() {
		testing.setTool( ToolType.ROTATE );
		testing.setTool( ToolType.EDGE_ALIGN );
		testing.undo();
		assertEquals( ToolType.ROTATE, testing.getCurrentTool() );
	}

	// @Test    Not yet implemented.
	public void testGetToolIcon() {
		fail("Not yet implemented");
	}

	// @Test    Not yet implemented.
	public void testGetToolCursor() {
		fail("Not yet implemented");
	}

}
