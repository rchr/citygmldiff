package de.igg.citygmldiff.citygmldiff.matching;

import de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.apache.log4j.Logger;
import org.citygml4j.model.citygml.core.CityModel;

import java.util.Map;

public class TreeMatcher {

    private static final Logger LOGGER = Logger.getLogger(TreeMatcher.class);

    private DistanceTableCalculator distanceTableCalculator = new DistanceTableCalculator();
    private HashValueFilter hashValueFilter = new HashValueFilter();

    public DistanceTable matchTrees(ElementNode<CityModel> head1, ElementNode<CityModel> head2, Map<String[], Boolean> uncertainMatchings) {
        hashValueFilter.filterSubtreesWithEqualDigestRecursively(head1, head2);
        return distanceTableCalculator.computeDistanceTable(head1, head2, uncertainMatchings);
    }

    public Map<String, String> getChangedIDs() {
        return distanceTableCalculator.getChangedIDs();
    }

    public Map<AbstractTreeNode, AbstractTreeNode> getUncertainMatchings() {
        return distanceTableCalculator.getUncertainMatchings();
    }
}
