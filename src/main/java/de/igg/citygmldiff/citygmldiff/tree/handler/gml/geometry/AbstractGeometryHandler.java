package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.base.GMLHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.AbstractGeometry;

import java.util.List;

/**
 * User: richard
 * Date: 05.08.13
 * Time: 15:36
 */
public abstract class AbstractGeometryHandler<T extends AbstractGeometry> extends GMLHandler<T> implements Constants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        String srsName = element.getSrsName();
        if (srsName != null) {
            AttributeNode srsNameNode = NodeFactory.createAttributeNode(SRS_NAME,
                    srsName, node);
            node.addChild(srsNameNode);
        }

        Integer srsDimension = element.getSrsDimension();
        if (srsDimension != null) {
            AttributeNode srsDimNode = NodeFactory.createAttributeNode(SRS_DIMENSION,
                    String.valueOf(srsDimension), node);
            node.addChild(srsDimNode);
        }

        List<String> axisLabels = element.getAxisLabels();
        if (axisLabels != null) {
            for (String label : axisLabels) {
                AttributeNode lableNode = NodeFactory.createAttributeNode(AXIS_LABEL,
                        label, node);
                node.addChild(lableNode);
            }
        }

        List<String> uomLabels = element.getUomLabels();
        if (uomLabels != null) {
            for (String uom : uomLabels) {
                AttributeNode uomNode = NodeFactory.createAttributeNode(UOM_LABEL,
                        uom, node);
                node.addChild(uomNode);
            }
        }
    }
}
