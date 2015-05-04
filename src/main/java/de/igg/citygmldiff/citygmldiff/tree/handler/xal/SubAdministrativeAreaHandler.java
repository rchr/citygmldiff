package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class SubAdministrativeAreaHandler<T extends SubAdministrativeArea>
        extends AddressLinesProcessor<T> {

    private static PostOfficeHandler<PostOffice> postOfficeHandler = new PostOfficeHandler<>();
    private static PostalCodeHandler<PostalCode> postalCodeHandler = new PostalCodeHandler<>();
    private SubAdministrativeAreaNameHandler<SubAdministrativeAreaName> subAdministrativeAreaNameHandler = new SubAdministrativeAreaNameHandler<>();
    private LocalityHandler<Locality> localityHandler = new LocalityHandler<>();

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

        String indicator1 = element.getIndicator();
        if (indicator1 != null) {
            AttributeNode indicatorNode = NodeFactory.createAttributeNode(INDICATOR,
                    indicator1, node);
            node.addChild(indicatorNode);
        }

        // elements
        List<AddressLine> addressLines = element.getAddressLine();
        processAddressLines(addressLines, node);

        List<SubAdministrativeAreaName> subAdministrativeAreaNames = element
                .getSubAdministrativeAreaName();
        processSubAdminAreaNames(subAdministrativeAreaNames, node);

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

    private void processSubAdminAreaNames(
            List<SubAdministrativeAreaName> subAdministrativeAreaNames,
            ElementNode<T> node) {
        if (subAdministrativeAreaNames != null) {
            for (SubAdministrativeAreaName subAdministrativeAreaName : subAdministrativeAreaNames) {
                ElementNode<SubAdministrativeAreaName> subAdminAreaNameNode = NodeFactory
                        .createElementNode(subAdministrativeAreaName,
                                SUB_ADMINISTRATIVE_AREA_NAME, node);
                subAdministrativeAreaNameHandler
                        .addChildsToNode(subAdminAreaNameNode);
                node.addChild(subAdminAreaNameNode);
            }
        }

    }

}
