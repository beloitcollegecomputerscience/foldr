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
		testing.setTool( ToolType.JOIN_EDGE );
		assertEquals( ToolType.JOIN_EDGE, testing.getCurrentTool() );
		testing.setTool( ToolType.ADD_SHAPE );
		assertEquals( ToolType.ADD_SHAPE, testing.getCurrentTool() );
		testing.setTool( ToolType.CAMERA_MOVE );
		testing.setTool( ToolType.SELECTION );
		assertEquals( ToolType.SELECTION, testing.getCurrentTool() );
	}

	@Test
	public void testUndo() {
		testing.setTool( ToolType.SELECTION );
		testing.setTool( ToolType.ADD_SHAPE );
		testing.undo();
		assertEquals( ToolType.SELECTION, testing.getCurrentTool() );
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
