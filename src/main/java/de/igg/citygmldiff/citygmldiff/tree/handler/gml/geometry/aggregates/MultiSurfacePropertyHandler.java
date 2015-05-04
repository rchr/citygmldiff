package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurface;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurfaceProperty;

/**
 * User: richard
 * Date: 07.08.13
 * Time: 11:45
 */
public class MultiSurfacePropertyHandler<T extends MultiSurfaceProperty> implements TreeNodeHandler<T>, Constants {

    private static MultiSurfaceHandler<MultiSurface> multiSurfaceHandler = new MultiSurfaceHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        MultiSurface multiSurface = element.getMultiSurface();
        if (multiSurface == null) {
            Utils.addXlinkFeature(node);
        } else {
            String signature = Utils.buildSignature(node, multiSurface, MULTI_SURFACE);
            ElementNode<MultiSurface> multiSurfaceNode = new ElementNode<>(multiSurface, MULTI_SURFACE, signature, node);
            multiSurfaceHandler.addChildsToNode(multiSurfaceNode);
            node.addChild(multiSurfaceNode);
        }
    }
}
