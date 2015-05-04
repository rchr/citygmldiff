//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.14 at 08:17:26 PM CEST 
//


package de.igg.citygmldiff.citygmldiff.editscript.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the de.igg.citygmldiff.citygmldiff.editscript.jaxb package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeleteOperation_QNAME = new QName("http://igg.tu-berlin.de", "DeleteOperation");
    private final static QName _InsertDeleteOperation_QNAME = new QName("http://igg.tu-berlin.de", "_InsertDeleteOperation");
    private final static QName _UpdateOperation_QNAME = new QName("http://igg.tu-berlin.de", "UpdateOperation");
    private final static QName _EditScript_QNAME = new QName("http://igg.tu-berlin.de", "EditScript");
    private final static QName _InsertOperation_QNAME = new QName("http://igg.tu-berlin.de", "InsertOperation");
    private final static QName _EditOperation_QNAME = new QName("http://igg.tu-berlin.de", "_EditOperation");
    private final static QName _Uncertain_QNAME = new QName("http://igg.tu-berlin.de", "Uncertain");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.igg.citygmldiff.citygmldiff.editscript.jaxb
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateOperationType }
     */
    public UpdateOperationType createUpdateOperationType() {
        return new UpdateOperationType();
    }

    /**
     * Create an instance of {@link DeleteOperationType }
     */
    public DeleteOperationType createDeleteOperationType() {
        return new DeleteOperationType();
    }

    /**
     * Create an instance of {@link UncertainType }
     */
    public UncertainType createUncertainType() {
        return new UncertainType();
    }

    /**
     * Create an instance of {@link InsertOperationType }
     */
    public InsertOperationType createInsertOperationType() {
        return new InsertOperationType();
    }

    /**
     * Create an instance of {@link EditScriptType }
     */
    public EditScriptType createEditScriptType() {
        return new EditScriptType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteOperationType }{@code >}}
     */
    @XmlElementDecl(namespace = "http://igg.tu-berlin.de", name = "DeleteOperation", substitutionHeadNamespace = "http://igg.tu-berlin.de", substitutionHeadName = "_InsertDeleteOperation")
    public JAXBElement<DeleteOperationType> createDeleteOperation(DeleteOperationType value) {
        return new JAXBElement<DeleteOperationType>(_DeleteOperation_QNAME, DeleteOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertDeleteOperationType }{@code >}}
     */
    @XmlElementDecl(namespace = "http://igg.tu-berlin.de", name = "_InsertDeleteOperation", substitutionHeadNamespace = "http://igg.tu-berlin.de", substitutionHeadName = "_EditOperation")
    public JAXBElement<InsertDeleteOperationType> createInsertDeleteOperation(InsertDeleteOperationType value) {
        return new JAXBElement<InsertDeleteOperationType>(_InsertDeleteOperation_QNAME, InsertDeleteOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateOperationType }{@code >}}
     */
    @XmlElementDecl(namespace = "http://igg.tu-berlin.de", name = "UpdateOperation", substitutionHeadNamespace = "http://igg.tu-berlin.de", substitutionHeadName = "_EditOperation")
    public JAXBElement<UpdateOperationType> createUpdateOperation(UpdateOperationType value) {
        return new JAXBElement<UpdateOperationType>(_UpdateOperation_QNAME, UpdateOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditScriptType }{@code >}}
     */
    @XmlElementDecl(namespace = "http://igg.tu-berlin.de", name = "EditScript")
    public JAXBElement<EditScriptType> createEditScript(EditScriptType value) {
        return new JAXBElement<EditScriptType>(_EditScript_QNAME, EditScriptType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertOperationType }{@code >}}
     */
    @XmlElementDecl(namespace = "http://igg.tu-berlin.de", name = "InsertOperation", substitutionHeadNamespace = "http://igg.tu-berlin.de", substitutionHeadName = "_InsertDeleteOperation")
    public JAXBElement<InsertOperationType> createInsertOperation(InsertOperationType value) {
        return new JAXBElement<InsertOperationType>(_InsertOperation_QNAME, InsertOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditOperationType }{@code >}}
     */
    @XmlElementDecl(namespace = "http://igg.tu-berlin.de", name = "_EditOperation")
    public JAXBElement<EditOperationType> createEditOperation(EditOperationType value) {
        return new JAXBElement<EditOperationType>(_EditOperation_QNAME, EditOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UncertainType }{@code >}}
     */
    @XmlElementDecl(namespace = "http://igg.tu-berlin.de", name = "Uncertain", substitutionHeadNamespace = "http://igg.tu-berlin.de", substitutionHeadName = "_EditOperation")
    public JAXBElement<UncertainType> createUncertain(UncertainType value) {
        return new JAXBElement<UncertainType>(_Uncertain_QNAME, UncertainType.class, null, value);
    }

}