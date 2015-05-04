package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core.AddressPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.building.Door;
import org.citygml4j.model.citygml.core.AddressProperty;

import java.util.List;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 12:14
 */
public class DoorHandler<T extends Door> extends AbstractOpeningHandler<T> implements Constants {

    private static AddressPropertyHandler<AddressProperty> addressPropertyHandler = new AddressPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<AddressProperty> addresses = element.getAddress();
        if (addresses != null) {
            processAddresses(addresses, node);
        }

        List<ADEComponent> genericApplicationPropertyOfDoor = element.getGenericApplicationPropertyOfDoor();

    }

    private void processAddresses(List<AddressProperty> addresses, ElementNode<T> node) {
        for (AddressProperty address : addresses) {
            ElementNode<AddressProperty> addressPropertyElementNode = NodeFactory.createElementNode(address, ADDRESS_PROPERTY, node);
            addressPropertyHandler.addChildsToNode(addressPropertyElementNode);
            node.addChild(addressPropertyElementNode);
        }
    }
}
