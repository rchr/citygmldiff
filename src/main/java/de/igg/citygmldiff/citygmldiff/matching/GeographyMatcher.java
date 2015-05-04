package de.igg.citygmldiff.citygmldiff.matching;

import de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable;
import de.igg.citygmldiff.citygmldiff.matching.model.Matching;
import de.igg.citygmldiff.citygmldiff.matching.model.MatchingState;
import de.igg.citygmldiff.citygmldiff.matching.spatial.BuildingWallMatcher;
import de.igg.citygmldiff.citygmldiff.matching.spatial.TopLevelFeatureMatcher;
import de.igg.citygmldiff.citygmldiff.tree.model.*;
import org.apache.log4j.Logger;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.citygml.core.CityObjectMember;
import org.citygml4j.model.gml.base.AbstractGML;
import org.citygml4j.model.gml.geometry.primitives.Polygon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by richard on 01.04.14.
 * <p/>
 * Matches features by geographic location.
 */
public class GeographyMatcher {

    private static final Logger LOGGER = Logger.getLogger(GeographyMatcher.class);

    private DistanceTable distanceTable;

    private Map<String, String> idChanges;

    private Map<AbstractTreeNode, AbstractTreeNode> uncertainMatchings;

    public GeographyMatcher(DistanceTable distanceTable) {

        this.distanceTable = distanceTable;
        this.idChanges = new HashMap<>();
        this.uncertainMatchings = new HashMap<>();
    }

    public DistanceTable matchCityModels(ElementNode<CityModel> cityModel1, ElementNode<CityModel> cityModel2, Map<String[], Boolean> uncertainMatchingsInput) {
        return matchTopLevelFeatures(cityModel1.getChildren(), cityModel2.getChildren(), uncertainMatchingsInput);
    }

    private DistanceTable matchTopLevelFeatures(List<AbstractTreeNode> nodes1, List<AbstractTreeNode> nodes2, Map<String[], Boolean> uncertainMatchingsInput) {
        for (AbstractTreeNode n1 : nodes1) {
            for (AbstractTreeNode n2 : nodes2) {
                if (!n1.isMatched() && !n2.isMatched()) {
                    MatchingState matchingState = matchTopLevelNodesGeographically(n1, n2, uncertainMatchingsInput);
                    if (matchingState == MatchingState.MATCHING) {
                        // do not match children if nodes are not matched
                        matchTopLevelFeatures(n1.getChildren(), n2.getChildren(), uncertainMatchingsInput);
                    } else if (matchingState == MatchingState.UNCERTAIN) {
                        uncertainMatchings.put(n1.getChildren().get(0), n2.getChildren().get(0));
                        Matching parentMatching = new Matching(n1.getParent(), n2.getParent());
                        distanceTable.addMatching(n1, n2, parentMatching);
                    }
                }
            }
        }
        return distanceTable;
    }

    private MatchingState lookUpMatchingStateForUncertainMatching(AbstractTreeNode n1, AbstractTreeNode n2, Map<String[], Boolean> uncertainMatchings) {

        String id1 = null;
        String id2 = null;

        if (n1 instanceof ElementNode<?> && n2 instanceof ElementNode<?>) {
            Object value1 = ((ElementNode) n1).getValue();
            Object value2 = ((ElementNode) n2).getValue();
            if (value1 instanceof AbstractGML && value2 instanceof AbstractGML) {
                id1 = ((AbstractGML) value1).getId();
                id2 = ((AbstractGML) value2).getId();
            }
        }

        if (id1 == null || id2 == null) {
            return MatchingState.UNCERTAIN;
        }

        for (Map.Entry<String[], Boolean> uncertainMatching : uncertainMatchings.entrySet()) {
            String[] matching = uncertainMatching.getKey();
            if (id1.equals(matching[0]) && id2.equals(matching[1])) {
                Boolean isMatching = uncertainMatching.getValue();
                if (isMatching) {
                    return MatchingState.MATCHING;
                } else if (!isMatching) {
                    return MatchingState.NOT_MATCHING;
                }
            }
        }
        return MatchingState.UNCERTAIN;
    }

