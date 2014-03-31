package foldr.main;

import static de.jreality.shader.CommonAttributes.DIFFUSE_COLOR;
import static de.jreality.shader.CommonAttributes.LINE_SHADER;
import static de.jreality.shader.CommonAttributes.POINT_RADIUS;
import static de.jreality.shader.CommonAttributes.POINT_SHADER;
import static de.jreality.shader.CommonAttributes.TUBE_RADIUS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import de.jreality.math.MatrixBuilder;
import de.jreality.plugin.JRViewer;
import de.jreality.scene.Appearance;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.Viewer;
import de.jreality.shader.DefaultPointShader;
import de.jreality.util.SceneGraphUtility;
import foldr.shape.Shape;
import foldr.shape.ShapeCollection;
import foldr.shape.ShapeGroup;
import foldr.utility.AnimateMovement;

/**
 * A simple class showing how to use a {@link JRViewer} to get a viewing
 * component which is then packed into another frame.
 * 
 * @author Charles Gunn
 * 
 */
public class GUI extends JFrame implements ActionListener {

	static ShapeCollection allShapes = ShapeCollection.getInstance();

	// the main scene graph component. All other SGC's will be a child of this.
	static SceneGraphComponent topScene = SceneGraphUtility
			.createFullSceneGraphComponent("topScene");
	Scanner input = new Scanner(System.in);

	// the swing components to create the jreality frame
	protected JFrame f;
	protected JDesktopPane desktop = new JDesktopPane();
	protected JInternalFrame jRealityFrame;

	// the swing components to create the menu bar
	protected JPanel menuBarPane;
	protected JMenuBar menuBar;
	protected JMenu fileMenu, editMenu, foldingMenu, windowMenu, helpMenu;
	protected JMenuItem fileOpen, fileNew, fileSave, fileSaveAs, fileExport,
			fileClose;
	protected JMenuItem editCopy, editCut, editPaste, editDelete,
			editSelectAll, editResizeShape;
	protected JMenuItem foldingThirty, foldingFortyFive, foldingNinety,
			foldingCustomAngle, foldingEdgeSelect, foldingPointSelect,
			foldingFoldShapes, foldingConnectShapes, foldingDetachShapes;
	protected JMenuItem windowShowTop, windowShowBack, windowShowLeft,
			windowShowHideTools, windowShowHideInfo, windowChangePerspective,
			windowSaveLoadPerspective, windowResizePerspective;
	protected JMenuItem helpManual, helpQuickStartGuide;

