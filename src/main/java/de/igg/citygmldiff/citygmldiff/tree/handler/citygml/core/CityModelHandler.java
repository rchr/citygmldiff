package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.feature.AbstractFeatureCollectionHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.citygml.core.CityObjectMember;

/**
 * User: richard
 * Date: 15.11.13
 * Time: 12:06
 */
public class CityModelHandler<T extends CityModel> extends AbstractFeatureCollectionHandler<T> implements Constants {

    private static CityObjectMemberHandler<CityObjectMember> cityObjectMemberHandler = new CityObjectMemberHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T cityModel = node.getValue();
        for (CityObjectMember member : cityModel.getCityObjectMember()) {
            ElementNode<CityObjectMember> cityObjectMemberNode = NodeFactory.createElementNode(member, CITY_OBJECT_MEMBER, node);
            cityObjectMemberHandler.addChildsToNode(cityObjectMemberNode);
            node.addChild(cityObjectMemberNode);
            cityObjectMemberNode.setParent(node);
        }
    }

}
