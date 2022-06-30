package model;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * KML Parser is in-charge of processing a KML file and returning the contents to the controller for use.
 */
public class KMLParser {

    private File kmlFile;
    private List<Route> routeList;
    private List<Placemark> placemarkList;
    private Document parsedKML;

    public KMLParser(File kmlFile){
        this.kmlFile = kmlFile;
        this.routeList = new ArrayList<>();
        this.placemarkList = new ArrayList<>();
    }

    public File getKmlFile() {
        return kmlFile;
    }

    public void setKmlFile(File kmlFile) {
        this.kmlFile = kmlFile;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public List<Placemark> getPlacemarkList() {
        return placemarkList;
    }

    // Functions to properly parse out the KML file.
    public void initParser(){

        try{
            // We want to parse out the KML with the build in Java DOM.
            // Launch a Builder Factory
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

            //Place KML File out into the BF.
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            //Actual KML Document.
            parsedKML = builder.parse(kmlFile);

            parsedKML.getDocumentElement().normalize();

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public void parseKML(String type) {
        //Validation
        if (parsedKML == null) {
            System.out.println("You must initialize the document via initParser()");
            return;
        }

        if (type == null || type.length() == 0) {
            System.out.println("Please enter a KML Folder name. (Route / Placemark)");
            return;
        }

//        switch (type) {
//            case "Routes (Train)":
//                if(placemarkList.isEmpty()){
//                    System.out.println("Placemarks need to be populated first before Routes.");
//                    return;
//                }
//                break;
//            case "Station":
//                break;
//            default:
//                System.out.println("Route / Placemark not provided in the params");
//                return;
//        }

        //We need to parse out the Routes from the KML.
        NodeList placemarkNodes = parsedKML.getElementsByTagName("Folder");

        //Test
        for (int i = 0; i < placemarkNodes.getLength(); i++) {
            Node node = placemarkNodes.item(i);
            NodeList folderNodes = node.getChildNodes();

            String name = ((DeferredElementImpl) folderNodes).getElementsByTagName("name").item(0).getFirstChild().getTextContent();

            if (!name.equalsIgnoreCase(type)) {
                System.out.println("Not what we're looking for: " + name);
                continue;
            }

            for (int j = 2; j < folderNodes.getLength(); j++) {
                Node child = folderNodes.item(j);

                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    if (type.equals("Routes (Train)") || type.equals("Router (Ferry)")) {
                        // We want to process a Route
                        String RouteName = child.getFirstChild().getNextSibling().getFirstChild().getTextContent();
                        String RouteDistance = child.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getFirstChild().getTextContent();

                        //Parse out the Route into individual Placemarks.
                        String[] stations = RouteName.split("-");
                        String stationA = stations[0].trim();
                        String stationB = stations[1].trim();
                        System.out.println(RouteName + " with distance " + RouteDistance);

                        Route route = new Route(null, null, null, 0);
                        //Since stations have already been populated, as per the logic, push to add routes.
                        for(Placemark sA : placemarkList){
                            if(sA.getName().equals(stationA)){
                                //Got the Station A. Let's make a temp obj and apply it to the
                                Placemark temp = new Placemark(stationA, sA.getLon(), sA.getLat());
                                route.setLocationA(temp);
                            }
                        }

                        //Let's get Station B.
                        for(Placemark sB : placemarkList){
                            if(sB.getName().equals(stationB)){
                                //Got station B. Do same logic as above.
                                Placemark temp = new Placemark(stationB, sB.getLon(), sB.getLat());
                                route.setLocationB(temp);
                            }
                        }

                        // Fill in extra vars.
                        route.setShortDesc(RouteName);

                        // Convert string hrs and mins to mins int.
                        Pattern pattern = Pattern.compile("\\d+");
                        Matcher matcher = pattern.matcher(RouteDistance);
                        List<Integer> matches = new ArrayList<>();
                        while(matcher.find()){
                            matches.add(Integer.parseInt(matcher.group()));
                        }

                        int hrs = 0;
                        int totalMinutes = 0;

                        if(matches.size() > 1){
                            //Convert hours and minutes to minutes.
                            hrs = matches.get(0) * 60;
                            totalMinutes = hrs + matches.get(1);
                        } else {
                            totalMinutes = matches.get(0) * 60;
                        }

                        // Add total minutes.
                        route.setMinuteDuration(totalMinutes);

                        // Add to arr.
                        routeList.add(route);

                    } else {
                        // We want to process a Station
                        System.out.println("Pause break, try and find station vals.");
                        Node StationNode = child.getFirstChild().getNextSibling();
                        Node PointNode = StationNode.getNextSibling().getNextSibling().getNextSibling().getNextSibling();
                        String stationName = StationNode.getFirstChild().getTextContent();
                        String stationCoords = PointNode.getFirstChild().getNextSibling().getFirstChild().getTextContent().trim();

                        //Got the name and coords for a station. Need to parse coodinates into long and lat.
                        String[] coords = stationCoords.split(",");
                        double lon = Double.parseDouble(coords[0]);
                        double lat = Double.parseDouble(coords[1]);
                        Placemark station = new Placemark(stationName, lon, lat);

                        placemarkList.add(station);
                    }
                }

            }

            System.out.println("hello");

        }
    }

}
