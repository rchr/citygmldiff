package de.igg.citygmldiff.citygmldiff.util;

import de.igg.citygmldiff.citygmldiff.main.Main;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import org.citygml4j.model.citygml.core.AbstractCityObject;
import org.citygml4j.model.citygml.generics.*;
import org.citygml4j.model.gml.base.AbstractGML;
import org.citygml4j.model.gml.base.AssociationByRepOrRef;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.primitives.Envelope;

import java.util.*;

/**
 * User: richard Date: 01.08.13 Time: 14:18
 */
public class Utils implements Constants {

    public final static String SRS_NAME = "srsName";
    public final static String SRS_DIMENSION = "srsDimension";
    public final static String FUNCTION = "function";
    public final static String USAGE = "usage";
    public final static String CLAZZ = "class";
    public final static String ROOF_TYPE = "roofType";
    public final static String MEASURED_HEIGHT = "measuredHeight";
    public final static String YEAR_OF_CONSTRUCTION = "yearOfConstruction";
    public final static String YEAR_OF_DEMOLOTION = "yearOfDemolotion";
    public final static ElementNode<Object> NIL_NODE = new ElementNode<>(
            null, "nil", "", null);
    public final static double OVERLAP_THRESHOLD = 0.5;

    public static Map<String, Set<AttributeNode>> xlinkXPathMap2 = new HashMap<>();
    public static Map<String, Set<AttributeNode>> xlinkXPathMap1 = new HashMap<>();

    /**
     * Creates the signature for a {@link AbstractTreeNode} holding the element 'element'. Since the id is included in the signature,
     *
     * @param parentNode
     * @param element
     * @param nameOfCurrentNode
     * @param <E>
     * @return
     */
    public static <E> String buildSignature(AbstractTreeNode parentNode, E element, String nameOfCurrentNode) {
        String id = "";
        if (element != null && element instanceof AbstractGML) {
            id = ":" + ((AbstractGML) element).getId();
        }
        nameOfCurrentNode += id;
        return parentNode.getSignature() + "/" + nameOfCurrentNode;
    }

    public static <E> String buildXPath(AbstractTreeNode parentNode, E element, String nameOfCurrentNode) {

        String id = "";
        if (element != null && element instanceof AbstractGML) {
            String gmlid = ((AbstractGML) element).getId();
            if (gmlid != null && gmlid.length() > 0) {
                id = "[@gml:id=\"" + gmlid + "\"]";
            }
            nameOfCurrentNode += id;
        }
        if (element != null && element instanceof AbstractGenericAttribute) {
            String attributeType = "";
            if (element instanceof DateAttribute) {
                attributeType = Constants.DATE_ATTRIBUTE;
            } else if (element instanceof DoubleAttribute) {
                attributeType = Constants.DOUBLE_ATTRIBUTE;
            } else if (element instanceof IntAttribute) {
                attributeType = Constants.INT_ATTRIBUTE;
            } else if (element instanceof MeasureAttribute) {
                attributeType = Constants.MEASURE_ATTRIBUTE;
            } else if (element instanceof StringAttribute) {
                attributeType = Constants.STRING_ATTRIBUTE;
            } else if (element instanceof UriAttribute) {
                attributeType = Constants.URI_ATTRIBUTE;
            }
            nameOfCurrentNode = attributeType + "[@name=\"" + nameOfCurrentNode + "\"]";
        }

        if (nameOfCurrentNode.equals(TextNode.TEXT_NODE)) {
            nameOfCurrentNode = "";
        }
        if (parentNode != null) {
            String parentXPath = parentNode.getXPath();
            if (parentXPath != null) {
                return parentNode.getXPath() + "/" + nameOfCurrentNode;
            }
        }
        return "/" + nameOfCurrentNode;
    }

