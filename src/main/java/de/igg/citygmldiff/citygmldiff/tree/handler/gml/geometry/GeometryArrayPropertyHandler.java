package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry;

import de.igg.citygmldiff.citygmldiff.tree.AbstrGeometryProcessor;
import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.gml.geometry.AbstractGeometry;
import org.citygml4j.model.gml.geometry.GeometryArrayProperty;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 10:30
 */
public class GeometryArrayPropertyHandler<T extends GeometryArrayProperty<? extends AbstractGeometry>> implements TreeNodeHandler<T> {

    private static AbstrGeometryProcessor<AbstractGeometry> abstrGeometryProcessor = new AbstrGeometryProcessor<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();
        List<? extends AbstractGeometry> geometries = element.getGeometry();
        if (geometries != null) {
            processGeometries(geometries, node);
        }
    }

    private void processGeometries(List<? extends AbstractGeometry> geometries, ElementNode<T> node) {
        for (AbstractGeometry geometry : geometries) {
            abstrGeometryProcessor.processAbstractGeoemtry(geometry, node);
        }
    }
}
