package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.SubPremiseNumber;

public class SubPremiseNumberHandler<T extends SubPremiseNumber> implements
        TreeNodeHandler<T>, XalConstants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        // attributes
        String type = element.getType();
        if (type != null) {
            AttributeNode typeNode = NodeFactory
                    .createAttributeNode(TYPE, type, node);
            node.addChild(typeNode);
        }

        String code = element.getCode();
        if (code != null) {
            AttributeNode codeNode = NodeFactory
                    .createAttributeNode(CODE, code, node);
            node.addChild(codeNode);
        }

        String indicator = element.getIndicator();
        if (indicator != null) {
            AttributeNode indicatorNode = NodeFactory.createAttributeNode(INDICATOR,
                    indicator, node);
            node.addChild(indicatorNode);
        }

        String indicatorOccurrence = element.getIndicatorOccurrence();
        if (indicatorOccurrence != null) {
            AttributeNode indicatorOccurenceNode = NodeFactory.createAttributeNode(
                    INDICATOR_OCCURENCE, indicatorOccurrence, node);
            node.addChild(indicatorOccurenceNode);
        }

        String numberTypeOccurrence = element.getNumberTypeOccurrence();
        if (numberTypeOccurrence != null) {
            AttributeNode numTypeOccurenceNode = NodeFactory.createAttributeNode(
                    NUMBER_TYPE_OCCURENCE, numberTypeOccurrence, node);
            node.addChild(numTypeOccurenceNode);
        }

        String premiseNumberSeparator = element.getPremiseNumberSeparator();
        if (premiseNumberSeparator != null) {
            AttributeNode separatorNode = NodeFactory.createAttributeNode(
                    premiseNumberSeparator, PREMISE_NUMBER_SEPARATOR, node);
            node.addChild(separatorNode);
        }

        // elements
        String content = element.getContent();
        if (content != null) {
            TextNode contentNode = new TextNode(content, node);
            node.addChild(contentNode);
        }
    }

}
