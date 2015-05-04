package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import org.citygml4j.model.gml.geometry.primitives.DirectPosition;

import java.util.List;

/**
 * User: richard
 * Date: 05.08.13
 * Time: 11:42
 */
public class DirectPositionHandler<T extends DirectPosition> implements TreeNodeHandler<T> {


    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        List<Double> positions = element.getValue();

        String posText = "";
        for (Double d : positions) {
            posText += String.valueOf(d) + " ";
        }
        posText = posText.trim();
        TextNode lowerCornerTextNode = new TextNode(posText, node);
        node.addChild(lowerCornerTextNode);
    }

}
