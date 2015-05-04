package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.AbstrSurfaceProcessor;
import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.gml.geometry.primitives.AbstractSurface;
import org.citygml4j.model.gml.geometry.primitives.SurfaceArrayProperty;

import java.util.List;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 10:07
 */
public class SurfaceArrayPropertyHandler<T extends SurfaceArrayProperty> implements TreeNodeHandler<T> {

    private static AbstrSurfaceProcessor<AbstractSurface> abstrSurfaceProcessor = new AbstrSurfaceProcessor<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        List<? extends AbstractSurface> surfaces = element.getSurface();
        if (surfaces != null) {
            processSurfaces(surfaces, node);
        }
    }

    private void processSurfaces(List<? extends AbstractSurface> surfaces, ElementNode<T> node) {
        for (AbstractSurface s : surfaces) {
            abstrSurfaceProcessor.processAbstractGeometry(s, node);
        }
    }
}
