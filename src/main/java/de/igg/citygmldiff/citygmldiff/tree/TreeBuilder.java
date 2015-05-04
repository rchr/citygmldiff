package de.igg.citygmldiff.citygmldiff.tree;

import de.igg.citygmldiff.citygmldiff.tree.handler.citygml.core.CityModelHandler;
import de.igg.citygmldiff.citygmldiff.tree.hash.TreeHasher;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.Constants;
import de.igg.citygmldiff.citygmldiff.util.Utils;
import org.apache.log4j.Logger;
import org.citygml4j.model.citygml.core.CityModel;

/**
 * User: richard Date: 01.08.13 Time: 14:09
 * <p/>
 * Class to create the tree.
 */
public class TreeBuilder implements Constants {

    private static final Logger LOGGER = Logger.getLogger(TreeBuilder.class);
    private static CityModelHandler<CityModel> cityModelHandler = new CityModelHandler<>();
    private TreeHasher treeHasher = new TreeHasher();

    /**
     * Creates the tree from the given {@link CityModel}.
     *
     * @param cityModel The city-model for which the tree is created.
     * @return The {@link ElementNode<CityModel>} of the tree, which is the root node.
     * It contains the {@link CityModel}'s elements as children.
     */
    public ElementNode<CityModel> createTree(CityModel cityModel) {
        ElementNode<CityModel> head = new ElementNode<>(cityModel, CITY_MODEL, "/" + CITY_MODEL, null);
        cityModelHandler.addChildsToNode(head);
        head.setParent(Utils.NIL_NODE);
        treeHasher.hashNodesInTree(head);
        LOGGER.info("Tree parsed and hashed.");
        return head;
    }
}
