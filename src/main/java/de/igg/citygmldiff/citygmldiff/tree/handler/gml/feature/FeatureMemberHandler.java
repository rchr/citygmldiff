package de.igg.citygmldiff.citygmldiff.tree.handler.gml.feature;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.gml.feature.FeatureMember;

/**
 * User: richard
 * Date: 15.11.13
 * Time: 11:58
 */
public class FeatureMemberHandler<T extends FeatureMember> implements TreeNodeHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();


    }
}
