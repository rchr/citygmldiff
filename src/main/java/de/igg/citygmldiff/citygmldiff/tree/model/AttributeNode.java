package de.igg.citygmldiff.citygmldiff.tree.model;

import de.igg.citygmldiff.citygmldiff.util.Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: richard Date: 01.08.13 Time: 14:21
 */
public class AttributeNode extends AbstractLeafNode {

    private static final String ATTRIBUTE_NODE = "attributeNode";
    private String attributeName;

    public AttributeNode(String attributeName, String attributeValue,
                         String signature, ElementNode<?> parent) {
        super(signature, parent, attributeValue);
        this.attributeName = attributeName;
        String xPath = Utils.buildXPath(parent, attributeValue, attributeName);
        setXPath(xPath);
    }

    public AttributeNode(AttributeNode attributeNode) {
        this(attributeNode.getAttributeName(), attributeNode.getAttributeValue(), attributeNode.getSignature(), attributeNode.getParent());
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeValue() {
        return super.getValue();
    }

    @Override
    public String getXPath() {
        String xPath = getParent().getXPath();
        xPath += "[@" + getAttributeName() + "]";
        return xPath;
    }

    @Override
    public byte[] calculateHash() {
        MessageDigest md = null;
        try {
            if (this.getAttributeName().equals("xmlns")
                    || this.getAttributeName().startsWith("xmlns:"))
                return null;
            md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update((byte) 0);
            md.update((byte) 0);
            md.update((byte) 0);
            md.update((byte) 2);
            md.update(this.getAttributeName().getBytes("UnicodeBigUnmarked"));
            md.update((byte) 0);
            md.update((byte) 0);
            md.update(this.getAttributeValue().getBytes("UnicodeBigUnmarked"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md.digest();
    }
}
