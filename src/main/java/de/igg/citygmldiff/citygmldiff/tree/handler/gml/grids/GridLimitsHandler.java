package de.igg.citygmldiff.citygmldiff.tree.handler.gml.grids;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.grids.GridEnvelope;
import org.citygml4j.model.gml.grids.GridLimits;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 18:53
 */
public class GridLimitsHandler<T extends GridLimits> implements TreeNodeHandler<T>, Constants {

    private static GridEnvelopeHandler<GridEnvelope> gridEnvelopeHandler = new GridEnvelopeHandler<GridEnvelope>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        GridEnvelope gridEnvelope = element.getGridEnvelope();
        if (gridEnvelope != null) {
            ElementNode<GridEnvelope> envelopeElementNode = NodeFactory.createElementNode(gridEnvelope, GRID_ENVELOPE, node);
            gridEnvelopeHandler.addChildsToNode(envelopeElementNode);
            node.addChild(envelopeElementNode);
        }
    }
}
