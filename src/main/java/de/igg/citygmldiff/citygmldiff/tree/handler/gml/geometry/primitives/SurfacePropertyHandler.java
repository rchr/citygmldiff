package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.AbstrSurfaceProcessor;
import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.primitives.AbstractSurface;
import org.citygml4j.model.gml.geometry.primitives.SurfaceProperty;

/**
 * User: richard Date: 07.08.13 Time: 11:58
 */
public class SurfacePropertyHandler<T extends SurfaceProperty> implements
        TreeNodeHandler<T>, Constants {

    private static AbstrSurfaceProcessor<AbstractSurface> abstrSurfaceProcessor = new AbstrSurfaceProcessor<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        AbstractSurface abstractSurface = element.getSurface();
        if (abstractSurface == null) {
            Utils.addXlinkFeature(node);
        } else {
            abstrSurfaceProcessor.processAbstractGeometry(abstractSurface, node);
        }
    }

}
