
package foldr.main;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import foldr.messages.Messages;
import foldr.messages.MessagesUtils;

/**
 * Running class.
 * 
 * @author couretn
 * @category Run
 */
public class Driver {

    public static void main(String[] args) {

        Messages.setBundle(Locale.getDefault());
        MessagesUtils.getInstance().init(MessagesUtils.DEFAULT_PATH);

        // set the look & feel to the use the system one since swing look & feel
        // is awful. (also swing look & feel is drawn pixel by pixel. Using the
        // system's look&feel may improve performance.)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        try {
            SwingUtilities.invokeAndWait(new Runnable() {

                @Override
                public void run() {

                    GUI gui = GUI.getInstance();
                    gui.setName("Polyhedra");
                    gui.initPanesAndGui();
                    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            });
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
