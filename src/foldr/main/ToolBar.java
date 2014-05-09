package foldr.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.jreality.scene.SceneGraphComponent;
import foldr.shape.Shape;
import foldr.utility.Tool;
import foldr.utility.Tool.ToolType;

/**
 * 
 * @author Nick, Hunter, and Tyler
 * 
 */

public class ToolBar implements ActionListener {
	// Testing
	public static Tool currentTool = new Tool();
	protected JDialog dialog, popUpDialog;
	protected JButton paletteSelect, paletteMove, paletteFill, paletteJoinEdge,
	paletteJoinPoint, paletteErase, palettePoint, paletteLine,
	paletteShape, paletteMoveCamera;
	protected JPanel palettePane, popUp;
	JTextField textField;
	SceneGraphComponent scene;
	
	protected void initPalettePane(GUI theProgram) {

		palettePane = new JPanel();
		palettePane.setBounds(300, 300, 250, 500);
		palettePane.setLayout(new GridLayout(3, 3));

		// Set up the button images
		ImageIcon selectImage = new ImageIcon("Icons/selectImage.png");
		ImageIcon moveImage = new ImageIcon("Icons/moveImage.png");
		ImageIcon fillImage = new ImageIcon("Icons/fillImage.png");
		ImageIcon joinEdgeImage = new ImageIcon("Icons/joinEdge.png");
		ImageIcon eraseImage = new ImageIcon("Icons/erase.png");
		ImageIcon pointImage = new ImageIcon("Icons/addPoint.png");
		ImageIcon lineImage = new ImageIcon("Icons/addLine.png");
		ImageIcon shapeImage = new ImageIcon("Icons/shape.png");
		ImageIcon moveCameraImage = new ImageIcon("Icons/moveCamera.png");

		// create the JButtons, passing in the image
		paletteSelect = new JButton(selectImage);
		paletteMove = new JButton(moveImage);
		paletteFill = new JButton(fillImage);
		paletteJoinEdge = new JButton(joinEdgeImage);
		// paletteJoinPoint = new JButton(pointImage);
		paletteErase = new JButton(eraseImage);
		palettePoint = new JButton(pointImage);
		paletteLine = new JButton(lineImage);
		paletteShape = new JButton(shapeImage);
		paletteMoveCamera = new JButton(moveCameraImage);

		// Set names so that the actionListener can reference them
		paletteSelect.setName("select");
		paletteMove.setName("move");
		paletteFill.setName("fill");
		paletteJoinEdge.setName("joinEdge");
		paletteErase.setName("erase");
		palettePoint.setName("point");
		paletteLine.setName("line");
		paletteShape.setName("shape");
		paletteMoveCamera.setName("moveCamera");

		// add tool tips
		paletteSelect
				.setToolTipText("Selects a shape or shapes to manipulate or move later. Can select individual points, lines, and shapes. If you click and drag you can select multiple points, lines, and shapes.");
		paletteMove.setToolTipText("Allows user to move selected object.");
		paletteFill
				.setToolTipText("Allows user to fill shapes with a particular color. When selected a color option will appear in the information panel.");
		paletteJoinEdge
				.setToolTipText("Allows the user to join together the edge of two shapes, creating a hinge along an edge.");
		paletteErase.setToolTipText("Destroys selected shape");
		palettePoint
				.setToolTipText("When selected allows the user to create a single point on the model by clicking on a particular part of any of the perspectives.");
		paletteLine
				.setToolTipText("When selected allows the user to click-and-drag to create a line in any of the views/perspectives.");
		paletteShape
				.setToolTipText("When selected allows the user to click-and-drag to create a line in any of the perspectives that is not the freeview perspective. By clicking and holding on this tool you can select more polygons from a drop-down menu, or select a ���������������������������custom��������������������������� option which would allow you to specify how many faces your object has.");
		paletteMoveCamera
				.setToolTipText(" By using using WASD, and the scroll-wheel the user can move the Freeview camera along the three dimensions.");

		// add them to the pane
		palettePane.add(paletteSelect);
		palettePane.add(paletteMove);
		palettePane.add(paletteFill);
		palettePane.add(paletteJoinEdge);
		// palettePane.add(paletteJoinPoint);
		palettePane.add(paletteErase);
		palettePane.add(palettePoint);
		palettePane.add(paletteLine);
		palettePane.add(paletteShape);
		palettePane.add(paletteMoveCamera);

		// Add action listeners
		paletteSelect.addActionListener(this);
		paletteMove.addActionListener(this);
		paletteFill.addActionListener(this);
		paletteJoinEdge.addActionListener(this);
		paletteErase.addActionListener(this);
		palettePoint.addActionListener(this);
		paletteLine.addActionListener(this);
		paletteShape.addActionListener(this);
		paletteMoveCamera.addActionListener(this);

		dialog = new JDialog(theProgram, "Tools", false);
		dialog.setPreferredSize(new Dimension(196, 350));
		dialog.add(palettePane);
		dialog.pack();
		dialog.setLocation(8, 170);
		dialog.setVisible(true);
	}
	
