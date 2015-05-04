package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.AbstractCurveSegment;

public class AbstractCurveSegmentHandler<T extends AbstractCurveSegment>
        implements TreeNodeHandler<T> {

    private static final String NUM_DERIVATIVES_AT_START = "numDerivativesAtStart";
    private static final String NUM_DERIVATIVES_AT_END = "numDerivativesAtEnd";
    private static final String NUM_DERIVATIVES_INTERIOR = "numDerivativesInterior";

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        // attributes
        Integer numDerivativeInterior = element.getNumDerivativeInterior();
        if (numDerivativeInterior != null) {
            AttributeNode interiorNode = NodeFactory.createAttributeNode(
                    NUM_DERIVATIVES_INTERIOR,
                    String.valueOf(numDerivativeInterior), node);
            node.addChild(interiorNode);
        }

        Integer numDerivativesAtEnd = element.getNumDerivativesAtEnd();
        if (numDerivativesAtEnd != null) {
            AttributeNode endNode = NodeFactory.createAttributeNode(
                    NUM_DERIVATIVES_AT_END, String.valueOf(numDerivativesAtEnd),
                    node);
            node.addChild(endNode);
        }

        Integer numDerivativesAtStart = element.getNumDerivativesAtStart();
        if (numDerivativesAtStart != null) {
            AttributeNode startNode = NodeFactory.createAttributeNode(
                    NUM_DERIVATIVES_AT_START,
                    String.valueOf(numDerivativesAtStart), node);
            node.addChild(startNode);
        }

    }

}
