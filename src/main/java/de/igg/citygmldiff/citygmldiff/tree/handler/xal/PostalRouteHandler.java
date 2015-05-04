package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class PostalRouteHandler<T extends PostalRoute> extends AddressLinesProcessor<T> {

    private static PostalRouteNameHandler<PostalRouteName> postalRouteNameHandler = new PostalRouteNameHandler<>();
    private static PostalRouteNumberHandler<PostalRouteNumber> postalRouteNumberHandler = new PostalRouteNumberHandler<>();
    private static PostBoxHandler<PostBox> postBoxHandler = new PostBoxHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        // attribute
        String type = element.getType();
        if (type != null) {
            AttributeNode typeNode = NodeFactory
                    .createAttributeNode(TYPE, type, node);
            node.addChild(typeNode);
        }

        // elements
        List<AddressLine> addressLines = element.getAddressLine();
        processAddressLines(addressLines, node);

        List<PostalRouteName> postalRouteNames = element.getPostalRouteName();
        processPostalRouteNames(postalRouteNames, node);

        PostalRouteNumber postalRouteNumber = element.getPostalRouteNumber();
        if (postalRouteNumber != null) {
            ElementNode<PostalRouteNumber> postalRouteNumberNode = NodeFactory
                    .createElementNode(postalRouteNumber, POSTAL_ROUTE_NUMBER,
                            node);
            postalRouteNumberHandler.addChildsToNode(postalRouteNumberNode);
            node.addChild(postalRouteNumberNode);
        }

        PostBox postBox = element.getPostBox();
        if (postBox != null) {
            ElementNode<PostBox> postBoxNode = NodeFactory.createElementNode(postBox,
                    POST_BOX, node);
            postBoxHandler.addChildsToNode(postBoxNode);
            node.addChild(postBoxNode);
        }
    }

    private void processPostalRouteNames(
            List<PostalRouteName> postalRouteNames, ElementNode<T> node) {
        if (postalRouteNames != null) {
            for (PostalRouteName postalRouteName : postalRouteNames) {
                ElementNode<PostalRouteName> postalRouteNameNode = NodeFactory
                        .createElementNode(postalRouteName, POSTAL_ROUTE_NAME,
                                node);
                postalRouteNameHandler.addChildsToNode(postalRouteNameNode);
                node.addChild(postalRouteNameNode);
            }
        }
    }

}
