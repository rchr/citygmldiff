package citygmldiff.util;

import de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable;
import de.igg.citygmldiff.citygmldiff.matching.model.Matching;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractLeafNode;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import org.citygml4j.geometry.BoundingBox;
import org.citygml4j.model.citygml.bridge.Bridge;
import org.citygml4j.model.citygml.building.*;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurface;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurfaceProperty;
import org.citygml4j.model.gml.geometry.primitives.*;

import java.util.Arrays;
import java.util.List;

public class TestInstances {


    public static String ID_1 = "1";
    public static Building BUILDING_1;

    public static Building BUILDING;

    public static ElementNode<Building> ELEMENT_NODE_EMPTY_BUILDING_1;
    public static ElementNode<Building> ELEMENT_NODE_EMPTY_BUILDING_2;
    public static ElementNode<Building> ELEMENT_NODE_EMPTY_BUILDING_3;

    public static Bridge EMPTY_BRIDGE = new Bridge();
    public static ElementNode<Bridge> ELEMENT_NODE_EMPTY_BRIDGE = new ElementNode<Bridge>(EMPTY_BRIDGE, "Bridge", "/bridge", null);

    public static ElementNode<String> PARENT_NODE_1;
    public static ElementNode<String> PARENT_NODE_2;
    public static ElementNode<String> CHILD_NODE_1;

    public static AbstractLeafNode TEXT_NODE_1;
    public static AbstractLeafNode TEXT_NODE_1a;
    public static AbstractLeafNode TEXT_NODE_1b;
    public static AbstractLeafNode TEXT_NODE_2;
    public static AbstractLeafNode TEXT_NODE_3;
    public static AbstractLeafNode TEXT_NODE_4;
    public static AbstractLeafNode TEXT_NODE_5;
    public static AbstractLeafNode TEXT_NODE_6;

    public static AbstractLeafNode ATTRIBUTE_NODE_1;
    public static AbstractLeafNode ATTRIBUTE_NODE_2;

    public static Matching PARENT_MATCHING;
    public static Matching MATCHING_1;
    public static Matching MATCHING_1a;
    public static Matching MATCHING_1b;
    public static Matching MATCHING_2;
    public static Matching ATTRIBUTE_MATCHING_1;

    public static DistanceTable DISTANCE_TABLE;
    public static DistanceTable DISTANCE_TABLE_WO_DISTANCES;

