package de.igg.citygmldiff.citygmldiff.tree.model;

/**
 * User: richard
 * Date: 01.08.13
 * Time: 14:12
 * Abstract class to represent a leaf node.
 */
public abstract class AbstractLeafNode extends AbstractTreeNode {

    private String value;

    public AbstractLeafNode(String signature, ElementNode<?> parent, String value) {
        super(signature, parent);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
