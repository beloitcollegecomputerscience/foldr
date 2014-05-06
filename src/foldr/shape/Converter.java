package foldr.shape;

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
		double dist1 = Math
				.sqrt(((cordinate1[0] * cordinate1[0])
						+ (cordinate1[0] * cordinate1[0]) + (cordinate1[0] * cordinate1[0])));

		double dist2 = Math
				.sqrt(((cordinate2[0] * cordinate2[0])
						+ (cordinate2[0] * cordinate2[0]) + (cordinate2[0] * cordinate2[0])));

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
	 * This method will take two vectors and calculate their cross-product
	 * @param vector1 first vector
	 * @param vector2 second vector
	 * @return the calculated vector
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
	


}
