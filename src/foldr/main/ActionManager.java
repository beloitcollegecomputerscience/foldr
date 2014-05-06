package foldr.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * 
 * @author Nick, Hunter, and Tyler
 * 
 */

public class ActionManager implements ActionListener {
	// Testing
	
	protected JDialog dialog;
	protected JButton paletteSelect, paletteMove, paletteFill, paletteJoinEdge,
	paletteJoinPoint, paletteErase, palettePoint, paletteLine,
	paletteShape, palettePanCamera, paletteFlymode,
	paletteRotateCamera, paletteMoveCamera;
	protected JPanel palettePane;
	
	protected void initPalettePane(GUI theProgram) {

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
		} else if (buttonName.equals("panCamera")) {
			doPanCamera();
		} else if (buttonName.equals("rotateCamera")) {
			doRotateCamera();
		} else if (buttonName.equals("flymode")) {
			doFlymode();
		} else if (buttonName.equals("moveCamera")) {
			doMoveCamera();
		}

	}

	public void doSelect() {
		System.out.println("select");

	}

	public void doMove() {
		System.out.println("move");
	}

	public void doFill() {
		System.out.println("fill");
	}

	public void doJoinEdge() {
		System.out.println("join edge");
	}

	public void doErase() {
		System.out.println("erase");
	}

	public void doPoint() {
		System.out.println("point");
	}

	public void doLine() {
		System.out.println("line");
	}

	public void doShape() {
		System.out.println("shape");
	}

	public void doPanCamera() {
		System.out.println("pan camera");
	}

	public void doFlymode() {
		System.out.println("fly mode");
	}

	public void doRotateCamera() {
		System.out.println("rotate camera");
	}

	public void doMoveCamera() {
		System.out.println("move camera");
	}

}
