package de.igg.citygmldiff.citygmldiff.editscript;

import de.igg.citygmldiff.citygmldiff.editscript.model.*;
import de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable;
import de.igg.citygmldiff.citygmldiff.matching.model.Matching;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractLeafNode;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.apache.log4j.Logger;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.gml.base.AbstractGML;

import java.util.*;

/**
 * Class to calculate the edit script.
 */
public class EditScriptCalculator {

    private static final String HASH = "#";
    private static Logger LOGGER = Logger.getLogger(EditScriptCalculator.class);
    private Set<String> deletedFeatures;
    private Set<String> insertedFeatures;

    /**
     * Calculates the edit script for the two city models from the given distance-table.
     *
     * @param cityModel1    {@link ElementNode} containing the first {@link CityModel}.
     * @param cityModel2    {@link ElementNode} containing the second {@link CityModel}.
     * @param distanceTable {@link DistanceTable} which contains the edit distances between the nodes.
     * @return The edit script.
     */
    public List<EditOperation> calculateEditScript(ElementNode<CityModel> cityModel1,
                                                   ElementNode<CityModel> cityModel2, DistanceTable distanceTable) {

        List<EditOperation> editOperations = new ArrayList<>();

        deletedFeatures = new HashSet<>();
        insertedFeatures = new HashSet<>();

        if (distanceTable == null || distanceTable.size() == 0) {
            /*
             * There is no matching or trees are equal in the distance table,
			 * thus delete model1 and insert model2
			 */
            // TODO: add check if trees are equal or there is no matching
            LOGGER.info(" Delete(" + cityModel1.getSignature() + "), Insert("
                    + cityModel2.getSignature() + ")");
        } else {
            editOperations.addAll(calculateUpdates(distanceTable));
        }
        editOperations.addAll(calculateInserts(cityModel2.getChildren(), distanceTable));
        editOperations.addAll(calculateDeletions(cityModel1.getChildren(), distanceTable));
        return editOperations;
    }

    /**
     * Calculates the updates between the two city models.
     *
     * @param distanceTable
     * @return
     */
    public List<EditOperation> calculateUpdates(DistanceTable distanceTable) {

        List<EditOperation> updates = new ArrayList<>();

        for (Map.Entry<Matching, Integer> entry : distanceTable.entrySet()) {
            Matching matching = entry.getKey();
            if ((matching.getNode1() instanceof AbstractLeafNode) && (matching.getNode2() instanceof AbstractLeafNode)) {
                Integer distance = entry.getValue();
                if (distance == null || distance == 0) {
                    // do nothing
                } else {
                    AbstractLeafNode leaf2 = (AbstractLeafNode) matching.getNode2();
                    String newValue = leaf2.getValue();
                    EditOperation update = new UpdateOperation((AbstractLeafNode) matching.getNode1(), newValue);
                    updates.add(update);
                }
            }
        }
        return updates;
    }

    /**
     * Calculates the inserts between the two city models. That means, this method determines
     * which elements are in the second city model, but not in the first one.
     *
     * @param nodes
     * @param distanceTable
     */
    public List<EditOperation> calculateInserts(List<AbstractTreeNode> nodes, DistanceTable distanceTable) {
        List<EditOperation> inserts = new ArrayList<>();
        for (AbstractTreeNode n : nodes) {
            if (!distanceTable.containsNode(n)) {
                EditOperation insertOp = new InsertOperation(n, n.getParent());
                inserts.add(insertOp);
                addInsertedObjectAndChildsToInsertetFeatures(n);
            } else if (n.hasChildren()) {
                inserts.addAll(calculateInserts(n.getChildren(), distanceTable));
            }
        }
        return inserts;
    }

