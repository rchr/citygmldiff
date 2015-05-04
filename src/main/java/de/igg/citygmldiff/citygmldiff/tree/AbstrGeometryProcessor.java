package de.igg.citygmldiff.citygmldiff.tree;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates.*;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes.CompositeCurveHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes.CompositeSolidHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes.CompositeSurfaceHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes.GeometricComplexHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.*;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.grids.GridHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.grids.RectifiedGridHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.citygml.texturedsurface._TexturedSurface;
import org.citygml4j.model.gml.geometry.AbstractGeometry;
import org.citygml4j.model.gml.geometry.aggregates.*;
import org.citygml4j.model.gml.geometry.complexes.CompositeCurve;
import org.citygml4j.model.gml.geometry.complexes.CompositeSolid;
import org.citygml4j.model.gml.geometry.complexes.CompositeSurface;
import org.citygml4j.model.gml.geometry.complexes.GeometricComplex;
import org.citygml4j.model.gml.geometry.primitives.*;
import org.citygml4j.model.gml.grids.Grid;
import org.citygml4j.model.gml.grids.RectifiedGrid;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 10:36
 */
public class AbstrGeometryProcessor<T extends AbstractGeometry> implements Constants {

    private static AbstrSurfaceProcessor<AbstractSurface> abstrSurfaceProcessor = new AbstrSurfaceProcessor<>();
    private static MultiCurveHandler<MultiCurve> multiCurveHandler = new MultiCurveHandler<>();
    private static MultiSurfaceHandler<MultiSurface> multiSurfaceHandler = new MultiSurfaceHandler<>();
    private static CompositeCurveHandler<CompositeCurve> compositeCurveHandler = new CompositeCurveHandler<>();
    private static CurveHandler<Curve> curveHandler = new CurveHandler<>();
    private static LinearRingHandler<LinearRing> linearRingHandler = new LinearRingHandler<>();
    private static OrientableCurveHandler<OrientableCurve> orientableCurveHandler = new OrientableCurveHandler<>();
    private static PointHandler<Point> pointHandler = new PointHandler<>();
    private static PolygonHandler<Polygon> polygonHandler = new PolygonHandler<>();
    private static SolidHandler<Solid> solidHandler = new SolidHandler<>();
    private static LineStringHandler<LineString> lineStringHandler = new LineStringHandler<>();
    private static CompositeSolidHandler<CompositeSolid> compositeSolidHandler = new CompositeSolidHandler<>();
    private static CompositeSurfaceHandler<CompositeSurface> compositeSurfaceHandler = new CompositeSurfaceHandler<>();
    private static GeometricComplexHandler<GeometricComplex> geometricComplexHandler = new GeometricComplexHandler<>();
    private static GridHandler<Grid> gridHandler = new GridHandler<>();
    private static MultiGeometryHandler<MultiGeometry> multiGeometryHandler = new MultiGeometryHandler<>();
    private static MultiLineStringHandler<MultiLineString> multiLineStringHandler = new MultiLineStringHandler<>();
    private static MultiPointHandler<MultiPoint> multiPointHandler = new MultiPointHandler<>();
    private static MultiPolygonHandler<MultiPolygon> multiPolygonHandler = new MultiPolygonHandler<>();
    private static MultiSolidHandler<MultiSolid> multiSolidHandler = new MultiSolidHandler<>();
    private static OrientableSurfaceHandler<OrientableSurface> orientableSurfaceHandler = new OrientableSurfaceHandler<>();
    private static RectifiedGridHandler<RectifiedGrid> rectifiedGridHandler = new RectifiedGridHandler<>();
    private static RingHandler<Ring> ringHandler = new RingHandler<>();
    private static SurfaceHandler<Surface> surfaceHandler = new SurfaceHandler<>();
    private static TinHandler<Tin> tinHandler = new TinHandler<>();
    private static TriangulatedSurfaceHandler<TriangulatedSurface> triangulatedSurfaceHandler = new TriangulatedSurfaceHandler<>();
    private static AbstrGeometryProcessor<AbstractGeometricPrimitive> geometricPrimitiveProcessor = new AbstrGeometryProcessor<>();

