package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.primitives.Solid;
import org.citygml4j.model.gml.geometry.primitives.SurfaceProperty;

import java.util.List;

public class SolidHandler<T extends Solid> extends AbstractSolidHandler<T> {

    private static SurfacePropertyHandler<SurfaceProperty> surfacePropertyHandler = new SurfacePropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        SurfaceProperty exterior = element.getExterior();
        if (exterior != null) {
            String signature = Utils.buildSignature(node, exterior,
                    "exterior");
            ElementNode<SurfaceProperty> surfacePropertyNode = new ElementNode<>(
                    exterior, "exterior", signature, node);
            surfacePropertyHandler.addChildsToNode(surfacePropertyNode);
            node.addChild(surfacePropertyNode);
        }

        List<SurfaceProperty> interiors = element.getInterior();
        for (SurfaceProperty surfProp : interiors) {
            String signature = Utils.buildSignature(node, surfProp,
                    "interior");
            ElementNode<SurfaceProperty> surfacePropertyNode = new ElementNode<>(
                    surfProp, "interior", signature, node);
            surfacePropertyHandler.addChildsToNode(surfacePropertyNode);
            node.addChild(surfacePropertyNode);
        }

    }

}
