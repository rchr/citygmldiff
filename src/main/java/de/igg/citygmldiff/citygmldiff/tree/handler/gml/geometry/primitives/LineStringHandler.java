package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.basicTypes.CoordinatesHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.basicTypes.Coordinates;
import org.citygml4j.model.gml.geometry.primitives.*;

import java.util.List;

public class LineStringHandler<T extends LineString> extends
        AbstractCurveHandler<T> {

    private static DirectPositionListHandler<DirectPositionList> directPositionListHandler = new DirectPositionListHandler<>();
    private static CoordinatesHandler<Coordinates> coordinatesHandler = new CoordinatesHandler<>();
    private static DirectPositionHandler<DirectPosition> directPositionHandler = new DirectPositionHandler<>();
    private static PointPropertyHandler<PointProperty> pointPropertyHandler = new PointPropertyHandler<>();
    private static PointPropertyHandler<PointRep> pointRepHandler = new PointPropertyHandler<>();
    private static CoordHandler<Coord> coordHandler = new CoordHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<PosOrPointPropertyOrPointRepOrCoord> posOrPointPropertyOrPointRepOrCoords = element
                .getPosOrPointPropertyOrPointRepOrCoord();
        processPosOrPointPropertyOrPointRepOrCoords(
                posOrPointPropertyOrPointRepOrCoords, node);

        DirectPositionList posList = element.getPosList();
        if (posList != null) {
            ElementNode<DirectPositionList> posListNode = NodeFactory
                    .createElementNode(posList, Constants.POS_LIST, node);
            directPositionListHandler.addChildsToNode(posListNode);
            node.addChild(posListNode);
        }

        Coordinates coordinates = element.getCoordinates();
        if (coordinates != null) {
            ElementNode<Coordinates> coordNode = NodeFactory.createElementNode(
                    coordinates, Constants.COORDINATES, node);
            coordinatesHandler.addChildsToNode(coordNode);
            node.addChild(coordNode);
        }
    }

    private void processPosOrPointPropertyOrPointRepOrCoords(
            List<PosOrPointPropertyOrPointRepOrCoord> posOrPointPropertyOrPointRepOrCoords,
            ElementNode<T> node) {
        if (posOrPointPropertyOrPointRepOrCoords == null) {
            return;
        }

        for (PosOrPointPropertyOrPointRepOrCoord entry : posOrPointPropertyOrPointRepOrCoords) {
            if (entry.isSetCoord()) {
                Coord coord = entry.getCoord();
                ElementNode<Coord> coordNode = NodeFactory.createElementNode(coord,
                        Constants.COORD, node);
                coordHandler.addChildsToNode(coordNode);
                node.addChild(coordNode);
            }
            if (entry.isSetPos()) {
                DirectPosition pos = entry.getPos();
                ElementNode<DirectPosition> posNode = NodeFactory.createElementNode(
                        pos, Constants.POS, node);
                directPositionHandler.addChildsToNode(posNode);
                node.addChild(posNode);
            }
            if (entry.isSetPointProperty()) {
                PointProperty pointProp = entry.getPointProperty();
                ElementNode<PointProperty> porpertyNode = NodeFactory
                        .createElementNode(pointProp, Constants.POINT_PROPERTY, node);
                pointPropertyHandler.addChildsToNode(porpertyNode);
                node.addChild(porpertyNode);
            }
            if (entry.isSetPointRep()) {
                PointRep pointRep = entry.getPointRep();
                ElementNode<PointRep> repNode = NodeFactory.createElementNode(
                        pointRep, Constants.POINT_REP, node);
                pointRepHandler.addChildsToNode(repNode);
                node.addChild(repNode);
            }
        }
    }

}
