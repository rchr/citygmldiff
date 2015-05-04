package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.AbstractGeometryHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.GeometricPrimitivePropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.complexes.GeometricComplex;
import org.citygml4j.model.gml.geometry.primitives.GeometricPrimitiveProperty;

import java.util.List;

/**
 * User: richard
 * Date: 20.09.13
 * Time: 11:28
 */
public class GeometricComplexHandler<T extends GeometricComplex> extends AbstractGeometryHandler<T> implements Constants {

    private static GeometricPrimitivePropertyHandler<GeometricPrimitiveProperty> geometricPrimitivePropertyHandler = new GeometricPrimitivePropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);
        T element = node.getValue();
        List<GeometricPrimitiveProperty> elements = element.getElement();
        if (elements != null) {
            processGeometricPrimitiveProperties(elements, node);
        }
    }

    private void processGeometricPrimitiveProperties(List<GeometricPrimitiveProperty> elements, ElementNode<T> node) {
        for (GeometricPrimitiveProperty geomPrimitiveProperty : elements) {
            ElementNode<GeometricPrimitiveProperty> geomPrimPropNode = NodeFactory.createElementNode(geomPrimitiveProperty, ELEMENT, node);
            geometricPrimitivePropertyHandler.addChildsToNode(geomPrimPropNode);
            node.addChild(geomPrimPropNode);
        }
    }
}
