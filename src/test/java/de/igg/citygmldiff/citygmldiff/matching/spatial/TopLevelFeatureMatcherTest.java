package de.igg.citygmldiff.citygmldiff.matching.spatial;

import citygmldiff.util.TestInstances;
import de.igg.citygmldiff.citygmldiff.matching.model.MatchingState;
import junit.framework.TestCase;
import org.citygml4j.geometry.BoundingBox;
import org.citygml4j.geometry.Point;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.gml.feature.BoundingShape;
import org.citygml4j.model.gml.geometry.primitives.Envelope;
import org.junit.Test;

/**
 * Created by richard on 01.04.14.
 */
public class TopLevelFeatureMatcherTest extends TestCase {

    @Test
    public void testAreEnvelopesMatching1() throws Exception {
        Building b1 = new Building();
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        BoundingShape boundingShape1 = new BoundingShape(bBox1);
        b1.setBoundedBy(boundingShape1);

        Building b2 = new Building();
        BoundingBox bBox2 = new BoundingBox(lowerCorner1, upperCorner1);
        BoundingShape boundingShape2 = new BoundingShape(bBox2);
        b2.setBoundedBy(boundingShape2);

        MatchingState matchingState = TopLevelFeatureMatcher.areEnvelopesMatching(b1, b2);
        assertEquals(MatchingState.MATCHING, matchingState);
    }

    @Test
    public void testAreEnvelopesMatching2() throws Exception {
        Building b1 = new Building();
        Point lowerCorner1 = new Point(-1, -1, -1);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        BoundingShape boundingShape1 = new BoundingShape(bBox1);
        b1.setBoundedBy(boundingShape1);

        Building b2 = new Building();
        BoundingBox bBox2 = new BoundingBox(lowerCorner1, upperCorner1);
        BoundingShape boundingShape2 = new BoundingShape(bBox2);
        b2.setBoundedBy(boundingShape2);

        MatchingState matchingState = TopLevelFeatureMatcher.areEnvelopesMatching(b1, b2);
        assertEquals(MatchingState.MATCHING, matchingState);
    }

    @Test
    public void testAreEnvelopesMatching3() throws Exception {
        Building b1 = new Building();
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        BoundingShape boundingShape1 = new BoundingShape(bBox1);
        b1.setBoundedBy(boundingShape1);

        Building b2 = new Building();
        Point lowerCorner2 = new Point(0.25, 0, 0);
        Point upperCorner2 = new Point(1.25, 1, 1);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        BoundingShape boundingShape2 = new BoundingShape(bBox2);
        b2.setBoundedBy(boundingShape2);

        MatchingState matchingState = TopLevelFeatureMatcher.areEnvelopesMatching(b1, b2);
        assertEquals(MatchingState.MATCHING, matchingState);
    }

    @Test
    public void testAreEnvelopesMatching4() throws Exception {
        Building b1 = new Building();
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        BoundingShape boundingShape1 = new BoundingShape(bBox1);
        b1.setBoundedBy(boundingShape1);

        Building b2 = new Building();
        Point lowerCorner2 = new Point(0, 0, 0.25);
        Point upperCorner2 = new Point(1, 1, 1.25);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        BoundingShape boundingShape2 = new BoundingShape(bBox2);
        b2.setBoundedBy(boundingShape2);

        MatchingState matchingState = TopLevelFeatureMatcher.areEnvelopesMatching(b1, b2);
        assertEquals(MatchingState.MATCHING, matchingState);
    }

    @Test
    public void testAreEnvelopesMatchingFail1() throws Exception {
        Building b1 = new Building();
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        BoundingShape boundingShape1 = new BoundingShape(bBox1);
        b1.setBoundedBy(boundingShape1);

        Building b2 = new Building();
        Point lowerCorner2 = new Point(0.33, 0, 0);
        Point upperCorner2 = new Point(1.33, 1, 1);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        BoundingShape boundingShape2 = new BoundingShape(bBox2);
        b2.setBoundedBy(boundingShape2);

        MatchingState matchingState = TopLevelFeatureMatcher.areEnvelopesMatching(b1, b2);
        assertEquals(MatchingState.NOT_MATCHING, matchingState);
    }

