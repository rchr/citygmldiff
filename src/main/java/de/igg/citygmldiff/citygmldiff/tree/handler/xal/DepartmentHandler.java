package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class DepartmentHandler<T extends Department> extends AddressLinesProcessor<T> {

    private static DepartmentNameHandler<DepartmentName> departmentNameHandler = new DepartmentNameHandler<>();
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

        // elements
        List<AddressLine> addressLines = element.getAddressLine();
        processAddressLines(addressLines, node);

        List<DepartmentName> departmentNames = element.getDepartmentName();
        processDepartmentNames(departmentNames, node);

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

    private void processDepartmentNames(List<DepartmentName> departmentNames,
                                        ElementNode<T> node) {
        if (departmentNames != null) {
            for (DepartmentName departmentName : departmentNames) {
                ElementNode<DepartmentName> departmentNameNode = NodeFactory
                        .createElementNode(departmentName, DEPARTMENT_NAME,
                                node);
                departmentNameHandler.addChildsToNode(departmentNameNode);
                node.addChild(departmentNameNode);
            }
        }
    }

}
