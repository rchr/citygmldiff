package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.core.AbstractSite;

import java.util.List;

/**
 * User: richard
 * Date: 25.07.13
 * Time: 14:08
 */
public abstract class SiteHandler<T extends AbstractSite> extends AbstractCityObjectHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();
        List<ADEComponent> genericApplicationPropertyOfSite = element.getGenericApplicationPropertyOfSite();

    }
}
