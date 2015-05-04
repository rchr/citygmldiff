package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core.AddressPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core.SiteHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.basicTypes.MeasureOrNullListHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates.MultiCurvePropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates.MultiSurfacePropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.SolidPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.measures.LengthHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.other.GregorianCalendarHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.other.IntegerHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.building.*;
import org.citygml4j.model.citygml.core.AddressProperty;
import org.citygml4j.model.gml.basicTypes.Code;
import org.citygml4j.model.gml.basicTypes.MeasureOrNullList;
import org.citygml4j.model.gml.geometry.aggregates.MultiCurveProperty;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurfaceProperty;
import org.citygml4j.model.gml.geometry.primitives.SolidProperty;
import org.citygml4j.model.gml.measures.Length;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Abstract class that handles {@link AbstractBuilding}s.
 *
 * @param <T>
 */
public abstract class AbstractBuildingHandler<T extends AbstractBuilding>
        extends SiteHandler<T> implements Constants {

    private static BoundarySurfacePropertyHandler<BoundarySurfaceProperty> boundarySurfacePropertyHandler = new BoundarySurfacePropertyHandler<>();
    private static LengthHandler<Length> lengthHandler = new LengthHandler<>();
    private static GregorianCalendarHandler<GregorianCalendar> gregorianCalendarHandler = new GregorianCalendarHandler<>();
    private static IntegerHandler integerHandler = new IntegerHandler();
    private static MeasureOrNullListHandler<MeasureOrNullList> measureOrNullListHandler = new MeasureOrNullListHandler<>();
    private static SolidPropertyHandler<SolidProperty> solidPropertyHandler = new SolidPropertyHandler<>();
    private static AddressPropertyHandler<AddressProperty> addressPropertyHandler = new AddressPropertyHandler<>();
    private static MultiCurvePropertyHandler<MultiCurveProperty> multiCurvePropertyHandler = new MultiCurvePropertyHandler<>();
    private static MultiSurfacePropertyHandler<MultiSurfaceProperty> multiSurfacePropertyHandler = new MultiSurfacePropertyHandler<>();
    private static BuildingInstallationPropertyHandler<BuildingInstallationProperty> buildingInstallationPropertyHandler = new BuildingInstallationPropertyHandler<>();
    private static IntBuildingInstallationPropertyHandler<IntBuildingInstallationProperty> intBuildingInstallationPropertyHandler = new IntBuildingInstallationPropertyHandler<>();
    private static BuildingPartPropertyHandler<BuildingPartProperty> buildingPartPropertyHandler = new BuildingPartPropertyHandler<>();
    private static InteriorRoomPropertyHandler<InteriorRoomProperty> interiorRoomPropertyHandler = new InteriorRoomPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        super.addChildsToNode(node);
        T element = node.getValue();

        Code clazz = element.getClazz();
        if (clazz != null) {
            processCodeList(Arrays.asList(clazz), CLAZZ, node);
        }

        List<Code> functions = element.getFunction();
        processCodeList(functions, FUNCTION, node);

        List<Code> usages = element.getUsage();
        processCodeList(usages, USAGE, node);

        GregorianCalendar yearOfConstruction = element.getYearOfConstruction();
        if (yearOfConstruction != null) {
            String signature = Utils.buildSignature(node, yearOfConstruction, YEAR_OF_CONSTRUCTION);
            ElementNode<GregorianCalendar> yearOfConstructionNode = new ElementNode<>(
                    yearOfConstruction, YEAR_OF_CONSTRUCTION, signature, node);
            gregorianCalendarHandler.addChildsToNode(yearOfConstructionNode);
            node.addChild(yearOfConstructionNode);
        }

        GregorianCalendar yearOfDemolition = element.getYearOfDemolition();
        if (yearOfDemolition != null) {
            String signature = Utils.buildSignature(node, yearOfDemolition, YEAR_OF_DEMOLITION);
            ElementNode<GregorianCalendar> yearDemolotionNode = new ElementNode<>(
                    yearOfConstruction, YEAR_OF_DEMOLITION, signature, node);
            gregorianCalendarHandler.addChildsToNode(yearDemolotionNode);
            node.addChild(yearDemolotionNode);
        }

        Code roofType = element.getRoofType();
        if (roofType != null) {
            processCodeList(Arrays.asList(roofType), ROOF_TYPE, node);
        }

        Length measuredHeight = element.getMeasuredHeight();
        if (measuredHeight != null) {
            String signature = Utils.buildSignature(node, measuredHeight, MEASURED_HEIGHT);
            ElementNode<Length> lengthNode = new ElementNode<>(
                    measuredHeight, MEASURED_HEIGHT, signature, node);
            lengthHandler.addChildsToNode(lengthNode);
            node.addChild(lengthNode);
        }

        Integer storeysAboveGround = element.getStoreysAboveGround();
        if (storeysAboveGround != null) {
            String signature = Utils.buildSignature(node, storeysAboveGround, STOREYS_ABOVE_GROUND);
            ElementNode<Integer> storeysAboveGroundNode = new ElementNode<>(
                    storeysAboveGround, STOREYS_ABOVE_GROUND, signature, node);
            integerHandler.addChildsToNode(storeysAboveGroundNode);
            node.addChild(storeysAboveGroundNode);
        }

        Integer storeysBelowGround = element.getStoreysBelowGround();
        if (storeysBelowGround != null) {
            String signature = Utils.buildSignature(node, storeysBelowGround, STOREYS_BELOW_GROUND);
            ElementNode<Integer> storeysBelowGroundNode = new ElementNode<>(
                    storeysBelowGround, STOREYS_BELOW_GROUND, signature, node);
            integerHandler.addChildsToNode(storeysBelowGroundNode);
            node.addChild(storeysBelowGroundNode);
        }

        MeasureOrNullList storeyHeightsAboveGround = element
                .getStoreyHeightsAboveGround();
        if (storeyHeightsAboveGround != null) {
            String signature = Utils.buildSignature(node, storeyHeightsAboveGround,
                    STOREY_HEIGHTS_ABOVE_GROUND);
            ElementNode<MeasureOrNullList> storeyHeightsAboveNode = new ElementNode<>(
                    storeyHeightsAboveGround, STOREY_HEIGHTS_ABOVE_GROUND,
                    signature, node);
            measureOrNullListHandler.addChildsToNode(storeyHeightsAboveNode);
            node.addChild(storeyHeightsAboveNode);
        }

        MeasureOrNullList storeyHeightsBelowGround = element
                .getStoreyHeightsBelowGround();
        if (storeyHeightsBelowGround != null) {
            String signature = Utils.buildSignature(node, storeyHeightsBelowGround,
                    STOREY_HEIGHTS_BELOW_GROUND);
            ElementNode<MeasureOrNullList> storeyHeightsBelowNode = new ElementNode<>(
                    storeyHeightsBelowGround, STOREY_HEIGHTS_BELOW_GROUND,
                    signature, node);
            measureOrNullListHandler.addChildsToNode(storeyHeightsBelowNode);
            node.addChild(storeyHeightsBelowNode);
        }

        SolidProperty lod1Solid = element.getLod1Solid();
        if (lod1Solid != null) {
            String signature = Utils.buildSignature(node, lod1Solid, LOD_1_SOLID);
            ElementNode<SolidProperty> solidNode = new ElementNode<>(
                    lod1Solid, LOD_1_SOLID, signature, node);
            solidPropertyHandler.addChildsToNode(solidNode);
            node.addChild(solidNode);
        }

        SolidProperty lod2Solid = element.getLod2Solid();
        if (lod2Solid != null) {
            String signature = Utils.buildSignature(node, lod2Solid, LOD_2_SOLID);
            ElementNode<SolidProperty> solidNode = new ElementNode<>(
                    lod2Solid, LOD_2_SOLID, signature, node);
            solidPropertyHandler.addChildsToNode(solidNode);
            node.addChild(solidNode);
        }

        SolidProperty lod3Solid = element.getLod3Solid();
        if (lod3Solid != null) {
            String signature = Utils.buildSignature(node, lod3Solid, LOD_3_SOLID);
            ElementNode<SolidProperty> solidNode = new ElementNode<>(
                    lod3Solid, LOD_3_SOLID, signature, node);
            solidPropertyHandler.addChildsToNode(solidNode);
            node.addChild(solidNode);
        }

        SolidProperty lod4Solid = element.getLod4Solid();
        if (lod4Solid != null) {
            String signature = Utils.buildSignature(node, lod4Solid, LOD_4_SOLID);
            ElementNode<SolidProperty> solidNode = new ElementNode<>(
                    lod4Solid, LOD_4_SOLID, signature, node);
            solidPropertyHandler.addChildsToNode(solidNode);
            node.addChild(solidNode);
        }

        MultiCurveProperty lod1TerrainIntersection = element
                .getLod1TerrainIntersection();
        if (lod1TerrainIntersection != null) {
            ElementNode<MultiCurveProperty> lod1TerrainNode = NodeFactory
                    .createElementNode(lod1TerrainIntersection,
                            LOD_1_TERRAIN_INTERSECTION, node);
            multiCurvePropertyHandler.addChildsToNode(lod1TerrainNode);
            node.addChild(lod1TerrainNode);
        }

        MultiCurveProperty lod2TerrainIntersection = element
                .getLod2TerrainIntersection();
        if (lod2TerrainIntersection != null) {
            ElementNode<MultiCurveProperty> lod2TerrainNode = NodeFactory.createElementNode(lod2TerrainIntersection, LOD_2_TERRAIN_INTERSECTION, node);
            multiCurvePropertyHandler.addChildsToNode(lod2TerrainNode);
            node.addChild(lod2TerrainNode);
        }

        MultiCurveProperty lod3TerrainIntersection = element
                .getLod3TerrainIntersection();
        if (lod3TerrainIntersection != null) {
            ElementNode<MultiCurveProperty> lod3TerrainNode = NodeFactory.createElementNode(lod3TerrainIntersection, LOD_3_TERRAIN_INTERSECTION, node);
            multiCurvePropertyHandler.addChildsToNode(lod3TerrainNode);
            node.addChild(lod3TerrainNode);
        }

        MultiCurveProperty lod4TerrainIntersection = element
                .getLod4TerrainIntersection();
        if (lod4TerrainIntersection != null) {
            ElementNode<MultiCurveProperty> lod4TerrainNode = NodeFactory.createElementNode(lod4TerrainIntersection, LOD_4_TERRAIN_INTERSECTION, node);
            multiCurvePropertyHandler.addChildsToNode(lod4TerrainNode);
            node.addChild(lod4TerrainNode);
        }

        MultiCurveProperty lod2MultiCurve = element.getLod2MultiCurve();
        if (lod2MultiCurve != null) {
            ElementNode<MultiCurveProperty> lod2MultiCurveNode = NodeFactory.createElementNode(lod2MultiCurve, LOD_2_MULTI_CURVE, node);
            multiCurvePropertyHandler.addChildsToNode(lod2MultiCurveNode);
            node.addChild(lod2MultiCurveNode);
        }

        MultiCurveProperty lod3MultiCurve = element.getLod3MultiCurve();
        if (lod3MultiCurve != null) {
            ElementNode<MultiCurveProperty> lod3MultiCurveNode = NodeFactory.createElementNode(lod3MultiCurve, LOD_3_MULTI_CURVE, node);
            multiCurvePropertyHandler.addChildsToNode(lod3MultiCurveNode);
            node.addChild(lod3MultiCurveNode);
        }

        MultiCurveProperty lod4MultiCurve = element.getLod4MultiCurve();
        if (lod4MultiCurve != null) {
            ElementNode<MultiCurveProperty> lod4MultiCurveNode = NodeFactory.createElementNode(lod4MultiCurve, LOD_4_MULTI_CURVE, node);
            multiCurvePropertyHandler.addChildsToNode(lod4MultiCurveNode);
            node.addChild(lod4MultiCurveNode);
        }

        MultiSurfaceProperty lod0FootPrint = element.getLod0FootPrint();
        if (lod0FootPrint != null) {
            ElementNode<MultiSurfaceProperty> lod0FootPrintNode = NodeFactory.createElementNode(lod0FootPrint, LOD_0_FOOT_PRINT, node);
            multiSurfacePropertyHandler.addChildsToNode(lod0FootPrintNode);
            node.addChild(lod0FootPrintNode);
        }

        MultiSurfaceProperty lod0RoofEdge = element.getLod0RoofEdge();
        if (lod0RoofEdge != null) {
            ElementNode<MultiSurfaceProperty> lod0RoofEdgeNode = NodeFactory.createElementNode(lod0RoofEdge, LOD_0_ROOF_EDGE, node);
            multiSurfacePropertyHandler.addChildsToNode(lod0RoofEdgeNode);
            node.addChild(lod0RoofEdgeNode);
        }

        MultiSurfaceProperty lod1MultiSurface = element.getLod1MultiSurface();
        if (lod1MultiSurface != null) {
            ElementNode<MultiSurfaceProperty> lod1MultiSurfNode = NodeFactory.createElementNode(lod1MultiSurface, LOD_1_MULTI_SURFACE, node);
            multiSurfacePropertyHandler.addChildsToNode(lod1MultiSurfNode);
            node.addChild(lod1MultiSurfNode);
        }

        MultiSurfaceProperty lod2MultiSurface = element.getLod2MultiSurface();
        if (lod2MultiSurface != null) {
            ElementNode<MultiSurfaceProperty> lod2MultiSurfNode = NodeFactory.createElementNode(lod2MultiSurface, LOD_2_MULTI_SURFACE, node);
            multiSurfacePropertyHandler.addChildsToNode(lod2MultiSurfNode);
            node.addChild(lod2MultiSurfNode);
        }
        MultiSurfaceProperty lod3MultiSurface = element.getLod3MultiSurface();
        if (lod3MultiSurface != null) {
            ElementNode<MultiSurfaceProperty> lod3MultiSurfNode = NodeFactory.createElementNode(lod3MultiSurface, LOD_3_MULTI_SURFACE, node);
            multiSurfacePropertyHandler.addChildsToNode(lod3MultiSurfNode);
            node.addChild(lod3MultiSurfNode);
        }

        MultiSurfaceProperty lod4MultiSurface = element.getLod4MultiSurface();
        if (lod4MultiSurface != null) {
            ElementNode<MultiSurfaceProperty> lod4MultiSurfNode = NodeFactory.createElementNode(lod4MultiSurface, LOD_4_MULTI_SURFACE, node);
            multiSurfacePropertyHandler.addChildsToNode(lod4MultiSurfNode);
            node.addChild(lod4MultiSurfNode);
        }

        List<BuildingInstallationProperty> outerBuildingInstallation = element
                .getOuterBuildingInstallation();
        if (outerBuildingInstallation != null) {
            processOuterBuildingInstallation(outerBuildingInstallation, node);
        }

        List<IntBuildingInstallationProperty> interiorBuildingInstallation = element
                .getInteriorBuildingInstallation();
        if (interiorBuildingInstallation != null) {
            processInteriorBuildingInstallation(interiorBuildingInstallation, node);
        }

        List<BoundarySurfaceProperty> boundedBySurface = element
                .getBoundedBySurface();
        if (boundedBySurface != null && boundedBySurface.size() > 0) {
            processBoundarySurfaceProperties(boundedBySurface, node);
        }

        List<BuildingPartProperty> buildingParts = element
                .getConsistsOfBuildingPart();
        if (buildingParts != null) {
            processBuildingParts(buildingParts, node);
        }

        List<InteriorRoomProperty> interiorRooms = element.getInteriorRoom();
        if (interiorRooms != null) {
            processInteriorRooms(interiorRooms, node);
        }

        List<AddressProperty> address = element.getAddress();
        processAddressPropertyList(address, ADDRESS, node);

        List<ADEComponent> ade = element
                .getGenericApplicationPropertyOfAbstractBuilding();
    }

    private void processInteriorRooms(List<InteriorRoomProperty> interiorRooms, ElementNode<T> node) {
        for (InteriorRoomProperty intRoom : interiorRooms) {
            ElementNode<InteriorRoomProperty> elementNode = NodeFactory.createElementNode(intRoom, INTERIOR_ROOM, node);
            interiorRoomPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }
    }

    private void processBuildingParts(List<BuildingPartProperty> buildingParts, ElementNode<T> node) {
        for (BuildingPartProperty part : buildingParts) {
            ElementNode<BuildingPartProperty> elementNode = NodeFactory.createElementNode(part, CONSISTS_OF_BUILDING_PART, node);
            buildingPartPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }
    }

    private void processInteriorBuildingInstallation(List<IntBuildingInstallationProperty> interiorBuildingInstallation, ElementNode<T> node) {
        for (IntBuildingInstallationProperty intBuildingInstallationProp : interiorBuildingInstallation) {
            ElementNode<IntBuildingInstallationProperty> elementNode = NodeFactory.createElementNode(intBuildingInstallationProp, INTERIOR_BUILDING_INSTALLATION, node);
            intBuildingInstallationPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }
    }

    private void processOuterBuildingInstallation(List<BuildingInstallationProperty> outerBuildingInstallation, ElementNode<T> node) {
        for (BuildingInstallationProperty buildingInstallationProperty : outerBuildingInstallation) {
            ElementNode<BuildingInstallationProperty> elementNode = NodeFactory.createElementNode(buildingInstallationProperty, OUTER_BUILDING_INSTALLATION, node);
            buildingInstallationPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }
    }

    private void processCodeList(List<Code> codeList, String name,
                                 ElementNode<T> node) {
        if (codeList != null) {
            for (Code code : codeList) {
                String signature = Utils.buildSignature(node, code, name);
                ElementNode<Code> codeTreeNode = new ElementNode<>(code,
                        name, signature, node);
                String value = code.getValue();
                TextNode textNode = new TextNode(value, codeTreeNode);
                codeTreeNode.addChild(textNode);
                node.addChild(codeTreeNode);
            }
        }
    }

    private void processAddressPropertyList(
            List<AddressProperty> addressProperties, String name,
            ElementNode<T> node) {
        if (addressProperties != null) {
            for (AddressProperty addressProperty : addressProperties) {
                String signature = Utils.buildSignature(node, addressProperty, name);
                ElementNode<AddressProperty> addressPropertyNode = new ElementNode<>(
                        addressProperty, name, signature, node);
                addressPropertyHandler.addChildsToNode(addressPropertyNode);
                node.addChild(addressPropertyNode);
            }

        }
    }

    /**
     * Processes a list of {@link BoundarySurfaceProperty} and adds entries as
     * children to the {@link ElementNode}.
     *
     * @param boundedBySurface
     * @param node
     */
    private void processBoundarySurfaceProperties(
            List<BoundarySurfaceProperty> boundedBySurface, ElementNode<T> node) {
        for (BoundarySurfaceProperty boundarySurfaceProp : boundedBySurface) {
            String name = BOUNDED_BY;
            String signature = Utils.buildSignature(node, boundarySurfaceProp, name);
            ElementNode<BoundarySurfaceProperty> boundarySurfacePropertyNode = new ElementNode<>(
                    boundarySurfaceProp, name, signature, node);
            boundarySurfacePropertyHandler
                    .addChildsToNode(boundarySurfacePropertyNode);
            node.addChild(boundarySurfacePropertyNode);
        }
    }

}
