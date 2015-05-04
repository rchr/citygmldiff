package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class PostOfficeHandler<T extends PostOffice> extends AddressLinesProcessor<T> {

    private static PostOfficeNameHandler<PostOfficeName> postOfficeNameHandler = new PostOfficeNameHandler<>();
    private static PostOfficeNumberHandler<PostOfficeNumber> postOfficeNumberHandler = new PostOfficeNumberHandler<>();
    private static PostalRouteHandler<PostalRoute> postalRouteHandler = new PostalRouteHandler<>();
    private static PostBoxHandler<PostBox> postBoxHandler = new PostBoxHandler<>();
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

        String indicator = element.getIndicator();
        if (indicator != null) {
            AttributeNode indicatorNode = NodeFactory.createAttributeNode(INDICATOR,
                    indicator, node);
            node.addChild(indicatorNode);
        }

        // elements
        List<AddressLine> addressLines = element.getAddressLine();
        processAddressLines(addressLines, node);

        List<PostOfficeName> postOfficeNames = element.getPostOfficeName();
        processPostOfficeNames(postOfficeNames, node);

        PostOfficeNumber postOfficeNumber = element.getPostOfficeNumber();
        if (postOfficeNumber != null) {
            ElementNode<PostOfficeNumber> postOfficeNumberNode = NodeFactory
                    .createElementNode(postOfficeNumber, POST_OFFICE_NUMBER,
                            node);
            postOfficeNumberHandler.addChildsToNode(postOfficeNumberNode);
            node.addChild(postOfficeNumberNode);
        }

        PostalRoute postalRoute = element.getPostalRoute();
        if (postalRoute != null) {
            ElementNode<PostalRoute> postalRouteNode = NodeFactory.createElementNode(
                    postalRoute, POSTAL_ROUTE, node);
            postalRouteHandler.addChildsToNode(postalRouteNode);
            node.addChild(postalRouteNode);
        }

        PostBox postBox = element.getPostBox();
        if (postBox != null) {
            ElementNode<PostBox> postBoxNode = NodeFactory.createElementNode(postBox,
                    POST_BOX, node);
            postBoxHandler.addChildsToNode(postBoxNode);
            node.addChild(postBoxNode);
        }

        PostalCode postalCode = element.getPostalCode();
        if (postalCode != null) {
            ElementNode<PostalCode> postalCodeNode = NodeFactory.createElementNode(
                    postalCode, POSTAL_CODE, node);
            postalCodeHandler.addChildsToNode(postalCodeNode);
            node.addChild(postalCodeNode);
        }

    }

    private void processPostOfficeNames(List<PostOfficeName> postOfficeNames,
                                        ElementNode<T> node) {
        if (postOfficeNames != null) {
            for (PostOfficeName postOfficeName : postOfficeNames) {
                ElementNode<PostOfficeName> postOfficeNameNode = NodeFactory
                        .createElementNode(postOfficeName, POST_OFFICE_NAME,
                                node);
                postOfficeNameHandler.addChildsToNode(postOfficeNameNode);
                node.addChild(postOfficeNameNode);
            }
        }
    }

}
