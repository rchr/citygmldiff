package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class PremiseHandler<T extends Premise> extends AddressLinesProcessor<T> {

    private static PremiseLocationHandler<PremiseLocation> premiseLocationHandler = new PremiseLocationHandler<>();
    private static PremiseNameHandler<PremiseName> premiseNameHandler = new PremiseNameHandler<>();
    private static PremiseNumberHandler<PremiseNumber> premiseNumberHandler = new PremiseNumberHandler<>();
    private static PremiseNumberRangeHandler<PremiseNumberRange> premiseNumberRangeHandler = new PremiseNumberRangeHandler<>();
    private static PremiseNumberSuffixHandler<PremiseNumberSuffix> premiseNumberSuffixHandler = new PremiseNumberSuffixHandler<>();
    private static PremiseNumberPrefixHandler<PremiseNumberPrefix> premiseNumberPrefixHandler = new PremiseNumberPrefixHandler<>();
    private static BuildingNameHandler<BuildingName> buildingNameHandler = new BuildingNameHandler<>();
    private static MailStopHandler<MailStop> mailStopHandler = new MailStopHandler<>();
    private static FirmHandler<Firm> firmHandler = new FirmHandler<>();
    private static PostalCodeHandler<PostalCode> postalCodeHandler = new PostalCodeHandler<>();
    private static PremiseHandler<Premise> premiseHandler = new PremiseHandler<>();
    private static SubPremiseHandler<SubPremise> subPremiseHandler = new SubPremiseHandler<>();

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

        String premiseDependency = element.getPremiseDependency();
        if (premiseDependency != null) {
            AttributeNode dependencyNode = NodeFactory.createAttributeNode(
                    PREMISE_DEPENDENCY, premiseDependency, node);
            node.addChild(dependencyNode);
        }

        String premiseDependencyType = element.getPremiseDependencyType();
        if (premiseDependencyType != null) {
            AttributeNode dependencyNode = NodeFactory.createAttributeNode(
                    PREMISE_DEPENDENCY_TYPE, premiseDependencyType, node);
            node.addChild(dependencyNode);
        }

        String premiseThoroughfareConnector = element
                .getPremiseThoroughfareConnector();
        if (premiseThoroughfareConnector != null) {
            AttributeNode connectorNode = NodeFactory.createAttributeNode(
                    PREMISE_THOROUGHFARE_CONNCECTOR,
                    premiseThoroughfareConnector, node);
            node.addChild(connectorNode);
        }

        // elements
        List<AddressLine> addressLines = element.getAddressLine();
        processAddressLines(addressLines, node);

        PremiseLocation premiseLocation = element.getPremiseLocation();
        if (premiseLocation != null) {
            ElementNode<PremiseLocation> locationNode = NodeFactory
                    .createElementNode(premiseLocation, PREMISE_LOCATION, node);
            premiseLocationHandler.addChildsToNode(locationNode);
            node.addChild(locationNode);
        }

        List<PremiseName> premiseNames = element.getPremiseName();
        processPremiseNames(premiseNames, node);

        List<PremiseNumber> premiseNumbers = element.getPremiseNumber();
        processPremiseNumbers(premiseNumbers, node);

        PremiseNumberRange premiseNumberRange = element.getPremiseNumberRange();
        if (premiseNumberRange != null) {
            ElementNode<PremiseNumberRange> rangeNode = NodeFactory
                    .createElementNode(premiseNumberRange,
                            PREMISE_NUMBER_RANGE, node);
            premiseNumberRangeHandler.addChildsToNode(rangeNode);
            node.addChild(rangeNode);
        }

        List<PremiseNumberSuffix> premiseNumberSuffixes = element
                .getPremiseNumberSuffix();
        processPremiseNumberSuffixes(premiseNumberSuffixes, node);

        List<PremiseNumberPrefix> premiseNumberPrefixes = element
                .getPremiseNumberPrefix();
        processPremiseNumberPrefixes(premiseNumberPrefixes, node);

        List<BuildingName> buildingNames = element.getBuildingName();
        processBuildingNames(buildingNames, node);

        MailStop mailStop = element.getMailStop();
        if (mailStop != null) {
            ElementNode<MailStop> mailStopNode = NodeFactory.createElementNode(
                    mailStop, MAIL_STOP, node);
            mailStopHandler.addChildsToNode(mailStopNode);
            node.addChild(mailStopNode);
        }

        List<SubPremise> subPremises = element.getSubPremise();
        processSubPremises(subPremises, node);

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

        Premise premise2 = element.getPremise();
        if (premise2 != null) {
            ElementNode<Premise> premiseNode = NodeFactory.createElementNode(
                    premise2, PREMISE, node);
            premiseHandler.addChildsToNode(premiseNode);
            node.addChild(premiseNode);
        }
    }

    private void processSubPremises(List<SubPremise> subPremises,
                                    ElementNode<T> node) {
        if (subPremises != null) {
            for (SubPremise subPremise : subPremises) {
                ElementNode<SubPremise> subNode = NodeFactory.createElementNode(
                        subPremise, SUB_PREMISE, node);
                subPremiseHandler.addChildsToNode(subNode);
                node.addChild(subNode);
            }
        }
    }

    private void processBuildingNames(List<BuildingName> buildingNames,
                                      ElementNode<T> node) {
        if (buildingNames != null) {
            for (BuildingName buildingName : buildingNames) {
                ElementNode<BuildingName> nameNode = NodeFactory.createElementNode(
                        buildingName, BUILDING_NAME, node);
                buildingNameHandler.addChildsToNode(nameNode);
                node.addChild(nameNode);
            }
        }
    }

    private void processPremiseNumberPrefixes(
            List<PremiseNumberPrefix> premiseNumberPrefixes, ElementNode<T> node) {
        if (premiseNumberPrefixes != null) {
            for (PremiseNumberPrefix premiseNumberPrefix : premiseNumberPrefixes) {
                ElementNode<PremiseNumberPrefix> prefixNode = NodeFactory
                        .createElementNode(premiseNumberPrefix,
                                PREMISE_NUMBER_PREFIX, node);
                premiseNumberPrefixHandler.addChildsToNode(prefixNode);
                node.addChild(prefixNode);
            }
        }
    }

    private void processPremiseNumberSuffixes(
            List<PremiseNumberSuffix> premiseNumberSuffixes, ElementNode<T> node) {
        if (premiseNumberSuffixes != null) {
            for (PremiseNumberSuffix premiseNumberSuffix : premiseNumberSuffixes) {
                ElementNode<PremiseNumberSuffix> suffixNode = NodeFactory
                        .createElementNode(premiseNumberSuffix,
                                PREMISE_NUMBER_SUFFIX, node);
                premiseNumberSuffixHandler.addChildsToNode(suffixNode);
                node.addChild(suffixNode);
            }
        }
    }

    private void processPremiseNumbers(List<PremiseNumber> premiseNumbers,
                                       ElementNode<T> node) {
        if (premiseNumbers != null) {
            for (PremiseNumber premiseNumber : premiseNumbers) {
                ElementNode<PremiseNumber> numberNode = NodeFactory
                        .createElementNode(premiseNumber, PREMISE_NUMBER, node);
                premiseNumberHandler.addChildsToNode(numberNode);
                node.addChild(numberNode);
            }
        }
    }

    private void processPremiseNames(List<PremiseName> premiseNames,
                                     ElementNode<T> node) {
        if (premiseNames != null) {
            for (PremiseName premiseName : premiseNames) {
                ElementNode<PremiseName> nameNode = NodeFactory.createElementNode(
                        premiseName, PREMISE_NAME, node);
                premiseNameHandler.addChildsToNode(nameNode);
                node.addChild(nameNode);
            }
        }
    }

}