    public void processAbstractGeoemtry(T geometry, ElementNode<?> parent) {
        if (geometry instanceof AbstractCurve) {
            AbstractCurve abstractCurve = (AbstractCurve) geometry;
            processAbstractCurve(parent, abstractCurve);
        } else if (geometry instanceof AbstractGeometricAggregate) {
            AbstractGeometricAggregate abstractGeometricAggregate = (AbstractGeometricAggregate) geometry;
            processAbstractGeometricAggregate(parent, abstractGeometricAggregate);
        } else if (geometry instanceof AbstractGeometricPrimitive) {
            AbstractGeometricPrimitive abstractGeometricPrimitive = (AbstractGeometricPrimitive) geometry;
            processAbstractGeometricPrimitive(parent, abstractGeometricPrimitive);
        } else if (geometry instanceof AbstractRing) {
            AbstractRing abstractRing = (AbstractRing) geometry;
            processAbstractRing(parent, abstractRing);
        } else if (geometry instanceof AbstractSolid) {
            AbstractSolid abstractSolid = (AbstractSolid) geometry;
            processAbstractSolid(parent, abstractSolid);
        } else if (geometry instanceof AbstractSurface) {
            AbstractSurface abstractSurface = (AbstractSurface) geometry;
            processAbstractSurface(parent, abstractSurface);
        } else if (geometry instanceof CompositeCurve) {
            CompositeCurve compoCurve = (CompositeCurve) geometry;
            processCompositeCurve(parent, compoCurve);
        } else if (geometry instanceof CompositeSolid) {
            CompositeSolid compoSolid = (CompositeSolid) geometry;
            processCompositeSolid(parent, compoSolid);
        } else if (geometry instanceof CompositeSurface) {
            CompositeSurface compositeSurface = (CompositeSurface) geometry;
            proecessCompositeSurface(parent, compositeSurface);
        } else if (geometry instanceof Curve) {
            Curve curve = (Curve) geometry;
            processCurve(parent, curve);
        } else if (geometry instanceof GeometricComplex) {
            GeometricComplex geometricComplex = (GeometricComplex) geometry;
            processGeometricComplex(parent, geometricComplex);
        } else if (geometry instanceof Grid) {
            Grid grid = (Grid) geometry;
            processGrid(parent, grid);
        } else if (geometry instanceof LineString) {
            LineString lineString = (LineString) geometry;
            processLineString(parent, lineString);
        } else if (geometry instanceof LinearRing) {
            LinearRing linearRing = (LinearRing) geometry;
            processLinearRing(parent, linearRing);
        } else if (geometry instanceof MultiCurve) {
            MultiCurve multiCurve = (MultiCurve) geometry;
            processMultiCurve(parent, multiCurve);
        } else if (geometry instanceof MultiGeometry) {
            MultiGeometry multiGeometry = (MultiGeometry) geometry;
            processMultiGeometry(parent, multiGeometry);
        } else if (geometry instanceof MultiLineString) {
            MultiLineString multiLineString = (MultiLineString) geometry;
            processMultiLineString(parent, multiLineString);
        } else if (geometry instanceof MultiPoint) {
            MultiPoint multiPoint = (MultiPoint) geometry;
            processMultiPoint(parent, multiPoint);
        } else if (geometry instanceof MultiPolygon) {
            MultiPolygon multiPolygon = (MultiPolygon) geometry;
            processMultiPolygon(parent, multiPolygon);
        } else if (geometry instanceof MultiSolid) {
            MultiSolid multiSolid = (MultiSolid) geometry;
            processMultiSolid(parent, multiSolid);
        } else if (geometry instanceof MultiSurface) {
            MultiSurface multiSurface = (MultiSurface) geometry;
            processMultiSurface(parent, multiSurface);
        } else if (geometry instanceof OrientableCurve) {
            OrientableCurve orientableCurve = (OrientableCurve) geometry;
            processOrientableCurve(parent, orientableCurve);
        } else if (geometry instanceof OrientableSurface) {
            OrientableSurface orientableSurface = (OrientableSurface) geometry;
            processOrientableSurface(parent, orientableSurface);
        } else if (geometry instanceof Point) {
            Point point = (Point) geometry;
            processPoint(parent, point);
        } else if (geometry instanceof Polygon) {
            Polygon polygon = (Polygon) geometry;
            processPolygon(parent, polygon);
        } else if (geometry instanceof RectifiedGrid) {
            RectifiedGrid rectifiedGrid = (RectifiedGrid) geometry;
            processRectifiedGrid(parent, rectifiedGrid);
        } else if (geometry instanceof Ring) {
            Ring ring = (Ring) geometry;
            processRing(parent, ring);
        } else if (geometry instanceof Solid) {
            Solid solid = (Solid) geometry;
            processSolid(parent, solid);
        } else if (geometry instanceof Surface) {
            Surface surface = (Surface) geometry;
            processSurface(parent, surface);
        } else if (geometry instanceof Tin) {
            Tin tin = (Tin) geometry;
            processTin(parent, tin);
        } else if (geometry instanceof TriangulatedSurface) {
            TriangulatedSurface triangulatedSurface = (TriangulatedSurface) geometry;
            processTriangulatesSurface(parent, triangulatedSurface);
        } else if (geometry instanceof _TexturedSurface) {

        }
    }

