package de.igg.citygmldiff.citygmldiff.tree.handler.xal;

import de.igg.citygmldiff.citygmldiff.tree.handler.xal.util.AddressLinesProcessor;
import de.igg.citygmldiff.citygmldiff.tree.model.ElementNode;
import de.igg.citygmldiff.citygmldiff.util.NodeFactory;
import org.citygml4j.model.xal.*;

import java.util.List;

public class PremiseNumberRangeFromHandler<T extends PremiseNumberRangeFrom>
        extends AddressLinesProcessor<T> {

    private static PremiseNumberHandler<PremiseNumber> premiseNumberHandler = new PremiseNumberHandler<>();
    private static PremiseNumberPrefixHandler<PremiseNumberPrefix> premiseNumberPrefixHandler = new PremiseNumberPrefixHandler<>();
    private static PremiseNumberSuffixHandler<PremiseNumberSuffix> premiseNumberSuffixHandler = new PremiseNumberSuffixHandler<>();

    @Override
    public void addChildsToNode(ElementNode<T> node) {
        T element = node.getValue();

        List<PremiseNumber> premiseNumbers = element.getPremiseNumber();
        processPremiseNumbers(premiseNumbers, node);

        List<PremiseNumberPrefix> premiseNumberPrefixes = element
                .getPremiseNumberPrefix();
        processPremiseNumberPrefixes(premiseNumberPrefixes, node);

        List<PremiseNumberSuffix> premiseNumberSuffixes = element
                .getPremiseNumberSuffix();
        processPremiseNumberSuffix(premiseNumberSuffixes, node);

        List<AddressLine> addressLines = element.getAddressLine();
        processAddressLines(addressLines, node);

    }

    private void processPremiseNumberSuffix(List<PremiseNumberSuffix> premiseNumberSuffixes, ElementNode<T> node) {
        if (premiseNumberSuffixes != null) {
            for (PremiseNumberSuffix premiseNumberSuffix : premiseNumberSuffixes) {
                ElementNode<PremiseNumberSuffix> suffixNode = NodeFactory
                        .createElementNode(premiseNumberSuffix,
                                PREMISE_NUMBER_SUFFIX, node);
                premiseNumberSuffixHandler.addChildsToNode(suffixNode);
                node.addChild(suffixNode);
            }
        }
    }

    private void processPremiseNumberPrefixes(List<PremiseNumberPrefix> premiseNumberPrefixes, ElementNode<T> node) {
        if (premiseNumberPrefixes != null) {
            for (PremiseNumberPrefix premiseNumberPrefix : premiseNumberPrefixes) {
                ElementNode<PremiseNumberPrefix> prefixNode = NodeFactory
                        .createElementNode(premiseNumberPrefix,
                                PREMISE_NUMBER_PREFIX, node);
                premiseNumberPrefixHandler.addChildsToNode(prefixNode);
                node.addChild(prefixNode);
            }
        }
    }

    private void processPremiseNumbers(List<PremiseNumber> premiseNumbers, ElementNode<T> node) {
        if (premiseNumbers != null) {
            for (PremiseNumber premiseNumber : premiseNumbers) {
                ElementNode<PremiseNumber> numberNode = NodeFactory
                        .createElementNode(premiseNumber, PREMISE_NUMBER, node);
                premiseNumberHandler.addChildsToNode(numberNode);
                node.addChild(numberNode);
            }
        }
    }

}