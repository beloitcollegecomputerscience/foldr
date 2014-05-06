package foldr.main;

/**
 * 
 * @author Nick, Hunter, and Tyler
 *
 */
public class ActionManager {

	public void doSelect() {
		System.out.println("select");

	}

	public void doMove() {
		System.out.println("move");
	}

	public void doFill() {
		System.out.println("fill");
	}

	public void doJoinEdge() {
		System.out.println("join edge");
	}

	public void doErase() {
		System.out.println("erase");
	}

	public void doPoint() {
		System.out.println("point");
	}

	public void doLine() {
		System.out.println("line");
	}

	public void doShape() {
		System.out.println("shape");
	}

	public void doPanCamera() {
		System.out.println("pan camera");
	}

	public void doFlymode() {
		System.out.println("fly mode");
	}

	public void doRotateCamera() {
		System.out.println("rotate camera");
	}

	public void doMoveCamera() {
		System.out.println("move camera");
	}

	
	public void doSelectNumSides(String num, SceneGraphComponent topScene) {
		// parse the string to an integer
		int numSides = Integer.parseInt(num);
		
		//check if number is within a certain legal range
		//TODO catch errors for input that aren't integers
		if (numSides < 3 || numSides > 10) {
			System.out.println("Please submit a number of sides between 3 and 10.");
			System.out.println("Defaulting to 3 sides.");
			// if not, default to a triangle
			numSides = 3;
		}
		//put the shape onto the screen
		Shape newShape = new Shape(numSides, topScene);
		
	}

}
