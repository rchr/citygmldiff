package de.igg.citygmldiff.citygmldiff.matching;

import citygmldiff.util.TestInstances;
import de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable;
import de.igg.citygmldiff.citygmldiff.matching.model.Matching;
import de.igg.citygmldiff.citygmldiff.tree.TreeBuilder;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import junit.framework.TestCase;
import org.citygml4j.geometry.BoundingBox;
import org.citygml4j.geometry.Point;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.citygml.core.CityObjectMember;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.primitives.Envelope;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by richard on 01.04.14.
 */
public class FeatureMatcherTest extends TestCase {

    private FeatureMatcher cut;

    private ElementNode<CityModel> head1;
    private ElementNode<CityModel> head3;

    private void initObjects() {
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

        Building b3 = new Building();
        b3.setId("1");
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

        CityModel cityModel1 = new CityModel();
        CityObjectMember cityObjectMember1 = new CityObjectMember(b1);
        cityModel1.addCityObjectMember(cityObjectMember1);

        CityModel cityModel3 = new CityModel();
        CityObjectMember cityObjectMember3 = new CityObjectMember(b3);
        cityModel3.addCityObjectMember(cityObjectMember3);

        TreeBuilder treeBuilder = new TreeBuilder();
        head1 = treeBuilder.createTree(cityModel1);
        head3 = treeBuilder.createTree(cityModel3);

    }

    @Before
    public void setUp() {
        DistanceTable distanceTable = new DistanceTable();
        cut = new FeatureMatcher(distanceTable);
    }

    @Test
    public void testCalculateMatchesTopDown() {
        initObjects();
        cut = new FeatureMatcher(new DistanceTable());

        Matching parentMatching = new Matching(head1, head3);
        cut.calculateMatchesTopDown(head1.getChildren(), head3.getChildren(), parentMatching);
        DistanceTable distanceTable = cut.getDistanceTable();
        assertNotNull(distanceTable);
        assertEquals(11, distanceTable.size());
    }

    @Test
    public void testAreFeaturesMatching() {
        boolean areMatching = cut.areFeaturesMatching(TestInstances.ELEMENT_NODE_EMPTY_BUILDING_1, TestInstances.ELEMENT_NODE_EMPTY_BUILDING_2);
        assertTrue(areMatching);
    }

    @Test
    public void testAreFeaturesMatchingFalse1() {
        boolean areMatching = cut.areFeaturesMatching(TestInstances.ELEMENT_NODE_EMPTY_BUILDING_1, TestInstances.ELEMENT_NODE_EMPTY_BUILDING_3);
        assertFalse(areMatching);
    }

    @Test
    public void testAreFeaturesMatchingFalse2() {
        boolean areMatching = cut.areFeaturesMatching(TestInstances.ELEMENT_NODE_EMPTY_BUILDING_1, TestInstances.TEXT_NODE_1);
        assertFalse(areMatching);
    }

    @Test
    public void testAreElementNodesMatching() {
        boolean areMatching = cut.areElementNodesMatching(TestInstances.ELEMENT_NODE_EMPTY_BUILDING_1, TestInstances.ELEMENT_NODE_EMPTY_BUILDING_2);
        assertTrue(areMatching);
    }

    @Test
    public void testAreElementNodesMatchingFalse1() {
        boolean areMatching = cut.areElementNodesMatching(TestInstances.ELEMENT_NODE_EMPTY_BUILDING_1, TestInstances.ELEMENT_NODE_EMPTY_BRIDGE);
        assertFalse(areMatching);
    }

    @Test
    public void testAreElementNodesMatchingFalse2() {
        boolean areMatching = cut.areElementNodesMatching(TestInstances.PARENT_NODE_1, TestInstances.PARENT_NODE_2);
        assertFalse(areMatching);
    }

}
