package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import org.citygml4j.model.citygml.core.RelativeToWater;

/**
 * User: richard Date: 12.08.13 Time: 15:21
 */
public class RelativeToWaterHandler implements TreeNodeHandler<RelativeToWater> {
    @Override
    public void addChildsToNode(ElementNode<RelativeToWater> node) {
        RelativeToWater element = node.getValue();
        String value = element.getValue();
        TextNode textNode = new TextNode(value, node);
        node.addChild(textNode);
    }
}
