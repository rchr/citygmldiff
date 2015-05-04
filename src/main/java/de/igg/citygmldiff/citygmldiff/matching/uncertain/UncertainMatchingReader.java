package de.igg.citygmldiff.citygmldiff.matching.uncertain;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by richard on 23.04.14.
 */
public class UncertainMatchingReader {

    public static final String YES = "yes";

    public Map<String[], Boolean> readUncertainMatchings(String uncertainMatchingFileName) {
        Map<String[], Boolean> uncertainMatchings = new HashMap<>();
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(uncertainMatchingFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot find " + uncertainMatchingFileName, e);
        }
        String[] nextLine;
        try {
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                String[] matching = {nextLine[0], nextLine[1]};
                Boolean isMatching = false;
                String matchingString = nextLine[2].trim();
                if (matchingString.equals(YES)) {
                    isMatching = true;
                }
                uncertainMatchings.put(matching, isMatching);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading next line in csv.", e);
        }
        return uncertainMatchings;
    }
}
