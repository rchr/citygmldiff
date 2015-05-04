package de.igg.citygmldiff.citygmldiff.matching.spatial;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import de.igg.citygmldiff.citygmldiff.matching.model.MatchingState;
import de.igg.citygmldiff.citygmldiff.util.CRSTransformer;
import de.igg.citygmldiff.citygmldiff.util.CityGMLToJTS;
import de.igg.citygmldiff.citygmldiff.util.GeoUtils;
import org.citygml4j.model.citygml.CityGMLClass;
import org.citygml4j.model.citygml.building.BoundarySurfaceProperty;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.gml.base.AbstractGML;
import org.citygml4j.model.gml.feature.AbstractFeature;
import org.citygml4j.model.gml.geometry.primitives.LinearRing;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.util.List;

/**
 * Created by richard on 01.04.14.
 * <p/>
 * Matches top-level features by their footprint resp. envelope and height.
 */
public class TopLevelFeatureMatcher {

    private static final double INTERSECT_THRESHOLD = 0.5;
    private static final double INTERSECT_THRESHOLD_UNCERTAIN = 0.25;

    /**
     * Returns true, if the two top-level features are matching. False otherwise.
     *
     * @param abstractGML1
     * @param abstractGML2
     * @return
     */
    public static MatchingState areTopLevelFeaturesMatching(AbstractGML abstractGML1, AbstractGML abstractGML2) {
        if (abstractGML1 instanceof AbstractFeature && abstractGML2 instanceof AbstractFeature) {
            AbstractFeature abstrFeature1 = (AbstractFeature) abstractGML1;
            AbstractFeature abstrFeature2 = (AbstractFeature) abstractGML2;
            return areAbstractFeaturesMatching(abstrFeature1, abstrFeature2);
        }
        return MatchingState.NOT_MATCHING;
    }


    /**
     * Checks if the two {@link org.citygml4j.model.gml.feature.AbstractFeature}s are matching.
     * Both are matching, if
     * their dimensions are the same, and if
     * {@link #areEnvelopesMatching(org.citygml4j.model.gml.feature.AbstractFeature, org.citygml4j.model.gml.feature.AbstractFeature)} is true.
     *
     * @param abstractFeature1
     * @param abstractFeature2
     * @return
     */
    public static MatchingState areAbstractFeaturesMatching(AbstractFeature abstractFeature1, AbstractFeature abstractFeature2) {
        if (abstractFeature1 == null || abstractFeature2 == null) {
            return MatchingState.NOT_MATCHING;
        }
        if (abstractFeature1.getBoundedBy() != null && abstractFeature2.getBoundedBy() != null) {
            if (abstractFeature1.getClass() == abstractFeature2.getClass()) {
                Integer srsDimension1 = abstractFeature1.getBoundedBy().getEnvelope().getSrsDimension();
                Integer srsDimension2 = abstractFeature2.getBoundedBy().getEnvelope().getSrsDimension();
                if (srsDimension1.intValue() == srsDimension2.intValue()) {
                    return areAbstractFeaturesMatchingByGeometry(abstractFeature1, abstractFeature2);
                }
            }
        }
        return MatchingState.NOT_MATCHING;
    }

    /**
     * Checks if both {@link org.citygml4j.model.gml.feature.AbstractFeature}s are matching by their geometry.
     * Note: {@link org.citygml4j.model.citygml.building.Building}s are matched by their footprint. Other feature types by their envelopes.
     *
     * @param abstractFeature1
     * @param abstractFeature2
     * @return
     */
    public static MatchingState areAbstractFeaturesMatchingByGeometry(AbstractFeature abstractFeature1, AbstractFeature abstractFeature2) {

        if (!areEnvelopesIntersecting(abstractFeature1, abstractFeature2)) {
            return MatchingState.NOT_MATCHING;
        }

        if (abstractFeature1 instanceof Building && abstractFeature2 instanceof Building) {
            Building building1 = (Building) abstractFeature1;
            Building building2 = (Building) abstractFeature2;
            return areBuildingsMatching(building1, building2);
        } else {
            return areEnvelopesMatching(abstractFeature1, abstractFeature2);
        }

    }

    /**
     * Checks if the 2D envelopes of both {@link org.citygml4j.model.gml.feature.AbstractFeature}s are intersecting.
     * Returns true if both are intersecting, false otherwise.
     *
     * @param abstractFeature1
     * @param abstractFeature2
     * @return
     */
    private static boolean areEnvelopesIntersecting(AbstractFeature abstractFeature1, AbstractFeature abstractFeature2) {
        Envelope[] envelopes = GeoUtils.extractEnvelopes(abstractFeature1, abstractFeature2);
        com.vividsolutions.jts.geom.Envelope envelope1 = envelopes[0];
        com.vividsolutions.jts.geom.Envelope envelope2 = envelopes[1];

        return envelope1.intersects(envelope2);
    }

