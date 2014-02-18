package foldr.main;

import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Driver {

	public static void main(String[] args) {
	    Lg.setBundle(Locale.US);
	    
	 // set the look & feel to the use the system one since swing look & feel
        // is awful. (also swing look & feel is drawn pixel by pixel. Using the system's look&feel
        // may improve performance.)
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName( ) );
            // UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace( );
        } catch ( InstantiationException e ) {
            e.printStackTrace( );
        } catch ( IllegalAccessException e ) {
            e.printStackTrace( );
        } catch ( UnsupportedLookAndFeelException e ) {
            e.printStackTrace( );
        }
		MainWindow mw = new MainWindow("Foldr");
		mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mw.initPanesAndGUI();
	}

}
