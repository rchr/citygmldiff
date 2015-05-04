package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.PostBoxNumberPrefix;

public class PostBoxNumberPrefixHandler<T extends PostBoxNumberPrefix> implements TreeNodeHandler<T>,
        XalConstants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        String code = element.getCode();
        if (code != null) {
            AttributeNode codeNode = NodeFactory
                    .createAttributeNode(CODE, code, node);
            node.addChild(codeNode);
        }

        String numberPrefixSeperator = element.getNumberPrefixSeparator();
        if (numberPrefixSeperator != null) {
            AttributeNode prefixNode = NodeFactory.createAttributeNode(NUMBER_PREFIX_SEPARATOR, numberPrefixSeperator, node);
            node.addChild(prefixNode);
        }
        // value
        String content = element.getContent();
        if (content != null) {
            TextNode contentNode = new TextNode(content, node);
            node.addChild(contentNode);
        }
    }

}
