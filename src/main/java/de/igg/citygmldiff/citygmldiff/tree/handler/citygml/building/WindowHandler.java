package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.building.Window;

import java.util.List;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 12:26
 */
public class WindowHandler<T extends Window> extends AbstractOpeningHandler<T> implements Constants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        List<ADEComponent> genericApplicationPropertyOfWindow = element.getGenericApplicationPropertyOfWindow();
    }
}
