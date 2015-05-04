package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.BuildingNameAndAddressLineProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class SubPremiseHandler<T extends SubPremise> extends
        BuildingNameAndAddressLineProcessor<T> {

    private static SubPremiseLocationHandler<SubPremiseLocation> subPremiseLocationHandler = new SubPremiseLocationHandler<>();
    private static SubPremiseNameHandler<SubPremiseName> subPremiseNameHandler = new SubPremiseNameHandler<>();
    private static SubPremiseNumberHandler<SubPremiseNumber> subPremiseNumberHandler = new SubPremiseNumberHandler<>();
    private static SubPremiseNumberSuffixHandler<SubPremiseNumberSuffix> subPremiseNumberSuffixHandler = new SubPremiseNumberSuffixHandler<>();
    private static SubPremiseNumberPrefixHandler<SubPremiseNumberPrefix> subPremiseNumberPrefixHandler = new SubPremiseNumberPrefixHandler<>();
    private static SubPremiseHandler<SubPremise> subPremiseHandler = new SubPremiseHandler<>();
    private static FirmHandler<Firm> firmHandler = new FirmHandler<>();
    private static MailStopHandler<MailStop> mailStopHandler = new MailStopHandler<>();
    private static PostalCodeHandler<PostalCode> postalCodeHandler = new PostalCodeHandler<>();

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

        List<AddressLine> addressLines = element.getAddressLine();
        processAddressLines(addressLines, node);

        SubPremiseLocation subPremiseLocation = element.getSubPremiseLocation();
        if (subPremiseLocation != null) {
            ElementNode<SubPremiseLocation> locationNode = NodeFactory
                    .createElementNode(subPremiseLocation,
                            SUB_PREMISE_LOCATION, node);
            subPremiseLocationHandler.addChildsToNode(locationNode);
            node.addChild(locationNode);
        }

        List<SubPremiseName> subPremiseNames = element.getSubPremiseName();
        processSubPremisesName(subPremiseNames, node);

        List<SubPremiseNumber> subPremiseNumbers = element
                .getSubPremiseNumber();
        processSubPremiseNumbers(subPremiseNumbers, node);

        List<SubPremiseNumberSuffix> subPremiseNumberSuffixes = element
                .getSubPremiseNumberSuffix();
        processNumberSuffixes(subPremiseNumberSuffixes, node);

        List<SubPremiseNumberPrefix> subPremiseNumberPrefixes = element
                .getSubPremiseNumberPrefix();
        processNumberPrefixes(subPremiseNumberPrefixes, node);

        List<BuildingName> buildingNames = element.getBuildingName();
        processBuildingNames(buildingNames, node);

        SubPremise subPremise = element.getSubPremise();
        if (subPremise != null) {
            ElementNode<SubPremise> subPremiseNode = NodeFactory.createElementNode(
                    subPremise, SUB_PREMISE, node);
            subPremiseHandler.addChildsToNode(subPremiseNode);
            node.addChild(subPremiseNode);
        }

        MailStop mailStop = element.getMailStop();
        if (mailStop != null) {
            ElementNode<MailStop> mailStopNode = NodeFactory.createElementNode(
                    mailStop, MAIL_STOP, node);
            mailStopHandler.addChildsToNode(mailStopNode);
            node.addChild(mailStopNode);
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

    private void processNumberPrefixes(
            List<SubPremiseNumberPrefix> subPremiseNumberPrefixes,
            ElementNode<T> node) {
        if (subPremiseNumberPrefixes != null) {
            for (SubPremiseNumberPrefix subPremiseNumberPrefix : subPremiseNumberPrefixes) {
                ElementNode<SubPremiseNumberPrefix> prefixNode = NodeFactory
                        .createElementNode(subPremiseNumberPrefix,
                                SUB_PREMISE_NUMBER_PREFIX, node);
                subPremiseNumberPrefixHandler.addChildsToNode(prefixNode);
                node.addChild(prefixNode);
            }
        }
    }

    private void processNumberSuffixes(
            List<SubPremiseNumberSuffix> subPremiseNumberSuffixes,
            ElementNode<T> node) {
        if (subPremiseNumberSuffixes != null) {
            for (SubPremiseNumberSuffix subPremiseNumberSuffix : subPremiseNumberSuffixes) {
                ElementNode<SubPremiseNumberSuffix> suffixNode = NodeFactory
                        .createElementNode(subPremiseNumberSuffix,
                                SUB_PREMISE_NUMBER_SUFFIX, node);
                subPremiseNumberSuffixHandler.addChildsToNode(suffixNode);
                node.addChild(suffixNode);
            }
        }
    }

    private void processSubPremiseNumbers(
            List<SubPremiseNumber> subPremiseNumbers, ElementNode<T> node) {
        if (subPremiseNumbers != null) {
            for (SubPremiseNumber subPremiseNumber : subPremiseNumbers) {
                ElementNode<SubPremiseNumber> numberNode = NodeFactory
                        .createElementNode(subPremiseNumber,
                                SUB_PREMISE_NUMBER, node);
                subPremiseNumberHandler.addChildsToNode(numberNode);
                node.addChild(numberNode);
            }
        }
    }

    private void processSubPremisesName(List<SubPremiseName> subPremiseNames,
                                        ElementNode<T> node) {
        if (subPremiseNames != null) {
            for (SubPremiseName subPremiseName : subPremiseNames) {
                ElementNode<SubPremiseName> nameNode = NodeFactory.createElementNode(
                        subPremiseName, SUB_PREMISE_NAME, node);
                subPremiseNameHandler.addChildsToNode(nameNode);
                node.addChild(nameNode);
            }
        }
    }

}
