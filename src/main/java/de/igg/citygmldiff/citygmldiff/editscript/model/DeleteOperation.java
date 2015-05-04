package de.igg.citygmldiff.citygmldiff.editscript.model;

import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;

/**
 * User: richard
 * Date: 13.09.13
 * Time: 17:15
 */
public class DeleteOperation implements EditOperation {

    private AbstractTreeNode nodeToDelete;

    public DeleteOperation(AbstractTreeNode node) {
        nodeToDelete = node;
    }

    public AbstractTreeNode getNodeToDelete() {
        return nodeToDelete;
    }

    @Override
    public String toString() {
        return "Delete(" +
                "nodeToDelete=" + nodeToDelete.getSignature() +
                ')';
    }

}
