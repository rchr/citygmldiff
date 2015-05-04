package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry;

import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.SRSReferenceGroup;

public abstract class SRSReferenceGroupAttributeHandler<T extends SRSReferenceGroup>
        extends SRSInformationGroupHandler<T> implements Constants {

    @Override
    public void addAttributesToElementNode(ElementNode<T> elementNode) {
        super.addAttributesToElementNode(elementNode);

        T element = elementNode.getValue();

        String srsName = element.getSrsName();
        if (srsName != null) {
            AttributeNode srsNameNode = NodeFactory.createAttributeNode(SRS_NAME,
                    srsName, elementNode);
            elementNode.addChild(srsNameNode);
        }

        Integer srsDimension = element.getSrsDimension();
        if (srsDimension != null) {
            AttributeNode srsDimNode = NodeFactory.createAttributeNode(SRS_DIMENSION,
                    String.valueOf(srsDimension), elementNode);
            elementNode.addChild(srsDimNode);
        }
    }

}