    private void processTriangulatesSurface(ElementNode<?> parent, TriangulatedSurface triangulatedSurface) {
        ElementNode<TriangulatedSurface> triangulatedSurfaceElementNode = NodeFactory.createElementNode(triangulatedSurface, "triangulatedSurface", parent);
        triangulatedSurfaceHandler.addChildsToNode(triangulatedSurfaceElementNode);
        parent.addChild(triangulatedSurfaceElementNode);
    }

    private void processTin(ElementNode<?> parent, Tin tin) {
        ElementNode<Tin> tinNode = NodeFactory.createElementNode(tin, "tin", parent);
        tinHandler.addChildsToNode(tinNode);
        tinNode.addChild(tinNode);
    }

    private void processSurface(ElementNode<?> parent, Surface surface) {
        ElementNode<Surface> surfaceElementNode = NodeFactory.createElementNode(surface, "surface", parent);
        surfaceHandler.addChildsToNode(surfaceElementNode);
        parent.addChild(surfaceElementNode);
    }

    private void processRing(ElementNode<?> parent, Ring ring) {
        ElementNode<Ring> ringNode = NodeFactory.createElementNode(ring, "ring", parent);
        ringHandler.addChildsToNode(ringNode);
        parent.addChild(ringNode);
    }

    private void processRectifiedGrid(ElementNode<?> parent, RectifiedGrid rectifiedGrid) {
        ElementNode<RectifiedGrid> rectifiedGridElementNode = NodeFactory.createElementNode(rectifiedGrid, "recifiedGrid", parent);
        rectifiedGridHandler.addChildsToNode(rectifiedGridElementNode);
        rectifiedGridElementNode.addChild(rectifiedGridElementNode);
    }

    private void processOrientableSurface(ElementNode<?> parent, OrientableSurface orientableSurface) {
        ElementNode<OrientableSurface> orientableSurfaceElementNode = NodeFactory.createElementNode(orientableSurface, "orienatableSurface", parent);
        orientableSurfaceHandler.addChildsToNode(orientableSurfaceElementNode);
        parent.addChild(orientableSurfaceElementNode);
    }

    private void processMultiSolid(ElementNode<?> parent, MultiSolid multiSolid) {
        ElementNode<MultiSolid> multiSolidNode = NodeFactory.createElementNode(multiSolid, "multiSolid", parent);
        multiSolidHandler.addChildsToNode(multiSolidNode);
        parent.addChild(multiSolidNode);
    }

    private void processMultiPolygon(ElementNode<?> parent, MultiPolygon multiPolygon) {
        ElementNode<MultiPolygon> multiPolygonElementNode = NodeFactory.createElementNode(multiPolygon, "multiPolygon", parent);
        multiPolygonHandler.addChildsToNode(multiPolygonElementNode);
        parent.addChild(multiPolygonElementNode);
    }

    private void processMultiPoint(ElementNode<?> parent, MultiPoint multiPoint) {
        ElementNode<MultiPoint> multiPointElementNode = NodeFactory.createElementNode(multiPoint, "multiPoint", parent);
        multiPointHandler.addChildsToNode(multiPointElementNode);
        parent.addChild(multiPointElementNode);
    }

