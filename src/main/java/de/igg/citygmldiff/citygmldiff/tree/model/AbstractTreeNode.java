package de.igg.citygmldiff.citygmldiff.tree.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: richard Date: 01.08.13 Time: 14:10
 * <p/>
 * Abstract class to represent a tree node.
 */
public abstract class AbstractTreeNode {

    protected static final String MD_5 = "MD5";
    protected static final String SHA_256 = "SHA-256";
    protected static final String HASH_ALGORITHM = SHA_256;
    private ElementNode<?> parent;
    private List<AbstractTreeNode> children;
    private String signature;
    private byte[] digest;
    private boolean isMatched = false;
    private String xPath;

    public AbstractTreeNode(String signature, ElementNode<?> parent) {
        this.signature = signature;
        this.children = new ArrayList<>();
        this.parent = parent;
    }

    public ElementNode<?> getParent() {
        return parent;
    }

    public void setParent(ElementNode<?> parent) {
        this.parent = parent;
    }

    public List<AbstractTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<AbstractTreeNode> children) {
        this.children = children;
    }

    public void addChild(AbstractTreeNode child) {
        children.add(child);
    }

    /**
     * Returns the signature of this treenode including type of the current
     * node.
     *
     * @return
     */
    public String getSignature() {
        return signature;
    }

    public String getXPath() {
        return xPath;
    }

    public void setXPath(String xPath) {
        this.xPath = xPath;
    }

    public byte[] getDigest() {
        if (digest != null) {
            return digest;
        } else {
            digest = calculateHash();
            return digest;
        }
    }

    public void setDigest(byte[] digest) {
        this.digest = digest;
    }

    public abstract byte[] calculateHash();

    public boolean hasChildren() {
        return (this.children.size() > 0);
    }

    public void removeChild(AbstractTreeNode child) {
        String signature = child.getSignature();
        for (Iterator<AbstractTreeNode> iter = children.iterator(); iter.hasNext(); ) {
            AbstractTreeNode currentChild = iter.next();
            String currentChildsSignature = currentChild.getSignature();
            if (signature.equals(currentChildsSignature)) {
                iter.remove();
            }
        }
    }

    public boolean hasParent() {
        return parent != null;
    }

    /**
     * Returns list containing the leaf-nodes of the (sub)tree.
     *
     * @return
     */
    public List<AbstractLeafNode> getLeafNodes() {
        List<AbstractLeafNode> leafs = new ArrayList<>();
        leafs = this.getLeafNodes(leafs);
        return leafs;
    }

    private List<AbstractLeafNode> getLeafNodes(List<AbstractLeafNode> leafNodes) {
        if (this.hasChildren()) {
            List<AbstractTreeNode> children = this.getChildren();
            for (AbstractTreeNode child : children) {
                if (child instanceof AbstractLeafNode) {
                    leafNodes.add((AbstractLeafNode) child);
                } else {
                    child.getLeafNodes(leafNodes);
                }
            }
        }
        return leafNodes;
    }

    public void setIsMatched(boolean isNodeMatched) {
        this.isMatched = isNodeMatched;
    }

    public boolean isMatched() {
        return this.isMatched;
    }

    /**
     * Calculates the number of all descendants. This is the number of children, and the children of the children, and so on.
     *
     * @return
     */
    public int getNumberOfDescendants() {
        int numberChildren = children.size();
        for (AbstractTreeNode child : children) {
            numberChildren += child.getNumberOfDescendants();
        }
        return numberChildren;
    }

}
