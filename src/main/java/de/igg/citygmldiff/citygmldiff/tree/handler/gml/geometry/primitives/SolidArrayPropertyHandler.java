package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes.CompositeSolidHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.gml.geometry.complexes.CompositeSolid;
import org.citygml4j.model.gml.geometry.primitives.AbstractSolid;
import org.citygml4j.model.gml.geometry.primitives.Solid;
import org.citygml4j.model.gml.geometry.primitives.SolidArrayProperty;

import java.util.List;

/**
 * User: richard
 * Date: 24.09.13
 * Time: 13:17
 */
public class SolidArrayPropertyHandler<T extends SolidArrayProperty> implements TreeNodeHandler<T> {

    private static SolidHandler<Solid> solidHandler = new SolidHandler<>();
    private static CompositeSolidHandler<CompositeSolid> compositeSolidHandler = new CompositeSolidHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();
        List<AbstractSolid> solids = element.getGeometry();
        if (solids != null) {
            processSolids(solids, node);
        }
    }

    private void processSolids(List<AbstractSolid> solids, ElementNode<T> node) {
        for (AbstractSolid abstractSolid : solids) {
            if (abstractSolid instanceof Solid) {
                Solid solid = (Solid) abstractSolid;
                ElementNode<Solid> solidNode = NodeFactory.createElementNode(solid, "solid", node);
                solidHandler.addChildsToNode(solidNode);
                node.addChild(solidNode);
            } else if (abstractSolid instanceof CompositeSolid) {
                CompositeSolid compositeSolid = (CompositeSolid) abstractSolid;
                ElementNode<CompositeSolid> compositeSolidNode = NodeFactory.createElementNode(compositeSolid, "compositeSolid", node);
                compositeSolidHandler.addChildsToNode(compositeSolidNode);
                node.addChild(compositeSolidNode);
            }
        }
    }
}
