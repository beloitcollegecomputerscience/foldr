package foldr.utility;

import de.jreality.math.MatrixBuilder;
import de.jreality.scene.SceneGraphComponent;

/**
 * An interface to manipulate the camera.
 * 
 * @author vogtb
 * */
public class CustomCamera {

	/**
	 * Hold's cameras location and rotation.
	 **/
	public Vector3d location = new Vector3d(0, 0, 0);
	public Vector3d rotation = new Vector3d(0, 0, 0);
		
	/**
	 * If the camera is facing the opposite direction
	 * (Front vs. Back, Left vs. Right.)
	 **/
	public boolean flipped = false;
	/**
	 * If the camera is locked on the origin of the scene.
	 **/
	boolean originLock = false;
	
	/**
	 * Constructor. If we want the camera to lock on the origin of the scene set to true.
	 **/
	public CustomCamera(boolean originLock) {
		this.originLock = originLock;
	}
	
	/**
	 * Sets the location of the camera
	 **/
	public void setLocation(double x, double y, double z) {
		location.set(x, y, z);
	}
	
	/**
	 * Sets the x location of the camera
	 **/
	public void setLocationX(double x) {
		location.x = x;
	}
	
	/**
	 * Sets the y location of the camera
	 **/
	public void setLocationY(double y) {
		location.y = y;
	}
	
	/**
	 * Sets the z location of the camera
	 **/
	public void setLocationZ(double z) {
		location.z = z;
	}
	
	/**
	 * Sets the rotation of the camera
	 **/
	public void setRotation(double x, double y, double z) {
		rotation.set(x, y, z);
	}
	
	/**
	 * Sets the x rotation of the camera
	 **/
	public void setRotationX(double x) {
		rotation.x = x;
	}
	
	/**
	 * Sets the y rotation of the camera
	 **/
	public void setRotationY(double y) {
		rotation.y = y;
	}
	
	/**
	 * Sets the z rotation of the camera
	 **/
	public void setRotationZ(double z) {
		rotation.z = z;
	}
	
	/**
	 * Flips the camera location, and rotation on axis.
	 * Intended for cameras that are locked on axis rather than origin.
	 **/
	public void flipOnAxis(String axisToFlipAlong) {
		if (axisToFlipAlong == "x") {
			this.location.y = this.location.y * -1;
			this.rotation.x = this.rotation.x * -1;
		} else if (axisToFlipAlong == "y") {
			this.location.x = this.location.x * -1;
			this.rotation.y = this.rotation.y * -1;
		} else if (axisToFlipAlong == "z") {
			this.location.z = this.location.z * -1;
			//Camera locked on z axis has no initial rotation
			if (this.rotation.y == 0) {
				this.rotation.y = 180;
			} else {
				this.rotation.y = 0;
			}
		}
		flipped = !flipped;
	}
	
	/**
	 * Sets the rotation of the camera
	 **/
	public void applyChangesTo(SceneGraphComponent cameraContainer) {
		if (originLock) {
			MatrixBuilder.euclidean()
			.translate(-location.x, -location.y, -location.z)
			.rotateX(Math.toRadians(-rotation.y))
			.rotateY(Math.toRadians(-rotation.x))
			.conjugateBy(
				MatrixBuilder.euclidean()
				.translate(location.x, location.y, location.z - 4.5) //4.5 is the x distance to the origin
				.getMatrix().getArray())
			.assignTo(cameraContainer);
		} else {
			MatrixBuilder.euclidean()
			.translate(location.x, location.y, location.z)
			.rotateX(Math.toRadians(rotation.x))
			.rotateY(Math.toRadians(rotation.y))
			.rotateZ(Math.toRadians(rotation.z))
			.assignTo(cameraContainer);
		}

	}
	
}