    private MatchingState lookUpMatchingStateForUncertainMatching(AbstractGML abstractGML1, AbstractGML abstractGML2, Map<String[], Boolean> uncertainMatchings) {

        String id1 = abstractGML1.getId();
        String id2 = abstractGML2.getId();

        if (id1 == null || id2 == null) {
            return MatchingState.UNCERTAIN;
        }

        for (Map.Entry<String[], Boolean> uncertainMatching : uncertainMatchings.entrySet()) {
            String[] matching = uncertainMatching.getKey();
            String matching0 = matching[0].trim();
            String matching1 = matching[1].trim();
            if (id1.equals(matching0) && id2.equals(matching1)) {
                Boolean isMatching = uncertainMatching.getValue();
                if (isMatching) {
                    return MatchingState.MATCHING;
                } else if (!isMatching) {
                    return MatchingState.NOT_MATCHING;
                }
            }
        }
        return MatchingState.UNCERTAIN;
    }

    private MatchingState matchTopLevelNodesGeographically(AbstractTreeNode topLevelFeatureNode1, AbstractTreeNode topLevelFeatureNode2,
                                                           Map<String[], Boolean> uncertainMatchingsInput) {
        MatchingState matchingState = MatchingState.NOT_MATCHING;
        Matching cityModelMatching = new Matching(topLevelFeatureNode1.getParent(), topLevelFeatureNode2.getParent());
        if (distanceTable.containsMatching(cityModelMatching.getId())) {
            cityModelMatching = distanceTable.getMatching(cityModelMatching.getId()); // necessary to get the correct matching
            if (topLevelFeatureNode1 instanceof ElementNode<?> && topLevelFeatureNode2 instanceof ElementNode<?>) {
                ElementNode<?> elemNode1 = (ElementNode) topLevelFeatureNode1;
                ElementNode<?> elemNode2 = (ElementNode) topLevelFeatureNode2;
                matchingState = matchTopLevelElementNodesGeographically(elemNode1, elemNode2, cityModelMatching, uncertainMatchingsInput);
            } else if (topLevelFeatureNode1 instanceof AbstractLeafNode && topLevelFeatureNode2 instanceof AbstractLeafNode) {
                AbstractLeafNode abstractLeafNode1 = (AbstractLeafNode) topLevelFeatureNode1;
                AbstractLeafNode abstractLeafNode2 = (AbstractLeafNode) topLevelFeatureNode2;
                if (areAbstractLeafNodesMatching(abstractLeafNode1, abstractLeafNode2)) {
                    distanceTable.addMatching(topLevelFeatureNode1, topLevelFeatureNode2, cityModelMatching);
                    matchingState = MatchingState.MATCHING;
                }
            }
        }
        return matchingState;
    }

    private boolean areAbstractLeafNodesMatching(AbstractLeafNode node1, AbstractLeafNode node2) {
        if (node1 instanceof TextNode && node2 instanceof TextNode) {
            return true;
        } else if (node1 instanceof AttributeNode && node2 instanceof AttributeNode) {
            AttributeNode attributeNode1 = (AttributeNode) node1;
            AttributeNode attributeNode2 = (AttributeNode) node2;
            if (attributeNode1.getAttributeName().equals(attributeNode2.getAttributeName())) {
                return true;
            }
        }
        return false;
    }

