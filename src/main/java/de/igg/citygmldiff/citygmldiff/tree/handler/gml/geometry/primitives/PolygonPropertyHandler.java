package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.primitives.Polygon;
import org.citygml4j.model.gml.geometry.primitives.PolygonProperty;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 12:22
 */
public class PolygonPropertyHandler<T extends PolygonProperty> implements TreeNodeHandler<T> {

    private static PolygonHandler<Polygon> polygonHandler = new PolygonHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        Polygon polygon = element.getPolygon();
        if (polygon == null) {
            Utils.addXlinkFeature(node);
        } else if (polygon != null) {
            ElementNode<Polygon> polygonElementNode = NodeFactory.createElementNode(polygon, "polygon", node);
            polygonHandler.addChildsToNode(polygonElementNode);
            node.addChild(polygonElementNode);
        }
    }
}
