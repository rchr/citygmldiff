package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.primitives.Point;
import org.citygml4j.model.gml.geometry.primitives.PointProperty;

public class PointPropertyHandler<T extends PointProperty> implements
        TreeNodeHandler<T> {

    private static PointHandler<Point> pointHandler = new PointHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        Point point = element.getPoint();
        if (point == null) {
            Utils.addXlinkFeature(node);
        } else if (point != null) {
            ElementNode<Point> pointNode = NodeFactory.createElementNode(point,
                    "point", node);
            pointHandler.addChildsToNode(pointNode);
            node.addChild(pointNode);
        }
    }

}
