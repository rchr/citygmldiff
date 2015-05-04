package de.igg.citygmldiff.citygmldiff.main;

import de.igg.citygmldiff.citygmldiff.editscript.EditScriptCalculator;
import de.igg.citygmldiff.citygmldiff.editscript.EditScriptWriter;
import de.igg.citygmldiff.citygmldiff.editscript.model.EditOperation;
import de.igg.citygmldiff.citygmldiff.io.reader.Reader;
import de.igg.citygmldiff.citygmldiff.matching.TreeMatcher;
import de.igg.citygmldiff.citygmldiff.matching.model.DistanceTable;
import de.igg.citygmldiff.citygmldiff.matching.uncertain.UncertainMatchingReader;
import de.igg.citygmldiff.citygmldiff.tree.TreeBuilder;
import de.igg.citygmldiff.citygmldiff.tree.model.AbstractTreeNode;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.apache.log4j.Logger;
import org.citygml4j.model.citygml.core.CityModel;
import org.citygml4j.util.xlink.XLinkResolver;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static final double EPSILON_DISTANCE = 2E-1;
    public static final double EPSILON_NORMAL_VECTOR = 1E-1;

    public static final XLinkResolver XLINK_RESOLVER = new XLinkResolver();
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final long MEGABYTE = 1024L * 1024L;
    public static int currentCityModel = 1; // Hack. Not beautiful, but needed ;)
    public static List<CityModel> models1;
    public static List<CityModel> models2;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        if (args.length < 3) {
            LOGGER.error("Not enough input parameters. Need at least 3: CityGMLDocument1, CityGMLDocument2, EditScript Destination, Uncertain Matching File");
        }

        String file1 = args[0];
        String file2 = args[1];
        String editScriptDestination = args[2];
        String uncertainMatchingsFile = null;
        if (args.length == 4) {
            uncertainMatchingsFile = args[3];
        }

        Map<String[], Boolean> uncertainMatchingsInput = new HashMap<>();
        if (uncertainMatchingsFile != null && !uncertainMatchingsFile.equals("")) {
            UncertainMatchingReader uncertainMatchingReader = new UncertainMatchingReader();
            uncertainMatchingsInput = uncertainMatchingReader.readUncertainMatchings(uncertainMatchingsFile);
        }

        Reader reader = new Reader();
        models1 = reader.getCityModelsFromFile(new File(file1));
        models2 = reader.getCityModelsFromFile(new File(file2));

        TreeBuilder treeBuilder = new TreeBuilder();
        ElementNode<CityModel> cityModelNode1 = treeBuilder.createTree(models1.get(0));
        currentCityModel = 2;
        ElementNode<CityModel> cityModelNode2 = treeBuilder.createTree(models2.get(0));

        if (Utils.treesHaveSameDigest(cityModelNode1, cityModelNode2)) {
            LOGGER.info("Both trees are equal. Nothing to do!");
            return;
        }

        TreeMatcher treeMatcher = new TreeMatcher();
        DistanceTable distanceTable = treeMatcher.matchTrees(cityModelNode1, cityModelNode2, uncertainMatchingsInput);

        EditScriptCalculator editScriptCalculator = new EditScriptCalculator();
        List<EditOperation> editOperations = editScriptCalculator.calculateEditScript(cityModelNode1, cityModelNode2, distanceTable);

        Set<String> deletedFeatures = editScriptCalculator.getDeletedFeatures();
        List<EditOperation> xlinkDeletes = editScriptCalculator.calculateUnreferencedXLinks(deletedFeatures, Utils.xlinkXPathMap2);
        editOperations.addAll(xlinkDeletes);

        Map<String, String> updatedIDs = treeMatcher.getChangedIDs();
        List<EditOperation> xlinkUpdates = editScriptCalculator.caclulateXlinkUpdates(updatedIDs, Utils.xlinkXPathMap1);
        editOperations.addAll(xlinkUpdates);

        Map<AbstractTreeNode, AbstractTreeNode> uncertainMatchings = treeMatcher.getUncertainMatchings();
        List<EditOperation> uncertainOperations = editScriptCalculator.calculateEditOpsForUncertainMatchings(uncertainMatchings);
        editOperations.addAll(uncertainOperations);

        EditScriptWriter editScriptWriter = new EditScriptWriter(editScriptDestination);
        editScriptWriter.writeEditScript(editOperations);

        LOGGER.info("------------------------");
        LOGGER.info("Done. EditScript written to: " + editScriptDestination + " -- Used input: " + file1);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        LOGGER.info("Used Time (ms): " + elapsedTime);

        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        LOGGER.info("Used memory is megabytes: "
                + bytesToMegabytes(memory));
    }

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

}
