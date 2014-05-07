/**
 * 
 */

package foldr.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import foldr.messages.Messages;
import foldr.messages.MessagesUtils;

/**
 * <p>
 * The MenuBar !
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
 * 
 * @author couretn
 * @category GUI
 */
final class MenuBar extends JMenuBar {

    /**
     * 
     */
    private static final long  serialVersionUID = -8724966099923583862L;

    private ActionListener     actionManager;

    JMenu                      fileMenu, editMenu, foldingMenu, windowMenu, helpMenu;
    JMenuItem                  fileOpen, fileNew, fileSave, fileSaveAs, fileExport, fileClose;
    JMenuItem                  editCopy, editCut, editPaste, editDelete, editSelectAll;
    JMenu                      foldingAngle, foldingShape;
    JMenuItem                  foldingThirty, foldingFortyFive, foldingNinety, foldingCustomAngle,
                    foldingEdgeSelect, foldingPointSelect, foldingFoldShapes, foldingConnectShapes,
                    foldingDetachShapes, foldingResizeShape;
    JMenu                      windowView, windowPerspective;
    JMenuItem                  windowShowTop, windowShowBack, windowShowLeft, windowShowHideTools,
                    windowShowHideInfo, windowChangePerspective, windowSavePerspective,
                    windowLoadPerspective, windowResizePerspective;
    JMenu                      helpLanguage;
    JMenuItem                  helpManual, helpQuickStartGuide;
    ButtonGroup                langGroup;
    List<JRadioButtonMenuItem> liLanguages;

    /**
     * @param actionManager
     */
    public MenuBar(ActionListener actionManager) {

        super();
        this.actionManager = actionManager;
        init();
    }

