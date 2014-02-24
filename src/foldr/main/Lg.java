
package foldr.main;

import java.beans.Beans;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * auto-generated class, manages Language bundles.
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
     * @param currentLocale
     * @return
     */
    public static ResourceBundle loadBundle(Locale currentLocale) {

        if (currentLocale != null) {
            return ResourceBundle.getBundle(BUNDLE_NAME, currentLocale);
        } else {
            return loadBundle();
        }
    }

    /**
     * @param currentLocale
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
     * @param key
     * @return
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
