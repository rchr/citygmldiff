package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.Surface;
import org.citygml4j.model.gml.geometry.primitives.SurfacePatchArrayProperty;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 14:27
 */
public class SurfaceHandler<T extends Surface> extends AbstractSurfaceHandler<T> {

    private static SurfacePatchArrayPropertyHandler<SurfacePatchArrayProperty> surfacePatchArrayPropertyHandler = new SurfacePatchArrayPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        SurfacePatchArrayProperty patches = element.getPatches();
        if (patches != null) {
            ElementNode<SurfacePatchArrayProperty> surfacePatchArrayPropertyElementNode = NodeFactory.createElementNode(patches, "patches", node);
            surfacePatchArrayPropertyHandler.addChildsToNode(surfacePatchArrayPropertyElementNode);
            node.addChild(surfacePatchArrayPropertyElementNode);
        }
    }
}
