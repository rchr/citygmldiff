package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.xal.AddressDetailsHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.core.XalAddressProperty;
import org.citygml4j.model.xal.AddressDetails;

public class XalAddressPropertyHander<T extends XalAddressProperty> implements
        TreeNodeHandler<T>, Constants {

    private static AddressDetailsHandler<AddressDetails> addressDetailsHandler = new AddressDetailsHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();
        AddressDetails addressDetails = element.getAddressDetails();
        if (addressDetails != null) {
            String signature = Utils.buildSignature(node, addressDetails, ADDRESS_DETAILS);
            ElementNode<AddressDetails> addressDetailsNode = new ElementNode<>(
                    addressDetails, ADDRESS_DETAILS, signature, node);
            addressDetailsHandler.addChildsToNode(addressDetailsNode);
            node.addChild(addressDetailsNode);
        }
    }

}
