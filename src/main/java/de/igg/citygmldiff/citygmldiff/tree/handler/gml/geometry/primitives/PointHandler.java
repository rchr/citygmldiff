package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.basicTypes.CoordinatesHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.basicTypes.Coordinates;
import org.citygml4j.model.gml.geometry.primitives.Coord;
import org.citygml4j.model.gml.geometry.primitives.DirectPosition;
import org.citygml4j.model.gml.geometry.primitives.Point;

public class PointHandler<T extends Point> extends
        AbstractGeometricPrimitiveHandler<T> {

    private static DirectPositionHandler<DirectPosition> directPositionHandler = new DirectPositionHandler<>();
    private static CoordinatesHandler<Coordinates> coordinatesHandler = new CoordinatesHandler<>();
    private static CoordHandler<Coord> coordHandler = new CoordHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        if (element.isSetCoord()) {
            Coord coord = element.getCoord();
            ElementNode<Coord> coordNode = NodeFactory.createElementNode(coord,
                    "coord", node);
            coordHandler.addChildsToNode(coordNode);
            node.addChild(coordNode);
        }
        if (element.isSetPos()) {
            DirectPosition pos = element.getPos();
            ElementNode<DirectPosition> posNode = NodeFactory.createElementNode(pos,
                    "pos", node);
            directPositionHandler.addChildsToNode(posNode);
            node.addChild(posNode);
        }
        if (element.isSetCoordinates()) {
            Coordinates coordinates = element.getCoordinates();
            ElementNode<Coordinates> coordNode = NodeFactory.createElementNode(
                    coordinates, "coordinates", node);
            coordinatesHandler.addChildsToNode(coordNode);
            node.addChild(coordNode);
        }

    }

}
