/**
 * 
 */

package foldr.main;

/**
 * <p>
 * Implemented by classes responsible of GUI elements willing to display text
 * that needs to be translated in different languages.
 * 
 * @author couretn
 */
public interface Internationalizable {

    /**
     * <p>
     * Set the text of all components contained by the implementing class and
     * redraw them according to the behavior defined by the used GUI Library.
     */
    public void updateTexts();

}
