package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.gml.geometry.primitives.AbstractSurface;

/**
 * User: richard
 * Date: 05.08.13
 * Time: 15:39
 */
public abstract class AbstractSurfaceHandler<T extends AbstractSurface> extends AbstractGeometricPrimitiveHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);
    }
}
