
package foldr.main;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import de.jreality.plugin.JRViewer;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.Viewer;
import de.jreality.util.SceneGraphUtility;

/**
 * A simple class showing how to use a {@link JRViewer} to get a viewing
 * component which is then packed into another frame.
 * 
 * @author Beloit College software engineering 2014 class
 */
public class GUI extends JFrame implements ActionListener {

    /**
     * 
     */
    private static final long               serialVersionUID = -3499535213024398095L;

    // the main scene graph component. All other SGC's will be a child of this.
    static SceneGraphComponent              topScene         =
                                                                 SceneGraphUtility.createFullSceneGraphComponent("topScene");

    // the swing components to create the jreality frame
    // XXX the class extends JFrame so you don't have to add a member. Add
    // components such as panels here.

    // the swing components to create the menu bar
    private JMenuBar                        menuBar;
    private JMenu                           fileMenu;
    private JMenuItem                       fileOpen, fileNew, fileSave, fileSaveAs, fileExport,
                    fileClose;
    private JMenu                           editMenu;
    private JMenuItem                       editCopy, editCut, editPaste, editDelete,
                    editSelectAll, foldingResizeShape;
    private JMenu                           foldingMenu, angleMenu, shapeMenu;
    private JMenuItem                       foldingThirty, foldingFortyFive, foldingNinety,
                    foldingCustomAngle, foldingEdgeSelect, foldingPointSelect, foldingFoldShapes,
                    foldingConnectShapes, foldingDetachShapes;
    private JMenu                           windowMenu, viewMenu, perspectiveMenu;
    private JMenuItem                       windowShowTop, windowShowBack, windowShowLeft,
                    windowShowHideTools, windowShowHideInfo, windowChangePerspective,
                    windowResizePerspective;
    private JCheckBoxMenuItem               windowSavePerspective, windowLoadPerspective;
    private JMenu                           helpMenu, languageMenu;
    private JMenuItem                       helpManual, helpQuickStartGuide;
    private ButtonGroup                     languageGroup;
    private ArrayList<JRadioButtonMenuItem> languaguesMenuItem;

    /**
     * @throws HeadlessException
     */
    public GUI()
        throws HeadlessException {

    }

    /**
     * @param title
     * @throws HeadlessException
     */
    public GUI(String title)
        throws HeadlessException {

        super(title);
    }

