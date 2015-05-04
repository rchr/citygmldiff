package de.igg.citygmldiff.citygmldiff.tree.handler.gml.basicTypes;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import org.citygml4j.model.gml.basicTypes.DoubleOrNull;
import org.citygml4j.model.gml.basicTypes.DoubleOrNullList;

import java.util.List;

public class DoubleOrNullListHandler<T extends DoubleOrNullList> implements
        TreeNodeHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();
        List<DoubleOrNull> doubleOrNullList = element.getDoubleOrNull();
        for (DoubleOrNull doubleNull : doubleOrNullList) {
            if (doubleNull.isSetDouble()) {
                Double value = doubleNull.getDouble();
                TextNode textNode = new TextNode(String.valueOf(value), node);
                node.addChild(textNode);
            }
        }

    }

}
