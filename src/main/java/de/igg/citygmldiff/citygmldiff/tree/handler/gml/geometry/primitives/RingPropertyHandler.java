package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.primitives.AbstractRing;
import org.citygml4j.model.gml.geometry.primitives.AbstractRingProperty;
import org.citygml4j.model.gml.geometry.primitives.LinearRing;
import org.citygml4j.model.gml.geometry.primitives.Ring;

/**
 * User: richard Date: 07.08.13 Time: 12:50
 */
public class RingPropertyHandler<T extends AbstractRingProperty> implements
        TreeNodeHandler<T> {

    private static LinearRingHandler<LinearRing> linearRingHandler = new LinearRingHandler<>();
    private static RingHandler<Ring> ringHandler = new RingHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        AbstractRing abstractRing = element.getRing();
        if (abstractRing instanceof LinearRing) {
            LinearRing linearRing = (LinearRing) abstractRing;
            String name = Constants.LINEAR_RING;
            String signature = Utils.buildSignature(node, linearRing, name);
            ElementNode<LinearRing> ringNode = new ElementNode<>(
                    linearRing, name, signature, node);
            linearRingHandler.addChildsToNode(ringNode);
            node.addChild(ringNode);
        } else if (abstractRing instanceof Ring) {
            Ring ring = (Ring) abstractRing;
            ElementNode<Ring> ringElementNode = NodeFactory.createElementNode(ring, "Ring", node);
            ringHandler.addChildsToNode(ringElementNode);
            node.addChild(ringElementNode);
        }
    }
}