    static {
        DISTANCE_TABLE = new DistanceTable();
        DISTANCE_TABLE_WO_DISTANCES = new DistanceTable();

        PARENT_NODE_1 = new ElementNode<>("parent", "Parent", "/parent", null);
        PARENT_NODE_2 = new ElementNode<String>("parent", "Parent", "/parent", null);
        PARENT_MATCHING = new Matching(PARENT_NODE_1, PARENT_NODE_2);
        DISTANCE_TABLE.put(PARENT_MATCHING, 1);
        DISTANCE_TABLE_WO_DISTANCES.put(PARENT_MATCHING, 0);

        TEXT_NODE_1 = new TextNode("text1", PARENT_NODE_1);
        PARENT_NODE_1.addChild(TEXT_NODE_1);
        TEXT_NODE_2 = new TextNode("text1", PARENT_NODE_2);
        PARENT_NODE_2.addChild(TEXT_NODE_2);
        MATCHING_1 = new Matching(TEXT_NODE_1, TEXT_NODE_2);
        MATCHING_1.setParentMatching(PARENT_MATCHING);
        PARENT_MATCHING.addChildMatching(MATCHING_1);
        DISTANCE_TABLE.put(MATCHING_1, 0);
        DISTANCE_TABLE_WO_DISTANCES.put(MATCHING_1, 0);

        TEXT_NODE_1a = new TextNode("text1a", PARENT_NODE_1);
        PARENT_NODE_1.addChild(TEXT_NODE_1a);
        TEXT_NODE_1b = new TextNode("text1b", PARENT_NODE_1);
        PARENT_NODE_1.addChild(TEXT_NODE_1b);

        TEXT_NODE_3 = new TextNode("text3", PARENT_NODE_2);
        PARENT_NODE_1.addChild(TEXT_NODE_3);
        TEXT_NODE_4 = new TextNode("text4", PARENT_NODE_2);
        PARENT_NODE_2.addChild(TEXT_NODE_4);
        MATCHING_2 = new Matching(TEXT_NODE_3, TEXT_NODE_4);
        DISTANCE_TABLE.put(MATCHING_2, 1);
        DISTANCE_TABLE_WO_DISTANCES.put(MATCHING_2, 0);

        MATCHING_1a = new Matching(TEXT_NODE_1a, TEXT_NODE_3);
        MATCHING_1b = new Matching(TEXT_NODE_1, TEXT_NODE_4);

        ATTRIBUTE_NODE_1 = new AttributeNode("attrName", "value1", "/parent", PARENT_NODE_2);
        PARENT_NODE_1.addChild(ATTRIBUTE_NODE_1);

        ATTRIBUTE_NODE_2 = new AttributeNode("attrName", "value2", "/parent", PARENT_NODE_2);
        PARENT_NODE_2.addChild(ATTRIBUTE_NODE_2);
        ATTRIBUTE_MATCHING_1 = new Matching(ATTRIBUTE_NODE_1, ATTRIBUTE_NODE_2);
        DISTANCE_TABLE.put(ATTRIBUTE_MATCHING_1, 1);
        DISTANCE_TABLE_WO_DISTANCES.put(ATTRIBUTE_MATCHING_1, 0);

        DISTANCE_TABLE.put(PARENT_MATCHING, 2);
        DISTANCE_TABLE_WO_DISTANCES.put(PARENT_MATCHING, 0);

        TEXT_NODE_5 = new TextNode("text5", PARENT_NODE_1);
        PARENT_NODE_1.addChild(TEXT_NODE_5);

        CHILD_NODE_1 = new ElementNode<String>("child", "Child", "/parent/child", PARENT_NODE_1);
        PARENT_NODE_1.addChild(CHILD_NODE_1);
        TEXT_NODE_6 = new TextNode("text6", CHILD_NODE_1);
        CHILD_NODE_1.addChild(TEXT_NODE_6);

        BUILDING = new Building();
        BUILDING.setId("1234");
        ELEMENT_NODE_EMPTY_BUILDING_1 = new ElementNode<Building>(BUILDING, "Building", "/building", null);
        ELEMENT_NODE_EMPTY_BUILDING_2 = new ElementNode<Building>(BUILDING, "Building", "/building", null);
        ELEMENT_NODE_EMPTY_BUILDING_3 = new ElementNode<Building>(BUILDING, "Building", "/building/somethingElse", null);

    }


