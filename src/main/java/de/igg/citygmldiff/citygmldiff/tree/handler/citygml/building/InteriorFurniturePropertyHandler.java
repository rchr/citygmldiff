package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.building.BuildingFurniture;
import org.citygml4j.model.citygml.building.InteriorFurnitureProperty;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 17:25
 */
public class InteriorFurniturePropertyHandler<T extends InteriorFurnitureProperty> implements TreeNodeHandler<T>, Constants {

    private static BuildingFurnitureHandler<BuildingFurniture> buildingFurnitureHandler = new BuildingFurnitureHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        BuildingFurniture buildingFurniture = element.getBuildingFurniture();
        if (buildingFurniture == null) {
            Utils.addXlinkFeature(node);
        } else if (buildingFurniture != null) {
            ElementNode<BuildingFurniture> elementNode = NodeFactory.createElementNode(buildingFurniture, BUILDING_FURNITURE, node);
            buildingFurnitureHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }
    }
}
