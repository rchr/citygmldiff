package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import org.apache.log4j.Logger;
import org.citygml4j.model.gml.geometry.primitives.CurveInterpolation;

public class CurveInterpolationHandler<T extends Enum<CurveInterpolation>>
        implements TreeNodeHandler<T> {

    private static final Logger LOGGER = Logger
            .getLogger(CurveInterpolationHandler.class);

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        if (element instanceof CurveInterpolation) {
            CurveInterpolation curveInter = (CurveInterpolation) element;
            String value = curveInter.getValue();
            TextNode textNode = new TextNode(value, node);
            node.addChild(textNode);

        } else {
            LOGGER.warn("Unknown interpolation type.");
        }
    }

}
