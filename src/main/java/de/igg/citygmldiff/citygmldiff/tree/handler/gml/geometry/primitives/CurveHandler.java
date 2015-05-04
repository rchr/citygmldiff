package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.Curve;
import org.citygml4j.model.gml.geometry.primitives.CurveSegmentArrayProperty;

public class CurveHandler<T extends Curve> extends AbstractCurveHandler<T> {

    private static CurveSegmentArrayPropertyHandler<CurveSegmentArrayProperty> curveSegmentArrayPropertyHandler = new CurveSegmentArrayPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        CurveSegmentArrayProperty segments = element.getSegments();
        if (segments != null) {
            ElementNode<CurveSegmentArrayProperty> segmentsNode = NodeFactory
                    .createElementNode(segments, "segments", node);
            curveSegmentArrayPropertyHandler.addChildsToNode(segmentsNode);
            node.addChild(segmentsNode);
        }

    }

}
