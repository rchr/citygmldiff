package de.igg.citygmldiff.citygmldiff.matching;

import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import de.igg.citygmldiff.citygmldiff.util.Utils;

import java.util.Iterator;
import java.util.List;

public class HashValueFilter {

    /**
     * Filters subtrees of tree1 and tree2 by digest value.
     *
     * @param tree1 First tree.
     * @param tree2 Second tree.
     */
    public void filterSubtreesWithEqualDigestRecursively(
            AbstractTreeNode tree1, AbstractTreeNode tree2) {
        if (tree1.hasChildren() && tree2.hasChildren()) {
            filterSubtreesWithEqualDigests(tree1.getChildren(), tree2.getChildren());
        }
    }

    /**
     * Compares {@link AbstractTreeNode}s in tree1 and tree2. If 2 nodes have
     * the same digest value, both nodes will be removed from the {@link List}.
     * If 2 nodes have different digest values, they stay in the tree,
     * and thus, will be added to the distance table.
     *
     * @param tree1 First tree.
     * @param tree2 Second tree.
     */
    private void filterSubtreesWithEqualDigests(List<AbstractTreeNode> tree1, List<AbstractTreeNode> tree2) {
        for (Iterator<AbstractTreeNode> iter1 = tree1.iterator(); iter1.hasNext(); ) {
            AbstractTreeNode node1 = iter1.next();
            boolean node1IsExisting = true;
            for (Iterator<AbstractTreeNode> iter2 = tree2.iterator(); iter2.hasNext(); ) {
                AbstractTreeNode node2 = iter2.next();
                if (Utils.treesHaveSameDigest(node1, node2) && node1IsExisting && node1.getSignature().equals(node2.getSignature())) {
                    iter1.remove();
                    iter2.remove();
                    node1IsExisting = false;
                } else {
                    filterSubtreesWithEqualDigestRecursively(node1, node2);
                }
            }
        }
    }


}
