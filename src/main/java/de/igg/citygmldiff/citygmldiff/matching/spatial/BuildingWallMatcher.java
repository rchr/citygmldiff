package de.igg.citygmldiff.citygmldiff.matching.spatial;

import de.igg.citygmldiff.citygmldiff.main.Main;
import de.igg.citygmldiff.citygmldiff.util.GeoUtils;
import org.apache.log4j.Logger;
import org.citygml4j.model.citygml.building.*;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurface;
import org.citygml4j.model.gml.geometry.aggregates.MultiSurfaceProperty;
import org.citygml4j.model.gml.geometry.primitives.AbstractSurface;
import org.citygml4j.model.gml.geometry.primitives.Polygon;
import org.citygml4j.model.gml.geometry.primitives.SolidProperty;
import org.citygml4j.model.gml.geometry.primitives.SurfaceProperty;
import org.citygml4j.util.walker.FeatureWalker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: richard
 * Date: 25.11.13
 * Time: 17:38
 * <p/>
 */
public class BuildingWallMatcher {

    private static final Logger LOGGER = Logger.getLogger(BuildingWallMatcher.class);
    private final static String LOD1_MULTI_SURFACE = "lod1MultiSurface";
    private final static String LOD2_MULTI_SURFACE = "lod2MultiSurface";
    private final static String LOD3_MULTI_SURFACE = "lod3MultiSurface";
    private final static String LOD4_MULTI_SURFACE = "lod4MultiSurface";
    private final static String WALL_SURFACE = "WallSurface";
    private final static String GROUND_SURFACE = "GroundSurface";
    private final static String ROOF_SURFACE = "RoofSurface";

    /**
     * Matches {@link org.citygml4j.model.citygml.building.AbstractBoundarySurface}s of the two {@link org.citygml4j.model.citygml.building.Building}s.
     * Does this by comparing normal vectors and distances of the surfaces to a designated point of the building.
     *
     * @param building1
     * @param building2
     * @return
     */
    public static Map<Polygon, Polygon> matchBuildingsBoundarySurfaces(Building building1, Building building2) {
        Map<Polygon, Polygon> polygonMatchings = new HashMap<>();

        Map<Polygon, List<Double>> polygonToNormalVectorBuilding1 = new HashMap<>();
        Map<Polygon, String> polygonToSemanticsBuilding1 = new HashMap<>();
        polygonToNormalVectorBuilding1 = calculateNormalVectorsOfAndSemanticsSurfaces(building1, polygonToNormalVectorBuilding1, polygonToSemanticsBuilding1);

        Map<Polygon, List<Double>> polygonToNormalVectorBuilding2 = new HashMap<>();
        Map<Polygon, String> polygonToSemanticsBuilding2 = new HashMap<>();
        polygonToNormalVectorBuilding2 = calculateNormalVectorsOfAndSemanticsSurfaces(building2, polygonToNormalVectorBuilding2, polygonToSemanticsBuilding2);

        Map<Polygon, List<Double>> polygonToDistance1 = new HashMap<>();
        polygonToDistance1 = calculateDistanceToLowerCorner(building1, polygonToDistance1);

        Map<Polygon, List<Double>> polygonToDistance2 = new HashMap<>();
        polygonToDistance2 = calculateDistanceToLowerCorner(building2, polygonToDistance2);

        for (Map.Entry<Polygon, List<Double>> polygonListEntry1 : polygonToNormalVectorBuilding1.entrySet()) {
            for (Map.Entry<Polygon, List<Double>> polygonListEntry2 : polygonToNormalVectorBuilding2.entrySet()) {
                Polygon polygon1 = polygonListEntry1.getKey();
                Polygon polygon2 = polygonListEntry2.getKey();
                String semantics1 = polygonToSemanticsBuilding1.get(polygon1);
                String semantics2 = polygonToSemanticsBuilding2.get(polygon2);
                if (semantics1.equals(semantics2)) {
                    List<Double> normalVector1 = polygonListEntry1.getValue();
                    List<Double> normalVector2 = polygonListEntry2.getValue();
                    if (areNormalVectorsMatching(normalVector1, normalVector2)) {
                        List<Double> distance1 = polygonToDistance1.get(polygon1);
                        List<Double> distance2 = polygonToDistance2.get(polygon2);
                        if (areDistancesMatching(distance1, distance2)) {
                            polygonMatchings.put(polygon1, polygon2);
                        }
                    }

                }
            }
        }
        return polygonMatchings;
    }


    private static boolean areDistancesMatching(List<Double> doubles1, List<Double> doubles2) {

        double length1 = GeoUtils.calcLength(doubles1);
        double length2 = GeoUtils.calcLength(doubles2);
        if (Math.abs(length1 - length2) <= Main.EPSILON_DISTANCE) {
            return true;
        }
        return false;
    }

