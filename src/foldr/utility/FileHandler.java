package foldr.utility;

/**
 * A general purpose file access class to handle reading from and writing to files.
 * 
 * Files can be read all at once, or one line at a time. If the former approach is
 * desired, use "openAndRead()"; if the later approach is desired, do an "openFile()",
 * then do "readLine()" until that method returns null, then do "closeFile()".
 * 
 * To write a file, first call "save" (with a parameter to specify whether you should
 * write to the file from which you read this data, or to a new file). Then call
 * "writeToFile()" repeatedly, and when you are done writing, call "closeFile()".
 * To write a file all at once, i.e. when you have the entire output as a single
 * String, call the "save( boolean, String )" version of the "save" method.
 * 
 * @author Darrah Chavey
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileHandler {


	/**
	 * This is a string which represents the path to the save location for the file.
	 * This is where the file dialog box starts when the user elects to open or save
	 * a file, and is shared if the program supports multiple file readers/writers.
	 * On file startup, it begins at the user's home directory. If an application 
	 * supports a "user preferences" initialization file in a known location, they 
	 * could use "setCurrentFilePath()" to locate that file, and either store a
	 * location there for future file searches, or use the method again to reset
	 * the file search start location.
	 * 
	 * This is a relative file path; it does not include the actual file name.
	 */
	protected static String currentFilePath;

	/**
	 * The currentFile object stores the logical file path and name of the file
	 * that is currently being used (i.e. last saved to.)
	 */
	protected static File currentFile;
	
	/**
	 *  The file being written to. Declared here so the full file can be written
	 *  via multiple calls to "writeToFile()".
	 */
	private FileWriter fileWriter;
	
	/** The buffer used to read in blocks from a file being read. */
	private BufferedReader bufferedReader;

	/**
	 * The default constructor for FileHandler stores the various elements of
	 * the program from which we will need to read data for file storage, or
	 * to which we will send data to re-initialize the program drawing.
	 */
	public FileHandler( ) {
		fileWriter     = null;
		bufferedReader = null;
	}
	
/* --------------------- Handling the filePath -------------------------- */

	/**
	 * Returns the path to the save location for the file.
	 * 
	 * @return currentFilePathThe the path to the save location for the file.
	 */
	public static String getFilePath() {
		return currentFilePath;
	}

	/**
	 * This sets the path to the save location for the next save. Just the path,
	 * not path and file name.
	 * 
	 * @param path
	 *            The path to the save location for the next save; it does not
	 *            include the file name.
	 */
	public static void setCurrentFilePath(String path) {
		currentFilePath = path;
	}

	/**
	 * Reset currentFilePath to null.
	 */
	public static void clearCurrentFilePath() {
		currentFilePath = null;
	}
	
	
