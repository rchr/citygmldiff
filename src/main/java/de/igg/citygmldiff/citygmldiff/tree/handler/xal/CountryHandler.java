package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class CountryHandler<T extends Country> extends AddressLinesProcessor<T> {

    private static LocalityHandler<Locality> localityHandler = new LocalityHandler<>();
    private static CountryNameHandler<CountryName> countryNameHandler = new CountryNameHandler<>();
    private static CountryNameCodeHandler<CountryNameCode> countryNameCodeHandler = new CountryNameCodeHandler<>();
    private static AdministrativeAreaHandler<AdministrativeArea> administrativeAreaHandler = new AdministrativeAreaHandler<>();
    private static ThoroughfareHandler<Thoroughfare> thoroughfareHandler = new ThoroughfareHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        List<AddressLine> addressLine = element.getAddressLine();
        processAddressLines(addressLine, node);

        List<CountryName> countryNames = element.getCountryName();
        processCountryNames(countryNames, node);

        List<CountryNameCode> countryNameCodes = element.getCountryNameCode();
        processCountryNameCodes(countryNameCodes, node);

        AdministrativeArea administrativeArea = element.getAdministrativeArea();
        if (administrativeArea != null) {
            ElementNode<AdministrativeArea> adminAreaNode = NodeFactory
                    .createElementNode(administrativeArea, ADMINISTRATIVE_AREA,
                            node);
            administrativeAreaHandler.addChildsToNode(adminAreaNode);
            node.addChild(adminAreaNode);
        }

        Locality locality = element.getLocality();
        if (locality != null) {
            ElementNode<Locality> localityNode = NodeFactory.createElementNode(
                    locality, LOCALITY, node);
            localityHandler.addChildsToNode(localityNode);
            node.addChild(localityNode);
        }

        Thoroughfare thoroughfare = element.getThoroughfare();
        if (thoroughfare != null) {
            ElementNode<Thoroughfare> thoroughfareNode = NodeFactory
                    .createElementNode(thoroughfare, THOROUGHFARE, node);
            thoroughfareHandler.addChildsToNode(thoroughfareNode);
            node.addChild(thoroughfareNode);
        }

    }

    private void processCountryNameCodes(
            List<CountryNameCode> countryNameCodes, ElementNode<T> node) {
        if (countryNameCodes != null) {
            for (CountryNameCode countryNameCode : countryNameCodes) {
                ElementNode<CountryNameCode> codeNode = NodeFactory
                        .createElementNode(countryNameCode, COUNTRY_NAME_CODE,
                                node);
                countryNameCodeHandler.addChildsToNode(codeNode);
                node.addChild(codeNode);
            }
        }
    }

    private void processCountryNames(List<CountryName> countryNames,
                                     ElementNode<T> node) {
        if (countryNames != null) {
            for (CountryName countryName : countryNames) {
                ElementNode<CountryName> nameNode = NodeFactory.createElementNode(
                        countryName, COUNTRY_NAME, node);
                countryNameHandler.addChildsToNode(nameNode);
                node.addChild(nameNode);
            }
        }
    }

}
