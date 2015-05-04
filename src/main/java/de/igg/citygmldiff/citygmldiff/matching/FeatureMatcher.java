package de.igg.citygmldiff.citygmldiff.matching;

import de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable;
import de.igg.citygmldiff.citygmldiff.matching.model.Matching;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.apache.log4j.Logger;
import org.citygml4j.model.citygml.core.AbstractCityObject;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.citygml.core.CityObjectMember;

import java.util.List;

/**
 * Created by richard on 01.04.14.
 * <p/>
 * Matches features by id and signature.
 */
public class FeatureMatcher {

    private static Logger LOGGER = Logger.getLogger(FeatureMatcher.class);

    private DistanceTable distanceTable;

    public FeatureMatcher(DistanceTable distanceTable) {
        this.distanceTable = distanceTable;
    }

    /**
     * Tries to match top-level CityGML features by id.
     * Top-level cityGML features are direct children of {@link AbstractCityObject}.
     *
     * @param cityModel1     First city model
     * @param cityModel2     Second city model
     * @param parentMatching The parent's matching
     * @return {@link de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable} containing the matches betweenn the trees.
     */
    public DistanceTable matchCityModels(ElementNode<CityModel> cityModel1, ElementNode<CityModel> cityModel2, Matching parentMatching) {
        for (AbstractTreeNode cityObjectMember1 : cityModel1.getChildren()) {
            for (AbstractTreeNode cityObjectMember2 : cityModel2.getChildren()) {
                if (areFeaturesMatching(cityObjectMember1, cityObjectMember2)) {
                    /* Top-down approach here: First match parents. If they are matching, */
                    /* we found a matching between top level features. */
                    LOGGER.info("I found a matching of 2 top level features.");
                    Matching matching = distanceTable.addMatching(cityObjectMember1, cityObjectMember2, parentMatching);
                    calculateMatchesTopDown(cityObjectMember1.getChildren(), cityObjectMember2.getChildren(), matching);
                }
            }
        }
        return distanceTable;
    }


    /**
     * Calculates matches in a top-down approach. Does not calculate distances. These need to be calculated afterwards.
     *
     * @param nodes1
     * @param nodes2
     * @param parentMatching
     */
    public void calculateMatchesTopDown(List<AbstractTreeNode> nodes1, List<AbstractTreeNode> nodes2, Matching parentMatching) {
        for (AbstractTreeNode n1 : nodes1) {
            for (AbstractTreeNode n2 : nodes2) {
                if (Utils.isSignatureEqual(n1, n2)) {
                    Matching matching = distanceTable.addMatching(n1, n2, parentMatching);
                    if (n1.hasChildren() && n2.hasChildren()) {
                        calculateMatchesTopDown(n1.getChildren(), n2.getChildren(), matching);
                    }
                }
            }
        }
    }

    /**
     * Checks if the two features are matching. Features are matching if the signature
     * and id is equal.
     *
     * @param child1
     * @param child2
     * @return
     */
    public boolean areFeaturesMatching(AbstractTreeNode child1, AbstractTreeNode child2) {
        if (Utils.isSignatureEqual(child1, child2)) {
            if (child1 instanceof ElementNode<?> && child2 instanceof ElementNode<?>) {
                ElementNode<?> elementNode1 = (ElementNode<?>) child1;
                ElementNode<?> elementNode2 = (ElementNode<?>) child2;
                if (areElementNodesMatching(elementNode1, elementNode2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the elementNodes are matching.
     *
     * @param node1
     * @param node2
     * @return
     */
    public boolean areElementNodesMatching(ElementNode<?> node1, ElementNode<?> node2) {
        Object object1 = node1.getValue();
        Object object2 = node2.getValue();

        if (object1 instanceof CityObjectMember
                && object2 instanceof CityObjectMember) {
            object1 = ((CityObjectMember) object1).getCityObject();
            object2 = ((CityObjectMember) object2).getCityObject();
        }
        if (object1 instanceof AbstractCityObject
                && object2 instanceof AbstractCityObject) {
            AbstractCityObject abstractCityObject1 = (AbstractCityObject) object1;
            AbstractCityObject abstractCityObject2 = (AbstractCityObject) object2;
            if (Utils.areCityObjectsMatching(abstractCityObject1, abstractCityObject2)) {
                return true;
            }
        }
        return false;
    }

    public DistanceTable getDistanceTable() {
        return distanceTable;
    }
}
