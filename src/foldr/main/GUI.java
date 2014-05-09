
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import de.jreality.plugin.JRViewer;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.Viewer;
import de.jreality.util.SceneGraphUtility;
import foldr.messages.Messages;
import foldr.messages.MessagesUtils;
import foldr.shape.Shape;
import foldr.shape.ShapeCollection;
import foldr.shape.ShapeGroup;
import foldr.utility.CustomCamera;
import foldr.utility.Tool;
import foldr.utility.Tool.ToolType;

/**
 *
 * 
 */
public final class GUI extends JFrame
    implements ActionListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private static final long          serialVersionUID  = 1L;

    Scanner                            input             = new Scanner(System.in);

    ShapeCollection                    allShapes         = ShapeCollection.getInstance();

    // the main scene graph component. All other SGC's will be a child of this.
    static SceneGraphComponent         scene             =
                                                             SceneGraphUtility.createFullSceneGraphComponent("scene");
    private JPanel                     mainPanel, freeViewPanel, topPanel, sidePanel, frontPanel,
                    popUp;
    private JPanel                     palettePane;

    // the viewer components that render the difference camera views
    JRViewer                           freeJRViewer, topJRViewer, sideJRViewer, frontJRViewer;
    Viewer                             freeViewer, topViewer, sideViewer, frontViewer;
    // the camera containers for the different cameras of the different views
    SceneGraphComponent                freeCameraContainer, topCameraContainer,
                    sideCameraContainer, frontCameraContainer;

    CustomCamera                       frontCamera       = new CustomCamera(false);
    CustomCamera                       sideCamera        = new CustomCamera(false);
    CustomCamera                       topCamera         = new CustomCamera(false);
    CustomCamera                       freeCamera        = new CustomCamera(true);

    // Capture the mouse location during drag events
    Point                              mouseDragLocation = null;

    // menu component
    private MenuBar menuBar;
	static ToolBar toolBar = new ToolBar();

    private JDialog                    dialog, popUpDialog;
    private JTextField                 textField;

    /**
     * <p>
     * Construct a new <tt>GUI</tt> (i.e. <tt>JFrame</tt>) with the given title.
     * 
     * @param title
     *            The window's title.
     */
    public GUI(String title) {

        super(title);
    }

    /**
     * <p>
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
    public void connectTwoShapes(Shape shapeOne, int vertexOne, Shape shapeTwo, int vertexTwo) {

        // store original coordinates for error calculation
        double originalX = shapeOne.getShapeSGC().getTransformation().getMatrix()[3];
        double originalY = shapeOne.getShapeSGC().getTransformation().getMatrix()[7];
        double originalZ = shapeOne.getShapeSGC().getTransformation().getMatrix()[11];

        // figure out how much to move the first shape in each direction
        double targetX =
            shapeTwo.getCurrentVertexCoordinates(vertexTwo)[0] -
                shapeOne.getCurrentVertexCoordinates(vertexOne)[0];
        double targetY =
            shapeTwo.getCurrentVertexCoordinates(vertexTwo)[1] -
                shapeOne.getCurrentVertexCoordinates(vertexOne)[1];
        double targetZ =
            shapeTwo.getCurrentVertexCoordinates(vertexTwo)[2] -
                shapeOne.getCurrentVertexCoordinates(vertexOne)[2];

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
        double errorX =
            shapeOne.getShapeSGC().getTransformation().getMatrix()[3] - (originalX + targetX);
        double errorY =
            shapeOne.getShapeSGC().getTransformation().getMatrix()[7] - (originalY + targetY);
        double errorZ =
            shapeOne.getShapeSGC().getTransformation().getMatrix()[11] - (originalZ + targetZ);
        // System.out.println("X error: " + errorX + ", Y error: " + errorY +
        // ", Z error: " + errorZ);
    }

    // Create the jReality viewers for each panel
    public void createJRViewers() {
        // TESTING with a visible shape
    	Shape octogon = new Shape(8, scene);

        // Setting up the free view
        freeJRViewer = new JRViewer();
        freeJRViewer.setContent(scene);
        freeJRViewer.startupLocal();
        freeViewer = freeJRViewer.getViewer();
        freeCameraContainer =
            (SceneGraphComponent) freeViewer.getCameraPath().get(
                freeViewer.getCameraPath().getLength() - 2);
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
        topCameraContainer =
            (SceneGraphComponent) topViewer.getCameraPath().get(
                topViewer.getCameraPath().getLength() - 2);
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
        sideCameraContainer =
            (SceneGraphComponent) sideViewer.getCameraPath().get(
                sideViewer.getCameraPath().getLength() - 2);
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
        frontCameraContainer =
            (SceneGraphComponent) frontViewer.getCameraPath().get(
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
	
	
	/**
	 * <p>
	 * Create the panes, panels and other gui elements and pack them up.
	 */
	public void initPanesAndGui() {
	    menuBar = new MenuBar(this);
		setJMenuBar(menuBar);

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
		
		createJRViewers();

		// Adding borders and titles
		freeViewPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), "Free Camera"));
		topPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), "Top Camera"));
		sidePanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), "Right Camera"));
		frontPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(), "Front Camera"));
		freeViewPanel.setBackground(Color.gray);
		topPanel.setBackground(Color.gray);
		sidePanel.setBackground(Color.gray);
		frontPanel.setBackground(Color.gray);
		
		topCamera.setLocation(0, 7, -4.5);
		topCamera.setRotationX(-90);
		topCamera.applyChangesTo(topCameraContainer);

		sideCamera.setLocation(7, 0, -4.5);
		sideCamera.setRotationY(90);
		sideCamera.applyChangesTo(sideCameraContainer);

		frontCamera.setLocation(0, 0, 4.5);
		frontCamera.applyChangesTo(frontCameraContainer);

		// Create the top frame to store desktop
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, "Center");
		label(); // write the messages
		pack();
		setSize(1000, 700);
		setVisible(true);
		
		toolBar.initPalettePane(this);
	}

    /*
     * * Creates a pop up box when 'shape' button is clicked on the tool bar.
     * Allows the user to enter the number of sides they want a polygon to have
     * which is being added to the scene.
     */
    protected void popUpPanel() {

        popUp = new JPanel();

        textField = new JTextField(1);

        JButton selectNumSides = new JButton("OK");
        selectNumSides.addActionListener((ActionListener) this);
        selectNumSides.setName("selectNumSides");


        popUp.add(textField);
        popUp.add(selectNumSides);

        popUpDialog = new JDialog(this, "Polygon Creator", false);
        popUpDialog.add(popUp);
        popUpDialog.pack();
        popUpDialog.setLocation(8, 170);
        popUpDialog.setVisible(true);
    }

    /**
     * <p>
     * Change the GUI text according to the current <tt>Locale</tt> used.
     */
    private void label() {

        menuBar.label();
		// panels titles FIXME make it work.
		((TitledBorder) freeViewPanel.getBorder()).setTitle(Messages
				.getString("panels.freeview"));
		((TitledBorder) frontPanel.getBorder()).setTitle(Messages
				.getString("panels.frontview"));
		((TitledBorder) sidePanel.getBorder()).setTitle(Messages
				.getString("panels.sideview"));
		((TitledBorder) topPanel.getBorder()).setTitle(Messages
				.getString("panels.topview"));
	}

    @Override
    public void mouseClicked(MouseEvent e) {
    	switch (toolBar.currentTool.getCurrentTool()) {
			case CAMERA_MOVE:
				handleMouseDoubleClickForCameraMove(e);
    	}
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {

        // System.out.println("Mouse Entered: " + arg0.toString());

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    	switch (toolBar.currentTool.getCurrentTool()) {
    		case CAMERA_MOVE:
    			handleMouseDragForCameraMove(e);
    	}
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
    public void mouseMoved(MouseEvent e) {

        // System.out.println("Mouse Moved: " + e.toString());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    	//We always allow the user to zoom the camera, regardless of tool selection.
        double amountZoom = e.getWheelRotation();
        if (e.getComponent().getParent().getParent().getName().equals("topPanel")) {
            topCamera.setLocationY(topCamera.location.y + amountZoom / 20);
            topCamera.applyChangesTo(topCameraContainer);
        } else if (e.getComponent().getParent().getParent().getName().equals("sidePanel")) {
            sideCamera.setLocationX(sideCamera.location.x + amountZoom / 20);
            sideCamera.applyChangesTo(sideCameraContainer);
        } else if (e.getComponent().getParent().getParent().getName().equals("frontPanel")) {
            frontCamera.setLocationZ(frontCamera.location.z + amountZoom / 20);
            frontCamera.applyChangesTo(frontCameraContainer);
        } else if (e.getComponent().getParent().getParent().getName().equals("freeViewPanel")) {
            freeCamera.setLocationZ(freeCamera.location.z + amountZoom / 20);
            freeCamera.applyChangesTo(freeCameraContainer);
        }

    }

    @Override
    public void mouseExited(MouseEvent arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub
        
    }
    
    /**
     * Handles camera changes for when the mouse is dragged, and the CAMERA_MOVE tool is selected
    */
    public void handleMouseDragForCameraMove(MouseEvent e) {
        int flipCoefficient = 1;
        if (mouseDragLocation == null) {
            mouseDragLocation = new Point(e.getX(), e.getY());
        } else {
            // Handling the event depending on which panel the even originated
            // from.
            if (e.getComponent().getParent().getParent().getName().equals("topPanel")) {
                if (topCamera.flipped) {
                    flipCoefficient = -1;
                }
                topCamera.setLocationX(topCamera.location.x +
                    ((double) e.getX() - mouseDragLocation.x) / -100);
                topCamera.setLocationZ(topCamera.location.z +
                    (((double) e.getY() - mouseDragLocation.y) * flipCoefficient) / -100);
                topCamera.applyChangesTo(topCameraContainer);
            } else if (e.getComponent().getParent().getParent().getName().equals("sidePanel")) {
                if (sideCamera.flipped) {
                    flipCoefficient = -1;
                }
                sideCamera.setLocationY(sideCamera.location.y +
                    ((double) e.getY() - mouseDragLocation.y) / 100);
                sideCamera.setLocationZ(sideCamera.location.z +
                    (((double) e.getX() - mouseDragLocation.x) * flipCoefficient) / 100);
                sideCamera.applyChangesTo(sideCameraContainer);
            } else if (e.getComponent().getParent().getParent().getName().equals("frontPanel")) {
                if (frontCamera.flipped) {
                    flipCoefficient = -1;
                }
                frontCamera.setLocationX(frontCamera.location.x +
                    (((double) e.getX() - mouseDragLocation.x) * flipCoefficient) / -100);
                frontCamera.setLocationY(frontCamera.location.y +
                    ((double) e.getY() - mouseDragLocation.y) / 100);
                frontCamera.applyChangesTo(frontCameraContainer);
            } else if (e.getComponent().getParent().getParent().getName().equals("freeViewPanel")) {
                freeCamera.setRotationX(freeCamera.rotation.x +
                    ((double) e.getX() - mouseDragLocation.x) / 4);
                freeCamera.setRotationY(freeCamera.rotation.y +
                    ((double) e.getY() - mouseDragLocation.y) / 4);
                freeCamera.applyChangesTo(freeCameraContainer);
            }
            mouseDragLocation.setLocation(e.getX(), e.getY());
        }
    }
    
    /**
     * Handles camera changes for when the mouse is double clicked, and the CAMERA_MOVE tool is selected
    */
    public void handleMouseDoubleClickForCameraMove(MouseEvent e) {
    	// Flip camera to other side on double-click
        if (e.getClickCount() == 2) {
            if (e.getComponent().getParent().getParent().getName().equals("topPanel")) {
                topCamera.flipOnAxis("x");
                topCamera.applyChangesTo(topCameraContainer);
                if (topCamera.flipped) {
                    topPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createEmptyBorder(), "Bottom Camera"));
                } else {
                    topPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createEmptyBorder(), "Top Camera"));
                }
            } else if (e.getComponent().getParent().getParent().getName().equals("sidePanel")) {
                sideCamera.flipOnAxis("y");
                sideCamera.applyChangesTo(sideCameraContainer);
                if (sideCamera.flipped) {
                    sidePanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createEmptyBorder(), "Left Camera"));
                } else {
                    sidePanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createEmptyBorder(), "Right Camera"));
                }
            } else if (e.getComponent().getParent().getParent().getName().equals("frontPanel")) {
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

}
