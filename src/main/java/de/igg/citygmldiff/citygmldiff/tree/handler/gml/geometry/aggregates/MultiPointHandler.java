package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.PointArrayPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.PointPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.aggregates.MultiPoint;
import org.citygml4j.model.gml.geometry.primitives.PointArrayProperty;
import org.citygml4j.model.gml.geometry.primitives.PointProperty;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 11:46
 */
public class MultiPointHandler<T extends MultiPoint> extends AbstractGeometricAggregateHandler<T> {

    private static PointPropertyHandler<PointProperty> pointPropertyHandler = new PointPropertyHandler<>();
    private static PointArrayPropertyHandler<PointArrayProperty> pointArrayPropertyHandler = new PointArrayPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<PointProperty> pointMember = element.getPointMember();
        if (pointMember != null) {
            processPointMember(pointMember, node);
        }

        PointArrayProperty pointMembers = element.getPointMembers();
        if (pointMembers != null) {
            ElementNode<PointArrayProperty> pointArrayPropertyElementNode = NodeFactory.createElementNode(pointMembers, "pointMembers", node);
            pointArrayPropertyHandler.addChildsToNode(pointArrayPropertyElementNode);
            node.addChild(pointArrayPropertyElementNode);
        }
    }

    private void processPointMember(List<PointProperty> pointMember, ElementNode<T> node) {
        for (PointProperty pointProperty : pointMember) {
            ElementNode<PointProperty> pointPropertyElementNode = NodeFactory.createElementNode(pointProperty, "pointProperty", node);
            pointPropertyHandler.addChildsToNode(pointPropertyElementNode);
            node.addChild(pointPropertyElementNode);
        }
    }
}
