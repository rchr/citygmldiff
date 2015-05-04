package de.igg.citygmldiff.citygmldiff.tree.handler.gml.measures;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.basicTypes.MeasureHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.gml.measures.Length;

/**
 * User: richard
 * Date: 12.08.13
 * Time: 15:39
 */
public class LengthHandler<T extends Length> extends MeasureHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);
    }
}
