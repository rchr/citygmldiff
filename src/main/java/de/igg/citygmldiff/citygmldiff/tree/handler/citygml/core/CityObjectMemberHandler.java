package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building.BuildingHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.building.AbstractBuilding;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.citygml.core.AbstractCityObject;
import org.citygml4j.model.citygml.core.AbstractSite;
import org.citygml4j.model.citygml.core.CityObjectMember;

/**
 * User: richard
 * Date: 16.09.13
 * Time: 19:27
 */
public class CityObjectMemberHandler<T extends CityObjectMember> implements TreeNodeHandler<T>, Constants {

    private static BuildingHandler<Building> buildingHandler = new BuildingHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();
        AbstractCityObject abstractCityObject = element.getCityObject();
        if (abstractCityObject == null) {
            Utils.addXlinkFeature(node);
        } else if (abstractCityObject instanceof AbstractSite) {
            AbstractSite site = (AbstractSite) abstractCityObject;
            addAbstractSiteToHead(site, node);
        }
    }

    /**
     * Adds an {@link AbstractSite} to the head.
     *
     * @param site
     * @param head
     */
    private void addAbstractSiteToHead(AbstractSite site, ElementNode<?> head) {
        if (site instanceof AbstractBuilding) {
            AbstractBuilding b = (AbstractBuilding) site;
            addAbstractBuildingToHead(b, head);
        }
    }

    /**
     * Adds an {@link AbstractBuilding} to the head.
     *
     * @param building
     * @param head
     */
    private void addAbstractBuildingToHead(AbstractBuilding building, ElementNode<?> head) {
        if (building instanceof Building) {
            Building b = (Building) building;
            addBuildingToHead(b, head);
        }
    }

    private void addBuildingToHead(Building building, ElementNode<?> head) {
        ElementNode<Building> buildingNode = new ElementNode<>(
                building, BUILDING, Utils.buildSignature(head, building, BUILDING), head);
        buildingHandler.addChildsToNode(buildingNode);
        head.addChild(buildingNode);
    }
}
