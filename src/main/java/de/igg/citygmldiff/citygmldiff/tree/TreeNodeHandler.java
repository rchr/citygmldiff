package de.igg.citygmldiff.citygmldiff.tree;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;

/**
 * User: richard
 * Date: 01.08.13
 * Time: 15:04
 * Interface which defines method body to handle {@link ElementNode}s.
 */
public interface TreeNodeHandler<T> {

    /**
     * Creates the subtree which is rooted at the given {@link ElementNode}.
     *
     * @param node The {@link ElementNode} which is root node of the created subtree.
     */
    public void addChildsToNode(ElementNode<T> node);

}
