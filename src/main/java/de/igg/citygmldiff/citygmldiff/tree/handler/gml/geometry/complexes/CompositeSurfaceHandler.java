package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.AbstractSurfaceHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.SurfacePropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.complexes.CompositeSurface;
import org.citygml4j.model.gml.geometry.primitives.SurfaceProperty;

import java.util.List;

/**
 * User: richard
 * Date: 19.09.13
 * Time: 20:24
 */
public class CompositeSurfaceHandler<T extends CompositeSurface> extends AbstractSurfaceHandler<T> implements Constants {

    private static SurfacePropertyHandler<SurfaceProperty> surfacePropertyHandler = new SurfacePropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<SurfaceProperty> surfaceMember = element.getSurfaceMember();
        if (surfaceMember != null) {
            processSurfaceMembers(surfaceMember, node);
        }
    }

    private void processSurfaceMembers(List<SurfaceProperty> surfaceMember, ElementNode<T> node) {
        for (SurfaceProperty surfProp : surfaceMember) {
            ElementNode<SurfaceProperty> surfacePropertyElementNode = NodeFactory.createElementNode(surfProp, SURFACE_MEMBER, node);
            surfacePropertyHandler.addChildsToNode(surfacePropertyElementNode);
            node.addChild(surfacePropertyElementNode);
        }
    }
}
