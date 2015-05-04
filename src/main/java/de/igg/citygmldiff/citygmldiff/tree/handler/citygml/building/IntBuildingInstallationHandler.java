package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core.AbstractCityObjectHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core.ImplicitRepresentationPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.feature.BoundingShapeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.GeometryPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.building.IntBuildingInstallation;
import org.citygml4j.model.citygml.core.ImplicitRepresentationProperty;
import org.citygml4j.model.gml.basicTypes.Code;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.GeometryProperty;

import java.util.List;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 15:15
 */
public class IntBuildingInstallationHandler<T extends IntBuildingInstallation> extends AbstractCityObjectHandler<T> implements Constants {

    private static GeometryPropertyHandler<GeometryProperty> geometryPropertyHandler = new GeometryPropertyHandler<>();
    private static ImplicitRepresentationPropertyHandler<ImplicitRepresentationProperty> implicitRepresentationPropertyHandler = new ImplicitRepresentationPropertyHandler<>();
    private static BoundingShapeHandler<BoundingShape> boundingShapeHandler = new BoundingShapeHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        Code clazz = element.getClazz();
        if (clazz != null) {
            processCode(clazz, node, CLAZZ);
        }

        List<Code> functions = element.getFunction();
        if (functions != null) {
            processCodeList(functions, node, FUNCTION);
        }

        List<Code> usages = element.getUsage();
        if (usages != null) {
            processCodeList(usages, node, USAGE);
        }

        GeometryProperty lod4Geometry = element.getLod4Geometry();
        if (lod4Geometry != null) {
            ElementNode<GeometryProperty> geometryPropertyElementNode = NodeFactory.createElementNode(lod4Geometry, LOD_4_GEOMETRY, node);
            geometryPropertyHandler.addChildsToNode(geometryPropertyElementNode);
            node.addChild(geometryPropertyElementNode);
        }

        ImplicitRepresentationProperty lod4ImplicitRepresentation = element.getLod4ImplicitRepresentation();
        if (lod4ImplicitRepresentation != null) {
            ElementNode<ImplicitRepresentationProperty> elementNode = NodeFactory.createElementNode(lod4ImplicitRepresentation, LOD_4_IMPLICIT_REPRESENTATION, node);
            implicitRepresentationPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        BoundingShape boundedBy = element.getBoundedBy();
        if (boundedBy != null) {
            ElementNode<BoundingShape> elementNode = NodeFactory.createElementNode(boundedBy, BOUNDED_BY, node);
            boundingShapeHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        List<ADEComponent> genericApplicationPropertyOfIntBuildingInstallation = element.getGenericApplicationPropertyOfIntBuildingInstallation();
    }

    /**
     * Processes a list of codes as is processCode().
     *
     * @param codeList
     * @param node
     * @param name
     */
    private void processCodeList(List<Code> codeList, ElementNode<T> node, String name) {
        for (Code code : codeList) {
            processCode(code, node, name);
        }
    }

    /**
     * Processes and adds a {@link Code} to the {@link ElementNode} with the given name.
     *
     * @param code The code to process.
     * @param node The parent node.
     * @param name The name of the created {@link ElementNode}.
     */
    private void processCode(Code code, ElementNode<T> node, String name) {
        ElementNode<Code> codeElementNode = NodeFactory.createElementNode(code, name, node);
        TextNode textNode = new TextNode(code.getValue(), codeElementNode);
        codeElementNode.addChild(textNode);
        node.addChild(codeElementNode);
    }
}
