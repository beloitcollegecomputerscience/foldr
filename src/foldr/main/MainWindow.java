/**
 * 
 */

package foldr.main;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

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
    private JMenuItem                       open;
    private JMenuItem                       create;
    private JMenuItem                       save;
    private JMenuItem                       saveas;
    private JMenuItem                       export;
    private JMenuItem                       close;

    // Edit
    private JMenu                           edit;
    private JMenuItem                       copy;
    private JMenuItem                       cut;
    private JMenuItem                       paste;
    private JMenuItem                       delete;
    private JMenuItem                       selectAll;
    private JMenuItem                       resize;

    // fold/shape
    private JMenu                           fold;
    private JMenu                           angle;
    private JMenuItem                       ang30;
    private JMenuItem                       ang45;
    private JMenuItem                       ang90;
    private JMenuItem                       angCustom;
    private JMenuItem                       edgeSelect;
    private JMenuItem                       pointSelect;
    private JMenu                           shape;
    private JMenuItem                       foldShape;
    private JMenuItem                       connect;
    private JMenuItem                       detach;

    // Window
    private JMenu                           window;
    private JMenu                           view;
    private JCheckBoxMenuItem               top;
    private JCheckBoxMenuItem               back;
    private JCheckBoxMenuItem               left;
    private JCheckBoxMenuItem               tools;
    private JCheckBoxMenuItem               informations;
    private JMenu                           perspective;
    private JMenuItem                       changeLayout;
    private JMenuItem                       saveLayout;
    private JMenuItem                       loadLayout;
    private JMenuItem                       resizeLayout;

    // Help
    private JMenu                           help;
    private JMenuItem                       manual;
    private JMenuItem                       guide;
    private JMenu                           language;
    private ButtonGroup                     lngsGroup;
    private ArrayList<JRadioButtonMenuItem> lngs;

    // ==================================
    // Panels
    // ==================================

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

        this.pack();
        this.setVisible(true);
    }

    /**
     * TODO finish
     */
    public void initMenuBar() {

        theMenuBar = new JMenuBar();

        file = new JMenu();
        open = new JMenuItem();
        open.addActionListener(this);
        create = new JMenuItem();
        create.addActionListener(this);
        save = new JMenuItem();
        save.addActionListener(this);
        saveas = new JMenuItem();
        saveas.addActionListener(this);
        export = new JMenuItem();
        export.addActionListener(this);
        close = new JMenuItem();
        close.addActionListener(this);
        file.add(open);
        file.add(create);
        file.add(save);
        file.add(saveas);
        file.add(export);
        file.add(close);
        theMenuBar.add(file);

        edit = new JMenu();
        copy = new JMenuItem();
        copy.addActionListener(this);
        cut = new JMenuItem();
        cut.addActionListener(this);
        paste = new JMenuItem();
        paste.addActionListener(this);
        delete = new JMenuItem();
        delete.addActionListener(this);
        selectAll = new JMenuItem();
        selectAll.addActionListener(this);
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(delete);
        edit.add(selectAll);
        theMenuBar.add(edit);

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

        help = new JMenu();
        manual = new JMenuItem();
        manual.addActionListener(this);
        guide = new JMenuItem();
        guide.addActionListener(this);
        language = new JMenu();
        language.addActionListener(this);
        lngs = new ArrayList<>();
        lngsGroup = new ButtonGroup();
        for (String s : LgUtils.getAvalaibleLanguages()) {
            JRadioButtonMenuItem jcmi = new JRadioButtonMenuItem(s);
            jcmi.addActionListener(this);
            language.add(jcmi);
            lngsGroup.add(jcmi);
            lngs.add(jcmi);
            if (s.equalsIgnoreCase(Lg.getLocale().getDisplayLanguage())) {
                jcmi.setSelected(true);
            }
        }
        help.add(manual);
        help.add(guide);
        help.add(language);
        theMenuBar.add(help);

        this.paintMenu();
    }

    private void paintMenu() {

        file.setText(Lg.getString(Lg.FI));
        open.setText(Lg.getString(Lg.FI + ".open"));
        create.setText(Lg.getString(Lg.FI + ".new"));
        save.setText(Lg.getString(Lg.FI + ".save"));
        saveas.setText(Lg.getString(Lg.FI + ".saveas"));
        export.setText(Lg.getString(Lg.FI + ".export"));
        close.setText(Lg.getString(Lg.FI + ".close"));

        edit.setText(Lg.getString(Lg.ED));
        copy.setText(Lg.getString(Lg.ED + ".copy"));
        cut.setText(Lg.getString(Lg.ED + ".cut"));
        paste.setText(Lg.getString(Lg.ED + ".paste"));
        delete.setText(Lg.getString(Lg.ED + ".delete"));
        selectAll.setText(Lg.getString(Lg.ED + ".selectall"));

        fold.setText(Lg.getString(Lg.FO));
        angle.setText(Lg.getString(Lg.FO + ".angle"));
        ang30.setText(Lg.getString(Lg.FO + ".angle.30"));
        ang45.setText(Lg.getString(Lg.FO + ".angle.45"));
        ang90.setText(Lg.getString(Lg.FO + ".angle.90"));
        angCustom.setText(Lg.getString(Lg.FO + ".angle.custom"));
        edgeSelect.setText(Lg.getString(Lg.FO + ".edgesel"));
        pointSelect.setText(Lg.getString(Lg.FO + ".pointsel"));
        shape.setText(Lg.getString(Lg.FO + ".shape"));
        foldShape.setText(Lg.getString(Lg.FO + ".shape.fold"));
        connect.setText(Lg.getString(Lg.FO + ".shape.connect"));
        detach.setText(Lg.getString(Lg.FO + ".shape.detach"));
        resize.setText(Lg.getString(Lg.FO + ".shape.resize"));

        window.setText(Lg.getString(Lg.WI));
        view.setText(Lg.getString(Lg.WI + ".view"));
        top.setText(Lg.getString(Lg.WI + ".view.top"));
        back.setText(Lg.getString(Lg.WI + ".view.back"));
        left.setText(Lg.getString(Lg.WI + ".view.left"));
        tools.setText(Lg.getString(Lg.WI + ".tools"));
        informations.setText(Lg.getString(Lg.WI + ".info"));
        perspective.setText(Lg.getString(Lg.WI + ".persp"));
        changeLayout.setText(Lg.getString(Lg.WI + ".persp.change"));
        saveLayout.setText(Lg.getString(Lg.WI + ".persp.save"));
        loadLayout.setText(Lg.getString(Lg.WI + ".persp.load"));
        resizeLayout.setText(Lg.getString(Lg.WI + ".persp.resize"));

        help.setText(Lg.getString(Lg.HE));
        manual.setText(Lg.getString(Lg.HE + ".manual"));
        guide.setText(Lg.getString(Lg.HE + ".guide"));
        language.setText(Lg.getString(Lg.HE + ".language"));
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
        } else if(e.getSource().equals(this.open)) {
        }else {
            for (JMenuItem j : this.lngs) {
                if (e.getSource().equals(j)) {
                    ArrayList<Locale> locales = LgUtils.getAvailableLocale();
                    for (Locale l : locales) {
                        if (l.getDisplayLanguage(l).equals(j.getText())) {
                            Lg.setBundle(l);
                            System.out.println(Lg.getLocale().getCountry()+" "+Lg.getLocale().getLanguage());
                            this.paintMenu();
                            break;
                        }
                    }
                }
            }
        }
    }
}
