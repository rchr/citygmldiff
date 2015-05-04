package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry;

import de.igg.citygmldiff.citygmldiff.tree.AttributesToElementNodeAdder;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.SRSInformationGroup;

import java.util.List;

public abstract class SRSInformationGroupHandler<T extends SRSInformationGroup>
        implements AttributesToElementNodeAdder<T>, Constants {

    @Override
    public void addAttributesToElementNode(ElementNode<T> elementNode) {

        T element = elementNode.getValue();

        List<String> axisLabels = element.getAxisLabels();
        if (axisLabels != null) {
            for (String label : axisLabels) {
                AttributeNode lableNode = NodeFactory.createAttributeNode(AXIS_LABEL,
                        label, elementNode);
                elementNode.addChild(lableNode);
            }
        }

        List<String> uomLabels = element.getUomLabels();
        if (uomLabels != null) {
            for (String uom : uomLabels) {
                AttributeNode uomNode = NodeFactory.createAttributeNode(UOM_LABEL,
                        uom, elementNode);
                elementNode.addChild(uomNode);
            }
        }
    }

}