	// This method creates the menu bar
	protected void initMenuBarPane() {
		menuBarPane = new JPanel();

		// File menu items
		fileNew = new JMenuItem("New");
		fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.META_MASK));
		fileNew.addActionListener(this);
		fileOpen = new JMenuItem("Open");
		fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.META_MASK));
		fileOpen.addActionListener(this);
		fileSave = new JMenuItem("Save");
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.META_MASK));
		fileSave.addActionListener(this);
		fileSaveAs = new JMenuItem("Save As");
		fileSaveAs.addActionListener(this);
		fileExport = new JMenuItem("Export");
		fileExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.META_MASK));
		fileExport.addActionListener(this);
		fileClose = new JMenuItem("Close");
		fileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.META_MASK));
		fileClose.addActionListener(this);

		// Edit menu items
		editCopy = new JMenuItem("Copy");
		editCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.META_MASK));
		editCopy.addActionListener(this);
		editCut = new JMenuItem("Cut");
		editCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.META_MASK));
		editCut.addActionListener(this);
		editPaste = new JMenuItem("Paste");
		editPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.META_MASK));
		editPaste.addActionListener(this);
		editDelete = new JMenuItem("Delete");
		editDelete.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_BACK_SPACE, ActionEvent.META_MASK));
		editDelete.addActionListener(this);
		editSelectAll = new JMenuItem("Select All");
		editSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.META_MASK));
		editSelectAll.addActionListener(this);
		editResizeShape = new JMenuItem("Resize Shape");
		editResizeShape.addActionListener(this);
		//
		// editCopy.setEnabled(false);
		// editCut.setEnabled(false);
		// editPaste.setEnabled(false);
		// editDelete.setEnabled(false);
		// editSelectAll.setEnabled(false);
		// editResizeShape.setEnabled(false);

		// Folding/Shapes menu items
		foldingThirty = new JMenuItem("Rotate 30 Degrees");
		foldingThirty.addActionListener(this);
		foldingFortyFive = new JMenuItem("Rotate 45 Degrees");
		foldingFortyFive.addActionListener(this);
		foldingNinety = new JMenuItem("Rotate 90 Degrees");
		foldingNinety.addActionListener(this);
		foldingCustomAngle = new JMenuItem("Custom Angle");
		foldingCustomAngle.addActionListener(this);
		foldingEdgeSelect = new JMenuItem("Edge Select");
		foldingEdgeSelect.addActionListener(this);
		foldingPointSelect = new JMenuItem("Point Select");
		foldingPointSelect.addActionListener(this);
		foldingFoldShapes = new JMenuItem("Fold Shapes");
		foldingFoldShapes.addActionListener(this);
		foldingConnectShapes = new JMenuItem("Connect Shapes");
		foldingConnectShapes.addActionListener(this);
		foldingDetachShapes = new JMenuItem("Detach Shapes");
		foldingDetachShapes.addActionListener(this);

		// foldingThirty.setEnabled(false);
		// foldingFortyFive.setEnabled(false);
		// foldingNinety.setEnabled(false);
		// foldingCustomAngle.setEnabled(false);
		// foldingEdgeSelect.setEnabled(false);
		// foldingPointSelect.setEnabled(false);
		// foldingFoldShapes.setEnabled(false);
		// foldingConnectShapes.setEnabled(false);
		// foldingDetachShapes.setEnabled(false);

		// Window menu items
		windowShowTop = new JMenuItem("Show Top");
		windowShowTop.addActionListener(this);
		windowShowBack = new JMenuItem("Show Back");
		windowShowBack.addActionListener(this);
		windowShowLeft = new JMenuItem("Show Left");
		windowShowLeft.addActionListener(this);
		windowShowHideTools = new JMenuItem("Show/Hide Tools");
		windowShowHideTools.addActionListener(this);
		windowShowHideInfo = new JMenuItem("Show/Hide Information Panel");
		windowShowHideInfo.addActionListener(this);
		windowChangePerspective = new JMenuItem("Change Perspective Layout");
		windowChangePerspective.addActionListener(this);
		windowSaveLoadPerspective = new JMenuItem(
				"Save/Load Perspective Layout");
		windowSaveLoadPerspective.addActionListener(this);
		windowResizePerspective = new JMenuItem("Resize Perspective");
		windowResizePerspective.addActionListener(this);

		// Help menu items
		helpManual = new JMenuItem("Manual");
		helpManual.addActionListener(this);
		helpQuickStartGuide = new JMenuItem("Quick Start Guide");
		helpQuickStartGuide.addActionListener(this);

		// Set up the file menu
		fileMenu = new JMenu("File");
		fileMenu.add(fileNew);
		fileMenu.add(fileOpen);
		fileMenu.add(new JSeparator());
		fileMenu.add(fileSave);
		fileMenu.add(fileSaveAs);
		fileMenu.add(new JSeparator());
		fileMenu.add(fileExport);
		fileMenu.add(fileClose);

		// Set up the edit menu
		editMenu = new JMenu("Edit");
		editMenu.add(editCopy);
		editMenu.add(editCut);
		editMenu.add(editPaste);
		editMenu.add(editDelete);
		editMenu.add(new JSeparator());
		editMenu.add(editSelectAll);
		editMenu.add(editResizeShape);

		// Set up the folding/shapes menu
		foldingMenu = new JMenu("Folding/Shapes");
		foldingMenu.add(foldingThirty);
		foldingMenu.add(foldingFortyFive);
		foldingMenu.add(foldingNinety);
		foldingMenu.add(foldingCustomAngle);
		foldingMenu.add(new JSeparator());
		foldingMenu.add(foldingEdgeSelect);
		foldingMenu.add(foldingPointSelect);
		foldingMenu.add(new JSeparator());
		foldingMenu.add(foldingFoldShapes);
		foldingMenu.add(foldingConnectShapes);
		foldingMenu.add(foldingDetachShapes);

		// Set up the window menu
		windowMenu = new JMenu("Window");
		windowMenu.add(windowShowTop);
		windowMenu.add(windowShowBack);
		windowMenu.add(windowShowLeft);
		windowMenu.add(new JSeparator());
		windowMenu.add(windowShowHideTools);
		windowMenu.add(windowShowHideInfo);
		windowMenu.add(new JSeparator());
		windowMenu.add(windowChangePerspective);
		windowMenu.add(windowSaveLoadPerspective);
		windowMenu.add(windowResizePerspective);

		// Set up the help menu
		helpMenu = new JMenu("Help");
		helpMenu.add(helpManual);
		helpMenu.add(helpQuickStartGuide);

		// Create the menuBar to contain the menus
		menuBar = new JMenuBar();

		// Add the menus to the bar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(foldingMenu);
		menuBar.add(windowMenu);
		menuBar.add(helpMenu);

		// Add the menu bar to the appropriate pane
		menuBarPane.setLayout(new GridLayout(1, 1));
		menuBarPane.add(menuBar);
	}

	// Action listener. For now, this method is just a placeholder.
	public void actionPerformed(ActionEvent e) {
		String theCommand = e.getActionCommand();
		System.out.println("You clicked " + theCommand);
		if (theCommand.equals("Connect Shapes")) {

			// TODO figure out how to do this by clicking on the shapes!
			// use the console to grab the shapes and vertices to connect
			// the number of a shape is its index number where it's stored in
			// ShapeCollection
			System.out.println("Enter the number of the first shape");
			int firstShape = input.nextInt();
			System.out.println("Enter the number of the vertex");
			int firstVertex = input.nextInt();
			System.out.println("Enter the number of the second shape");
			int secondShape = input.nextInt();
			System.out.println("Enter the number of the vertex");
			int secondVertex = input.nextInt();

			// grab the 2 shapes the user inputed
			Shape shapeOne = allShapes.getShapeFromCollection(firstShape);
			Shape shapeTwo = allShapes.getShapeFromCollection(secondShape);

			System.out.println("Connecting...");

			// figure out how much to move shape one in each direction
			double targetX = shapeTwo.getCurrentVertexCoordinates(secondVertex)[0]
					- shapeOne.getCurrentVertexCoordinates(firstVertex)[0];
			double targetY = shapeTwo.getCurrentVertexCoordinates(secondVertex)[1]
					- shapeOne.getCurrentVertexCoordinates(firstVertex)[1];
			double targetZ = shapeTwo.getCurrentVertexCoordinates(secondVertex)[2]
					- shapeOne.getCurrentVertexCoordinates(firstVertex)[2];

			double[] endPoint = new double[3];
			endPoint[0] = targetX;
			endPoint[1] = targetY;
			endPoint[2] = targetZ;

			ShapeGroup shapeGroupToMove = shapeOne.getGroup();
			// animate all shapes in the group
			shapeGroupToMove.animateGroup(endPoint);
			// put the newly glued shapes into the same group
			shapeGroupToMove.resetGroup(shapeTwo.getGroup());
		} else {
			System.out.println("The command is not yet implemented!");
		}
	}

	// Create the jReality canvas
	public void createJRCanvas() {
		JRViewer v = JRViewer.createJRViewer(topScene);

		// call this to avoid creating a Frame
		v.startupLocal();
		Viewer viewer = v.getViewer();

		jRealityFrame = new JInternalFrame("jReality canvas");
		jRealityFrame.setSize(600, 400);
		jRealityFrame.setLayout(new GridLayout());
		jRealityFrame.add((Component) viewer.getViewingComponent());
		jRealityFrame.setResizable(true);
		jRealityFrame.setVisible(true);
	}

	private void initPanesAndGui() {
		// create the jReality canvas and menu bars
		createJRCanvas();
		initMenuBarPane();

		// stick them both in a desktop pane
		desktop.setLayout(new BorderLayout());
		desktop.add(menuBarPane, "North");
		desktop.add(jRealityFrame);
		pack();
		// setSize(800, 645); // Has to happen after "pack()"
		// setVisible(true);

		// Create the top frame to store desktop
		f = new JFrame("Polyhedra");
		f.setLayout(new GridLayout());
		f.add(desktop);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setVisible(true);

	}

	private static GUI theProgram;

	public static void main(String[] args) {

		theProgram = new GUI();
		theProgram.initPanesAndGui();
		theProgram.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Appearance ap = topScene.getAppearance();
		ap.setAttribute(LINE_SHADER + "." + DIFFUSE_COLOR, Color.yellow);
		ap.setAttribute(LINE_SHADER + "." + TUBE_RADIUS, .05);
		ap.setAttribute(POINT_SHADER + "." + DIFFUSE_COLOR, Color.red);
		ap.setAttribute(POINT_SHADER + "." + POINT_RADIUS, .1);

		// put shapes on the canvas
		Shape shapeOne = new Shape(3, topScene);
		Shape shapeTwo = new Shape(4, topScene);
		Shape shapeThree = new Shape(5, topScene);
		Shape shapeFour = new Shape(6, topScene);

		// translate the shapes around so that the animation tests actually are interesting
		shapeOne.translate(1, 0, 0);
		shapeTwo.translate(2, 1, 1);
		shapeThree.translate(-2, -1, 0);

	}

}