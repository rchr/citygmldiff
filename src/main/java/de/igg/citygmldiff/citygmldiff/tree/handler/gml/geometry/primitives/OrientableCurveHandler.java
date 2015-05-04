package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.CurveProperty;
import org.citygml4j.model.gml.geometry.primitives.OrientableCurve;
import org.citygml4j.model.gml.geometry.primitives.Sign;

public class OrientableCurveHandler<T extends OrientableCurve> extends AbstractCurveHandler<T> {

    private static CurvePropertyHandler<CurveProperty> curvePropertyHandler = new CurvePropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        CurveProperty baseCurve = element.getBaseCurve();
        if (baseCurve != null) {
            ElementNode<CurveProperty> curvePropertyElementNode = NodeFactory.createElementNode(baseCurve, "baseCurve", node);
            curvePropertyHandler.addChildsToNode(curvePropertyElementNode);
            node.addChild(curvePropertyElementNode);
        }

        // attribute
        Sign orientation = element.getOrientation();
        if (orientation != null) {
            AttributeNode signNode = NodeFactory.createAttributeNode("orientation", orientation.getValue(), node);
            node.addChild(signNode);
        }

    }


}
