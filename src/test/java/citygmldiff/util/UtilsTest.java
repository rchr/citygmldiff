package citygmldiff.util;

import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * User: richard
 * Date: 13.09.13
 * Time: 12:06
 */
public class UtilsTest {

    @Test
    public void testIsSignatureEqual() {
        assertTrue(Utils.isSignatureEqual(TestInstances.TEXT_NODE_1, TestInstances.TEXT_NODE_2));
    }

    @Test
    public void testIsSignatureEqualNotEqual() {
        assertFalse(Utils.isSignatureEqual(TestInstances.TEXT_NODE_1, TestInstances.PARENT_NODE_2));
    }

}
