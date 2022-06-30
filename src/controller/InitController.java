package controller;

import model.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  Init Controller provides the main application functionality.
 *  Mainly that of controlling initial user input.
 */
public class InitController extends JFrame {

    //Store the parser globally in this controller.
    private static KMLParser parser;
    private final String kmlPath;

    public InitController(String kmlPath) throws IOException {
        this.kmlPath = kmlPath;
        beginParse();
        mainDecision();
    }

    public void beginParse() {
        // Grab the KML Path and Init.
        String fullPath = "src/data/" + kmlPath;

        File kml = new File(fullPath);

        parser = new KMLParser(kml);
        parser.initParser();
        parser.parseKML("Station");
        parser.parseKML("Ports");
        parser.parseKML("Routes (Train)");
        parser.parseKML("Router (Ferry)");
    }

    public void mainDecision() throws IOException {
        MenuController menu = new MenuController();
        int decision = menu.chooseAction();

        switch(decision){
            case 1:
                //View all Stations
                drawStations();
                mainDecision();
            case 2:
                //View all routes.
                drawRoutes();
                mainDecision();
            case 3:
                //Plan Journey.
                //First, let's parse all of our placemarks and routes into an Edge List Graph.
                prepareGraph();
                mainDecision();
            default:
                System.out.println("Application Shutting Down. Goodbye.");
                System.exit(0);
                break;
        }
    }

    /**
     * The prepare graph function utilized all of our parsed placemarks and routes and populates them into a graph.
     */
    private NetworkGraph prepareGraph() {
        NetworkGraph graph = new NetworkGraph();
        List<Route> routes = parser.getRouteList();

        int val = -1;
        for(int i = 0; i < routes.size(); i++){
            val = val + 1;
            PlacemarkNode node = new PlacemarkNode(val, routes.get(i).getLocationA().getName());
            val = val + 1;
            PlacemarkNode node2 = new PlacemarkNode(val, routes.get(i).getLocationB().getName());
            graph.addPlacemark(node);
            graph.addRoute(node, node2, routes.get(i).getMinuteDuration());
        }

        return graph;
    }

    public void drawStations() throws IOException {
        // Now we've got all the locations into a data type, we'll start by drawing up the GUI to show stations (test)
        JFrame frame = new JFrame("Station Draw");
        DrawStationController draw = new DrawStationController(parser.getPlacemarkList());
        frame.setSize(796, 1000);
        frame.add(draw);
        frame.setVisible(true);
    }

    public void drawRoutes() throws IOException {
        // Now we've got all the locations into a data type, we'll start by drawing up the GUI to show stations (test)
        JFrame frame = new JFrame("Route Draw");
        DrawRoutesController draw = new DrawRoutesController(parser.getRouteList());
        frame.setSize(796, 1000);
        frame.add(draw);
        frame.setVisible(true);
    }

}