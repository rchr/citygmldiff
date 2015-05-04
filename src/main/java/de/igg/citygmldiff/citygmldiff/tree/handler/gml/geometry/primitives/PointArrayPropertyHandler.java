package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.Point;
import org.citygml4j.model.gml.geometry.primitives.PointArrayProperty;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 11:50
 */
public class PointArrayPropertyHandler<T extends PointArrayProperty> implements TreeNodeHandler<T> {

    private static PointHandler<Point> pointHandler = new PointHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        List<Point> points = element.getPoint();
        if (points != null) {
            processPoints(points, node);
        }
    }

    private void processPoints(List<Point> points, ElementNode<T> node) {
        for (Point p : points) {
            ElementNode<Point> pointElementNode = NodeFactory.createElementNode(p, "point", node);
            pointHandler.addChildsToNode(pointElementNode);
            node.addChild(pointElementNode);
        }
    }
}
