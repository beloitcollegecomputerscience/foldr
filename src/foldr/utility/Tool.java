package foldr.utility;

import java.awt.Cursor;

import javax.swing.ImageIcon;

import foldr.shape.ShapeCollection;

/**
 * <p>
 * <b>Alex :</b>Contains all the ToolTypes and allow another class to get or set
 * the tool. Only the GUI should set the tool when the user has selected one,
 * and other classes such as the {@link ShapeCollection} class get the tool so
 * as to check its type to know how to manipulate the shape and/or perspective.
 * </p>
 * 
 * @author Alex 
 * @author Darrah
 * 
 */
public class Tool {
	
	/** This class is responsible for knowing what the tool is that has been selected
	 *  by the user. It has nothing to do with the GUI presentation of that tool, just
	 *  remembers what it is.
	 *  
	 *  Optionally, we could make this class know about the icons to use for the various
	 *  tools when they are or are not selected, and/or what cursors to use when those
	 *  tools have been selected. If you need these capabilities, let me know.
	 *  
	 *  @author Darrah Chavey
	 *
	 */
	
	public enum ToolType { SELECTION, POLYGON, EDGE_ALIGN, JOIN, SERIES_JOIN, HINGE, ROTATE, 
		COLOR, CAMERA_MOVE,  }
	
	private ToolType previousTool, selectedTool;
	
	/** Create a Tool class. Default tool is the SELECTION arrow. */
	public Tool( ) {
		previousTool = ToolType.SELECTION;
		selectedTool = ToolType.SELECTION;
	}
	
	/** Change the selected tool to the one suggested. 
	 * 
	 * @param chosenTool The tool which is to be activated.
	 */
	public void setTool( ToolType chosenTool ) {
		previousTool = selectedTool;
		selectedTool = chosenTool;
	}
	
	/** Get the currently selected tool. */
	public ToolType getCurrentTool( ) {
		return selectedTool;
	}
	
	/** Undoes the previous "setTool". Does not support multiple undo's. */
	public void undo( ) {
		selectedTool = previousTool;	// And previous stays the same.
	}
	
	// Optional methods we could implement, as discussed in the Class Overview
	
	static public ImageIcon getToolIcon( ToolType whichTool, boolean activated ) {
		return null;	// Not implemented
	}
	
	/** Get the cursor associated with the current tool. */
	static public Cursor getToolCursor( ) {
		return null;	// Not implemented
	}

}
