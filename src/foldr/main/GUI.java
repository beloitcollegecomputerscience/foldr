package foldr.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import de.jreality.geometry.Primitives;
import de.jreality.math.MatrixBuilder;
import de.jreality.plugin.JRViewer;
import de.jreality.scene.IndexedFaceSet;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.Viewer;
import de.jreality.util.SceneGraphUtility;
import foldr.shape.Shape;
import foldr.shape.ShapeCollection;
import foldr.shape.ShapeGroup;
import foldr.utility.CustomCamera;
import foldr.utility.Vector3d;

/**
 *
 * 
 */
public class GUI extends JFrame implements ActionListener, MouseListener,
		MouseMotionListener, MouseWheelListener {

	private static final long serialVersionUID = 1L;

	Scanner input = new Scanner(System.in);

	ShapeCollection allShapes = ShapeCollection.getInstance();

	// the main scene graph component. All other SGC's will be a child of this.
	static SceneGraphComponent scene = SceneGraphUtility
			.createFullSceneGraphComponent("scene");

	// the swing components to create the jreality frame
	protected JFrame f;
	protected JDesktopPane desktop = new JDesktopPane();

	private JPanel mainPanel, freeViewPanel, topPanel, sidePanel, frontPanel;
	protected JPanel palettePane;
	
	//the viewer components that render the difference camera views
	JRViewer freeJRViewer, topJRViewer, sideJRViewer, frontJRViewer;
	Viewer freeViewer, topViewer, sideViewer, frontViewer;
	// the camera containers for the different cameras of the different views
	SceneGraphComponent freeCameraContainer, topCameraContainer,
			sideCameraContainer, frontCameraContainer;

	CustomCamera frontCamera = new CustomCamera(false);
	CustomCamera sideCamera = new CustomCamera(false);
	CustomCamera topCamera = new CustomCamera(false);
	CustomCamera freeCamera = new CustomCamera(true);

	// Capture the mouse location during drag events
	Point mouseDragLocation = null;

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
	protected JDialog dialog;
	protected JButton paletteSelect, paletteMove, paletteFill, paletteJoinEdge,
	paletteJoinPoint, paletteErase, palettePoint, paletteLine,
	paletteShape, palettePanCamera, paletteFlymode,
	paletteRotateCamera, paletteMoveCamera;
	
	ActionManager actionManager = new ActionManager();

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
//	public void actionPerformed(ActionEvent e) {
//		String theCommand = e.getActionCommand();
//		System.out.println("You clicked " + theCommand);
//		if (theCommand.equals("Connect Shapes")) {
//
//			// TODO figure out how to do this by clicking on the shapes!
//			// use the console to grab the shapes and vertices to connect
//			// the number of a shape is its index number where it's stored in
//			// ShapeCollection
//			System.out.println("Enter the number of the first shape");
//			int firstShape = input.nextInt();
//			System.out.println("Enter the number of the vertex");
//			int firstVertex = input.nextInt();
//			System.out.println("Enter the number of the second shape");
//			int secondShape = input.nextInt();
//			System.out.println("Enter the number of the vertex");
//			int secondVertex = input.nextInt();
//
//			// grab the 2 shapes the user inputed
//			Shape shapeOne = allShapes.getShapeFromCollection(firstShape);
//			Shape shapeTwo = allShapes.getShapeFromCollection(secondShape);
//
//			System.out.println("Connecting...");
//
//			// animate it
//			connectTwoShapes(shapeOne, firstVertex, shapeTwo, secondVertex);
//
//		} else {
//			System.out.println("The command is not yet implemented!");
//		}
//	}

	
	/**
	 * Animates connecting two shapes at a vertex. The entire ShapeGroup is
	 * moved as if it is one shape.
	 * 
	 * @param shapeOne
	 *            the first shape you want to connect
	 * @param vertexOne
	 *            the vertex on the first shape that you want to connect to.
	 * @param shapeTwo
	 *            the second shape you want to connect
	 * @param vertexTwo
	 *            the vertex on the second shape that you want to connect to.
	 */
	public void connectTwoShapes(Shape shapeOne, int vertexOne,
			Shape shapeTwo, int vertexTwo) {

		// store original coordinates for error calculation
		double originalX = shapeOne.getShapeSGC().getTransformation()
				.getMatrix()[3];
		double originalY = shapeOne.getShapeSGC().getTransformation()
				.getMatrix()[7];
		double originalZ = shapeOne.getShapeSGC().getTransformation()
				.getMatrix()[11];

		// figure out how much to move the first shape in each direction
		double targetX = shapeTwo.getCurrentVertexCoordinates(vertexTwo)[0]
				- shapeOne.getCurrentVertexCoordinates(vertexOne)[0];
		double targetY = shapeTwo.getCurrentVertexCoordinates(vertexTwo)[1]
				- shapeOne.getCurrentVertexCoordinates(vertexOne)[1];
		double targetZ = shapeTwo.getCurrentVertexCoordinates(vertexTwo)[2]
				- shapeOne.getCurrentVertexCoordinates(vertexOne)[2];

		// set the target coordinates
		double[] endPoint = new double[3];
		endPoint[0] = targetX;
		endPoint[1] = targetY;
		endPoint[2] = targetZ;

		// animate all shapes in the group
		ShapeGroup shapeGroupToMove = shapeOne.getGroup();
		shapeGroupToMove.animateGroup(endPoint);
		// put the newly glued shapes into the same group
		shapeGroupToMove.resetGroup(shapeTwo.getGroup());

		// print out error
		double errorX = shapeOne.getShapeSGC().getTransformation().getMatrix()[3]
				- (originalX + targetX);
		double errorY = shapeOne.getShapeSGC().getTransformation().getMatrix()[7]
				- (originalY + targetY);
		double errorZ = shapeOne.getShapeSGC().getTransformation().getMatrix()[11]
				- (originalZ + targetZ);
		// System.out.println("X error: " + errorX + ", Y error: " + errorY +
		// ", Z error: " + errorZ);

	}

	// Create the jReality viewers for each panel
	public void createJRViewers() {
		// TESTING with a visible shape @TODO: Remove this.
		IndexedFaceSet octo = Primitives.regularPolygon(8);
		SceneGraphComponent octoOne = SceneGraphUtility
				.createFullSceneGraphComponent("octogon1");
		octoOne.setGeometry(octo);
		scene.addChild(octoOne);

		// Setting up the free view
		freeJRViewer = new JRViewer();
		freeJRViewer.setContent(scene);
		freeJRViewer.startupLocal();
		freeViewer = freeJRViewer.getViewer();
		freeCameraContainer = (SceneGraphComponent) freeViewer.getCameraPath()
				.get(freeViewer.getCameraPath().getLength() - 2);
		freeViewPanel.setLayout(new GridLayout());
		freeViewPanel.add((Component) freeViewer.getViewingComponent());
		freeViewPanel.setVisible(true);
		freeViewPanel.getComponent(0).addMouseMotionListener(this);
		freeViewPanel.getComponent(0).addMouseListener(this);
		freeViewPanel.getComponent(0).addMouseWheelListener(this);
		freeViewPanel.getComponent(0).setName("freeViewPanel");

		// Setting up the top view
		topJRViewer = new JRViewer();
		topJRViewer.setContent(scene);
		topJRViewer.startupLocal();
		topViewer = topJRViewer.getViewer();
		topCameraContainer = (SceneGraphComponent) topViewer.getCameraPath()
				.get(topViewer.getCameraPath().getLength() - 2);
		topPanel.setLayout(new GridLayout());
		topPanel.add((Component) topViewer.getViewingComponent());
		topPanel.setVisible(true);
		topPanel.getComponent(0).addMouseMotionListener(this);
		topPanel.getComponent(0).addMouseListener(this);
		topPanel.getComponent(0).addMouseWheelListener(this);
		topPanel.getComponent(0).setName("topPanel");

		// Setting up the side view
		sideJRViewer = new JRViewer();
		sideJRViewer.setContent(scene);
		sideJRViewer.startupLocal();
		sideViewer = sideJRViewer.getViewer();
		sideCameraContainer = (SceneGraphComponent) sideViewer.getCameraPath()
				.get(sideViewer.getCameraPath().getLength() - 2);
		sidePanel.setLayout(new GridLayout());
		sidePanel.add((Component) sideViewer.getViewingComponent());
		sidePanel.setVisible(true);
		sidePanel.getComponent(0).addMouseMotionListener(this);
		sidePanel.getComponent(0).addMouseListener(this);
		sidePanel.getComponent(0).addMouseWheelListener(this);
		sidePanel.getComponent(0).setName("sidePanel");

		// Setting up the front view
		frontJRViewer = new JRViewer();
		frontJRViewer.setContent(scene);
		frontJRViewer.startupLocal();
		frontViewer = frontJRViewer.getViewer();
		frontCameraContainer = (SceneGraphComponent) frontViewer
				.getCameraPath().get(
						frontViewer.getCameraPath().getLength() - 2);
		frontPanel.setLayout(new GridLayout());
		frontPanel.add((Component) frontViewer.getViewingComponent());
		frontPanel.setVisible(true);
		frontPanel.getComponent(0).addMouseMotionListener(this);
		frontPanel.getComponent(0).addMouseListener(this);
		frontPanel.getComponent(0).addMouseWheelListener(this);
		frontPanel.getComponent(0).setName("frontPanel");

		topCamera.setLocation(0, 7, -4.5);
		topCamera.setRotationX(-90);
		topCamera.applyChangesTo(topCameraContainer);

		sideCamera.setLocation(7, 0, -4.5);
		sideCamera.setRotationY(90);
		sideCamera.applyChangesTo(sideCameraContainer);

		frontCamera.setLocation(0, 0, 4.5);
		frontCamera.applyChangesTo(frontCameraContainer);
	}	
	protected void initPalettePane() {

		palettePane = new JPanel();
		palettePane.setBounds(300, 300, 250, 500);
		palettePane.setLayout(new GridLayout(4, 3));

		// TODO put in right graphics here-- make sure that you either specify
		// the right path or drop the files into the top-level project folder.
		// Eclipse won't be able to find them otherwise.
		// Set up the button images
		ImageIcon selectImage = new ImageIcon("Icons/selectImage.png");
		ImageIcon moveImage = new ImageIcon("Icons/moveImage.png");
		ImageIcon fillImage = new ImageIcon("Icons/fillImage.png");
		ImageIcon joinEdgeImage = new ImageIcon("Icons/joinEdge.png");
		ImageIcon eraseImage = new ImageIcon("Icons/erase.png");
		ImageIcon pointImage = new ImageIcon("Icons/addPoint.png");
		ImageIcon lineImage = new ImageIcon("Icons/addLine.png");
		ImageIcon shapeImage = new ImageIcon("Icons/shape.png");
		ImageIcon panCameraImage = new ImageIcon("Icons/panCamera.png");
		ImageIcon rotateCameraImage = new ImageIcon("Icons/rotateCamera.png");
		ImageIcon flymodeImage = new ImageIcon("Icons/flyMode.png");
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
		palettePanCamera = new JButton(panCameraImage);
		paletteFlymode = new JButton(flymodeImage);
		paletteRotateCamera = new JButton(rotateCameraImage);
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
		palettePanCamera.setName("panCamera");
		paletteRotateCamera.setName("rotateCamera");
		paletteFlymode.setName("flymode");
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
				.setToolTipText("When selected allows the user to click-and-drag to create a line in any of the perspectives that is not the freeview perspective. By clicking and holding on this tool you can select more polygons from a drop-down menu, or select a �custom� option which would allow you to specify how many faces your object has.");
		palettePanCamera
				.setToolTipText("Will move camera up and down and side to side.");
		paletteRotateCamera
				.setToolTipText("Allows you to click-and-drag on the Freeview perspective to rotate, or �point� the camera in the desired direction.");
		paletteFlymode
				.setToolTipText(" When selected allows the user to fly the camera in the Freeview perspective.");
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
		palettePane.add(palettePanCamera);
		palettePane.add(paletteFlymode);
		palettePane.add(paletteRotateCamera);
		palettePane.add(paletteMoveCamera);

		// Add action listeners
		paletteSelect.addActionListener((ActionListener) this);
		paletteMove.addActionListener((ActionListener) this);
		paletteFill.addActionListener((ActionListener) this);
		paletteJoinEdge.addActionListener((ActionListener) this);
		paletteErase.addActionListener((ActionListener) this);
		palettePoint.addActionListener((ActionListener) this);
		paletteLine.addActionListener((ActionListener) this);
		paletteShape.addActionListener((ActionListener) this);
		palettePanCamera.addActionListener((ActionListener) this);
		paletteRotateCamera.addActionListener((ActionListener) this);
		paletteFlymode.addActionListener((ActionListener) this);
		paletteMoveCamera.addActionListener((ActionListener) this);

		dialog = new JDialog(theProgram, "Tools", false);
		dialog.setPreferredSize(new Dimension(196, 350));
		dialog.add(palettePane);
		dialog.pack();
		dialog.setLocation(8, 170);
		dialog.setVisible(true);
	}
	
	//Create the panes, panels and other gui elements and pack them up.
	public void initPanesAndGui() {
		// Adding the view panels (free, top, side, front)
		GridLayout gl = new GridLayout(2, 2);
		mainPanel = new JPanel(gl, true);
		mainPanel.setBackground(new Color(128, 128, 64));
		freeViewPanel = new JPanel();
		mainPanel.add(freeViewPanel);
		topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		mainPanel.add(topPanel);
		sidePanel = new JPanel();
		sidePanel.setBackground(Color.WHITE);
		mainPanel.add(sidePanel);
		frontPanel = new JPanel();
		frontPanel.setBackground(Color.GRAY);
		mainPanel.add(frontPanel);
		mainPanel.addMouseListener(this);

		// Adding borders and titles
		freeViewPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), "Free Camera"));
		topPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), "Top Camera"));
		sidePanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), "Right Camera"));
		frontPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), "Front Camera"));

		createJRViewers();
		initMenuBarPane();

		// stick them both in a desktop pane
		desktop.setLayout(new BorderLayout());
		desktop.add(menuBarPane, "North");
		desktop.add(mainPanel);
		pack();

		// Create the top frame to store desktop
		f = new JFrame("Polyhedra");
		f.setLayout(new GridLayout());
		f.add(desktop);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 700);
		f.setVisible(true);
		
		// Initializes the palette last so as to have it in the foreground
		initPalettePane();
		
	}

	public void actionPerformed(ActionEvent e) {

		JButton theCommand = (JButton) e.getSource();
		String buttonName = theCommand.getName();

		if (buttonName.equals("select")) {
			actionManager.doSelect();
		} else if (buttonName.equals("move")) {
			actionManager.doMove();
		} else if (buttonName.equals("fill")) {
			actionManager.doFill();
		} else if (buttonName.equals("joinEdge")) {
			actionManager.doJoinEdge();
		} else if (buttonName.equals("erase")) {
			actionManager.doErase();
		} else if (buttonName.equals("point")) {
			actionManager.doPoint();
		} else if (buttonName.equals("line")) {
			actionManager.doLine();
		} else if (buttonName.equals("shape")) {
			actionManager.doShape();
		} else if (buttonName.equals("panCamera")) {
			actionManager.doPanCamera();
		} else if (buttonName.equals("rotateCamera")) {
			actionManager.doRotateCamera();
		} else if (buttonName.equals("flymode")) {
			actionManager.doFlymode();
		} else if (buttonName.equals("moveCamera")) {
			actionManager.doMoveCamera();
		}
	}

	private static GUI theProgram;

	public static void main(String[] args) {
		theProgram = new GUI();
		theProgram.initPanesAndGui();
		theProgram.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Flip camera to other side on double-click
		if (e.getClickCount() == 2) {
			if (e.getComponent().getParent().getParent().getName()
					.equals("topPanel")) {
				topCamera.flipOnAxis("x");
				topCamera.applyChangesTo(topCameraContainer);
				if (topCamera.flipped) {
					topPanel.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createEmptyBorder(), "Bottom Camera"));
				} else {
					topPanel.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createEmptyBorder(), "Top Camera"));
				}
			} else if (e.getComponent().getParent().getParent().getName()
					.equals("sidePanel")) {
				sideCamera.flipOnAxis("y");
				sideCamera.applyChangesTo(sideCameraContainer);
				if (sideCamera.flipped) {
					sidePanel.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createEmptyBorder(), "Left Camera"));
				} else {
					sidePanel.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createEmptyBorder(), "Right Camera"));
				}
			} else if (e.getComponent().getParent().getParent().getName()
					.equals("frontPanel")) {
				frontCamera.flipOnAxis("z");
				frontCamera.applyChangesTo(frontCameraContainer);
				if (frontCamera.flipped) {
					frontPanel.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createEmptyBorder(), "Back Camera"));
				} else {
					frontPanel.setBorder(BorderFactory.createTitledBorder(
							BorderFactory.createEmptyBorder(), "Front Camera"));
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// System.out.println("Mouse Entered: " + arg0.toString());

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// System.out.println("Mouse Exited: " + arg0.toString());

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// System.out.println("Mouse Pressed: " + arg0.toString());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Reset the previous mouse location between drags, so we're only
		// recording drag position
		mouseDragLocation = null;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int flipCoefficient = 1;
		if (mouseDragLocation == null) {
			mouseDragLocation = new Point(e.getX(), e.getY());
		} else {
			// Handling the event depending on which panel the even originated
			// from.
			if (e.getComponent().getParent().getParent().getName()
					.equals("topPanel")) {
				if (topCamera.flipped) {
					flipCoefficient = -1;
				}
				topCamera.setLocationX(topCamera.location.x
						+ ((double) e.getX() - mouseDragLocation.x) / -100);
				topCamera
						.setLocationZ(topCamera.location.z
								+ (((double) e.getY() - mouseDragLocation.y) * flipCoefficient)
								/ -100);
				topCamera.applyChangesTo(topCameraContainer);
			} else if (e.getComponent().getParent().getParent().getName()
					.equals("sidePanel")) {
				if (sideCamera.flipped) {
					flipCoefficient = -1;
				}
				sideCamera.setLocationY(sideCamera.location.y
						+ ((double) e.getY() - mouseDragLocation.y) / 100);
				sideCamera
						.setLocationZ(sideCamera.location.z
								+ (((double) e.getX() - mouseDragLocation.x) * flipCoefficient)
								/ 100);
				sideCamera.applyChangesTo(sideCameraContainer);
			} else if (e.getComponent().getParent().getParent().getName()
					.equals("frontPanel")) {
				if (frontCamera.flipped) {
					flipCoefficient = -1;
				}
				frontCamera
						.setLocationX(frontCamera.location.x
								+ (((double) e.getX() - mouseDragLocation.x) * flipCoefficient)
								/ -100);
				frontCamera.setLocationY(frontCamera.location.y
						+ ((double) e.getY() - mouseDragLocation.y) / 100);
				frontCamera.applyChangesTo(frontCameraContainer);
			} else if (e.getComponent().getParent().getParent().getName()
					.equals("freeViewPanel")) {
				freeCamera.setRotationX(freeCamera.rotation.x
						+ ((double) e.getX() - mouseDragLocation.x) / 4);
				freeCamera.setRotationY(freeCamera.rotation.y
						+ ((double) e.getY() - mouseDragLocation.y) / 4);
				freeCamera.applyChangesTo(freeCameraContainer);
			}
			mouseDragLocation.setLocation(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// System.out.println("Mouse Moved: " + e.toString());
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		double amountZoom = e.getWheelRotation();
		if (e.getComponent().getParent().getParent().getName()
				.equals("topPanel")) {
			topCamera.setLocationY(topCamera.location.y + amountZoom / 20);
			topCamera.applyChangesTo(topCameraContainer);
		} else if (e.getComponent().getParent().getParent().getName()
				.equals("sidePanel")) {
			sideCamera.setLocationX(sideCamera.location.x + amountZoom / 20);
			sideCamera.applyChangesTo(sideCameraContainer);
		} else if (e.getComponent().getParent().getParent().getName()
				.equals("frontPanel")) {
			frontCamera.setLocationZ(frontCamera.location.z + amountZoom / 20);
			frontCamera.applyChangesTo(frontCameraContainer);
		} else if (e.getComponent().getParent().getParent().getName()
				.equals("freeViewPanel")) {
			freeCamera.setLocationZ(freeCamera.location.z + amountZoom / 20);
			freeCamera.applyChangesTo(freeCameraContainer);
		}

	}

}
