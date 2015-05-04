package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.basicTypes.CoordinatesHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.basicTypes.Coordinates;
import org.citygml4j.model.gml.geometry.primitives.*;

import java.util.List;

public class LineStringSegmentHandler<T extends LineStringSegment> extends
        AbstractCurveSegmentHandler<T> {

    private static final String POINT_REP = "pointRep";
    private static final String POINT_PROPERTY = "pointProperty";
    private static final String POS = "pos";

    private static CoordinatesHandler<Coordinates> coordinatesHandler = new CoordinatesHandler<>();
    private static DirectPositionListHandler<DirectPositionList> directPositionListHandler = new DirectPositionListHandler<>();
    private static DirectPositionHandler<DirectPosition> directPositionHandler = new DirectPositionHandler<>();
    private static PointPropertyHandler<PointProperty> pointPropertyHandler = new PointPropertyHandler<>();
    private static PointPropertyHandler<PointRep> pointRepHandler = new PointPropertyHandler<>();
    private static CurveInterpolationHandler<Enum<CurveInterpolation>> curveInterpolationHandler = new CurveInterpolationHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        Coordinates coordinates = element.getCoordinates();
        if (coordinates != null) {
            ElementNode<Coordinates> coordNode = NodeFactory.createElementNode(
                    coordinates, "coordinates", node);
            coordinatesHandler.addChildsToNode(coordNode);
            node.addChild(coordNode);
        }

        DirectPositionList posList = element.getPosList();
        if (posList != null) {
            ElementNode<DirectPositionList> posListNode = NodeFactory
                    .createElementNode(posList, Constants.POS_LIST, node);
            directPositionListHandler.addChildsToNode(posListNode);
            node.addChild(posListNode);
        }

        List<PosOrPointPropertyOrPointRep> posOrPointPropertyOrPointRep = element
                .getPosOrPointPropertyOrPointRep();
        processPosOrPointPropertyOrPointRep(posOrPointPropertyOrPointRep, node);

        Enum<CurveInterpolation> interpolation = element.getInterpolation();
        if (interpolation != null) {
            ElementNode<Enum<CurveInterpolation>> interNode = NodeFactory
                    .createElementNode(interpolation, "interpolation", node);
            curveInterpolationHandler.addChildsToNode(interNode);
            node.addChild(interNode);
        }

    }

    private void processPosOrPointPropertyOrPointRep(
            List<PosOrPointPropertyOrPointRep> posOrPointPropertyOrPointRep,
            ElementNode<T> node) {
        if (posOrPointPropertyOrPointRep == null) {
            return;
        }

        for (PosOrPointPropertyOrPointRep entry : posOrPointPropertyOrPointRep) {
            if (entry.isSetPos()) {
                DirectPosition pos = entry.getPos();
                ElementNode<DirectPosition> posNode = NodeFactory.createElementNode(
                        pos, POS, node);
                directPositionHandler.addChildsToNode(posNode);
                node.addChild(posNode);
            }
            if (entry.isSetPointProperty()) {
                PointProperty pointProp = entry.getPointProperty();
                ElementNode<PointProperty> porpertyNode = NodeFactory
                        .createElementNode(pointProp, POINT_PROPERTY, node);
                pointPropertyHandler.addChildsToNode(porpertyNode);
                node.addChild(porpertyNode);
            }
            if (entry.isSetPointRep()) {
                PointRep pointRep = entry.getPointRep();
                ElementNode<PointRep> repNode = NodeFactory.createElementNode(
                        pointRep, POINT_REP, node);
                pointRepHandler.addChildsToNode(repNode);
                node.addChild(repNode);
            }
        }
    }

}
