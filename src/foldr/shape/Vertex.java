package foldr.shape;

/**
 * <p>
 * This is a class to make it easier to work with Vertices.
 * </p>
 * @author vogtb and couretn
 * 
 */
public class Vertex {

	public Double x;
	public Double y;
	public Double z;
	
	public Vertex(Double x, Double y, Double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Double getX() {
		return x;
	}
	
	public Double getY() {
		return y;
	}
	
	public Double getZ() {
		return z;
	}
	
	public Double[] toArray() {
		return new Double[] { x, y, z };
	}
	
}
