package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes.CompositeCurveHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.complexes.CompositeCurve;
import org.citygml4j.model.gml.geometry.primitives.*;

import java.util.List;

public class CurveArrayPropertyHandler<T extends CurveArrayProperty> implements
        TreeNodeHandler<T> {

    private static LineStringHandler<LineString> lineStringHandler = new LineStringHandler<>();
    private static CompositeCurveHandler<CompositeCurve> compositeCurveHandler = new CompositeCurveHandler<>();
    private static CurveHandler<Curve> curveHandler = new CurveHandler<>();
    private static OrientableCurveHandler<OrientableCurve> orientableCurveHandler = new OrientableCurveHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        List<? extends AbstractCurve> curves = element.getCurve();
        processCurves(curves, node);
    }

    private void processCurves(List<? extends AbstractCurve> curves,
                               ElementNode<T> node) {
        if (curves == null) {
            return;
        }

        for (AbstractCurve abstractCurve : curves) {
            if (abstractCurve instanceof LineString) {
                LineString lineString = (LineString) abstractCurve;
                ElementNode<LineString> lineNode = NodeFactory.createElementNode(
                        lineString, "LineString", node);
                lineStringHandler.addChildsToNode(lineNode);
                node.addChild(lineNode);
            } else if (abstractCurve instanceof CompositeCurve) {
                CompositeCurve compositeCurve = (CompositeCurve) abstractCurve;
                ElementNode<CompositeCurve> compositeCurveElementNode = NodeFactory.createElementNode(compositeCurve, "CompositeCurve", node);
                compositeCurveHandler.addChildsToNode(compositeCurveElementNode);
                node.addChild(compositeCurveElementNode);
            } else if (abstractCurve instanceof Curve) {
                Curve curve = (Curve) abstractCurve;
                ElementNode<Curve> curveElementNode = NodeFactory.createElementNode(curve, "Curve", node);
                curveHandler.addChildsToNode(curveElementNode);
                node.addChild(curveElementNode);
            } else if (abstractCurve instanceof OrientableCurve) {
                OrientableCurve orientableCurve = (OrientableCurve) abstractCurve;
                ElementNode<OrientableCurve> orientableCurveElementNode = NodeFactory.createElementNode(orientableCurve, "OrientableCurve", node);
                orientableCurveHandler.addChildsToNode(orientableCurveElementNode);
                node.addChild(orientableCurveElementNode);
            }
        }
    }

}