    /**
     * 
     */
    private void init() {

        // File submenu
        fileMenu = new JMenu("File");
        this.add(fileMenu);// fileMenu -> this
        fileNew = new JMenuItem("New");
        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.META_MASK));
        fileNew.addActionListener(actionManager);
        fileMenu.add(fileNew);
        fileOpen = new JMenuItem("Open");
        fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.META_MASK));
        fileOpen.addActionListener(actionManager);
        fileMenu.add(fileOpen);
        fileSave = new JMenuItem("Save");
        fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
        fileSave.addActionListener(actionManager);
        fileMenu.add(fileSave);
        fileSaveAs = new JMenuItem("Save As");
        fileSaveAs.addActionListener(actionManager);
        fileMenu.add(fileSaveAs);
        fileExport = new JMenuItem("Export");
        fileExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.META_MASK));
        fileExport.addActionListener(actionManager);
        fileMenu.add(fileExport);
        fileClose = new JMenuItem("Close");
        fileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.META_MASK));
        fileClose.addActionListener(actionManager);
        fileMenu.add(fileClose);

        // Edit submenu
        editMenu = new JMenu("Edit");
        this.add(editMenu); // editMenu -> this
        editCopy = new JMenuItem("Copy");
        editCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.META_MASK));
        editCopy.addActionListener(actionManager);
        editMenu.add(editCopy);
        editCut = new JMenuItem("Cut");
        editCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.META_MASK));
        editCut.addActionListener(actionManager);
        editMenu.add(editCut);
        editPaste = new JMenuItem("Paste");
        editPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.META_MASK));
        editPaste.addActionListener(actionManager);
        editMenu.add(editPaste);
        editMenu.add(new JSeparator()); // separate
        editDelete = new JMenuItem("Delete");
        editDelete.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_BACK_SPACE, ActionEvent.META_MASK));
        editDelete.addActionListener(actionManager);
        editMenu.add(editDelete);
        editSelectAll = new JMenuItem("Select All");
        editSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.META_MASK));
        editSelectAll.addActionListener(actionManager);
        editMenu.add(editSelectAll);

        // Folding/Shapes submenu
        foldingMenu = new JMenu("Folding");
        this.add(foldingMenu); // folMenu -> this
        foldingAngle = new JMenu("Angle");
        foldingMenu.add(foldingAngle);
        foldingThirty = new JMenuItem("Rotate 30 Degrees");
        foldingThirty.addActionListener(actionManager);
        foldingAngle.add(foldingThirty);
        foldingFortyFive = new JMenuItem("Rotate 45 Degrees");
        foldingFortyFive.addActionListener(actionManager);
        foldingAngle.add(foldingFortyFive);
        foldingNinety = new JMenuItem("Rotate 90 Degrees");
        foldingNinety.addActionListener(actionManager);
        foldingAngle.add(foldingNinety);
        foldingCustomAngle = new JMenuItem("Custom Angle");
        foldingCustomAngle.addActionListener(actionManager);
        foldingAngle.add(foldingCustomAngle);
        foldingMenu.add(new JSeparator()); // separate
        foldingEdgeSelect = new JMenuItem("Edge Select");
        foldingEdgeSelect.addActionListener(actionManager);
        foldingMenu.add(foldingEdgeSelect);
        foldingPointSelect = new JMenuItem("Point Select");
        foldingPointSelect.addActionListener(actionManager);
        foldingMenu.add(foldingPointSelect);
        foldingMenu.add(new JSeparator()); // separate
        foldingShape = new JMenu("Shape");
        foldingMenu.add(foldingShape); // foldShape -> foldMenu
        foldingFoldShapes = new JMenuItem("Fold Shapes");
        foldingFoldShapes.addActionListener(actionManager);
        foldingShape.add(foldingFoldShapes);
        foldingConnectShapes = new JMenuItem("Connect Shapes");
        foldingConnectShapes.addActionListener(actionManager);
        foldingShape.add(foldingConnectShapes);
        foldingDetachShapes = new JMenuItem("Detach Shapes");
        foldingDetachShapes.addActionListener(actionManager);
        foldingShape.add(foldingDetachShapes);
        foldingResizeShape = new JMenuItem("Resize shape");
        foldingResizeShape.addActionListener(actionManager);
        foldingShape.add(foldingResizeShape);

        // Window submenu
        windowMenu = new JMenu("Window");
        this.add(windowMenu);// winMenu -> this
        windowView = new JMenu("View");
        windowMenu.add(windowView); // winView -> winMenu
        windowShowTop = new JCheckBoxMenuItem("Show top", true);
        windowShowTop.addActionListener(actionManager);
        windowView.add(windowShowTop);
        windowShowBack = new JCheckBoxMenuItem("Show Back", true);
        windowShowBack.addActionListener(actionManager);
        windowView.add(windowShowBack);
        windowShowLeft = new JCheckBoxMenuItem("Show Left", true);
        windowShowLeft.addActionListener(actionManager);
        windowView.add(windowShowLeft);
        windowMenu.add(new JSeparator()); // separate
        windowShowHideTools = new JMenuItem("Show/Hide Tools");
        windowShowHideTools.addActionListener(actionManager);
        windowMenu.add(windowShowHideTools);
        windowShowHideInfo = new JMenuItem("Show/Hide Information Panel");
        windowShowHideInfo.addActionListener(actionManager);
        windowMenu.add(windowShowHideInfo);
        windowMenu.add(new JSeparator()); // separate
        windowPerspective = new JMenu("Perspective");
        windowMenu.add(windowPerspective); // winPersp -> winMenu
        windowChangePerspective = new JMenuItem("Change Perspective Layout");
        windowChangePerspective.addActionListener(actionManager);
        windowPerspective.add(windowChangePerspective);
        windowSavePerspective = new JMenuItem("Save Perspective Layout");
        windowSavePerspective.addActionListener(actionManager);
        windowPerspective.add(windowSavePerspective);
        windowLoadPerspective = new JMenuItem("Load Perspective Layout");
        windowLoadPerspective.addActionListener(actionManager);
        windowPerspective.add(windowLoadPerspective);
        windowResizePerspective = new JMenuItem("Resize Perspective");
        windowResizePerspective.addActionListener(actionManager);
        windowPerspective.add(windowResizePerspective);

        // Help menu items
        helpMenu = new JMenu("Help");
        this.add(helpMenu); // helpMenu -> this
        helpManual = new JMenuItem("Manual");
        helpManual.addActionListener(actionManager);
        helpMenu.add(helpManual);
        helpQuickStartGuide = new JMenuItem("Quick Start Guide");
        helpQuickStartGuide.addActionListener(actionManager);
        helpMenu.add(helpQuickStartGuide);

        helpLanguage = new JMenu("Languages");
        helpMenu.add(helpLanguage);
        liLanguages = new ArrayList<>();
        langGroup = new ButtonGroup();
        for (String s : MessagesUtils.getInstance().getDisplayedLanguages()) {
            JRadioButtonMenuItem jrbmi = new JRadioButtonMenuItem(s, false);
            jrbmi.addActionListener(actionManager);
            if (s.equals(Messages.getLocale().getDisplayLanguage(Messages.getLocale()))) {
                jrbmi.setSelected(true);
            }
            liLanguages.add(jrbmi);
            helpLanguage.add(jrbmi);
            langGroup.add(jrbmi);
        }
    }

    /**
     * 
     */
    public void label() {

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
    }

}
