package de.igg.citygmldiff.citygmldiff.tree.handler.gml.grids;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.PointPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.VectorHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.primitives.PointProperty;
import org.citygml4j.model.gml.geometry.primitives.Vector;
import org.citygml4j.model.gml.grids.RectifiedGrid;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 14:02
 */
public class RectifiedGridHandler<T extends RectifiedGrid> extends GridHandler<T> implements Constants {

    private static PointPropertyHandler<PointProperty> pointPropertyHandler = new PointPropertyHandler<>();
    private static VectorHandler<Vector> vectorHandler = new VectorHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        PointProperty origin = element.getOrigin();
        if (origin != null) {
            ElementNode<PointProperty> pointPropertyElementNode = NodeFactory.createElementNode(origin, ORIGIN, node);
            pointPropertyHandler.addChildsToNode(pointPropertyElementNode);
            node.addChild(pointPropertyElementNode);
        }

        List<Vector> offsetVector = element.getOffsetVector();
        if (offsetVector != null) {
            processOffsetVector(offsetVector, node);
        }
    }

    private void processOffsetVector(List<Vector> offsetVector, ElementNode<T> node) {
        for (Vector vector : offsetVector) {
            ElementNode<Vector> vectorNode = NodeFactory.createElementNode(vector, OFFSET_VECTOR, node);
            vectorHandler.addChildsToNode(vectorNode);
            node.addChild(vectorNode);
        }
    }
}
