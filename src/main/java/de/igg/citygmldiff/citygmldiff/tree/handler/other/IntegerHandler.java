package de.igg.citygmldiff.citygmldiff.tree.handler.other;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;

public class IntegerHandler implements TreeNodeHandler<Integer> {

    @Override
    public void addChildsToNode(ElementNode<Integer> node) {

        Integer element = node.getValue();
        String value = String.valueOf(element);

        TextNode textNode = new TextNode(value, node);
        node.addChild(textNode);
    }

}
