package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.AbstrGeometryProcessor;
import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.primitives.AbstractGeometricPrimitive;
import org.citygml4j.model.gml.geometry.primitives.GeometricPrimitiveProperty;

/**
 * User: richard
 * Date: 20.09.13
 * Time: 11:34
 */
public class GeometricPrimitivePropertyHandler<T extends GeometricPrimitiveProperty> implements TreeNodeHandler<T> {

    private static AbstrGeometryProcessor<AbstractGeometricPrimitive> abstrGeometryProcessor = new AbstrGeometryProcessor<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();
        AbstractGeometricPrimitive geometricPrimitive = element.getGeometricPrimitive();
        if (geometricPrimitive == null) {
            Utils.addXlinkFeature(node);
        } else {
            abstrGeometryProcessor.processAbstractGeoemtry(geometricPrimitive, node);
        }
    }
}
