package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class PostBoxHandler<T extends PostBox> extends AddressLinesProcessor<T> {

    private static PostBoxNumberHandler<PostBoxNumber> postBoxNumberHandler = new PostBoxNumberHandler<>();
    private static PostBoxNumberPrefixHandler<PostBoxNumberPrefix> postBoxNumberPrefixHandler = new PostBoxNumberPrefixHandler<>();
    private static PostBoxNumberSuffixHandler<PostBoxNumberSuffix> postBoxNumberSuffixHandler = new PostBoxNumberSuffixHandler<>();
    private static PostBoxNumberExtensionHandler<PostBoxNumberExtension> postBoxNumberExtensionHandler = new PostBoxNumberExtensionHandler<>();
    private static PostalCodeHandler<PostalCode> postalCodeHandler = new PostalCodeHandler<>();
    private static FirmHandler<Firm> firmHandler = new FirmHandler<>();

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

        PostBoxNumber postBoxNumber = element.getPostBoxNumber();
        if (postBoxNumber != null) {
            ElementNode<PostBoxNumber> postBoxNumberNode = NodeFactory
                    .createElementNode(postBoxNumber, POST_BOX_NUMBER, node);
            postBoxNumberHandler.addChildsToNode(postBoxNumberNode);
            node.addChild(postBoxNumberNode);
        }

        PostBoxNumberPrefix postBoxNumberPrefix = element
                .getPostBoxNumberPrefix();
        if (postBoxNumberPrefix != null) {
            ElementNode<PostBoxNumberPrefix> prefixNode = NodeFactory
                    .createElementNode(postBoxNumberPrefix,
                            POST_BOX_NUMBER_PREFIX, node);
            postBoxNumberPrefixHandler.addChildsToNode(prefixNode);
            node.addChild(prefixNode);
        }

        PostBoxNumberSuffix postBoxNumberSuffix = element
                .getPostBoxNumberSuffix();
        if (postBoxNumberSuffix != null) {
            ElementNode<PostBoxNumberSuffix> suffixNode = NodeFactory
                    .createElementNode(postBoxNumberSuffix,
                            POST_BOX_NUMBER_SUFFIX, node);
            postBoxNumberSuffixHandler.addChildsToNode(suffixNode);
            node.addChild(suffixNode);
        }

        PostBoxNumberExtension postBoxNumberExtension = element
                .getPostBoxNumberExtension();
        if (postBoxNumberExtension != null) {
            ElementNode<PostBoxNumberExtension> extensionNode = NodeFactory
                    .createElementNode(postBoxNumberExtension,
                            POST_BOX_NUMBER_EXTENSION, node);
            postBoxNumberExtensionHandler.addChildsToNode(extensionNode);
            node.addChild(extensionNode);
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
            ElementNode<PostalCode> postalCodeNode = NodeFactory.createElementNode(
                    postalCode, POSTAL_CODE, node);
            postalCodeHandler.addChildsToNode(postalCodeNode);
            node.addChild(postalCodeNode);
        }
    }

}
