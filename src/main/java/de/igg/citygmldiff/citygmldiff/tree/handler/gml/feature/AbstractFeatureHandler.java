package de.igg.citygmldiff.citygmldiff.tree.handler.gml.feature;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.base.GMLHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.apache.log4j.Logger;
import org.citygml4j.model.gml.feature.AbstractFeature;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.feature.LocationProperty;

/**
 * User: richard
 * Date: 22.07.13
 * Time: 12:46
 */

/**
 * Class that handles {@link AbstractFeature}s.
 *
 * @param <T>
 */
public abstract class AbstractFeatureHandler<T extends AbstractFeature> extends
        GMLHandler<T> implements Constants {

    private static final Logger LOGGER = Logger.getLogger(AbstractFeatureHandler.class);
    private static BoundingShapeHandler<BoundingShape> boundingShapeHandler = new BoundingShapeHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);
        T element = node.getValue();
        BoundingShape boundingShape = element.getBoundedBy();
        if (boundingShape != null) {
            ElementNode<BoundingShape> boundingShapeNode = NodeFactory
                    .createElementNode(boundingShape, GML_BOUNDED_BY, node);
            boundingShapeHandler.addChildsToNode(boundingShapeNode);
            node.addChild(boundingShapeNode);
        }

        LocationProperty locationProperty = element.getLocation();
        if (locationProperty != null) {
            LOGGER.warn("Found deprecated locationProperty in " + node.getSignature() + ". Will not use it.");
        }

    }
}
