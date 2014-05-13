package foldr.shape;

/**
 * 
 * My utility class for doing conversions and other methods of importance.
 * 
 * @author Ellery Addington-White
 * 
 */
public class Converter {

	/**
	 * This method takes to points and converts them to a JReality vector
	 * starting from the origin
	 * 
	 * @param cordinate1
	 *            the first coordinate to convert
	 * @param cordinate2
	 *            the second coordinate to convert
	 * @return a vector that can be used in the rotation methods
	 */
	public double[] convertPointsToVectorOnOrigin(double[] cordinate1,
			double[] cordinate2) {
		double[] returnVector = new double[3];

		// figure out which point is closer to origin
		double dist1 = Math.sqrt(
				((cordinate1[0] * cordinate1[0]) + (cordinate1[0] * cordinate1[0])
						 + (cordinate1[0] * cordinate1[0])) );

		double dist2 = Math.sqrt(
				((cordinate2[0] * cordinate2[0]) + (cordinate2[0] * cordinate2[0])
						 + (cordinate2[0] * cordinate2[0])));

		// which ever point is closer to origin is gets put on the origin with
		// the other point determining the vector.
		if (dist1 < dist2) {
			returnVector[0] = cordinate2[0] - cordinate1[0];
			returnVector[1] = cordinate2[1] - cordinate1[1];
			returnVector[2] = cordinate2[2] - cordinate1[2];
		} else {
			returnVector[0] = cordinate1[0] - cordinate2[0];
			returnVector[1] = cordinate1[1] - cordinate2[1];
			returnVector[2] = cordinate1[2] - cordinate2[2];
		}

		return returnVector;

	}

	/**
	 * Calculates a cross product of two given vectors.
	 * 
	 * @param vector1
	 *            first vector to use in calculation
	 * @param vector2
	 *            second vector to use in calculation
	 * @return the calculated cross-product
	 */
	public double[] crossProduct(double[] vector1, double[] vector2) {
		double[] crossProduct = new double[3];

		// Do Calculations for cross product
		crossProduct[0] = (vector1[1] * vector2[2]) - (vector1[2] * vector2[1]);
		crossProduct[1] = (vector1[2] * vector2[0]) - (vector1[0] * vector2[2]);
		crossProduct[2] = (vector1[0] * vector2[1]) - (vector1[1] * vector2[0]);

		// Return Cross Product
		return crossProduct;
	}

	/**
	 * Converts Cartesian coordinates to spherical coordinates
	 * 
	 * TODO: Does not really work
	 * 
	 * @param vector1
	 *            the first vector to convert
	 * @param vector2
	 *            the second vector to convert
	 * @return the converted vector
	 */
	public double[] calculateCartisanChange(double[] vector1, double[] vector2) {

		// double[] cartisan1, cartisan2;
		// cartisan1 =
		// JSci.maths.CoordinateMath.cartesianToSpherical(vector1[0],
		// vector1[1], vector1[2]);
		// cartisan2 =
		// JSci.maths.CoordinateMath.cartesianToSpherical(vector2[0],
		// vector2[1], vector2[2]);

		return null;
	}

	/**
	 * Calculate what angle we should rotate by based off of two shapes given.
	 * 
	 * @param shapeOne
	 *            this first shape to compare
	 * @param shapeTwo
	 *            the second shape to compare
	 * @return the correct angle you should rotate two shapes.
	 */
	public double angleToRotateBy(Shape shapeOne, Shape shapeTwo) {
		// Case for cube
		if (shapeOne.getVertexCount() == 4 && shapeTwo.getVertexCount() == 4) {
			return 90;
			// Case for pyramid
		} else if (shapeOne.getVertexCount() == 4
				&& shapeTwo.getVertexCount() == 3) {
			return 65;
		}

		return 0;
	}

}
