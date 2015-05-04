package de.igg.citygmldiff.citygmldiff.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

/**
 * User: richard
 * Date: 10.02.14
 * Time: 12:12
 */
public class StaticTransform {

    public static void main(String[] args) throws FactoryException, TransformException {
        String sourceCRS = "urn:ogc:def:crs:EPSG::4326";
        String targetCRS = "urn:ogc:def:crs:EPSG::32633";
        CoordinateReferenceSystem source = CRS.decode(sourceCRS);
        CoordinateReferenceSystem target = CRS.decode(targetCRS);
        MathTransform transform = CRS.findMathTransform(source, target);
        GeometryFactory geometryFactory = new GeometryFactory();

        Point lowerCorner = geometryFactory.createPoint(new Coordinate(52.5321790563424, 13.3269637686746, 74.6634593922645));
        lowerCorner = (Point) JTS.transform(lowerCorner, transform);
        System.out.println("lowerCorner=" + lowerCorner.toString());
        Point upperCorner = geometryFactory.createPoint(new Coordinate(52.5323175874323, 13.3274412933907, 90.3453739183024));
        upperCorner = (Point) JTS.transform(upperCorner, transform);
        System.out.println("upperCorner=" + upperCorner.toString());

        Point lowerCornerWall = geometryFactory.createPoint(new Coordinate(52.5321790563463, 13.3269637686813, 74.6634593894705));
        lowerCornerWall = (Point) JTS.transform(lowerCornerWall, transform);
        System.out.println("lowerCornerWall=" + lowerCornerWall.toString());
        Point upperCornerWall = geometryFactory.createPoint(new Coordinate(52.5323175874317, 13.3274412933906, 90.3453737022355));
        upperCornerWall = (Point) JTS.transform(upperCornerWall, transform);
        System.out.println("upperCornerWall=" + upperCornerWall.toString());

        Coordinate[] coordswall1SurfaceProp1 = {toCoordinate(13.3274412761895, 52.532182792233, 74.6634503705427), toCoordinate(13.3274412775653, 52.5321827935465, 79.210948058404),
                toCoordinate(13.3272422407462, 52.5321797602721, 79.2112362170592), toCoordinate(13.3272422393704, 52.5321797589586, 74.6637385291979),
                toCoordinate(13.3274412761895, 52.532182792233, 74.6634503705427)};
        LinearRing wall1SurfaceProp1 = geometryFactory.createLinearRing(coordswall1SurfaceProp1);
        wall1SurfaceProp1 = (LinearRing) JTS.transform(wall1SurfaceProp1, transform);
        System.out.println("wall1SurfaceProp1=" + wall1SurfaceProp1.toString());

        Coordinate[] coordswall1SurfaceProp2 = {toCoordinate(13.3272387341963, 52.5323145430766, 74.6632613306865), toCoordinate(13.3272387355721, 52.5323145443901, 79.2107590194792),
                toCoordinate(13.3274377730004, 52.5323175776712, 79.2104708570987), toCoordinate(13.3274377716246, 52.5323175763577, 74.6629731673747),
                toCoordinate(13.3272387341963, 52.5323145430766, 74.663261330686)};
        LinearRing wall1SurfaceProp2 = geometryFactory.createLinearRing(coordswall1SurfaceProp2);
        wall1SurfaceProp2 = (LinearRing) JTS.transform(wall1SurfaceProp2, transform);
        System.out.println("wall1SurfaceProp2=" + wall1SurfaceProp2.toString());

        Coordinate[] coordsWall1SurfaceProp3 = {toCoordinate(13.3274377716246, 52.5323175763577, 74.6629731673747),
                toCoordinate(13.3274377730004, 52.5323175776712, 79.2104708570987), toCoordinate(13.3274384739216, 52.5322906206026, 79.2105662999675),
                toCoordinate(13.3274412775653, 52.5321827935465, 79.210948058404), toCoordinate(13.3274412761895, 52.532182792233, 74.6634503705427),
                toCoordinate(13.3274384725457, 52.5322906192891, 74.6630686083809), toCoordinate(13.3274377716246, 52.5323175763577, 74.6629731673747)};
        LinearRing wall1SurfaceProp3 = geometryFactory.createLinearRing(coordsWall1SurfaceProp3);
        wall1SurfaceProp3 = (LinearRing) JTS.transform(wall1SurfaceProp3, transform);
        System.out.println("wall1SurfaceProp3=" + wall1SurfaceProp3.toString());

        Coordinate[] coordsWall1SurfaceProp4 = {toCoordinate(13.3272387355721, 52.5323145443901, 79.2107590194792),
                toCoordinate(13.3272387341963, 52.5323145430766, 74.6632613306865), toCoordinate(13.3272394352393, 52.5322875860094, 74.6633567716926),
                toCoordinate(13.3272422393704, 52.5321797589586, 74.6637385291979), toCoordinate(13.3272422407462, 52.5321797602721, 79.2112362170592),
                toCoordinate(13.3272394366151, 52.5322875873229, 79.2108544614166), toCoordinate(13.3272387355721, 52.5323145443901, 79.2107590194792)};
        LinearRing wall1SurfaceProp4 = geometryFactory.createLinearRing(coordsWall1SurfaceProp4);
        wall1SurfaceProp4 = (LinearRing) JTS.transform(wall1SurfaceProp4, transform);
        System.out.println("wall1SurfaceProp4=" + wall1SurfaceProp4.toString());

        Coordinate[] coordsWall1SurfaceProp5 = {toCoordinate(13.327239676004, 52.5322588109418, 75.0134577443823),
                toCoordinate(13.3272396806425, 52.5322588153702, 90.3451951900497), toCoordinate(13.3269641825761, 52.5322556072843, 90.3455904964358),
                toCoordinate(13.3269641779378, 52.5322556028559, 75.0138530535623), toCoordinate(13.327239676004, 52.5322588109418, 75.0134577443823)};
        LinearRing wall1SurfaceProp5 = geometryFactory.createLinearRing(coordsWall1SurfaceProp5);
        wall1SurfaceProp5 = (LinearRing) JTS.transform(wall1SurfaceProp5, transform);
        System.out.println("wall1SurfaceProp5=" + wall1SurfaceProp5.toString());

        Coordinate[] coordsWall1SurfaceProp6 = {toCoordinate(13.3272392942551, 52.5323149163834, 75.0132576012984),
                toCoordinate(13.3272392988936, 52.5323149208119, 90.3449950441718), toCoordinate(13.3272396806425, 52.5322588153702, 90.3451951900497),
                toCoordinate(13.327239676004, 52.5322588109418, 75.0134577443823), toCoordinate(13.3272392942551, 52.5323149163834, 75.0132576012984)};
        LinearRing wall1SurfaceProp6 = geometryFactory.createLinearRing(coordsWall1SurfaceProp6);
        wall1SurfaceProp6 = (LinearRing) JTS.transform(wall1SurfaceProp6, transform);
        System.out.println("wall1SurfaceProp6=" + wall1SurfaceProp6.toString());

        Coordinate[] coordsWall1SurfaceProp7 = {toCoordinate(13.3269637958378, 52.5323117082961, 75.0136529114097),
                toCoordinate(13.3269638004762, 52.5323117127246, 90.3453903552145), toCoordinate(13.3272392988936, 52.5323149208119, 90.3449950441718),
                toCoordinate(13.3272392942551, 52.5323149163834, 75.0132576012984), toCoordinate(13.3269637958378, 52.5323117082961, 75.0136529114097)};
        LinearRing wall1SurfaceProp7 = geometryFactory.createLinearRing(coordsWall1SurfaceProp7);
        wall1SurfaceProp7 = (LinearRing) JTS.transform(wall1SurfaceProp7, transform);
        System.out.println("wall1SurfaceProp7=" + wall1SurfaceProp7.toString());

        Coordinate[] coordsWall1SurfaceProp8 = {toCoordinate(13.3269641779378, 52.5322556028559, 75.0138530535623),
                toCoordinate(13.3269641825761, 52.5322556072843, 90.3455904964358), toCoordinate(13.3269638004762, 52.5323117127246, 90.3453903552145),
                toCoordinate(13.3269637958378, 52.5323117082961, 75.0136529114097), toCoordinate(13.3269641779378, 52.5322556028559, 75.0138530535623)};
        LinearRing wall1SurfaceProp8 = geometryFactory.createLinearRing(coordsWall1SurfaceProp8);
        wall1SurfaceProp8 = (LinearRing) JTS.transform(wall1SurfaceProp8, transform);
        System.out.println("wall1SurfaceProp8=" + wall1SurfaceProp8.toString());

        Point lowerCornerGround = geometryFactory.createPoint(toCoordinate(13.326963764043, 52.5321790563463, 74.6634593894705));
        lowerCornerGround = (Point) JTS.transform(lowerCornerGround, transform);
        System.out.println("lowerCornerGround=" + lowerCornerGround.toString());
        Point upperCornerGround = geometryFactory.createPoint(toCoordinate(13.3274412933906, 52.5323175830033, 75.0136362574995));
        upperCornerGround = (Point) JTS.transform(upperCornerGround, transform);
        System.out.println("upperCornerGround=" + upperCornerGround.toString());

        Coordinate[] coordsGround1SurfaceProp1 = {toCoordinate(13.3272387341963, 52.5323145430766, 74.6632613306865),
                toCoordinate(13.3274377716246, 52.5323175763577, 74.6629731673747), toCoordinate(13.3274384725457, 52.5322906192891, 74.6630686083809),
                toCoordinate(13.3274412761895, 52.532182792233, 74.6634503705427), toCoordinate(13.3272422393704, 52.5321797589586, 74.6637385291979),
                toCoordinate(13.3272394352393, 52.5322875860094, 74.6633567716926), toCoordinate(13.3272387341963, 52.5323145430766, 74.6632613306865)};
        LinearRing ground1SurfaceProp1 = geometryFactory.createLinearRing(coordsGround1SurfaceProp1);
        ground1SurfaceProp1 = (LinearRing) JTS.transform(ground1SurfaceProp1, transform);
        System.out.println("ground1SurfaceProp1=" + ground1SurfaceProp1.toString());


        Coordinate[] coordsGround1SurfaceProp2 = {toCoordinate(13.3269637958378, 52.5323117082961, 75.0136529114097),
                toCoordinate(13.3272392942551, 52.5323149163834, 75.0132576012984), toCoordinate(13.327239676004, 52.5322588109418, 75.0134577443823),
                toCoordinate(13.3269641779378, 52.5322556028559, 75.0138530535623), toCoordinate(13.3269637958378, 52.5323117082961, 75.0136529114097)};
        LinearRing ground1SurfaceProp2 = geometryFactory.createLinearRing(coordsGround1SurfaceProp2);
        ground1SurfaceProp2 = (LinearRing) JTS.transform(ground1SurfaceProp2, transform);
        System.out.println("ground1SurfaceProp2=" + ground1SurfaceProp2.toString());

        Point lowerCornerRoof = geometryFactory.createPoint(toCoordinate(13.3269637686813, 52.5321790576598, 79.2109570810571));
        lowerCornerRoof = (Point) JTS.transform(lowerCornerRoof, transform);
        System.out.println("lowerCornerRoof=" + lowerCornerRoof.toString());
        Point upperCornerRoof = geometryFactory.createPoint(toCoordinate(13.3274412947665, 52.5323175874317, 90.3453737022355));
        upperCornerRoof = (Point) JTS.transform(upperCornerRoof, transform);
        System.out.println("upperCornerRoof=" + upperCornerRoof.toString());

        Coordinate[] coordsRoof1SurfaceProp1 = {toCoordinate(13.3274377730004, 52.5323175776712, 79.2104708570987),
                toCoordinate(13.3272387355721, 52.5323145443901, 79.2107590194792), toCoordinate(13.3272394366151, 52.5322875873229, 79.2108544614166),
                toCoordinate(13.3272422407462, 52.5321797602721, 79.2112362170592), toCoordinate(13.3274412775653, 52.5321827935465, 79.210948058404),
                toCoordinate(13.3274384739216, 52.5322906206026, 79.2105662999675), toCoordinate(13.3274377730004, 52.5323175776712, 79.2104708570987)};
        LinearRing roof1SurfaceProp1 = geometryFactory.createLinearRing(coordsRoof1SurfaceProp1);
        roof1SurfaceProp1 = (LinearRing) JTS.transform(roof1SurfaceProp1, transform);
        System.out.println("roof1SurfaceProp1=" + roof1SurfaceProp1.toString());

        Coordinate[] coordsRoof1SurfaceProp2 = {toCoordinate(13.3269641825761, 52.5322556072843, 90.3455904964358),
                toCoordinate(13.3272396806425, 52.5322588153702, 90.3451951900497), toCoordinate(13.3272392988936, 52.5323149208119, 90.3449950441718),
                toCoordinate(13.3269638004762, 52.5323117127246, 90.3453903552145), toCoordinate(13.3269641825761, 52.5322556072843, 90.3455904964358)};
        LinearRing roof1SurfaceProp2 = geometryFactory.createLinearRing(coordsRoof1SurfaceProp2);
        roof1SurfaceProp2 = (LinearRing) JTS.transform(roof1SurfaceProp2, transform);
        System.out.println("roof1SurfaceProp2=" + roof1SurfaceProp2.toString());
    }

    private static Coordinate toCoordinate(double y, double x, double z) {
        return new Coordinate(x, y, z);
    }
}
