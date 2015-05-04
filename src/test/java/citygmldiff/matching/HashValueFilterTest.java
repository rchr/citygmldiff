package citygmldiff.matching;

import de.igg.citygmldiff.citygmldiff.matching.HashValueFilter;
import de.igg.citygmldiff.citygmldiff.tree.TreeBuilder;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.geometry.BoundingBox;
import org.citygml4j.geometry.Point;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.citygml.core.CityObjectMember;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.primitives.Envelope;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HashValueFilterTest {

    private HashValueFilter cut;
    private ElementNode<CityModel> head1;
    private ElementNode<CityModel> head1a;
    private ElementNode<CityModel> head2;
    private ElementNode<CityModel> head3;
    private ElementNode<CityModel> head4;

    @Before
    public void setUp() {
        cut = new HashValueFilter();
    }

    public void initObjects() {
        Building b1 = new Building();
        b1.setId("1");
        Point lowerCorner1 = new Point(13.3269637686746, 52.5321790563424,
                74.6634593922645);
        Point upperCorner1 = new Point(13.3274412933907, 52.5323175874323,
                90.3453739183024);
        BoundingBox boundingBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(boundingBox1);
        envelope1.setSrsName("urn:ogc:def:crs:EPSG::4326");
        envelope1.setSrsDimension(3);
        BoundingShape boundingShape1 = new BoundingShape(envelope1);
        b1.setBoundedBy(boundingShape1);

        Building b1a = new Building();
        b1a.setId("1");
        Point lowerCorner1a = new Point(13.3269637686747, 52.5321790563424,
                74.6634593922645);
        Point upperCorner1a = new Point(13.3274412933907, 52.5323175874323,
                90.3453739183024);
        BoundingBox boundingBox1a = new BoundingBox(lowerCorner1a, upperCorner1a);
        Envelope envelope1a = new Envelope(boundingBox1a);
        envelope1a.setSrsName("urn:ogc:def:crs:EPSG::4326");
        envelope1a.setSrsDimension(3);
        BoundingShape boundingShape1a = new BoundingShape(envelope1a);
        b1a.setBoundedBy(boundingShape1a);

        Building b2 = new Building();
        b2.setId("2");
        Point lowerCorner2 = new Point(13.3269637686746, 52.5321790563424,
                74.6634593922645);
        Point upperCorner2 = new Point(13.3274412933907, 52.5323175874323,
                90.3453739183024);
        BoundingBox boundingBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        Envelope envelope2 = new Envelope(boundingBox2);
        envelope2.setSrsName("urn:ogc:def:crs:EPSG::4326");
        envelope2.setSrsDimension(3);
        BoundingShape boundingShape2 = new BoundingShape(envelope2);
        b2.setBoundedBy(boundingShape2);

        Building b3 = new Building();
        b3.setId("3");
        Point lowerCorner3 = new Point(13.3269637686747, 52.5321790563425,
                74.6634593922646);
        Point upperCorner3 = new Point(13.3274412933907, 52.5323175874323,
                90.3453739183024);
        BoundingBox boundingBox3 = new BoundingBox(lowerCorner3, upperCorner3);
        Envelope envelope3 = new Envelope(boundingBox3);
        envelope3.setSrsName("urn:ogc:def:crs:EPSG::4326");
        envelope3.setSrsDimension(3);
        BoundingShape boundingShape3 = new BoundingShape(envelope3);
        b3.setBoundedBy(boundingShape3);

        Building b4 = new Building();
        b4.setId("1");
        Point lowerCorner4 = new Point(13.3269637686746, 52.5321790563424,
                74.6634593922645);
        Point upperCorner4 = new Point(13.3274412933907, 52.5323175874323,
                90.3453739183024);
        BoundingBox boundingBox4 = new BoundingBox(lowerCorner4, upperCorner4);
        Envelope envelope4 = new Envelope(boundingBox4);
        envelope4.setSrsName("urn:ogc:def:crs:EPSG::4326");
        envelope4.setSrsDimension(3);
        BoundingShape boundingShape4 = new BoundingShape(envelope4);
        b4.setBoundedBy(boundingShape4);

        CityModel cityModel1 = new CityModel();
        CityObjectMember cityObjectMember1 = new CityObjectMember(b1);
        cityModel1.addCityObjectMember(cityObjectMember1);

        CityModel cityModel1a = new CityModel();
        CityObjectMember cityObjectMember1a = new CityObjectMember(b1a);
        cityModel1a.addCityObjectMember(cityObjectMember1a);

        CityModel cityModel2 = new CityModel();
        CityObjectMember cityObjectMember2 = new CityObjectMember(b2);
        cityModel2.addCityObjectMember(cityObjectMember2);

        CityModel cityModel3 = new CityModel();
        CityObjectMember cityObjectMember3 = new CityObjectMember(b3);
        cityModel3.addCityObjectMember(cityObjectMember3);

        CityModel cityModel4 = new CityModel();
        CityObjectMember cityObjectMember4 = new CityObjectMember(b4);
        cityModel4.addCityObjectMember(cityObjectMember4);

        TreeBuilder treeBuilder = new TreeBuilder();
        head1 = treeBuilder.createTree(cityModel1);
        head1a = treeBuilder.createTree(cityModel1a);
        head2 = treeBuilder.createTree(cityModel2);
        head3 = treeBuilder.createTree(cityModel3);
        head4 = treeBuilder.createTree(cityModel4);
    }

    @Test
    public void testFilterSubtreesWithEqualDigestRecursively1() {
        initObjects();
        cut.filterSubtreesWithEqualDigestRecursively(head1, head2);
        assertNotNull(head1);
        assertNotNull(head2);


        AbstractTreeNode cityObjectMember1Node = head1.getChildren().get(0);
        AbstractTreeNode cityObjectMember2Node = head2.getChildren().get(0);
        AbstractTreeNode building1Node = cityObjectMember1Node.getChildren().get(0);
        AbstractTreeNode building2Node = cityObjectMember2Node.getChildren().get(0);
        Building building1 = ((ElementNode<Building>) building1Node).getValue();
        Building building2 = ((ElementNode<Building>) building2Node).getValue();
        assertEquals("1", building1.getId());
        assertEquals("2", building2.getId());

        assertEquals(2, building1Node.getChildren().size());
        assertEquals(2, building2Node.getChildren().size());
    }

    @Test
    public void testFilterSubtreesWithEqualDigestRecursively2() {
        initObjects();
        cut.filterSubtreesWithEqualDigestRecursively(head1, head3);
        assertNotNull(head1);
        assertNotNull(head3);

        AbstractTreeNode cityObjectMember1Node = head1.getChildren().get(0);
        AbstractTreeNode cityObjectMember2Node = head3.getChildren().get(0);
        AbstractTreeNode building1Node = cityObjectMember1Node.getChildren().get(0);
        AbstractTreeNode building2Node = cityObjectMember2Node.getChildren().get(0);
        Building building1 = ((ElementNode<Building>) building1Node).getValue();
        Building building2 = ((ElementNode<Building>) building2Node).getValue();
        assertEquals("1", building1.getId());
        assertEquals("3", building2.getId());

        assertEquals(2, building1Node.getChildren().size());
        assertEquals(2, building2Node.getChildren().size());
    }

    @Test
    public void testFilterSubtreesWithEqualDigestRecursively3() {
        initObjects();
        cut.filterSubtreesWithEqualDigestRecursively(head1, head1a);
        assertNotNull(head1);
        assertNotNull(head1a);

        AbstractTreeNode cityObjectMember1Node = head1.getChildren().get(0);
        AbstractTreeNode cityObjectMember2Node = head1a.getChildren().get(0);
        AbstractTreeNode building1Node = cityObjectMember1Node.getChildren().get(0);
        AbstractTreeNode building2Node = cityObjectMember2Node.getChildren().get(0);
        Building building1 = ((ElementNode<Building>) building1Node).getValue();
        Building building2 = ((ElementNode<Building>) building2Node).getValue();
        assertEquals("1", building1.getId());
        assertEquals("1", building2.getId());

        assertEquals(1, building1Node.getChildren().size());
        assertEquals(1, building2Node.getChildren().size());
    }

    @Test
    public void testFilterSubtreesWithEqualDigestRecursivelyWithoutChanges() {
        initObjects();
        cut.filterSubtreesWithEqualDigestRecursively(head1, head4);
        assertNotNull(head1);
        assertNotNull(head4);

        assertEquals(0, head1.getChildren().size());
        assertEquals(0, head4.getChildren().size());

    }

}
