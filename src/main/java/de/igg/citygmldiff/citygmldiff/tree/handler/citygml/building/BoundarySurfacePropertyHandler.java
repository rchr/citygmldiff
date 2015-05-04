package de.igg.citygmldiff.citygmldiff.tree.handler.citygml.building;

import de.igg.citygmldiff.citygmldiff.tree.TreeNodeHandler;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.citygml4j.model.citygml.building.*;

/**
 * User: richard
 * Date: 07.08.13
 * Time: 10:46
 */
public class BoundarySurfacePropertyHandler<T extends BoundarySurfaceProperty> implements TreeNodeHandler<T>, Constants {

    private static RoofSurfaceHandler<RoofSurface> roofSurfaceHandler = new RoofSurfaceHandler<>();
    private static WallSurfaceHandler<WallSurface> wallSurfaceHandler = new WallSurfaceHandler<>();
    private static GroundSurfaceHandler<GroundSurface> groundSurfaceHandler = new GroundSurfaceHandler<>();
    private static ClosureSurfaceHandler<ClosureSurface> closureSurfaceHandler = new ClosureSurfaceHandler<>();
    private static OuterFloorSurfaceHandler<OuterFloorSurface> outerFloorSurfaceHandler = new OuterFloorSurfaceHandler<>();
    private static OuterCeilingSurfaceHandler<OuterCeilingSurface> outerCeilingSurfaceHandler = new OuterCeilingSurfaceHandler<>();
    private static FloorSurfaceHandler<FloorSurface> floorSurfaceHandler = new FloorSurfaceHandler<>();
    private static InteriorWallSurfaceHandler<InteriorWallSurface> interiorWallSurfaceHandler = new InteriorWallSurfaceHandler<>();
    private static CeilingSurfaceHandler<CeilingSurface> ceilingSurfaceHandler = new CeilingSurfaceHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {

        T element = node.getValue();

        AbstractBoundarySurface boundarySurface = element.getBoundarySurface();
        if (boundarySurface == null) {
            Utils.addXlinkFeature(node);
        } else if (boundarySurface instanceof RoofSurface) {
            RoofSurface roofSurface = (RoofSurface) boundarySurface;
            ElementNode<RoofSurface> surfaceNode = NodeFactory.createElementNode(roofSurface, ROOF_SURFACE, node);
            roofSurfaceHandler.addChildsToNode(surfaceNode);
            node.addChild(surfaceNode);

        } else if (boundarySurface instanceof WallSurface) {
            WallSurface wallSurface = (WallSurface) boundarySurface;
            ElementNode<WallSurface> surfaceNode = NodeFactory.createElementNode(wallSurface, WALL_SURFACE, node);
            wallSurfaceHandler.addChildsToNode(surfaceNode);
            node.addChild(surfaceNode);

        } else if (boundarySurface instanceof GroundSurface) {
            GroundSurface groundSurface = (GroundSurface) boundarySurface;
            ElementNode<GroundSurface> surfaceNode = NodeFactory.createElementNode(groundSurface, GROUND_SURFACE, node);
            groundSurfaceHandler.addChildsToNode(surfaceNode);
            node.addChild(surfaceNode);
        } else if (boundarySurface instanceof ClosureSurface) {
            ClosureSurface closureSurface = (ClosureSurface) boundarySurface;
            ElementNode surfaceNode = NodeFactory.createElementNode(closureSurface, CLOSURE_SURFACE, node);
            closureSurfaceHandler.addChildsToNode(surfaceNode);
            node.addChild(surfaceNode);
        } else if (boundarySurface instanceof OuterFloorSurface) {
            OuterFloorSurface outerFloorSurface = (OuterFloorSurface) boundarySurface;
            ElementNode<OuterFloorSurface> surfaceNode = NodeFactory.createElementNode(outerFloorSurface, OUTER_FLOOR_SURFACE, node);
            outerFloorSurfaceHandler.addChildsToNode(surfaceNode);
            node.addChild(surfaceNode);
        } else if (boundarySurface instanceof OuterCeilingSurface) {
            OuterCeilingSurface outerCeilingSurface = (OuterCeilingSurface) boundarySurface;
            ElementNode<OuterCeilingSurface> surfaceNode = NodeFactory.createElementNode(outerCeilingSurface, OUTER_CEILING_SURFACE, node);
            outerCeilingSurfaceHandler.addChildsToNode(surfaceNode);
            node.addChild(surfaceNode);
        } else if (boundarySurface instanceof FloorSurface) {
            FloorSurface floorSurface = (FloorSurface) boundarySurface;
            ElementNode<FloorSurface> surfaceNode = NodeFactory.createElementNode(floorSurface, FLOOR_SURFACE, node);
            floorSurfaceHandler.addChildsToNode(surfaceNode);
            node.addChild(surfaceNode);
        } else if (boundarySurface instanceof InteriorWallSurface) {
            InteriorWallSurface interiorWallSurface = (InteriorWallSurface) boundarySurface;
            ElementNode<InteriorWallSurface> surfaceNode = NodeFactory.createElementNode(interiorWallSurface, INTERIOR_WALL_SURFACE, node);
            interiorWallSurfaceHandler.addChildsToNode(surfaceNode);
            node.addChild(surfaceNode);
        } else if (boundarySurface instanceof CeilingSurface) {
            CeilingSurface ceilingSurface = (CeilingSurface) boundarySurface;
            ElementNode<CeilingSurface> surfaceNode = NodeFactory.createElementNode(ceilingSurface, CEILING_SURFACE, node);
            ceilingSurfaceHandler.addChildsToNode(surfaceNode);
            node.addChild(surfaceNode);
        }
    }

}
