package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.xal.*;

public class AddressDetailsHandler<T extends AddressDetails> implements
        TreeNodeHandler<T>, XalConstants {

    private static CountryHandler<Country> countryHandler = new CountryHandler<>();
    private static LocalityHandler<Locality> localityHandler = new LocalityHandler<>();
    private static ThoroughfareHandler<Thoroughfare> thoroughfareHandler = new ThoroughfareHandler<>();
    private static PostalServiceElementsHandler<PostalServiceElements> postalServiceElementsHandler = new PostalServiceElementsHandler<>();
    private static AddressHandler<Address> addressHandler = new AddressHandler<>();
    private static AddressLinesHandler<AddressLines> addressLinesHandler = new AddressLinesHandler<>();
    private static AdministrativeAreaHandler<AdministrativeArea> administrativeAreaHandler = new AdministrativeAreaHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        // attributes
        String addressType = element.getAddressType();
        if (addressType != null) {
            AttributeNode typeSignatureNode = NodeFactory.createAttributeNode(
                    ADDRESS_TYPE, addressType, node);
            node.addChild(typeSignatureNode);
        }

        String addressStatus = element.getCurrentStatus();
        if (addressStatus != null) {
            AttributeNode statusNode = NodeFactory.createAttributeNode(
                    CURRENT_STATUS, addressStatus, node);
            node.addChild(statusNode);
        }

        String addressValidFromDate = element.getValidFromDate();
        if (addressValidFromDate != null) {
            AttributeNode validFromNode = NodeFactory.createAttributeNode(
                    VALID_FROM_DATE, addressValidFromDate, node);
            node.addChild(validFromNode);
        }
        String addressValidToDate = element.getValidToDate();
        if (addressValidToDate != null) {
            AttributeNode validToNode = NodeFactory.createAttributeNode(
                    VALID_TO_DATE, addressValidToDate, node);
            node.addChild(validToNode);
        }

        String usage = element.getUsage();
        if (usage != null) {
            AttributeNode usageNode = NodeFactory.createAttributeNode(USAGE, usage,
                    node);
            node.addChild(usageNode);
        }

        String code = element.getCode();
        if (code != null) {
            AttributeNode codeNode = NodeFactory
                    .createAttributeNode(CODE, code, node);
            node.addChild(codeNode);
        }

        String addressDetailsKey = element.getAddressDetailsKey();
        if (addressDetailsKey != null) {
            AttributeNode detailsKeyNode = NodeFactory.createAttributeNode(
                    ADDRESS_DETAILS_KEY, addressDetailsKey, node);
            node.addChild(detailsKeyNode);
        }

        // Elements
        PostalServiceElements postalServiceElements = element
                .getPostalServiceElements();
        if (postalServiceElements != null) {
            ElementNode<PostalServiceElements> postalServiceElNode = NodeFactory
                    .createElementNode(postalServiceElements,
                            POSTAL_SERVICE_ELEMENTS, node);
            postalServiceElementsHandler.addChildsToNode(postalServiceElNode);
            node.addChild(postalServiceElNode);
        }

        Address address = element.getAddress();
        if (address != null) {
            ElementNode<Address> addressNode = NodeFactory.createElementNode(address,
                    ADDRESS, node);
            addressHandler.addChildsToNode(addressNode);
            node.addChild(addressNode);
        }

        AddressLines addressLines = element.getAddressLines();
        if (addressLines != null) {
            ElementNode<AddressLines> addressLinesNode = NodeFactory
                    .createElementNode(addressLines, ADDRESS_LINES, node);
            addressLinesHandler.addChildsToNode(addressLinesNode);
            node.addChild(addressLinesNode);
        }

        Country country = element.getCountry();
        if (country != null) {
            String signature = Utils.buildSignature(node, country, COUNTRY);
            ElementNode<Country> countryNode = new ElementNode<>(
                    country, COUNTRY, signature, node);
            countryHandler.addChildsToNode(countryNode);
            node.addChild(countryNode);
        }

        AdministrativeArea administrativeArea = element.getAdministrativeArea();
        if (administrativeArea != null) {
            ElementNode<AdministrativeArea> adminAreaNode = NodeFactory.createElementNode(administrativeArea, ADMINISTRATIVE_AREA, node);
            administrativeAreaHandler.addChildsToNode(adminAreaNode);
            node.addChild(adminAreaNode);
        }

        Locality locality = element.getLocality();
        if (locality != null) {
            ElementNode<Locality> localityNode = NodeFactory.createElementNode(
                    locality, LOCALITY, node);
            localityHandler.addChildsToNode(localityNode);
            node.addChild(localityNode);
        }

        Thoroughfare thoroughfare = element.getThoroughfare();
        if (thoroughfare != null) {
            ElementNode<Thoroughfare> thoroughfareNode = NodeFactory
                    .createElementNode(thoroughfare, THOROUGHFARE, node);
            thoroughfareHandler.addChildsToNode(thoroughfareNode);
            node.addChild(thoroughfareNode);
        }

    }

}
