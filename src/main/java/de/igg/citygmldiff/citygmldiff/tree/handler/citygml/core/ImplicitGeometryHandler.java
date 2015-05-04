package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.handler.gml.base.GMLHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.GeometryPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.geometry.primitives.PointPropertyHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.citygml.core.ImplicitGeometry;
import org.citygml4j.model.citygml.core.TransformationMatrix4x4;
import org.citygml4j.model.gml.basicTypes.Code;
import org.citygml4j.model.gml.geometry.GeometryProperty;
import org.citygml4j.model.gml.geometry.primitives.PointProperty;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 13:45
 */
public class ImplicitGeometryHandler<T extends ImplicitGeometry> extends GMLHandler<T> implements Constants {

    private static TransformationMatrix4x4Handler<TransformationMatrix4x4> transformationMatrix4x4Handler = new TransformationMatrix4x4Handler<>();
    private static GeometryPropertyHandler<GeometryProperty> geometryPropertyHandler = new GeometryPropertyHandler<>();
    private static PointPropertyHandler<PointProperty> pointPropertyHandler = new PointPropertyHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        super.addChildsToNode(node);

        T element = node.getValue();

        Code mimeType = element.getMimeType();
        if (mimeType != null) {
            ElementNode<Code> mimeTypeNode = NodeFactory.createElementNode(mimeType, MIME_TYPE, node);
            TextNode mimeTypeTextNode = new TextNode(mimeType.getValue(), mimeTypeNode);
            mimeTypeNode.addChild(mimeTypeTextNode);
            node.addChild(mimeTypeNode);
        }

        TransformationMatrix4x4 transformationMatrix = element.getTransformationMatrix();
        if (transformationMatrix != null) {
            ElementNode<TransformationMatrix4x4> transformationMatrix4x4ElementNode = NodeFactory.createElementNode(transformationMatrix, TRANSFORMATION_MATRIX, node);
            transformationMatrix4x4Handler.addChildsToNode(transformationMatrix4x4ElementNode);
            node.addChild(transformationMatrix4x4ElementNode);
        }

        String libraryObject = element.getLibraryObject();
        if (libraryObject != null) {
            ElementNode<String> libraryObjectElementNode = NodeFactory.createElementNode(libraryObject, LIBRARY_OBJECT, node);
            TextNode textNode = new TextNode(libraryObject, libraryObjectElementNode);
            libraryObjectElementNode.addChild(textNode);
            node.addChild(libraryObjectElementNode);
        }

        GeometryProperty relativeGMLGeometry = element.getRelativeGMLGeometry();
        if (relativeGMLGeometry != null) {
            ElementNode<GeometryProperty> elementNode = NodeFactory.createElementNode(relativeGMLGeometry, RELATIVE_GML_GEOMETRY, node);
            geometryPropertyHandler.addChildsToNode(elementNode);
            node.addChild(elementNode);
        }

        PointProperty referencePoint = element.getReferencePoint();
        if (referencePoint != null) {
            ElementNode<PointProperty> pointPropertyElementNode = NodeFactory.createElementNode(referencePoint, REFERENCE_POINT, node);
            pointPropertyHandler.addChildsToNode(pointPropertyElementNode);
            node.addChild(pointPropertyElementNode);
        }
    }
}
