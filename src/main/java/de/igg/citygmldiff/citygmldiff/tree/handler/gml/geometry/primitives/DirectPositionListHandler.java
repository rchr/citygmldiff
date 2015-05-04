package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.SRSReferenceGroupAttributeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.DirectPositionList;

import java.util.List;

/**
 * User: richard Date: 07.08.13 Time: 13:00
 */
public class DirectPositionListHandler<T extends DirectPositionList> extends
        SRSReferenceGroupAttributeHandler<T> implements
        TreeNodeHandler<T> {

    private static final String COUNT = "count";

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addAttributesToElementNode(node);

        T element = node.getValue();

        Integer count = element.getCount();
        if (count != null) {
            AttributeNode countNode = NodeFactory.createAttributeNode(COUNT, String.valueOf(count), node);
            node.addChild(countNode);
        }

        List<Double> posValues = element.getValue();
        String positions = "";
        for (Double p : posValues) {
            positions += String.valueOf(p) + " ";
        }
        TextNode posNode = new TextNode(positions, node);
        node.addChild(posNode);

    }

}
