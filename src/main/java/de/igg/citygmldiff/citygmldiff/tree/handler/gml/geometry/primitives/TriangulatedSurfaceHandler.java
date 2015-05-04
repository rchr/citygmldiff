package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.gml.geometry.primitives.TriangulatedSurface;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 15:56
 */
public class TriangulatedSurfaceHandler<T extends TriangulatedSurface> extends SurfaceHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);
    }
}
