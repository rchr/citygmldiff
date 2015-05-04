package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.GeometryArrayPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.GeometryPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.AbstractGeometry;
import org.citygml4j.model.gml.geometry.GeometryArrayProperty;
import org.citygml4j.model.gml.geometry.GeometryProperty;
import org.citygml4j.model.gml.geometry.aggregates.MultiGeometry;

import java.util.List;

/**
 * User: richard
 * Date: 20.09.13
 * Time: 18:16
 */
public class MultiGeometryHandler<T extends MultiGeometry> extends AbstractGeometricAggregateHandler<T> implements Constants {

    private static GeometryPropertyHandler<GeometryProperty> geometryPropertyHandler = new GeometryPropertyHandler<>();
    private static GeometryArrayPropertyHandler<GeometryArrayProperty<? extends AbstractGeometry>> geometryArrayPropertyHandler = new GeometryArrayPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<GeometryProperty<? extends AbstractGeometry>> geometryMember = element.getGeometryMember();
        if (geometryMember != null) {
            processGeometryMember(geometryMember, node);
        }

        GeometryArrayProperty<? extends AbstractGeometry> geometryMembers = element.getGeometryMembers();
        if (geometryMembers != null) {

            String signature = Utils.buildSignature(node, geometryMembers, GEOMETRY_MEMBERS);
            ElementNode<GeometryArrayProperty<? extends AbstractGeometry>> geometryArrayPropertyElementNode = new ElementNode<GeometryArrayProperty<? extends AbstractGeometry>>(geometryMembers, "geometryMembers", signature, node);
            geometryArrayPropertyHandler.addChildsToNode(geometryArrayPropertyElementNode);
            node.addChild(geometryArrayPropertyElementNode);
        }

    }

    private void processGeometryMember(List<GeometryProperty<? extends AbstractGeometry>> geometryMember, ElementNode<T> node) {
        for (GeometryProperty geomProp : geometryMember) {
            ElementNode<GeometryProperty> geometryPropertyElementNode = NodeFactory.createElementNode(geomProp, GEOMETRY_MEMBER, node);
            geometryPropertyHandler.addChildsToNode(geometryPropertyElementNode);
            node.addChild(geometryPropertyElementNode);
        }
    }
}
