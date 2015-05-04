package de.igg.citygmldiff.citygmldiff.tree.handler.gml.basicTypes;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.basicTypes.Coordinates;

public class CoordinatesHandler<T extends Coordinates> implements
        TreeNodeHandler<T>, Constants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        // attributes
        String decimal = element.getDecimal();
        if (decimal != null) {
            AttributeNode decimalNode = NodeFactory.createAttributeNode(decimal,
                    DECIMAL, node);
            node.addChild(decimalNode);
        }

        String cs = element.getCs();
        if (cs != null) {
            AttributeNode csNode = NodeFactory.createAttributeNode(cs, CS, node);
            node.addChild(csNode);
        }

        String ts = element.getTs();
        if (ts != null) {
            AttributeNode tsNode = NodeFactory.createAttributeNode(ts, TS, node);
            node.addChild(tsNode);
        }

        // values
        String value = element.getValue();
        if (value != null) {
            TextNode valueNode = new TextNode(value, node);
            node.addChild(valueNode);
        }
    }

}
