package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class PostalCodeHandler<T extends PostalCode> extends AddressLinesProcessor<T> {

    private static PostTownHandler<PostTown> postTownHandler = new PostTownHandler<>();
    private static PostalCodeNumberHandler<PostalCodeNumber> postalCodeNumberHandler = new PostalCodeNumberHandler<>();
    private static PostalCodeNumberExtensionHandler<PostalCodeNumberExtension> postalCodeNumberExtensionHandler = new PostalCodeNumberExtensionHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        String type = element.getType();
        if (type != null) {
            AttributeNode typeNode = NodeFactory
                    .createAttributeNode(TYPE, type, node);
            node.addChild(typeNode);
        }

        List<AddressLine> addressLines = element.getAddressLine();
        processAddressLines(addressLines, node);

        PostTown postTown = element.getPostTown();
        if (postTown != null) {
            ElementNode<PostTown> postTownNode = NodeFactory.createElementNode(
                    postTown, POST_TOWN, node);
            postTownHandler.addChildsToNode(postTownNode);
            node.addChild(postTownNode);
        }

        List<PostalCodeNumber> postalCodeNumbers = element
                .getPostalCodeNumber();
        processPostalCodeNumbers(postalCodeNumbers, node);

        List<PostalCodeNumberExtension> postalCodeNumberExtensions = element
                .getPostalCodeNumberExtension();
        processPostalCodeNumberExtensions(postalCodeNumberExtensions, node);
    }

    private void processPostalCodeNumberExtensions(
            List<PostalCodeNumberExtension> postalCodeNumberExtensions,
            ElementNode<T> node) {
        if (postalCodeNumberExtensions != null) {
            for (PostalCodeNumberExtension postalCodeNumberExtension : postalCodeNumberExtensions) {
                ElementNode<PostalCodeNumberExtension> extensionNode = NodeFactory
                        .createElementNode(postalCodeNumberExtension,
                                POSTAL_CODE_NUMBER_EXTENSION, node);
                postalCodeNumberExtensionHandler.addChildsToNode(extensionNode);
                node.addChild(extensionNode);
            }
        }
    }

    private void processPostalCodeNumbers(
            List<PostalCodeNumber> postalCodeNumbers, ElementNode<T> node) {
        if (postalCodeNumbers != null) {
            for (PostalCodeNumber postalCodeNumber : postalCodeNumbers) {
                ElementNode<PostalCodeNumber> numberNode = NodeFactory
                        .createElementNode(postalCodeNumber,
                                POSTAL_CODE_NUMBER, node);
                postalCodeNumberHandler.addChildsToNode(numberNode);
                node.addChild(numberNode);
            }
        }
    }

}
