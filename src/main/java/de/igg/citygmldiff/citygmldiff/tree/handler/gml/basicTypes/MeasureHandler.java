package de.igg.citygmldiff.citygmldiff.tree.handler.gml.basicTypes;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.basicTypes.Measure;

/**
 * User: richard
 * Date: 12.08.13
 * Time: 15:39
 */
public class MeasureHandler<T extends Measure> implements TreeNodeHandler<T>, Constants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        String uomValue = element.getUom();
        String signature = Utils.buildSignature(node, uomValue, UOM);
        AttributeNode uomNode = new AttributeNode(UOM, uomValue, signature, node);
        node.addChild(uomNode);

        double value = element.getValue();
        TextNode textNode = new TextNode(String.valueOf(value), node);
        node.addChild(textNode);
    }
}
