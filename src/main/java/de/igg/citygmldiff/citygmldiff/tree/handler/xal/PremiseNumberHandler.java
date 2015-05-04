package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.PremiseNumber;

public class PremiseNumberHandler<T extends PremiseNumber> implements
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

        String numberType = element.getNumberType();
        if (numberType != null) {
            AttributeNode numberTypeNode = NodeFactory.createAttributeNode(
                    NUMBER_TYPE, numberType, node);
            node.addChild(numberTypeNode);
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

        // elements
        String content = element.getContent();
        if (content != null) {
            TextNode contentNode = new TextNode(content, node);
            node.addChild(contentNode);
        }
    }

}
