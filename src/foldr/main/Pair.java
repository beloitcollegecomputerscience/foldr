/**
 * 
 */
package foldr.main;


/**
 * @author Ataw
 *
 */
public class Pair<L, R> {
    L left;
    R right;

    /**
     * 
     */
    public Pair() {

        // TODO Auto-generated constructor stub
    }
    
    /**
     * 
     * @param left
     * @param right
     */
    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }
    
    public L getLeft() {
        return this.left;
    }
    
    public R getRight() {
        return this.right;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((left == null) ? 0 : left.hashCode());
        result = prime * result + ((right == null) ? 0 : right.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Pair))
            return false;
        Pair other = (Pair) obj;
        if (left == null) {
            if (other.left != null)
                return false;
        } else if (!left.equals(other.left))
            return false;
        if (right == null) {
            if (other.right != null)
                return false;
        } else if (!right.equals(other.right))
            return false;
        return true;
    }
    
    

}
