package test;

import static org.junit.Assert.*;

import org.junit.Test;

import foldr.shape.Converter;

public class converterTest {

	@Test
	public void test() {
		Converter foo = new Converter();
		double[] point1 = {4,3,0};
		double[] point2 = {6,6,0};
		
		double[] returnVector = foo.convertPointsToVector(point1, point2);
		
		assert(returnVector[0] == 2);
		assert(returnVector[1] == 3);
		assert(returnVector[2] == 0);
	}

}
