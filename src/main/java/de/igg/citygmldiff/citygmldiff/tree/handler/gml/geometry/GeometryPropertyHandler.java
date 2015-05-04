package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry;

import de.igg.citygmldiff.citygmldiff.tree.AbstrGeometryProcessor;
import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.AbstractGeometry;
import org.citygml4j.model.gml.geometry.GeometryProperty;

/**
 * User: richard
 * Date: 19.09.13
 * Time: 15:53
 */
public class GeometryPropertyHandler<T extends GeometryProperty> implements TreeNodeHandler<T> {

    private static AbstrGeometryProcessor<AbstractGeometry> abstrGeometryProcessor = new AbstrGeometryProcessor<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();
        AbstractGeometry geometry = element.getGeometry();
        if (geometry == null) {
//			geometry = Utils.resolveXlinkGeometry(element);
            Utils.addXlinkFeature(node);
        } else {
            abstrGeometryProcessor.processAbstractGeoemtry(geometry, node);
        }
    }
}
