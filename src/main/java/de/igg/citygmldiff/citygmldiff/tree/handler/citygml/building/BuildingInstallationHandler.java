package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core.AbstractCityObjectHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core.ImplicitRepresentationPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.feature.BoundingShapeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.GeometryPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.building.BuildingInstallation;
import org.citygml4j.model.citygml.core.ImplicitRepresentationProperty;
import org.citygml4j.model.gml.basicTypes.Code;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.GeometryProperty;

import java.util.List;

/**
 * User: richard
 * Date: 15.09.13
 * Time: 18:16
 */
public class BuildingInstallationHandler<T extends BuildingInstallation> extends AbstractCityObjectHandler<T> implements Constants {

    private static BoundingShapeHandler<BoundingShape> boundingShapeHandler = new BoundingShapeHandler<>();
    private static GeometryPropertyHandler geometryPropertyHandler = new GeometryPropertyHandler();
    private static ImplicitRepresentationPropertyHandler<ImplicitRepresentationProperty> implicitRepresentationPropertyHandler = new ImplicitRepresentationPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        Code clazz = element.getClazz();
        if (clazz != null) {
            ElementNode<Code> codeNode = NodeFactory.createElementNode(clazz, CLAZZ, node);
            String value = clazz.getValue();
            TextNode textNode = new TextNode(value, codeNode);
            codeNode.addChild(textNode);
            node.addChild(codeNode);
        }

        List<Code> function = element.getFunction();
        processCodeList(function, FUNCTION, node);

        List<Code> usage = element.getUsage();
        processCodeList(usage, USAGE, node);

        GeometryProperty lod2Geometry = element.getLod2Geometry();
        if (lod2Geometry != null) {
            ElementNode<GeometryProperty> elementNode = NodeFactory.createElementNode(lod2Geometry, LOD_2_GEOMETRY, node);
            geometryPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        GeometryProperty lod3Geometry = element.getLod3Geometry();
        if (lod3Geometry != null) {
            ElementNode<GeometryProperty> elementNode = NodeFactory.createElementNode(lod3Geometry, LOD_3_GEOMETRY, node);
            geometryPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        GeometryProperty lod4Geometry = element.getLod4Geometry();
        if (lod4Geometry != null) {
            ElementNode<GeometryProperty> elementNode = NodeFactory.createElementNode(lod4Geometry, LOD_4_GEOMETRY, node);
            geometryPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        ImplicitRepresentationProperty lod2ImplicitRepresentation = element.getLod2ImplicitRepresentation();
        if (lod2ImplicitRepresentation != null) {
            ElementNode<ImplicitRepresentationProperty> elementNode = NodeFactory.createElementNode(lod2ImplicitRepresentation, LOD_2_IMPLICIT_REPRESENTATION, node);
            implicitRepresentationPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        ImplicitRepresentationProperty lod3ImplicitRepresentation = element.getLod3ImplicitRepresentation();
        if (lod3ImplicitRepresentation != null) {
            ElementNode<ImplicitRepresentationProperty> elementNode = NodeFactory.createElementNode(lod3ImplicitRepresentation, LOD_3_IMPLICIT_REPRESENTATION, node);
            implicitRepresentationPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        ImplicitRepresentationProperty lod4ImplicitRepresentation = element.getLod4ImplicitRepresentation();
        if (lod4ImplicitRepresentation != null) {
            ElementNode<ImplicitRepresentationProperty> elementNode = NodeFactory.createElementNode(lod4ImplicitRepresentation, LOD_4_IMPLICIT_REPRESENTATION, node);
            implicitRepresentationPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        BoundingShape boundedBy = element.getBoundedBy();
        if (boundedBy != null) {
            ElementNode<BoundingShape> boundedByNode = NodeFactory.createElementNode(boundedBy, BOUNDED_BY, node);
            boundingShapeHandler.addChildsToNode(boundedByNode);
            node.addChild(boundedByNode);
        }

        List<ADEComponent> genericApplicationPropertyOfBuildingInstallation = element.getGenericApplicationPropertyOfBuildingInstallation();

    }

    private void processCodeList(List<Code> codeList, String name,
                                 ElementNode<T> node) {
        if (codeList != null) {
            for (Code code : codeList) {
                String signature = Utils.buildSignature(node, code, name);
                ElementNode<Code> codeTreeNode = new ElementNode<>(code,
                        name, signature, node);
                String value = code.getValue();
                TextNode textNode = new TextNode(value, node);
                codeTreeNode.addChild(textNode);
                node.addChild(codeTreeNode);
            }
        }
    }
}
