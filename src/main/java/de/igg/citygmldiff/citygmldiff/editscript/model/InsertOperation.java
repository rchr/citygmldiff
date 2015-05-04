package de.igg.citygmldiff.citygmldiff.editscript.model;

import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;

/**
 * User: richard
 * Date: 13.09.13
 * Time: 17:17
 */
public class InsertOperation implements EditOperation {

    AbstractTreeNode nodeToInsert;
    ElementNode<?> parentNode;

    public InsertOperation(AbstractTreeNode nodeToInsert, ElementNode<?> parentNode) {
        this.nodeToInsert = nodeToInsert;
        this.parentNode = parentNode;
    }

    public AbstractTreeNode getNodeToInsert() {
        return nodeToInsert;
    }

    public ElementNode<?> getParentNode() {
        return parentNode;
    }

    @Override
    public String toString() {
        if (nodeToInsert != null && parentNode != null) {
            return "Insert(" +
                    "nodeToInsert=" + nodeToInsert.getSignature() +
                    ", parentNode=" + parentNode.getSignature() +
                    ')';
        }
        return "";
    }
}
