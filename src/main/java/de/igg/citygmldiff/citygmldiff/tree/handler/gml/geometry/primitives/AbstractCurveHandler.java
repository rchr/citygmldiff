package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.gml.geometry.primitives.AbstractGeometricPrimitive;

public abstract class AbstractCurveHandler<T extends AbstractGeometricPrimitive>
        extends AbstractGeometricPrimitiveHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);
    }

}
