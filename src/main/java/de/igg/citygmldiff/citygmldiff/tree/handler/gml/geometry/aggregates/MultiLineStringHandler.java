package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.aggregates;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.LineStringPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.aggregates.MultiLineString;
import org.citygml4j.model.gml.geometry.primitives.LineStringProperty;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 11:34
 */
public class MultiLineStringHandler<T extends MultiLineString> extends AbstractGeometricAggregateHandler<T> implements Constants {

    private static LineStringPropertyHandler<LineStringProperty> lineStringPropertyHandler = new LineStringPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<LineStringProperty> lineStringMember = element.getLineStringMember();
        if (lineStringMember != null) {
            processMultiLineStringMember(lineStringMember, node);
        }
    }

    private void processMultiLineStringMember(List<LineStringProperty> lineStringMember, ElementNode<T> node) {
        for (LineStringProperty lineStringProperty : lineStringMember) {
            ElementNode<LineStringProperty> lineStringPropertyElementNode = NodeFactory.createElementNode(lineStringProperty, LINE_STRING_MEMBER, node);
            lineStringPropertyHandler.addChildsToNode(lineStringPropertyElementNode);
            node.addChild(lineStringPropertyElementNode);
        }
    }
}
