package model;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 *  Placemark is a class that holds details about a single location on the KML Network
 */
public class Placemark {

    // Instance properties.
    private String name;
    private double lon;
    private double lat;


    /**
     *
     * @param name the name of the placemark
     * @param lon the longitude coordinate of the placemark
     * @param lat the latitude coordinate of the placemark
     */
    public Placemark(String name, double lon, double lat){
        this.name = name;
        this.lon = lon;
        this.lat = lat;
    }

    /**
     * Gets the name of the current placemark
     * @return the name of the placemark
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the current placemark
     * @param name the name of the placemark
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the longitude value of the placemark
     * @return the longitude value of the placemark
     */
    public double getLon() {
        return lon;
    }

    /**
     * Sets the longitude value of the placemark
     * @param lon the longitude value of the placemark
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * Gets the latitude value of the placemark
     * @return the latitude value of the placemark
     */
    public double getLat() {
        return lat;
    }

    /**
     * Sets the latitude value of the placemark
     * @param lat the latitude value of the placemark
     */
    public void setLat(double lat) {
        this.lat = lat;
    }
}
