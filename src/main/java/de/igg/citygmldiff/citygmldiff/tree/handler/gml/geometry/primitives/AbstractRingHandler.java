package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.AbstractGeometryHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.gml.geometry.primitives.AbstractRing;

/**
 * User: richard
 * Date: 07.08.13
 * Time: 12:50
 */
public abstract class AbstractRingHandler<T extends AbstractRing> extends AbstractGeometryHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);


    }
}
