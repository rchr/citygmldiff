package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core.AbstractCityObjectHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core.ImplicitRepresentationPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates.MultiSurfacePropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.building.AbstractOpening;
import org.citygml4j.model.citygml.core.ImplicitRepresentationProperty;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurfaceProperty;

import java.util.List;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 12:15
 */
public class AbstractOpeningHandler<T extends AbstractOpening> extends AbstractCityObjectHandler<T> implements Constants {

    private static MultiSurfacePropertyHandler<MultiSurfaceProperty> multiSurfacePropertyHandler = new MultiSurfacePropertyHandler<>();
    private static ImplicitRepresentationPropertyHandler<ImplicitRepresentationProperty> implicitRepresentationPropertyHandler = new ImplicitRepresentationPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        MultiSurfaceProperty lod3MultiSurface = element.getLod3MultiSurface();
        if (lod3MultiSurface != null) {
            ElementNode<MultiSurfaceProperty> multiSurfacePropertyElementNode = NodeFactory.createElementNode(lod3MultiSurface, LOD_3_MULTI_SURFACE, node);
            multiSurfacePropertyHandler.addChildsToNode(multiSurfacePropertyElementNode);
            node.addChild(multiSurfacePropertyElementNode);
        }

        MultiSurfaceProperty lod4MultiSurface = element.getLod4MultiSurface();
        if (lod4MultiSurface != null) {
            ElementNode<MultiSurfaceProperty> multiSurfacePropertyElementNode = NodeFactory.createElementNode(lod4MultiSurface, LOD_4_MULTI_SURFACE, node);
            multiSurfacePropertyHandler.addChildsToNode(multiSurfacePropertyElementNode);
            node.addChild(multiSurfacePropertyElementNode);
        }

        ImplicitRepresentationProperty lod3ImplicitRepresentation = element.getLod3ImplicitRepresentation();
        if (lod3ImplicitRepresentation != null) {
            ElementNode<ImplicitRepresentationProperty> elementNode = NodeFactory.createElementNode(lod3ImplicitRepresentation, LOD_3_MULTI_SURFACE, node);
            implicitRepresentationPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        ImplicitRepresentationProperty lod4ImplicitRepresentation = element.getLod4ImplicitRepresentation();
        if (lod4ImplicitRepresentation != null) {
            ElementNode<ImplicitRepresentationProperty> elementNode = NodeFactory.createElementNode(lod4ImplicitRepresentation, LOD_4_MULTI_SURFACE, node);
            implicitRepresentationPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        List<ADEComponent> genericApplicationPropertyOfOpening = element.getGenericApplicationPropertyOfOpening();

    }
}
