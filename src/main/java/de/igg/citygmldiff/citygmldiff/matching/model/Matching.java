package de.igg.citygmldiff.citygmldiff.matching.model;

import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a matching between two {@link AbstractTreeNode}s.
 *
 * @author richard
 */
public class Matching {

    private AbstractTreeNode node1;
    private AbstractTreeNode node2;
    private Integer[] id;

    private Matching parentMatching;
    private List<Matching> childMatchings;

    /**
     * @param node1 Node in tree1 which matches node2 in tree2
     * @param node2 Node in tree2 which matches node1 in tree1
     */
    public Matching(AbstractTreeNode node1, AbstractTreeNode node2) {
        super();
        this.node1 = node1;
        this.node2 = node2;
        this.id = new Integer[2];
        id[0] = node1.hashCode();
        id[1] = node2.hashCode();

        childMatchings = new ArrayList<>();
    }

    public AbstractTreeNode getNode1() {
        return node1;
    }

    public AbstractTreeNode getNode2() {
        return node2;
    }

    public String getId() {
        return id[0] + ":" + id[1];
    }

    public Matching getParentMatching() {
        return parentMatching;
    }

    public void setParentMatching(Matching parentMatching) {
        this.parentMatching = parentMatching;
    }

    public List<Matching> getChildMatchings() {
        return childMatchings;
    }

    public void setChildMatchings(List<Matching> childMatchings) {
        this.childMatchings = childMatchings;
    }

    public void addChildMatching(Matching childMatching) {
        this.childMatchings.add(childMatching);
    }

    public boolean containsNode(AbstractTreeNode node) {
        return node1.hashCode() == node.hashCode() || node2.hashCode() == node.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;

        if (other == null || (this.getClass() != other.getClass())) {
            return false;
        }

        Matching otherMatching = (Matching) other;
        return (getId().equals(otherMatching.getId()));
    }

    @Override
    public int hashCode() {
        /*
        Compute hash on the generated id string.
		See here: http://stackoverflow.com/questions/21479743/set-contains-and-object-equal
		 */
        return getId().hashCode();
    }

}
