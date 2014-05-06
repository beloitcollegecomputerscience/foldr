
package test;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import foldr.messages.Messages;

public class MessagesTest {

    // TODO more!
    private static final String NAME = "foldr.messages.messages";

    @BeforeClass
    public static void setUpBeforeClass()
        throws Exception {

        Messages.setBundle(Locale.US);
    }

    @Before
    public void setUp()
        throws Exception {

        Messages.setBundle(Locale.US);

    }

    @After
    public void tearDown()
        throws Exception {

    }

    @Test
    public void testLoadBundle() {

        assertEquals(ResourceBundle.getBundle(NAME, Locale.US), Messages.loadBundle(Locale.US));
        Messages.setBundle(Locale.FRANCE);
        assertNotEquals(ResourceBundle.getBundle(NAME, Locale.US), Messages.loadBundle(null));

    }

    @Test
    public void testGetString() {

        assertEquals("File", Messages.getString(Messages.FI));
        Messages.setBundle(Locale.FRANCE);
        assertEquals("Fichier", Messages.getString(Messages.FI));
    }

    @Test
    public void testGetLocale() {

        assertEquals(Locale.US, Messages.getLocale());
    }

}
