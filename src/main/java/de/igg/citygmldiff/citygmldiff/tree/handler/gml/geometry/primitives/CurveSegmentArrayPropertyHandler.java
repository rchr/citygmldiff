package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.apache.log4j.Logger;
import org.citygml4j.model.gml.geometry.primitives.AbstractCurveSegment;
import org.citygml4j.model.gml.geometry.primitives.CurveSegmentArrayProperty;
import org.citygml4j.model.gml.geometry.primitives.LineStringSegment;

import java.util.List;

public class CurveSegmentArrayPropertyHandler<T extends CurveSegmentArrayProperty>
        implements TreeNodeHandler<T> {

    private static final Logger LOGGER = Logger
            .getLogger(CurveSegmentArrayPropertyHandler.class);
    private static LineStringSegmentHandler<LineStringSegment> lineStringSegmentHandler = new LineStringSegmentHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();
        List<? extends AbstractCurveSegment> curveSegments = element
                .getCurveSegment();
        if (curveSegments != null) {
            for (AbstractCurveSegment abstractCurveSegment : curveSegments) {
                processAbstractCurveSegment(abstractCurveSegment, node);
            }
        }

    }

    private void processAbstractCurveSegment(
            AbstractCurveSegment abstractCurveSegment, ElementNode<T> node) {

        if (abstractCurveSegment instanceof LineStringSegment) {
            LineStringSegment segment = (LineStringSegment) abstractCurveSegment;
            ElementNode<LineStringSegment> segmentNode = NodeFactory
                    .createElementNode(segment, "lineStringSegment", node);
            lineStringSegmentHandler.addChildsToNode(segmentNode);
            node.addChild(segmentNode);
        } else {
            LOGGER.warn("Unknown type of curveSegment.");
        }
    }

}
