package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.ThoroughfareNumber;

public class ThoroughfareNumberHandler<T extends ThoroughfareNumber> implements
        TreeNodeHandler<T>, XalConstants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        // atrributes
        String numberType = element.getNumberType();
        if (numberType != null) {
            AttributeNode numberTypeNode = NodeFactory.createAttributeNode(
                    NUMBER_TYPE, numberType, node);
            node.addChild(numberTypeNode);
        }
        String type = element.getType();
        if (type != null) {
            AttributeNode typeNode = NodeFactory
                    .createAttributeNode(TYPE, type, node);
            node.addChild(typeNode);
        }
        String indicator = element.getIndicator();
        if (indicator != null) {
            AttributeNode indicatorNode = NodeFactory.createAttributeNode(INDICATOR,
                    indicator, node);
            node.addChild(indicatorNode);
        }
        String indicatorOccurrence = element.getIndicatorOccurrence();
        if (indicatorOccurrence != null) {
            AttributeNode indicatorOccurNode = NodeFactory.createAttributeNode(
                    INDICATOR_OCCURENCE, indicatorOccurrence, node);
            node.addChild(indicatorOccurNode);
        }
        String numberOccurrence = element.getNumberOccurrence();
        if (numberOccurrence != null) {
            AttributeNode numberOccurNode = NodeFactory.createAttributeNode(
                    NUMBER_OCCURENCE, numberOccurrence, node);
            node.addChild(numberOccurNode);
        }
        String code = element.getCode();
        if (code != null) {
            AttributeNode codeNode = NodeFactory
                    .createAttributeNode(CODE, code, node);
            node.addChild(codeNode);
        }

        // text element
        String content = element.getContent();
        TextNode contentNode = new TextNode(content, node);
        node.addChild(contentNode);
    }

}
