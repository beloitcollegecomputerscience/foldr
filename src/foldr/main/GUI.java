package foldr.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.vecmath.Vector3d;

import de.jreality.geometry.Primitives;
import de.jreality.math.MatrixBuilder;
import de.jreality.plugin.JRViewer;
import de.jreality.scene.Camera;
import de.jreality.scene.IndexedFaceSet;
import de.jreality.scene.PointSet;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.SceneGraphPath;
import de.jreality.scene.Viewer;
import de.jreality.util.SceneGraphUtility;
import foldr.shape.Shape;

/**
 *
 * 
 */
public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	
	private static final long serialVersionUID = 1L;

	// the main scene graph component. All other SGC's will be a child of this.
	static SceneGraphComponent scene = SceneGraphUtility
			.createFullSceneGraphComponent("scene");

	// the swing components to create the jreality frame
	protected JFrame f;
	protected JDesktopPane desktop = new JDesktopPane();
	private JPanel mainPanel, freeViewPanel, topPanel, sidePanel, frontPanel;
	
	//the viewer components that render the difference camera views
	JRViewer freeJRViewer, topJRViewer, sideJRViewer, frontJRViewer;
	Viewer freeViewer, topViewer, sideViewer, frontViewer;
	// the camera containers for the different cameras of the different views
	SceneGraphComponent freeCameraContainer, topCameraContainer, sideCameraContainer, frontCameraContainer;
	
	//Hold the different camera locations (hard-coded for now)
	Vector3d frontCameraLocation = new Vector3d(0, 0, 4.5);
	Vector3d sideCameraLocation = new Vector3d(7, 0, -4.5);
	Vector3d topCameraLocation = new Vector3d(0, 7, -4.5);
	Vector3d freeCameraLocation = new Vector3d(0, 0, 0);
	
	double freeCamRotationDegX = 0;
	double freeCamRotationDegY = 0;
	
	//Capture the mouse location during drag events
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

	// Create the jReality viewers for each panel
	public void createJRViewers() {
		//TESTING with a visible shape @TODO: Remove this.
		IndexedFaceSet octo = Primitives.regularPolygon(8);
		SceneGraphComponent octoOne = SceneGraphUtility
				.createFullSceneGraphComponent("octogon1");
		octoOne.setGeometry(octo);
		scene.addChild(octoOne);
		
		//Setting up the free view
		freeJRViewer = new JRViewer();
		freeJRViewer.setContent(scene);
		freeJRViewer.startupLocal();
		freeViewer = freeJRViewer.getViewer();
		freeCameraContainer = (SceneGraphComponent)freeViewer.getCameraPath().get(freeViewer.getCameraPath().getLength()-2);
		freeViewPanel.setLayout(new GridLayout());
		freeViewPanel.add((Component) freeViewer.getViewingComponent());
		freeViewPanel.setVisible(true);
		freeViewPanel.getComponent(0).addMouseMotionListener(theProgram);
		freeViewPanel.getComponent(0).addMouseListener(theProgram);
		freeViewPanel.getComponent(0).setName("freeViewPanel");
		
		//Setting up the top view
		topJRViewer = new JRViewer();
		topJRViewer.setContent(scene);
		topJRViewer.startupLocal();
		topViewer = topJRViewer.getViewer();
		topCameraContainer = (SceneGraphComponent)topViewer.getCameraPath().get(topViewer.getCameraPath().getLength()-2);		
		topPanel.setLayout(new GridLayout());
		topPanel.add((Component) topViewer.getViewingComponent());
		topPanel.setVisible(true);
		topPanel.getComponent(0).addMouseMotionListener(theProgram);
		topPanel.getComponent(0).addMouseListener(theProgram);
		topPanel.getComponent(0).setName("topPanel");
		
		//Setting up the side view
		sideJRViewer = new JRViewer();
		sideJRViewer.setContent(scene);
		sideJRViewer.startupLocal();
		sideViewer = sideJRViewer.getViewer();
		sideCameraContainer = (SceneGraphComponent)sideViewer.getCameraPath().get(sideViewer.getCameraPath().getLength()-2);
		sidePanel.setLayout(new GridLayout());
		sidePanel.add((Component) sideViewer.getViewingComponent());
		sidePanel.setVisible(true);
		sidePanel.getComponent(0).addMouseMotionListener(theProgram);
		sidePanel.getComponent(0).addMouseListener(theProgram);
		sidePanel.getComponent(0).setName("sidePanel");
		
		//Setting up the front view
		frontJRViewer = new JRViewer();
		frontJRViewer.setContent(scene);
		frontJRViewer.startupLocal();
		frontViewer = frontJRViewer.getViewer();
		frontCameraContainer = (SceneGraphComponent)frontViewer.getCameraPath().get(frontViewer.getCameraPath().getLength()-2);
		frontPanel.setLayout(new GridLayout());
		frontPanel.add((Component) frontViewer.getViewingComponent());
		frontPanel.setVisible(true);
		frontPanel.getComponent(0).addMouseMotionListener(theProgram);
		frontPanel.getComponent(0).addMouseListener(theProgram);
		frontPanel.getComponent(0).setName("frontPanel");
		
		
		//Setting the initial camera positions
		MatrixBuilder.euclidean().translate(frontCameraLocation.x, frontCameraLocation.y, frontCameraLocation.z).assignTo(frontCameraContainer);
		MatrixBuilder.euclidean().translate(sideCameraLocation.x, sideCameraLocation.y, sideCameraLocation.z).rotateY(Math.toRadians(90)).assignTo(sideCameraContainer);
		MatrixBuilder.euclidean().translate(topCameraLocation.x, topCameraLocation.y, topCameraLocation.z).rotateX(Math.toRadians(-90)).assignTo(topCameraContainer);
	
		
	}
	
	//Create the panes, panels and other gui elements and pack them up.
	private void initPanesAndGui() {
		//Adding the view panels (free, top, side, front)
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

		//Adding border
		freeViewPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		sidePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		frontPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
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
	}

	private static GUI theProgram;
	public static void main(String[] args) {
		theProgram = new GUI();
		theProgram.initPanesAndGui();
		theProgram.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// Action listener. For now, this method is just a placeholder.
	public void actionPerformed(ActionEvent e) {
		System.out.println("That action is not yet implemented");
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		//System.out.println("Mouse Clicked: " + arg0.toString());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("Mouse Entered: " + arg0.toString());
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//System.out.println("Mouse Exited: " + arg0.toString());
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//System.out.println("Mouse Pressed: " + arg0.toString());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//Reset the previous mouse location between drags, so we're only recording drag position
		mouseDragLocation = null;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (mouseDragLocation == null) {
			mouseDragLocation = new Point(e.getX(), e.getY());
		} else {
			//Handling the event depending on which panel the even originated from.
			if (e.getComponent().getParent().getParent().getName().equals("topPanel")) {
				double xDiff = e.getX() - mouseDragLocation.x;
				double yDiff = e.getY() - mouseDragLocation.y;
				topCameraLocation.set(topCameraLocation.x + xDiff/-100, topCameraLocation.y, topCameraLocation.z + yDiff/-100);
				MatrixBuilder.euclidean().translate(topCameraLocation.x, topCameraLocation.y, topCameraLocation.z).rotateX(Math.toRadians(-90)).assignTo(topCameraContainer);
				mouseDragLocation.x = e.getX();
				mouseDragLocation.y = e.getY();
			} else if(e.getComponent().getParent().getParent().getName().equals("sidePanel")) {
				double xDiff = e.getX() - mouseDragLocation.x;
				double yDiff = e.getY() - mouseDragLocation.y;
				sideCameraLocation.set(sideCameraLocation.x, sideCameraLocation.y + yDiff/100, sideCameraLocation.z + xDiff/100);
				MatrixBuilder.euclidean().translate(sideCameraLocation.x, sideCameraLocation.y, sideCameraLocation.z).rotateY(Math.toRadians(90)).assignTo(sideCameraContainer);
				mouseDragLocation.x = e.getX();
				mouseDragLocation.y = e.getY();
			} else if(e.getComponent().getParent().getParent().getName().equals("frontPanel")) {
				double xDiff = e.getX() - mouseDragLocation.x;
				double yDiff = e.getY() - mouseDragLocation.y;
				frontCameraLocation.set(frontCameraLocation.x + xDiff/-100, frontCameraLocation.y + yDiff/100, frontCameraLocation.z);
				MatrixBuilder.euclidean().translate(frontCameraLocation.x, frontCameraLocation.y, frontCameraLocation.z).assignTo(frontCameraContainer);
				mouseDragLocation.x = e.getX();
				mouseDragLocation.y = e.getY();
			} else if (e.getComponent().getParent().getParent().getName().equals("freeViewPanel")) {
				double xDiff = e.getX() - mouseDragLocation.x;
				double yDiff = e.getY() - mouseDragLocation.y;
				freeCamRotationDegX = freeCamRotationDegX + xDiff/4;
				freeCamRotationDegY = freeCamRotationDegY + yDiff/4;
				MatrixBuilder.euclidean().rotateX(Math.toRadians(-freeCamRotationDegY)).rotateY(Math.toRadians(-freeCamRotationDegX)).conjugateBy(MatrixBuilder.euclidean().translate(0, 0, -4.5).getMatrix().getArray()).assignTo(freeCameraContainer);
				mouseDragLocation.x = e.getX();
				mouseDragLocation.y = e.getY();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println("Mouse Moved: " + e.toString());
	}

}
