package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.core.ExternalObject;
import org.citygml4j.model.citygml.core.ExternalReference;

/**
 * User: richard Date: 08.08.13 Time: 15:30
 */
public class ExternalReferenceHandler<T extends ExternalReference> implements
        TreeNodeHandler<T>, Constants {

    private static ExternalObjectHandler<ExternalObject> externalObjectHandler = new ExternalObjectHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        String informationSystem = element.getInformationSystem();
        String signature = Utils.buildSignature(node, informationSystem, INFORMATION_SYSTEM);
        ElementNode<String> informationSystemElementNode = new ElementNode<>(
                informationSystem, INFORMATION_SYSTEM, signature, node);
        TextNode informationSystemNode = new TextNode(informationSystem,
                informationSystemElementNode);
        informationSystemElementNode.addChild(informationSystemNode);
        node.addChild(informationSystemElementNode);

        ExternalObject externalObject = element.getExternalObject();
        String name = EXTERNAL_OBJECT;
        String externalObjSignature = Utils.buildSignature(node, externalObject, name);
        ElementNode<ExternalObject> externalObjectElementNode = new ElementNode<>(
                externalObject, name, externalObjSignature, node);
        externalObjectHandler.addChildsToNode(externalObjectElementNode);
        node.addChild(externalObjectElementNode);

    }
}
