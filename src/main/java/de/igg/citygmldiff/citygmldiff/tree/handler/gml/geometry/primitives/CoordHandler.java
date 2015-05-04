package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import org.citygml4j.model.gml.geometry.primitives.Coord;

public class CoordHandler<T extends Coord> implements TreeNodeHandler<T> {

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        Double x = element.getX();
        if (x != null) {
            TextNode xNode = new TextNode(String.valueOf(x), node);
            node.addChild(xNode);
        }

        Double y = element.getY();
        if (y != null) {
            TextNode yNode = new TextNode(String.valueOf(y), node);
            node.addChild(yNode);
        }

        Double z = element.getZ();
        if (z != null) {
            TextNode zNode = new TextNode(String.valueOf(z), node);
            node.addChild(zNode);
        }
    }

}
