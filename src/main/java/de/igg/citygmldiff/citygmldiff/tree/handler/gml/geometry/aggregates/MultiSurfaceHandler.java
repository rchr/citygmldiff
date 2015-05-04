package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.SurfaceArrayPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.SurfacePropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurface;
import org.citygml4j.model.gml.geometry.primitives.SurfaceArrayProperty;
import org.citygml4j.model.gml.geometry.primitives.SurfaceProperty;

import java.util.List;

/**
 * User: richard Date: 05.08.13 Time: 14:41
 */
public class MultiSurfaceHandler<T extends MultiSurface> extends AbstractGeometricAggregateHandler<T> implements
        TreeNodeHandler<T>, Constants {

    private static SurfacePropertyHandler<SurfaceProperty> surfacePropertyHandler = new SurfacePropertyHandler<>();
    private static SurfaceArrayPropertyHandler<SurfaceArrayProperty> surfaceArrayPropertyHandler = new SurfaceArrayPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();
        super.addChildsToNode(node);
        List<SurfaceProperty> surfaceMember = element.getSurfaceMember();
        if (surfaceMember != null) {
            for (SurfaceProperty surfProp : surfaceMember) {
                // handle each surface
                String signature = Utils.buildSignature(node, surfProp,
                        Constants.SURFACE_MEMBER);
                ElementNode<SurfaceProperty> surfacePropertyNode = new ElementNode<>(
                        surfProp, SURFACE_MEMBER, signature, node);
                surfacePropertyHandler.addChildsToNode(surfacePropertyNode);
                node.addChild(surfacePropertyNode);
            }
        }

        SurfaceArrayProperty surfaceMembers = element.getSurfaceMembers();
        if (surfaceMembers != null) {
            ElementNode<SurfaceArrayProperty> surfaceArrayPropertyElementNode = NodeFactory.createElementNode(surfaceMembers, SURFACE_MEMBERS, node);
            surfaceArrayPropertyHandler.addChildsToNode(surfaceArrayPropertyElementNode);
            node.addChild(surfaceArrayPropertyElementNode);
        }

    }
}
