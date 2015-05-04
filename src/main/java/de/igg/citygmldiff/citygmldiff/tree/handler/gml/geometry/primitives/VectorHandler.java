package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.SRSReferenceGroupAttributeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import org.citygml4j.model.gml.geometry.primitives.Vector;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 14:08
 */
public class VectorHandler<T extends Vector> extends SRSReferenceGroupAttributeHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addAttributesToElementNode(node);

        T element = node.getValue();
        List<Double> value = element.getValue();
        if (value != null) {
            processValue(value, node);
        }
    }

    private void processValue(List<Double> value, ElementNode<T> node) {
        String posText = "";
        for (Double v : value) {
            posText += String.valueOf(v) + " ";
        }
        posText = posText.trim();
        TextNode valueNode = new TextNode(posText, node);
        node.addChild(valueNode);
    }
}
