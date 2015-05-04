package de.igg.citygmldiff.citygmldiff.matching.spatial;

import junit.framework.TestCase;
import org.citygml4j.geometry.BoundingBox;
import org.citygml4j.geometry.Point;
import org.citygml4j.model.citygml.building.BoundarySurfaceProperty;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.citygml.building.GroundSurface;
import org.citygml4j.model.citygml.building.WallSurface;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurface;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurfaceProperty;
import org.citygml4j.model.gml.geometry.primitives.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by richard on 01.04.14.
 */
public class BuildingWallMatcherTest extends TestCase {

    private Building b1, b3;

    private void initObjects() {
        b1 = new Building();
        b1.setId("1");
        Point lowerCorner1 = new Point(13.3269637686746, 52.5321790563424, 74.6634593922645);
        Point upperCorner1 = new Point(13.3274412933907, 52.5323175874323, 90.3453739183024);
        BoundingBox boundingBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(boundingBox1);
        envelope1.setSrsName("urn:ogc:def:crs:EPSG::4326");
        envelope1.setSrsDimension(3);
        BoundingShape boundingShape1 = new BoundingShape(envelope1);
        b1.setBoundedBy(boundingShape1);

        WallSurface wallSurface = new WallSurface();
        MultiSurface multiSurface = new MultiSurface();
        List<Double> posList = Arrays.asList(13.3274412761895, 52.532182792233, 74.6634503705427, 13.3274412775653, 52.5321827935465, 79.210948058404, 13.3272422407462, 52.5321797602721, 79.2112362170592, 13.3272422393704, 52.5321797589586, 74.6637385291979, 13.3274412761895, 52.532182792233, 74.6634503705427);
        DirectPositionList directPositionList = new DirectPositionList();
        directPositionList.setValue(posList);
        directPositionList.setSrsDimension(3);
        LinearRing linearRing = new LinearRing();
        linearRing.setPosList(directPositionList);
        linearRing.setId("GEOM_6398777_0_");
        Exterior exterior = new Exterior(linearRing);
        Polygon polygon = new Polygon();
        polygon.setExterior(exterior);
        polygon.setId("GEOM_6398777");
        SurfaceProperty surfaceProperty = new SurfaceProperty(polygon);
        multiSurface.addSurfaceMember(surfaceProperty);

        List<Double> posList2 = Arrays.asList(13.3272387341963, 52.5323145430766, 74.6632613306865, 13.3272387355721, 52.5323145443901, 79.2107590194792, 13.3274377730004, 52.5323175776712, 79.2104708570987, 13.3274377716246, 52.5323175763577, 74.6629731673747, 13.3272387341963, 52.5323145430766, 74.6632613306865);
        DirectPositionList directPositionList2 = new DirectPositionList();
        directPositionList2.setValue(posList2);
        directPositionList2.setSrsDimension(3);
        LinearRing linearRing2 = new LinearRing();
        linearRing2.setPosList(directPositionList2);
        linearRing2.setId("GEOM_6398778_0_");
        Exterior exterior2 = new Exterior(linearRing2);
        Polygon polygon2 = new Polygon();
        polygon2.setExterior(exterior2);
        polygon2.setId("GEOM_6398778");
        SurfaceProperty surfaceProperty2 = new SurfaceProperty(polygon2);
        multiSurface.addSurfaceMember(surfaceProperty2);

        MultiSurfaceProperty multiSurfaceProperty = new MultiSurfaceProperty(multiSurface);
        wallSurface.setLod2MultiSurface(multiSurfaceProperty);
        BoundarySurfaceProperty boundarySurfaceProperty = new BoundarySurfaceProperty(wallSurface);
        b1.addBoundedBySurface(boundarySurfaceProperty);

        GroundSurface groundSurface = new GroundSurface();
        MultiSurface multiSurface3 = new MultiSurface();
        List<Double> posList3 = Arrays.asList(13.3272387341963, 52.5323145430766, 74.6632613306865, 13.3274377716246, 52.5323175763577, 74.6629731673747, 13.3274384725457, 52.5322906192891, 74.6630686083809, 13.3274412761895, 52.532182792233, 74.6634503705427, 13.3272422393704, 52.5321797589586, 74.6637385291979, 13.3272394352393, 52.5322875860094, 74.6633567716926, 13.3272387341963, 52.5323145430766, 74.6632613306865);
        DirectPositionList directPositionList3 = new DirectPositionList();
        directPositionList3.setValue(posList3);
        directPositionList3.setSrsDimension(3);
        LinearRing linearRing3 = new LinearRing();
        linearRing3.setPosList(directPositionList3);
        linearRing3.setId("GEOM_6398772_0_");
        Exterior exterior3 = new Exterior(linearRing3);
        Polygon polygon3 = new Polygon();
        polygon3.setExterior(exterior3);
        polygon3.setId("GEOM_6398772");
        SurfaceProperty surfaceProperty3 = new SurfaceProperty(polygon3);
        multiSurface3.addSurfaceMember(surfaceProperty3);

        List<Double> posList4 = Arrays.asList(13.3269637958378, 52.5323117082961, 75.0136529114097, 13.3272392942551, 52.5323149163834, 75.0132576012984, 13.327239676004, 52.5322588109418, 75.0134577443823, 13.3269641779378, 52.5322556028559, 75.0138530535623, 13.3269637958378, 52.5323117082961, 75.0136529114097);
        DirectPositionList directPositionList4 = new DirectPositionList();
        directPositionList4.setValue(posList4);
        directPositionList4.setSrsDimension(3);
        LinearRing linearRing4 = new LinearRing();
        linearRing4.setPosList(directPositionList4);
        linearRing4.setId("GEOM_6398773_0_");
        Exterior exterior4 = new Exterior(linearRing4);
        Polygon polygon4 = new Polygon();
        polygon4.setExterior(exterior4);
        polygon4.setId("GEOM_6398773");
        SurfaceProperty surfaceProperty4 = new SurfaceProperty(polygon4);
        multiSurface3.addSurfaceMember(surfaceProperty4);

        MultiSurfaceProperty multiSurfaceProperty3 = new MultiSurfaceProperty(multiSurface3);
        groundSurface.setLod2MultiSurface(multiSurfaceProperty3);
        BoundarySurfaceProperty boundarySurfaceProperty3 = new BoundarySurfaceProperty(groundSurface);
        b1.addBoundedBySurface(boundarySurfaceProperty3);


        b3 = new Building();
        b3.setId("1");
        Point lowerCorner3 = new Point(13.3269638686746, 52.5321790563424, 74.6634593922645);
        Point upperCorner3 = new Point(13.3274413933907, 52.5323175874323, 90.3453739183024);
        BoundingBox boundingBox3 = new BoundingBox(lowerCorner3, upperCorner3);
        Envelope envelope3 = new Envelope(boundingBox3);
        envelope3.setSrsName("urn:ogc:def:crs:EPSG::4326");
        envelope3.setSrsDimension(3);
        BoundingShape boundingShape3 = new BoundingShape(envelope3);
        b3.setBoundedBy(boundingShape3);


        WallSurface wallSurfaceB = new WallSurface();
        MultiSurface multiSurface5 = new MultiSurface();
        List<Double> posList5 = Arrays.asList(13.3274413761895, 52.532182792233, 74.6634503705427, 13.3274413775653, 52.5321827935465, 79.210948058404, 13.3272423407462, 52.5321797602721, 79.2112362170592, 13.3272423393704, 52.5321797589586, 74.6637385291979, 13.3274413761895, 52.532182792233, 74.6634503705427);
        DirectPositionList directPositionList5 = new DirectPositionList();
        directPositionList5.setValue(posList5);
        directPositionList5.setSrsDimension(3);
        LinearRing linearRing5 = new LinearRing();
        linearRing5.setPosList(directPositionList5);
        linearRing5.setId("GEOM_6398777_0__SHIFTED");
        Exterior exterior5 = new Exterior(linearRing5);
        Polygon polygon5 = new Polygon();
        polygon5.setExterior(exterior5);
        polygon5.setId("GEOM_6398777_SHIFTED");
        SurfaceProperty surfaceProperty5 = new SurfaceProperty(polygon5);
        multiSurface5.addSurfaceMember(surfaceProperty5);

        List<Double> posList6 = Arrays.asList(13.3272388341963, 52.5323145430766, 74.6632613306865, 13.3272388355721, 52.5323145443901, 79.2107590194792, 13.3274378730004, 52.5323175776712, 79.2104708570987, 13.3274378716246, 52.5323175763577, 74.6629731673747, 13.3272388341963, 52.5323145430766, 74.6632613306865);
        DirectPositionList directPositionList6 = new DirectPositionList();
        directPositionList6.setValue(posList6);
        directPositionList6.setSrsDimension(3);
        LinearRing linearRing6 = new LinearRing();
        linearRing6.setPosList(directPositionList6);
        linearRing6.setId("GEOM_6398778_0__SHIFTED");
        Exterior exterior6 = new Exterior(linearRing6);
        Polygon polygon6 = new Polygon();
        polygon6.setExterior(exterior6);
        polygon6.setId("GEOM_6398778_SHIFTED");
        SurfaceProperty surfaceProperty6 = new SurfaceProperty(polygon6);
        multiSurface5.addSurfaceMember(surfaceProperty6);

        MultiSurfaceProperty multiSurfaceProperty5 = new MultiSurfaceProperty(multiSurface5);
        wallSurfaceB.setLod2MultiSurface(multiSurfaceProperty5);
        BoundarySurfaceProperty boundarySurfaceProperty5 = new BoundarySurfaceProperty(wallSurfaceB);
        b3.addBoundedBySurface(boundarySurfaceProperty5);

        GroundSurface groundSurfaceB = new GroundSurface();
        MultiSurface multiSurface7 = new MultiSurface();
        List<Double> posList7 = Arrays.asList(13.3272388341963, 52.5323145430766, 74.6632613306865, 13.3274378716246, 52.5323175763577, 74.6629731673747, 13.3274385725457, 52.5322906192891, 74.6630686083809, 13.3274413761895, 52.532182792233, 74.6634503705427, 13.3272423393704, 52.5321797589586, 74.6637385291979, 13.3272395352393, 52.5322875860094, 74.6633567716926, 13.3272388341963, 52.5323145430766, 74.6632613306865);
        DirectPositionList directPositionList7 = new DirectPositionList();
        directPositionList7.setValue(posList7);
        directPositionList7.setSrsDimension(3);
        LinearRing linearRing7 = new LinearRing();
        linearRing7.setPosList(directPositionList7);
        linearRing7.setId("GEOM_6398772_0__SHIFTED");
        Exterior exterior7 = new Exterior(linearRing7);
        Polygon polygon7 = new Polygon();
        polygon7.setExterior(exterior7);
        polygon7.setId("GEOM_6398772_SHIFTED");
        SurfaceProperty surfaceProperty7 = new SurfaceProperty(polygon7);
        multiSurface7.addSurfaceMember(surfaceProperty7);

        List<Double> posList8 = Arrays.asList(13.3269638958378, 52.5323117082961, 75.0136529114097, 13.3272393942551, 52.5323149163834, 75.0132576012984, 13.327239776004, 52.5322588109418, 75.0134577443823, 13.3269642779378, 52.5322556028559, 75.0138530535623, 13.3269638958378, 52.5323117082961, 75.0136529114097);
        DirectPositionList directPositionList8 = new DirectPositionList();
        directPositionList8.setValue(posList8);
        directPositionList8.setSrsDimension(3);
        LinearRing linearRing8 = new LinearRing();
        linearRing8.setPosList(directPositionList8);
        linearRing8.setId("GEOM_6398773_0__SHIFTED");
        Exterior exterior8 = new Exterior(linearRing8);
        Polygon polygon8 = new Polygon();
        polygon8.setExterior(exterior8);
        polygon8.setId("GEOM_6398773_SHIFTED");
        SurfaceProperty surfaceProperty8 = new SurfaceProperty(polygon8);
        multiSurface7.addSurfaceMember(surfaceProperty8);

        MultiSurfaceProperty multiSurfaceProperty7 = new MultiSurfaceProperty(multiSurface7);
        groundSurfaceB.setLod2MultiSurface(multiSurfaceProperty7);
        BoundarySurfaceProperty boundarySurfaceProperty7 = new BoundarySurfaceProperty(groundSurfaceB);
        b3.addBoundedBySurface(boundarySurfaceProperty7);
    }

    @Test
    public void testMatchBuildingsChildren() {
        initObjects();
        Map<Polygon, Polygon> polygonPolygonMap = BuildingWallMatcher.matchBuildingsBoundarySurfaces(b1, b3);

        assertEquals(4, polygonPolygonMap.size());

        for (Map.Entry<Polygon, Polygon> polygonPolygonEntry : polygonPolygonMap.entrySet()) {
            String id1 = polygonPolygonEntry.getKey().getId();
            String id2 = polygonPolygonEntry.getValue().getId();

            assertEquals(id1 + "_SHIFTED", id2);
        }
    }

}
