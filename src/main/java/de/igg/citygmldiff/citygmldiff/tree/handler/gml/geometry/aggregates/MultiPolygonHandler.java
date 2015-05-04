package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.PolygonPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.aggregates.MultiPolygon;
import org.citygml4j.model.gml.geometry.primitives.PolygonProperty;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 12:14
 */
public class MultiPolygonHandler<T extends MultiPolygon> extends AbstractGeometricAggregateHandler<T> implements Constants {

    private static PolygonPropertyHandler<PolygonProperty> polygonPropertyHandler = new PolygonPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<PolygonProperty> polygonMember = element.getPolygonMember();
        if (polygonMember != null) {
            processPolygonMembers(polygonMember, node);
        }
    }

    private void processPolygonMembers(List<PolygonProperty> polygonMember, ElementNode<T> node) {
        for (PolygonProperty polygonProperty : polygonMember) {
            ElementNode<PolygonProperty> polygonPropertyElementNode = NodeFactory.createElementNode(polygonProperty, POLYGON_MEMBER, node);
            polygonPropertyHandler.addChildsToNode(polygonPropertyElementNode);
            node.addChild(polygonPropertyElementNode);
        }
    }
}
