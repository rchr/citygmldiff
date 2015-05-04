package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import org.citygml4j.model.citygml.core.RelativeToTerrain;

/**
 * User: richard Date: 12.08.13 Time: 15:08
 */
public class RelativeToTerrainHandler implements
        TreeNodeHandler<RelativeToTerrain> {
    @Override
    public void addChildsToNode(ElementNode<RelativeToTerrain> node) {

        RelativeToTerrain element = node.getValue();
        String value = element.getValue();
        TextNode textNode = new TextNode(value, node);
        node.addChild(textNode);
    }
}
