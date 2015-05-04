package de.igg.citygmldiff.citygmldiff.editscript.model;

import de.igg.citygmldiff.citygmldiff.tree.model.AbstractLeafNode;

/**
 * User: richard
 * Date: 13.09.13
 * Time: 17:08
 */
public class UpdateOperation implements EditOperation {

    private AbstractLeafNode nodeToUpdate;
    private String updateValue;

    public UpdateOperation(AbstractLeafNode node, String value) {
        this.nodeToUpdate = node;
        this.updateValue = value;
    }

    public AbstractLeafNode getNodeToUpdate() {
        return nodeToUpdate;
    }

    public String getUpdateValue() {
        return updateValue;
    }

    @Override
    public String toString() {
        return "Update(" +
                "nodeToUpdate=" + nodeToUpdate.getSignature() +
                ", updateValue='" + updateValue + '\'' +
                ')';
    }
}
