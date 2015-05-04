package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.AddressIdentifier;

public class AddressIdentifierHandler<T extends AddressIdentifier> implements
        TreeNodeHandler<T>, XalConstants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        String type = element.getType();
        if (type != null) {
            AttributeNode typeNode = NodeFactory
                    .createAttributeNode(TYPE, type, node);
            node.addChild(typeNode);
        }

        String content = element.getContent();
        if (content != null) {
            TextNode contentNode = new TextNode(content, node);
            node.addChild(contentNode);
        }

    }

}
