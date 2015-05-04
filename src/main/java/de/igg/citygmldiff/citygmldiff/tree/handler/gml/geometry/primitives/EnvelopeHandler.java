package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.primitives.DirectPosition;
import org.citygml4j.model.gml.geometry.primitives.Envelope;

/**
 * User: richard
 * Date: 22.07.13
 * Time: 13:03
 */
public class EnvelopeHandler<T extends Envelope> implements TreeNodeHandler<T> {

    private static DirectPositionHandler<DirectPosition> directPositionHandler = new DirectPositionHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        String srsName = element.getSrsName();
        String signature = Utils.buildSignature(node, srsName, srsName);
        AttributeNode srsNameNode = new AttributeNode(Utils.SRS_NAME, srsName, signature, node);
        node.addChild(srsNameNode);

        Integer srs = element.getSrsDimension();
        String srsString = String.valueOf(srs);
        String srsDimSignature = Utils.buildSignature(node, srsString, srsString);
        AttributeNode srsDimensionNode = new AttributeNode(Utils.SRS_DIMENSION, srsString, srsDimSignature, node);
        node.addChild(srsDimensionNode);

        DirectPosition lowerCorner = element.getLowerCorner();
        String lowerCornerSignature = Utils.buildSignature(node, lowerCorner, Utils.LOWER_CORNER);
        ElementNode<DirectPosition> lowerCornerNode = new ElementNode<>(
                lowerCorner, Utils.LOWER_CORNER, lowerCornerSignature, node);
        directPositionHandler.addChildsToNode(lowerCornerNode);
        node.addChild(lowerCornerNode);

        DirectPosition upperCorner = element.getUpperCorner();
        String upperCornerSignature = Utils.buildSignature(node, upperCorner, Utils.UPPER_CORNER);
        ElementNode<DirectPosition> upperCornerNode = new ElementNode<>(
                upperCorner, Utils.UPPER_CORNER, upperCornerSignature, node);
        directPositionHandler.addChildsToNode(upperCornerNode);
        node.addChild(upperCornerNode);
    }
}
