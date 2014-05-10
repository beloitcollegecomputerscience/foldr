package foldr.utility;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class is in charge of displaying errors to the user.
 * This applies to errors not serious enough to throw an exception,
 * but worth warning the user about.
 */
public class ErrorHandler {
	
	/**
	 * ErrorHandler receives the newError message with a warning string.
	 * It then opens a dialog box displaying that string, and waits
	 * for the user to dismiss it.
	 * 
	 * @param theError is a String that contains the error message.
	 */
	public ErrorHandler (String theError) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, theError, "Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * A problem has occurred that the user should be warned about. We can continue the
	 * operation, or we can abort it.
	 * @param message What is the operation/problem?
	 * @return false to abort; true to go ahead and do it anyway.
	 */
	static public boolean shouldWeContinue( String message ) {
		JFrame frame = new JFrame();
		int result = JOptionPane.showConfirmDialog(frame, message, "fubar", JOptionPane.OK_CANCEL_OPTION);
		return (result==0);
	}
}

