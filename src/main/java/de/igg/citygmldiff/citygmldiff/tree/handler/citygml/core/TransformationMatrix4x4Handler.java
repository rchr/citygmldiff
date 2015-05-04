package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import org.citygml4j.geometry.Matrix;
import org.citygml4j.model.citygml.core.TransformationMatrix4x4;

/**
 * User: richard
 * Date: 27.09.13
 * Time: 13:58
 */
public class TransformationMatrix4x4Handler<T extends TransformationMatrix4x4> implements TreeNodeHandler<T>, Constants {

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        Matrix matrix = element.getMatrix();
        String matrixAsText = parseMatrix(matrix);
        TextNode textNode = new TextNode(matrixAsText, node);
        node.addChild(textNode);
    }

    /**
     * Parses the matrix and returns its string representation.
     *
     * @param matrix
     * @return
     */
    private String parseMatrix(Matrix matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        int rows = matrix.getRowDimension();
        int cols = matrix.getColumnDimension();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                stringBuilder.append(" ");
                String currentVal = String.valueOf(matrix.get(i, j));
                stringBuilder.append(currentVal);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
