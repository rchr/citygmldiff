package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core.AbstractCityObjectHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates.MultiSurfacePropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.building.AbstractBoundarySurface;
import org.citygml4j.model.citygml.building.OpeningProperty;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurfaceProperty;

import java.util.List;

/**
 * Abstract class to handle {@link AbstractBoundarySurface}s.
 *
 * @param <T>
 */
public abstract class AbstractBoundarySurfaceHandler<T extends AbstractBoundarySurface>
        extends AbstractCityObjectHandler<T> implements TreeNodeHandler<T>, Constants {

    private static MultiSurfacePropertyHandler<MultiSurfaceProperty> multiSurfacePropertyHandler = new MultiSurfacePropertyHandler<>();
    private static OpeningPropertyHandler<OpeningProperty> openingPropertyHandler = new OpeningPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        super.addChildsToNode(node);

        T element = node.getValue();
        MultiSurfaceProperty lod2MultiSurfaceProperty = element
                .getLod2MultiSurface();
        if (lod2MultiSurfaceProperty != null) {
            String signature = Utils.buildSignature(node, lod2MultiSurfaceProperty, LOD_2_MULTI_SURFACE);
            ElementNode<MultiSurfaceProperty> multiSurfacePropertyNode = new ElementNode<>(
                    lod2MultiSurfaceProperty, LOD_2_MULTI_SURFACE, signature,
                    node);
            multiSurfacePropertyHandler
                    .addChildsToNode(multiSurfacePropertyNode);
            node.addChild(multiSurfacePropertyNode);
        }

        MultiSurfaceProperty lod3MultiSurface = element.getLod3MultiSurface();
        if (lod3MultiSurface != null) {
            ElementNode<MultiSurfaceProperty> lod3MultiSurfaceNode = NodeFactory.createElementNode(lod3MultiSurface, LOD_3_MULTI_SURFACE, node);
            multiSurfacePropertyHandler.addChildsToNode(lod3MultiSurfaceNode);
            node.addChild(lod3MultiSurfaceNode);
        }

        MultiSurfaceProperty lod4MultiSurface = element.getLod4MultiSurface();
        if (lod4MultiSurface != null) {
            ElementNode<MultiSurfaceProperty> lod4MultiSurfaceNode = NodeFactory.createElementNode(lod4MultiSurface, LOD_4_MULTI_SURFACE, node);
            multiSurfacePropertyHandler.addChildsToNode(lod4MultiSurfaceNode);
            node.addChild(lod4MultiSurfaceNode);
        }

        List<OpeningProperty> opening = element.getOpening();
        if (opening != null) {
            processOpenings(opening, node);
        }

        List<ADEComponent> ade = element
                .getGenericApplicationPropertyOfBoundarySurface();

    }

    private void processOpenings(List<OpeningProperty> openings, ElementNode<T> node) {
        for (OpeningProperty o : openings) {
            ElementNode<OpeningProperty> openingPropertyElementNode = NodeFactory.createElementNode(o, OPENING, node);
            openingPropertyHandler.addChildsToNode(openingPropertyElementNode);
            node.addChild(openingPropertyElementNode);
        }

    }
}
