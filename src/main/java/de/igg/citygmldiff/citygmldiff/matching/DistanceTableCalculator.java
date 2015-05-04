package de.igg.citygmldiff.citygmldiff.matching;

import de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable;
import de.igg.citygmldiff.citygmldiff.matching.model.Matching;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractLeafNode;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.apache.log4j.Logger;
import org.citygml4j.model.citygml.core.CityModel;

import java.util.*;

/**
 * Class to calculate the {@link DistanceTable}.
 */
public class DistanceTableCalculator {

    private static final Logger LOGGER = Logger.getLogger(DistanceTableCalculator.class);
    private DistanceTable distanceTable;
    private Map<String, String> changedIDs;
    private Map<AbstractTreeNode, AbstractTreeNode> uncertainMatchings;

    public DistanceTableCalculator() {

        distanceTable = new DistanceTable();
        changedIDs = new HashMap<>();
    }

    /**
     * Calculates the {@link DistanceTable} between two {@link CityModel}s.
     *
     * @param cityModel1 First citymodel
     * @param cityModel2 Second citymodel
     * @return {@link de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable} containing matches between city models.
     */
    public DistanceTable computeDistanceTable(ElementNode<CityModel> cityModel1, ElementNode<CityModel> cityModel2,
                                              Map<String[], Boolean> uncertainMatchingsInput) {

        Matching cityModelMatching = new Matching(cityModel1, cityModel2);

        FeatureMatcher featureMatcher = new FeatureMatcher(distanceTable);
        distanceTable = featureMatcher.matchCityModels(cityModel1, cityModel2, cityModelMatching);
        distanceTable.put(cityModelMatching, 0);

        GeographyMatcher geographyMatcher = new GeographyMatcher(distanceTable);
        distanceTable = geographyMatcher.matchCityModels(cityModel1, cityModel2, uncertainMatchingsInput);
        changedIDs = geographyMatcher.getChangedIDs();
        uncertainMatchings = geographyMatcher.getUncertainMatchings();

        calculateDistances();
        removeNodesWithZeroDist(cityModel1, cityModel2);

        if (distanceTable.size() == 1) {
            distanceTable.remove(cityModelMatching);
        }

        LOGGER.info("Matching done.");
        return distanceTable;
    }

    public void removeNodesWithZeroDist(ElementNode<CityModel> cityModel1, ElementNode<CityModel> cityModel2) {
        Set<Matching> matchingsWithZeroDist = collectMatchingsWithZeroDist();
        List<ElementNode<CityModel>> tree1 = new ArrayList<>();
        tree1.add(cityModel1);
        List<ElementNode<CityModel>> tree2 = new ArrayList<>();
        tree2.add(cityModel2);
        removeNodesWithZeroDistRecursively(tree1, tree2, matchingsWithZeroDist);
    }

    /**
     * Collects and returns all matchings which have a distance=0.
     *
     * @return Set containing {@link Matching}s with distance=0.
     */
    public Set<Matching> collectMatchingsWithZeroDist() {
        Set<Matching> matchingsWithZeroDist = new HashSet<>();
        for (Map.Entry<Matching, Integer> matchingEntry : distanceTable.entrySet()) {
            if (matchingEntry.getValue() == 0) {
                Matching matching = matchingEntry.getKey();
                if (Utils.treesHaveSameDigest(matching.getNode1(), matching.getNode2())) {
                    matchingsWithZeroDist.add(matchingEntry.getKey());
                }
            }
        }
        return matchingsWithZeroDist;
    }

    /**
     * Removes all nodes which are associated in a {@link Matching} in matchingsWithZeroDist from the trees.
     * Thus, removes nodes which are associated to a matching with distance=0.
     *
     * @param nodes1                First nodes
     * @param nodes2                Second nodes
     * @param matchingsWithZeroDist Set containing the matches with distance=0
     */
    public <T extends AbstractTreeNode> void removeNodesWithZeroDistRecursively(List<T> nodes1, List<T> nodes2, Set<Matching> matchingsWithZeroDist) {
        Set<T> nodesToRemove1 = new HashSet<>();
        Set<T> nodesToRemove2 = new HashSet<>();
        for (T node1 : nodes1) {
            for (T node2 : nodes2) {
                Matching m = new Matching(node1, node2);
                /*
                Check if this matching is a zero distance matching. Furthermore, check if number of descendants of both nodes is the same.
                  Latter is important, to keep inserts and deletes.
                 */
                if (matchingsWithZeroDist.contains(m) && node1.getNumberOfDescendants() == node2.getNumberOfDescendants()) {
                    nodesToRemove1.add(node1);
                    nodesToRemove2.add(node2);
                } else {
                    if (node1.hasChildren() && node2.hasChildren()) {
                        removeNodesWithZeroDistRecursively(node1.getChildren(), node2.getChildren(), matchingsWithZeroDist);
                    }
                }
            }
        }
        nodes1.removeAll(nodesToRemove1);
        nodes2.removeAll(nodesToRemove2);
    }

    public DistanceTable getDistanceTable() {
        return distanceTable;
    }

    public void setDistanceTable(DistanceTable distanceTable) {
        this.distanceTable = distanceTable;
    }

    /**
     * Calculates the distances according to the matches in the distanceTable.
     */
    public void calculateDistances() {
        Set<Matching> leafNodeMatchings = distanceTable.getLeafNodeMatchings();
        for (Matching matching : leafNodeMatchings) {
            AbstractLeafNode leaf1 = (AbstractLeafNode) matching.getNode1();
            AbstractLeafNode leaf2 = (AbstractLeafNode) matching.getNode2();
            int dist = computeDistanceForLeafNodes(leaf1, leaf2);
            distanceTable.put(matching, dist);
            if (dist > 0) {
                incrementParentMatchings(matching);
            }
        }
        LOGGER.info("Distance calculation done");
    }

    /**
     * Calculates the distance between two matching {@link AbstractLeafNode}s.
     *
     * @param leaf1 Leaf node 1
     * @param leaf2 Leaf node 2
     * @return Distance between the leaf nodes.
     */
    public int computeDistanceForLeafNodes(AbstractLeafNode leaf1, AbstractLeafNode leaf2) {
        int distance;
        String value1 = leaf1.getValue();
        String value2 = leaf2.getValue();
        if (value1.equals(value2)) {
            distance = 0;
        } else {
            distance = 1;
        }
        return distance;
    }

    public void incrementParentMatchings(Matching matching) {
        Matching parentMatching = matching.getParentMatching();
        if (parentMatching == null) {
            return;
        }
        Integer oldDist = distanceTable.get(parentMatching.getId());
        Integer newDist = (oldDist == null) ? 1 : (oldDist + 1);
        distanceTable.put(parentMatching, newDist);
        incrementParentMatchings(parentMatching);
    }

    public Map<String, String> getChangedIDs() {
        return changedIDs;
    }

    public Map<AbstractTreeNode, AbstractTreeNode> getUncertainMatchings() {
        return uncertainMatchings;
    }

}