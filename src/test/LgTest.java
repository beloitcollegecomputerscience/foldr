
package test;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import foldr.main.Lg;

public class LgTest {
    // TODO more!
    private static final String NAME = "foldr.messages.messages";

    @BeforeClass
    public static void setUpBeforeClass()
        throws Exception {

        Lg.setBundle(Locale.US);
    }

    @Before
    public void setUp()
        throws Exception {

        Lg.setBundle(Locale.US);

    }

    @After
    public void tearDown()
        throws Exception {

    }

    @Test
    public void testLoadBundle() {

        assertEquals(ResourceBundle.getBundle(NAME, Locale.US), Lg.loadBundle(Locale.US));
        Lg.setBundle(Locale.FRANCE);
        assertNotEquals(ResourceBundle.getBundle(NAME, Locale.US), Lg.loadBundle(null));

    }

    @Test
    public void testGetString() {

        assertEquals("File", Lg.getString(Lg.FI));
        Lg.setBundle(Locale.FRANCE);
        assertEquals("Fichier", Lg.getString(Lg.FI));
    }

    @Test
    public void testGetLocale() {

        assertEquals(Locale.US, Lg.getLocale());
    }

}
