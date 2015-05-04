package de.igg.citygmldiff.citygmldiff.util;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import org.apache.commons.lang3.StringUtils;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

/**
 * User: richard
 * Date: 04.02.14
 * Time: 19:53
 */
public class CRSTransformer {

    public static Envelope transform(Envelope envelope, String sourceCRS, String targetCRS) {

        if (StringUtils.length(sourceCRS) == 0 || StringUtils.length(targetCRS) == 0) {
            // Do not transform envelope if one of the given crs is null or empty.
            return envelope;
        }

        if (sourceCRS.equals(targetCRS)) {
            return envelope;
        }

        Envelope resultEnvelope = null;
        try {
            CoordinateReferenceSystem source = CRS.decode(sourceCRS);
            CoordinateReferenceSystem target = CRS.decode(targetCRS);
            MathTransform transform = CRS.findMathTransform(source, target);
            resultEnvelope = JTS.transform(envelope, transform);
        } catch (FactoryException e) {
            e.printStackTrace();
        } catch (TransformException e) {
            e.printStackTrace();
        }
        return resultEnvelope;
    }

    public static Polygon transform(Polygon polygon, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) {

        Polygon transformedPolygon = null;
        try {
            MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);
            transformedPolygon = (Polygon) JTS.transform(polygon, transform);
        } catch (FactoryException e) {
            e.printStackTrace();
        } catch (TransformException e) {
            e.printStackTrace();
        }
        return transformedPolygon;
    }

    public static Geometry transform(Geometry polygon, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) {

        Geometry transformedPolygon = null;
        try {
            MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);
            transformedPolygon = JTS.transform(polygon, transform);
        } catch (FactoryException e) {
            e.printStackTrace();
        } catch (TransformException e) {
            e.printStackTrace();
        }
        return transformedPolygon;
    }
}
