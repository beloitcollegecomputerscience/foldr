/**
 * 
 */

package foldr.main;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * @author Ataw
 */
public final class LgUtility {

    private static File     root;
    private static File[]   files;
    private static String[] locales;
    private static HashMap<String, Locale> localeMap;

    /**
     * 
     */
    private LgUtility() {
    }
    
    public static void init(String path) {
        doList(path);
        parseList();
        buildLocaleMap();
    }
    
    /**
     * 
     * @return
     */
    private static void doList(String path) {
        if(path.equals("") || path == null) {
            LgUtility.root = new File(System.getProperty("user.dir"));
        } else {
            LgUtility.root = new File(path);
        }

        files = root.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {

                return name.startsWith("messages") && name.endsWith("properties") &&
                    !name.equalsIgnoreCase("messages.properties");
            }
        });
    }
    
    /**
     * 
     * @return
     */
    private static void parseList() {

        locales = new String[files.length];
        ArrayList<Pair<String, String>> arr = new ArrayList<>(files.length);
        for (int i = 0; i < files.length; i++) {
            locales[i] = files[i].getName();
            locales[i] = locales[i].substring(9);
            locales[i] = locales[i].substring(0, locales[i].indexOf("."));
            String[] split = locales[i].split("_");
            arr.add(new Pair<String, String>(split[0], split[1]));
        }
    }
    
    private static void buildLocaleMap() {
        localeMap = new HashMap<>(locales.length);
        String[] s = getAvalaibleLanguages();
        ArrayList<Locale> a = getAvailableLocale();
        for(int i = 0; i < a.size(); i++) {
            localeMap.put(s[i], a.get(i));
        }
    }
    
    public static ArrayList<Locale> getAvailableLocale() {
        ArrayList<Locale> map = new ArrayList<>(locales.length);
        for(int i = 0; i < locales.length;i++) {
            String[] s = locales[i].split("_");
            Pair<String, String> p = new Pair<>(s[0], s[1]);
            map.add(new Locale(p.getLeft(), p.getRight()));
        }
        trimLocale(map);
        return map; 
    }
    
    /**
     * 
     * @return
     */
    public static String[] getAvalaibleLanguages() {
        String[] ret = new String[locales.length];
        ArrayList<Locale> l = getAvailableLocale();
        for(int i = 0; i < ret.length;i++) {
            ret[i] = l.get(i).getDisplayLanguage(l.get(i));
        }
        
        return ret;
    }
    
    /**
     * 
     * @param a
     * @return
     */
    private static ArrayList<Locale> trimLocale(ArrayList<Locale> a) {
        for(int i = 0; i < a.size()-1; i++) {
            Locale l = a.get(i);
            for(int j = i+1; j < a.size();j++) {
                // use ISO3 language to ensure correctness
                if(l.getISO3Language().equals(a.get(j).getISO3Language())) {
                    a.remove(j);
                }
            }
        }
        return a;
    }
    
    public static void main(String[] args) {
        LgUtility.init("src/foldr/messages");
        String[] s = LgUtility.getAvalaibleLanguages();
        for(int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }


}
