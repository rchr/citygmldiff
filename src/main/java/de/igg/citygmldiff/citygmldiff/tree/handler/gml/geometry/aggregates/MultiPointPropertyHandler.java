package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.aggregates.MultiPoint;
import org.citygml4j.model.gml.geometry.aggregates.MultiPointProperty;

/**
 * User: richard
 * Date: 02.10.13
 * Time: 19:09
 */
public class MultiPointPropertyHandler<T extends MultiPointProperty> implements TreeNodeHandler<T>, Constants {

    private static MultiPointHandler<MultiPoint> multiPointHandler = new MultiPointHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();
        MultiPoint multiPoint = element.getMultiPoint();
        if (multiPoint == null) {
            Utils.addXlinkFeature(node);
        } else {
            ElementNode<MultiPoint> elementNode = NodeFactory.createElementNode(multiPoint, MULTI_POINT, node);
            multiPointHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

    }
}
