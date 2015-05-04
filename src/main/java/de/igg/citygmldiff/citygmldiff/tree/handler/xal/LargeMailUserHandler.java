package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class LargeMailUserHandler<T extends LargeMailUser> extends AddressLinesProcessor<T> {

    private static LargeMailUserNameHandler<LargeMailUserName> largeMailUserNameHandler = new LargeMailUserNameHandler<>();
    private static LargeMailUserIdentifierHandler<LargeMailUserIdentifier> largeMailUserIdentifierHandler = new LargeMailUserIdentifierHandler<>();
    private static DepartmentHandler<Department> departmentHandler = new DepartmentHandler<>();
    private static BuildingNameHandler<BuildingName> buildingNameHandler = new BuildingNameHandler<>();
    private static PostBoxHandler<PostBox> postBoxHandler = new PostBoxHandler<>();
    private static ThoroughfareHandler<Thoroughfare> thoroughfareHandler = new ThoroughfareHandler<>();
    private static PostalCodeHandler<PostalCode> postalCodeHandler = new PostalCodeHandler<>();

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

        List<LargeMailUserName> largeMailUserNames = element
                .getLargeMailUserName();
        processLargeMailUserNames(largeMailUserNames, node);

        LargeMailUserIdentifier largeMailUserIdentifier = element
                .getLargeMailUserIdentifier();
        if (largeMailUserIdentifier != null) {
            ElementNode<LargeMailUserIdentifier> identifierNode = NodeFactory
                    .createElementNode(largeMailUserIdentifier,
                            LARGE_MAIL_USER_IDENTIFIER, node);
            largeMailUserIdentifierHandler.addChildsToNode(identifierNode);
            node.addChild(identifierNode);
        }

        Department department = element.getDepartment();
        if (department != null) {
            ElementNode<Department> departmentNode = NodeFactory.createElementNode(
                    department, DEPARTMENT, node);
            departmentHandler.addChildsToNode(departmentNode);
            node.addChild(departmentNode);
        }

        List<BuildingName> buildingNames = element.getBuildingName();
        processBuildingNames(buildingNames, node);

        PostBox postBox = element.getPostBox();
        if (postBox != null) {
            ElementNode<PostBox> postBoxNode = NodeFactory.createElementNode(postBox,
                    POST_BOX, node);
            postBoxHandler.addChildsToNode(postBoxNode);
            node.addChild(postBoxNode);
        }

        Thoroughfare thoroughfare = element.getThoroughfare();
        if (thoroughfare != null) {
            ElementNode<Thoroughfare> thoroughfareNode = NodeFactory
                    .createElementNode(thoroughfare, THOROUGHFARE, node);
            thoroughfareHandler.addChildsToNode(thoroughfareNode);
            node.addChild(thoroughfareNode);
        }

        PostalCode postalCode = element.getPostalCode();
        if (postalCode != null) {
            ElementNode<PostalCode> codeNode = NodeFactory.createElementNode(
                    postalCode, POSTAL_CODE, node);
            postalCodeHandler.addChildsToNode(codeNode);
            node.addChild(codeNode);
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

    private void processLargeMailUserNames(
            List<LargeMailUserName> largeMailUserNames, ElementNode<T> node) {
        if (largeMailUserNames != null) {
            for (LargeMailUserName largeMailUserName : largeMailUserNames) {
                ElementNode<LargeMailUserName> userNameNode = NodeFactory
                        .createElementNode(largeMailUserName,
                                LARGE_MAIL_USER_NAME, node);
                largeMailUserNameHandler.addChildsToNode(userNameNode);
                node.addChild(userNameNode);
            }
        }
    }

}
