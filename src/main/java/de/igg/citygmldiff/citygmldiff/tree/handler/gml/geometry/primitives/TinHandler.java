package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.measures.LengthHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.ControlPoint;
import org.citygml4j.model.gml.geometry.primitives.LineStringSegmentArrayProperty;
import org.citygml4j.model.gml.geometry.primitives.Tin;
import org.citygml4j.model.gml.measures.Length;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 15:54
 */
public class TinHandler<T extends Tin> extends TriangulatedSurfaceHandler<T> {

    private static LineStringSegmentArrayPropertyHandler<LineStringSegmentArrayProperty> lineStringSegmentArrayPropertyHandler = new LineStringSegmentArrayPropertyHandler<>();
    private static LengthHandler<Length> lengthHandler = new LengthHandler<>();
    private static ControlPointHandler<ControlPoint> controlPointHandler = new ControlPointHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<LineStringSegmentArrayProperty> stopLines = element.getStopLines();
        if (stopLines != null) {
            processStopLines(stopLines, node);
        }

        List<LineStringSegmentArrayProperty> breakLines = element.getBreakLines();
        if (breakLines != null) {
            processBreakLines(breakLines, node);
        }

        Length maxLength = element.getMaxLength();
        if (maxLength != null) {
            ElementNode<Length> lengthNode = NodeFactory.createElementNode(maxLength, "maxLenght", node);
            lengthHandler.addChildsToNode(lengthNode);
            node.addChild(lengthNode);
        }

        ControlPoint controlPoint = element.getControlPoint();
        if (controlPoint != null) {
            ElementNode<ControlPoint> controlPointElementNode = NodeFactory.createElementNode(controlPoint, "controlPoint", node);
            controlPointHandler.addChildsToNode(controlPointElementNode);
            node.addChild(controlPointElementNode);
        }
    }

    private void processBreakLines(List<LineStringSegmentArrayProperty> breakLines, ElementNode<T> node) {
        for (LineStringSegmentArrayProperty breakLine : breakLines) {
            ElementNode<LineStringSegmentArrayProperty> breakLineNode = NodeFactory.createElementNode(breakLine, "breakLines", node);
            lineStringSegmentArrayPropertyHandler.addChildsToNode(breakLineNode);
            node.addChild(breakLineNode);
        }
    }

    private void processStopLines(List<LineStringSegmentArrayProperty> stopLines, ElementNode<T> node) {
        for (LineStringSegmentArrayProperty stopLine : stopLines) {
            ElementNode<LineStringSegmentArrayProperty> stopLineNode = NodeFactory.createElementNode(stopLine, "stopLines", node);
            lineStringSegmentArrayPropertyHandler.addChildsToNode(stopLineNode);
            node.addChild(stopLineNode);
        }
    }
}
