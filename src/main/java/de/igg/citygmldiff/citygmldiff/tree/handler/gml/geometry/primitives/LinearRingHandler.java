package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.apache.log4j.Logger;
import org.citygml4j.model.gml.geometry.primitives.*;

import java.util.List;

/**
 * User: richard Date: 07.08.13 Time: 12:58
 */
public class LinearRingHandler<T extends LinearRing> extends AbstractRingHandler<T> {

    private static final Logger LOGGER = Logger.getLogger(LinearRingHandler.class);

    private static DirectPositionListHandler<DirectPositionList> directPositionListHandler = new DirectPositionListHandler<>();
    private static DirectPositionHandler<DirectPosition> directPositionHandler = new DirectPositionHandler<>();
    private static PointPropertyHandler<PointProperty> pointPropertyHandler = new PointPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        DirectPositionList posList = element.getPosList();
        if (posList != null) {
            processDirectPositionList(posList, node);
        }

        if (posList == null) {
            List<PosOrPointPropertyOrPointRep> posOrPointPropertyOrPointRep = element.getPosOrPointPropertyOrPointRep();
            processPosOrPointPropertyOrPointRepList(posOrPointPropertyOrPointRep, node);
        }
    }

    private void processPosOrPointPropertyOrPointRepList(List<PosOrPointPropertyOrPointRep> posOrPointPropertyOrPointRepList, ElementNode<T> node) {
        if (posOrPointPropertyOrPointRepList != null) {
            for (PosOrPointPropertyOrPointRep posOrPointPropertyOrPointRep : posOrPointPropertyOrPointRepList) {
                posOrPointPropertyOrPointRep(posOrPointPropertyOrPointRep, node);
            }
        }
    }

    private void posOrPointPropertyOrPointRep(PosOrPointPropertyOrPointRep posOrPointPropertyOrPointRep, ElementNode<T> node) {
        DirectPosition pos = posOrPointPropertyOrPointRep.getPos();
        if (pos != null) {
            ElementNode<DirectPosition> directPositionElementNode = NodeFactory.createElementNode(pos, POS, node);
            directPositionHandler.addChildsToNode(directPositionElementNode);
            node.addChild(directPositionElementNode);
        }

        PointRep pointRep = posOrPointPropertyOrPointRep.getPointRep();
        if (pointRep != null) {
            throw new RuntimeException("PointRep currently not supported.");
        }

        PointProperty pointProperty = posOrPointPropertyOrPointRep.getPointProperty();
        if (pointProperty != null) {
            ElementNode<PointProperty> pointPropertyElementNode = NodeFactory.createElementNode(pointProperty, POS, node);
            pointPropertyHandler.addChildsToNode(pointPropertyElementNode);
            node.addChild(pointPropertyElementNode);
        }
    }

    private void processDirectPositionList(DirectPositionList posList, ElementNode<T> node) {
        String name = Constants.POS_LIST;
        String signature = Utils.buildSignature(node, posList, name);
        ElementNode<DirectPositionList> posListNode = new ElementNode<>(
                posList, name, signature, node);
        directPositionListHandler.addChildsToNode(posListNode);
        node.addChild(posListNode);
    }
}
