package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.LineStringSegment;
import org.citygml4j.model.gml.geometry.primitives.LineStringSegmentArrayProperty;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 16:02
 */
public class LineStringSegmentArrayPropertyHandler<T extends LineStringSegmentArrayProperty> implements TreeNodeHandler<T> {

    private static LineStringSegmentHandler<LineStringSegment> lineStringSegmentHandler = new LineStringSegmentHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        List<LineStringSegment> lineStringSegments = element.getLineStringSegment();
        if (lineStringSegments != null) {
            processLineStringSegments(lineStringSegments, node);
        }
    }

    private void processLineStringSegments(List<LineStringSegment> lineStringSegments, ElementNode<T> node) {
        for (LineStringSegment lineStringSegment : lineStringSegments) {
            ElementNode<LineStringSegment> lineStringSegmentElementNode = NodeFactory.createElementNode(lineStringSegment, "LineStringSegment", node);
            lineStringSegmentHandler.addChildsToNode(lineStringSegmentElementNode);
            node.addChild(lineStringSegmentElementNode);
        }
    }
}
