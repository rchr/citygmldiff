package de.igg.citygmldiff.citygmldiff.tree.handler.gml.feature;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.apache.log4j.Logger;
import org.citygml4j.model.gml.feature.AbstractFeatureCollection;

/**
 * User: richard
 * Date: 15.11.13
 * Time: 11:56
 */
public class AbstractFeatureCollectionHandler<T extends AbstractFeatureCollection> extends AbstractFeatureHandler<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractFeatureHandler.class);

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        LOGGER.warn("Handling of AbstractFeatureCollection not implemented.");
    }

}
