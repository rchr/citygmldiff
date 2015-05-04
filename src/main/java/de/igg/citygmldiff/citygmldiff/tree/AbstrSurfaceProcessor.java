package de.igg.citygmldiff.citygmldiff.tree;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes.CompositeSurfaceHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.*;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.citygml.texturedsurface._TexturedSurface;
import org.citygml4j.model.gml.geometry.complexes.CompositeSurface;
import org.citygml4j.model.gml.geometry.primitives.*;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 14:40
 */
public class AbstrSurfaceProcessor<T extends AbstractSurface> {

    private static CompositeSurfaceHandler<CompositeSurface> compositeSurfaceHandler = new CompositeSurfaceHandler<>();
    private static OrientableSurfaceHandler<OrientableSurface> orientableSurfaceHandler = new OrientableSurfaceHandler<>();
    private static PolygonHandler<Polygon> polygonHandler = new PolygonHandler<>();
    private static SurfaceHandler<Surface> surfaceHandler = new SurfaceHandler<>();
    private static TinHandler<Tin> tinHandler = new TinHandler<>();
    private static TriangulatedSurfaceHandler<TriangulatedSurface> triangulatedSurfaceHandler = new TriangulatedSurfaceHandler<>();

    public void processAbstractGeometry(T geometry, ElementNode<?> parent) {


        if (geometry instanceof CompositeSurface) {
            CompositeSurface compositeSurface = (CompositeSurface) geometry;
            proecessCompositeSurface(parent, compositeSurface);
        } else if (geometry instanceof OrientableSurface) {
            OrientableSurface orientableSurface = (OrientableSurface) geometry;
            processOrientableSurface(parent, orientableSurface);
        } else if (geometry instanceof Polygon) {
            Polygon polygon = (Polygon) geometry;
            processPolygon(parent, polygon);
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

    private void processOrientableSurface(ElementNode<?> parent, OrientableSurface orientableSurface) {
        ElementNode<OrientableSurface> orientableSurfaceElementNode = NodeFactory.createElementNode(orientableSurface, "orienatableSurface", parent);
        orientableSurfaceHandler.addChildsToNode(orientableSurfaceElementNode);
        parent.addChild(orientableSurfaceElementNode);
    }

    private void proecessCompositeSurface(ElementNode<?> node, CompositeSurface compositeSurface) {
        ElementNode<CompositeSurface> compositeSurfaceElementNode = NodeFactory.createElementNode(compositeSurface, "compositeSurface", node);
        compositeSurfaceHandler.addChildsToNode(compositeSurfaceElementNode);
        node.addChild(compositeSurfaceElementNode);
    }

    private void processPolygon(ElementNode<?> node, Polygon polygon) {
        ElementNode<Polygon> polygonNode = NodeFactory.createElementNode(polygon, "polygon", node);
        polygonHandler.addChildsToNode(polygonNode);
        node.addChild(polygonNode);
    }
}
