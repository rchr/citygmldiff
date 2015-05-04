package de.igg.citygmldiff.citygmldiff.tree.handler.gml.base;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.base.AbstractGML;
import org.citygml4j.model.gml.base.StringOrRef;
import org.citygml4j.model.gml.basicTypes.Code;

import java.util.List;

/**
 * Class that handles {@link AbstractGML} objects.
 *
 * @param <T>
 */
public class GMLHandler<T extends AbstractGML> implements TreeNodeHandler<T>, Constants {

    private void addNames(ElementNode<T> actualNode, List<Code> names) {
        for (Code code : names) {
            ElementNode<Code> codeElementNode = NodeFactory.createElementNode(code, NAME, actualNode);
            String value = code.getValue();
            TextNode valueTreeNode = new TextNode(value, codeElementNode);
            codeElementNode.addChild(valueTreeNode);
            actualNode.addChild(codeElementNode);
        }
    }

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        String id = element.getId();
        if (id != null) {
            AttributeNode idTreeNode = NodeFactory.createAttributeNode(GML_ID, id, node);
            node.addChild(idTreeNode);
        }

        List<Code> names = element.getName();
        if (names != null && names.size() > 0) {
            addNames(node, names);
        }

        StringOrRef description = element.getDescription();
        if (description != null) {
            ElementNode<StringOrRef> stringOrRefElementNode = NodeFactory.createElementNode(description, DESCRIPTION, node);
            String value = description.getValue();
            TextNode textNode = new TextNode(value, stringOrRefElementNode);
            node.addChild(stringOrRefElementNode);
            stringOrRefElementNode.addChild(textNode);
        }

    }
}
