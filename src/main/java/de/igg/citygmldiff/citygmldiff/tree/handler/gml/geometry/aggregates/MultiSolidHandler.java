package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.SolidArrayPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.SolidPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.aggregates.MultiSolid;
import org.citygml4j.model.gml.geometry.primitives.SolidArrayProperty;
import org.citygml4j.model.gml.geometry.primitives.SolidProperty;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 13:12
 */
public class MultiSolidHandler<T extends MultiSolid> extends AbstractGeometricAggregateHandler<T> implements Constants {

    private static SolidPropertyHandler<SolidProperty> solidPropertyHandler = new SolidPropertyHandler<>();
    private static SolidArrayPropertyHandler<SolidArrayProperty> solidArrayPropertyHandler = new SolidArrayPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<SolidProperty> solidMember = element.getSolidMember();
        if (solidMember != null) {
            processSolidMember(solidMember, node);
        }

        SolidArrayProperty solidMembers = element.getSolidMembers();
        if (solidMembers != null) {
            ElementNode<SolidArrayProperty> solidArrayPropertyElementNode = NodeFactory.createElementNode(solidMembers, SOLID_MEMBERS, node);
            solidArrayPropertyHandler.addChildsToNode(solidArrayPropertyElementNode);
            node.addChild(solidArrayPropertyElementNode);
        }
    }

    private void processSolidMember(List<SolidProperty> solidMember, ElementNode<T> node) {
        for (SolidProperty solidProperty : solidMember) {
            ElementNode<SolidProperty> solidPropertyElementNode = NodeFactory.createElementNode(solidProperty, SOLID_MEMBER, node);
            solidPropertyHandler.addChildsToNode(solidPropertyElementNode);
            node.addChild(solidPropertyElementNode);
        }
    }
}
