package citygmldiff.tree;

import de.igg.citygmldiff.citygmldiff.io.reader.Reader;
import de.igg.citygmldiff.citygmldiff.tree.TreeBuilder;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.citygml.core.CityModel;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class TreeBuilderTest {

    private TreeBuilder cut;
    private Reader reader;

    @Before
    public void setUp() {
        cut = new TreeBuilder();
        reader = new Reader();
    }

    @Ignore
    @Test
    public void testOneBuilding() {
        String fileName = "/building.gml";
        InputStream resourceAsStream = this.getClass().getResourceAsStream(fileName);
        List<CityModel> cityGMLs = reader.getCityModelsFromInputStream(resourceAsStream);
        CityModel cityModel = cityGMLs.get(0);
        ElementNode<CityModel> head = cut.createTree(cityModel);

        assertNotNull(head);
        assertTrue(head.hasChildren());

        List<AbstractTreeNode> children = head.getChildren();
        assertEquals(1, children.size());

        AbstractTreeNode buildingNode = children.get(0);

        List<AbstractTreeNode> buildingsChildren = buildingNode.getChildren();
        assertEquals(52, buildingsChildren.size());
    }

}
