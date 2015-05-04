package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.CurveArrayPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.CurvePropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.aggregates.MultiCurve;
import org.citygml4j.model.gml.geometry.primitives.CurveArrayProperty;
import org.citygml4j.model.gml.geometry.primitives.CurveProperty;

import java.util.List;

public class MultiCurveHandler<T extends MultiCurve> extends
        AbstractGeometricAggregateHandler<T> implements Constants {

    private static CurvePropertyHandler<CurveProperty> curvePropertyHandler = new CurvePropertyHandler<>();
    private static CurveArrayPropertyHandler<CurveArrayProperty> curveArrayPropertyHandler = new CurveArrayPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<CurveProperty> curveMember = element.getCurveMember();
        processCurveMemberList(curveMember, node);

        CurveArrayProperty curveMembers = element.getCurveMembers();
        if (curveMembers != null) {
            ElementNode<CurveArrayProperty> arrayNode = NodeFactory
                    .createElementNode(curveMembers, CURVE_MEMBERS, node);
            curveArrayPropertyHandler.addChildsToNode(arrayNode);
            node.addChild(arrayNode);
        }
    }

    private void processCurveMemberList(List<CurveProperty> curveMember,
                                        ElementNode<T> node) {
        if (curveMember != null) {
            for (CurveProperty curveProperty : curveMember) {
                ElementNode<CurveProperty> curveNode = NodeFactory.createElementNode(
                        curveProperty, CURVE_MEMBER, node);
                curvePropertyHandler.addChildsToNode(curveNode);
                node.addChild(curveNode);
            }
        }
    }

}
