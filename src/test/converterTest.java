package test;

import org.junit.Test;

import de.jreality.scene.SceneGraphComponent;
import de.jreality.util.SceneGraphUtility;
import foldr.shape.Converter;
import foldr.shape.Shape;
/**
 * Tests for testing conversion methods
 * 
 * @author Ellery Addington-White
 */
public class converterTest {
	Converter testConvert = new Converter();
	
	static SceneGraphComponent topScene = SceneGraphUtility
			.createFullSceneGraphComponent("topScene");

	Shape shapeOne = new Shape(4, topScene);
	Shape shapeTwo = new Shape(4, topScene);

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
		
		//Test angle to rotate by for two squares
		double amountToRotate = testConvert.angleToRotateBy(shapeOne, shapeOne);
		assert(amountToRotate == 90);
	}

}
