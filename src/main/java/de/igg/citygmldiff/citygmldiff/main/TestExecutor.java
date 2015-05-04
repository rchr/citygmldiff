package de.igg.citygmldiff.citygmldiff.main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by richard on 23.05.14.
 * Class to execute performance test!
 */
public class TestExecutor {

    private static final String changesXML = "/.../data/performance/changes.xml";

    public static void main(String[] args) {

        Map<String, String> files = new HashMap<>();
        files.put("/.../data/performance/10_buildings_clean.gml", "/.../data/performance/10_buildings_clean_changed_id.gml");
        // add more files here
        files.put("/.../data/performance/200_buildings_clean.gml", "/.../data/performance/200_buildings_clean_changed_id.gml");
        String[] a = new String[3];
        a[2] = changesXML;
        for (Map.Entry<String, String> entry : files.entrySet()) {
            a[0] = entry.getKey();
            a[1] = entry.getValue();
            Main.main(a);
        }
    }
}
