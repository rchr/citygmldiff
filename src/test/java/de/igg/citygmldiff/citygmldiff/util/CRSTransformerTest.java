package de.igg.citygmldiff.citygmldiff.util;

import com.vividsolutions.jts.geom.Envelope;
import org.citygml4j.geometry.BoundingBox;
import org.citygml4j.geometry.Point;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: richard
 * Date: 05.02.14
 * Time: 10:40
 */
public class CRSTransformerTest {

    private static final String SRS_4326 = "urn:ogc:def:crs:EPSG::4326";
    private static final String SRS_32633 = "urn:ogc:def:crs:EPSG::32633";
    private Envelope envelope4326;
    private Envelope envelope32633;

    @Before
    public void setUp() {
        Point lowerCorner1 = new Point(13.3269637686746, 52.5321790563424, 74.6634593922645); // E:386516, N:5821547 - 33U WGS84
        Point upperCorner1 = new Point(13.3274412933907, 52.5323175874323, 90.3453739183024); // E:386549, N:5821561- 33U WGS84

        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        org.citygml4j.model.gml.geometry.primitives.Envelope cityGMLEnvelope4326 = new org.citygml4j.model.gml.geometry.primitives.Envelope(bBox1);
        cityGMLEnvelope4326.setSrsDimension(3);
        cityGMLEnvelope4326.setSrsName(SRS_4326);
        envelope4326 = CityGMLToJTS.toJtsEnvelope(cityGMLEnvelope4326);

        Point lowerCorner2 = new Point(5821547, 386516, 74.6634593922645); // E:386516, N:5821547 - 33U WGS84
        Point upperCorner2 = new Point(5821561, 386549, 90.3453739183024); // E:386549, N:5821561- 33U WGS84

        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        org.citygml4j.model.gml.geometry.primitives.Envelope cityGMLEnvelope32633 = new org.citygml4j.model.gml.geometry.primitives.Envelope(bBox2);
        cityGMLEnvelope32633.setSrsDimension(3);
        cityGMLEnvelope32633.setSrsName(SRS_32633);
        envelope32633 = CityGMLToJTS.toJtsEnvelope(cityGMLEnvelope32633);
    }

    @Test
    public void testTransform1() {
        Envelope transformed = CRSTransformer.transform(envelope4326, SRS_4326, SRS_32633);
        assertEquals(envelope32633.getMaxX(), transformed.getMaxX(), 1.5);
        assertEquals(envelope32633.getMinX(), transformed.getMinX(), 1.5);
        assertEquals(envelope32633.getMaxY(), transformed.getMaxY(), 1.5);
        assertEquals(envelope32633.getMinY(), transformed.getMinY(), 1.5);
    }

    @Test
    public void testTransform2() {
        Envelope transformed = CRSTransformer.transform(envelope32633, SRS_32633, SRS_4326);
        assertEquals(envelope4326.getMaxX(), transformed.getMaxX(), 0.1);
        assertEquals(envelope4326.getMinX(), transformed.getMinX(), 0.1);
        assertEquals(envelope4326.getMaxY(), transformed.getMaxY(), 0.1);
        assertEquals(envelope4326.getMinY(), transformed.getMinY(), 0.1);
    }

    @Test
    public void testTransformNullCrs() {
        Envelope transformed = CRSTransformer.transform(envelope4326, null, null);
        assertEquals(envelope4326, transformed);
    }

    @Test
    public void testTransformEmptyCrs() {
        Envelope transformed = CRSTransformer.transform(envelope4326, SRS_4326, "");
        assertEquals(envelope4326, transformed);
    }

    @Test
    public void testTransformEqualCrs() {
        Envelope transformed = CRSTransformer.transform(envelope4326, SRS_4326, SRS_4326);
        assertEquals(envelope4326, transformed);
    }

}
