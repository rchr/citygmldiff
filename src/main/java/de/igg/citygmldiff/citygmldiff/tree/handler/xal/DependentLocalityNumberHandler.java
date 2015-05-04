package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.DependentLocalityNumber;

/**
 * User: richard
 * Date: 02.10.13
 * Time: 22:26
 */
public class DependentLocalityNumberHandler<T extends DependentLocalityNumber> implements TreeNodeHandler<T>, XalConstants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        // attributes
        String nameNumberOccurrence = element.getNameNumberOccurrence();
        if (nameNumberOccurrence != null) {
            AttributeNode occurenceNode = NodeFactory.createAttributeNode(NUMBER_NAME_OCCURRENCE, nameNumberOccurrence, node);
            node.addChild(occurenceNode);
        }

        String code = element.getCode();
        if (code != null) {
            AttributeNode codeNode = NodeFactory.createAttributeNode(CODE, code, node);
            node.addChild(codeNode);
        }

        // element
        String content = element.getContent();
        if (content != null) {
            TextNode contentNode = new TextNode(content, node);
            node.addChild(contentNode);
        }
    }
}
