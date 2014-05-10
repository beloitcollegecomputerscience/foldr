package foldr.utility;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

import de.jreality.math.Matrix;
import de.jreality.scene.SceneGraphComponent;
import foldr.shape.Shape;
import foldr.shape.ShapeCollection;

/**
 * This class is the application specific interface to the general "FileHandler"
 * class. It calls that class to open a user selected file for purposes of
 * importing the data for a Time Visualization project. It reads in the file,
 * sending the header lines to the "ProjectSettings" class, and the rest of the
 * file to the "EventManager" class. It does no error detection on the file,
 * other than verifying that it is able to read the file and detect when it has
 * reached the end.
 * 
 * @author Darrah Chavey, Forest Johnson
 */
public class FileParser {

	/* Objects from which we read, and to which we send the input: */
	private FileHandler fileHandler;

	final public String commaSymbol = new String("_COMMA_"); // Could be useful
																// for encoding
																// commas.

	/**
	 * For the FileParser to do its job, we must know the fileHandler class to
	 * read in the file (which cannot be local, since we share it with with the
	 * writing commands), and must know the two classes to whom we send the
	 * data. The creator of this class must give us those objects.
	 * 
	 * @throws IllegalArgumentException
	 *             if someone tries to pass in null objects.
	 */
	public FileParser() {
		fileHandler = new FileHandler();
	}

	/**
	 * Locate a user specified file, and causes it to be read in to the
	 * application.
	 * 
	 */
	public void loadInput(SceneGraphComponent topScene) {
		try {
			if (fileHandler.openFile()) {
				readFile(topScene);
			} // Otherwise, the user cancelled the request to read a file.
		} catch (Exception e) {
			new ErrorHandler("Problem in FileParser.loadInput(): "
					+ e.getMessage());
		} finally {
			fileHandler.closeFile();
		}
	}

	/**
	 * READ FILE AT PATH INTO APPLICATION
	 * 
	 * @param path
	 */
	public void loadInput(String path, SceneGraphComponent topScene) {
		try {
			if (fileHandler.openFile(path)) {
				readFile(topScene);
			} // Otherwise, the user cancelled the request to read a file.
		} catch (Exception e) {
			new ErrorHandler("Problem in FileParser.loadInput(): "
					+ e.getMessage());
		} finally {
			fileHandler.closeFile();
		}
	}

	/**
	 * Reads in the file specified by the user.
	 * Create the specified shapes.
	 * 
	 * @throws IOException
	 *             if there is an error reading a file from the disk.
	 * 
	 * TODO actually make it handle errors-- look back at timeviz
	 */
	private void readFile(SceneGraphComponent topScene) throws IOException {

		String nextLine = fileHandler.readLine();
		
		while (nextLine != null) {
				
			String[] currentLine = nextLine.split(",");

			// create the shape
			Shape newShape = new Shape(Integer.parseInt(currentLine[0]),
					topScene);
			// apply the transformation matrix
			Matrix transformations = new Matrix(
					Double.parseDouble(currentLine[1]),
					Double.parseDouble(currentLine[2]),
					Double.parseDouble(currentLine[3]),
					Double.parseDouble(currentLine[4]),
					Double.parseDouble(currentLine[5]),
					Double.parseDouble(currentLine[6]),
					Double.parseDouble(currentLine[7]),
					Double.parseDouble(currentLine[8]),
					Double.parseDouble(currentLine[9]),
					Double.parseDouble(currentLine[10]),
					Double.parseDouble(currentLine[11]),
					Double.parseDouble(currentLine[12]),
					Double.parseDouble(currentLine[13]),
					Double.parseDouble(currentLine[14]),
					Double.parseDouble(currentLine[15]),
					Double.parseDouble(currentLine[16]));
			
			transformations.assignTo(newShape.shapeSGC);

			//grab the next line
			nextLine = fileHandler.readLine();
		}
	}

	/**
	 * Write out a file containing all of the .csv file information about the
	 * visualization constructed on the screen.
	 * 
	 * @precondition The file has been opened for us, we just need to write to
	 *               it.
	 */
	// TODO: The "toString()" methods listed below need to be over-ridden to
	// give us the
	// data we actually need.
	private void writeFile(FileHandler outputFile) {
		try {
			// Write the column headings
			ShapeCollection allShapes = ShapeCollection.getInstance();
			for (int i = 0; i < allShapes.getLength(); i++) {
				Shape currentShape = allShapes.getShapeFromCollection(i);
				int numberOfVertices = currentShape.getVertexCount();
				double[] transformationArray = currentShape.shapeSGC
						.getTransformation().getMatrix();

				// record how many sides the polygon has
				outputFile.writeToFile(numberOfVertices + ",");

				// record the transformation matrix
				for (int j = 0; j < transformationArray.length; j++) {
					outputFile.writeToFile(transformationArray[j] + ",");
				}

				outputFile.writeToFile("\n");
			}

		} catch (FileException e) {
			new ErrorHandler("Problem in writeFile()");
		}
	}

	/**
	 * Handle a request to "Save" or "Save As..." a project.
	 * 
	 * @param makeNewFile
	 *            true for a "Save As..." command; false for "Save".
	 */
	public void doSave(boolean makeNewFile) {
		fileHandler.save(makeNewFile); // File is created
		writeFile(fileHandler);
		// TODO: If we did a "Save", we should now rename the temporary file
		// created.
		fileHandler.closeFile();
	}

}
