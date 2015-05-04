package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.building.AbstractOpening;
import org.citygml4j.model.citygml.building.Door;
import org.citygml4j.model.citygml.building.OpeningProperty;
import org.citygml4j.model.citygml.building.Window;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 12:13
 */
public class OpeningPropertyHandler<T extends OpeningProperty> implements TreeNodeHandler<T>, Constants {

    private static WindowHandler<Window> windowHandler = new WindowHandler<>();
    private static DoorHandler<Door> doorHandler = new DoorHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        AbstractOpening opening = element.getOpening();
        if (opening == null) {
            Utils.addXlinkFeature(node);
        } else if (opening != null) {
            processOpening(opening, node);
        }
    }

    private void processOpening(AbstractOpening abstractOpening, ElementNode<T> node) {
        if (abstractOpening instanceof Window) {
            Window window = (Window) abstractOpening;
            ElementNode<Window> windowElementNode = NodeFactory.createElementNode(window, WINDOW, node);
            windowHandler.addChildsToNode(windowElementNode);
            node.addChild(windowElementNode);
        } else if (abstractOpening instanceof Door) {
            Door door = (Door) abstractOpening;
            ElementNode<Door> doorElementNode = NodeFactory.createElementNode(door, DOOR, node);
            doorHandler.addChildsToNode(doorElementNode);
            node.addChild(doorElementNode);
        }
    }
}
