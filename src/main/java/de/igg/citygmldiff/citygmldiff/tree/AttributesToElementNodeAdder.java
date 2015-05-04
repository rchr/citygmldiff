package de.igg.citygmldiff.citygmldiff.tree;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;

public interface AttributesToElementNodeAdder<T> extends TreeNodeHandler<T> {

    public void addAttributesToElementNode(ElementNode<T> elementNode);

}
