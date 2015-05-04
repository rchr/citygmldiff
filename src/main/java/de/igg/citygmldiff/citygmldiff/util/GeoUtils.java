package de.igg.citygmldiff.citygmldiff.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import org.apache.log4j.Logger;
import org.citygml4j.model.citygml.CityGMLClass;
import org.citygml4j.model.citygml.building.AbstractBoundarySurface;
import org.citygml4j.model.citygml.building.BoundarySurfaceProperty;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.gml.feature.AbstractFeature;
import org.citygml4j.model.gml.geometry.SRSReferenceGroup;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurface;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurfaceProperty;
import org.citygml4j.model.gml.geometry.primitives.DirectPosition;
import org.citygml4j.model.gml.geometry.primitives.LinearRing;
import org.citygml4j.model.gml.geometry.primitives.Polygon;
import org.citygml4j.model.gml.geometry.primitives.SurfaceProperty;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.GeographicCRS;

import java.util.ArrayList;
import java.util.List;

/**
 * User: richard
 * Date: 07.02.14
 * Time: 09:55
 * <p/>
 * Holds some util methods for geometries.
 */
public class GeoUtils {

    private static final Logger LOGGER = Logger.getLogger(GeoUtils.class);
    private static final double A = 6378137; //m
    private static final double E_SQUARE = 0.006694379990;
    private static GeometryFactory geometryFactory = new GeometryFactory();

    /**
     * Extracts the envelopes of both {@link org.citygml4j.model.gml.feature.AbstractFeature}s and transforms them into {@link com.vividsolutions.jts.geom.Envelope}s.
     *
     * @param abstractFeature1
     * @param abstractFeature2
     * @return Envelope[]. abstractFeature1's envelope at index 0, abstractFeature2's envelope at index 1.
     */
    public static com.vividsolutions.jts.geom.Envelope[] extractEnvelopes(AbstractFeature abstractFeature1, AbstractFeature abstractFeature2) {

        com.vividsolutions.jts.geom.Envelope[] envelopes = new Envelope[2];
        org.citygml4j.model.gml.geometry.primitives.Envelope abstractFeatureEnvelope1 = abstractFeature1.getBoundedBy().getEnvelope();
        org.citygml4j.model.gml.geometry.primitives.Envelope abstractFeatureEnvelope2 = abstractFeature2.getBoundedBy().getEnvelope();

        com.vividsolutions.jts.geom.Envelope envelope1 = CityGMLToJTS.toJtsEnvelope(abstractFeatureEnvelope1);
        com.vividsolutions.jts.geom.Envelope envelope2 = CityGMLToJTS.toJtsEnvelope(abstractFeatureEnvelope2);

        String srsName1 = getSrsName(abstractFeatureEnvelope1);
        String srsName2 = getSrsName(abstractFeatureEnvelope2);

        envelope1 = CRSTransformer.transform(envelope1, srsName1, srsName2);

        envelopes[0] = envelope1;
        envelopes[1] = envelope2;
        return envelopes;
    }

    /**
     * Extracts and returns the srs name of the given {@link com.vividsolutions.jts.geom.Envelope}.
     *
     * @param geomtery
     * @return
     */
    public static String getSrsName(SRSReferenceGroup geomtery) {
        String srsName = geomtery.getSrsName();
        if (srsName == null || srsName.length() == 0) {
            srsName = geomtery.getInheritedSrsName();
        }
        if (srsName.contains(",")) {
            srsName = srsName.split(",")[0];
        }
        return srsName;
    }

    public static CoordinateReferenceSystem decodeSrsName(String srsName) {
        CoordinateReferenceSystem crs = null;
        try {
            crs = CRS.decode(srsName);
        } catch (FactoryException e) {
            e.printStackTrace();
        }
        return crs;
    }

    /**
     * Extracts {@link org.citygml4j.model.gml.geometry.primitives.Envelope}'s height values as 2D {@link com.vividsolutions.jts.geom.LineString}.
     *
     * @param envelope
     * @return
     */
    public static LineString extractHeightInterval(org.citygml4j.model.gml.geometry.primitives.Envelope envelope) {
        if (envelope.getSrsDimension() < 3) {
            return null;
        }

        Double[] minMax = extractMinMaxZCoordinate(envelope);
        Coordinate min = new Coordinate(minMax[0], 0);
        Coordinate max = new Coordinate(minMax[1], 0);
        Coordinate[] minMaxCoordinated = {min, max};
        return geometryFactory.createLineString(minMaxCoordinated);
    }

