package de.igg.citygmldiff.citygmldiff.tree.model;

import de.igg.citygmldiff.citygmldiff.util.Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: richard Date: 01.08.13 Time: 14:23
 */
public class TextNode extends AbstractLeafNode {

    public final static String TEXT_NODE = "textNode";

    public TextNode(String value, ElementNode<?> parent) {
        super(parent.getSignature() + "/" + TEXT_NODE, parent, value);
        String xPath = Utils.buildXPath(parent, value, TEXT_NODE);
        setXPath(xPath);
    }

    public TextNode(TextNode textNode) {
        this(textNode.getValue(), textNode.getParent());
    }

    @Override
    public byte[] calculateHash() {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update((byte) 0);
            md.update((byte) 0);
            md.update((byte) 0);
            md.update((byte) 3);
            md.update(this.getValue().getBytes("UnicodeBigUnmarked"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md.digest();
    }
}
