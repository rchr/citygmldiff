package de.igg.citygmldiff.citygmldiff.tree.hash;

import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;

/**
 * User: richard
 * Date: 12.08.13
 * Time: 13:22
 */
public class TreeHasher {

    public void hashNodesInTree(AbstractTreeNode head) {
        for (AbstractTreeNode abstractTreeNode : head.getChildren()) {
            abstractTreeNode.setDigest(abstractTreeNode.getDigest());
            hashNodesInTree(abstractTreeNode);
        }
    }
}
