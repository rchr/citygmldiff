package de.igg.citygmldiff.citygmldiff.matching.model;

import de.igg.citygmldiff.citygmldiff.tree.model.AbstractLeafNode;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class to represent the distance-table, that is a mapping from a {@link Matching} to a distance between
 * the two nodes of the {@link Matching}.
 */
public class DistanceTable extends HashMap<Matching, Integer> {

    private Set<AbstractTreeNode> matchedNodes1 = new HashSet<>();
    private Set<AbstractTreeNode> matchedNodes2 = new HashSet<>();

    /**
     * Returns the distance for the id of a {@link Matching}.
     *
     * @param id
     * @return
     */
    public Integer get(String id) {
        for (java.util.Map.Entry<Matching, Integer> entry : entrySet()) {
            Matching m = entry.getKey();
            if (id.equals(m.getId())) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Returns true if this distance-table contains the given matchingID.
     *
     * @param matchingID
     * @return
     */
    public boolean containsMatching(String matchingID) {
        Integer i = get(matchingID);
        return i != null;
    }

    /**
     * Returns true if a {@link Matching} this distance-table contains the given {@link AbstractTreeNode}.
     *
     * @param node
     * @return
     */
    public boolean containsNode(AbstractTreeNode node) {
        for (java.util.Map.Entry<Matching, Integer> entry : entrySet()) {
            Matching m = entry.getKey();
            if (m.containsNode(node)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (java.util.Map.Entry<Matching, Integer> entry : entrySet()) {
            Matching matching = entry.getKey();
            stringBuilder.append(matching.getId()).append("; ").append(matching.getNode1().getSignature()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns the editing distance of these two {@link de.igg.citygmldiff.citygmldiff.tree.model.ElementNode}s.
     * Looks up the editing distance of child nodes in the {@link DistanceTable}.
     *
     * @param node1
     * @param node2
     * @return
     */
    public Integer getDistanceForSubtrees(ElementNode<?> node1, ElementNode<?> node2) {
        List<AbstractTreeNode> children1 = node1.getChildren();
        List<AbstractTreeNode> children2 = node2.getChildren();
        Integer summedDistance = 0;
        for (AbstractTreeNode child1 : children1) {
            for (AbstractTreeNode child2 : children2) {
                Matching matching = new Matching(child1, child2);
                Integer distance = this.get(matching.getId());
                if (distance != null) {
                    summedDistance += distance;
                }
            }
        }
        return summedDistance;
    }

    public Set<Matching> getLeafNodeMatchings() {
        Set<Matching> matchings = this.keySet();
        Set<Matching> leafMatchings = new HashSet<>();
        for (Matching m : matchings) {
            if (m.getNode1() instanceof AbstractLeafNode) {
                leafMatchings.add(m);
            }
        }
        return leafMatchings;
    }

    public Matching getMatching(String id) {
        for (Matching matching : keySet()) {
            if (id.equals(matching.getId())) {
                return matching;
            }
        }
        return null;
    }

    /**
     * Creates and adds a {@link de.igg.citygmldiff.citygmldiff.matching.model.Matching} to the distanceTable.
     * Furthermore, returns the created matching.
     *
     * @param n1             First {@link de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode} of the matching.
     * @param n2             Second {@link de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode} of the matching.
     * @param parentMatching Parent matching of the matching defined by n1 and n2.
     * @return
     */
    public Matching addMatching(AbstractTreeNode n1, AbstractTreeNode n2, Matching parentMatching) {
        Matching matching = new Matching(n1, n2);
        matching.setParentMatching(parentMatching);
        parentMatching.addChildMatching(matching);
        this.put(matching, 0);
        matchedNodes1.add(n1);
        matchedNodes2.add(n2);
        n1.setIsMatched(true);
        n2.setIsMatched(true);
        return matching;
    }

}