    public static boolean treesHaveSameDigest(AbstractTreeNode head1,
                                              AbstractTreeNode head2) {
        byte[] digestTree1 = head1.getDigest();
        byte[] digestTree2 = head2.getDigest();
        return Arrays.equals(digestTree1, digestTree2);
    }

    public static boolean areCityObjectsMatching(AbstractCityObject cityObject1, AbstractCityObject cityObject2) {
        return cityObject1.getClass() == cityObject2.getClass() && cityObject1.getId().equals(cityObject2.getId());
    }

    /**
     * Checks if the two {@link org.citygml4j.model.citygml.core.AbstractCityObject}s are matching.
     * Both are matching if the intersection of their {@link org.citygml4j.model.gml.feature.BoundingShape}'s {@link org.citygml4j.model.gml.geometry.primitives.Envelope} is greater than the {@link #OVERLAP_THRESHOLD}.
     * Note: The intersection of 2D envelopes is calculated. Thus, the z-coordinate is disregarded. Thus, this method will return true for objects which are located above each other.
     *
     * @param cityObject1
     * @param cityObject2
     * @return
     */
    public static boolean areCityObjectsMatchingSpatially(AbstractCityObject cityObject1, AbstractCityObject cityObject2) {
        BoundingShape boundedByObject1 = cityObject1.getBoundedBy();
        Envelope envelope1 = boundedByObject1.getEnvelope();
        com.vividsolutions.jts.geom.Envelope jtsEnvelope1 = CityGMLToJTS.toJtsEnvelope(envelope1);

        BoundingShape boundedByObject2 = cityObject2.getBoundedBy();
        Envelope envelope2 = boundedByObject2.getEnvelope();
        com.vividsolutions.jts.geom.Envelope jtsEnvelope2 = CityGMLToJTS.toJtsEnvelope(envelope2);

        com.vividsolutions.jts.geom.Envelope intersection12 = jtsEnvelope1.intersection(jtsEnvelope2);
        com.vividsolutions.jts.geom.Envelope intersection21 = jtsEnvelope2.intersection(jtsEnvelope1);

        double overlap1 = jtsEnvelope1.getArea() / intersection12.getArea();
        double overlap2 = jtsEnvelope2.getArea() / intersection21.getArea();

        return (overlap1 > OVERLAP_THRESHOLD || overlap2 > OVERLAP_THRESHOLD);
    }

    public static boolean isSignatureEqual(AbstractTreeNode node1, AbstractTreeNode node2) {
        return node1.getSignature().equals(node2.getSignature());
    }

    public static <E extends AssociationByRepOrRef<?>> void addXlinkFeature(ElementNode<E> node) {
        E element = node.getValue();
        String href = element.getHref();
        AttributeNode attributeNode = NodeFactory.createAttributeNode(XLINK_HREF, href, node);
        node.addChild(attributeNode);

        String xlink = href.replace("#", "");
        if (Main.currentCityModel == 1) {
            addXlinkNodeToMap1(attributeNode, xlink);
        } else if (Main.currentCityModel == 2) {
            addXlinkNodeToMap2(attributeNode, xlink);
        }
    }

    private static void addXlinkNodeToMap2(AttributeNode attributeNode, String xlink) {

        if (xlinkXPathMap2.containsKey(xlink)) {
            xlinkXPathMap2.get(xlink).add(attributeNode);
        } else {
            Set<AttributeNode> attributeNodes = new HashSet<>();
            attributeNodes.add(attributeNode);
            xlinkXPathMap2.put(xlink, attributeNodes);
        }
    }

    private static void addXlinkNodeToMap1(AttributeNode attributeNode, String xlink) {

        if (xlinkXPathMap1.containsKey(xlink)) {
            xlinkXPathMap1.get(xlink).add(attributeNode);
        } else {
            Set<AttributeNode> attributeNodes = new HashSet<>();
            attributeNodes.add(attributeNode);
            xlinkXPathMap1.put(xlink, attributeNodes);
        }
    }

}
