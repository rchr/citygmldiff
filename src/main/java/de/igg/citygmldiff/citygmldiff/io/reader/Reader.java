package de.igg.citygmldiff.citygmldiff.io.reader;

import org.citygml4j.CityGMLContext;
import org.citygml4j.builder.CityGMLBuilder;
import org.citygml4j.model.citygml.CityGML;
import org.citygml4j.model.citygml.CityGMLClass;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.xml.io.CityGMLInputFactory;
import org.citygml4j.xml.io.reader.CityGMLReadException;
import org.citygml4j.xml.io.reader.CityGMLReader;
import org.citygml4j.xml.schema.SchemaHandler;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private CityGMLInputFactory inFactory;

    public Reader() {
        init();
    }

    private void init() {
        try {
            CityGMLContext ctx = new CityGMLContext();
            CityGMLBuilder builder = ctx.createCityGMLBuilder();
            inFactory = builder.createCityGMLInputFactory();
        } catch (JAXBException | CityGMLReadException e) {
            throw new RuntimeException("Error while creating reader.", e);
        }
    }

    public List<CityModel> getCityModelsFromInputStream(InputStream is) {
        List<CityModel> cityModels;
        try {
            CityGMLReader reader = inFactory.createCityGMLReader("is", is);
            cityModels = getCityModelsOfReader(reader);
        } catch (CityGMLReadException e) {
            throw new RuntimeException("Error while reading file: "
                    + is.toString(), e);
        }
        return cityModels;
    }

    public List<CityModel> getCityModelsFromFile(File cityGMLFile) {
        List<CityModel> cityModels;
        try {
            CityGMLReader reader = inFactory.createCityGMLReader(cityGMLFile);
            cityModels = getCityModelsOfReader(reader);
        } catch (CityGMLReadException e) {
            throw new RuntimeException("Error while reading file: "
                    + cityGMLFile.getName(), e);
        }
        return cityModels;
    }

    private List<CityModel> getCityModelsOfReader(CityGMLReader reader)
            throws CityGMLReadException {
        List<CityModel> cityModels = new ArrayList<>();
        while (reader.hasNext()) {
            CityGML citygml = reader.nextFeature();
            if (citygml.getCityGMLClass() == CityGMLClass.CITY_MODEL) {
                cityModels.add((CityModel) citygml);
            }
        }
        return cityModels;
    }

    public SchemaHandler getInputSchemaHandler() {
        return inFactory.getSchemaHandler();
    }

}
