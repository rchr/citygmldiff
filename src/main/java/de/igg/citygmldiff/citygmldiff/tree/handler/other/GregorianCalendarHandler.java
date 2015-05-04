package de.igg.citygmldiff.citygmldiff.tree.handler.other;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;

import java.util.GregorianCalendar;

/**
 * User: richard
 * Date: 08.08.13
 * Time: 15:07
 */
public class GregorianCalendarHandler<T extends GregorianCalendar> implements TreeNodeHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();
        TextNode textNode = new TextNode(element.getTime().toString(), node);
        node.addChild(textNode);
    }
}
