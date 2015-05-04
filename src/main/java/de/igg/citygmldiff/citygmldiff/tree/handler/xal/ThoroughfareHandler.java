package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class ThoroughfareHandler<T extends Thoroughfare> extends
        AddressLinesProcessor<T> {

    private static ThoroughfareNumberHandler<ThoroughfareNumber> thoroughfareNumberHandler = new ThoroughfareNumberHandler<>();
    private static ThoroughfareNameHandler<ThoroughfareName> thoroughfareNameHandler = new ThoroughfareNameHandler<>();
    private static FirmHandler<Firm> firmHandler = new FirmHandler<>();
    private static PostalCodeHandler<PostalCode> postalCodeHandler = new PostalCodeHandler<>();
    private static PremiseHandler<Premise> premiseHandler = new PremiseHandler<>();

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
        String dependentThoroughfaresAttr = element.getDependentThoroughfares();
        if (dependentThoroughfaresAttr != null) {
            AttributeNode attributeNode = NodeFactory.createAttributeNode(DEPENDENT_THOROUGHFARES, dependentThoroughfaresAttr, node);
            node.addChild(attributeNode);
        }
        String dependentThoroughfaresIndicatorAttr = element
                .getDependentThoroughfaresIndicator();
        if (dependentThoroughfaresIndicatorAttr != null) {
            AttributeNode attributeNode = NodeFactory.createAttributeNode(DEPENDENT_THOROUGHFARES_INDICATOR, dependentThoroughfaresIndicatorAttr, node);
            node.addChild(attributeNode);
        }

        String dependentThoroughfaresConnectorAttr = element
                .getDependentThoroughfaresConnector();
        if (dependentThoroughfaresConnectorAttr != null) {
            AttributeNode attributeNode = NodeFactory.createAttributeNode(DEPENDENT_THOROUGHFARES_CONNECTOR, dependentThoroughfaresConnectorAttr, node);
            node.addChild(attributeNode);
        }

        String dependentThoroughfaresTypeAttr = element
                .getDependentThoroughfaresType();
        if (dependentThoroughfaresTypeAttr != null) {
            AttributeNode attributeNode = NodeFactory.createAttributeNode(DEPENDENT_THOROUGHFARES_TYPE, dependentThoroughfaresTypeAttr, node);
            node.addChild(attributeNode);
        }

        // elements
        List<AddressLine> addressLines = element.getAddressLine();
        processAddressLines(addressLines, node);

        List<ThoroughfareName> thoroughfareNames = element
                .getThoroughfareName();
        processThoroughfareNames(thoroughfareNames, node);

        ThoroughfarePreDirection thoroughfarePreDirection = element
                .getThoroughfarePreDirection();
        ThoroughfareLeadingType thoroughfareLeadingType = element
                .getThoroughfareLeadingType();
        ThoroughfareTrailingType thoroughfareTrailingType = element
                .getThoroughfareTrailingType();

        List<ThoroughfareNumberOrRange> thoroughfareNumberOrThoroughfareNumberRanges = element
                .getThoroughfareNumberOrThoroughfareNumberRange();
        processThoroughfareNumberOrRanges(
                thoroughfareNumberOrThoroughfareNumberRanges, node);

        List<ThoroughfareNumberPrefix> thoroughfareNumberPrefixs = element
                .getThoroughfareNumberPrefix();
        List<ThoroughfareNumberSuffix> thoroughfareNumberSuffixs = element
                .getThoroughfareNumberSuffix();
        ThoroughfarePostDirection thoroughfarePostDirection = element
                .getThoroughfarePostDirection();
        DependentThoroughfare dependentThoroughfare = element
                .getDependentThoroughfare();

        Premise premise = element.getPremise();
        if (premise != null) {
            ElementNode<Premise> premiseNode = NodeFactory.createElementNode(premise,
                    PREMISE, node);
            premiseHandler.addChildsToNode(premiseNode);
            node.addChild(premiseNode);
        }

        Firm firm = element.getFirm();
        if (firm != null) {
            ElementNode<Firm> firmNode = NodeFactory.createElementNode(firm, FIRM,
                    node);
            firmHandler.addChildsToNode(firmNode);
            node.addChild(firmNode);
        }

        PostalCode postalCode = element.getPostalCode();
        if (postalCode != null) {
            ElementNode<PostalCode> codeNode = NodeFactory.createElementNode(
                    postalCode, POSTAL_CODE, node);
            postalCodeHandler.addChildsToNode(codeNode);
            node.addChild(codeNode);
        }
    }

    private void processThoroughfareNumberOrRanges(
            List<ThoroughfareNumberOrRange> numberOrRanges,
            ElementNode<T> parent) {
        if (numberOrRanges != null) {
            for (ThoroughfareNumberOrRange thoroughfareNumberOrRange : numberOrRanges) {
                ThoroughfareNumber thoroughfareNumber = thoroughfareNumberOrRange
                        .getThoroughfareNumber();
                if (thoroughfareNumber != null) {
                    ElementNode<ThoroughfareNumber> thoroughfareNumberNode = NodeFactory
                            .createElementNode(thoroughfareNumber,
                                    THOROUGHFARE_NUMBER, parent);
                    thoroughfareNumberHandler
                            .addChildsToNode(thoroughfareNumberNode);
                    parent.addChild(thoroughfareNumberNode);
                }
                ThoroughfareNumberRange thoroughfareNumberRange = thoroughfareNumberOrRange
                        .getThoroughfareNumberRange();
                if (thoroughfareNumberRange != null) {

                }
            }
        }
    }

    private void processThoroughfareNames(List<ThoroughfareName> names,
                                          ElementNode<T> parent) {
        if (names != null) {
            for (ThoroughfareName thoroughfareName : names) {
                ElementNode<ThoroughfareName> thoroughfareNameNode = NodeFactory
                        .createElementNode(thoroughfareName, THOROUGHFARE_NAME,
                                parent);
                thoroughfareNameHandler.addChildsToNode(thoroughfareNameNode);
                parent.addChild(thoroughfareNameNode);
            }
        }
    }

}
