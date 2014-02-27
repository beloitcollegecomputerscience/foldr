
package foldr.main;

import java.beans.Beans;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

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
     * Return the <code>ResourceBundle</code> associated to the given
     * {@link Locale}, or the current bundle if the argument is
     * <code>null</code>.
     * 
     * @param currentLocale
     *            The <code>Locale</code> whose associated bundle is to be
     *            returned.
     * @return The desired bundle, or the current bundle of the given
     *         <code>Locale</code> is <code>null</code>.
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
     * @param currentLocale
     *            the <code>Locale</code> to set in the message bundle.
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

    /**
     * Utility class. Gives access to the languages supported by the software.
     * For GUI.
     * 
     * @author couretn
     */
    public static final class Utils {

        private static ArrayList<String>      names            = null;
        private static ArrayList<Locale>      locales          = null;

        /**
         * <p>
         * Default path to the file containing the list of languages. Should be
         * passed to the {@link Utils#init(String)} method unless you want to
         * define your own language list (if you want to restrict the available
         * languages for example).
         * <p>
         * Keep in mind that messages files are contained inside the
         * <code>foldr.messages</code> package and thus adding a language
         * without adding the corresponding message file can lead to undefined
         * behaviors.
         */
        public static final String            DEFAULT_PATH     = "languages";

        /**
         * <p>
         * Default list of a locale as written inside in the languages' list
         * file. Only list English (country: USA) as the default language.
         * <p>
         * Used in case the languages can't be retrieved from the file.
         */
        public static final ArrayList<String> DEFAULT_LANGUAGE = new ArrayList<>(
                                                                   Arrays.asList("en_US"));

        /**
         * This class should not be instantiated.
         */
        private Utils() {

        }

        /**
         * <p>
         * Initialize the utility module for the messages class and load the
         * available languages listed in the file located at the given path.
         * <p>
         * The file located at {@link Utils#DEFAULT_PATH} is loaded if :<br>
         * <ul>
         * <li>The argument is <code>null</code>;</li>
         * <li>The file is a directory;</li>
         * <li>The file doesn't exist.</li>
         * </ul>
         * <p>
         * This method should be called before any use of the other methods.
         * 
         * @param path
         *            The path whose file languages must be retrieved from.
         */
        public static void init(String path) {

            try {
                names = new ArrayList<>();
                File file;
                if (path != null) {
                    file = new File(path);
                    if (!file.exists() || file.isDirectory()) {
                        file = new File(DEFAULT_PATH);
                    }
                } else {
                    file = new File(DEFAULT_PATH);
                }

                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String s = sc.nextLine();
                    // regexp [a-zA-Z]{2,8}_([a-zA-Z]{2}|[0-9]{3})
                    // (language_country)
                    // TODO make a smarter regex ???
                    if (!s.startsWith("#")) {
                        names.add(s);
                    }
                }
                sc.close();
            } catch (FileNotFoundException e) {
                names = new ArrayList<>(DEFAULT_LANGUAGE);
            }
        }

        /**
         * <p>
         * Give the {@link Locale} available for the software.
         * 
         * @return a list of <code>Locale</code> available.
         */
        public static ArrayList<Locale> getAvailableLocales() {

            if (locales == null) {
                return buildLocaleList();
            } else {
                return locales;
            }
        }

        /**
         * <p>
         * Give a list of languages supported in their displayed form according
         * to the <code>Locale</code>. This method should be used for GUI
         * display.
         * <p>
         * Examples:<br>
         * <ul>
         * <li>en_US - English</li>
         * <li>fr_FR - français</li>
         * </ul>
         * 
         * @return The list of language names.
         */
        public static ArrayList<String> getDisplayedLanguages() {

            if (locales == null) {
                buildLocaleList();
            }
            ArrayList<String> ret = new ArrayList<>(locales.size());
            for (Locale l : locales) {
                if (!ret.contains(l.getDisplayLanguage(l))) {
                    ret.add(l.getDisplayLanguage(l));
                    ret.trimToSize();
                }
            }
            return ret;
        }

        /**
         * @return
         */
        private static ArrayList<Locale> buildLocaleList() {

            if (names == null) {
                init(DEFAULT_PATH);
            }
            locales = new ArrayList<>(names.size());
            for (String s : names) {
                String[] sp = s.split("_");
                switch (sp.length) {
                    case 1:
                        locales.add(new Locale(sp[0]));
                    break;
                    case 2:
                        locales.add(new Locale(sp[0], sp[1]));
                    break;
                    case 3:
                        locales.add(new Locale(sp[0], sp[1], sp[2]));
                    break;
                    default:
                        // something went wrong, do nothing
                }
            }
            return locales;
        }

    }
}
