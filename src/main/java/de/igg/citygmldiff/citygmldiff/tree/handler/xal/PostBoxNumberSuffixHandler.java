package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.PostBoxNumberSuffix;

public class PostBoxNumberSuffixHandler<T extends PostBoxNumberSuffix>
        implements TreeNodeHandler<T>, XalConstants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        String code = element.getCode();
        if (code != null) {
            AttributeNode codeNode = NodeFactory
                    .createAttributeNode(CODE, code, node);
            node.addChild(codeNode);
        }

        String numberSuffixSeperator = element.getNumberSuffixSeparator();
        if (numberSuffixSeperator != null) {
            AttributeNode prefixNode = NodeFactory.createAttributeNode(
                    NUMBER_SUFFIX_SEPARATOR, numberSuffixSeperator, node);
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
