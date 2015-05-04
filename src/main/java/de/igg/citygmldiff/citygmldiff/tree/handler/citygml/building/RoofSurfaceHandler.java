package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.building.RoofSurface;

import java.util.List;

/**
 * User: richard
 * Date: 08.08.13
 * Time: 13:07
 */
public class RoofSurfaceHandler<T extends RoofSurface> extends AbstractBoundarySurfaceHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();
        List<ADEComponent> genericApplicationPropertyOfRoofSurface = element.getGenericApplicationPropertyOfRoofSurface();
    }
}
