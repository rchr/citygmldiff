package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.PostalCodeNumberExtension;

public class PostalCodeNumberExtensionHandler<T extends PostalCodeNumberExtension>
        implements TreeNodeHandler<T>, XalConstants {

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
        if (type != null) {
            AttributeNode codeNode = NodeFactory
                    .createAttributeNode(CODE, code, node);
            node.addChild(codeNode);
        }

        String numberExtensionSeparator = element.getNumberExtensionSeparator();
        if (numberExtensionSeparator != null) {
            AttributeNode numberExtensionNode = NodeFactory.createAttributeNode(
                    NUMBER_EXTENSION_SEPARATOR, numberExtensionSeparator, node);
            node.addChild(numberExtensionNode);
        }

        // elements
        String content = element.getContent();
        if (content != null) {
            TextNode contentNode = new TextNode(content, node);
            node.addChild(contentNode);
        }
    }

}
