/**
 * 
 */

package foldr.main;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * @author Ataw
 */
public class listFiles {

    private File     root;
    private File[]   files;
    private String[] locales;

    /**
     * 
     */
    public listFiles() {

        this(System.getProperty("user.dir"));
    }
    
    /**
     * 
     * @param path
     */
    public listFiles(String path) {

        root = new File(path);
        files = null;
        locales = null;
    }
    
    /**
     * 
     * @return
     */
    public File[] doList() {

        files = root.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {

                return name.startsWith("messages") && name.endsWith("properties") &&
                    !name.equalsIgnoreCase("messages.properties");
            }
        });
        return files;
    }
    
    /**
     * 
     * @return
     */
    public String[] parseList() {

        locales = new String[files.length];
        ArrayList<Pair<String, String>> arr = new ArrayList<>(files.length);
        for (int i = 0; i < files.length; i++) {
            locales[i] = files[i].getName();
            locales[i] = locales[i].substring(9);
            locales[i] = locales[i].substring(0, locales[i].indexOf("."));
            String[] split = locales[i].split("_");
            arr.add(new Pair<String, String>(split[0], split[1]));
        }
        
        return locales;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        listFiles lf = new listFiles("src/foldr/messages");
        File[] fs = lf.doList();
        for(int i = 0; i < fs.length; i++) {
            System.out.println(i+" "+fs[i].getName());
        }
        System.out.println("");
        String[] s = lf.parseList();
        for(int i = 0; i < s.length; i++) {
            System.out.println(i+" "+s[i]);
        }
        
    }

}
