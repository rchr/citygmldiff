package de.igg.citygmldiff.citygmldiff.tree.model;

import de.igg.citygmldiff.citygmldiff.util.Utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: richard
 * Date: 01.08.13
 * Time: 14:19
 */
public class ElementNode<E> extends AbstractTreeNode {

    private E value;
    private String name;

    public ElementNode(E value, String name, String signature, ElementNode<?> parent) {
        super(signature, parent);
        this.value = value;
        this.name = name;
        String xPath = Utils.buildXPath(parent, value, name);
        setXPath(xPath);
    }

    public ElementNode(ElementNode<E> elementNode) {
        this(elementNode.getValue(), elementNode.getName(), elementNode.getSignature(), elementNode.getParent());
        this.setChildren(elementNode.getChildren());
    }

    public E getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public byte[] calculateHash() {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeInt(1);//This is stored in network byte order
            dos.write(this.getName().getBytes("UnicodeBigUnmarked"));
            dos.write((byte) 0);
            dos.write((byte) 0);
            // Collect all attributes except for namespace declarations
            List<AbstractTreeNode> children = this.getChildren();
            List<byte[]> attributeHashes = new ArrayList<>();
            List<byte[]> childHashes = new ArrayList<>();

            int numberAttributes = 0;
            int numberChildren = 0;
            for (AbstractTreeNode abstractTreeNode : children) {
                if (abstractTreeNode instanceof AttributeNode) {
                    attributeHashes.add(abstractTreeNode.getDigest());
                    numberAttributes++;
                } else {
                    childHashes.add(abstractTreeNode.getDigest());
                    numberChildren++;
                }
            }
            dos.writeInt(numberAttributes);

            for (byte[] hash : attributeHashes) {
                dos.write(hash);
            }

            dos.writeInt(numberChildren);
            for (byte[] hash : childHashes) {
                dos.write(hash);
            }

            dos.close();
            md.update(baos.toByteArray());
            return md.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;

    }


}