    // This method creates the menu bar
    private void initMenuBar() {

        // File menu items
        // XXX ActionEvent.META_MASK doesn't work on Windows.
        // XXX Use default constructor on items then add
        fileNew = new JMenuItem();
        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.META_MASK));
        fileNew.addActionListener(this);
        fileOpen = new JMenuItem();
        fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.META_MASK));
        fileOpen.addActionListener(this);
        fileSave = new JMenuItem();
        fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
        fileSave.addActionListener(this);
        fileSaveAs = new JMenuItem();
        fileSaveAs.addActionListener(this);
        fileExport = new JMenuItem();
        fileExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.META_MASK));
        fileExport.addActionListener(this);
        fileClose = new JMenuItem();
        fileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.META_MASK));
        fileClose.addActionListener(this);

        // Edit menu items
        editCopy = new JMenuItem();
        editCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.META_MASK));
        editCopy.addActionListener(this);
        editCut = new JMenuItem();
        editCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.META_MASK));
        editCut.addActionListener(this);
        editPaste = new JMenuItem();
        editPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.META_MASK));
        editPaste.addActionListener(this);
        editDelete = new JMenuItem();
        editDelete.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_BACK_SPACE, ActionEvent.META_MASK));
        editDelete.addActionListener(this);
        editSelectAll = new JMenuItem();
        editSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.META_MASK));
        editSelectAll.addActionListener(this);

        //
        // editCopy.setEnabled(false);
        // editCut.setEnabled(false);
        // editPaste.setEnabled(false);
        // editDelete.setEnabled(false);
        // editSelectAll.setEnabled(false);
        // editResizeShape.setEnabled(false);

        // Folding/Shapes menu items
        angleMenu = new JMenu();
        foldingThirty = new JMenuItem();
        foldingThirty.addActionListener(this);
        foldingFortyFive = new JMenuItem();
        foldingFortyFive.addActionListener(this);
        foldingNinety = new JMenuItem();
        foldingNinety.addActionListener(this);
        foldingCustomAngle = new JMenuItem();
        foldingCustomAngle.addActionListener(this);
        foldingEdgeSelect = new JMenuItem();
        foldingEdgeSelect.addActionListener(this);
        foldingPointSelect = new JMenuItem();
        foldingPointSelect.addActionListener(this);
        shapeMenu = new JMenu();
        foldingFoldShapes = new JMenuItem();
        foldingFoldShapes.addActionListener(this);
        foldingConnectShapes = new JMenuItem();
        foldingConnectShapes.addActionListener(this);
        foldingDetachShapes = new JMenuItem();
        foldingDetachShapes.addActionListener(this);
        foldingResizeShape = new JMenuItem();
        foldingResizeShape.addActionListener(this);

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
        viewMenu = new JMenu();
        windowShowTop = new JMenuItem();
        windowShowTop.addActionListener(this);
        windowShowBack = new JMenuItem();
        windowShowBack.addActionListener(this);
        windowShowLeft = new JMenuItem();
        windowShowLeft.addActionListener(this);
        windowShowHideTools = new JMenuItem();
        windowShowHideTools.addActionListener(this);
        windowShowHideInfo = new JMenuItem();
        windowShowHideInfo.addActionListener(this);
        perspectiveMenu = new JMenu();
        windowChangePerspective = new JMenuItem();
        windowChangePerspective.addActionListener(this);
        windowSavePerspective = new JCheckBoxMenuItem();
        windowSavePerspective.addActionListener(this);
        windowLoadPerspective = new JCheckBoxMenuItem();
        windowLoadPerspective.addActionListener(this);
        windowResizePerspective = new JMenuItem();
        windowResizePerspective.addActionListener(this);

        // Help menu items
        helpManual = new JMenuItem();
        helpManual.addActionListener(this);
        helpQuickStartGuide = new JMenuItem();
        helpQuickStartGuide.addActionListener(this);
        languageMenu = new JMenu();
        languageGroup = new ButtonGroup();
        languaguesMenuItem = new ArrayList<JRadioButtonMenuItem>();
        for (String s : Messages.Utils.getDisplayedLanguages()) {
            JRadioButtonMenuItem j = new JRadioButtonMenuItem(s);
            j.addActionListener(this);
            languaguesMenuItem.add(j);
            languageGroup.add(j);
            languageMenu.add(j);
            if (s.equals(Messages.getLocale().getDisplayCountry(Messages.getLocale()))) {
                j.setSelected(true);
            }
        }

        // Set up the file menu
        fileMenu = new JMenu();
        fileMenu.add(fileNew);
        fileMenu.add(fileOpen);
        fileMenu.add(new JSeparator());
        fileMenu.add(fileSave);
        fileMenu.add(fileSaveAs);
        fileMenu.add(new JSeparator());
        fileMenu.add(fileExport);
        fileMenu.add(fileClose);

        // Set up the edit menu
        editMenu = new JMenu();
        editMenu.add(editCopy);
        editMenu.add(editCut);
        editMenu.add(editPaste);
        editMenu.add(editDelete);
        editMenu.add(new JSeparator());
        editMenu.add(editSelectAll);

        // Set up the folding/shapes menu
        foldingMenu = new JMenu();
        foldingMenu.add(angleMenu);
        angleMenu.add(foldingThirty);
        angleMenu.add(foldingFortyFive);
        angleMenu.add(foldingNinety);
        angleMenu.add(foldingCustomAngle);
        foldingMenu.add(new JSeparator());
        foldingMenu.add(foldingEdgeSelect);
        foldingMenu.add(foldingPointSelect);
        foldingMenu.add(new JSeparator());
        foldingMenu.add(shapeMenu);
        shapeMenu.add(foldingFoldShapes);
        shapeMenu.add(foldingConnectShapes);
        shapeMenu.add(foldingDetachShapes);
        shapeMenu.add(foldingResizeShape);

        // Set up the window menu
        windowMenu = new JMenu();
        windowMenu.add(viewMenu);
        windowMenu.add(new JSeparator());
        windowMenu.add(windowShowHideTools);
        windowMenu.add(windowShowHideInfo);
        windowMenu.add(new JSeparator());
        windowMenu.add(perspectiveMenu);
        viewMenu.add(windowShowTop);
        viewMenu.add(windowShowBack);
        viewMenu.add(windowShowLeft);
        perspectiveMenu.add(windowChangePerspective);
        perspectiveMenu.add(windowSavePerspective);
        perspectiveMenu.add(windowLoadPerspective);
        perspectiveMenu.add(windowResizePerspective);

        // Set up the help menu
        helpMenu = new JMenu();
        helpMenu.add(helpManual);
        helpMenu.add(helpQuickStartGuide);
        helpMenu.add(languageMenu);

        // Create the menuBar to contain the menus
        menuBar = new JMenuBar();

        // Add the menus to the bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(foldingMenu);
        menuBar.add(windowMenu);
        menuBar.add(helpMenu);

        // Add the menu bar to the frame
        setJMenuBar(menuBar);

        // draw labels
        paintMenu();
    }

    private void paintMenu() {

        fileMenu.setText(Messages.getString(Messages.FI));
        fileOpen.setText(Messages.getString(Messages.FI + ".open"));
        fileNew.setText(Messages.getString(Messages.FI + ".new"));
        fileSave.setText(Messages.getString(Messages.FI + ".save"));
        fileSaveAs.setText(Messages.getString(Messages.FI + ".saveas"));
        fileExport.setText(Messages.getString(Messages.FI + ".export"));
        fileClose.setText(Messages.getString(Messages.FI + ".close"));

        editMenu.setText(Messages.getString(Messages.ED));
        editCopy.setText(Messages.getString(Messages.ED + ".copy"));
        editCut.setText(Messages.getString(Messages.ED + ".cut"));
        editPaste.setText(Messages.getString(Messages.ED + ".paste"));
        editDelete.setText(Messages.getString(Messages.ED + ".delete"));
        editSelectAll.setText(Messages.getString(Messages.ED + ".selectall"));

        foldingMenu.setText(Messages.getString(Messages.FO));
        angleMenu.setText(Messages.getString(Messages.FO + ".angle"));
        foldingThirty.setText(Messages.getString(Messages.FO + ".angle.30"));
        foldingFortyFive.setText(Messages.getString(Messages.FO + ".angle.45"));
        foldingNinety.setText(Messages.getString(Messages.FO + ".angle.90"));
        foldingCustomAngle.setText(Messages.getString(Messages.FO + ".angle.custom"));
        foldingEdgeSelect.setText(Messages.getString(Messages.FO + ".edgesel"));
        foldingPointSelect.setText(Messages.getString(Messages.FO + ".pointsel"));
        shapeMenu.setText(Messages.getString(Messages.FO + ".shape"));
        foldingConnectShapes.setText(Messages.getString(Messages.FO + ".shape.connect"));
        foldingFoldShapes.setText(Messages.getString(Messages.FO + ".shape.fold"));
        foldingDetachShapes.setText(Messages.getString(Messages.FO + ".shape.detach"));
        foldingResizeShape.setText(Messages.getString(Messages.FO + ".shape.resize"));

        windowMenu.setText(Messages.getString(Messages.WI));
        viewMenu.setText(Messages.getString(Messages.WI + ".view"));
        windowShowTop.setText(Messages.getString(Messages.WI + ".view.top"));
        windowShowLeft.setText(Messages.getString(Messages.WI + ".view.left"));
        windowShowBack.setText(Messages.getString(Messages.WI + ".view.back"));
        windowShowHideInfo.setText(Messages.getString(Messages.WI + ".info"));
        windowShowHideTools.setText(Messages.getString(Messages.WI + ".tools"));
        perspectiveMenu.setText(Messages.getString(Messages.WI + ".persp"));
        windowResizePerspective.setText(Messages.getString(Messages.WI + ".persp.resize"));
        windowSavePerspective.setText(Messages.getString(Messages.WI + ".persp.save"));

        helpMenu.setText(Messages.getString(Messages.HE));
        helpManual.setText(Messages.getString(Messages.HE + ".manual"));
        helpQuickStartGuide.setText(Messages.getString(Messages.HE + ".guide"));
        languageMenu.setText(Messages.getString(Messages.HE + ".language"));
    }

    // Action listener. For now, this method is just a placeholder.
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(this.fileClose)) {
            setVisible(false);
            dispose();
            System.exit(0);
        } else if (e.getSource().equals(fileOpen)) {
            // open?
        } else if (e.getSource().equals(fileSave)) {
            // save?
        } else {
            if (languaguesMenuItem.contains(e.getSource())) {
                JMenuItem j = (JMenuItem) e.getSource();
                if (Messages.Utils.getDisplayedLanguages().contains(j.getText())) {
                    Messages.setBundle(Messages.Utils.getLocale(j.getText()));
                    paintMenu();
                    // TODO add paint methods for other components if we have
                    // panels or other stuff with text.
                    return;
                }
            }
        }

        System.out.println("That action is not yet implemented");
    }

    // Create the jReality canvas
    private void createJRCanvas() {

        // TODO add branch specific code here
    }

    /**
     * 
     */
    public void initPanesAndGui() {

        // create the jReality canvas and menu bars
        createJRCanvas();
        initMenuBar();

        // TODO add branch specific code here.

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        this.pack();
        setVisible(true);

    }

}
