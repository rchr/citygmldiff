package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.SortingCode;

public class SortingCodeHandler<T extends SortingCode> implements TreeNodeHandler<T>, XalConstants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        // attributes
        String type = element.getType();
        if (type != null) {
            AttributeNode typeNode = NodeFactory
                    .createAttributeNode(TYPE, type, node);
            node.addChild(typeNode);
        }

        String code = element.getCode();
        if (code != null) {
            AttributeNode codeNode = NodeFactory
                    .createAttributeNode(CODE, code, node);
            node.addChild(codeNode);
        }

    }

}
