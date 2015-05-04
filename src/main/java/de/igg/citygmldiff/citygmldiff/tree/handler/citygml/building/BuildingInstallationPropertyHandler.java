package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.building.BuildingInstallation;
import org.citygml4j.model.citygml.building.BuildingInstallationProperty;

/**
 * User: richard
 * Date: 15.09.13
 * Time: 15:59
 */
public class BuildingInstallationPropertyHandler<T extends BuildingInstallationProperty> implements TreeNodeHandler<T>, Constants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        BuildingInstallation buildingInstallation = element.getBuildingInstallation();
        if (buildingInstallation == null) {
            Utils.addXlinkFeature(node);
        } else {
            ElementNode<BuildingInstallation> buildingInstallationNode = NodeFactory.createElementNode(buildingInstallation, BUILDING_INSTALLATION, node);
            node.addChild(buildingInstallationNode);
        }
    }
}
