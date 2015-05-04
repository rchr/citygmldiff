package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class FirmHandler<T extends Firm> extends AddressLinesProcessor<T> {

    private static FirmNameHandler<FirmName> firmNameHandler = new FirmNameHandler<>();
    private static DepartmentHandler<Department> departmentHandler = new DepartmentHandler<>();
    private static PostalCodeHandler<PostalCode> postalCodeHandler = new PostalCodeHandler<>();
    private static MailStopHandler<MailStop> mailStopHandler = new MailStopHandler<>();

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

        List<AddressLine> addressLines = element.getAddressLine();
        processAddressLines(addressLines, node);

        List<FirmName> firmNames = element.getFirmName();
        processFirmNames(firmNames, node);

        List<Department> departments = element.getDepartment();
        processDepartments(departments, node);

        PostalCode postalCode = element.getPostalCode();
        if (postalCode != null) {
            ElementNode<PostalCode> postalCodeNode = NodeFactory.createElementNode(
                    postalCode, POSTAL_CODE, node);
            postalCodeHandler.addChildsToNode(postalCodeNode);
            node.addChild(postalCodeNode);
        }

        MailStop mailStop = element.getMailStop();
        if (mailStop != null) {
            ElementNode<MailStop> mailStopNode = NodeFactory.createElementNode(
                    mailStop, MAIL_STOP, node);
            mailStopHandler.addChildsToNode(mailStopNode);
            node.addChild(mailStopNode);
        }
    }

    private void processDepartments(List<Department> departments,
                                    ElementNode<T> node) {
        if (departments != null) {
            for (Department department : departments) {
                ElementNode<Department> departmentNode = NodeFactory
                        .createElementNode(department, DEPARTMENT, node);
                departmentHandler.addChildsToNode(departmentNode);
                node.addChild(departmentNode);
            }
        }
    }

    private void processFirmNames(List<FirmName> firmNames, ElementNode<T> node) {
        if (firmNames != null) {
            for (FirmName firmName : firmNames) {
                ElementNode<FirmName> firmNameNode = NodeFactory.createElementNode(
                        firmName, FIRM_NAME, node);
                firmNameHandler.addChildsToNode(firmNameNode);
                node.addChild(firmNameNode);
            }
        }
    }

}
