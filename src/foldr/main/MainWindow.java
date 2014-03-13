/**
 * 
 */

package foldr.main;

import java.awt.Component;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import de.jreality.plugin.JRViewer;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.Viewer;
import de.jreality.ui.JRealitySplashScreen;
import de.jreality.util.SceneGraphUtility;

/**
 * @author Nathanaël COURET
 */
public class MainWindow extends JFrame implements ActionListener, MouseListener {

    /**
     * 
     */
    private static final long               serialVersionUID = 1453878868551517672L;

    // ==================================
    // Menubar
    // ==================================
    private JMenuBar                        theMenuBar;

    // File
    private JMenu                           file;
    private JMenuItem                       open, create, save, saveas, export, close;

    // Edit
    private JMenu                           edit;
    private JMenuItem                       copy, cut, paste, delete, selectAll, resize;
    // fold/shape
    private JMenu                           fold;
    private JMenu                           angle;
    private JMenuItem                       ang30, ang45, ang90, angCustom;
    private JMenuItem                       edgeSelect, pointSelect;
    private JMenu                           shape;
    private JMenuItem                       foldShape, connect, detach;

    // Window
    private JMenu                           window;
    private JMenu                           view;
    private JCheckBoxMenuItem               top, back, left, tools;
    private JCheckBoxMenuItem               informations;
    private JMenu                           perspective;
    private JMenuItem                       changeLayout, saveLayout, loadLayout, resizeLayout;

    // Help
    private JMenu                           help;
    private JMenuItem                       manual, guide;
    private JMenu                           languages;
    private ButtonGroup                     lngsGroup;
    private ArrayList<JRadioButtonMenuItem> languagesList;

    // FIXME explicit names

    // ==================================
    // Panels
    // ==================================
    private JDesktopPane                    internalDesktop;
    private JInternalFrame                  JRFrame;

    // ==================================
    // JReality
    // ==================================
    private static SceneGraphComponent      root             =
                                                                 SceneGraphUtility.createFullSceneGraphComponent("root");

    // ==================================
    // Constructor
    // ==================================
    /**
     * @throws HeadlessException
     */
    public MainWindow()
        throws HeadlessException {

    }

    /**
     * @param arg0
     */
    public MainWindow(GraphicsConfiguration arg0) {

        super(arg0);
    }

