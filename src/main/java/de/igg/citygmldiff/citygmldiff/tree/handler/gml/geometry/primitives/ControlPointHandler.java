package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.ControlPoint;
import org.citygml4j.model.gml.geometry.primitives.DirectPositionList;
import org.citygml4j.model.gml.geometry.primitives.GeometricPositionGroup;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 16:16
 */
public class ControlPointHandler<T extends ControlPoint> implements TreeNodeHandler<T> {

    private static DirectPositionListHandler<DirectPositionList> directPositionListHandler = new DirectPositionListHandler<>();
    private static GeometricPositionGroupHandler<GeometricPositionGroup> geometricPositionGroupHandler = new GeometricPositionGroupHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        DirectPositionList posList = element.getPosList();
        if (posList != null) {
            ElementNode<DirectPositionList> directPositionListElementNode = NodeFactory.createElementNode(posList, Constants.POS_LIST, node);
            directPositionListHandler.addChildsToNode(directPositionListElementNode);
            node.addChild(directPositionListElementNode);
        }
        List<GeometricPositionGroup> geometricPositionGroups = element.getGeometricPositionGroup();
        if (geometricPositionGroups != null) {
            processGeometricPositionGroups(geometricPositionGroups, node);
        }
    }

    private void processGeometricPositionGroups(List<GeometricPositionGroup> geometricPositionGroups, ElementNode<T> node) {
        for (GeometricPositionGroup geometricPositionGroup : geometricPositionGroups) {
            ElementNode<GeometricPositionGroup> geometricPositionGroupElementNode = NodeFactory.createElementNode(geometricPositionGroup, "geometricPositionGroup", node);
            geometricPositionGroupHandler.addChildsToNode(geometricPositionGroupElementNode);
            node.addChild(geometricPositionGroupElementNode);
        }
    }

}
