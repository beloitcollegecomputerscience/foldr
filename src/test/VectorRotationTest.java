package test;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jreality.scene.SceneGraphComponent;
import de.jreality.util.SceneGraphUtility;
import foldr.shape.Converter;
import foldr.shape.Shape;

public class VectorRotationTest {

	@Test
	public void test() {
		
		Converter convert = new Converter();
		SceneGraphComponent scene = SceneGraphUtility
				.createFullSceneGraphComponent("scene");
		Shape testShape1 = new Shape(3, scene);
		Shape testShape2 = new Shape(4, scene);
		testShape1.translate(2, 2, 0);
		testShape1.rotate(Math.toRadians(20), 'z');
		// testShape1.rotateShape(Math.toRadians(20), 'z');

		double[] vector1 = convert.convertPointsToVectorOnOrigin(
				testShape1.getCurrentVertexCoordinates(2),
				testShape1.getCurrentVertexCoordinates(1));
		double[] vector2 = convert.convertPointsToVectorOnOrigin(
				testShape2.getCurrentVertexCoordinates(1),
				testShape2.getCurrentVertexCoordinates(2));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testShape2.rotateOnVector(vector1, vector2);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO BUG: will always start from original coordinates of shape from
		// when it was added to the scene need to have a matrix or something
		// holding the shapes vertices and keeping track of rotation.
		// connectTwoShapes(testShape2, 0, testShape1, 1);
	}

}
