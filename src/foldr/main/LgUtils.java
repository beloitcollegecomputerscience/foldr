/**
 * 
 */

package foldr.main;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * @author couretn
 */
public final class LgUtils {

    private static String[]          localesNames = null;
    private static ArrayList<Locale> localesList  = null;

    /**
     * @param path
     */
    public static void init(String path) {

        doList(path);
        buildLocaleList();
    }

    /**
     * @return
     */
    private static void doList(String path) {

        // Find the files matching the desired pattern
        File root;
        if (path.equals("") || path == null) {
            root = new File(System.getProperty("user.dir"));
        } else {
            root = new File(path);
        }
        File[] files = root.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {

                return name.startsWith("messages") && name.endsWith("properties") &&
                    !name.equalsIgnoreCase("messages.properties");
            }
        });

        // extract locale from filenames. We only care about languages
        // name so far.
        localesNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            localesNames[i] = files[i].getName();
            localesNames[i] = localesNames[i].substring(9);
            localesNames[i] = localesNames[i].substring(0, localesNames[i].indexOf("."));
        }
    }

    private static ArrayList<Locale> buildLocaleList() {

        localesList = new ArrayList<>(localesNames.length);
        for (String s : localesNames) {
            String[] split = s.split("_");
            Locale l = new Locale(split[0], split[1]);
            if (!localesList.contains(l)) {
                localesList.add(l);
            }
        }
        return localesList;
    }

    public static ArrayList<Locale> getAvailableLocale() {

        if (localesList != null) {
            return localesList;
        } else {
            return buildLocaleList();
        }
    }

    /**
     * @return
     */
    public static String[] getAvalaibleLanguages() {

        String[] ret = new String[localesNames.length];
        ArrayList<Locale> l = getAvailableLocale();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = l.get(i).getDisplayLanguage(l.get(i));
        }
        return ret;
    }
}
