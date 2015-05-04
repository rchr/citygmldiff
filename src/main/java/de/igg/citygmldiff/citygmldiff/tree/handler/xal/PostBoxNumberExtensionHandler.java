package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.PostBoxNumberExtension;

public class PostBoxNumberExtensionHandler<T extends PostBoxNumberExtension>
        implements TreeNodeHandler<T>, XalConstants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        String numberExtensionSeperator = element.getNumberExtensionSeparator();
        if (numberExtensionSeperator != null) {
            AttributeNode prefixNode = NodeFactory.createAttributeNode(
                    NUMBER_EXTENSION_SEPARATOR, numberExtensionSeperator, node);
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
