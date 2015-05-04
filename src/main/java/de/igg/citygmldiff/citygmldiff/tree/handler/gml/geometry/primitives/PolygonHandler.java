package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.primitives.AbstractRingProperty;
import org.citygml4j.model.gml.geometry.primitives.Polygon;

import java.util.List;

/**
 * User: richard Date: 07.08.13 Time: 12:03
 */
public class PolygonHandler<T extends Polygon> extends AbstractSurfaceHandler<T> {

    private static RingPropertyHandler<AbstractRingProperty> ringPropertyHandler = new RingPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        super.addChildsToNode(node);

        T element = node.getValue();
        AbstractRingProperty exterior = element.getExterior();
        String signature = Utils.buildSignature(node, exterior, "exterior");
        ElementNode<AbstractRingProperty> exteriorNode = new ElementNode<>(
                exterior, "exterior", signature,
                node);
        ringPropertyHandler.addChildsToNode(exteriorNode);
        node.addChild(exteriorNode);

        List<AbstractRingProperty> interior = element.getInterior();

    }
}
