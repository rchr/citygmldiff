package citygmldiff.matching.model;

import citygmldiff.util.TestInstances;
import de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable;
import de.igg.citygmldiff.citygmldiff.matching.model.Matching;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * User: richard
 * Date: 13.09.13
 * Time: 13:45
 */
public class DistanceTableTest extends TestInstances {

    @Test
    public void testGet() throws Exception {
        Integer dist = DISTANCE_TABLE.get(PARENT_NODE_1.hashCode() + ":" + PARENT_NODE_2.hashCode());
        assertEquals(new Integer(2), dist);

        dist = DISTANCE_TABLE.get(TEXT_NODE_1.hashCode() + ":" + TEXT_NODE_2.hashCode());
        assertEquals(new Integer(0), dist);

        dist = DISTANCE_TABLE.get(TEXT_NODE_3.hashCode() + ":" + TEXT_NODE_4.hashCode());
        assertEquals(new Integer(1), dist);

        dist = DISTANCE_TABLE.get(PARENT_NODE_1.hashCode() + ":" + CHILD_NODE_1.hashCode());
        assertNull(dist);
    }

    @Test
    public void testContainsMatching() throws Exception {
        assertTrue(DISTANCE_TABLE.containsMatching(PARENT_NODE_1.hashCode() + ":" + PARENT_NODE_2.hashCode()));
        assertTrue(DISTANCE_TABLE.containsMatching(TEXT_NODE_1.hashCode() + ":" + TEXT_NODE_2.hashCode()));
        assertTrue(DISTANCE_TABLE.containsMatching(ATTRIBUTE_NODE_1.hashCode() + ":" + ATTRIBUTE_NODE_2.hashCode()));
        assertFalse(DISTANCE_TABLE.containsMatching(TEXT_NODE_1.hashCode() + ":" + TEXT_NODE_6.hashCode()));
        assertFalse(DISTANCE_TABLE.containsMatching(PARENT_NODE_1.hashCode() + ":" + CHILD_NODE_1.hashCode()));
    }

    @Test
    public void testContainsNode() throws Exception {
        assertTrue(DISTANCE_TABLE.containsNode(PARENT_NODE_1));
        assertFalse(DISTANCE_TABLE.containsNode(CHILD_NODE_1));
        assertTrue(DISTANCE_TABLE.containsNode(TEXT_NODE_1));
        assertTrue(DISTANCE_TABLE.containsNode(ATTRIBUTE_NODE_1));
        assertFalse(DISTANCE_TABLE.containsNode(TEXT_NODE_6));
    }

    @Test
    public void testGetDistanceForSubtrees1() {
        DistanceTable cut = DISTANCE_TABLE;
        Integer dist = cut.getDistanceForSubtrees(PARENT_NODE_1, PARENT_NODE_2);
        assertEquals(new Integer(2), dist);
    }

    @Test
    public void testGetDistanceForSubtrees2() {
        DistanceTable cut = DISTANCE_TABLE;
        Integer dist = cut.getDistanceForSubtrees(PARENT_NODE_1, CHILD_NODE_1);
        assertEquals(new Integer(0), dist);
    }

    @Test
    public void testGetLeafNodeMatchings() {
        DistanceTable cut = DISTANCE_TABLE;
        Set<Matching> leafNodeMatchings = cut.getLeafNodeMatchings();
        assertNotNull(leafNodeMatchings);

        assertFalse(leafNodeMatchings.contains(PARENT_MATCHING));
        assertTrue(leafNodeMatchings.contains(MATCHING_1));
        assertTrue(leafNodeMatchings.contains(MATCHING_2));
        assertTrue(leafNodeMatchings.contains(ATTRIBUTE_MATCHING_1));
        assertEquals(3, leafNodeMatchings.size());
    }
}
