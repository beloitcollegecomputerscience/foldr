
package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import foldr.messages.MessagesUtils;

/**
 * <p>
 * Test class for the {@link MessagesUtils} class.
 * 
 * @author couretn
 * @category i18n
 */
public class MessagesUtilsTest {

    private static final String  PATH  = "languages";

    // instance
    private static MessagesUtils utils = null;

    @BeforeClass
    public static void setUpBeforeClass()
        throws Exception {

        utils = MessagesUtils.getInstance();
        utils.init(PATH);
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
        assertTrue(utils.getAvailableLocales().contains(Locale.US));
        assertFalse(utils.getAvailableLocales().contains(Locale.CHINESE));
    }

    @Test
    public void testGetDisplayedLanguages() {

        // TODO more
        assertTrue(utils.getDisplayedLanguages().contains(Locale.US.getDisplayLanguage(Locale.US)));
        assertTrue(utils.getDisplayedLanguages().contains(
            Locale.FRANCE.getDisplayLanguage(Locale.FRANCE)));
    }

    @Test
    public void testGetLocale() {

        assertEquals(Locale.US, utils.getLocale("English"));
        // charset issue with french display.
        assertEquals(
            Locale.FRANCE, utils.getLocale(Locale.FRANCE.getDisplayLanguage(Locale.FRANCE)));
    }

}
