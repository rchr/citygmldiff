package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.AbstractSurfacePatch;
import org.citygml4j.model.gml.geometry.primitives.Rectangle;
import org.citygml4j.model.gml.geometry.primitives.SurfacePatchArrayProperty;
import org.citygml4j.model.gml.geometry.primitives.Triangle;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 14:36
 */
public class SurfacePatchArrayPropertyHandler<T extends SurfacePatchArrayProperty> implements TreeNodeHandler<T> {

    private static RectangleHandler<Rectangle> rectangleHandler = new RectangleHandler<>();
    private static TriangleHandler<Triangle> triangleHandler = new TriangleHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        List<? extends AbstractSurfacePatch> surfaces = element.getSurfacePatch();
        if (surfaces != null) {
            processSurfacePatches(surfaces, node);
        }
    }

    private void processSurfacePatches(List<? extends AbstractSurfacePatch> surfacePatches, ElementNode<T> node) {
        for (AbstractSurfacePatch surfacePatch : surfacePatches) {
            if (surfacePatch instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) surfacePatch;
                ElementNode<Rectangle> rectangleElementNode = NodeFactory.createElementNode(rectangle, "Recatangle", node);
                rectangleHandler.addChildsToNode(rectangleElementNode);
                node.addChild(rectangleElementNode);
            } else if (surfacePatch instanceof Triangle) {
                Triangle triangle = (Triangle) surfacePatch;
                ElementNode<Triangle> triangleNode = NodeFactory.createElementNode(triangle, "Triangle", node);
                triangleHandler.addChildsToNode(triangleNode);
                node.addChild(triangleNode);
            }
        }
    }
}
