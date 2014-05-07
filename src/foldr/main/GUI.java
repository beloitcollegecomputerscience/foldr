
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

    // menu components
    private JMenuBar                   menuBar;
    private JMenu                      fileMenu, editMenu, foldingMenu, windowMenu, helpMenu;
    private JMenuItem                  fileOpen, fileNew, fileSave, fileSaveAs, fileExport,
                    fileClose;
    private JMenuItem                  editCopy, editCut, editPaste, editDelete, editSelectAll;
    private JMenu                      foldingAngle, foldingShape;
    private JMenuItem                  foldingThirty, foldingFortyFive, foldingNinety,
                    foldingCustomAngle, foldingEdgeSelect, foldingPointSelect, foldingFoldShapes,
                    foldingConnectShapes, foldingDetachShapes, foldingResizeShape;
    private JMenu                      windowView, windowPerspective;
    private JMenuItem                  windowShowTop, windowShowBack, windowShowLeft,
                    windowShowHideTools, windowShowHideInfo, windowChangePerspective,
                    windowSavePerspective, windowLoadPerspective, windowResizePerspective;
    private JMenu                      helpLanguage;
    private JMenuItem                  helpManual, helpQuickStartGuide;
    private ButtonGroup                langGroup;
    private List<JRadioButtonMenuItem> liLanguages;

    ActionManager                      actionManager     = new ActionManager();

    private JButton                    paletteSelect, paletteMove, paletteFill, paletteJoinEdge,
                    paletteErase, palettePoint, paletteLine, paletteShape, palettePanCamera,
                    paletteFlymode, paletteRotateCamera, paletteMoveCamera;

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
     * Initialize the menu components and add them to the frame.
     * <p>
     * Menu structure :
     * <p>
     * File<br>
     * |-Open<br>
     * |-New<br>
     * |-Save<br>
     * |-Save As<br>
     * |-Export<br>
     * |-Close
     * <p>
     * Edit<br>
     * |-Copy<br>
     * |-Cut<br>
     * |-Paste<br>
     * |-Delete<br>
     * |-Select All
     * <p>
     * Fold<br>
     * |-Angle<br>
     * | |-30<br>
     * | |-45<br>
     * | |-90<br>
     * | |-Custom<br>
     * |-Edge Select<br>
     * |-Point Select<br>
     * |-Shape<br>
     * | |-Fold<br>
     * | |-Connect<br>
     * | |-Detach<br>
     * | |-Resize
     * <p>
     * Window<br>
     * |-View<br>
     * | |-Top<br>
     * | |-Back<br>
     * | |-Left<br>
     * |-Show/Hide Tool<br>
     * |-Show/Hide Info<br>
     * |-Perspective<br>
     * | |-Change Perspective<br>
     * | |-Save<br>
     * | |-Load<br>
     * | |-Resize
     * <p>
     * Help |-Manual<br>
     * |-Quick Start Guide<br>
     * |-Language<br>
     * | |-<tt>List of languages</tt>
     */
    private void initMenuBarPane() {

        menuBar = new JMenuBar();
        // File submenu
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);// fileMenu -> menuBar
        fileNew = new JMenuItem("New");
        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.META_MASK));
        fileNew.addActionListener(this);
        fileMenu.add(fileNew);
        fileOpen = new JMenuItem("Open");
        fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.META_MASK));
        fileOpen.addActionListener(this);
        fileMenu.add(fileOpen);
        fileSave = new JMenuItem("Save");
        fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
        fileSave.addActionListener(this);
        fileMenu.add(fileSave);
        fileSaveAs = new JMenuItem("Save As");
        fileSaveAs.addActionListener(this);
        fileMenu.add(fileSaveAs);
        fileExport = new JMenuItem("Export");
        fileExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.META_MASK));
        fileExport.addActionListener(this);
        fileMenu.add(fileExport);
        fileClose = new JMenuItem("Close");
        fileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.META_MASK));
        fileClose.addActionListener(this);
        fileMenu.add(fileClose);

        // Edit submenu
        editMenu = new JMenu("Edit");
        menuBar.add(editMenu); // editMenu -> menuBar
        editCopy = new JMenuItem("Copy");
        editCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.META_MASK));
        editCopy.addActionListener(this);
        editMenu.add(editCopy);
        editCut = new JMenuItem("Cut");
        editCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.META_MASK));
        editCut.addActionListener(this);
        editMenu.add(editCut);
        editPaste = new JMenuItem("Paste");
        editPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.META_MASK));
        editPaste.addActionListener(this);
        editMenu.add(editPaste);
        editMenu.add(new JSeparator()); // separate
        editDelete = new JMenuItem("Delete");
        editDelete.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_BACK_SPACE, ActionEvent.META_MASK));
        editDelete.addActionListener(this);
        editMenu.add(editDelete);
        editSelectAll = new JMenuItem("Select All");
        editSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.META_MASK));
        editSelectAll.addActionListener(this);
        editMenu.add(editSelectAll);

        // Folding/Shapes submenu
        foldingMenu = new JMenu("Folding");
        menuBar.add(foldingMenu); // folMenu -> menuBar
        foldingAngle = new JMenu("Angle");
        foldingMenu.add(foldingAngle);
        foldingThirty = new JMenuItem("Rotate 30 Degrees");
        foldingThirty.addActionListener(this);
        foldingAngle.add(foldingThirty);
        foldingFortyFive = new JMenuItem("Rotate 45 Degrees");
        foldingFortyFive.addActionListener(this);
        foldingAngle.add(foldingFortyFive);
        foldingNinety = new JMenuItem("Rotate 90 Degrees");
        foldingNinety.addActionListener(this);
        foldingAngle.add(foldingNinety);
        foldingCustomAngle = new JMenuItem("Custom Angle");
        foldingCustomAngle.addActionListener(this);
        foldingAngle.add(foldingCustomAngle);
        foldingMenu.add(new JSeparator()); // separate
        foldingEdgeSelect = new JMenuItem("Edge Select");
        foldingEdgeSelect.addActionListener(this);
        foldingMenu.add(foldingEdgeSelect);
        foldingPointSelect = new JMenuItem("Point Select");
        foldingPointSelect.addActionListener(this);
        foldingMenu.add(foldingPointSelect);
        foldingMenu.add(new JSeparator()); // separate
        foldingShape = new JMenu("Shape");
        foldingMenu.add(foldingShape); // foldShape -> foldMenu
        foldingFoldShapes = new JMenuItem("Fold Shapes");
        foldingFoldShapes.addActionListener(this);
        foldingShape.add(foldingFoldShapes);
        foldingConnectShapes = new JMenuItem("Connect Shapes");
        foldingConnectShapes.addActionListener(this);
        foldingShape.add(foldingConnectShapes);
        foldingDetachShapes = new JMenuItem("Detach Shapes");
        foldingDetachShapes.addActionListener(this);
        foldingShape.add(foldingDetachShapes);
        foldingResizeShape = new JMenuItem("Resize shape");
        foldingResizeShape.addActionListener(this);
        foldingShape.add(foldingResizeShape);

        // Window submenu
        windowMenu = new JMenu("Window");
        menuBar.add(windowMenu);// winMenu -> menuBar
        windowView = new JMenu("View");
        windowMenu.add(windowView); // winView -> winMenu
        windowShowTop = new JCheckBoxMenuItem("Show top", true);
        windowShowTop.addActionListener(this);
        windowView.add(windowShowTop);
        windowShowBack = new JCheckBoxMenuItem("Show Back", true);
        windowShowBack.addActionListener(this);
        windowView.add(windowShowBack);
        windowShowLeft = new JCheckBoxMenuItem("Show Left", true);
        windowShowLeft.addActionListener(this);
        windowView.add(windowShowLeft);
        windowMenu.add(new JSeparator()); // separate
        windowShowHideTools = new JMenuItem("Show/Hide Tools");
        windowShowHideTools.addActionListener(this);
        windowMenu.add(windowShowHideTools);
        windowShowHideInfo = new JMenuItem("Show/Hide Information Panel");
        windowShowHideInfo.addActionListener(this);
        windowMenu.add(windowShowHideInfo);
        windowMenu.add(new JSeparator()); // separate
        windowPerspective = new JMenu("Perspective");
        windowMenu.add(windowPerspective); // winPersp -> winMenu
        windowChangePerspective = new JMenuItem("Change Perspective Layout");
        windowChangePerspective.addActionListener(this);
        windowPerspective.add(windowChangePerspective);
        windowSavePerspective = new JMenuItem("Save Perspective Layout");
        windowSavePerspective.addActionListener(this);
        windowPerspective.add(windowSavePerspective);
        windowLoadPerspective = new JMenuItem("Load Perspective Layout");
        windowLoadPerspective.addActionListener(this);
        windowPerspective.add(windowLoadPerspective);
        windowResizePerspective = new JMenuItem("Resize Perspective");
        windowResizePerspective.addActionListener(this);
        windowPerspective.add(windowResizePerspective);

        // Help menu items
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu); // helpMenu -> menuBar
        helpManual = new JMenuItem("Manual");
        helpManual.addActionListener(this);
        helpMenu.add(helpManual);
        helpQuickStartGuide = new JMenuItem("Quick Start Guide");
        helpQuickStartGuide.addActionListener(this);
        helpMenu.add(helpQuickStartGuide);

        helpLanguage = new JMenu("Languages");
        helpMenu.add(helpLanguage);
        liLanguages = new ArrayList<>();
        langGroup = new ButtonGroup();
        for (String s : MessagesUtils.getInstance().getDisplayedLanguages()) {
            JRadioButtonMenuItem jrbmi = new JRadioButtonMenuItem(s, false);
            jrbmi.addActionListener(this);
            if (s.equals(Messages.getLocale().getDisplayLanguage(Messages.getLocale()))) {
                jrbmi.setSelected(true);
            }
            liLanguages.add(jrbmi);
            helpLanguage.add(jrbmi);
            langGroup.add(jrbmi);
        }

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

        // TESTING with a visible shape @TODO: Remove this.
        /*
         * IndexedFaceSet octo = Primitives.regularPolygon(8);
         * SceneGraphComponent octoOne = SceneGraphUtility
         * .createFullSceneGraphComponent("octogon1");
         * octoOne.setGeometry(octo); scene.addChild(octoOne);
         */

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

        initMenuBarPane();
        setJMenuBar(menuBar);

        initPalettePane();

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
    }

    /**
     * 
     */
    private void initPalettePane() {

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
        paletteSelect.setToolTipText("Selects a shape or shapes to manipulate or move later. Can select individual points, lines, and shapes. If you click and drag you can select multiple points, lines, and shapes.");
        paletteMove.setToolTipText("Allows user to move selected object.");
        paletteFill.setToolTipText("Allows user to fill shapes with a particular color. When selected a color option will appear in the information panel.");
        paletteJoinEdge.setToolTipText("Allows the user to join together the edge of two shapes, creating a hinge along an edge.");
        paletteErase.setToolTipText("Destroys selected shape");
        palettePoint.setToolTipText("When selected allows the user to create a single point on the model by clicking on a particular part of any of the perspectives.");
        paletteLine.setToolTipText("When selected allows the user to click-and-drag to create a line in any of the views/perspectives.");
        paletteShape.setToolTipText("When selected allows the user to click-and-drag to create a line in any of the perspectives that is not the freeview perspective. By clicking and holding on this tool you can select more polygons from a drop-down menu, or select a �custom� option which would allow you to specify how many faces your object has.");
        palettePanCamera.setToolTipText("Will move camera up and down and side to side.");
        paletteRotateCamera.setToolTipText("Allows you to click-and-drag on the Freeview perspective to rotate, or �point� the camera in the desired direction.");
        paletteFlymode.setToolTipText(" When selected allows the user to fly the camera in the Freeview perspective.");
        paletteMoveCamera.setToolTipText(" By using using WASD, and the scroll-wheel the user can move the Freeview camera along the three dimensions.");

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

        dialog = new JDialog(this, "Tools", false);
        dialog.setPreferredSize(new Dimension(196, 350));
        dialog.add(palettePane);
        dialog.pack();
        dialog.setLocation(8, 170);
        dialog.setVisible(true);
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

        // menu bar
        // file submenu
        fileMenu.setText(Messages.getString(Messages.FI));
        fileOpen.setText(Messages.getString(Messages.FI + ".open"));
        fileNew.setText(Messages.getString(Messages.FI + ".new"));
        fileSave.setText(Messages.getString(Messages.FI + ".save"));
        fileSaveAs.setText(Messages.getString(Messages.FI + ".saveas"));
        fileExport.setText(Messages.getString(Messages.FI + ".export"));
        fileClose.setText(Messages.getString(Messages.FI + ".close"));
        // edit submenu
        editMenu.setText(Messages.getString(Messages.ED));
        editCopy.setText(Messages.getString(Messages.ED + ".copy"));
        editCut.setText(Messages.getString(Messages.ED + ".cut"));
        editPaste.setText(Messages.getString(Messages.ED + ".paste"));
        editDelete.setText(Messages.getString(Messages.ED + ".delete"));
        // folding submenu
        foldingMenu.setText(Messages.getString(Messages.FO));
        foldingThirty.setText(Messages.getString(Messages.FO + ".angle.30"));
        foldingFortyFive.setText(Messages.getString(Messages.FO + ".angle.45"));
        foldingNinety.setText(Messages.getString(Messages.FO + ".angle.90"));
        foldingCustomAngle.setText(Messages.getString(Messages.FO + ".angle.custom"));
        foldingEdgeSelect.setText(Messages.getString(Messages.FO + ".edgesel"));
        foldingPointSelect.setText(Messages.getString(Messages.FO + ".pointsel"));
        foldingFoldShapes.setText(Messages.getString(Messages.FO + ".shape.fold"));
        foldingConnectShapes.setText(Messages.getString(Messages.FO + ".shape.connect"));
        foldingDetachShapes.setText(Messages.getString(Messages.FO + ".shape.detach"));
        // window submenu
        windowMenu.setText(Messages.getString(Messages.WI));
        windowShowTop.setText(Messages.getString(Messages.WI + ".view.top"));
        windowShowBack.setText(Messages.getString(Messages.WI + ".view.back"));
        windowShowLeft.setText(Messages.getString(Messages.WI + ".view.left"));
        windowShowHideInfo.setText(Messages.getString(Messages.WI + ".info"));
        windowShowHideTools.setText(Messages.getString(Messages.WI + ".tools"));
        windowChangePerspective.setText(Messages.getString(Messages.WI + ".persp.change"));
        windowSavePerspective.setText(Messages.getString(Messages.WI + ".persp.save"));
        windowResizePerspective.setText(Messages.getString(Messages.WI + ".persp.resize"));
        // help submenu
        helpMenu.setText(Messages.getString(Messages.HE));
        helpManual.setText(Messages.getString(Messages.HE + ".manual"));
        helpQuickStartGuide.setText(Messages.getString(Messages.HE + ".guide"));
        helpLanguage.setText(Messages.getString(Messages.HE + ".language"));

        // panels titles FIXME make it work.
        ((TitledBorder) freeViewPanel.getBorder()).setTitle(Messages.getString("panels.freeview"));
        ((TitledBorder) frontPanel.getBorder()).setTitle(Messages.getString("panels.frontview"));
        ((TitledBorder) sidePanel.getBorder()).setTitle(Messages.getString("panels.sideview"));
        ((TitledBorder) topPanel.getBorder()).setTitle(Messages.getString("panels.topview"));
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
            popUpPanel();
        } else if (buttonName.equals("panCamera")) {
            actionManager.doPanCamera();
        } else if (buttonName.equals("rotateCamera")) {
            actionManager.doRotateCamera();
        } else if (buttonName.equals("flymode")) {
            actionManager.doFlymode();
        } else if (buttonName.equals("moveCamera")) {
            actionManager.doMoveCamera();
        } else if (buttonName.equals("selectNumSides")) {
            actionManager.doSelectNumSides(textField.getText(), scene);
            popUpDialog.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

    @Override
    public void mouseEntered(MouseEvent arg0) {

        // System.out.println("Mouse Entered: " + arg0.toString());

    }

    @Override
    public void mouseDragged(MouseEvent e) {

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

}
