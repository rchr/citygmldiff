package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.aggregates.MultiCurve;
import org.citygml4j.model.gml.geometry.aggregates.MultiCurveProperty;

public class MultiCurvePropertyHandler<T extends MultiCurveProperty> implements
        TreeNodeHandler<T>, Constants {

    private static MultiCurveHandler<MultiCurve> multiCurveHander = new MultiCurveHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        MultiCurve multiCurve = element.getMultiCurve();
        if (multiCurve == null) {
//			AbstractGeometry abstractGeometry = Utils.resolveXlinkGeometry(element);
//			multiCurve = (MultiCurve) abstractGeometry;
            Utils.addXlinkFeature(node);
        } else {
            String signature = Utils.buildSignature(node, multiCurve, MULTI_CURVE);
            ElementNode<MultiCurve> multiCurveNode = new ElementNode<>(
                    multiCurve, MULTI_CURVE, signature, node);
            multiCurveHander.addChildsToNode(multiCurveNode);
            node.addChild(multiCurveNode);
        }
    }

}
