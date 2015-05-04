package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.generics.GenericAttributeHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.gml.feature.AbstractFeatureHandler;
import de.igg.citygmldiff.citygmldiff.tree.handler.other.GregorianCalendarHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.citygml.core.*;
import org.citygml4j.model.citygml.generics.AbstractGenericAttribute;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Class to handle {@link AbstractCityObject}s.
 *
 * @param <T>
 */
public abstract class AbstractCityObjectHandler<T extends AbstractCityObject>
        extends AbstractFeatureHandler<T> implements TreeNodeHandler<T>, Constants {

    private static GenericAttributeHandler<AbstractGenericAttribute> genericAttributeHandler = new GenericAttributeHandler<>();
    private static GregorianCalendarHandler<GregorianCalendar> gregorianCalendarHandler = new GregorianCalendarHandler<>();
    private static ExternalReferenceHandler<ExternalReference> externalReferenceHandler = new ExternalReferenceHandler<>();
    private static GeneralizationRelationHandler<GeneralizationRelation> generalizationRelationHandler = new GeneralizationRelationHandler<>();
    private static RelativeToTerrainHandler relativeToTerrainHandler = new RelativeToTerrainHandler();
    private static RelativeToWaterHandler relativeToWaterHandler = new RelativeToWaterHandler();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        super.addChildsToNode(node);

        T element = node.getValue();

        GregorianCalendar creationDate = element.getCreationDate();
        if (creationDate != null) {
            String signature = Utils.buildSignature(node, creationDate, CREATION_DATE);
            ElementNode<GregorianCalendar> creationNode = new ElementNode<>(
                    creationDate, CREATION_DATE, signature, node);
            gregorianCalendarHandler.addChildsToNode(creationNode);
            node.addChild(creationNode);
        }

        GregorianCalendar terminationDate = element.getTerminationDate();
        if (terminationDate != null) {
            String signature = Utils.buildSignature(node, terminationDate, TERMINATION_DATE);
            ElementNode<GregorianCalendar> terminationNode = new ElementNode<>(
                    terminationDate, TERMINATION_DATE, signature, node);
            gregorianCalendarHandler.addChildsToNode(terminationNode);
            node.addChild(terminationNode);
        }

        List<ExternalReference> externalReference = element
                .getExternalReference();
        if (externalReference != null) {
            addExternalReferences(externalReference, node);
        }

        List<GeneralizationRelation> generalizesTo = element.getGeneralizesTo();
        if (generalizesTo != null) {
            addGeneralizesTo(generalizesTo, node);
        }

        RelativeToTerrain relativeToTerrain = element.getRelativeToTerrain();
        if (relativeToTerrain != null) {
            String signature = Utils.buildSignature(node, relativeToTerrain, RELATIVE_TO_TERRAIN);
            ElementNode<RelativeToTerrain> relativeToTerrainElementNode = new ElementNode<>(
                    relativeToTerrain, RELATIVE_TO_TERRAIN, signature, node);
            relativeToTerrainHandler
                    .addChildsToNode(relativeToTerrainElementNode);
            node.addChild(relativeToTerrainElementNode);
        }

        RelativeToWater relativeToWater = element.getRelativeToWater();
        if (relativeToWater != null) {
            String signature = Utils.buildSignature(node, relativeToWater, RELATIVE_TO_WATER);
            ElementNode<RelativeToWater> relativeToWaterElementNode = new ElementNode<>(
                    relativeToWater, RELATIVE_TO_WATER, signature, node);
            relativeToWaterHandler.addChildsToNode(relativeToWaterElementNode);
            node.addChild(relativeToWaterElementNode);
        }

        List<ADEComponent> genericApplicationPropertyOfCityObject = element
                .getGenericApplicationPropertyOfCityObject();

        List<AbstractGenericAttribute> genericAttributes = element
                .getGenericAttribute();
        if (genericAttributes != null) {
            for (AbstractGenericAttribute genAttr : genericAttributes) {
                String name = genAttr.getName();
                String signature = Utils.buildSignature(node, genAttr, name);
                ElementNode<AbstractGenericAttribute> genAttrNode = new ElementNode<>(
                        genAttr, name, signature, node);
                genericAttributeHandler.addChildsToNode(genAttrNode);
                node.addChild(genAttrNode);
            }
        }

    }

    private void addGeneralizesTo(List<GeneralizationRelation> generalizesTo,
                                  ElementNode<T> node) {
        for (GeneralizationRelation generalizationRelation : generalizesTo) {
            String signature = Utils.buildSignature(node, generalizationRelation, GENERALIZES_TO);
            ElementNode<GeneralizationRelation> generalizationRelationElementNode = new ElementNode<>(
                    generalizationRelation, GENERALIZES_TO, signature, node);
            generalizationRelationHandler
                    .addChildsToNode(generalizationRelationElementNode);
            node.addChild(generalizationRelationElementNode);
        }
    }

    private void addExternalReferences(
            List<ExternalReference> externalReferences, ElementNode<T> node) {
        for (ExternalReference er : externalReferences) {
            String signature = Utils.buildSignature(node, er, EXTERNAL_REFERENCE);
            ElementNode<ExternalReference> externalReferenceNode = new ElementNode<>(
                    er, EXTERNAL_REFERENCE, signature, node);
            externalReferenceHandler.addChildsToNode(externalReferenceNode);
            node.addChild(externalReferenceNode);
        }

    }

}
