package de.igg.citygmldiff.citygmldiff.editscript;

import de.igg.citygmldiff.citygmldiff.editscript.jaxb.*;
import de.igg.citygmldiff.citygmldiff.editscript.model.*;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.AttributeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.tree.model.TextNode;
import org.citygml4j.builder.jaxb.JAXBBuilder;
import org.citygml4j.builder.jaxb.marshal.JAXBMarshaller;
import org.citygml4j.builder.jaxb.marshal.xal.XALMarshaller;
import org.citygml4j.model.common.base.ModelObject;
import org.citygml4j.model.gml.base.AbstractGML;
import org.citygml4j.model.module.ModuleContext;
import org.citygml4j.model.module.citygml.*;
import org.citygml4j.model.module.gml.GMLCoreModule;
import org.citygml4j.model.module.gml.XLinkModule;
import org.citygml4j.model.module.xal.XALCoreModule;
import org.citygml4j.util.xml.SAXWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.dom.DOMResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by richard on 19.02.14.
 */
public class EditScriptWriter {

    private de.igg.citygmldiff.citygmldiff.editscript.jaxb.ObjectFactory objectFactory;
    private EditScriptType editScriptType;
    private List<EditOperationType> editOperationTypes;
    private JAXBMarshaller jaxbMarshaller;
    private Marshaller marshaller;
    private XALMarshaller xalMarshaller;
    private SAXWriter saxWriter;
    private Marshaller cityGMLDiffMarshaller;

    private org.citygml4j.jaxb.citygml.core._2.ObjectFactory objectFactory1;


    public EditScriptWriter(String destinationFileName) {
        objectFactory = new de.igg.citygmldiff.citygmldiff.editscript.jaxb.ObjectFactory();
        editScriptType = objectFactory.createEditScriptType();
        init(destinationFileName);
    }