    private void processMultiLineString(ElementNode<?> parent, MultiLineString multiLineString) {
        ElementNode<MultiLineString> multiLineStringElementNode = NodeFactory.createElementNode(multiLineString, "multiLineString", parent);
        multiLineStringHandler.addChildsToNode(multiLineStringElementNode);
        parent.addChild(multiLineStringElementNode);
    }

    private void processMultiGeometry(ElementNode<?> parent, MultiGeometry multiGeometry) {
        ElementNode<MultiGeometry> multiGeometryElementNode = NodeFactory.createElementNode(multiGeometry, "multiGeometry", parent);
        multiGeometryHandler.addChildsToNode(multiGeometryElementNode);
        parent.addChild(multiGeometryElementNode);
    }

    private void processGrid(ElementNode<?> node, Grid grid) {
        ElementNode<Grid> gridNode = NodeFactory.createElementNode(grid, "grid", node);
        gridHandler.addChildsToNode(gridNode);
        gridNode.addChild(gridNode);
    }

    private void processGeometricComplex(ElementNode<?> node, GeometricComplex geometricComplex) {
        ElementNode<GeometricComplex> geometricComplexElementNode = NodeFactory.createElementNode(geometricComplex, "geometricComplex", node);
        geometricComplexHandler.addChildsToNode(geometricComplexElementNode);
        node.addChild(geometricComplexElementNode);
    }

    private void proecessCompositeSurface(ElementNode<?> node, CompositeSurface compositeSurface) {
        ElementNode<CompositeSurface> compositeSurfaceElementNode = NodeFactory.createElementNode(compositeSurface, COMPOSITE_SURFACE, node);
        compositeSurfaceHandler.addChildsToNode(compositeSurfaceElementNode);
        node.addChild(compositeSurfaceElementNode);
    }

    private void processCompositeSolid(ElementNode<?> node, CompositeSolid compoSolid) {
        ElementNode<CompositeSolid> compoNode = NodeFactory.createElementNode(compoSolid, "compositeSolid", node);
        compositeSolidHandler.addChildsToNode(compoNode);
        node.addChild(compoNode);
    }

    private void processAbstractSurface(ElementNode<?> node, AbstractSurface abstractSurface) {
        abstrSurfaceProcessor.processAbstractGeometry(abstractSurface, node);
    }

    private void processAbstractCurve(ElementNode<?> node, AbstractCurve abstractCurve) {
        if (abstractCurve instanceof CompositeCurve) {
            CompositeCurve compositeCurve = (CompositeCurve) abstractCurve;
            processCompositeCurve(node, compositeCurve);
        } else if (abstractCurve instanceof Curve) {
            Curve curve = (Curve) abstractCurve;
            processCurve(node, curve);
        } else if (abstractCurve instanceof LineString) {
            LineString lineString = (LineString) abstractCurve;
            processLineString(node, lineString);
        } else if (abstractCurve instanceof OrientableCurve) {
            OrientableCurve orientableCurve = (OrientableCurve) abstractCurve;
            processOrientableCurve(node, orientableCurve);
        }
    }

    private void processLineString(ElementNode<?> node, LineString lineString) {
        ElementNode<LineString> lineStringNode = NodeFactory.createElementNode(lineString, "lineString", node);
        lineStringHandler.addChildsToNode(lineStringNode);
        node.addChild(lineStringNode);
    }

    private void processAbstractSolid(ElementNode<?> node, AbstractSolid abstractSolid) {
        if (abstractSolid instanceof Solid) {
            Solid solid = (Solid) abstractSolid;
            processSolid(node, solid);
        } else if (abstractSolid instanceof CompositeSolid) {

        }
    }

    private void processAbstractRing(ElementNode<?> node, AbstractRing abstractRing) {
        if (abstractRing instanceof LinearRing) {
            LinearRing linearRing = (LinearRing) abstractRing;
            processLinearRing(node, linearRing);
        } else if (abstractRing instanceof Ring) {

        }

    }

    private void processSolid(ElementNode<?> node, Solid solid) {
        ElementNode<Solid> solidNode = NodeFactory.createElementNode(solid, "solid", node);
        solidHandler.addChildsToNode(solidNode);
        node.addChild(solidNode);
    }

