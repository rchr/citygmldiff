package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.PremiseNumberRange;
import org.citygml4j.model.xal.PremiseNumberRangeFrom;
import org.citygml4j.model.xal.PremiseNumberRangeTo;

public class PremiseNumberRangeHandler<T extends PremiseNumberRange> implements
        TreeNodeHandler<T>, XalConstants {

    private static PremiseNumberRangeFromHandler<PremiseNumberRangeFrom> premiseNumberRangeFromHandler = new PremiseNumberRangeFromHandler<>();
    private static PremiseNumberRangeToHandler<PremiseNumberRangeTo> premiseNumberRangeToHandler = new PremiseNumberRangeToHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        // attributes
        String type = element.getType();
        if (type != null) {
            AttributeNode typeNode = NodeFactory
                    .createAttributeNode(TYPE, type, node);
            node.addChild(typeNode);
        }

        String rangeType = element.getRangeType();
        if (rangeType != null) {
            AttributeNode rangeTypeNode = NodeFactory.createAttributeNode(RANGE_TYPE,
                    rangeType, node);
            node.addChild(rangeTypeNode);
        }

        String indicator = element.getIndicator();
        if (indicator != null) {
            AttributeNode indicatorNode = NodeFactory.createAttributeNode(INDICATOR,
                    indicator, node);
            node.addChild(indicatorNode);
        }

        String indicatorOccurrence = element.getIndicatorOccurence();
        if (indicatorOccurrence != null) {
            AttributeNode indicatorOccurenceNode = NodeFactory.createAttributeNode(
                    INDICATOR_OCCURENCE, indicatorOccurrence, node);
            node.addChild(indicatorOccurenceNode);
        }

        String numberRangeOccurrence = element.getNumberRangeOccurence();
        if (numberRangeOccurrence != null) {
            AttributeNode numberRangeOccurrenceNode = NodeFactory
                    .createAttributeNode(NUMBER_RANGE_OCCURENCE,
                            numberRangeOccurrence, node);
            node.addChild(numberRangeOccurrenceNode);
        }

        String separator = element.getSeparator();
        if (separator != null) {
            AttributeNode separatorNode = NodeFactory.createAttributeNode(SEPARATOR,
                    separator, node);
            node.addChild(separatorNode);
        }

        // elements
        PremiseNumberRangeFrom premiseNumberRangeFrom = element
                .getPremiseNumberRangeFrom();
        if (premiseNumberRangeFrom != null) {
            ElementNode<PremiseNumberRangeFrom> fromNode = NodeFactory
                    .createElementNode(premiseNumberRangeFrom,
                            PREMISE_NUMBER_FROM, node);
            premiseNumberRangeFromHandler.addChildsToNode(fromNode);
            node.addChild(fromNode);
        }

        PremiseNumberRangeTo premiseNumberRangeTo = element
                .getPremiseNumberRangeTo();
        if (premiseNumberRangeTo != null) {
            ElementNode<PremiseNumberRangeTo> toNode = NodeFactory.createElementNode(
                    premiseNumberRangeTo, PREMISE_NUMBER_TO, node);
            premiseNumberRangeToHandler.addChildsToNode(toNode);
            node.addChild(toNode);
        }
    }

}
