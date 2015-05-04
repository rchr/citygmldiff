package de.igg.citygmldiff.citygmldiff.editscript.model;

import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;

/**
 * Created by richard on 14.04.14.
 */
public class UncertainOperation implements EditOperation {

    private AbstractTreeNode node1;
    private AbstractTreeNode node2;

    public UncertainOperation(AbstractTreeNode node1, AbstractTreeNode node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public AbstractTreeNode getNode1() {
        return node1;
    }

    public AbstractTreeNode getNode2() {
        return node2;
    }

    @Override
    public String toString() {
        if (node1 != null && node2 != null) {
            return "Uncertain(" +
                    "node1=" + node1.getSignature() +
                    ", node2=" + node2.getSignature() +
                    ')';
        }
        return "";
    }
}
