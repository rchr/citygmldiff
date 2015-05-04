package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.CurveProperty;
import org.citygml4j.model.gml.geometry.primitives.Ring;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 14:21
 */
public class RingHandler<T extends Ring> extends AbstractRingHandler<T> {

    private static CurvePropertyHandler<CurveProperty> curvePropertyHandler = new CurvePropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();
        List<CurveProperty> curveMember = element.getCurveMember();
        if (curveMember != null) {
            processCurveMember(curveMember, node);
        }

    }

    private void processCurveMember(List<CurveProperty> curveMember, ElementNode<T> node) {
        for (CurveProperty curveProperty : curveMember) {
            ElementNode<CurveProperty> curvePropertyElementNode = NodeFactory.createElementNode(curveProperty, "curveMember", node);
            curvePropertyHandler.addChildsToNode(curvePropertyElementNode);
            node.addChild(curvePropertyElementNode);
        }
    }
}
