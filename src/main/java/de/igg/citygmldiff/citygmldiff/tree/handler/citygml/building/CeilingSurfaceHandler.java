package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.building.CeilingSurface;

import java.util.List;

/**
 * User: richard
 * Date: 02.10.13
 * Time: 18:36
 */
public class CeilingSurfaceHandler<T extends CeilingSurface> extends AbstractBoundarySurfaceHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();
        List<ADEComponent> genericApplicationPropertyOfCeilingSurface = element.getGenericApplicationPropertyOfCeilingSurface();
    }
}
