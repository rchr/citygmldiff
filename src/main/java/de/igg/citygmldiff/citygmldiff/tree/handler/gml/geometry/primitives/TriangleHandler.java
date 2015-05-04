package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.AbstractRingProperty;
import org.citygml4j.model.gml.geometry.primitives.SurfaceInterpolation;
import org.citygml4j.model.gml.geometry.primitives.Triangle;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 15:48
 */
public class TriangleHandler<T extends Triangle> extends AbstractSurfacePatchHandler<T> {

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