/* ----------------- Methods for opening and reading a file ------------------- */

	
	/**
	 * Opens and reads in a file selected by the user. Returns the entire file
	 * as a single String. Returns "null" if the user cancelled the offer to open
	 * a file.
	 * 
	 * @throws FileException if you do not have read permission to the file or if
	 *                       the file itself is corrupt and cannot be read.
	 */
	public String openAndRead( ) throws FileException {

		if ( !openFile() ) {
			return null;	// The user cancelled the read request
		}

		// read in the file
		StringBuffer fileAsString = new StringBuffer();
		try {
			while (bufferedReader.ready()) {
				fileAsString.append( bufferedReader.readLine() );
				fileAsString.append( '\n' );
			}
		} catch ( IOException ioe ) {
			throw new FileException( ioe.getMessage() );
		} finally {
			closeFile();
		}

		// update the file path if the user changed it
		return fileAsString.toString();
	}

	/**
	 * Opens a file selected by the user. Prepares it for "readLine()", but does not
	 * read any of the file.
	 * 
	 * @returns true if a file was opened; false otherwise, e.g. if the user Cancelled.
	 * @throws FileException if the file cannot be read, e.g. due to permission problems.
	 */
	public boolean openFile( )  throws FileException {
		
		// Ask for the file path and file name:
		JFileChooser theFileChooser = new JFileChooser(currentFilePath);
		int returnValue = theFileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File toOpen = theFileChooser.getSelectedFile();

			// Create the reader for the file
			try {
				bufferedReader = new BufferedReader(new FileReader(toOpen));
			} catch (IOException ioe) {
				if (ioe.getMessage().contains("(Permission denied)")) {
					throw new FileException("No read permission for that file.");
				} else {
					throw new FileException("The specified file could not be read.");
				}
			}

			// update the file path if the user changed it
			currentFile = toOpen;
			setCurrentFilePath(currentFile.getParent());
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Opens a file at given path. Prepares it for "readLine()", but does not
	 * read any of the file.
	 * 
	 * @returns true if a file was opened; false otherwise, e.g. if the user Cancelled.
	 * @throws FileException if the file cannot be read, e.g. due to permission problems.
	 */
	public boolean openFile(String path)  throws FileException {
		File toOpen = new File(path);

		// Create the reader for the file
		try {
			bufferedReader = new BufferedReader(new FileReader(toOpen));
		} catch (IOException ioe) {
			if (ioe.getMessage().contains("(Permission denied)")) {
				throw new FileException("Permissions on that file do not allow you to read it.");
			} else {
				throw new FileException("The file you specified could not be read.");
			}
		}

		// update the file path if the user changed it
		currentFile = toOpen;
		setCurrentFilePath(currentFile.getParent());
		return true;
	}
	
	/**
	 * Returns a line of the file opened via "openFile()". If we have reached the end of the file, we
	 * return null. (Which is quite different than returning an empty string.)
	 * 
	 * @return The next line of the file, or null if we've reached the end of the file.
	 * @throws IOException
	 */
	public String readLine( ) throws IOException {
		if (bufferedReader == null) {
			throw new IOException( "You tried to read a file before it was opened, or after it was closed.");
		} else if (bufferedReader.ready()) {
			return bufferedReader.readLine();
		} else {
			return null;
		}
	}
	
/* ------------------- Methods for writing to a file --------------------- */


	/**
	 * Interface method for createFile(), and also checks if it needs to prompt
	 * the user for the file name and path it is a save as or if none is
	 * specified. This opens, or creates, the new file, and prepares it to be
	 * written to. The rest of the program must handle the actual writing.
	 * 
	 * @param Tells this method whether to save to a new file, i.e. "Save As",
	 *        or save to the same file, i.e. "Save".
	 */
	// TODO: Is currently creating the new file in the workspace directory instead of
	//       in the previously read directory. Fix this bug.
	// TODO: If the user cancels a write operation (in the dialog box), this crashes.
	public void save(boolean makeNewFile) {

		// If the action was "save" and there is a valid last used file path, this method 
		// can simply pass the file path into the createFile() method to overwrite the old file.
		if (makeNewFile == false && currentFile != null && currentFile.exists() && currentFile.canRead()) {
			try {
				// TODO: Should create a temporary file, then have the closeFile method rename it
				createFile(currentFile.getName());
			} catch (FileException fe) {
				// Error dialog.JOptionPane.ERROR_MESSAGE);
			}

		// Prompting the user for the file path is handled by a JFileChooser object.
		} else {
			JFileChooser theChooser = new JFileChooser(currentFilePath);
			int returnValue = theChooser.showSaveDialog(null);

			// Send the file path and name information to
			// the createFile method if the user clicked "OK".
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				currentFile = theChooser.getSelectedFile();
				try {
					createFile(currentFile.getName());
				} catch (FileException fe) {
					// Error dialog.
					JOptionPane.showMessageDialog(null, fe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/**
	 * Using the provided string as the full contents of a file, write out that file.
	 * 
	 * @param Tells this method whether to save to a new file, i.e. "Save As",
	 *        or save to the same file, i.e. "Save".
	 * @param fileContents the String to be written to the file.
	 * @throws FileException if there is an error in writing out the file
	 */
	public void save(boolean makeNewFile, String fileContents ) throws FileException {
		save( makeNewFile );
		writeToFile( fileContents );		// May throw FileException
		closeFile();
	}

	/**
	 * Create a new, empty file, which is then open and available for writing.
	 * 
	 * @param fileName
	 *            The name of the file being created.
	 * @throws FileException
	 *             If there is an error creating the file, e.g. no write permission
	 *             to the currentFilePath directory
	 */
	private void createFile(String fileName) throws FileException {
		File fileToSave = new File(fileName);
		try {
			fileWriter = new FileWriter(fileToSave);
		} catch (IOException ioe) {
			throw new FileException(ioe.getMessage());
		}

		// Update the current file path
		currentFile = fileToSave;
		setCurrentFilePath(currentFile.getParent());
	}
	
	/**
	 * Write some additional text to the previously selected output file. This call can be executed
	 * multiple times on one file. When you have completed writing to the file, you must call "closeFile".
	 * 
	 * @precondition: You must already have opened the file you are going to write to.
	 * @postcondition: The output file HAS NOT been closed, and it's your responsibility to calls "closeFile"
	 * @param toWriteOut the text to be written to the output file.
	 */
	public void writeToFile( String toWriteOut ) throws FileException {
		try {
			fileWriter.write(toWriteOut);
		} catch (IOException ioe) {
			closeFile();   // See if you can save the output up to here.
			throw new FileException( ioe.getMessage() );
		} 
	}
	
	/**
	 * A file opened for reading must be closed before anything else can be done with it, e.g.
	 * before doing a "Save" to update the file.
	 * A file opened for writing must be closed for this reason as well, but also to force the
	 * write buffer to be fully pushed out to the disk.
	 */
	public void closeFile() {
		try {
			if (bufferedReader != null) {
				bufferedReader.close();
				bufferedReader = null;
			}
			if (fileWriter != null) {
				fileWriter.flush();
				fileWriter.close();
				fileWriter = null;
			}
		} catch (IOException e) { 
			new ErrorHandler( "Problem with writing to file.");
		}
	}

}