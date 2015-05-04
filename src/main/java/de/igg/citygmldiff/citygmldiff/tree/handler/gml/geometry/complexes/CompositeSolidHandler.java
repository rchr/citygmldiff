package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.AbstractSolidHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.SolidPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.complexes.CompositeSolid;
import org.citygml4j.model.gml.geometry.primitives.SolidProperty;

import java.util.List;

/**
 * User: richard
 * Date: 19.09.13
 * Time: 20:00
 */
public class CompositeSolidHandler<T extends CompositeSolid> extends AbstractSolidHandler<T> implements Constants {

    private static SolidPropertyHandler<SolidProperty> solidPropertySolidPropertyHandler = new SolidPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<SolidProperty> solidMembers = element.getSolidMember();
        if (solidMembers != null) {
            processSolidMembers(solidMembers, node);
        }
    }

    private void processSolidMembers(List<SolidProperty> solidMembers, ElementNode<T> node) {
        for (SolidProperty solidProperty : solidMembers) {
            ElementNode<SolidProperty> solidNode = NodeFactory.createElementNode(solidProperty, SOLID_MEMBER, node);
            solidPropertySolidPropertyHandler.addChildsToNode(solidNode);
            node.addChild(solidNode);
        }
    }
}
