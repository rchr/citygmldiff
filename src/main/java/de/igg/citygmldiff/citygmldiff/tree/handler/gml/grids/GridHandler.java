package de.igg.citygmldiff.citygmldiff.tree.handler.gml.grids;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.AbstractGeometryHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.grids.Grid;
import org.citygml4j.model.gml.grids.GridLimits;

import java.util.List;

/**
 * User: richard
 * Date: 20.09.13
 * Time: 12:09
 */
public class GridHandler<T extends Grid> extends AbstractGeometryHandler<T> implements Constants {

    private static GridLimitsHandler<GridLimits> gridLimitsHandler = new GridLimitsHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        // attribute
        Integer dimension = element.getDimension();
        if (dimension != null) {
            AttributeNode dimensionNode = NodeFactory.createAttributeNode(DIMENSION, String.valueOf(dimension), node);
            node.addChild(dimensionNode);
        }

        // elements
        GridLimits limits = element.getLimits();
        if (limits != null) {
            ElementNode<GridLimits> limitsNode = NodeFactory.createElementNode(limits, LIMITS, node);
            gridLimitsHandler.addChildsToNode(limitsNode);
            node.addChild(limitsNode);
        }

        List<String> axisNames = element.getAxisName();
        if (axisNames != null) {
            processAxisNames(axisNames, node);
        }

    }

    private void processAxisNames(List<String> axisNames, ElementNode<T> node) {
        for (String axisName : axisNames) {
            ElementNode<String> axisNameNode = NodeFactory.createElementNode(axisName, AXIS_NAME, node);
            TextNode axisNameTextNode = new TextNode(axisName, axisNameNode);
            axisNameNode.addChild(axisNameTextNode);
            node.addChild(axisNameNode);
        }
    }
}
