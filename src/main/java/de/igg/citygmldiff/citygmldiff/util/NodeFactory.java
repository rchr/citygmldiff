package de.igg.citygmldiff.citygmldiff.util;

import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;

/**
 * User: richard
 * Date: 13.09.13
 * Time: 12:00
 */
public class NodeFactory {

    public static AttributeNode createAttributeNode(String attributeName, String attributeValue, ElementNode<?> parent) {
        String signature = Utils.buildSignature(parent, null, attributeName);
        return new AttributeNode(attributeName, attributeValue, signature, parent);
    }

    public static <E> ElementNode<E> createElementNode(E element, String name, ElementNode<?> parent) {
        String signature = Utils.buildSignature(parent, element, name);
        return new ElementNode<>(element, name, signature, parent);
    }
}
