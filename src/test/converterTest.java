package test;

import org.junit.Test;

import foldr.shape.Converter;
/**
 * Tests for testing conversion methods
 */
public class converterTest {
	Converter testConvert = new Converter();

	/**
	 * Tests for testing conversion methods
	 */
	@Test
	public void test() {
		// Test converting points to vector on origin
		double[] point1 = { 4, 3, 0 };
		double[] point2 = { 6, 6, 0 };

		double[] returnVector1 = testConvert.convertPointsToVectorOnOrigin(point1,
				point2);

		assert (returnVector1[0] == 2);
		assert (returnVector1[1] == 3);
		assert (returnVector1[2] == 0);

		// Test cross product method

		// create vectors
		double[] vector1 = { 2, 3, 5 };
		double[] vector2 = { 5, 2, 6 };

		// Run cross product method
		double[] results = testConvert.crossProduct(vector1, vector2);

		assert (results[0] == 8);
		assert (results[1] == 13);
		assert (results[2] == -11);
	}

}
