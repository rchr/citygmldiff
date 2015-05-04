package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.building.InteriorRoomProperty;
import org.citygml4j.model.citygml.building.Room;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 17:01
 */
public class InteriorRoomPropertyHandler<T extends InteriorRoomProperty> implements TreeNodeHandler<T>, Constants {

    private static RoomHandler<Room> roomHandler = new RoomHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        Room room = element.getRoom();
        if (room == null) {
            Utils.addXlinkFeature(node);
        } else if (room != null) {
            ElementNode<Room> elementNode = NodeFactory.createElementNode(room, ROOM, node);
            roomHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }
    }
}
