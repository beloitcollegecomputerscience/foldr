package test;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import foldr.main.LgUtils;


public class LgUtilsTest {
    
    private static final String PATH = "languages";

    @BeforeClass
    public static void setUpBeforeClass()
        throws Exception {
        LgUtils.init(PATH);
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
        //TODO more
        assertTrue(LgUtils.getAvailableLocales().contains(Locale.US));
        assertFalse(LgUtils.getAvailableLocales().contains(Locale.CHINESE));
    }

    @Test
    public void testGetDisplayedLanguages() {
        // TODO more
        assertTrue(LgUtils.getDisplayedLanguages().contains(Locale.US.getDisplayLanguage(Locale.US)));
    }

}
