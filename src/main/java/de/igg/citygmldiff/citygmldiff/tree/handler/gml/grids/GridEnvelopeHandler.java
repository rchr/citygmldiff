package de.igg.citygmldiff.citygmldiff.tree.handler.gml.grids;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.grids.GridEnvelope;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 18:57
 */
public class GridEnvelopeHandler<T extends GridEnvelope> implements TreeNodeHandler<T>, Constants {

    private static final String SPACE = " ";

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        List<Integer> highs = element.getHigh();
        if (highs != null) {
            String h = parseHighsAndLows(highs);
            ElementNode<List<Integer>> highElementNode = NodeFactory.createElementNode(highs, HIGH, node);
            TextNode highTextNode = new TextNode(h, highElementNode);
            highElementNode.addChild(highTextNode);
            node.addChild(highElementNode);
        }

        List<Integer> lows = element.getLow();
        if (lows != null) {
            String l = parseHighsAndLows(lows);
            ElementNode<List<Integer>> lowElementNode = NodeFactory.createElementNode(lows, LOW, node);
            TextNode lowTextNode = new TextNode(l, lowElementNode);
            lowElementNode.addChild(lowTextNode);
            node.addChild(lowElementNode);
        }
    }

    private String parseHighsAndLows(List<Integer> highsOrLows) {
        StringBuilder sb = new StringBuilder("");
        for (Integer i : highsOrLows) {
            sb.append(String.valueOf(i));
            sb.append(SPACE);
        }
        return sb.toString();
    }
}
