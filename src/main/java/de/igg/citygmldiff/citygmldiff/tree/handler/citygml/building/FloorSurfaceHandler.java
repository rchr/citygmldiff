package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.building.FloorSurface;

import java.util.List;

/**
 * User: richard
 * Date: 02.10.13
 * Time: 18:29
 */
public class FloorSurfaceHandler<T extends FloorSurface> extends AbstractBoundarySurfaceHandler<T> implements Constants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<ADEComponent> genericApplicationPropertyOfFloorSurface = element.getGenericApplicationPropertyOfFloorSurface();
    }
}
