package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class PostalServiceElementsHandler<T extends PostalServiceElements>
        implements TreeNodeHandler<T>, XalConstants {

    private AddressIdentifierHandler<AddressIdentifier> addressIdentifierHandler = new AddressIdentifierHandler<>();
    private EndorsementLineCodeHandler<EndorsementLineCode> endorsementLineCodeHandler = new EndorsementLineCodeHandler<>();
    private BarcodeHandler<Barcode> barcodeHandler = new BarcodeHandler<>();
    private SortingCodeHandler<SortingCode> sortingCodeHandler = new SortingCodeHandler<>();
    private AddressLatitudeHandler<AddressLatitude> addressLatitudeHandler = new AddressLatitudeHandler<>();
    private AddressLongitudeHandler<AddressLongitude> addressLongitudeHandler = new AddressLongitudeHandler<>();
    private AddressLatitudeDirectionHandler<AddressLatitudeDirection> addressLatitudeDirectionHandler = new AddressLatitudeDirectionHandler<>();
    private AddressLongitudeDirectionHandler<AddressLongitudeDirection> addressLongitudeDirectionHandler = new AddressLongitudeDirectionHandler<>();
    private SupplementaryPostalServiceDataHandler<SupplementaryPostalServiceData> supplementaryPostalServiceDataHandler = new SupplementaryPostalServiceDataHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        // attributes
        String type = element.getType();
        if (type != null) {
            AttributeNode typeNode = NodeFactory
                    .createAttributeNode(TYPE, type, node);
            node.addChild(typeNode);
        }

        // elements
        List<AddressIdentifier> addressIdentifier = element
                .getAddressIdentifier();
        processAddressIdentifiers(addressIdentifier, node);

        EndorsementLineCode endorsementLineCode = element
                .getEndorsementLineCode();
        if (endorsementLineCode != null) {
            ElementNode<EndorsementLineCode> endorsementLineCodeNode = NodeFactory
                    .createElementNode(endorsementLineCode,
                            ENDORSEMENT_LINE_CODE, node);
            endorsementLineCodeHandler.addChildsToNode(endorsementLineCodeNode);
            node.addChild(endorsementLineCodeNode);
        }

        Barcode barcode = element.getBarcode();
        if (barcode != null) {
            ElementNode<Barcode> barcodeNode = NodeFactory.createElementNode(barcode,
                    BARCODE, node);
            barcodeHandler.addChildsToNode(barcodeNode);
            node.addChild(barcodeNode);
        }

        SortingCode sortingCode = element.getSortingCode();
        if (sortingCode != null) {
            ElementNode<SortingCode> sortingCodeNode = NodeFactory.createElementNode(
                    sortingCode, SORTING_CODE, node);
            sortingCodeHandler.addChildsToNode(sortingCodeNode);
            node.addChild(sortingCodeNode);
        }

        AddressLatitude addressLatitude = element.getAddressLatitude();
        if (addressLatitude != null) {
            ElementNode<AddressLatitude> latNode = NodeFactory.createElementNode(
                    addressLatitude, ADDRESS_LATITUDE, node);
            addressLatitudeHandler.addChildsToNode(latNode);
            node.addChild(latNode);
        }

        AddressLongitude addressLongitude = element.getAddressLongitude();
        if (addressLongitude != null) {
            ElementNode<AddressLongitude> longNode = NodeFactory.createElementNode(
                    addressLongitude, ADDRESS_LONGITUDE, node);
            addressLongitudeHandler.addChildsToNode(longNode);
            node.addChild(longNode);
        }

        AddressLatitudeDirection addressLatitudeDirection = element
                .getAddressLatitudeDirection();
        if (addressLatitudeDirection != null) {
            ElementNode<AddressLatitudeDirection> latDirNode = NodeFactory
                    .createElementNode(addressLatitudeDirection,
                            ADDRESS_LATITUDE_DIRECTION, node);
            addressLatitudeDirectionHandler.addChildsToNode(latDirNode);
            node.addChild(latDirNode);
        }

        AddressLongitudeDirection addressLongitudeDirection = element
                .getAddressLongitudeDirection();
        if (addressLongitudeDirection != null) {
            ElementNode<AddressLongitudeDirection> lonDirNode = NodeFactory
                    .createElementNode(addressLongitudeDirection,
                            ADDRESS_LONGITUDE_DIRECTION, node);
            addressLongitudeDirectionHandler.addChildsToNode(lonDirNode);
            node.addChild(lonDirNode);
        }

        List<SupplementaryPostalServiceData> supplementaryPostalServiceData = element
                .getSupplementaryPostalServiceData();
        processSupplementaryPostalServiceData(supplementaryPostalServiceData,
                node);

    }

    private void processAddressIdentifiers(List<AddressIdentifier> identifiers,
                                           ElementNode<T> parent) {
        if (identifiers != null) {
            for (AddressIdentifier addressIdentifier : identifiers) {
                ElementNode<AddressIdentifier> addressIdNode = NodeFactory
                        .createElementNode(addressIdentifier,
                                ADDRESS_IDENTIFIER, parent);
                addressIdentifierHandler.addChildsToNode(addressIdNode);
                parent.addChild(addressIdNode);
            }
        }
    }

    private void processSupplementaryPostalServiceData(
            List<SupplementaryPostalServiceData> data, ElementNode<T> parent) {
        if (data != null) {
            for (SupplementaryPostalServiceData supplementaryPostalServiceData : data) {
                ElementNode<SupplementaryPostalServiceData> supplementaryPostNode = NodeFactory
                        .createElementNode(supplementaryPostalServiceData,
                                SUPPLEMENTARY_POSTAL_SERVICE_DATA, parent);
                supplementaryPostalServiceDataHandler
                        .addChildsToNode(supplementaryPostNode);

            }
        }
    }

}
