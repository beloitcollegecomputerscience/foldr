package foldr.utility;


/**
 * An exception class to handle file exceptions, such as unreadable files or, more
 * often, files that do not have the expected data to read from.  Deals with normal 
 * FileIO problems, as well as problems of trying to open files that are corrupted or
 * apparently are not files saved by this application.
 *
 * @author Darrah Chavey
 */
public class FileException extends Exception {

	/**
	 * Serial ID to allow implementation of Serializable. We don't actually
	 * implement saving the file this way, but it is expected by "Exception".
	 */
	protected static final long serialVersionUID = 8095078020386417779L;

	/**
     * Constructs a FileException. The no parameter constructor should normally
     * be avoided, preferring the exception that includes a descriptive message.
     */
    public FileException() {
        super();
    }

    /**
     * Constructs a FileException with the specified detail message.
	 * @param errorMessage is the detail message about what went wrong.
     */
    public FileException(String errorMessage) {
        super(errorMessage);
    }

}