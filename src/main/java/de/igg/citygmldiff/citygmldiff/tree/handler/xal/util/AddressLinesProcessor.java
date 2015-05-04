package de.igg.citygmldiff.citygmldiff.tree.handler.xal.util;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.xal.AddressLineHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.xal.XalConstants;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.AddressLine;

import java.util.List;

public abstract class AddressLinesProcessor<T> implements
        TreeNodeHandler<T>, XalConstants {

    private static AddressLineHandler<AddressLine> addressLineHandler = new AddressLineHandler<>();

    public void processAddressLines(List<AddressLine> addressLines,
                                    ElementNode<T> parent) {
        if (addressLines != null) {
            for (AddressLine addrLine : addressLines) {
                ElementNode<AddressLine> addressLineNode = NodeFactory
                        .createElementNode(addrLine, ADDRESS_LINE, parent);
                addressLineHandler.addChildsToNode(addressLineNode);
                parent.addChild(addressLineNode);
            }
        }
    }

}
