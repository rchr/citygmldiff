package de.igg.citygmldiff.citygmldiff.main;

import citygmldiff.util.TestInstances;
import de.igg.citygmldiff.citygmldiff.editscript.EditScriptCalculator;
import de.igg.citygmldiff.citygmldiff.editscript.model.EditOperation;
import de.igg.citygmldiff.citygmldiff.matching.TreeMatcher;
import de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable;
import de.igg.citygmldiff.citygmldiff.tree.TreeBuilder;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import org.citygml4j.model.citygml.building.Building;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.model.citygml.core.CityObjectMember;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: richard
 * Date: 06.02.14
 * Time: 14:09
 */
public class MainTest {

    @Test
    public void testAllModules() {
        Building buildingA = TestInstances.createBuilding("A");
        CityModel cityModelA = new CityModel();
        CityObjectMember cityObjectMemberA = new CityObjectMember(buildingA);
        cityModelA.addCityObjectMember(cityObjectMemberA);

        Building buildingB = TestInstances.createBuilding("B");
        CityModel cityModelB = new CityModel();
        CityObjectMember cityObjectMemberB = new CityObjectMember(buildingB);
        cityModelB.addCityObjectMember(cityObjectMemberB);

        TreeBuilder treeBuilder = new TreeBuilder();
        ElementNode<CityModel> headA = treeBuilder.createTree(cityModelA);
        ElementNode<CityModel> headB = treeBuilder.createTree(cityModelB);

        TreeMatcher treeMatcher = new TreeMatcher();
        DistanceTable distanceTable = treeMatcher.matchTrees(headA, headB, new HashMap<String[], Boolean>());

        EditScriptCalculator editScriptCalculator = new EditScriptCalculator();
        List<EditOperation> editOperations = editScriptCalculator.calculateEditScript(headA, headB, distanceTable);

        assertNotNull(editOperations);
        assertEquals(26, editOperations.size());
    }

    @Test
    public void testAllModulesTransformed() {
        Building buildingA = TestInstances.createBuilding("A");
        CityModel cityModelA = new CityModel();
        CityObjectMember cityObjectMemberA = new CityObjectMember(buildingA);
        cityModelA.addCityObjectMember(cityObjectMemberA);

        Building buildingB = TestInstances.createBuildingTransformed("B");
        CityModel cityModelB = new CityModel();
        CityObjectMember cityObjectMemberB = new CityObjectMember(buildingB);
        cityModelB.addCityObjectMember(cityObjectMemberB);

        TreeBuilder treeBuilder = new TreeBuilder();
        ElementNode<CityModel> headA = treeBuilder.createTree(cityModelA);
        ElementNode<CityModel> headB = treeBuilder.createTree(cityModelB);

        TreeMatcher treeMatcher = new TreeMatcher();
        DistanceTable distanceTable = treeMatcher.matchTrees(headA, headB, new HashMap<String[], Boolean>());

        EditScriptCalculator editScriptCalculator = new EditScriptCalculator();
        List<EditOperation> editOperations = editScriptCalculator.calculateEditScript(headA, headB, distanceTable);

        assertNotNull(editOperations);
        for (EditOperation editOperation : editOperations) {
            System.out.println(editOperation.toString());
        }
        assertEquals(52, editOperations.size());
    }

}
