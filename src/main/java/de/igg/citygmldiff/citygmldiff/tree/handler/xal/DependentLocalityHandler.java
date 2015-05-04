package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

/**
 * User: richard
 * Date: 02.10.13
 * Time: 21:47
 */
public class DependentLocalityHandler<T extends DependentLocality> extends AddressLinesProcessor<T> {

    private static PostBoxHandler<PostBox> postBoxHandler = new PostBoxHandler<>();
    private static LargeMailUserHandler<LargeMailUser> largeMailUserHandler = new LargeMailUserHandler<>();
    private static PostOfficeHandler<PostOffice> postOfficeHandler = new PostOfficeHandler<>();
    private static PostalRouteHandler<PostalRoute> postalRouteHandler = new PostalRouteHandler<>();
    private static ThoroughfareHandler<Thoroughfare> thoroughfareHandler = new ThoroughfareHandler<>();
    private static PremiseHandler<Premise> premiseHandler = new PremiseHandler<>();
    private static DependentLocalityHandler<DependentLocality> dependentLocalityHandler = new DependentLocalityHandler<>();
    private static PostalCodeHandler<PostalCode> postalCodeHandler = new PostalCodeHandler<>();
    private static DependentLocalityNameHandler<DependentLocalityName> dependentLocalityNameHandler = new DependentLocalityNameHandler<>();
    private static DependentLocalityNumberHandler<DependentLocalityNumber> dependentLocalityNumberHandler = new DependentLocalityNumberHandler<>();

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
            AttributeNode usageNode = NodeFactory.createAttributeNode(USAGE_TYPE, usageType, node);
            node.addChild(usageNode);
        }

        String connector = element.getConnector();
        if (connector != null) {
            AttributeNode connectorNode = NodeFactory.createAttributeNode(CONNECTOR, connector, node);
            node.addChild(connectorNode);
        }

        String indicator = element.getIndicator();
        if (indicator != null) {
            AttributeNode indicatorNode = NodeFactory.createAttributeNode(INDICATOR, indicator, node);
            node.addChild(indicatorNode);
        }

        // elements
        List<AddressLine> addressLines = element.getAddressLine();
        super.processAddressLines(addressLines, node);

        List<DependentLocalityName> dependentLocalityNames = element.getDependentLocalityName();
        if (dependentLocalityNames != null) {
            processDependentLocalityNames(dependentLocalityNames, node);
        }

        DependentLocalityNumber dependentLocalityNumber = element.getDependentLocalityNumber();
        if (dependentLocalityNumber != null) {
            ElementNode<DependentLocalityNumber> depLocNumberNode = NodeFactory.createElementNode(dependentLocalityNumber, DEPENDENT_LOCALITY_NUMBER, node);
            dependentLocalityNumberHandler.addChildsToNode(depLocNumberNode);
            node.addChild(depLocNumberNode);
        }

        PostBox postBox = element.getPostBox();
        if (postBox != null) {
            ElementNode<PostBox> postBoxElementNode = NodeFactory.createElementNode(postBox, POST_BOX, node);
            postBoxHandler.addChildsToNode(postBoxElementNode);
            node.addChild(postBoxElementNode);
        }

        LargeMailUser largeMailUser = element.getLargeMailUser();
        if (largeMailUser != null) {
            ElementNode<LargeMailUser> largeMailUserElementNode = NodeFactory.createElementNode(largeMailUser, LARGE_MAIL_USER, node);
            largeMailUserHandler.addChildsToNode(largeMailUserElementNode);
            node.addChild(largeMailUserElementNode);
        }

        PostOffice postOffice = element.getPostOffice();
        if (postOffice != null) {
            ElementNode<PostOffice> postOfficeElementNode = NodeFactory.createElementNode(postOffice, POST_OFFICE, node);
            postOfficeHandler.addChildsToNode(postOfficeElementNode);
            node.addChild(postOfficeElementNode);
        }

        PostalRoute postalRoute = element.getPostalRoute();
        if (postalRoute != null) {
            ElementNode<PostalRoute> postalRouteElementNode = NodeFactory.createElementNode(postalRoute, POSTAL_ROUTE, node);
            postalRouteHandler.addChildsToNode(postalRouteElementNode);
            node.addChild(postalRouteElementNode);
        }

        Thoroughfare thoroughfare = element.getThoroughfare();
        if (thoroughfare != null) {
            ElementNode<Thoroughfare> thoroughfareElementNode = NodeFactory.createElementNode(thoroughfare, THOROUGHFARE, node);
            thoroughfareHandler.addChildsToNode(thoroughfareElementNode);
            node.addChild(thoroughfareElementNode);
        }

        Premise premise = element.getPremise();
        if (premise != null) {
            ElementNode<Premise> premiseElementNode = NodeFactory.createElementNode(premise, PREMISE, node);
            premiseHandler.addChildsToNode(premiseElementNode);
            node.addChild(premiseElementNode);
        }

        DependentLocality dependentLocality = element.getDependentLocality();
        if (dependentLocality != null) {
            ElementNode<DependentLocality> dependentLocalityElementNode = NodeFactory.createElementNode(dependentLocality, DEPENDENT_LOCALITY, node);
            dependentLocalityHandler.addChildsToNode(dependentLocalityElementNode);
            node.addChild(dependentLocalityElementNode);
        }

        PostalCode postalCode = element.getPostalCode();
        if (postalCode != null) {
            ElementNode<PostalCode> postalCodeElementNode = NodeFactory.createElementNode(postalCode, POSTAL_CODE, node);
            postalCodeHandler.addChildsToNode(postalCodeElementNode);
            node.addChild(postalCodeElementNode);
        }
    }

    private void processDependentLocalityNames(List<DependentLocalityName> dependentLocalityNames, ElementNode<T> node) {
        for (DependentLocalityName depLocName : dependentLocalityNames) {
            ElementNode<DependentLocalityName> elementNode = NodeFactory.createElementNode(depLocName, DEPENDENT_LOCALITY_NAME, node);
            dependentLocalityNameHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }
    }
}