    /**
     * @param arg0
     * @throws HeadlessException
     */
    public MainWindow(String arg0)
        throws HeadlessException {

        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public MainWindow(String arg0, GraphicsConfiguration arg1) {

        super(arg0, arg1);
    }

    // ==================================
    // Methods
    // ==================================
    /**
     * TODO write
     */
    public void initPanesAndGUI() {

        this.initMenuBar();
        this.setJMenuBar(theMenuBar);

        JRViewer viewer = JRViewer.createJRViewer(root);
        viewer.startupLocal();

        Viewer v = viewer.getViewer();

        internalDesktop = new JDesktopPane();
        JRFrame = new JInternalFrame("canvas", true, true, true, true);
        JRFrame.setSize(480, 320);
        JRFrame.add((Component) v.getViewingComponent());
        internalDesktop.add(JRFrame);
        this.add(internalDesktop);
        JRFrame.setVisible(true);
        this.pack();
        this.setVisible(true);
    }

    /**
     * TODO finish. almost
     */
    public void initMenuBar() {

        theMenuBar = new JMenuBar();

        // File menu
        file = new JMenu();
        open = new JMenuItem();
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)); // CTRL+O
        create = new JMenuItem();
        create.addActionListener(this);
        create.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)); // CTRL+N
        save = new JMenuItem();
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)); // CTRL+S
        saveas = new JMenuItem();
        saveas.addActionListener(this);
        saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK |
            ActionEvent.SHIFT_MASK));
        export = new JMenuItem();
        export.addActionListener(this);
        export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        close = new JMenuItem();
        close.addActionListener(this);
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        file.add(open);
        file.add(create);
        file.add(save);
        file.add(saveas);
        file.add(export);
        file.add(close);
        theMenuBar.add(file);

        // edit Menu
        edit = new JMenu();
        copy = new JMenuItem();
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        cut = new JMenuItem();
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        paste = new JMenuItem();
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        delete = new JMenuItem();
        delete.addActionListener(this);
        delete.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_DELETE));
        selectAll = new JMenuItem();
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(delete);
        edit.add(selectAll);
        theMenuBar.add(edit);

        // Fold menu
        fold = new JMenu();
        angle = new JMenu();
        ang30 = new JMenuItem();
        ang30.addActionListener(this);
        ang45 = new JMenuItem();
        ang45.addActionListener(this);
        ang90 = new JMenuItem();
        ang90.addActionListener(this);
        angCustom = new JMenuItem();
        angCustom.addActionListener(this);
        edgeSelect = new JMenuItem();
        edgeSelect.addActionListener(this);
        pointSelect = new JMenuItem();
        pointSelect.addActionListener(this);
        shape = new JMenu();
        foldShape = new JMenuItem();
        foldShape.addActionListener(this);
        connect = new JMenuItem();
        connect.addActionListener(this);
        detach = new JMenuItem();
        detach.addActionListener(this);
        resize = new JMenuItem();
        resize.addActionListener(this);
        angle.add(ang30);
        angle.add(ang45);
        angle.add(ang90);
        angle.add(angCustom);
        shape.add(foldShape);
        shape.add(connect);
        shape.add(detach);
        shape.add(resize);
        fold.add(angle);
        fold.add(edgeSelect);
        fold.add(pointSelect);
        fold.add(shape);
        theMenuBar.add(fold);

        // Window Menu
        window = new JMenu();
        view = new JMenu();
        top = new JCheckBoxMenuItem();
        top.addActionListener(this);
        back = new JCheckBoxMenuItem();
        back.addActionListener(this);
        left = new JCheckBoxMenuItem();
        left.addActionListener(this);
        tools = new JCheckBoxMenuItem();
        tools.addActionListener(this);
        informations = new JCheckBoxMenuItem();
        informations.addActionListener(this);
        perspective = new JMenu();
        changeLayout = new JMenuItem();
        changeLayout.addActionListener(this);
        saveLayout = new JMenuItem();
        saveLayout.addActionListener(this);
        loadLayout = new JMenuItem();
        loadLayout.addActionListener(this);
        resizeLayout = new JMenuItem();
        resizeLayout.addActionListener(this);
        view.add(top);
        view.add(back);
        view.add(left);
        perspective.add(changeLayout);
        perspective.add(saveLayout);
        perspective.add(loadLayout);
        perspective.add(resizeLayout);
        window.add(view);
        window.add(tools);
        window.add(informations);
        window.add(perspective);
        theMenuBar.add(window);

        // Help menu
        help = new JMenu();
        manual = new JMenuItem();
        manual.addActionListener(this);
        guide = new JMenuItem();
        guide.addActionListener(this);
        languages = new JMenu();
        languages.addActionListener(this);
        languagesList = new ArrayList<JRadioButtonMenuItem>();
        lngsGroup = new ButtonGroup();
        for (String s : Messages.Utils.getDisplayedLanguages()) {
            JRadioButtonMenuItem jcmi = new JRadioButtonMenuItem(s);
            jcmi.addActionListener(this);
            languages.add(jcmi);
            lngsGroup.add(jcmi);
            languagesList.add(jcmi);
            if (s.equalsIgnoreCase(Messages.getLocale().getDisplayLanguage())) {
                jcmi.setSelected(true);
            }
        }
        help.add(manual);
        help.add(guide);
        help.add(languages);
        theMenuBar.add(help);

        this.paintMenu();
    }

    /**
     * 
     */
    private void paintMenu() {

        file.setText(Messages.getString(Messages.FI));
        open.setText(Messages.getString(Messages.FI + ".open"));
        create.setText(Messages.getString(Messages.FI + ".new"));
        save.setText(Messages.getString(Messages.FI + ".save"));
        saveas.setText(Messages.getString(Messages.FI + ".saveas"));
        export.setText(Messages.getString(Messages.FI + ".export"));
        close.setText(Messages.getString(Messages.FI + ".close"));

        edit.setText(Messages.getString(Messages.ED));
        copy.setText(Messages.getString(Messages.ED + ".copy"));
        cut.setText(Messages.getString(Messages.ED + ".cut"));
        paste.setText(Messages.getString(Messages.ED + ".paste"));
        delete.setText(Messages.getString(Messages.ED + ".delete"));
        selectAll.setText(Messages.getString(Messages.ED + ".selectall"));

        fold.setText(Messages.getString(Messages.FO));
        angle.setText(Messages.getString(Messages.FO + ".angle"));
        ang30.setText(Messages.getString(Messages.FO + ".angle.30"));
        ang45.setText(Messages.getString(Messages.FO + ".angle.45"));
        ang90.setText(Messages.getString(Messages.FO + ".angle.90"));
        angCustom.setText(Messages.getString(Messages.FO + ".angle.custom"));
        edgeSelect.setText(Messages.getString(Messages.FO + ".edgesel"));
        pointSelect.setText(Messages.getString(Messages.FO + ".pointsel"));
        shape.setText(Messages.getString(Messages.FO + ".shape"));
        foldShape.setText(Messages.getString(Messages.FO + ".shape.fold"));
        connect.setText(Messages.getString(Messages.FO + ".shape.connect"));
        detach.setText(Messages.getString(Messages.FO + ".shape.detach"));
        resize.setText(Messages.getString(Messages.FO + ".shape.resize"));

        window.setText(Messages.getString(Messages.WI));
        view.setText(Messages.getString(Messages.WI + ".view"));
        top.setText(Messages.getString(Messages.WI + ".view.top"));
        back.setText(Messages.getString(Messages.WI + ".view.back"));
        left.setText(Messages.getString(Messages.WI + ".view.left"));
        tools.setText(Messages.getString(Messages.WI + ".tools"));
        informations.setText(Messages.getString(Messages.WI + ".info"));
        perspective.setText(Messages.getString(Messages.WI + ".persp"));
        changeLayout.setText(Messages.getString(Messages.WI + ".persp.change"));
        saveLayout.setText(Messages.getString(Messages.WI + ".persp.save"));
        loadLayout.setText(Messages.getString(Messages.WI + ".persp.load"));
        resizeLayout.setText(Messages.getString(Messages.WI + ".persp.resize"));

        help.setText(Messages.getString(Messages.HE));
        manual.setText(Messages.getString(Messages.HE + ".manual"));
        guide.setText(Messages.getString(Messages.HE + ".guide"));
        languages.setText(Messages.getString(Messages.HE + ".language"));
    }

    // ==================================
    // Listeners
    // ==================================

    /*
     * (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent arg0) {

    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent arg0) {

    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent arg0) {

    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent arg0) {

    }

    /*
     * (non-Javadoc)
     * @see
     * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent arg0) {

    }

    /*
     * (non-Javadoc)
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(this.close)) {
            dispose();
            System.exit(0);
        } else if (e.getSource().equals(this.open)) {} else {
            for (JMenuItem j : this.languagesList) {
                if (e.getSource().equals(j)) {
                    ArrayList<Locale> locales = Messages.Utils.getAvailableLocales();
                    for (Locale l : locales) {
                        if (l.getDisplayLanguage(l).equals(j.getText())) {
                            Messages.setBundle(l);
                            this.paintMenu();
                            break;
                        }
                    }
                }
            }
        }
    }
}
