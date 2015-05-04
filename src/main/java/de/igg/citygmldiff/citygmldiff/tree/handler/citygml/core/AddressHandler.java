package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.feature.AbstractFeatureHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.core.Address;
import org.citygml4j.model.citygml.core.XalAddressProperty;
import org.citygml4j.model.gml.geometry.aggregates.MultiPointProperty;

import java.util.List;

public class AddressHandler<T extends Address> extends
        AbstractFeatureHandler<T> implements Constants {

    private static XalAddressPropertyHander<XalAddressProperty> xalAddressPropertyHander = new XalAddressPropertyHander<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        XalAddressProperty xalAddressProperty = element.getXalAddress();
        if (xalAddressProperty != null) {
            String signature = Utils.buildSignature(node, xalAddressProperty, XAL_ADDRESS);
            ElementNode<XalAddressProperty> xalAElementNode = new ElementNode<>(
                    xalAddressProperty, XAL_ADDRESS, signature, node);
            xalAddressPropertyHander.addChildsToNode(xalAElementNode);
            node.addChild(xalAElementNode);
        }

        MultiPointProperty multiPointProperty = element.getMultiPoint();
        if (multiPointProperty != null) {
            ElementNode<MultiPointProperty> elementNode = NodeFactory.createElementNode(multiPointProperty, MULTI_POINT, node);
        }

        List<ADEComponent> genericApplicationPropertyOfAddress = element
                .getGenericApplicationPropertyOfAddress();
    }

}