    public static Building createBuilding(String id) {
        Building b1 = new Building();
        b1.setId("building" + id);

        org.citygml4j.geometry.Point lowerCorner1 = new org.citygml4j.geometry.Point(13.3269637686746, 52.5321790563424, 74.6634593922645);
        org.citygml4j.geometry.Point upperCorner1 = new org.citygml4j.geometry.Point(13.3274412933907, 52.5323175874323, 90.3453739183024);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(bBox1);
        envelope1.setSrsDimension(3);
        envelope1.setSrsName("urn:ogc:def:crs:EPSG::4326");
        BoundingShape boundingShape1 = new BoundingShape(envelope1);
        b1.setBoundedBy(boundingShape1);

        WallSurface wallSurface = new WallSurface();
        org.citygml4j.geometry.Point lowerCornerWall = new org.citygml4j.geometry.Point(13.3269637686813, 52.5321790563463, 74.6634593894705);
        org.citygml4j.geometry.Point upperCornerWall = new org.citygml4j.geometry.Point(13.3274412933906, 52.5323175874317, 90.3453737022355);
        BoundingBox bBoxWall = new BoundingBox(lowerCornerWall, upperCornerWall);
        Envelope envelopeWall = new Envelope(bBoxWall);
        envelopeWall.setSrsDimension(3);
        envelopeWall.setSrsName("urn:ogc:def:crs:EPSG::4326");
        BoundingShape boundingShapeWall = new BoundingShape(envelopeWall);
        wallSurface.setBoundedBy(boundingShapeWall);

        MultiSurface multiSurfaceWall = new MultiSurface();
        multiSurfaceWall.setId("multiSurfaceWall" + id);

        SurfaceProperty wall1SurfaceProp1 = createSurfaceProperty("polygonWall1" + id, "linearRingWall1" + id, Arrays.asList(13.3274412761895, 52.532182792233, 74.6634503705427, 13.3274412775653, 52.5321827935465, 79.210948058404, 13.3272422407462, 52.5321797602721, 79.2112362170592, 13.3272422393704, 52.5321797589586, 74.6637385291979, 13.3274412761895, 52.532182792233, 74.6634503705427));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp1);

