package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.AddressLine;
import org.citygml4j.model.xal.MailStop;
import org.citygml4j.model.xal.MailStopName;
import org.citygml4j.model.xal.MailStopNumber;

import java.util.List;

public class MailStopHandler<T extends MailStop> extends AddressLinesProcessor<T> {

    private static MailStopNameHandler<MailStopName> mailStopNameHandler = new MailStopNameHandler<>();
    private static MailStopNumberHandler<MailStopNumber> mailStopNumberHandler = new MailStopNumberHandler<>();

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

        MailStopName mailStopName = element.getMailStopName();
        if (mailStopName != null) {
            ElementNode<MailStopName> mailStopNameNode = NodeFactory
                    .createElementNode(mailStopName, MAIL_STOP_NAME, node);
            mailStopNameHandler.addChildsToNode(mailStopNameNode);
            node.addChild(mailStopNameNode);
        }

        MailStopNumber mailStopNumber = element.getMailStopNumber();
        if (mailStopNumber != null) {
            ElementNode<MailStopNumber> mailStopNumberNode = NodeFactory
                    .createElementNode(mailStopNumber, MAIL_STOP_NUMBER, node);
            mailStopNumberHandler.addChildsToNode(mailStopNumberNode);
            node.addChild(mailStopNumberNode);
        }
    }

}
