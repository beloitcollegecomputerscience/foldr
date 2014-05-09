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
        fileMenu.setName(Messages.FI);
        this.add(fileMenu);// fileMenu -> this
        fileNew = new JMenuItem("New");
        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.META_MASK));
        fileNew.addActionListener(actionManager);
        fileNew.setName(Messages.FI + ".new");
        fileMenu.add(fileNew);
        fileOpen = new JMenuItem("Open");
        fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.META_MASK));
        fileOpen.addActionListener(actionManager);
        fileOpen.setName(Messages.FI + ".open");
        fileMenu.add(fileOpen);
        fileSave = new JMenuItem("Save");
        fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
        fileSave.addActionListener(actionManager);
        fileSave.setName(Messages.FI + ".save");
        fileMenu.add(fileSave);
        fileSaveAs = new JMenuItem("Save As");
        fileSaveAs.addActionListener(actionManager);
        fileSaveAs.setName(Messages.FI + ".saveas");
        fileMenu.add(fileSaveAs);
        fileExport = new JMenuItem("Export");
        fileExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.META_MASK));
        fileExport.addActionListener(actionManager);
        fileExport.setName(Messages.FI + ".export");
        fileMenu.add(fileExport);
        fileClose = new JMenuItem("Close");
        fileClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.META_MASK));
        fileClose.addActionListener(actionManager);
        fileClose.setName(Messages.FI + ".close");
        fileMenu.add(fileClose);

        // Edit submenu
        editMenu = new JMenu("Edit");
        editMenu.setName(Messages.ED);
        this.add(editMenu); // editMenu -> this
        editCopy = new JMenuItem("Copy");
        editCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.META_MASK));
        editCopy.addActionListener(actionManager);
        editCopy.setName(Messages.ED + ".copy");
        editMenu.add(editCopy);
        editCut = new JMenuItem("Cut");
        editCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.META_MASK));
        editCut.addActionListener(actionManager);
        editCut.setName(Messages.ED + ".cut");
        editMenu.add(editCut);
        editPaste = new JMenuItem("Paste");
        editPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.META_MASK));
        editPaste.addActionListener(actionManager);
        editPaste.setName(Messages.ED + ".paste");
        editMenu.add(editPaste);
        editMenu.add(new JSeparator()); // separate
        editDelete = new JMenuItem("Delete");
        editDelete.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_BACK_SPACE, ActionEvent.META_MASK));
        editDelete.addActionListener(actionManager);
        editDelete.setName(Messages.ED + ".delete");
        editMenu.add(editDelete);
        editSelectAll = new JMenuItem("Select All");
        editSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.META_MASK));
        editSelectAll.addActionListener(actionManager);
        editSelectAll.setName(Messages.ED + ".selectall");
        editMenu.add(editSelectAll);

        // Folding/Shapes submenu
        foldingMenu = new JMenu("Folding");
        foldingMenu.setName(Messages.FO);
        this.add(foldingMenu); // folMenu -> this
        foldingAngle = new JMenu("Angle");
        foldingAngle.setName(Messages.FO + ".angle");
        foldingMenu.add(foldingAngle);
        foldingThirty = new JMenuItem("Rotate 30 Degrees");
        foldingThirty.addActionListener(actionManager);
        foldingThirty.setName(Messages.FO + ".angle.30");
        foldingAngle.add(foldingThirty);
        foldingFortyFive = new JMenuItem("Rotate 45 Degrees");
        foldingFortyFive.addActionListener(actionManager);
        foldingFortyFive.setName(Messages.FO + ".angle.45");
        foldingAngle.add(foldingFortyFive);
        foldingNinety = new JMenuItem("Rotate 90 Degrees");
        foldingNinety.addActionListener(actionManager);
        foldingNinety.setName(Messages.FO + ".angle.90");
        foldingAngle.add(foldingNinety);
        foldingCustomAngle = new JMenuItem("Custom Angle");
        foldingCustomAngle.addActionListener(actionManager);
        foldingCustomAngle.setName(Messages.FO + ".angle.custom");
        foldingAngle.add(foldingCustomAngle);
        foldingMenu.add(new JSeparator()); // separate
        foldingEdgeSelect = new JMenuItem("Edge Select");
        foldingEdgeSelect.addActionListener(actionManager);
        foldingEdgeSelect.setName(Messages.FO + ".edgesel");
        foldingMenu.add(foldingEdgeSelect);
        foldingPointSelect = new JMenuItem("Point Select");
        foldingPointSelect.addActionListener(actionManager);
        foldingPointSelect.setName(Messages.FO + ".pointsel");
        foldingMenu.add(foldingPointSelect);
        foldingMenu.add(new JSeparator()); // separate
        foldingShape = new JMenu("Shape");
        foldingShape.setName(Messages.FO + ".shape");
        foldingMenu.add(foldingShape); // foldShape -> foldMenu
        foldingFoldShapes = new JMenuItem("Fold Shapes");
        foldingFoldShapes.addActionListener(actionManager);
        foldingFoldShapes.setName(Messages.FO + ".shape.fold");
        foldingShape.add(foldingFoldShapes);
        foldingConnectShapes = new JMenuItem("Connect Shapes");
        foldingConnectShapes.addActionListener(actionManager);
        foldingConnectShapes.setName(Messages.FO + ".shape.connect");
        foldingShape.add(foldingConnectShapes);
        foldingDetachShapes = new JMenuItem("Detach Shapes");
        foldingDetachShapes.addActionListener(actionManager);
        foldingDetachShapes.setName(Messages.FO + ".shape.detach");
        foldingShape.add(foldingDetachShapes);
        foldingResizeShape = new JMenuItem("Resize shape");
        foldingResizeShape.addActionListener(actionManager);
        foldingResizeShape.setName(Messages.FO + ".shape.resize");
        foldingShape.add(foldingResizeShape);

        // Window submenu
        windowMenu = new JMenu("Window");
        windowMenu.setName(Messages.WI);
        this.add(windowMenu);// winMenu -> this
        windowView = new JMenu("View");
        windowView.setName(Messages.WI + ".view");
        windowMenu.add(windowView); // winView -> winMenu
        windowShowTop = new JCheckBoxMenuItem("Show top", true);
        windowShowTop.addActionListener(actionManager);
        windowShowTop.setName(Messages.WI + ".view.top");
        windowView.add(windowShowTop);
        windowShowBack = new JCheckBoxMenuItem("Show Back", true);
        windowShowBack.addActionListener(actionManager);
        windowShowBack.setName(Messages.WI + ".view.back");
        windowView.add(windowShowBack);
        windowShowLeft = new JCheckBoxMenuItem("Show Left", true);
        windowShowLeft.addActionListener(actionManager);
        windowShowLeft.setName(Messages.WI + ".view.left");
        windowView.add(windowShowLeft);
        windowMenu.add(new JSeparator()); // separate
        windowShowHideTools = new JMenuItem("Show/Hide Tools");
        windowShowHideTools.addActionListener(actionManager);
        windowShowHideTools.setName(Messages.WI + ".tools");
        windowMenu.add(windowShowHideTools);
        windowShowHideInfo = new JMenuItem("Show/Hide Information Panel");
        windowShowHideInfo.addActionListener(actionManager);
        windowShowHideInfo.setName(Messages.WI + ".info");
        windowMenu.add(windowShowHideInfo);
        windowMenu.add(new JSeparator()); // separate
        windowPerspective = new JMenu("Perspective");
        windowPerspective.setName(Messages.WI + ".persp");
        windowMenu.add(windowPerspective); // winPersp -> winMenu
        windowChangePerspective = new JMenuItem("Change Perspective Layout");
        windowChangePerspective.addActionListener(actionManager);
        windowChangePerspective.setName(Messages.WI + ".persp.change");
        windowPerspective.add(windowChangePerspective);
        windowSavePerspective = new JMenuItem("Save Perspective Layout");
        windowSavePerspective.addActionListener(actionManager);
        windowSavePerspective.setName(Messages.WI + ".persp.save");
        windowPerspective.add(windowSavePerspective);
        windowLoadPerspective = new JMenuItem("Load Perspective Layout");
        windowLoadPerspective.addActionListener(actionManager);
        windowLoadPerspective.setName(Messages.WI + ".persp.load");
        windowPerspective.add(windowLoadPerspective);
        windowResizePerspective = new JMenuItem("Resize Perspective");
        windowResizePerspective.addActionListener(actionManager);
        windowResizePerspective.setName(Messages.WI + ".persp.resize");
        windowPerspective.add(windowResizePerspective);

        // Help menu items
        helpMenu = new JMenu("Help");
        helpMenu.setName(Messages.HE);
        this.add(helpMenu); // helpMenu -> this
        helpManual = new JMenuItem("Manual");
        helpManual.addActionListener(actionManager);
        helpManual.setName(Messages.HE + ".manual");
        helpMenu.add(helpManual);
        helpQuickStartGuide = new JMenuItem("Quick Start Guide");
        helpQuickStartGuide.addActionListener(actionManager);
        helpQuickStartGuide.setName(Messages.HE + ".guide");
        helpMenu.add(helpQuickStartGuide);

        helpLanguage = new JMenu("Languages");
        helpLanguage.setName(Messages.HE + ".language");
        helpMenu.add(helpLanguage);
        liLanguages = new ArrayList<JRadioButtonMenuItem>();
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
     * <p>
     * Update the text of the menu items.
     */
    public void label() {

        // menu bar
        // file submenu
        fileMenu.setText(Messages.getString(fileMenu.getName()));
        fileOpen.setText(Messages.getString(fileOpen.getName()));
        fileNew.setText(Messages.getString(fileNew.getName()));
        fileSave.setText(Messages.getString(fileSave.getName()));
        fileSaveAs.setText(Messages.getString(fileSaveAs.getName()));
        fileExport.setText(Messages.getString(fileExport.getName()));
        fileClose.setText(Messages.getString(fileClose.getName()));
        // edit submenu
        editMenu.setText(Messages.getString(editMenu.getName()));
        editCopy.setText(Messages.getString(editCopy.getName()));
        editCut.setText(Messages.getString(editCut.getName()));
        editPaste.setText(Messages.getString(editPaste.getName()));
        editDelete.setText(Messages.getString(editDelete.getName()));
        // folding submenu
        foldingMenu.setText(Messages.getString(foldingMenu.getName()));
        foldingThirty.setText(Messages.getString(foldingThirty.getName()));
        foldingFortyFive.setText(Messages.getString(foldingFortyFive.getName()));
        foldingNinety.setText(Messages.getString(foldingNinety.getName()));
        foldingCustomAngle.setText(Messages.getString(foldingCustomAngle.getName()));
        foldingEdgeSelect.setText(Messages.getString(foldingEdgeSelect.getName()));
        foldingPointSelect.setText(Messages.getString(foldingPointSelect.getName()));
        foldingFoldShapes.setText(Messages.getString(foldingFoldShapes.getName()));
        foldingConnectShapes.setText(Messages.getString(foldingConnectShapes.getName()));
        foldingDetachShapes.setText(Messages.getString(foldingDetachShapes.getName()));
        // window submenu
        windowMenu.setText(Messages.getString(windowMenu.getName()));
        windowShowTop.setText(Messages.getString(windowShowTop.getName()));
        windowShowBack.setText(Messages.getString(windowShowBack.getName()));
        windowShowLeft.setText(Messages.getString(windowShowLeft.getName()));
        windowShowHideInfo.setText(Messages.getString(windowShowHideInfo.getName()));
        windowShowHideTools.setText(Messages.getString(windowShowHideTools.getName()));
        windowChangePerspective.setText(Messages.getString(windowChangePerspective.getName()));
        windowSavePerspective.setText(Messages.getString(windowSavePerspective.getName()));
        windowLoadPerspective.setText(Messages.getString(windowLoadPerspective.getName()));
        windowResizePerspective.setText(Messages.getString(windowResizePerspective.getName()));
        // help submenu
        helpMenu.setText(Messages.getString(helpMenu.getName()));
        helpManual.setText(Messages.getString(helpManual.getName()));
        helpQuickStartGuide.setText(Messages.getString(helpQuickStartGuide.getName()));
        helpLanguage.setText(Messages.getString(helpLanguage.getName()));
    }

}
