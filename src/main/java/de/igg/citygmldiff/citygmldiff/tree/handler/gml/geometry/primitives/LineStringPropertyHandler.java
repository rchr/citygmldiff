package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.primitives.LineString;
import org.citygml4j.model.gml.geometry.primitives.LineStringProperty;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 11:37
 */
public class LineStringPropertyHandler<T extends LineStringProperty> implements TreeNodeHandler<T> {

    private static LineStringHandler<LineString> lineStringHandler = new LineStringHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        LineString lineString = element.getLineString();
        if (lineString == null) {
            Utils.addXlinkFeature(node);
        } else if (lineString != null) {
            ElementNode<LineString> lineStringElementNode = NodeFactory.createElementNode(lineString, "lineString", node);
            lineStringHandler.addChildsToNode(lineStringElementNode);
            node.addChild(lineStringElementNode);
        }
    }
}
