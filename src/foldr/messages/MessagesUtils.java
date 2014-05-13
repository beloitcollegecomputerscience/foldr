/**
 * 
 */

package foldr.messages;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Utility class. Gives access to the languages supported by the software. For
 * GUI.
 * 
 * @author couretn
 * @category i18n
 */
public final class MessagesUtils {

    private ArrayList<String> names    = null;
    private ArrayList<Locale>  locales = null;

    /**
     * <p>
     * Default path to the file containing the list of languages. Should be
     * passed to the {@link Utils#init(String)} method unless you want to define
     * your own language list (if you want to restrict the available languages
     * for example).
     * <p>
     * Keep in mind that messages files are contained inside the
     * <code>foldr.messages</code> package and thus adding a language without
     * adding the corresponding message file can lead to undefined behaviors.
     */
    public static final String DEFAULT_PATH  = "languages";

    /**
     * <p>
     * Default list of a locale as written inside in the languages' list file.
     * Only list English (country: USA) as the default language.
     * <p>
     * Used in case the languages can't be retrieved from the file.
     */
    public static final ArrayList<String> DEFAULT_LANGUAGE = 
    		new ArrayList<String>( Arrays.asList("en_US"));
    
    private static MessagesUtils INSTANCE = null;

    /**
     * Singleton.
     * 
     * @return The unique instance of this class.
     */
    public static MessagesUtils getInstance() {

        if (INSTANCE == null) {
            synchronized (MessagesUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MessagesUtils();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Singleton.
     */
    private MessagesUtils() {

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
    public void init(String path) {

        try {
            names = new ArrayList<String>();
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
                // names should be of the form language_country
                if (!s.startsWith("#")) {
                    names.add(s);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            names = new ArrayList<String>(DEFAULT_LANGUAGE);
        }
    }

    /**
     * <p>
     * Give the {@link Locale} available for the software.
     * 
     * @return a list of <code>Locale</code> available.
     */
    public ArrayList<Locale> getAvailableLocales() {

        if (locales == null) {
            return buildLocaleList();
        } else {
            return locales;
        }
    }

    /**
     * <p>
     * Give a list of languages supported in their displayed form according to
     * the <code>Locale</code>. This method should be used for GUI display.
     * <p>
     * Examples:<br>
     * <ul>
     * <li>en_US - English</li>
     * <li>fr_FR - français</li>
     * </ul>
     * 
     * @return The list of language names.
     */
    public ArrayList<String> getDisplayedLanguages() {

        if (locales == null) {
            buildLocaleList();
        }
        ArrayList<String> ret = new ArrayList<String>(locales.size());
        for (Locale l : locales) {
            if (!ret.contains(l.getDisplayLanguage(l))) {
                ret.add(l.getDisplayLanguage(l));
            }
        }
        return ret;
    }

    /**
     * Get the <code>Locale</code> associated to the given displayed language.
     * If no <code>Locale</code> is found, the current <code>Locale</code>
     * returned by {@link Messages#getLocale()} is used.
     * 
     * @param displayed
     *            The displayed language whose <code>Locale</code> must be
     *            found.
     * @return The <code>Locale</code> returning the given Displayed language,
     *         or the <code>Locale</code> currently used by the
     *         <code>MessageBundle</code>.
     */
    public Locale getLocale(String displayed) {

        ArrayList<String> languages = getDisplayedLanguages();
        if (languages.contains(displayed)) { 
        	return locales.get(languages.indexOf(displayed));
        }
        return Messages.getLocale();
    }

    /**
     * <p>
     * Create the <tt>Locale</tt> list based on the list of names contained in the 
     * language list files and return all available <tt>Locale</tt>.
     * 
     * @return a <tt>List</tt> of available <tt>locale</tt>.
     */
    private ArrayList<Locale> buildLocaleList() {

        if (names == null) {
            init(DEFAULT_PATH);
        }
        locales = new ArrayList<Locale>(names.size());
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
