package controller;

import java.io.IOException;

public class Main {

    /**
     * Main function, handles instantiation of KML file.
     * @param args - Args 0 is the KML file placed in /data/
     */
    public static void main(String[] args) throws IOException {
        // Check for empty args.
        if(args.length == 0){
            System.out.println("No arguments. Proper usage has KML file name as only argument. i.e. EURail.kml");
            System.exit(0);
        }

        // Process args[0] as KML file path.
        String kmlPath = args[0];

        InitController initController = new InitController(kmlPath);

    }
}
