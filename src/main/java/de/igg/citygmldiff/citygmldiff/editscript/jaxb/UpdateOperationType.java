//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.14 at 08:17:26 PM CEST 
//


package de.igg.citygmldiff.citygmldiff.editscript.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateOperationType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="UpdateOperationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://igg.tu-berlin.de}EditOperationType">
 *       &lt;sequence>
 *         &lt;element name="oldValue" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="newValue" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateOperationType", propOrder = {
        "oldValue",
        "newValue"
})
public class UpdateOperationType
        extends EditOperationType {

    @XmlElement(required = true)
    protected Object oldValue;
    @XmlElement(required = true)
    protected Object newValue;

    /**
     * Gets the value of the oldValue property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getOldValue() {
        return oldValue;
    }

    /**
     * Sets the value of the oldValue property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setOldValue(Object value) {
        this.oldValue = value;
    }

    /**
     * Gets the value of the newValue property.
     *
     * @return possible object is
     * {@link Object }
     */
    public Object getNewValue() {
        return newValue;
    }

    /**
     * Sets the value of the newValue property.
     *
     * @param value allowed object is
     *              {@link Object }
     */
    public void setNewValue(Object value) {
        this.newValue = value;
    }

}
