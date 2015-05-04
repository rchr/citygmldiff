package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.AbstractRingProperty;
import org.citygml4j.model.gml.geometry.primitives.Rectangle;
import org.citygml4j.model.gml.geometry.primitives.SurfaceInterpolation;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 15:01
 */
public class RectangleHandler<T extends Rectangle> extends AbstractSurfacePatchHandler<T> {

    private static AbstractRingPropertyHandler<AbstractRingProperty> abstractRingPropertyHandler = new AbstractRingPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        SurfaceInterpolation interpolation = element.getInterpolation();
        if (interpolation != null) {
            AttributeNode interpolationNode = NodeFactory.createAttributeNode("interpolation", interpolation.getValue(), node);
            node.addChild(interpolationNode);
        }

        AbstractRingProperty exterior = element.getExterior();
        if (exterior != null) {
            ElementNode<AbstractRingProperty> exteriorNode = NodeFactory.createElementNode(exterior, "exterior", node);
            abstractRingPropertyHandler.addChildsToNode(exteriorNode);
            node.addChild(exteriorNode);
        }
    }
}
