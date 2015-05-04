package de.igg.citygmldiff.citygmldiff.tree.handler.xal.util;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.BuildingNameHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.BuildingName;

import java.util.List;

public abstract class BuildingNameAndAddressLineProcessor<T>
        extends AddressLinesProcessor<T> {

    private static BuildingNameHandler<BuildingName> buildingNameHandler = new BuildingNameHandler<>();

    public void processBuildingNames(List<BuildingName> buildingNames,
                                     ElementNode<T> node) {
        if (buildingNames != null) {
            for (BuildingName buildingName : buildingNames) {
                ElementNode<BuildingName> nameNode = NodeFactory.createElementNode(
                        buildingName, BUILDING_NAME, node);
                buildingNameHandler.addChildsToNode(nameNode);
                node.addChild(nameNode);
            }
        }
    }

}
