package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.OrientableSurface;
import org.citygml4j.model.gml.geometry.primitives.Sign;
import org.citygml4j.model.gml.geometry.primitives.SurfaceProperty;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 13:35
 */
public class OrientableSurfaceHandler<T extends OrientableSurface> extends AbstractSurfaceHandler<T> {

    private static SurfacePropertyHandler<SurfaceProperty> surfacePropertyHandler = new SurfacePropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        Sign orientation = element.getOrientation();
        if (orientation != null) {
            AttributeNode signNode = NodeFactory.createAttributeNode("orientation", orientation.getValue(), node);
            node.addChild(signNode);
        }

        SurfaceProperty baseSurface = element.getBaseSurface();
        if (baseSurface != null) {
            ElementNode<SurfaceProperty> surfacePropertyElementNode = NodeFactory.createElementNode(baseSurface, "baseSurface", node);
            surfacePropertyHandler.addChildsToNode(surfacePropertyElementNode);
            node.addChild(surfacePropertyElementNode);
        }
    }
}