    /**
     * Extracts the min and max Z-coordinate of the given {@link com.vividsolutions.jts.geom.Envelope}.
     * Min is at index 0, Max at index 1.
     *
     * @param envelope
     * @return
     */
    private static Double[] extractMinMaxZCoordinate(org.citygml4j.model.gml.geometry.primitives.Envelope envelope) {
        DirectPosition lowerCorner = envelope.getLowerCorner();
        Double lowerCornerZValue = extractZCoordinate(lowerCorner);
        DirectPosition upperCorner = envelope.getUpperCorner();
        Double upperCornerZValue = extractZCoordinate(upperCorner);
        Double[] result = new Double[2];
        result[0] = lowerCornerZValue;
        result[1] = upperCornerZValue;
        return result;
    }

    /**
     * Extracts and returns the z-coordinate of the given {@link org.citygml4j.model.gml.geometry.primitives.DirectPosition}.
     *
     * @param position
     * @return
     */
    private static Double extractZCoordinate(DirectPosition position) {
        List<Double> coordinates = position.getValue();
        return coordinates.get(2);
    }


    /**
     * Extracts and return the {@link org.citygml4j.model.citygml.building.BoundarySurfaceProperty} specified by the {@link org.citygml4j.model.citygml.CityGMLClass }of the given {@link org.citygml4j.model.citygml.building.Building}.
     *
     * @param building       Extracts the {@link org.citygml4j.model.citygml.building.BoundarySurfaceProperty} from this {@link org.citygml4j.model.citygml.building.Building}.
     * @param wallIdentifier Identifies the class of the extracted {@link org.citygml4j.model.citygml.building.BoundarySurfaceProperty} (e.g. {@link org.citygml4j.model.citygml.CityGMLClass#BUILDING_WALL_SURFACE}, {@link org.citygml4j.model.citygml.CityGMLClass#BUILDING_GROUND_SURFACE}, or {@link org.citygml4j.model.citygml.CityGMLClass#BUILDING_ROOF_SURFACE}).
     * @return
     */
    public static BoundarySurfaceProperty getBoundarySurfaceProperty(Building building, CityGMLClass wallIdentifier) {

        CityGMLClass wallClass;
        if (wallIdentifier == CityGMLClass.BUILDING_WALL_SURFACE) {
            wallClass = CityGMLClass.BUILDING_WALL_SURFACE;
        } else if (wallIdentifier == CityGMLClass.BUILDING_ROOF_SURFACE) {
            wallClass = CityGMLClass.BUILDING_ROOF_SURFACE;
        } else if (wallIdentifier == CityGMLClass.BUILDING_GROUND_SURFACE) {
            wallClass = CityGMLClass.BUILDING_GROUND_SURFACE;
        } else {
            LOGGER.error("No valid wallIdentifier: " + wallIdentifier.toString());
            return null;
        }

        List<BoundarySurfaceProperty> boundarySurfaceProperties = building.getBoundedBySurface();
        for (BoundarySurfaceProperty bSP : boundarySurfaceProperties) {
            CityGMLClass surfaceClass = bSP.getBoundarySurface().getCityGMLClass();
            if (surfaceClass.equals(wallClass)) {
                return bSP;
            }
        }
        return null;
    }

    public static List<LinearRing> toLinearRing(BoundarySurfaceProperty boundarySurfaceProperty) {
        if (boundarySurfaceProperty == null) {
            return null;
        }
        AbstractBoundarySurface boundarySurface = boundarySurfaceProperty.getBoundarySurface();
        if (boundarySurface == null) {
            return null;
        }
        MultiSurfaceProperty multiSurfaceProperty = boundarySurface.getLod2MultiSurface();
        if (multiSurfaceProperty == null) {
            multiSurfaceProperty = boundarySurface.getLod3MultiSurface();
        }
        if (multiSurfaceProperty == null) {
            multiSurfaceProperty = boundarySurface.getLod4MultiSurface();
        }
        if (multiSurfaceProperty == null) {
            return null;
        }
        MultiSurface multiSurface = multiSurfaceProperty.getGeometry();
        if (multiSurface == null) {
            return null;
        }
        List<SurfaceProperty> surfaceMember = multiSurface.getSurfaceMember();
        if (surfaceMember == null) {
            return null;
        }

        List<LinearRing> linearRings = new ArrayList<>();
        for (SurfaceProperty surfaceProp : surfaceMember) {
            if (surfaceProp.getObject() instanceof org.citygml4j.model.gml.geometry.primitives.Polygon) {
                org.citygml4j.model.gml.geometry.primitives.Polygon p = (org.citygml4j.model.gml.geometry.primitives.Polygon) surfaceProp.getObject();
                linearRings.add((LinearRing) p.getExterior().getObject());
            }
        }
        return linearRings;
    }

