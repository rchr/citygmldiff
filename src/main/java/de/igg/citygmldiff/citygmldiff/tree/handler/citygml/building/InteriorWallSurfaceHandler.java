package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.building.InteriorWallSurface;

import java.util.List;

/**
 * User: richard
 * Date: 02.10.13
 * Time: 18:33
 */
public class InteriorWallSurfaceHandler<T extends InteriorWallSurface> extends AbstractBoundarySurfaceHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();
        List<ADEComponent> genericApplicationPropertyOfInteriorWallSurface = element.getGenericApplicationPropertyOfInteriorWallSurface();
    }
}
