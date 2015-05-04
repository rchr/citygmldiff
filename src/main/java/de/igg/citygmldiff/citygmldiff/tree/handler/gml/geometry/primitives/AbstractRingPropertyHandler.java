package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.AbstractRing;
import org.citygml4j.model.gml.geometry.primitives.AbstractRingProperty;
import org.citygml4j.model.gml.geometry.primitives.LinearRing;
import org.citygml4j.model.gml.geometry.primitives.Ring;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 15:07
 */
public class AbstractRingPropertyHandler<T extends AbstractRingProperty> implements TreeNodeHandler<T> {

    private static LinearRingHandler<LinearRing> linearRingHandler = new LinearRingHandler<>();
    private static RingHandler<Ring> ringHandler = new RingHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();
        AbstractRing abstractRing = element.getGeometry();
        if (abstractRing != null) {
            processAbstractRing(abstractRing, node);
        }

    }

    private void processAbstractRing(AbstractRing abstractRing, ElementNode<T> node) {
        if (abstractRing instanceof LinearRing) {
            LinearRing linearRing = (LinearRing) abstractRing;
            ElementNode<LinearRing> linearRingElementNode = NodeFactory.createElementNode(linearRing, Constants.LINEAR_RING, node);
            linearRingHandler.addChildsToNode(linearRingElementNode);
            node.addChild(linearRingElementNode);
        } else if (abstractRing instanceof Ring) {
            Ring ring = (Ring) abstractRing;
            ElementNode<Ring> ringElementNode = NodeFactory.createElementNode(ring, "Ring", node);
            ringHandler.addChildsToNode(ringElementNode);
            node.addChild(ringElementNode);
        }
    }
}
