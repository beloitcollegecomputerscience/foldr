package foldr.utility;

/**
 * Simple class to hold vectors.
 * Can also be used to hold an object's rotation.
 * 
 * @author vogtb
 * */
public class Vector3d {

	public double x = 0;
	public double y = 0;
	public double z = 0;
	
	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString() {
		return x + ", " + y + ", " + z;
	}
}