        SurfaceProperty wall1SurfaceProp2 = createSurfaceProperty("polygonWall2" + id, "linearRingWall2" + id, Arrays.asList(13.3272387341963, 52.5323145430766, 74.6632613306865, 13.3272387355721, 52.5323145443901, 79.2107590194792, 13.3274377730004, 52.5323175776712, 79.2104708570987, 13.3274377716246, 52.5323175763577, 74.6629731673747, 13.3272387341963, 52.5323145430766, 74.663261330686));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp2);

        SurfaceProperty wall1SurfaceProp3 = createSurfaceProperty("polygonWall3" + id, "linearRingWall3" + id, Arrays.asList(13.3274377716246, 52.5323175763577, 74.6629731673747, 13.3274377730004, 52.5323175776712, 79.2104708570987, 13.3274384739216, 52.5322906206026, 79.2105662999675, 13.3274412775653, 52.5321827935465, 79.210948058404, 13.3274412761895, 52.532182792233, 74.6634503705427, 13.3274384725457, 52.5322906192891, 74.6630686083809, 13.3274377716246, 52.5323175763577, 74.6629731673747));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp3);

        SurfaceProperty wall1SurfaceProp4 = createSurfaceProperty("polygonWall4" + id, "linearRingWall4" + id, Arrays.asList(13.3272387355721, 52.5323145443901, 79.2107590194792, 13.3272387341963, 52.5323145430766, 74.6632613306865, 13.3272394352393, 52.5322875860094, 74.6633567716926, 13.3272422393704, 52.5321797589586, 74.6637385291979, 13.3272422407462, 52.5321797602721, 79.2112362170592, 13.3272394366151, 52.5322875873229, 79.2108544614166, 13.3272387355721, 52.5323145443901, 79.2107590194792));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp4);

        SurfaceProperty wall1SurfaceProp5 = createSurfaceProperty("polygonWall5" + id, "linearRingWall5" + id, Arrays.asList(13.327239676004, 52.5322588109418, 75.0134577443823, 13.3272396806425, 52.5322588153702, 90.3451951900497, 13.3269641825761, 52.5322556072843, 90.3455904964358, 13.3269641779378, 52.5322556028559, 75.0138530535623, 13.327239676004, 52.5322588109418, 75.0134577443823));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp5);

        SurfaceProperty wall1SurfaceProp6 = createSurfaceProperty("polygonWall6" + id, "linearRingWall6" + id, Arrays.asList(13.3272392942551, 52.5323149163834, 75.0132576012984, 13.3272392988936, 52.5323149208119, 90.3449950441718, 13.3272396806425, 52.5322588153702, 90.3451951900497, 13.327239676004, 52.5322588109418, 75.0134577443823, 13.3272392942551, 52.5323149163834, 75.0132576012984));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp6);

        SurfaceProperty wall1SurfaceProp7 = createSurfaceProperty("polygonWall7" + id, "linearRingWall7" + id, Arrays.asList(13.3269637958378, 52.5323117082961, 75.0136529114097, 13.3269638004762, 52.5323117127246, 90.3453903552145, 13.3272392988936, 52.5323149208119, 90.3449950441718, 13.3272392942551, 52.5323149163834, 75.0132576012984, 13.3269637958378, 52.5323117082961, 75.0136529114097));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp7);

        SurfaceProperty wall1SurfaceProp8 = createSurfaceProperty("polygonWall8", "linearRingWall8", Arrays.asList(13.3269641779378, 52.5322556028559, 75.0138530535623, 13.3269641825761, 52.5322556072843, 90.3455904964358, 13.3269638004762, 52.5323117127246, 90.3453903552145, 13.3269637958378, 52.5323117082961, 75.0136529114097, 13.3269641779378, 52.5322556028559, 75.0138530535623));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp8);

        MultiSurfaceProperty multiSurfacePropertyWall = new MultiSurfaceProperty(multiSurfaceWall);
        wallSurface.setLod2MultiSurface(multiSurfacePropertyWall);
        BoundarySurfaceProperty boundarySurfacePropertyWall = new BoundarySurfaceProperty(wallSurface);
        b1.addBoundedBySurface(boundarySurfacePropertyWall);

        GroundSurface groundSurface = new GroundSurface();
        org.citygml4j.geometry.Point lowerCornerGround = new org.citygml4j.geometry.Point(13.326963764043, 52.5321790563463, 74.6634593894705);
        org.citygml4j.geometry.Point upperCornerGround = new org.citygml4j.geometry.Point(13.3274412933906, 52.5323175830033, 75.0136362574995);
        BoundingBox bBoxGround = new BoundingBox(lowerCornerGround, upperCornerGround);
        Envelope envelopeGround = new Envelope(bBoxGround);
        envelopeGround.setSrsDimension(3);
        envelopeGround.setSrsName("urn:ogc:def:crs:EPSG::4326");
        BoundingShape boundingShapeGround = new BoundingShape(envelopeGround);
        groundSurface.setBoundedBy(boundingShapeGround);
        MultiSurface multiSurfaceGround = new MultiSurface();
        multiSurfaceGround.setId("multiSurfaceGround" + id);

        SurfaceProperty ground1SurfaceProp1 = createSurfaceProperty("polygonGround1" + id, "linearRingGround1" + id, Arrays.asList(13.3272387341963, 52.5323145430766, 74.6632613306865, 13.3274377716246, 52.5323175763577, 74.6629731673747, 13.3274384725457, 52.5322906192891, 74.6630686083809, 13.3274412761895, 52.532182792233, 74.6634503705427, 13.3272422393704, 52.5321797589586, 74.6637385291979, 13.3272394352393, 52.5322875860094, 74.6633567716926, 13.3272387341963, 52.5323145430766, 74.6632613306865));
        multiSurfaceGround.addSurfaceMember(ground1SurfaceProp1);
        SurfaceProperty ground1SurfaceProp2 = createSurfaceProperty("polygonGround2" + id, "linearRingGround2" + id, Arrays.asList(13.3269637958378, 52.5323117082961, 75.0136529114097, 13.3272392942551, 52.5323149163834, 75.0132576012984, 13.327239676004, 52.5322588109418, 75.0134577443823, 13.3269641779378, 52.5322556028559, 75.0138530535623, 13.3269637958378, 52.5323117082961, 75.0136529114097));
        multiSurfaceGround.addSurfaceMember(ground1SurfaceProp2);

        MultiSurfaceProperty multiSurfacePropertyGround = new MultiSurfaceProperty(multiSurfaceGround);
        groundSurface.setLod2MultiSurface(multiSurfacePropertyGround);
        BoundarySurfaceProperty boundarySurfacePropertyGround = new BoundarySurfaceProperty(groundSurface);
        b1.addBoundedBySurface(boundarySurfacePropertyGround);

        RoofSurface roofSurface = new RoofSurface();
        org.citygml4j.geometry.Point lowerCornerRoof = new org.citygml4j.geometry.Point(13.3269637686813, 52.5321790576598, 79.2109570810571);
        org.citygml4j.geometry.Point upperCornerRoof = new org.citygml4j.geometry.Point(13.3274412947665, 52.5323175874317, 90.3453737022355);
        BoundingBox bBoxRoof = new BoundingBox(lowerCornerRoof, upperCornerRoof);
        Envelope envelopeRoof = new Envelope(bBoxRoof);
        envelopeRoof.setSrsDimension(3);
        envelopeRoof.setSrsName("urn:ogc:def:crs:EPSG::4326");
        BoundingShape boundingShapeRoof = new BoundingShape(envelopeRoof);
        roofSurface.setBoundedBy(boundingShapeRoof);
        MultiSurface multiSurfaceRoof = new MultiSurface();
        multiSurfaceRoof.setId("multiSurfaceRoof" + id);

        SurfaceProperty roof1SurfaceProp1 = createSurfaceProperty("polygonRoof1" + id, "linearRingRoof1" + id, Arrays.asList(13.3274377730004, 52.5323175776712, 79.2104708570987, 13.3272387355721, 52.5323145443901, 79.2107590194792, 13.3272394366151, 52.5322875873229, 79.2108544614166, 13.3272422407462, 52.5321797602721, 79.2112362170592, 13.3274412775653, 52.5321827935465, 79.210948058404, 13.3274384739216, 52.5322906206026, 79.2105662999675, 13.3274377730004, 52.5323175776712, 79.2104708570987));
        multiSurfaceRoof.addSurfaceMember(roof1SurfaceProp1);

        SurfaceProperty roof2SurfaceProp1 = createSurfaceProperty("polygonRoof2" + id, "linearRingRoof2" + id, Arrays.asList(13.3269641825761, 52.5322556072843, 90.3455904964358, 13.3272396806425, 52.5322588153702, 90.3451951900497, 13.3272392988936, 52.5323149208119, 90.3449950441718, 13.3269638004762, 52.5323117127246, 90.3453903552145, 13.3269641825761, 52.5322556072843, 90.3455904964358));
        multiSurfaceRoof.addSurfaceMember(roof2SurfaceProp1);

        MultiSurfaceProperty multiSurfacePropertyRoof = new MultiSurfaceProperty(multiSurfaceRoof);
        roofSurface.setLod2MultiSurface(multiSurfacePropertyRoof);
        BoundarySurfaceProperty boundarySurfacePropertyRoof = new BoundarySurfaceProperty(roofSurface);
        b1.addBoundedBySurface(boundarySurfacePropertyRoof);

        return b1;
    }

    public static Building createBuildingTransformed(String id) {
        Building b1 = new Building();
        b1.setId("building" + id);

        org.citygml4j.geometry.Point lowerCorner1 = new org.citygml4j.geometry.Point(5821546.573717301, 386516.2813817383, 74.6634593922645);
        org.citygml4j.geometry.Point upperCorner1 = new org.citygml4j.geometry.Point(5821561.230493521, 386549.0270575486, 90.3453739183024);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(bBox1);
        envelope1.setSrsDimension(3);
        envelope1.setSrsName("urn:ogc:def:crs:EPSG::32633");
        BoundingShape boundingShape1 = new BoundingShape(envelope1);
        b1.setBoundedBy(boundingShape1);

        WallSurface wallSurface = new WallSurface();
        org.citygml4j.geometry.Point lowerCornerWall = new org.citygml4j.geometry.Point(5821546.5737177245, 386516.2813822028, 74.6634593894705);
        org.citygml4j.geometry.Point upperCornerWall = new org.citygml4j.geometry.Point(5821561.230493454, 386549.0270575402, 90.3453737022355);
        BoundingBox bBoxWall = new BoundingBox(lowerCornerWall, upperCornerWall);
        Envelope envelopeWall = new Envelope(bBoxWall);
        envelopeWall.setSrsDimension(3);
        envelopeWall.setSrsName("urn:ogc:def:crs:EPSG::32633");
        BoundingShape boundingShapeWall = new BoundingShape(envelopeWall);
        wallSurface.setBoundedBy(boundingShapeWall);

        MultiSurface multiSurfaceWall = new MultiSurface();
        multiSurfaceWall.setId("multiSurfaceWall" + id);

        SurfaceProperty wall1SurfaceProp1 = createSurfaceProperty("polygonWall1" + id, "linearRingWall1" + id, Arrays.asList(5821546.238511254, 386548.67843756895, 74.6634503705427, 5821546.238655179, 386548.67853426974, 79.210948058404, 5821546.214182442, 386535.170843701, 79.2112362170592, 5821546.214038516, 386535.1707469999, 74.6637385291979, 5821546.238511254, 386548.67843756895, 74.6634503705427));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp1);

        SurfaceProperty wall1SurfaceProp2 = createSurfaceProperty("polygonWall2" + id, "linearRingWall2" + id, Arrays.asList(5821561.210325485, 386535.2804718575, 74.6632613306865, 5821561.210469411, 386535.2805685585, 79.2107590194792, 5821561.234941682, 386548.78825912985, 79.2104708570987, 5821561.234797755, 386548.7881624294, 74.6629731673747, 5821561.210325485, 386535.2804718575, 74.663261330686));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp2);

        SurfaceProperty wall1SurfaceProp3 = createSurfaceProperty("polygonWall3" + id, "linearRingWall3" + id, Arrays.asList(5821561.234797755, 386548.7881624294, 74.6629731673747, 5821561.234941682, 386548.78825912985, 79.2104708570987, 5821558.235657244, 386548.76631392, 79.2105662999675, 5821546.238655179, 386548.67853426974, 79.210948058404, 5821546.238511254, 386548.67843756895, 74.6634503705427, 5821558.235513317, 386548.7662172127, 74.6630686083809, 5821561.234797755, 386548.7881624294, 74.6629731673747));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp3);

        SurfaceProperty wall1SurfaceProp4 = createSurfaceProperty("polygonWall4" + id, "linearRingWall4" + id, Arrays.asList(5821561.210469411, 386535.2805685585, 79.2107590194792, 5821561.210325485, 386535.2804718575, 74.6632613306865, 5821558.211040961, 386535.2585266455, 74.6633567716926, 5821546.214038516, 386535.1707469999, 74.6637385291979, 5821546.214182442, 386535.170843701, 79.2112362170592, 5821558.211184885, 386535.2586233465, 79.2108544614166, 5821561.210469411, 386535.2805685585, 79.2107590194792));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp4);

        SurfaceProperty wall1SurfaceProp5 = createSurfaceProperty("polygonWall5" + id, "linearRingWall5" + id, Arrays.asList(5821555.010280882, 386535.2006760242, 75.0134577443823, 5821555.010766119, 386535.2010020508, 90.3451951900497, 5821555.087111213, 386516.50683189364, 90.3455904964358, 5821555.0866259765, 386516.5065058785, 75.0138530535623, 5821555.010280882, 386535.2006760242, 75.0134577443823));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp5);

        SurfaceProperty wall1SurfaceProp6 = createSurfaceProperty("polygonWall6" + id, "linearRingWall6" + id, Arrays.asList(5821561.250964431, 386535.31942065776, 75.0132576012984, 5821561.251449679, 386535.31974668405, 90.3449950441718, 5821555.010766119, 386535.2010020508, 90.3451951900497, 5821555.010280882, 386535.2006760242, 75.0134577443823, 5821561.250964431, 386535.31942065776, 75.0132576012984));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp6);

        SurfaceProperty wall1SurfaceProp7 = createSurfaceProperty("polygonWall7" + id, "linearRingWall7" + id, Arrays.asList(5821561.32730979, 386516.62525051704, 75.0136529114097, 5821561.327795038, 386516.62557653844, 90.3453903552145, 5821561.251449679, 386535.31974668405, 90.3449950441718, 5821561.250964431, 386535.31942065776, 75.0132576012984, 5821561.32730979, 386516.62525051704, 75.0136529114097));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp7);

        SurfaceProperty wall1SurfaceProp8 = createSurfaceProperty("polygonWall8" + id, "linearRingWall8" + id, Arrays.asList(5821555.0866259765, 386516.5065058785, 75.0138530535623, 5821555.087111213, 386516.50683189364, 90.3455904964358, 5821561.327795038, 386516.62557653844, 90.3453903552145, 5821561.32730979, 386516.62525051704, 75.0136529114097, 5821555.0866259765, 386516.5065058785, 75.0138530535623));
        multiSurfaceWall.addSurfaceMember(wall1SurfaceProp8);

        MultiSurfaceProperty multiSurfacePropertyWall = new MultiSurfaceProperty(multiSurfaceWall);
        wallSurface.setLod2MultiSurface(multiSurfacePropertyWall);
        BoundarySurfaceProperty boundarySurfacePropertyWall = new BoundarySurfaceProperty(wallSurface);
        b1.addBoundedBySurface(boundarySurfacePropertyWall);

        GroundSurface groundSurface = new GroundSurface();
        org.citygml4j.geometry.Point lowerCornerGround = new org.citygml4j.geometry.Point(5821546.573725018, 386516.2810676055, 74.6634593894705);
        org.citygml4j.geometry.Point upperCornerGround = new org.citygml4j.geometry.Point(5821561.230000925, 386549.0270461254, 75.0136362574995);
        BoundingBox bBoxGround = new BoundingBox(lowerCornerGround, upperCornerGround);
        Envelope envelopeGround = new Envelope(bBoxGround);
        envelopeGround.setSrsDimension(3);
        envelopeGround.setSrsName("urn:ogc:def:crs:EPSG::32633");
        BoundingShape boundingShapeGround = new BoundingShape(envelopeGround);
        groundSurface.setBoundedBy(boundingShapeGround);
        MultiSurface multiSurfaceGround = new MultiSurface();
        multiSurfaceGround.setId("multiSurfaceGround" + id);

        SurfaceProperty ground1SurfaceProp1 = createSurfaceProperty("polygonGround1" + id, "linearRingGround1" + id, Arrays.asList(5821561.210325485, 386535.2804718575, 74.6632613306865, 5821561.234797755, 386548.7881624294, 74.6629731673747, 5821558.235513317, 386548.7662172127, 74.6630686083809, 5821546.238511254, 386548.67843756895, 74.6634503705427, 5821546.214038516, 386535.1707469999, 74.6637385291979, 5821558.211040961, 386535.2585266455, 74.6633567716926, 5821561.210325485, 386535.2804718575, 74.6632613306865));
        multiSurfaceGround.addSurfaceMember(ground1SurfaceProp1);
        SurfaceProperty ground1SurfaceProp2 = createSurfaceProperty("polygonGround2" + id, "linearRingGround2" + id, Arrays.asList(5821561.32730979, 386516.62525051704, 75.0136529114097, 5821561.250964431, 386535.31942065776, 75.0132576012984, 5821555.010280882, 386535.2006760242, 75.0134577443823, 5821555.0866259765, 386516.5065058785, 75.0138530535623, 5821561.32730979, 386516.62525051704, 75.0136529114097));
        multiSurfaceGround.addSurfaceMember(ground1SurfaceProp2);

        MultiSurfaceProperty multiSurfacePropertyGround = new MultiSurfaceProperty(multiSurfaceGround);
        groundSurface.setLod2MultiSurface(multiSurfacePropertyGround);
        BoundarySurfaceProperty boundarySurfacePropertyGround = new BoundarySurfaceProperty(groundSurface);
        b1.addBoundedBySurface(boundarySurfacePropertyGround);

        RoofSurface roofSurface = new RoofSurface();
        org.citygml4j.geometry.Point lowerCornerRoof = new org.citygml4j.geometry.Point(5821546.573863813, 386516.2813855895, 79.2109570810571);
        org.citygml4j.geometry.Point upperCornerRoof = new org.citygml4j.geometry.Point(5821561.230491292, 386549.02715086174, 90.3453737022355);
        BoundingBox bBoxRoof = new BoundingBox(lowerCornerRoof, upperCornerRoof);
        Envelope envelopeRoof = new Envelope(bBoxRoof);
        envelopeRoof.setSrsDimension(3);
        envelopeRoof.setSrsName("urn:ogc:def:crs:EPSG::32633");
        BoundingShape boundingShapeRoof = new BoundingShape(envelopeRoof);
        roofSurface.setBoundedBy(boundingShapeRoof);
        MultiSurface multiSurfaceRoof = new MultiSurface();
        multiSurfaceRoof.setId("multiSurfaceRoof" + id);

        SurfaceProperty roof1SurfaceProp1 = createSurfaceProperty("polygonRoof1" + id, "linearRingRoof1" + id, Arrays.asList(5821561.234941682, 386548.78825912985, 79.2104708570987, 5821561.210469411, 386535.2805685585, 79.2107590194792, 5821558.211184885, 386535.2586233465, 79.2108544614166, 5821546.214182442, 386535.170843701, 79.2112362170592, 5821546.238655179, 386548.67853426974, 79.210948058404, 5821558.235657244, 386548.76631392, 79.2105662999675, 5821561.234941682, 386548.78825912985, 79.2104708570987));
        multiSurfaceRoof.addSurfaceMember(roof1SurfaceProp1);

        SurfaceProperty roof2SurfaceProp1 = createSurfaceProperty("polygonRoof2" + id, "linearRingRoof2" + id, Arrays.asList(5821555.087111213, 386516.50683189364, 90.3455904964358, 5821555.010766119, 386535.2010020508, 90.3451951900497, 5821561.251449679, 386535.31974668405, 90.3449950441718, 5821561.327795038, 386516.62557653844, 90.3453903552145, 5821555.087111213, 386516.50683189364, 90.3455904964358));
        multiSurfaceRoof.addSurfaceMember(roof2SurfaceProp1);

        MultiSurfaceProperty multiSurfacePropertyRoof = new MultiSurfaceProperty(multiSurfaceRoof);
        roofSurface.setLod2MultiSurface(multiSurfacePropertyRoof);
        BoundarySurfaceProperty boundarySurfacePropertyRoof = new BoundarySurfaceProperty(roofSurface);
        b1.addBoundedBySurface(boundarySurfacePropertyRoof);

        return b1;
    }

    private static SurfaceProperty createSurfaceProperty(String polygonID, String linearRingID, List<Double> coordinates) {
        Polygon polygon = new Polygon();
        polygon.setId(polygonID);
        LinearRing linearRing = new LinearRing();
        linearRing.setId(linearRingID);
        DirectPositionList directPositionList = new DirectPositionList();
        directPositionList.addValue(coordinates);
        directPositionList.setSrsDimension(3);
        linearRing.setPosList(directPositionList);
        Exterior exterior = new Exterior(linearRing);
        polygon.setExterior(exterior);
        return new SurfaceProperty(polygon);
    }


}