    @Test
    public void testAreEnvelopesMatchingFail2() throws Exception {
        Building b1 = new Building();
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        BoundingShape boundingShape1 = new BoundingShape(bBox1);
        b1.setBoundedBy(boundingShape1);

        Building b2 = new Building();
        Point lowerCorner2 = new Point(0, 0, 0.33);
        Point upperCorner2 = new Point(1, 1, 1.33);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        BoundingShape boundingShape2 = new BoundingShape(bBox2);
        b2.setBoundedBy(boundingShape2);

        MatchingState matchingState = TopLevelFeatureMatcher.areEnvelopesMatching(b1, b2);
        assertEquals(MatchingState.NOT_MATCHING, matchingState);
    }

    @Test
    public void testAreEnvelopesMatchingFail3() throws Exception {
        Building b1 = new Building();
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        BoundingShape boundingShape1 = new BoundingShape(bBox1);
        b1.setBoundedBy(boundingShape1);

        Building b2 = new Building();
        Point lowerCorner2 = new Point(2, 2, 0);
        Point upperCorner2 = new Point(3, 3, 1);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        BoundingShape boundingShape2 = new BoundingShape(bBox2);
        b2.setBoundedBy(boundingShape2);

        MatchingState matchingState = TopLevelFeatureMatcher.areEnvelopesMatching(b1, b2);
        assertEquals(MatchingState.NOT_MATCHING, matchingState);
    }

    @Test
    public void testAreEnvelopesMatchingFail4() throws Exception {
        Building b1 = new Building();
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        BoundingShape boundingShape1 = new BoundingShape(bBox1);
        b1.setBoundedBy(boundingShape1);

        Building b2 = new Building();
        Point lowerCorner2 = new Point(0, 0, 2);
        Point upperCorner2 = new Point(1, 1, 3);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        BoundingShape boundingShape2 = new BoundingShape(bBox2);
        b2.setBoundedBy(boundingShape2);

        MatchingState matchingState = TopLevelFeatureMatcher.areEnvelopesMatching(b1, b2);

        assertEquals(MatchingState.NOT_MATCHING, matchingState);
    }

    @Test
    public void testIsHeightMatching1() {
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(bBox1);
        envelope1.setSrsDimension(3);
        Envelope envelope2 = new Envelope(bBox1);
        envelope2.setSrsDimension(3);
        MatchingState heightMatching = TopLevelFeatureMatcher.isHeightMatching(envelope1, envelope2);
        assertEquals(MatchingState.MATCHING, heightMatching);
    }

    @Test
    public void testIsHeightMatching2() {
        Point lowerCorner1 = new Point(-1, -1, -1);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(bBox1);
        envelope1.setSrsDimension(3);
        Envelope envelope2 = new Envelope(bBox1);
        envelope2.setSrsDimension(3);
        MatchingState heightMatching = TopLevelFeatureMatcher.isHeightMatching(envelope1, envelope2);
        assertEquals(MatchingState.MATCHING, heightMatching);
    }

    @Test
    public void testIsHeightMatching3() {
        Point lowerCorner1 = new Point(-1, -1, 0);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(bBox1);
        envelope1.setSrsDimension(3);

        Point lowerCorner2 = new Point(0, 0, 0.25);
        Point upperCorner2 = new Point(0, 0, 1.25);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        Envelope envelope2 = new Envelope(bBox2);
        envelope2.setSrsDimension(3);

        MatchingState heightMatching = TopLevelFeatureMatcher.isHeightMatching(envelope1, envelope2);
        assertEquals(MatchingState.MATCHING, heightMatching);
    }

    @Test
    public void testIsHeightMatchingFail1() {
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(bBox1);
        envelope1.setSrsDimension(3);

        Point lowerCorner2 = new Point(0, 0, 0.33);
        Point upperCorner2 = new Point(1, 1, 1.33);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        Envelope envelope2 = new Envelope(bBox2);
        envelope2.setSrsDimension(3);

        MatchingState heightMatching = TopLevelFeatureMatcher.isHeightMatching(envelope1, envelope2);
        assertEquals(MatchingState.NOT_MATCHING, heightMatching);
    }

    @Test
    public void testIsHeightMatchingFail2() {
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(bBox1);
        envelope1.setSrsDimension(3);

        Point lowerCorner2 = new Point(2, 2, 2);
        Point upperCorner2 = new Point(2, 2, 3);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        Envelope envelope2 = new Envelope(bBox2);
        envelope2.setSrsDimension(3);

        MatchingState heightMatching = TopLevelFeatureMatcher.isHeightMatching(envelope1, envelope2);
        assertEquals(MatchingState.NOT_MATCHING, heightMatching);
    }

    @Test
    public void testIsHeightMatchingFalse3() {
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 0);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(bBox1);
        envelope1.setSrsDimension(3);

