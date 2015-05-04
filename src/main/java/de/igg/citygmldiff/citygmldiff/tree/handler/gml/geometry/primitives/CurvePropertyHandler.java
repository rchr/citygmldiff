package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes.CompositeCurveHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.complexes.CompositeCurve;
import org.citygml4j.model.gml.geometry.primitives.*;

public class CurvePropertyHandler<T extends CurveProperty> implements
        TreeNodeHandler<T> {

    private static final String ABSTRACT_CURVE = "AbstractCurve";
    private static LineStringHandler<LineString> lineStringHandler = new LineStringHandler<>();
    private static CurveHandler<Curve> curveHandler = new CurveHandler<>();
    private static CompositeCurveHandler<CompositeCurve> compositeCurveHandler = new CompositeCurveHandler<>();
    private static OrientableCurveHandler<OrientableCurve> orientableCurveHandler = new OrientableCurveHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        AbstractCurve abstractCurve = element.getCurve();
        if (abstractCurve == null) {
            Utils.addXlinkFeature(node);
        } else if (abstractCurve instanceof LineString) {
            LineString lineString = (LineString) abstractCurve;
            ElementNode<LineString> lineNode = NodeFactory.createElementNode(
                    lineString, ABSTRACT_CURVE, node);
            lineStringHandler.addChildsToNode(lineNode);
            node.addChild(lineNode);
        } else if (abstractCurve instanceof Curve) {
            Curve curve = (Curve) abstractCurve;
            ElementNode<Curve> curveNode = NodeFactory.createElementNode(curve,
                    ABSTRACT_CURVE, node);
            curveHandler.addChildsToNode(curveNode);
            node.addChild(curveNode);
        } else if (abstractCurve instanceof CompositeCurve) {
            CompositeCurve curve = (CompositeCurve) abstractCurve;
            ElementNode<CompositeCurve> curveNode = NodeFactory.createElementNode(curve,
                    ABSTRACT_CURVE, node);
            compositeCurveHandler.addChildsToNode(curveNode);
            node.addChild(curveNode);
        } else if (abstractCurve instanceof OrientableCurve) {
            OrientableCurve orientableCurve = (OrientableCurve) abstractCurve;
            ElementNode<OrientableCurve> orientableCurveElementNode = NodeFactory.createElementNode(orientableCurve, "OrientableCurve", node);
            orientableCurveHandler.addChildsToNode(orientableCurveElementNode);
            node.addChild(orientableCurveElementNode);
        }

    }

}