    private MatchingState matchTopLevelElementNodesGeographically(ElementNode<?> node1, ElementNode<?> node2, Matching cityModelMatching,
                                                                  Map<String[], Boolean> uncertainMatchingsInput) {
//        boolean areNodesMatched = false;
        MatchingState matchingState = MatchingState.NOT_MATCHING;
        Object o1 = node1.getValue();
        Object o2 = node2.getValue();

        AbstractGML abstractGML1 = null;
        AbstractGML abstractGML2 = null;
        ElementNode<?> parentNode1;
        ElementNode<?> parentNode2;

        if (o1 instanceof CityObjectMember && o2 instanceof CityObjectMember) {
            abstractGML1 = ((CityObjectMember) o1).getFeature();
            abstractGML2 = ((CityObjectMember) o2).getFeature();
            parentNode1 = node1;
            parentNode2 = node2;
            node1 = getCorrespondingBuildingNode(parentNode1, abstractGML1.getId());
            node2 = getCorrespondingBuildingNode(parentNode2, abstractGML2.getId());

            if (abstractGML1 instanceof Building && abstractGML2 instanceof Building) {
                matchingState = TopLevelFeatureMatcher.areTopLevelFeaturesMatching(abstractGML1, abstractGML2);
            }
            if (matchingState == MatchingState.UNCERTAIN) {
                matchingState = lookUpMatchingStateForUncertainMatching(abstractGML1, abstractGML2, uncertainMatchingsInput);
            }
            if (matchingState == MatchingState.MATCHING) {
                Matching pMatching = distanceTable.addMatching(parentNode1, parentNode2, cityModelMatching);
                distanceTable.addMatching(node1, node2, pMatching);

                Map<Polygon, Polygon> polygonMatchings = BuildingWallMatcher.matchBuildingsBoundarySurfaces((Building) abstractGML1, (Building) abstractGML2);
                calculateBuildingMatchesTopDown(node1.getChildren(), node2.getChildren(), pMatching);
                addPolygonMatchingsToDistanceTable(node1, node2, polygonMatchings);
            } else if (matchingState == MatchingState.UNCERTAIN) {
                LOGGER.info("Found uncertain match!");
            }

        } else if (o1 instanceof AbstractGML && o2 instanceof AbstractGML) {
            abstractGML1 = (AbstractGML) o1;
            abstractGML2 = (AbstractGML) o2;
        }

        if (matchingState == MatchingState.NOT_MATCHING && abstractGML1 == null && abstractGML2 == null) { // actual element is no abstract feature --> match it by name
            String name1 = node1.getName();
            String name2 = node2.getName();
            if (name1.equals(name2)) {
                matchingState = MatchingState.MATCHING;
                distanceTable.addMatching(node1, node2, cityModelMatching);
            }
        }
        return matchingState;
    }

    private ElementNode<?> getCorrespondingBuildingNode(ElementNode<?> node, String gmlId) {
        for (AbstractTreeNode abstractTreeNode : node.getChildren()) {
            if (abstractTreeNode instanceof ElementNode<?>) {
                ElementNode<?> elementNode = (ElementNode<?>) abstractTreeNode;
                Object value = elementNode.getValue();
                if (value instanceof Building) {
                    Building building = (Building) value;
                    if (building.getId().equals(gmlId)) {
                        return elementNode;
                    }
                }
            }
        }
        return null;
    }

    private void addPolygonMatchingsToDistanceTable(ElementNode<?> node1, ElementNode<?> node2, Map<Polygon, Polygon> polygonMatchings) {

        for (Map.Entry<Polygon, Polygon> entry : polygonMatchings.entrySet()) {
            ElementNode<Polygon> polygonNode1 = getCorrespondingElementNode(node1.getChildren(), entry.getKey());
            ElementNode<Polygon> polygonNode2 = getCorrespondingElementNode(node2.getChildren(), entry.getValue());

            String id1 = polygonNode1.getValue().getId();
            String id2 = polygonNode2.getValue().getId();
            if (id1 != null && id2 != null) {
                idChanges.put(id1, id2);
            }

            Matching matching = new Matching(polygonNode1, polygonNode2);
            addPolygonsParentsMatchingsToDistanceTable(polygonNode1, polygonNode2, matching);
            addPolygonsChildrenMatchingToDistanceTable(polygonNode1, polygonNode2, matching);
        }
    }