    private static boolean areNormalVectorsMatching(List<Double> normal1, List<Double> normal2) {
        if (Math.abs(normal1.get(0) - normal2.get(0)) <= Main.EPSILON_NORMAL_VECTOR) {
            if (Math.abs(normal1.get(1) - normal2.get(1)) <= Main.EPSILON_NORMAL_VECTOR) {
                if (Math.abs(normal1.get(2) - normal2.get(2)) <= Main.EPSILON_NORMAL_VECTOR) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculates the normal vectors of the {@link org.citygml4j.model.citygml.building.AbstractBoundarySurface}s of the {@link org.citygml4j.model.citygml.building.Building}s.
     * Saves the results in polygonToNormalVectorBuilding and returns this map.
     *
     * @param building
     * @param polygonToNormalVectorBuilding
     * @return
     */
    private static Map<Polygon, List<Double>> calculateNormalVectorsOfAndSemanticsSurfaces(Building building, final Map<Polygon, List<Double>> polygonToNormalVectorBuilding,
                                                                                           final Map<Polygon, String> polygonToSemantics) {
        MultiSurfaceProperty lod1MultiSurface = building.getLod1MultiSurface();
        if (lod1MultiSurface != null) {
            polygonToNormalVectorBuilding.putAll(caclulateNormalVectorsOfLodSurfaces(lod1MultiSurface, polygonToNormalVectorBuilding, polygonToSemantics, LOD1_MULTI_SURFACE));
        }
        MultiSurfaceProperty lod2MultiSurface = building.getLod2MultiSurface();
        if (lod2MultiSurface != null) {
            polygonToNormalVectorBuilding.putAll(caclulateNormalVectorsOfLodSurfaces(lod2MultiSurface, polygonToNormalVectorBuilding, polygonToSemantics, LOD2_MULTI_SURFACE));
        }
        MultiSurfaceProperty lod3MultiSurface = building.getLod3MultiSurface();
        if (lod3MultiSurface != null) {
            polygonToNormalVectorBuilding.putAll(caclulateNormalVectorsOfLodSurfaces(lod3MultiSurface, polygonToNormalVectorBuilding, polygonToSemantics, LOD3_MULTI_SURFACE));
        }
        MultiSurfaceProperty lod4MultiSurface = building.getLod4MultiSurface();
        if (lod4MultiSurface != null) {
            polygonToNormalVectorBuilding.putAll(caclulateNormalVectorsOfLodSurfaces(lod4MultiSurface, polygonToNormalVectorBuilding, polygonToSemantics, LOD4_MULTI_SURFACE));
        }

        SolidProperty lod1Solid = building.getLod1Solid();
        SolidProperty lod2Solid = building.getLod2Solid();
        SolidProperty lod3Solid = building.getLod3Solid();
        SolidProperty lod4Solid = building.getLod4Solid();

        FeatureWalker walker = new FeatureWalker() {
            @Override
            public void visit(AbstractBoundarySurface boundarySurface) {

                String surfaceType = determineSurfaceType(boundarySurface);

                MultiSurfaceProperty lod2MultiSurface = boundarySurface.getLod2MultiSurface();
                if (lod2MultiSurface != null) {
                    polygonToNormalVectorBuilding.putAll(caclulateNormalVectorsOfLodSurfaces(lod2MultiSurface, polygonToNormalVectorBuilding, polygonToSemantics, LOD2_MULTI_SURFACE + surfaceType));
                }

                MultiSurfaceProperty lod3MultiSurface = boundarySurface.getLod3MultiSurface();
                if (lod3MultiSurface != null) {
                    polygonToNormalVectorBuilding.putAll(caclulateNormalVectorsOfLodSurfaces(lod3MultiSurface, polygonToNormalVectorBuilding, polygonToSemantics, LOD3_MULTI_SURFACE + surfaceType));
                }

                MultiSurfaceProperty lod4MultiSurface = boundarySurface.getLod4MultiSurface();
                if (lod4MultiSurface != null) {
                    polygonToNormalVectorBuilding.putAll(caclulateNormalVectorsOfLodSurfaces(lod4MultiSurface, polygonToNormalVectorBuilding, polygonToSemantics, LOD4_MULTI_SURFACE + surfaceType));
                }

                super.visit(boundarySurface);
            }
        };

        building.accept(walker);
        return polygonToNormalVectorBuilding;
    }

    private static String determineSurfaceType(AbstractBoundarySurface boundarySurface) {
        if (boundarySurface instanceof WallSurface) {
            return WALL_SURFACE;
        } else if (boundarySurface instanceof GroundSurface) {
            return GROUND_SURFACE;
        } else if (boundarySurface instanceof RoofSurface) {
            return ROOF_SURFACE;
        }
        LOGGER.warn("Surface type not implemented!");
        return null;
    }

    public static Map<Polygon, List<Double>> caclulateNormalVectorsOfLodSurfaces(MultiSurfaceProperty multiSurfaceProperty, Map<Polygon, List<Double>> polygonToNormalVector,
                                                                                 final Map<Polygon, String> polygonToSemantics, String semantics) {

        MultiSurface multiSurface = multiSurfaceProperty.getMultiSurface();
        if (multiSurface == null) {
            return polygonToNormalVector;
        }

        List<SurfaceProperty> surfaceMember = multiSurface.getSurfaceMember();
        for (SurfaceProperty surfaceProperty : surfaceMember) {
            AbstractSurface abstractSurface = surfaceProperty.getObject();
            if (abstractSurface instanceof Polygon) {
                Polygon polygon = (Polygon) abstractSurface;
                List<Double> doubles = GeoUtils.calculateNormalVectorForPolygon(polygon);
                polygonToNormalVector.put(polygon, doubles);
                polygonToSemantics.put(polygon, semantics);
            }
        }
        return polygonToNormalVector;
    }

    /**
     * Calculates the distances of the {@link org.citygml4j.model.citygml.building.Building}'s {@link org.citygml4j.model.citygml.building.AbstractBoundarySurface}s to a designated
     * point of the building. Saves the results in polygonToDistance and returns this map. Distance is a vector containing distance in x, y, and z direction.
     *
     * @param building
     * @param polygonToDistance
     * @return
     */
    private static Map<Polygon, List<Double>> calculateDistanceToLowerCorner(Building building, final Map<Polygon, List<Double>> polygonToDistance) {

        final List<Double> lowerCornerBuilding = GeoUtils.calculateLowerCorner(building);
        FeatureWalker walker = new FeatureWalker() {
            @Override
            public void visit(AbstractBoundarySurface boundarySurface) {

                MultiSurfaceProperty multiSurfacePropertyLod2 = boundarySurface.getLod2MultiSurface();
                if (multiSurfacePropertyLod2 != null) {
                    polygonToDistance.putAll(calculateDistanceToLowerCorner(multiSurfacePropertyLod2, polygonToDistance));
                }

                MultiSurfaceProperty multiSurfacePropertyLod3 = boundarySurface.getLod3MultiSurface();
                if (multiSurfacePropertyLod3 != null) {
                    polygonToDistance.putAll(calculateDistanceToLowerCorner(multiSurfacePropertyLod3, polygonToDistance));
                }

                MultiSurfaceProperty multiSurfacePropertyLod4 = boundarySurface.getLod4MultiSurface();
                if (multiSurfacePropertyLod4 != null) {
                    polygonToDistance.putAll(calculateDistanceToLowerCorner(multiSurfacePropertyLod4, polygonToDistance));
                }
                super.visit(boundarySurface);
            }

            private Map<Polygon, List<Double>> calculateDistanceToLowerCorner(MultiSurfaceProperty multiSurfaceProperty, Map<Polygon, List<Double>> polygonToDistance) {

                MultiSurface multiSurface = multiSurfaceProperty.getMultiSurface();
                if (multiSurface == null) {
                    return polygonToDistance;
                }

                List<SurfaceProperty> surfaceMember = multiSurface.getSurfaceMember();
                for (SurfaceProperty surfaceProperty : surfaceMember) {
                    AbstractSurface abstractSurface = surfaceProperty.getObject();
                    if (abstractSurface instanceof Polygon) {
                        Polygon polygon = (Polygon) abstractSurface;
                        List<Double> lowerCornerPolygon = GeoUtils.calculateLowerLeftPoint(polygon);

                        String srsName = polygon.getSrsName();
                        if (srsName == null || srsName.equals("")) {
                            srsName = polygon.getInheritedSrsName();
                        }
                        lowerCornerPolygon = GeoUtils.transformToGeocentricCoords(lowerCornerPolygon, srsName);
                        List<Double> referenceCornerBuilding = GeoUtils.transformToGeocentricCoords(lowerCornerBuilding, srsName);

                        List<Double> result = new ArrayList<>();
                        result.add(lowerCornerPolygon.get(0) - referenceCornerBuilding.get(0));
                        result.add(lowerCornerPolygon.get(1) - referenceCornerBuilding.get(1));
                        result.add(lowerCornerPolygon.get(2) - referenceCornerBuilding.get(2));
                        polygonToDistance.put(polygon, result);
                    }
                }
                return polygonToDistance;
            }
        };
        building.accept(walker);

        return polygonToDistance;
    }

}