	/*
	 *  * Creates a pop up box when 'shape' button is clicked on the tool bar.
	 * Allows the user to enter the number of sides they want a polygon to have
	 * which is being added to the scene.
	 */
	protected void popUpPanel() {
		JPanel popUp = new JPanel();

		textField = new JTextField(1);

		JButton selectNumSides = new JButton("OK");
		selectNumSides.addActionListener((ActionListener) this);
		selectNumSides.setName("selectNumSides");

		popUp.add(textField);
		popUp.add(selectNumSides);

		popUpDialog = new JDialog(dialog, "Polygon Creator");
		popUpDialog.add(popUp);
		popUpDialog.pack();
		popUpDialog.setLocation(8, 170);
		popUpDialog.setVisible(true);
	}
	
	
	/**
	 * Register the scene being used in the GUI so we can add shapes to it.
	 * @param topScene
	 */
	public void registerTopScene(SceneGraphComponent topScene) {
		scene = topScene;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton theCommand = (JButton) e.getSource();
		String buttonName = theCommand.getName();

		if (buttonName.equals("select")) {
			doSelect();
		} else if (buttonName.equals("move")) {
			doMove();
		} else if (buttonName.equals("fill")) {
			doFill();
		} else if (buttonName.equals("joinEdge")) {
			doJoinEdge();
		} else if (buttonName.equals("erase")) {
			doErase();
		} else if (buttonName.equals("point")) {
			doPoint();
		} else if (buttonName.equals("line")) {
			doLine();
		} else if (buttonName.equals("shape")) {
			doShape();
		} else if (buttonName.equals("selectNumSides")) {
			doSelectNumSides(textField.getText(), scene);
		} else if (buttonName.equals("moveCamera")) {
			doMoveCamera();
		}	
	}

	public void doSelect() {
		System.out.println("Previous tool was: " + currentTool.getCurrentTool());
		currentTool.setTool(ToolType.SELECTION);
		System.out.println("Current tool is now: " + currentTool.getCurrentTool());

	}

	public void doMove() {
		System.out.println("Previous tool was: " + currentTool.getCurrentTool());
		currentTool.setTool(ToolType.MOVE);
		System.out.println("Current tool is now: " + currentTool.getCurrentTool());
	}

	public void doFill() {
		System.out.println("Previous tool was: " + currentTool.getCurrentTool());
		currentTool.setTool(ToolType.FILL);
		System.out.println("Current tool is now: " + currentTool.getCurrentTool());
	}

	public void doJoinEdge() {
		System.out.println("Previous tool was: " + currentTool.getCurrentTool());
		currentTool.setTool(ToolType.MOVE);
		System.out.println("Current tool is now: " + currentTool.getCurrentTool());
	}

	public void doErase() {
		System.out.println("Previous tool was: " + currentTool.getCurrentTool());
		currentTool.setTool(ToolType.ERASE);
		System.out.println("Current tool is now: " + currentTool.getCurrentTool());
	}

	public void doPoint() {
		System.out.println("Previous tool was: " + currentTool.getCurrentTool());
		currentTool.setTool(ToolType.ADD_POINT);
		System.out.println("Current tool is now: " + currentTool.getCurrentTool());
	}

	public void doLine() {
		System.out.println("Previous tool was: " + currentTool.getCurrentTool());
		currentTool.setTool(ToolType.ADD_LINE);
		System.out.println("Current tool is now: " + currentTool.getCurrentTool());
	}

	public void doShape() {
		popUpPanel();
	}
	
	public void doSelectNumSides(String num, SceneGraphComponent topScene) {
		
		// parse the string to an integer
		int numSides = Integer.parseInt(num);
		
		//check if number is within a certain legal range
		//TODO catch errors for input that aren't integers
		if (numSides < 3 || numSides > 10) {
			System.out.println("Please submit a number of sides between 3 and 10.");
			System.out.println("Defaulting to 3 sides.");
			// if not, default to a triangle
			numSides = 3;
		}
		//put the shape onto the screen
		Shape newShape = new Shape(numSides, topScene);
		
		//close the window
		popUpDialog.setVisible(false);
	}


	public void doMoveCamera() {
		System.out.println("Previous tool was: " + currentTool.getCurrentTool());
		currentTool.setTool(ToolType.CAMERA_MOVE);
		System.out.println("Current tool is now: " + currentTool.getCurrentTool());
	}

}