    /**
     * Checks if the two {@link org.citygml4j.model.citygml.building.Building}s are matching.
     * <p/>
     * Checks if the overlap of their footprint is >= {@link #INTERSECT_THRESHOLD} and overlap of bounding-box height values >= {@link #INTERSECT_THRESHOLD}.
     * Returns true if the {@link org.citygml4j.model.citygml.building.Building}s are matching, false otherwise.
     *
     * @param building1
     * @param building2
     * @return
     */
    public static MatchingState areBuildingsMatching(Building building1, Building building2) {
        // check footprint and height

        BoundarySurfaceProperty boundarySurfaceProperty1 = GeoUtils.getBoundarySurfaceProperty(building1, CityGMLClass.BUILDING_GROUND_SURFACE);
        BoundarySurfaceProperty boundarySurfaceProperty2 = GeoUtils.getBoundarySurfaceProperty(building2, CityGMLClass.BUILDING_GROUND_SURFACE);

        List<LinearRing> linearRings1 = GeoUtils.toLinearRing(boundarySurfaceProperty1);
        List<LinearRing> linearRings2 = GeoUtils.toLinearRing(boundarySurfaceProperty2);

        if (linearRings1 == null || linearRings2 == null || linearRings1.size() == 0 || linearRings2.size() == 0) {
            return areEnvelopesMatching(building1, building2);
        }

        String srsName1 = GeoUtils.getSrsName(linearRings1.get(0));
        CoordinateReferenceSystem crs1 = GeoUtils.decodeSrsName(srsName1);

        String srsName2 = GeoUtils.getSrsName(linearRings2.get(0));
        CoordinateReferenceSystem crs2 = GeoUtils.decodeSrsName(srsName2);

        Geometry polygon1 = CityGMLToJTS.toPolygon(linearRings1);
        Geometry polygon2 = CityGMLToJTS.toPolygon(linearRings2);

        if (!crs1.equals(crs2)) {
            // Transform first geometry into crs of second geometry.
            polygon1 = CRSTransformer.transform(polygon1, crs1, crs2);
        }

        double overlap1 = polygon1.intersection(polygon2).getArea() / polygon1.getArea();
        double overlap2 = polygon2.intersection(polygon1).getArea() / polygon2.getArea();

        if (overlap1 >= INTERSECT_THRESHOLD && overlap2 >= INTERSECT_THRESHOLD) {
            org.citygml4j.model.gml.geometry.primitives.Envelope abstractFeatureEnvelope1 = building1.getBoundedBy().getEnvelope();
            org.citygml4j.model.gml.geometry.primitives.Envelope abstractFeatureEnvelope2 = building2.getBoundedBy().getEnvelope();
            return isHeightMatching(abstractFeatureEnvelope1, abstractFeatureEnvelope2);
        } else if (overlap1 >= INTERSECT_THRESHOLD_UNCERTAIN && overlap2 >= INTERSECT_THRESHOLD_UNCERTAIN) {
            return MatchingState.UNCERTAIN;
        }

        return MatchingState.NOT_MATCHING;
    }

    /**
     * Checks if the envelopes of the {@link org.citygml4j.model.gml.feature.AbstractFeature}s are matching.
     * The envelopes are matching if
     * - they intersect,
     * - their overlap of heights is >= {@link #INTERSECT_THRESHOLD}, and
     * - overlap of 2D footprint is >= {@link #INTERSECT_THRESHOLD}.
     *
     * @param abstractFeature1
     * @param abstractFeature2
     * @return
     */
    public static MatchingState areEnvelopesMatching(AbstractFeature abstractFeature1, AbstractFeature abstractFeature2) {
        Envelope[] envelopes = GeoUtils.extractEnvelopes(abstractFeature1, abstractFeature2);
        com.vividsolutions.jts.geom.Envelope envelope1 = envelopes[0];
        com.vividsolutions.jts.geom.Envelope envelope2 = envelopes[1];
        if (!envelope1.intersects(envelope2)) {
            return MatchingState.NOT_MATCHING;
        }

        double area12 = envelope1.intersection(envelope2).getArea();
        double overlap1 = area12 / envelope1.getArea();
        double area21 = envelope2.intersection(envelope1).getArea();
        double overlap2 = area21 / envelope2.getArea();
        if (overlap1 >= INTERSECT_THRESHOLD || overlap2 >= INTERSECT_THRESHOLD) {
            org.citygml4j.model.gml.geometry.primitives.Envelope abstractFeatureEnvelope1 = abstractFeature1.getBoundedBy().getEnvelope();
            org.citygml4j.model.gml.geometry.primitives.Envelope abstractFeatureEnvelope2 = abstractFeature2.getBoundedBy().getEnvelope();
            return isHeightMatching(abstractFeatureEnvelope1, abstractFeatureEnvelope2);
        }
        return MatchingState.NOT_MATCHING;
    }

    /**
     * Checks if height of both {@link org.citygml4j.model.gml.geometry.primitives.Envelope}'s is matching.
     *
     * @param envelope1
     * @param envelope2
     * @return
     */
    public static MatchingState isHeightMatching(org.citygml4j.model.gml.geometry.primitives.Envelope envelope1, org.citygml4j.model.gml.geometry.primitives.Envelope envelope2) {
        LineString heightInterval1 = GeoUtils.extractHeightInterval(envelope1);
        LineString heightInterval2 = GeoUtils.extractHeightInterval(envelope2);
        double overlap12 = heightInterval1.intersection(heightInterval2).getLength();
        overlap12 = overlap12 / heightInterval1.getLength();
        double overlap21 = heightInterval2.intersection(heightInterval1).getLength();
        overlap21 = overlap21 / heightInterval2.getLength();

        if (overlap12 >= INTERSECT_THRESHOLD || overlap21 >= INTERSECT_THRESHOLD) {
            return MatchingState.MATCHING;
        } else {
            return MatchingState.NOT_MATCHING;
        }
    }
}
