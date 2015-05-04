package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.main.Main;
import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.citygml.core.ImplicitGeometry;
import org.citygml4j.model.citygml.core.ImplicitRepresentationProperty;
import org.citygml4j.model.gml.base.AbstractGML;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 13:45
 */
public class ImplicitRepresentationPropertyHandler<T extends ImplicitRepresentationProperty> implements TreeNodeHandler<T>, Constants {

    private static ImplicitGeometryHandler<ImplicitGeometry> implicitGeometryHandler = new ImplicitGeometryHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        ImplicitGeometry implicitGeometry = element.getImplicitGeometry();
        if (implicitGeometry == null) {
            String href = element.getHref();
            href = href.replace(HASH, EMPTY_STRING);
            CityModel currentModel;
            if (Main.currentCityModel == 1) {
                currentModel = Main.models1.get(0);
            } else {
                currentModel = Main.models2.get(0);
            }
            AbstractGML abstractGML = Main.XLINK_RESOLVER.getAbstractGML(href, currentModel, ImplicitGeometry.class);
            implicitGeometry = (ImplicitGeometry) abstractGML;
        }

        ElementNode<ImplicitGeometry> implicitGeometryElementNode = NodeFactory.createElementNode(implicitGeometry, IMPLICIT_GEOMETRY, node);
        implicitGeometryHandler.addChildsToNode(implicitGeometryElementNode);
        node.addChild(implicitGeometryElementNode);
    }
}
