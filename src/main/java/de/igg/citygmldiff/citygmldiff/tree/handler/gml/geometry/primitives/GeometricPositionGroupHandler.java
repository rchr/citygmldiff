package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.DirectPosition;
import org.citygml4j.model.gml.geometry.primitives.GeometricPositionGroup;
import org.citygml4j.model.gml.geometry.primitives.PointProperty;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 16:21
 */
public class GeometricPositionGroupHandler<T extends GeometricPositionGroup> implements TreeNodeHandler<T> {

    private static DirectPositionHandler<DirectPosition> directPositionHandler = new DirectPositionHandler<>();
    private static PointPropertyHandler<PointProperty> pointPropertyHandler = new PointPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        DirectPosition pos = element.getPos();
        if (pos != null) {
            ElementNode<DirectPosition> directPositionElementNode = NodeFactory.createElementNode(pos, "pos", node);
            directPositionHandler.addChildsToNode(directPositionElementNode);
            node.addChild(directPositionElementNode);
        }

        PointProperty pointProperty = element.getPointProperty();
        if (pointProperty != null) {
            ElementNode<PointProperty> pointPropertyElementNode = NodeFactory.createElementNode(pointProperty, "pointProperty", node);
            pointPropertyHandler.addChildsToNode(pointPropertyElementNode);
            node.addChild(pointPropertyElementNode);
        }

    }
}
