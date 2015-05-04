package citygmldiff.editscript;

import citygmldiff.util.TestInstances;
import de.igg.citygmldiff.citygmldiff.editscript.EditScriptCalculator;
import de.igg.citygmldiff.citygmldiff.editscript.model.DeleteOperation;
import de.igg.citygmldiff.citygmldiff.editscript.model.EditOperation;
import de.igg.citygmldiff.citygmldiff.editscript.model.UpdateOperation;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * User: richard
 * Date: 13.09.13
 * Time: 10:45
 */
public class EditScriptCalculatorTest extends TestInstances {

    private EditScriptCalculator cut;

    @Before
    public void setUp() throws Exception {
        cut = new EditScriptCalculator();
    }

    @Test
    public void testCalculateEditScript() throws Exception {
        List<EditOperation> updates = cut.calculateUpdates(DISTANCE_TABLE);
        assertNotNull(updates);
        assertEquals(2, updates.size());
        for (EditOperation update : updates) {
            assertTrue(update instanceof UpdateOperation);
            assertNotNull(((UpdateOperation) update).getNodeToUpdate());
            assertNotNull(((UpdateOperation) update).getUpdateValue());
        }
    }

    @Test
    public void testCalculateDeletions() {
        List<AbstractTreeNode> treeList = new ArrayList<>();
        treeList.add(PARENT_NODE_1);
        List<EditOperation> deletions = cut.calculateDeletions(treeList, DISTANCE_TABLE);
        assertEquals(4, deletions.size());
        for (EditOperation deletion : deletions) {
            assertTrue(deletion instanceof DeleteOperation);
            assertNotNull(((DeleteOperation) deletion).getNodeToDelete());
        }
    }
}
