package de.igg.citygmldiff.citygmldiff.tree.handler.gml.feature;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import org.citygml4j.model.gml.feature.LocationProperty;

/**
 * User: richard
 * Date: 02.10.13
 * Time: 19:55
 */
public class LocationPropertyHandler<T extends LocationProperty> implements TreeNodeHandler<T>, Constants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        // TODO: implement this!!!
    }
}