    private void addPolygonsParentsMatchingsToDistanceTable(ElementNode<?> node1, ElementNode<?> node2, Matching polygonMatching) {

        ElementNode<?> parent1 = node1.getParent();
        ElementNode<?> parent2 = node2.getParent();

        if (parent1.getValue() instanceof Building && parent2.getValue() instanceof Building) {
            Matching buildingMatching = new Matching(parent1, parent2);
            buildingMatching = distanceTable.getMatching(buildingMatching.getId());
            polygonMatching.setParentMatching(buildingMatching);
            buildingMatching.addChildMatching(polygonMatching);
            return;
        }

        if (parent1.getName().equals(parent2.getName())) {
            Matching matching = new Matching(parent1, parent2);
            matching.addChildMatching(polygonMatching);
            polygonMatching.setParentMatching(matching);
            distanceTable.put(matching, 0);

            List<AbstractTreeNode> children1 = parent1.getChildren();
            List<AbstractTreeNode> children2 = parent2.getChildren();
            for (AbstractTreeNode child1 : children1) {
                for (AbstractTreeNode child2 : children2) {
                    if (child1 instanceof ElementNode<?> && child2 instanceof ElementNode<?>) {
                        ElementNode elementNode1 = (ElementNode) child1;
                        ElementNode elementNode2 = (ElementNode) child2;
                        String name1 = elementNode1.getName();
                        String name2 = elementNode2.getName();
                        if (name1.equals(name2)) {
                            distanceTable.addMatching(child1, child2, matching);
                            addChangedID(elementNode1, elementNode2);
                        }
                    } else if (child1 instanceof AttributeNode && child2 instanceof AttributeNode) {
                        String attributeName1 = ((AttributeNode) child1).getAttributeName();
                        String attributeName2 = ((AttributeNode) child2).getAttributeName();
                        if (attributeName1.equals(attributeName2)) {
                            distanceTable.addMatching(child1, child2, matching);
                        }
                    } else if (child1 instanceof TextNode && child2 instanceof TextNode) {
                        distanceTable.addMatching(child1, child2, matching);
                    }
                }
            }

            addPolygonsParentsMatchingsToDistanceTable(parent1, parent2, matching);
        } else {
            LOGGER.error("Cannot match parent nodes, because their names differ!");
        }
    }

    private void addChangedID(ElementNode<?> node1, ElementNode<?> node2) {
        if (node1.getValue() instanceof AbstractGML && node2.getValue() instanceof AbstractGML) {
            AbstractGML abstractGML1 = (AbstractGML) node1.getValue();
            AbstractGML abstractGML2 = (AbstractGML) node2.getValue();

            String id1 = abstractGML1.getId();
            String id2 = abstractGML2.getId();
            if (id1 != null && id2 != null) {
                idChanges.put(abstractGML1.getId(), abstractGML2.getId());
            }
        }
    }

    private void calculateBuildingMatchesTopDown(List<AbstractTreeNode> nodes1, List<AbstractTreeNode> nodes2, Matching parentMatching) {
        for (AbstractTreeNode abstractTreeNode1 : nodes1) {
            for (AbstractTreeNode abstractTreeNode2 : nodes2) {
                Matching matching = null;
                boolean isMatched = false;
                if (abstractTreeNode1 instanceof ElementNode<?> && abstractTreeNode2 instanceof ElementNode<?>) {
                    // do not match boundarysurfaces which are of type abstractgml
                    if (!(((ElementNode) abstractTreeNode1).getValue() instanceof AbstractGML) && !(((ElementNode) abstractTreeNode2).getValue() instanceof AbstractGML)) {
                        ElementNode<?> elementNode1 = (ElementNode<?>) abstractTreeNode1;
                        ElementNode<?> elementNode2 = (ElementNode<?>) abstractTreeNode2;
                        String name1 = elementNode1.getName();
                        String name2 = elementNode2.getName();
                        if (name1.equals(name2)) {
                            matching = distanceTable.addMatching(abstractTreeNode1, abstractTreeNode2, parentMatching);
                            isMatched = true;
                        }
                    }
                } else if (abstractTreeNode1 instanceof AttributeNode && abstractTreeNode2 instanceof AttributeNode) {
                    String attributeName1 = ((AttributeNode) abstractTreeNode1).getAttributeName();
                    String attributeName2 = ((AttributeNode) abstractTreeNode2).getAttributeName();
                    if (attributeName1.equals(attributeName2)) {
                        matching = distanceTable.addMatching(abstractTreeNode1, abstractTreeNode2, parentMatching);
                        isMatched = true;
                    }
                } else if (abstractTreeNode1 instanceof TextNode && abstractTreeNode2 instanceof TextNode) {
                    matching = distanceTable.addMatching(abstractTreeNode1, abstractTreeNode2, parentMatching);
                    isMatched = true;
                }

                if (isMatched && abstractTreeNode1.hasChildren() && abstractTreeNode2.hasChildren()) {
                    calculateBuildingMatchesTopDown(abstractTreeNode1.getChildren(), abstractTreeNode2.getChildren(), matching);
                }
            }
        }
    }

