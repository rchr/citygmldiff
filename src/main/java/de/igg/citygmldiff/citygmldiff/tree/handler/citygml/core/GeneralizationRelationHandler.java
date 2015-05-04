package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import org.citygml4j.model.citygml.core.AbstractCityObject;
import org.citygml4j.model.citygml.core.GeneralizationRelation;

/**
 * User: richard
 * Date: 12.08.13
 * Time: 14:59
 */
public class GeneralizationRelationHandler<T extends GeneralizationRelation> implements TreeNodeHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();
        AbstractCityObject abstractCityObject = element.getCityObject();
        String id = abstractCityObject.getId();
        TextNode textNode = new TextNode(id, node);
        node.addChild(textNode);
    }
}
