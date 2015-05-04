package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.AbstractCurveHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.CurvePropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.complexes.CompositeCurve;
import org.citygml4j.model.gml.geometry.primitives.CurveProperty;

import java.util.List;

public class CompositeCurveHandler<T extends CompositeCurve> extends
        AbstractCurveHandler<T> implements Constants {

    private static CurvePropertyHandler<CurveProperty> curvePropertyHandler = new CurvePropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);
        T element = node.getValue();
        List<CurveProperty> curveMembers = element.getCurveMember();
        if (curveMembers != null) {
            processCurveMembers(curveMembers, node);
        }
    }

    private void processCurveMembers(List<CurveProperty> curveMembers,
                                     ElementNode<T> node) {
        for (CurveProperty curveProperty : curveMembers) {
            ElementNode<CurveProperty> curveNode = NodeFactory.createElementNode(
                    curveProperty, CURVE_MEMBER, node);
            curvePropertyHandler.addChildsToNode(curveNode);
            node.addChild(curveNode);
        }
    }

}
