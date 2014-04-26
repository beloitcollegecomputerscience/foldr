package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import foldr.utility.CustomCamera;

public class CustomCameraTest {

	public CustomCamera testCamera =new CustomCamera(false);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetLocation() {
		testCamera.setLocation(3.5, 5.6, 0);
		assertEquals(3.5, testCamera.location.x);
		assertEquals(5.6, testCamera.location.y);
		assertEquals(0, testCamera.location.z);
	}
	
	@Test
	public void testSetLocationIndividually() {
		testCamera.setLocationX(9);
		testCamera.setLocationY(12);
		testCamera.setLocationZ(4.4);
		assertEquals(9, testCamera.location.x);
		assertEquals(12, testCamera.location.y);
		assertEquals(4.4, testCamera.location.z);
	}
	
	@Test
	public void testSetRotation() {
		testCamera.setRotation(3.5, 5.6, 0);
		assertEquals(3.5, testCamera.rotation.x);
		assertEquals(5.6, testCamera.rotation.y);
		assertEquals(0, testCamera.rotation.z);
	}
	
	@Test
	public void testSetRotationIndividually() {
		testCamera.setRotationX(9);
		testCamera.setRotationY(12);
		testCamera.setRotationZ(4.4);
		assertEquals(9, testCamera.rotation.x);
		assertEquals(12, testCamera.rotation.y);
		assertEquals(4.4, testCamera.rotation.z);
	}
	
	@Test
	public void testflipOnAxis() {
		testCamera.flipOnAxis("x");
		assertEquals(-12, testCamera.location.y);
		assertEquals(-4.4, testCamera.rotation.y);
		testCamera.setLocation(-10.1,  20.9,  33.3);
		testCamera.setRotation(55.9, 90, -90);
		testCamera.flipOnAxis("y");
		assertEquals(10.1, testCamera.location.x);
		assertEquals(-90, testCamera.rotation.y);
		testCamera.setRotationY(0);
		testCamera.flipOnAxis("z");
		assertEquals(180, testCamera.rotation.y);
		assertEquals(-33.3, testCamera.location.z);
	}

}
