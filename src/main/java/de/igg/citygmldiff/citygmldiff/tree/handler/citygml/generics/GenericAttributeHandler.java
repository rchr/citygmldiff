package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.generics;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import org.citygml4j.model.citygml.generics.*;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * User: richard
 * Date: 05.08.13
 * Time: 12:06
 */
public class GenericAttributeHandler<T extends AbstractGenericAttribute> {

    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        TextNode textNode = null;
        if (element instanceof StringAttribute) {
            String value = ((StringAttribute) element).getValue();
            textNode = new TextNode(value, node);
        } else if (element instanceof DateAttribute) {
            GregorianCalendar value = ((DateAttribute) element).getValue();
            // TODO
        } else if (element instanceof IntAttribute) {
            int value = ((IntAttribute) element).getValue();
            textNode = new TextNode(String.valueOf(value), node);
        } else if (element instanceof DoubleAttribute) {
            double value = ((DoubleAttribute) element).getValue();
            textNode = new TextNode(String.valueOf(value), node);
        } else if (element instanceof MeasureAttribute) {
            // TODO
        } else if (element instanceof UriAttribute) {
            String value = ((StringAttribute) element).getValue();
            textNode = new TextNode(value, node);
        } else if (element instanceof GenericAttributeSet) {
            List<AbstractGenericAttribute> attributeSet = ((GenericAttributeSet) element).getGenericAttribute();
            for (AbstractGenericAttribute genAttr : attributeSet) {
                // TODO
            }
        } else {
            System.out.println("Unknown generic attribute type!");
        }
        node.addChild(textNode);
    }

}
