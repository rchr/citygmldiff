package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.gml.geometry.primitives.AbstractSurfacePatch;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 15:04
 */
public class AbstractSurfacePatchHandler<T extends AbstractSurfacePatch> implements TreeNodeHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

    }
}
