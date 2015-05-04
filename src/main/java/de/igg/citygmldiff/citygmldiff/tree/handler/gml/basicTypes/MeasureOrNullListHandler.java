package de.igg.citygmldiff.citygmldiff.tree.handler.gml.basicTypes;

import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.basicTypes.MeasureOrNullList;

public class MeasureOrNullListHandler<T extends MeasureOrNullList> extends
        DoubleOrNullListHandler<T> implements Constants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        String uom = element.getUom();
        String signature = Utils.buildSignature(node, uom, UOM);
        AttributeNode uomNode = new AttributeNode(UOM, uom, signature, node);
        node.addChild(uomNode);

    }

}
