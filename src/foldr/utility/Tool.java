package foldr.utility;

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
 * 
 */
public class Tool {

	/**
	 * <p>
	 * <b>Alex :</b> A list of the different tool types available. ToopTypes
	 * represent tools that the user can use to manipulate the shapes or
	 * perspectives. This list will contain all the tools found in the toolbar
	 * and certain others found in the menu, depending on their specific
	 * function.
	 * </p>
	 * 
	 * 
	 */
	public enum ToolType {

	}

	/**
	 * <p>
	 * <b>Dan :</b> A reference to the current tool which is chosen by the user.
	 * Will be used to determine what happens upon shape clicks.
	 * </p>
	 * 
	 */
	private ToolType currentTool;

	/**
	 * 
	 */
	public Tool() {
	}

	/**
	 * <p>
	 * <b>Alex :</b> Returns the current tool type. This can be called after the
	 * SHapeCollection find the clicked shape. It would check which tool was
	 * used to click the shape with this method to determine how to manipulate
	 * the shape.
	 * </p>
	 * 
	 * @return the currentTool
	 */
	public ToolType getCurrentTool() {
		return currentTool;
	}

	/**
	 * <p>
	 * <b>Hunter :</b> Would most likely have to call upon the
	 * {@link Tool#getCurrentTool()} then set the current tool to the one select
	 * through the side panel.
	 * </p>
	 * 
	 * @param currentTool
	 *            the currentTool to set
	 */
	public void setCurrentTool(ToolType currentTool) {
		this.currentTool = currentTool;
	}

}
