package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.AddressLine;
import org.citygml4j.model.xal.PostTown;
import org.citygml4j.model.xal.PostTownName;
import org.citygml4j.model.xal.PostTownSuffix;

import java.util.List;

public class PostTownHandler<T extends PostTown> extends AddressLinesProcessor<T> {

    private static PostTownNameHandler<PostTownName> postTownNameHandler = new PostTownNameHandler<>();
    private static PostTownSuffixHandler<PostTownSuffix> postTownSuffixHandler = new PostTownSuffixHandler<>();

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

        List<PostTownName> postTownNames = element.getPostTownName();
        processPostTownNames(postTownNames, node);

        PostTownSuffix postTownSuffix = element.getPostTownSuffix();
        if (postTownSuffix != null) {
            ElementNode<PostTownSuffix> suffixNode = NodeFactory.createElementNode(
                    postTownSuffix, POST_TOWN_SUFFIX, node);
            postTownSuffixHandler.addChildsToNode(suffixNode);
            node.addChild(suffixNode);
        }
    }

    private void processPostTownNames(List<PostTownName> postTownNames,
                                      ElementNode<T> node) {
        if (postTownNames != null) {
            for (PostTownName postTownName : postTownNames) {
                ElementNode<PostTownName> nameNode = NodeFactory.createElementNode(
                        postTownName, POST_TOWN_NAME, node);
                postTownNameHandler.addChildsToNode(nameNode);
                node.addChild(nameNode);
            }
        }
    }

}
