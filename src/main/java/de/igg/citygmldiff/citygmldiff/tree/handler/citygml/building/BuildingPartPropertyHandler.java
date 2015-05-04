package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.building.BuildingPart;
import org.citygml4j.model.citygml.building.BuildingPartProperty;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 15:46
 */
public class BuildingPartPropertyHandler<T extends BuildingPartProperty> implements TreeNodeHandler<T>, Constants {

    private static BuildingPartHandler<BuildingPart> buildingPartHandler = new BuildingPartHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        BuildingPart buildingPart = element.getBuildingPart();
        if (buildingPart == null) {
            Utils.addXlinkFeature(node);
        } else if (buildingPart != null) {
            ElementNode<BuildingPart> elementNode = NodeFactory.createElementNode(buildingPart, BUILDING_PART, node);
            buildingPartHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }
    }
}
