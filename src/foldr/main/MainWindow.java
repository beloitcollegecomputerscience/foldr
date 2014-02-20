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

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author Ataw
 */
public class MainWindow extends JFrame implements ActionListener, MouseListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1453878868551517672L;

    // ==================================
    // Menubar
    // ==================================
    private JMenuBar          theMenuBar;

    // File
    private JMenu             file;
    private JMenuItem         open;
    private JMenuItem         create;
    private JMenuItem         save;
    private JMenuItem         saveas;
    private JMenuItem         export;
    private JMenuItem         close;

    // Edit
    private JMenu             edit;
    private JMenuItem         copy;
    private JMenuItem         cut;
    private JMenuItem         paste;
    private JMenuItem         delete;
    private JMenuItem         selectall;
    private JMenuItem         resize;

    // fold/shape
    private JMenu             fold;
    private JMenu             angle;
    private JMenuItem         ang30;
    private JMenuItem         ang45;
    private JMenuItem         ang90;
    private JMenuItem         angcustom;
    private JMenuItem         edgeselect;
    private JMenuItem         pointselect;
    private JMenu             shape;
    private JMenuItem         foldshape;
    private JMenuItem         connect;
    private JMenuItem         detach;

    // Window
    private JMenu             window;
    private JMenu             view;
    private JCheckBoxMenuItem top;
    private JCheckBoxMenuItem back;
    private JCheckBoxMenuItem left;
    private JCheckBoxMenuItem tools;
    private JCheckBoxMenuItem informations;
    private JMenu             perspective;
    private JMenuItem         changelayout;
    private JMenuItem         savelayout;
    private JMenuItem         loadlayout;
    private JMenuItem         resizelayout;

    // Help
    private JMenu             help;
    private JMenuItem         manual;
    private JMenuItem         guide;

    private JPanel            toolbar;

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

    public void initPanesAndGUI() {

        this.initMenuBar();
        this.setJMenuBar(theMenuBar);

        this.pack();
        this.setVisible(true);
    }

    public void initMenuBar() {

        theMenuBar = new JMenuBar();

        file = new JMenu(Lg.getString(Lg.FI));
        open = new JMenuItem(Lg.getString(Lg.FI + ".open"));
        create = new JMenuItem(Lg.getString(Lg.FI + ".new"));
        save = new JMenuItem(Lg.getString(Lg.FI + ".save"));
        saveas = new JMenuItem(Lg.getString(Lg.FI + ".saveas"));
        export = new JMenuItem(Lg.getString(Lg.FI + ".export"));
        close = new JMenuItem(Lg.getString(Lg.FI + ".close"));
        file.add(open);
        file.add(create);
        file.add(save);
        file.add(saveas);
        file.add(export);
        file.add(close);
        theMenuBar.add(file);

        edit = new JMenu(Lg.getString(Lg.ED));
        copy = new JMenuItem(Lg.getString(Lg.ED + ".copy"));
        cut = new JMenuItem(Lg.getString(Lg.ED + ".cut"));
        paste = new JMenuItem(Lg.getString(Lg.ED + ".paste"));
        delete = new JMenuItem(Lg.getString(Lg.ED + ".delete"));
        selectall = new JMenuItem(Lg.getString(Lg.ED + ".selectall"));
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(delete);
        edit.add(selectall);
        theMenuBar.add(edit);

        fold = new JMenu(Lg.getString(Lg.FO));
        angle = new JMenu(Lg.getString(Lg.FO + ".angle"));
        ang30 = new JMenuItem(Lg.getString(Lg.FO + ".angle.30"));
        ang45 = new JMenuItem(Lg.getString(Lg.FO + ".angle.45"));
        ang90 = new JMenuItem(Lg.getString(Lg.FO + ".angle.90"));
        angcustom = new JMenuItem(Lg.getString(Lg.FO + ".angle.custom"));
        edgeselect = new JMenuItem(Lg.getString(Lg.FO + ".edgesel"));
        pointselect = new JMenuItem(Lg.getString(Lg.FO + ".pointsel"));
        shape = new JMenu(Lg.getString(Lg.FO + ".shape"));
        foldshape = new JMenuItem(Lg.getString(Lg.FO + ".shape.fold"));
        connect = new JMenuItem(Lg.getString(Lg.FO + ".shape.connect"));
        detach = new JMenuItem(Lg.getString(Lg.FO + ".shape.detach"));
        resize = new JMenuItem(Lg.getString(Lg.FO + ".shape.resize"));
        angle.add(ang30);
        angle.add(ang45);
        angle.add(ang90);
        angle.add(angcustom);
        shape.add(foldshape);
        shape.add(connect);
        shape.add(detach);
        shape.add(resize);
        fold.add(angle);
        fold.add(edgeselect);
        fold.add(pointselect);
        fold.add(shape);
        theMenuBar.add(fold);

        window = new JMenu(Lg.getString(Lg.WI));
        view = new JMenu(Lg.getString(Lg.WI + ".view"));
        top = new JCheckBoxMenuItem(Lg.getString(Lg.WI + ".view.top"));
        back = new JCheckBoxMenuItem(Lg.getString(Lg.WI + ".view.back"));
        left = new JCheckBoxMenuItem(Lg.getString(Lg.WI + ".view.left"));
        tools = new JCheckBoxMenuItem(Lg.getString(Lg.WI + ".tools"));
        informations = new JCheckBoxMenuItem(Lg.getString(Lg.WI + ".info"));
        perspective = new JMenu(Lg.getString(Lg.WI + ".persp"));
        changelayout = new JMenuItem(Lg.getString(Lg.WI + ".persp.change"));
        savelayout = new JMenuItem(Lg.getString(Lg.WI + ".persp.save"));
        loadlayout = new JMenuItem(Lg.getString(Lg.WI + ".persp.load"));
        resizelayout = new JMenuItem(Lg.getString(Lg.WI + ".persp.resize"));
        view.add(top);
        view.add(back);
        view.add(left);
        perspective.add(changelayout);
        perspective.add(savelayout);
        perspective.add(loadlayout);
        perspective.add(resizelayout);
        window.add(view);
        window.add(tools);
        window.add(informations);
        window.add(perspective);
        theMenuBar.add(window);

        help = new JMenu(Lg.getString(Lg.HE));
        manual = new JMenuItem(Lg.getString(Lg.HE + ".manual"));
        guide = new JMenuItem(Lg.getString(Lg.HE + ".guide"));
        help.add(manual);
        help.add(guide);
        theMenuBar.add(help);
    }

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
    public void actionPerformed(ActionEvent arg0) {

    }

}
