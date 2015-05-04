package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.building.IntBuildingInstallation;
import org.citygml4j.model.citygml.building.IntBuildingInstallationProperty;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 15:34
 */
public class IntBuildingInstallationPropertyHandler<T extends IntBuildingInstallationProperty> implements TreeNodeHandler<T>, Constants {

    private static IntBuildingInstallationHandler<IntBuildingInstallation> intBuildingInstallationHandler = new IntBuildingInstallationHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        IntBuildingInstallation intBuildingInstallation = element.getIntBuildingInstallation();
        if (intBuildingInstallation == null) {
            Utils.addXlinkFeature(node);
        } else if (intBuildingInstallation != null) {
            ElementNode<IntBuildingInstallation> elementNode = NodeFactory.createElementNode(intBuildingInstallation, INT_BUILDING_INSTALLATION, node);
            intBuildingInstallationHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }
    }
}