    /**
     * Calculates the deletions between the two city models. That means, this method determines
     * which elements are in the first city model, but not in the second one.
     *
     * @param nodes
     * @param distanceTable
     */
    public List<EditOperation> calculateDeletions(List<AbstractTreeNode> nodes, DistanceTable distanceTable) {
        List<EditOperation> deletions = new ArrayList<>();
        for (AbstractTreeNode n : nodes) {
            if (!distanceTable.containsNode(n)) {
                EditOperation deletionOp = new DeleteOperation(n);
                deletions.add(deletionOp);
                addDeletedObjectAndChildsToDeletedFeatures(n);
            } else if (n.hasChildren()) {
                deletions.addAll(calculateDeletions(n.getChildren(), distanceTable));
            }
        }
        return deletions;
    }

    /**
     * Adds the value of the current node and the values of its children to {@link #deletedFeatures}.
     *
     * @param node
     */
    private void addDeletedObjectAndChildsToDeletedFeatures(AbstractTreeNode node) {
        if (node instanceof ElementNode<?>) {
            Object value = ((ElementNode) node).getValue();
            if (value instanceof AbstractGML) {
                String id = ((AbstractGML) value).getId();
                if (id != null && !id.equals("")) {
                    deletedFeatures.add(id);
                }
            }
            if (node.hasChildren()) {
                for (AbstractTreeNode n : node.getChildren()) {
                    addDeletedObjectAndChildsToDeletedFeatures(n);
                }
            }
        }
    }

    private void addInsertedObjectAndChildsToInsertetFeatures(AbstractTreeNode node) {
        if (node instanceof ElementNode<?>) {
            Object value = ((ElementNode) node).getValue();
            if (value instanceof AbstractGML) {
                String id = ((AbstractGML) value).getId();
                if (id != null && !id.equals("")) {
                    insertedFeatures.add(id);
                }
            }
            if (node.hasChildren()) {
                for (AbstractTreeNode n : node.getChildren()) {
                    addInsertedObjectAndChildsToInsertetFeatures(n);
                }
            }
        }
    }

    public List<EditOperation> calculateUnreferencedXLinks(Set<String> deletedFeatures, Map<String, Set<AttributeNode>> xlinkPathMap) {
        List<EditOperation> deletions = new ArrayList<>();

        for (String id : deletedFeatures) {
            if (xlinkPathMap.get(id) != null && xlinkPathMap.get(id).size() > 0) {
                if (!insertedFeatures.contains(id)) {
                    Set<AttributeNode> attributeNodes = xlinkPathMap.get(id);
                    for (AttributeNode node : attributeNodes) {
                        DeleteOperation deleteOperation = new DeleteOperation(node);
                        deletions.add(deleteOperation);
                    }
                }
            }
        }
        return deletions;
    }

    public Set<String> getDeletedFeatures() {
        return deletedFeatures;
    }

    public Set<String> getInsertedFeatures() {
        return insertedFeatures;
    }

    public List<EditOperation> caclulateXlinkUpdates(Map<String, String> xlinkUpdates, Map<String, Set<AttributeNode>> xlinkPathMap) {
        List<EditOperation> updates = new ArrayList<>();

        for (String oldId : xlinkUpdates.keySet()) {
            if (xlinkPathMap.get(oldId) != null && xlinkPathMap.get(oldId).size() > 0) {
                Set<AttributeNode> attributeNodes = xlinkPathMap.get(oldId);
                for (AttributeNode node : attributeNodes) {
                    UpdateOperation updateOperation = new UpdateOperation(node, HASH + xlinkUpdates.get(oldId));
                    updates.add(updateOperation);
                }
            }
        }
        return updates;
    }

    public List<EditOperation> calculateEditOpsForUncertainMatchings(Map<AbstractTreeNode, AbstractTreeNode> uncertainMatchings) {
        List<EditOperation> uncertainOperations = new ArrayList<>();
        for (Map.Entry<AbstractTreeNode, AbstractTreeNode> entry : uncertainMatchings.entrySet()) {
            UncertainOperation uncertainOp = new UncertainOperation(entry.getKey(), entry.getValue());
            uncertainOperations.add(uncertainOp);
        }
        return uncertainOperations;
    }

}
