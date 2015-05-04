package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.core.Address;
import org.citygml4j.model.citygml.core.AddressProperty;

public class AddressPropertyHandler<T extends AddressProperty> implements TreeNodeHandler<T>, Constants {

    private static AddressHandler<Address> addressHandler = new AddressHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();
        Address address = element.getAddress();
        if (address == null) {
            Utils.addXlinkFeature(node);
        } else if (address != null) {
            String signature = Utils.buildSignature(node, address, ADDRESS);
            ElementNode<Address> addressNode = new ElementNode<>(address, ADDRESS, signature, node);
            addressHandler.addChildsToNode(addressNode);
            node.addChild(addressNode);
        }
    }
}