    private void init(String destinationFileName) {
        try {
            objectFactory1 = new org.citygml4j.jaxb.citygml.core._2.ObjectFactory();
            JAXBBuilder jaxbBuilder = new JAXBBuilder();
            ModuleContext moduleContext = new ModuleContext(CityGMLVersion.v2_0_0);
            jaxbMarshaller = jaxbBuilder.createJAXBMarshaller(moduleContext);
            JAXBContext jc = jaxbBuilder.getJAXBContext();
            marshaller = jc.createMarshaller();
            xalMarshaller = new XALMarshaller();

            JAXBContext cityGMLDiffJAXBContext = JAXBContext.newInstance("de.igg.citygmldiff.citygmldiff.editscript.jaxb");
            cityGMLDiffMarshaller = cityGMLDiffJAXBContext.createMarshaller();
            FileWriter fileWriter = new FileWriter(destinationFileName);
            saxWriter = new SAXWriter(fileWriter);
            // saxWriter.setPrefix("citygml", "http://www.opengis.net/citygml/2.0");
            saxWriter.setPrefix(AppearanceModule.v2_0_0.getNamespacePrefix(), AppearanceModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(BridgeModule.v2_0_0.getNamespacePrefix(), BridgeModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(BuildingModule.v2_0_0.getNamespacePrefix(), BuildingModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(CityFurnitureModule.v2_0_0.getNamespacePrefix(), CityFurnitureModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(CityObjectGroupModule.v2_0_0.getNamespacePrefix(), CityObjectGroupModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(CoreModule.v2_0_0.getNamespacePrefix(), CoreModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(GMLCoreModule.v3_1_1.getNamespacePrefix(), GMLCoreModule.v3_1_1.getNamespaceURI());
            saxWriter.setPrefix(GenericsModule.v2_0_0.getNamespacePrefix(), GenericsModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(LandUseModule.v2_0_0.getNamespacePrefix(), LandUseModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(ReliefModule.v2_0_0.getNamespacePrefix(), ReliefModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(TexturedSurfaceModule.v2_0_0.getNamespacePrefix(), TexturedSurfaceModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(TransportationModule.v2_0_0.getNamespacePrefix(), TransportationModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(TunnelModule.v2_0_0.getNamespacePrefix(), TunnelModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(VegetationModule.v2_0_0.getNamespacePrefix(), VegetationModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(WaterBodyModule.v2_0_0.getNamespacePrefix(), WaterBodyModule.v2_0_0.getNamespaceURI());
            saxWriter.setPrefix(XALCoreModule.v2_0.getNamespacePrefix(), XALCoreModule.v2_0.getNamespaceURI());
            saxWriter.setPrefix(XLinkModule.v3_1_1.getNamespacePrefix(), XLinkModule.v3_1_1.getNamespaceURI());
//            saxWriter.setDefaultNamespace("http://www.opengis.net/citygml/2.0");
//            CityGMLNamespaceContext cityGMLNamespaceContext = new CityGMLNamespaceContext();
//            cityGMLNamespaceContext.setDefaultNamespace("http://www.opengis.net/citygml/2.0");
//            saxWriter.setNamespaceContext(cityGMLNamespaceContext);
            saxWriter.setIndentString("    ");
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }


    public void writeEditScript(List<EditOperation> editOperations) {
        editOperationTypes = editScriptType.getEditOperation();
        for (EditOperation editOp : editOperations) {
            if (editOp instanceof InsertOperation) {
                InsertOperation insertOperation = (InsertOperation) editOp;
                editOperationTypes.add(createInsertOperationType(insertOperation));
            } else if (editOp instanceof DeleteOperation) {
                DeleteOperation deleteOperation = (DeleteOperation) editOp;
                editOperationTypes.add(createDeleteOperationType(deleteOperation));
            } else if (editOp instanceof UpdateOperation) {
                UpdateOperation updateOperation = (UpdateOperation) editOp;
                editOperationTypes.add(createUpdateOperationType(updateOperation));
            } else if (editOp instanceof UncertainOperation) {
                UncertainOperation uncertainOperation = (UncertainOperation) editOp;
                editOperationTypes.add(createUncertainOperationType(uncertainOperation));
            }
        }
        JAXBElement<EditScriptType> editScript = objectFactory.createEditScript(editScriptType);
        try {
            cityGMLDiffMarshaller.marshal(editScript, saxWriter);
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }

    private UpdateOperationType createUpdateOperationType(UpdateOperation updateOperation) {
        String oldValue = updateOperation.getNodeToUpdate().getValue();
        String newValue = updateOperation.getUpdateValue();
        String xPath = updateOperation.getNodeToUpdate().getXPath();
        return createUpdateOperationType(oldValue, newValue, xPath);
    }

    private DeleteOperationType createDeleteOperationType(DeleteOperation deleteOperation) {
        AbstractTreeNode nodeToDelete = deleteOperation.getNodeToDelete();
        Object element = extractElement(nodeToDelete);
        String xPath = nodeToDelete.getXPath();
        return createDeleteOperationType(element, xPath);
    }

    private InsertOperationType createInsertOperationType(InsertOperation insertOperation) {
        AbstractTreeNode nodeToInsert = insertOperation.getNodeToInsert();
        String xPath = nodeToInsert.getXPath();
        Object element = extractElement(nodeToInsert);
        return createInsertOperationType(element, xPath);
    }

    private UncertainType createUncertainOperationType(UncertainOperation uncertainOperation) {
        AbstractTreeNode node1 = uncertainOperation.getNode1();
        AbstractTreeNode node2 = uncertainOperation.getNode2();
        String xPath1 = node1.getXPath();
        String id1 = "";
        String id2 = "";
        if (node1 instanceof ElementNode && node2 instanceof ElementNode) {
            ElementNode elementNode1 = (ElementNode) node1;
            ElementNode elementNode2 = (ElementNode) node2;

            Object value1 = elementNode1.getValue();
            Object value2 = elementNode2.getValue();
            if (value1 instanceof AbstractGML && value2 instanceof AbstractGML) {
                id1 = ((AbstractGML) value1).getId();
                id2 = ((AbstractGML) value2).getId();
            }
        }
        return createUncertainType(xPath1, id1, id2);

    }

    private Object extractElement(AbstractTreeNode changedNode) {
        Object element = null;
        Element elt = null;
        if (changedNode instanceof ElementNode<?>) {
            ElementNode<?> node = (ElementNode<?>) changedNode;
            element = node.getValue();
        } else if (changedNode instanceof TextNode) {
            TextNode textNode = (TextNode) changedNode;
            element = textNode.getValue();
        } else if (changedNode instanceof AttributeNode) {
            AttributeNode attributeNode = (AttributeNode) changedNode;
            element = attributeNode.getAttributeValue();
        }
        try {
            JAXBElement<?> jaxbElement = jaxbMarshaller.marshalJAXBElement(element);

            if (jaxbElement != null) {
                DOMResult res = new DOMResult();
                marshaller.marshal(jaxbElement, res);
                elt = ((Document) res.getNode()).getDocumentElement();
            } else {
                if (element instanceof ModelObject) {
                    ModelObject modelObject = (ModelObject) element;
                    Object jaxbElement1 = jaxbMarshaller.marshal(modelObject);
                    if (jaxbElement1 == null) {
                        jaxbElement1 = xalMarshaller.marshal((ModelObject) element);
                    }
                    DOMResult res = new DOMResult();
                    marshaller.marshal(jaxbElement1, res);
                    elt = ((Document) res.getNode()).getDocumentElement();
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        if (elt != null) {
            return elt;
        }
        return element;
    }

    private InsertOperationType createInsertOperationType(Object value, String xPath) {
        InsertOperationType insertOperationType = objectFactory.createInsertOperationType();
        insertOperationType.setXPath(xPath);
        insertOperationType.setValue(value);
        return insertOperationType;
    }

    private DeleteOperationType createDeleteOperationType(Object value, String xPath) {
        DeleteOperationType deleteOperationType = objectFactory.createDeleteOperationType();
        deleteOperationType.setXPath(xPath);
        deleteOperationType.setValue(value);
        return deleteOperationType;
    }

    private UpdateOperationType createUpdateOperationType(String oldValue, String newValue, String xPath) {
        UpdateOperationType updateOperationType = objectFactory.createUpdateOperationType();
        updateOperationType.setXPath(xPath);
        updateOperationType.setOldValue(oldValue);
        updateOperationType.setNewValue(newValue);
        return updateOperationType;
    }

    private UncertainType createUncertainType(String xPath, String oldID, String newID) {
        UncertainType uncertainType = objectFactory.createUncertainType();
        uncertainType.setOldID(oldID);
        uncertainType.setNewID(newID);
        uncertainType.setXPath(xPath);
        return uncertainType;
    }
}
