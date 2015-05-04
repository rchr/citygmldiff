package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.AddressLine;
import org.citygml4j.model.xal.AddressLines;

import java.util.List;

public class AddressLinesHandler<T extends AddressLines> implements
        TreeNodeHandler<T>, XalConstants {

    private AddressLineHandler<AddressLine> addressLineHandler = new AddressLineHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        List<AddressLine> addressLines = element.getAddressLine();
        if (addressLines != null) {
            for (AddressLine addressLine : addressLines) {
                ElementNode<AddressLine> addressLineNode = NodeFactory
                        .createElementNode(addressLine, ADDRESS_LINE, node);
                addressLineHandler.addChildsToNode(addressLineNode);
                node.addChild(addressLineNode);
            }
        }

    }

}
