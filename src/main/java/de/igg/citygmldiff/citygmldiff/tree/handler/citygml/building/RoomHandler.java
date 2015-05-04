package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core.AbstractCityObjectHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.feature.BoundingShapeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates.MultiSurfacePropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.SolidPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.building.IntBuildingInstallationProperty;
import org.citygml4j.model.citygml.building.InteriorFurnitureProperty;
import org.citygml4j.model.citygml.building.Room;
import org.citygml4j.model.gml.basicTypes.Code;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurfaceProperty;
import org.citygml4j.model.gml.geometry.primitives.SolidProperty;

import java.util.List;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 17:05
 */
public class RoomHandler<T extends Room> extends AbstractCityObjectHandler<T> implements Constants {

    private static SolidPropertyHandler<SolidProperty> solidPropertyHandler = new SolidPropertyHandler<>();
    private static MultiSurfacePropertyHandler<MultiSurfaceProperty> multiSurfacePropertyHandler = new MultiSurfacePropertyHandler<>();
    private static BoundingShapeHandler<BoundingShape> boundingShapeHandler = new BoundingShapeHandler<>();
    private static IntBuildingInstallationPropertyHandler<IntBuildingInstallationProperty> intBuildingInstallationPropertyHandler = new IntBuildingInstallationPropertyHandler<>();
    private static InteriorFurniturePropertyHandler<InteriorFurnitureProperty> interiorFurniturePropertyHandler = new InteriorFurniturePropertyHandler<>();

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

        SolidProperty lod4Solid = element.getLod4Solid();
        if (lod4Solid != null) {
            ElementNode<SolidProperty> elementNode = NodeFactory.createElementNode(lod4Solid, LOD_4_SOLID, node);
            solidPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        MultiSurfaceProperty lod4MultiSurface = element.getLod4MultiSurface();
        if (lod4MultiSurface != null) {
            ElementNode<MultiSurfaceProperty> elementNode = NodeFactory.createElementNode(lod4MultiSurface, LOD_4_MULTI_SURFACE, node);
            multiSurfacePropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        BoundingShape boundedBy = element.getBoundedBy();
        if (boundedBy != null) {
            ElementNode<BoundingShape> elementNode = NodeFactory.createElementNode(boundedBy, BOUNDED_BY, node);
            boundingShapeHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        List<InteriorFurnitureProperty> interiorFurnitures = element.getInteriorFurniture();
        if (interiorFurnitures != null) {
            processInteriorFurnitures(interiorFurnitures, node);
        }

        List<IntBuildingInstallationProperty> roomInstallations = element.getRoomInstallation();
        if (roomInstallations != null) {
            processRoomInstallations(roomInstallations, node);
        }

        List<ADEComponent> genericApplicationPropertyOfRoom = element.getGenericApplicationPropertyOfRoom();
    }

    private void processRoomInstallations(List<IntBuildingInstallationProperty> roomInstallations, ElementNode<T> node) {
        for (IntBuildingInstallationProperty roomInst : roomInstallations) {
            ElementNode<IntBuildingInstallationProperty> elementNode = NodeFactory.createElementNode(roomInst, ROOM_INSTALLATION, node);
            intBuildingInstallationPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }
    }

    private void processInteriorFurnitures(List<InteriorFurnitureProperty> interiorFurnitures, ElementNode<T> node) {
        for (InteriorFurnitureProperty intFurniture : interiorFurnitures) {
            ElementNode<InteriorFurnitureProperty> elementNode = NodeFactory.createElementNode(intFurniture, INTERIOR_FURNITURE, node);
            interiorFurniturePropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }
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
