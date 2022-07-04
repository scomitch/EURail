package test;

import model.KMLParser;
import org.junit.jupiter.api.Test;

import java.io.File;

class TaskOneParserTest {

    @Test
    void TestKmlParserConstructor(){

        //NOTE: IF YOU HAVE A DIFFERENT KML FILE, RENAME HERE FOR TEST COMPLETION
        String kmlFile = "eurail-map.kml";

        // Grab the KML Path and Init.
        String fullPath = "src/data/" + kmlFile;
        File kml = new File(fullPath);
        KMLParser parser = new KMLParser(kml);
        assert parser.getKmlFile().equals(kml);
    }
}