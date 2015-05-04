package de.igg.citygmldiff.citygmldiff.tree.handler.gml.feature;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.EnvelopeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.primitives.Envelope;

/**
 * User: richard
 * Date: 22.07.13
 * Time: 13:56
 */
public class BoundingShapeHandler<T extends BoundingShape> implements TreeNodeHandler<T>, Constants {

    private static EnvelopeHandler<Envelope> envelopeHandler = new EnvelopeHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();
        Envelope envelope = element.getEnvelope();
        String signature = Utils.buildSignature(node, envelope, ENVELOPE);
        ElementNode<Envelope> envelopeNode = new ElementNode<>(envelope, ENVELOPE, signature, node);
        envelopeHandler.addChildsToNode(envelopeNode);
        node.addChild(envelopeNode);
    }
}