        Point lowerCorner2 = new Point(2, 2, 2);
        Point upperCorner2 = new Point(2, 2, 3);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        Envelope envelope2 = new Envelope(bBox2);
        envelope2.setSrsDimension(3);

        MatchingState heightMatching = TopLevelFeatureMatcher.isHeightMatching(envelope1, envelope2);
        assertEquals(MatchingState.NOT_MATCHING, heightMatching);
    }

    @Test
    public void testIsHeightMatchingFalse4() {
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 0);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(bBox1);
        envelope1.setSrsDimension(3);

        Point lowerCorner2 = new Point(2, 2, 2);
        Point upperCorner2 = new Point(2, 2, 2);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        Envelope envelope2 = new Envelope(bBox2);
        envelope2.setSrsDimension(3);

        MatchingState heightMatching = TopLevelFeatureMatcher.isHeightMatching(envelope1, envelope2);
        assertEquals(MatchingState.NOT_MATCHING, heightMatching);
    }

    @Test
    public void testIsHeightMatching4() {
        Point lowerCorner1 = new Point(0, 0, 1);
        Point upperCorner1 = new Point(1, 1, 1);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(bBox1);
        envelope1.setSrsDimension(3);

        Point lowerCorner2 = new Point(2, 2, 0);
        Point upperCorner2 = new Point(2, 2, 2);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        Envelope envelope2 = new Envelope(bBox2);
        envelope2.setSrsDimension(3);

        MatchingState heightMatching = TopLevelFeatureMatcher.isHeightMatching(envelope1, envelope2);
        assertEquals(MatchingState.NOT_MATCHING, heightMatching);
    }

    @Test
    public void testIsHeightMatching5() {
        Point lowerCorner1 = new Point(0, 0, 0);
        Point upperCorner1 = new Point(1, 1, 2);
        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(bBox1);
        envelope1.setSrsDimension(3);

        Point lowerCorner2 = new Point(2, 2, 1);
        Point upperCorner2 = new Point(2, 2, 1);
        BoundingBox bBox2 = new BoundingBox(lowerCorner2, lowerCorner2);
        Envelope envelope2 = new Envelope(bBox2);
        envelope2.setSrsDimension(3);

        MatchingState heightMatching = TopLevelFeatureMatcher.isHeightMatching(envelope1, envelope2);
        assertEquals(MatchingState.MATCHING, heightMatching);
    }

    @Test
    public void testAreEnvelopesMatchingOnCrsChange() {
        Point lowerCorner1 = new Point(13.3269637686746, 52.5321790563424, 74.6634593922645); // E:386516, N:5821547 - 33U WGS84
        Point upperCorner1 = new Point(13.3274412933907, 52.5323175874323, 90.3453739183024); // E:386549, N:5821561- 33U WGS84

        BoundingBox bBox1 = new BoundingBox(lowerCorner1, upperCorner1);
        Envelope envelope1 = new Envelope(bBox1);
        envelope1.setSrsDimension(3);
        envelope1.setSrsName("urn:ogc:def:crs:EPSG::4326");
        BoundingShape boundingShape1 = new BoundingShape(envelope1);
        Building b1 = new Building();
        b1.setBoundedBy(boundingShape1);

        Point lowerCorner2 = new Point(5821547, 386516, 74.6634593922645); // E:386516, N:5821547 - 33U WGS84
        Point upperCorner2 = new Point(5821561, 386549, 90.3453739183024); // E:386549, N:5821561- 33U WGS84

        BoundingBox bBox2 = new BoundingBox(lowerCorner2, upperCorner2);
        Envelope envelope2 = new Envelope(bBox2);
        envelope2.setSrsDimension(3);
        envelope2.setSrsName("urn:ogc:def:crs:EPSG::32633");
        BoundingShape boundingShape2 = new BoundingShape(envelope2);
        Building b2 = new Building();
        b2.setBoundedBy(boundingShape2);

        MatchingState matchingState = TopLevelFeatureMatcher.areAbstractFeaturesMatching(b1, b2);

        assertEquals(MatchingState.MATCHING, matchingState);
    }

    @Test
    public void testFullBuilding() {
        Building buildingA = TestInstances.createBuilding("A");
        Building buildingB = TestInstances.createBuilding("B");
        MatchingState matchingState = TopLevelFeatureMatcher.areAbstractFeaturesMatching(buildingA, buildingB);
        assertEquals(MatchingState.MATCHING, matchingState);
    }
}
