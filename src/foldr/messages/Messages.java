
package foldr.messages;

import java.beans.Beans;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * <p>
 * auto-generated class, manages message bundles.
 * 
 * @author Eclipse
 * @author couretn
 * @category i18n
 */
public class Messages {

    /**
     * <p>
     * Key for the File menu.
     */
    public static final String FI = "menu.file";
    
    /**
     * <p>
     * Key for the Edit menu.
     */
    public static final String ED = "menu.edit";
    
    /**
     * <p>
     * Key for the Folding/Shape menu.
     */
    public static final String FO = "menu.fold";
    
    /**
     * <p>
     * Key for the Window menu.
     */
    public static final String WI = "menu.wind";
    
    /**
     * <p>
     * Key for the Help menu.
     */
    public static final String HE = "menu.help";

    // //////////////////////////////////////////////////////////////////////////
    //
    // Constructor
    //
    // //////////////////////////////////////////////////////////////////////////
    /**
     * Should not be instantiated.
     */
    private Messages() {

        // do not instantiate
    }

    // //////////////////////////////////////////////////////////////////////////
    //
    // Bundle access
    //
    // //////////////////////////////////////////////////////////////////////////
    private static final String   BUNDLE_NAME     = "foldr.messages.messages";      //$NON-NLS-1$
    private static ResourceBundle RESOURCE_BUNDLE = loadBundle(Locale.getDefault());

    private static ResourceBundle loadBundle() {

        return ResourceBundle.getBundle(BUNDLE_NAME);
    }

    /**
     * Return the <tt>ResourceBundle</tt> associated to the given
     * {@link Locale}, or the current bundle if the argument is
     * <tt>null</tt>.
     * 
     * @param currentLocale
     *            The <tt>Locale</tt> whose associated bundle is to be
     *            returned.
     * @return The desired bundle, or the current bundle of the given
     *         <tt>Locale</tt> is <tt>null</tt>.
     */
    public static ResourceBundle loadBundle(Locale currentLocale) {

        if (currentLocale != null) {
            return ResourceBundle.getBundle(BUNDLE_NAME, currentLocale);
        } else {
            return loadBundle();
        }
    }

    /**
     * <p>
     * Set the message bundle with the given <tt>Locale</tt> (if not
     * <tt>null</tt>).
     * 
     * @param currentLocale
     *            the <tt>Locale</tt> to set in the message bundle.
     */
    public static void setBundle(Locale currentLocale) {

        if (currentLocale != null) {
            RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, currentLocale);
        }
    }

    // //////////////////////////////////////////////////////////////////////////
    //
    // Strings access
    //
    // //////////////////////////////////////////////////////////////////////////
    /**
     * <p>
     * Return the value (a <tt>String</tt>) to which the specified key is
     * mapped, or !key! if no such key exists in the message file.
     * 
     * @param key
     *            The key whose associated value is to be returned.
     * @return The value to which the key is mapped, or !key!
     */
    public static String getString(String key) {

        try {
            ResourceBundle bundle;
            if (Beans.isDesignTime())
                bundle = loadBundle();
            else
                bundle = RESOURCE_BUNDLE;

            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return "!" + key + "!";
        }
    }

    /**
     * @return
     */
    public static Locale getLocale() {

        ResourceBundle bundle;
        if (Beans.isDesignTime())
            bundle = loadBundle();
        else
            bundle = RESOURCE_BUNDLE;

        return bundle.getLocale();
    }
}
