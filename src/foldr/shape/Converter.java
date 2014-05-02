package foldr.shape;

public class Converter {

	
	/**
	 * This method takes to points and converts them to a JReality vector starting from the origin
	 * @param cordinate1 the first coordinate to convert
	 * @param cordinate2 the second coordinate to convert
	 * @return a vector that can be used in the rotation methods
	 */
	public double[] convertPointsToVector(double[] cordinate1, double[] cordinate2) {
		double[] returnVector = new double[3];

		// figure out which point is closer to origin
		double dist1 = Math
				.sqrt(((cordinate1[0] * cordinate1[0])
						+ (cordinate1[0] * cordinate1[0]) + (cordinate1[0] * cordinate1[0])));

		double dist2 = Math
				.sqrt(((cordinate2[0] * cordinate2[0])
						+ (cordinate2[0] * cordinate2[0]) + (cordinate2[0] * cordinate2[0])));
		
		if (dist1<dist2){
			returnVector[0] = cordinate2[0]-cordinate1[0];
			returnVector[1] = cordinate2[1]-cordinate1[1];
			returnVector[2] = cordinate2[2]-cordinate1[2];
		}else{
			returnVector[0] = cordinate1[0]-cordinate2[0];
			returnVector[1] = cordinate1[1]-cordinate2[1];
			returnVector[2] = cordinate1[2]-cordinate2[2];
		}
		
		return returnVector;

	}

}