    public static List<Double> calculateLowerCorner(Building b) {
        return b.getBoundedBy().getEnvelope().getLowerCorner().toList3d();
    }

    public static List<Double> calculateLowerLeftPoint(Polygon p) {
        LinearRing linearRing = (LinearRing) p.getExterior().getObject();
        List<Double> linearRingCoordinated = linearRing.getPosList().getValue();

        Double minX = linearRingCoordinated.get(0);
        Double minY = linearRingCoordinated.get(1);
        Double minZ = linearRingCoordinated.get(2);

        for (int i = 0; i < 6; i += 3) {
            if (linearRingCoordinated.get(i) < minX) {
                if (linearRingCoordinated.get(i + 1) < minY) {
                    if (linearRingCoordinated.get(i + 2) < minZ) {
                        minX = linearRingCoordinated.get(i);
                        minY = linearRingCoordinated.get(i + 1);
                        minZ = linearRingCoordinated.get(i + 2);
                    }
                }
            }
        }

        List<Double> result = new ArrayList<>();
        result.add(minX);
        result.add(minY);
        result.add(minZ);
        return result;
    }

    /**
     * Calculate normal vector of given {@link org.citygml4j.model.gml.geometry.primitives.Polygon}
     *
     * @param p
     * @return
     */
    public static List<Double> calculateNormalVectorForPolygon(Polygon p) {
        String srsName = getSrsName(p);
        CoordinateReferenceSystem coordinateReferenceSystem = decodeSrsName(srsName);
        boolean isGeographicCRS = isGeographicCRS(coordinateReferenceSystem);
        LinearRing linearRing = (LinearRing) p.getExterior().getObject();
        List<Double> linearRingCoordinated = linearRing.getPosList().getValue();
        return calculateNormalVectorForSurface(linearRingCoordinated, isGeographicCRS);
    }

    public static List<Double> calculateNormalVectorForSurface(List<Double> surface, boolean isGeographicCRS) {
        // get 3 random points of plane
        List<Double> planePoints = getThreeRandomPoints(surface);
        return calculateNormalVector(planePoints, isGeographicCRS);
    }

    private static boolean isGeographicCRS(CoordinateReferenceSystem crs) {
        return crs instanceof GeographicCRS;
    }

    /**
     * Calculates normal vector of plane defined by points
     *
     * @param planePoints
     * @return
     */
    public static List<Double> calculateNormalVector(List<Double> planePoints, boolean isGeographicCRS) {

        List<Double> points;
        if (isGeographicCRS) {
            points = transformToGeocentricCoordinates(planePoints);
        } else {
            points = planePoints;
        }

        List<Double> u = new ArrayList<>();
        u.add(points.get(3) - points.get(0));
        u.add(points.get(4) - points.get(1));
        u.add(points.get(5) - points.get(2));

        List<Double> v = new ArrayList<>();
        v.add(points.get(6) - points.get(0));
        v.add(points.get(7) - points.get(1));
        v.add(points.get(8) - points.get(2));

        List<Double> n = GeoUtils.crossProduct(u, v);
        double length = Math.sqrt(Math.pow(n.get(0), 2) + Math.pow(n.get(1), 2)
                + Math.pow(n.get(2), 2));

        List<Double> n0 = new ArrayList<>();
        for (Double d : n) {
            n0.add(d / length);
        }
        return n0;
    }

