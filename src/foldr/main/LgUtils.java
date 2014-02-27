/**
 * 
 */

package foldr.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Ataw
 */
public final class LgUtils {

    private static ArrayList<String>      names            = null;
    private static ArrayList<Locale>      locales          = null;

    /**
     * <p>
     * Default path to the file containing the list of languages. Should be
     * passed to the {@link LgUtils#init(String)} method unless you want to
     * define your own language list (if you want to restrict the available
     * languages for example).
     * <p>
     * Keep in mind that messages files are contained inside the
     * <code>foldr.messages</code> package and thus adding a language without
     * adding the corresponding message file can lead to undefined behaviors.
     */
    public static final String            DEFAULT_PATH     = "languages";

    /**
     * <p>
     * Default list of a locale as written inside in the languages' list file.
     * Only list English (country: USA) as the default language.
     * <p>
     * Used in case
     */
    public static final ArrayList<String> DEFAULT_LANGUAGE =
                                                               new ArrayList<>(
                                                                   Arrays.asList("en_US"));

    /**
     * This class should not be instantiated.
     */
    private LgUtils() {

    }

    /**
     * <p>
     * Initialize the utility module for the messages class and load the
     * available languages listed in the given file.
     * <p>
     * This method must be called before any use of the other methods.
     * 
     * @param path
     * @throws FileNotFoundException
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
                // TODO make a smarter regex
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
     * Give a list of {@link Locale} available something something je sais pas
     * comment le dire.
     * 
     * @return a list of <code>Locale</code> defined
     */
    public static ArrayList<Locale> getAvailableLocales() {

        if (locales == null) {
            return buildLocaleList();
        } else {
            return locales;
        }
    }

    /**
     * @return
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
     * @throws IllegalStateException
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
