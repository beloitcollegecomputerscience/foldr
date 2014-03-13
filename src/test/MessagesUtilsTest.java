
package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import foldr.main.Messages.Utils;

public class MessagesUtilsTest {

    private static final String PATH = "languages";

    @BeforeClass
    public static void setUpBeforeClass()
        throws Exception {

        Utils.init(PATH);
    }

    @Before
    public void setUp()
        throws Exception {

    }

    @After
    public void tearDown()
        throws Exception {

    }

    @Test
    public void testGetAvailableLocales() {

        // TODO more
        assertTrue(Utils.getAvailableLocales().contains(Locale.US));
        assertFalse(Utils.getAvailableLocales().contains(Locale.CHINESE));
    }

    @Test
    public void testGetDisplayedLanguages() {

        // TODO more
        assertTrue(Utils.getDisplayedLanguages().contains(Locale.US.getDisplayLanguage(Locale.US)));
        assertTrue(Utils.getDisplayedLanguages().contains("français"));
    }
    
    @Test
    public void testGetLocale() {
        assertEquals(Locale.US, Utils.getLocale("English"));
        assertEquals(Locale.FRANCE, Utils.getLocale("français"));
    }

}