    private void addPolygonsChildrenMatchingToDistanceTable(ElementNode<?> node1, ElementNode<?> node2, Matching parentMatching) {

        for (AbstractTreeNode abstractTreeNode1 : node1.getChildren()) {
            for (AbstractTreeNode abstractTreeNode2 : node2.getChildren()) {
                if (abstractTreeNode1 instanceof ElementNode<?> && abstractTreeNode2 instanceof ElementNode<?>) {
                    ElementNode elementNode1 = (ElementNode) abstractTreeNode1;
                    ElementNode elementNode2 = (ElementNode) abstractTreeNode2;
                    String name1 = elementNode1.getName();
                    String name2 = elementNode2.getName();
                    if (name1.equals(name2)) {
                        Matching m = distanceTable.addMatching(abstractTreeNode1, abstractTreeNode2, parentMatching);
                        addPolygonsChildrenMatchingToDistanceTable(elementNode1, elementNode2, m);
                        addChangedID(elementNode1, elementNode2);
                    }
                } else if (abstractTreeNode1 instanceof AttributeNode && abstractTreeNode2 instanceof AttributeNode) {
                    String attributeName1 = ((AttributeNode) abstractTreeNode1).getAttributeName();
                    String attributeName2 = ((AttributeNode) abstractTreeNode2).getAttributeName();
                    if (attributeName1.equals(attributeName2)) {
                        distanceTable.addMatching(abstractTreeNode1, abstractTreeNode2, parentMatching);
                    }
                } else if (abstractTreeNode1 instanceof TextNode && abstractTreeNode2 instanceof TextNode) {
                    distanceTable.addMatching(abstractTreeNode1, abstractTreeNode2, parentMatching);
                }
            }
        }

    }


    /**
     * Searches for the corresponding {@link de.igg.citygmldiff.citygmldiff.tree.model.ElementNode} which holds the given {@link org.citygml4j.model.gml.geometry.primitives.Polygon}
     * in the tree.
     *
     * @param nodes   Root nodes of the trees in which the polygon is searched.
     * @param polygon The {@link org.citygml4j.model.gml.geometry.primitives.Polygon} which is searched.
     * @return The {@link de.igg.citygmldiff.citygmldiff.tree.model.ElementNode} which hold the given polygon.
     */
    private ElementNode<Polygon> getCorrespondingElementNode(List<AbstractTreeNode> nodes, Polygon polygon) {

        ElementNode<Polygon> resultNode = null;
        for (AbstractTreeNode abstractTreeNode : nodes) {
            ElementNode<?> elementNode;
            if (abstractTreeNode instanceof ElementNode<?>) {
                elementNode = (ElementNode<?>) abstractTreeNode;
                if (elementNode.getValue() instanceof Polygon) {
                    if (elementNode.getValue().equals(polygon)) {
                        return (ElementNode<Polygon>) abstractTreeNode;
                    }
                }
                if (elementNode.hasChildren()) {
                    resultNode = getCorrespondingElementNode(elementNode.getChildren(), polygon);
                    if (resultNode != null) {
                        return resultNode;
                    }
                }
            }
        }
        return resultNode;
    }

    public Map<String, String> getChangedIDs() {
        return idChanges;
    }

    public Map<AbstractTreeNode, AbstractTreeNode> getUncertainMatchings() {
        return uncertainMatchings;
    }
}
