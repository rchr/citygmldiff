package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.PostOfficeNumber;

public class PostOfficeNumberHandler<T extends PostOfficeNumber> implements
        TreeNodeHandler<T>, XalConstants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        // atrributes
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
