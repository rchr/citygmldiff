package de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.complexes.CompositeSolidHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.gml.geometry.complexes.CompositeSolid;
import org.citygml4j.model.gml.geometry.primitives.AbstractSolid;
import org.citygml4j.model.gml.geometry.primitives.Solid;
import org.citygml4j.model.gml.geometry.primitives.SolidProperty;

public class SolidPropertyHandler<T extends SolidProperty> implements
        TreeNodeHandler<T> {

    private static SolidHandler<Solid> solidHandler = new SolidHandler<>();
    private static CompositeSolidHandler<CompositeSolid> compositeSolidHandler = new CompositeSolidHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        AbstractSolid abstractSolid = element.getGeometry();
        if (abstractSolid == null) {
            Utils.addXlinkFeature(node);
        } else if (abstractSolid instanceof Solid) {
            Solid solid = (Solid) abstractSolid;
            String name = solid.getGMLClass().name();
            String signature = Utils.buildSignature(node, solid, name);
            ElementNode<Solid> solidNode = new ElementNode<>(solid, name,
                    signature, node);
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
