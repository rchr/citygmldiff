package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class AdministrativeAreaHandler<T extends AdministrativeArea> extends
        AddressLinesProcessor<T> {

    private AdministrativeAreaNameHandler<AdministrativeAreaName> administrativeAreaNameHandler = new AdministrativeAreaNameHandler<>();
    private SubAdministrativeAreaHandler<SubAdministrativeArea> subAdministrativeAreaHandler = new SubAdministrativeAreaHandler<>();
    private LocalityHandler<Locality> localityHandler = new LocalityHandler<>();
    private PostOfficeHandler<PostOffice> postOfficeHandler = new PostOfficeHandler<>();
    private PostalCodeHandler<PostalCode> postalCodeHandler = new PostalCodeHandler<>();

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

        String usageType = element.getUsageType();
        if (usageType != null) {
            AttributeNode usageNode = NodeFactory.createAttributeNode(USAGE_TYPE,
                    usageType, node);
            node.addChild(usageNode);
        }

        String indicator = element.getIndicator();
        if (indicator != null) {
            AttributeNode indicatorNode = NodeFactory.createAttributeNode(INDICATOR,
                    indicator, node);
            node.addChild(indicatorNode);
        }

        // elements
        List<AddressLine> addressLines = element.getAddressLine();
        processAddressLines(addressLines, node);

        List<AdministrativeAreaName> administrativeAreaNames = element
                .getAdministrativeAreaName();
        processAdministrativeAreaNames(administrativeAreaNames, node);

        SubAdministrativeArea subAdministrativeArea = element
                .getSubAdministrativeArea();
        if (subAdministrativeArea != null) {
            ElementNode<SubAdministrativeArea> subAdminAreaNode = NodeFactory
                    .createElementNode(subAdministrativeArea,
                            SUB_ADMINISTRATIVE_AREA, node);
            subAdministrativeAreaHandler.addChildsToNode(subAdminAreaNode);
            node.addChild(subAdminAreaNode);
        }

        Locality locality = element.getLocality();
        if (locality != null) {
            ElementNode<Locality> localityNode = NodeFactory.createElementNode(
                    locality, LOCALITY, node);
            localityHandler.addChildsToNode(localityNode);
            node.addChild(localityNode);
        }

        PostOffice postOffice = element.getPostOffice();
        if (postOffice != null) {
            ElementNode<PostOffice> officeNode = NodeFactory.createElementNode(
                    postOffice, POST_OFFICE, node);
            postOfficeHandler.addChildsToNode(officeNode);
            node.addChild(officeNode);
        }

        PostalCode postalCode = element.getPostalCode();
        if (postalCode != null) {
            ElementNode<PostalCode> codeNode = NodeFactory.createElementNode(
                    postalCode, POSTAL_CODE, node);
            postalCodeHandler.addChildsToNode(codeNode);
            node.addChild(codeNode);
        }
    }

    private void processAdministrativeAreaNames(
            List<AdministrativeAreaName> administrativeAreaNames,
            ElementNode<T> parent) {
        if (administrativeAreaNames != null) {
            for (AdministrativeAreaName administrativeAreaName : administrativeAreaNames) {
                ElementNode<AdministrativeAreaName> adminAreaNameNode = NodeFactory
                        .createElementNode(administrativeAreaName,
                                ADMINISTRATIVE_AREA_NAME, parent);
                administrativeAreaNameHandler
                        .addChildsToNode(adminAreaNameNode);
                parent.addChild(adminAreaNameNode);
            }
        }
    }

}
