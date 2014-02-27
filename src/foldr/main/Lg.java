
package foldr.main;

import java.beans.Beans;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * auto-generated class, manages message bundles.
 * 
 * @author Eclipse
 * @author couretn
 */
public class Lg {

    public static final String FI = "menu.file";
    public static final String ED = "menu.edit";
    public static final String FO = "menu.fold";
    public static final String WI = "menu.wind";
    public static final String HE = "menu.help";

    // //////////////////////////////////////////////////////////////////////////
    //
    // Constructor
    //
    // //////////////////////////////////////////////////////////////////////////
    /**
     * Should not be instantiated.
     */
    private Lg() {

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
     * Return the <code>ResourceBundle</code> associated to the given {@link Locale}, or the current bundle if the argument is <code>null</code>.
     * @param currentLocale The <code>Locale</code> whose associated bundle is to be returned.
     * @return The desired bundle, or the current bundle of the given <code>Locale</code> is <code>null</code>.
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
     * Set the message bundle with the given <code>Locale</code> (if not
     * <code>null</code>).
     * 
     * @param currentLocale the <code>Locale</code> to set in the message bundle.
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
     * Return the value (a <code>String</code>) to which the specified key is
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
