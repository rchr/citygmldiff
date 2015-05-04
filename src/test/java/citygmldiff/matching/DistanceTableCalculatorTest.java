package citygmldiff.matching;

import citygmldiff.util.TestInstances;
import de.igg.citygmldiff.citygmldiff.matching.DistanceTableCalculator;
import de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable;
import de.igg.citygmldiff.citygmldiff.matching.model.Matching;
import de.igg.citygmldiff.citygmldiff.tree.TreeBuilder;
import de.igg.citygmldiff.citygmldiff.tree.model.*;
import org.citygml4j.geometry.BoundingBox;
import org.citygml4j.geometry.Point;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.citygml.core.CityObjectMember;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.primitives.Envelope;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DistanceTableCalculatorTest {

    private ElementNode<CityModel> head1;
    private ElementNode<CityModel> head1Transformed;
    private ElementNode<CityModel> head2;
    private ElementNode<CityModel> head3;

    private ElementNode<String> parentNode1;
    private ElementNode<String> parentNode2;
    private ElementNode<String> middleNode1;
    private ElementNode<String> middleNode2;
    private ElementNode<String> middleNode3;
    private ElementNode<String> middleNode4;
    private ElementNode<String> middleNode5;
    private ElementNode<String> middleNode6;
    private ElementNode<String> childNode1;

    private AbstractLeafNode textNode1;
    private AbstractLeafNode textNode1a;
    private AbstractLeafNode textNode1b;
    private AbstractLeafNode textNode2;
    private AbstractLeafNode textNode3;
    private AbstractLeafNode textNode4;
    private AbstractLeafNode textNode5;
    private AbstractLeafNode textNode6;

    private AbstractLeafNode attributeNode1;
    private AbstractLeafNode attributeNode2;

    private Matching parentMatching;
    private Matching middleMatching12;
    private Matching middleMatching34;
    private Matching middleMatching56;
    private Matching matching1;
    private Matching matching2;
    private Matching matching3;
    private Matching attributeMatching1;

    private Map<String[], Boolean> emptyUncertainMap;

    private DistanceTable distanceTable;
    private DistanceTable distanceTableWoDistances;


    private DistanceTableCalculator cut;

    @Before
    public void setUp() {
        cut = new DistanceTableCalculator();
        emptyUncertainMap = new HashMap<>();
    }

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

        Building b1Transformed = new Building();
        b1Transformed.setId("1Transformed");

        Point lowerCorner1Transformed = new Point(5821545.822976049, 386516.2813817383, 74.6634593922645);
        Point upperCorner1Transformed = new Point(5821561.9812338, 386549.0270575486, 90.3453739183024);
        BoundingBox boundingBox1Transformed = new BoundingBox(lowerCorner1Transformed, upperCorner1Transformed);
        Envelope envelope1Transformed = new Envelope(boundingBox1Transformed);
        envelope1Transformed.setSrsName("urn:ogc:def:crs:EPSG::32633");
        envelope1Transformed.setSrsDimension(3);
        BoundingShape boundingShape1Transformed = new BoundingShape(envelope1Transformed);
        b1Transformed.setBoundedBy(boundingShape1Transformed);

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

        CityModel cityModel1Transformed = new CityModel();
        CityObjectMember cityObjectMember1Transformed = new CityObjectMember(b1Transformed);
        cityModel1Transformed.addCityObjectMember(cityObjectMember1Transformed);

        CityModel cityModel2 = new CityModel();
        CityObjectMember cityObjectMember2 = new CityObjectMember(b2);
        cityModel2.addCityObjectMember(cityObjectMember2);

        CityModel cityModel3 = new CityModel();
        CityObjectMember cityObjectMember3 = new CityObjectMember(b3);
        cityModel3.addCityObjectMember(cityObjectMember3);

        TreeBuilder treeBuilder = new TreeBuilder();
        head1 = treeBuilder.createTree(cityModel1);
        head1Transformed = treeBuilder.createTree(cityModel1Transformed);
        head2 = treeBuilder.createTree(cityModel2);
        head3 = treeBuilder.createTree(cityModel3);

    }

    private void initDistanceTable() {
        distanceTable = new DistanceTable();
        distanceTableWoDistances = new DistanceTable();

        parentNode1 = new ElementNode<>("parent", "Parent", "/parent", null);
        parentNode2 = new ElementNode<String>("parent", "Parent", "/parent", null);
        parentMatching = new Matching(parentNode1, parentNode2);
        distanceTable.put(parentMatching, 3);
        distanceTableWoDistances.put(parentMatching, 0);

        middleNode1 = new ElementNode<>("middle", "Middle", "/parent/middle12", parentNode1);
        parentNode1.addChild(middleNode1);
        middleNode2 = new ElementNode<>("middle", "Middle", "/parent/middle12", parentNode2);
        parentNode2.addChild(middleNode2);
        middleMatching12 = new Matching(middleNode1, middleNode2);
        middleMatching12.setParentMatching(parentMatching);
        parentMatching.addChildMatching(middleMatching12);
        distanceTableWoDistances.put(middleMatching12, 0);
        distanceTable.put(middleMatching12, 1);

        middleNode3 = new ElementNode<>("middle", "Middle", "/parent/middle34", parentNode1);
        parentNode1.addChild(middleNode3);
        middleNode4 = new ElementNode<>("middle", "Middle", "/parent/middle34", parentNode2);
        parentNode2.addChild(middleNode4);
        middleMatching34 = new Matching(middleNode3, middleNode4);
        middleMatching34.setParentMatching(parentMatching);
        parentMatching.addChildMatching(middleMatching34);
        distanceTableWoDistances.put(middleMatching34, 0);
        distanceTable.put(middleMatching34, 1);

        middleNode5 = new ElementNode<>("middle", "Middle", "/parent/middle56", parentNode1);
        parentNode1.addChild(middleNode5);
        middleNode6 = new ElementNode<>("middle", "Middle", "/parent/middle56", parentNode2);
        parentNode2.addChild(middleNode6);
        middleMatching56 = new Matching(middleNode5, middleNode6);
        middleMatching56.setParentMatching(parentMatching);
        parentMatching.addChildMatching(middleMatching56);
        distanceTable.put(middleMatching56, 0);
        distanceTableWoDistances.put(middleMatching56, 0);

        textNode1 = new TextNode("text1", parentNode1);
        parentNode1.addChild(middleNode1);
        textNode2 = new TextNode("text1a", parentNode2);
        parentNode2.addChild(middleNode2);
        matching1 = new Matching(textNode1, textNode2);
        matching1.setParentMatching(middleMatching12);
        middleMatching12.addChildMatching(matching1);
        distanceTable.put(matching1, 1);
        distanceTableWoDistances.put(matching1, 0);

        textNode1a = new TextNode("text1a", parentNode1);
        parentNode1.addChild(textNode1a);
        textNode1b = new TextNode("text1b", parentNode1);
        parentNode1.addChild(textNode1b);

        textNode3 = new TextNode("text3", middleNode3);
        middleNode3.addChild(textNode3);
        textNode4 = new TextNode("text4", middleNode4);
        middleNode4.addChild(textNode4);
        matching2 = new Matching(textNode3, textNode4);
        matching2.setParentMatching(middleMatching34);
        middleMatching34.addChildMatching(matching2);
        distanceTable.put(matching2, 1);
        distanceTableWoDistances.put(matching2, 0);

        attributeNode1 = new AttributeNode("attrName", "value1", "/parent", parentNode2);
        parentNode1.addChild(attributeNode1);
        attributeNode2 = new AttributeNode("attrName", "value2", "/parent", parentNode2);
        parentNode2.addChild(attributeNode2);
        attributeMatching1 = new Matching(attributeNode1, attributeNode2);
        parentMatching.addChildMatching(attributeMatching1);
        attributeMatching1.setParentMatching(parentMatching);
        distanceTable.put(attributeMatching1, 1);
        distanceTableWoDistances.put(attributeMatching1, 0);

        textNode5 = new TextNode("text56", middleNode5);
        middleNode5.addChild(textNode5);
        textNode6 = new TextNode("text56", middleNode6);
        middleNode6.addChild(textNode6);
        matching3 = new Matching(textNode5, textNode6);
        matching3.setParentMatching(middleMatching56);
        middleMatching56.addChildMatching(matching3);
        distanceTable.put(matching3, 0);
        distanceTableWoDistances.put(matching3, 0);

    }

    @Test
    public void testMatchCityModelsDifferentIDs() {
        initObjects();
        DistanceTable distanceTable = cut.computeDistanceTable(head1, head2, emptyUncertainMap);
        assertNotNull(distanceTable);
        assertEquals(12, distanceTable.size());
    }

    @Ignore("Complications at CRS transformation.")
    @Test
    public void testMatchCityModelsDifferentIDsAndDifferentCRS() {
        initObjects();
        DistanceTable distanceTable = cut.computeDistanceTable(head1, head1Transformed, emptyUncertainMap);
        assertNotNull(distanceTable);
        assertEquals(12, distanceTable.size());
    }

    @Test
    public void testMatchCityModelsSameIDsDifferentBBox() {
        initObjects();
        cut = new DistanceTableCalculator();
        cut.setDistanceTable(new DistanceTable());
        DistanceTable distanceTable = cut.computeDistanceTable(head1, head3, emptyUncertainMap);
        assertNotNull(distanceTable);

        assertEquals(12, distanceTable.size());
    }

    @Test
    public void testComputeDistanceForLeafNodes1() {
        int dist = cut.computeDistanceForLeafNodes(TestInstances.TEXT_NODE_1, TestInstances.TEXT_NODE_2);
        assertEquals(0, dist);
    }

    @Test
    public void testComputeDistanceForLeafNodes2() {
        int dist = cut.computeDistanceForLeafNodes(TestInstances.TEXT_NODE_1, TestInstances.TEXT_NODE_3);
        assertEquals(1, dist);
    }

    @Test
    public void testComputeDistanceForLeafNodes3() {
        int dist = cut.computeDistanceForLeafNodes(TestInstances.TEXT_NODE_1, TestInstances.ATTRIBUTE_NODE_1);
        assertEquals(1, dist);
    }

    @Test
    public void testCalculateDistances() {
        initDistanceTable();
        cut.setDistanceTable(distanceTableWoDistances);
        cut.calculateDistances();
        DistanceTable distanceTable = cut.getDistanceTable();

        assertNotNull(distanceTable);

        int matching1Dist = distanceTable.get(this.matching1);
        assertEquals(1, matching1Dist);

        int matching2Dist = distanceTable.get(this.matching2);
        assertEquals(1, matching2Dist);

        int matching3Dist = distanceTable.get(this.matching3);
        assertEquals(0, matching3Dist);

        int middleDistance12 = distanceTable.get(this.middleMatching12);
        assertEquals(1, middleDistance12);

        int middleDistance34 = distanceTable.get(this.middleMatching34);
        assertEquals(1, middleDistance34);

        int middleDistance56 = distanceTable.get(this.middleMatching56);
        assertEquals(0, middleDistance56);

        int parentDistance = distanceTable.get(this.parentMatching);
        assertEquals(3, parentDistance);

    }

    @Test
    public void testIncrementParentMatchings() {
        initDistanceTable();
        cut.setDistanceTable(distanceTableWoDistances);
        cut.incrementParentMatchings(matching1);
        DistanceTable distanceTable = cut.getDistanceTable();
        assertNotNull(distanceTable);

        Matching middleMatching = matching1.getParentMatching();
        int middleDistance = distanceTable.get(middleMatching);
        assertEquals(1, middleDistance);

        Matching parentMatching = middleMatching.getParentMatching();
        int parentDistance = distanceTable.get(parentMatching);
        assertEquals(1, parentDistance);

        Matching middleMatching2 = matching2.getParentMatching();
        int zeroDist = distanceTable.get(middleMatching2);
        assertEquals(0, zeroDist);
    }

    @Test
    public void testCollectMatchingsWithZeroDist1() {
        initDistanceTable();
        cut.setDistanceTable(distanceTable);
        Set<Matching> matchings = cut.collectMatchingsWithZeroDist();
        assertNotNull(matchings);
        assertEquals(2, matchings.size());
    }

    @Test
    public void testRemoveNodesWithZeroDistRecursively1() {
        initDistanceTable();
        cut.setDistanceTable(distanceTable);
        Set<Matching> matchingsZeroDist = cut.collectMatchingsWithZeroDist();

        List<ElementNode<?>> tree1 = new ArrayList<>();
        tree1.add(parentNode1);
        List<ElementNode<?>> tree2 = new ArrayList<>();
        tree2.add(parentNode2);

        cut.removeNodesWithZeroDistRecursively(tree1, tree2, matchingsZeroDist);
        assertTreeNotContains(parentNode1, "/parent/middle56/textNode");
        assertTreeNotContains(parentNode1, "/parent/middle56");
    }

    @Test
    public void testRemoveNodesWithZeroDistRecursively2() {
        initDistanceTable();
        cut.setDistanceTable(distanceTableWoDistances);
        Set<Matching> matchingsZeroDist = cut.collectMatchingsWithZeroDist();
        List<ElementNode<?>> tree1 = new ArrayList<>();
        tree1.add(parentNode1);
        List<ElementNode<?>> tree2 = new ArrayList<>();
        tree2.add(parentNode2);
        cut.removeNodesWithZeroDistRecursively(tree1, tree2, matchingsZeroDist);
        assertEquals(1, tree1.size());
        assertEquals(1, tree2.size());
    }

    private void assertTreeNotContains(AbstractTreeNode root, String signature) {
        assertFalse(doesNodesContain(Arrays.asList(root), signature, false));
    }

    private boolean doesNodesContain(List<AbstractTreeNode> nodes, String signature, boolean foundNode) {
        for (AbstractTreeNode n : nodes) {
            if (signature.equals(n.getSignature())) {
                foundNode = true;
            }
            if (n.hasChildren()) {
                foundNode = doesNodesContain(n.getChildren(), signature, foundNode);
            }
        }
        return foundNode;
    }

}
