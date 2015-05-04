package de.igg.citygmldiff.citygmldiff.matching.model;

import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import junit.framework.TestCase;

/**
 * User: richard
 * Date: 31.01.14
 * Time: 11:54
 */
public class MatchingTest extends TestCase {

    public void testEquals() throws Exception {
        ElementNode<String> elementNode1 = new ElementNode<>("parent", "Parent", "/parent", null);
        ElementNode<String> elementNode2 = new ElementNode<>("parent", "Parent", "/parent", null);
        TextNode textNode1 = new TextNode("text", elementNode1);
        TextNode textNode2 = new TextNode("text", elementNode2);
        Matching matching1 = new Matching(textNode1, textNode2);
        Matching matching2 = new Matching(textNode1, textNode2);

        assertTrue(matching1.equals(matching2));
    }

    public void testHashCode() {
        ElementNode<String> elementNode1 = new ElementNode<>("parent", "Parent", "/parent", null);
        ElementNode<String> elementNode2 = new ElementNode<>("parent", "Parent", "/parent", null);
        TextNode textNode1 = new TextNode("text", elementNode1);
        TextNode textNode2 = new TextNode("text", elementNode2);
        Matching matching1 = new Matching(textNode1, textNode2);
        Matching matching2 = new Matching(textNode1, textNode2);

        int hash1 = matching1.hashCode();
        int hash2 = matching1.hashCode();
        assertEquals(hash1, hash2);
    }
}