    private void processPolygon(ElementNode<?> node, Polygon polygon) {
        ElementNode<Polygon> polygonNode = NodeFactory.createElementNode(polygon, "polygon", node);
        polygonHandler.addChildsToNode(polygonNode);
        node.addChild(polygonNode);
    }

    private void processPoint(ElementNode<?> node, Point point) {
        ElementNode<Point> pointNode = NodeFactory.createElementNode(point, "point", node);
        pointHandler.addChildsToNode(pointNode);
        node.addChild(pointNode);
    }

    private void processOrientableCurve(ElementNode<?> node, OrientableCurve orientableCurve) {
        ElementNode<OrientableCurve> orientableCurveNode = NodeFactory.createElementNode(orientableCurve, "orientableCurve", node);
        orientableCurveHandler.addChildsToNode(orientableCurveNode);
        node.addChild(orientableCurveNode);
    }

    private void processMultiSurface(ElementNode<?> node, MultiSurface multiSurface) {
        ElementNode<MultiSurface> multiSurfaceNode = NodeFactory.createElementNode(multiSurface, "multiSurface", node);
        multiSurfaceHandler.addChildsToNode(multiSurfaceNode);
        node.addChild(multiSurfaceNode);
    }

    private void processMultiCurve(ElementNode<?> node, MultiCurve multiCurve) {
        ElementNode<MultiCurve> curveNode = NodeFactory.createElementNode(multiCurve, "multiCurve", node);
        multiCurveHandler.addChildsToNode(curveNode);
        node.addChild(curveNode);
    }

    private void processLinearRing(ElementNode<?> node, LinearRing linearRing) {
        ElementNode<LinearRing> ringNode = NodeFactory.createElementNode(linearRing, Constants.LINEAR_RING, node);
        linearRingHandler.addChildsToNode(ringNode);
        node.addChild(ringNode);
    }

    private void processCurve(ElementNode<?> node, Curve curve) {
        ElementNode<Curve> curveNode = NodeFactory.createElementNode(curve, "curve", node);
        curveHandler.addChildsToNode(curveNode);
        node.addChild(curveNode);
    }

    private void processCompositeCurve(ElementNode<?> node, CompositeCurve compoCurve) {
        ElementNode<CompositeCurve> compoCurveNode = NodeFactory.createElementNode(compoCurve, "compositeCurve", node);
        compositeCurveHandler.addChildsToNode(compoCurveNode);
        node.addChild(compoCurveNode);
    }

    private void processAbstractGeometricPrimitive(ElementNode<?> node, AbstractGeometricPrimitive abstractGeometricPrimitive) {
        geometricPrimitiveProcessor.processAbstractGeoemtry(abstractGeometricPrimitive, node);
    }

    private void processAbstractGeometricAggregate(ElementNode<?> node, AbstractGeometricAggregate abstractGeometricAggregate) {
        if (abstractGeometricAggregate instanceof MultiCurve) {
            MultiCurve multiCurve = (MultiCurve) abstractGeometricAggregate;
            processMultiCurve(node, multiCurve);
        } else if (abstractGeometricAggregate instanceof MultiGeometry) {
            MultiGeometry multiGeometry = (MultiGeometry) abstractGeometricAggregate;
            processMultiGeometry(node, multiGeometry);
        } else if (abstractGeometricAggregate instanceof MultiLineString) {
            MultiLineString multiLineString = (MultiLineString) abstractGeometricAggregate;
            processMultiLineString(node, multiLineString);
        } else if (abstractGeometricAggregate instanceof MultiPoint) {
            MultiPoint multiPoint = (MultiPoint) abstractGeometricAggregate;
            processMultiPoint(node, multiPoint);
        } else if (abstractGeometricAggregate instanceof MultiPolygon) {
            MultiPolygon multiPolygon = (MultiPolygon) abstractGeometricAggregate;
            processMultiPolygon(node, multiPolygon);
        } else if (abstractGeometricAggregate instanceof MultiSolid) {
            MultiSolid multiSolid = (MultiSolid) abstractGeometricAggregate;
            processMultiSolid(node, multiSolid);
        } else if (abstractGeometricAggregate instanceof MultiSurface) {
            MultiSurface multiSurface = (MultiSurface) abstractGeometricAggregate;
            processMultiSurface(node, multiSurface);
        }
    }
}
