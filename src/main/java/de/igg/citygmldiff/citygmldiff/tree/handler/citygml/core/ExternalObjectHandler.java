package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.core.ExternalObject;

/**
 * User: richard
 * Date: 08.08.13
 * Time: 15:33
 */
public class ExternalObjectHandler<T extends ExternalObject> implements TreeNodeHandler<T>, Constants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        String name = element.getName();
        String uri = element.getUri();
        ElementNode<String> elementNode = null;
        if (name != null) {
            elementNode = createExternalObjectNode(name, NAME, node);
        } else if (uri != null) {
            elementNode = createExternalObjectNode(uri, URI, node);
        }
        node.addChild(elementNode);
    }

    private ElementNode<String> createExternalObjectNode(String element, String name, ElementNode<T> node) {
        String signature = Utils.buildSignature(node, element, name);
        ElementNode<String> elementNode = new ElementNode<>(element,
                name, signature, node);
        TextNode nameTextNode = new TextNode(element, elementNode);
        elementNode.addChild(nameTextNode);
        return elementNode;
    }
}