    private static List<Double> transformToGeocentricCoordinates(List<Double> planePoints) {
        List<Double> geocentricPoints = new ArrayList<>();

        double nu1 = calcNu(planePoints.get(1));
        double nu2 = calcNu(planePoints.get(4));
        double nu3 = calcNu(planePoints.get(7));

        List<Double> radiansPoints = toRadians(planePoints);

        geocentricPoints.add((nu1 + planePoints.get(2)) * Math.cos(radiansPoints.get(1)) * Math.cos(radiansPoints.get(0)));
        geocentricPoints.add((nu1 + planePoints.get(2)) * Math.cos(radiansPoints.get(1)) * Math.sin(radiansPoints.get(0)));
        geocentricPoints.add(((1 - E_SQUARE) * nu1 + planePoints.get(2)) * Math.sin(radiansPoints.get(1)));

        geocentricPoints.add((nu2 + planePoints.get(5)) * Math.cos(radiansPoints.get(4)) * Math.cos(radiansPoints.get(3)));
        geocentricPoints.add((nu2 + planePoints.get(5)) * Math.cos(radiansPoints.get(4)) * Math.sin(radiansPoints.get(3)));
        geocentricPoints.add(((1 - E_SQUARE) * nu2 + planePoints.get(5)) * Math.sin(radiansPoints.get(4)));

        geocentricPoints.add((nu3 + planePoints.get(8)) * Math.cos(radiansPoints.get(7)) * Math.cos(radiansPoints.get(6)));
        geocentricPoints.add((nu3 + planePoints.get(8)) * Math.cos(radiansPoints.get(7)) * Math.sin(radiansPoints.get(6)));
        geocentricPoints.add(((1 - E_SQUARE) * nu3 + planePoints.get(8)) * Math.sin(radiansPoints.get(7)));
        return geocentricPoints;
    }

    private static List<Double> transformPointToGeocentricCoordinates(List<Double> planePoints) {
        List<Double> geocentricPoints = new ArrayList<>();

        double nu1 = calcNu(planePoints.get(1));

        List<Double> radiansPoints = toRadians(planePoints);

        geocentricPoints.add((nu1 + planePoints.get(2)) * Math.cos(radiansPoints.get(1)) * Math.cos(radiansPoints.get(0)));
        geocentricPoints.add((nu1 + planePoints.get(2)) * Math.cos(radiansPoints.get(1)) * Math.sin(radiansPoints.get(0)));
        geocentricPoints.add(((1 - E_SQUARE) * nu1 + planePoints.get(2)) * Math.sin(radiansPoints.get(1)));
        return geocentricPoints;
    }

    private static List<Double> toRadians(List<Double> points) {
        List<Double> radiansPoints = new ArrayList<>();
        for (Double point : points) {
            radiansPoints.add(point * Math.PI / 180);
        }
        return radiansPoints;
    }

    private static double calcNu(double lat) {
        return A / Math.sqrt((1 - E_SQUARE * Math.pow(Math.sin(lat * Math.PI / 180), 2)));
    }

    /**
     * Get three points of input list.
     *
     * @param input
     * @return List<Double> Contains three points which are not on a line
     */
    public static List<Double> getThreeRandomPoints(List<Double> input) {
        List<Double> result = new ArrayList<>();

        // add first and second point
        for (int i = 0; i < 6; i += 3) {
            result.add(input.get(i));
            result.add(input.get(i + 1));
            result.add(input.get(i + 2));
        }

        int index = 6;
        while (result.size() < 9) {
            if ((input.get(index) - result.get(3) != 0)
                    || (input.get(index + 1) - result.get(4) != 0)
                    || (input.get(index + 2) - result.get(5) != 0)) {
                if ((input.get(index) - result.get(0) != 0)
                        || (input.get(index + 1) - result.get(1) != 0)) {
                    result.add(input.get(index));
                    result.add(input.get(index + 1));
                    result.add(input.get(index + 2));
                }
            }
            index += 3;
        }
        return result;
    }

    /**
     * Calculates cross product of two given vectors
     *
     * @param u
     * @param v
     * @return
     */
    public static List<Double> crossProduct(List<Double> u, List<Double> v) {
        List<Double> n = new ArrayList<>();
        n.add(u.get(1) * v.get(2) - u.get(2) * v.get(1));
        n.add(u.get(2) * v.get(0) - u.get(0) * v.get(2));
        n.add(u.get(0) * v.get(1) - u.get(1) * v.get(0));
        return n;
    }

    private static String determineSRSName(Polygon polygon) {
        String srsName = polygon.getSrsName();
        if (srsName == null) {
            srsName = polygon.getInheritedSrsName();
        }
        return srsName;
    }

    public static double calcLength(List<Double> vector) {
        return Math.sqrt(Math.pow(vector.get(0), 2) + Math.pow(vector.get(1), 2) + Math.pow(vector.get(2), 2));
    }

    public static List<Double> transformToGeocentricCoords(List<Double> point, String srsName) {
        List<Double> geocentricPoint = new ArrayList<>(3);

        CoordinateReferenceSystem coordinateReferenceSystem = decodeSrsName(srsName);
        boolean isGeographicCRS = isGeographicCRS(coordinateReferenceSystem);

        if (isGeographicCRS) {
            return transformPointToGeocentricCoordinates(point);
        } else {

            return point;
        }

    }
}
