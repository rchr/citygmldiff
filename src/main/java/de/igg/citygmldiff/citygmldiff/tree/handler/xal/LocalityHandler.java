package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class LocalityHandler<T extends Locality> extends AddressLinesProcessor<T> {

    private static LocalityNameHandler<LocalityName> localityNameHandler = new LocalityNameHandler<>();
    private static ThoroughfareHandler<Thoroughfare> thoroughfareHandler = new ThoroughfareHandler<>();
    private static PostBoxHandler<PostBox> postBoxHandler = new PostBoxHandler<>();
    private static PostOfficeHandler<PostOffice> postOfficeHandler = new PostOfficeHandler<>();
    private static PostalCodeHandler<PostalCode> postalCodeHandler = new PostalCodeHandler<>();
    private static PostalRouteHandler<PostalRoute> postalRouteHandler = new PostalRouteHandler<>();
    private static LargeMailUserHandler<LargeMailUser> largeMailUserHandler = new LargeMailUserHandler<>();
    private static PremiseHandler<Premise> premiseHandler = new PremiseHandler<>();
    private static DependentLocalityHandler<DependentLocality> dependentLocalityHandler = new DependentLocalityHandler<>();

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

        List<LocalityName> localityName = element.getLocalityName();
        processLocalityName(localityName, node);

        PostBox postBox = element.getPostBox();
        if (postBox != null) {
            ElementNode<PostBox> postBoxNode = NodeFactory.createElementNode(postBox,
                    POST_BOX, node);
            postBoxHandler.addChildsToNode(postBoxNode);
            node.addChild(postBoxNode);
        }

        LargeMailUser largeMailUser = element.getLargeMailUser();
        if (largeMailUser != null) {
            ElementNode<LargeMailUser> userNode = NodeFactory.createElementNode(
                    largeMailUser, LARGE_MAIL_USER, node);
            largeMailUserHandler.addChildsToNode(userNode);
            node.addChild(userNode);
        }

        PostOffice postOffice = element.getPostOffice();
        if (postOffice != null) {
            ElementNode<PostOffice> postOfficeNode = NodeFactory.createElementNode(
                    postOffice, POST_OFFICE, node);
            postOfficeHandler.addChildsToNode(postOfficeNode);
            node.addChild(postOfficeNode);
        }

        Thoroughfare thoroughfare = element.getThoroughfare();
        if (thoroughfare != null) {
            ElementNode<Thoroughfare> thoroughfareNode = NodeFactory
                    .createElementNode(thoroughfare, THOROUGHFARE, node);
            thoroughfareHandler.addChildsToNode(thoroughfareNode);
            node.addChild(thoroughfareNode);
        }

        Premise premise = element.getPremise();
        if (premise != null) {
            ElementNode<Premise> premiseNode = NodeFactory.createElementNode(premise,
                    PREMISE, node);
            premiseHandler.addChildsToNode(premiseNode);
            node.addChild(premiseNode);
        }

        DependentLocality dependentLocality = element.getDependentLocality();
        if (dependentLocality != null) {
            ElementNode<DependentLocality> depLocNode = NodeFactory.createElementNode(dependentLocality, DEPENDENT_LOCALITY, node);
            dependentLocalityHandler.addChildsToNode(depLocNode);
            node.addChild(depLocNode);
        }

        PostalCode postalCode = element.getPostalCode();
        if (postalCode != null) {
            ElementNode<PostalCode> postalCodeNode = NodeFactory.createElementNode(
                    postalCode, POSTAL_CODE, node);
            postalCodeHandler.addChildsToNode(postalCodeNode);
            node.addChild(postalCodeNode);
        }

        PostalRoute postalRoute = element.getPostalRoute();
        if (postalRoute != null) {
            ElementNode<PostalRoute> postalRouteNode = NodeFactory.createElementNode(
                    postalRoute, POSTAL_ROUTE, node);
            postalRouteHandler.addChildsToNode(postalRouteNode);
            node.addChild(postalRouteNode);
        }
    }

    private void processLocalityName(List<LocalityName> names,
                                     ElementNode<T> parent) {
        if (names != null) {
            for (LocalityName localityName : names) {
                ElementNode<LocalityName> localityNameNode = NodeFactory
                        .createElementNode(localityName, LOCALITY_NAME, parent);
                localityNameHandler.addChildsToNode(localityNameNode);
                parent.addChild(localityNameNode);
            }
        }
    }
}
