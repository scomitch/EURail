package model;

/**
 *  Route is a class that holds details about a journey between 2 points of a KML Map
 */
public class Route {

    // Instance properties.
    private Placemark locationA;
    private Placemark locationB;
    private String shortDesc;
    private int minuteDuration;

    /**
     * Single overloaded constructor to plot a Route between point A and point B with more information
     * @param locationA first point on the map, being a Placemark
     * @param locationB second point on the map, being a Placemark
     * @param description Small description of the route.
     * @param minuteDuration How long the route will take in minutes.
     */
    public Route(Placemark locationA, Placemark locationB, String description, int minuteDuration){
        this.locationA = locationA;
        this.locationB = locationB;
        this.shortDesc = description;
        this.minuteDuration = minuteDuration;
    }

    /**
     * Returns the first location
     * @return returns a Placemark object location
     */
    public Placemark getLocationA() {
        return locationA;
    }

    /**
     * Sets the first location
     * @param locationA The first Placemark object location
     */
    public void setLocationA(Placemark locationA) {
        this.locationA = locationA;
    }

    /**
     * Returns the second location
     * @return returns a Placemark object location
     */
    public Placemark getLocationB() {
        return locationB;
    }

    /**
     * Sets the second location
     * @param locationB The second Placemark object location
     */
    public void setLocationB(Placemark locationB) {
        this.locationB = locationB;
    }

    /**
     * Gets the short description of the Route
     * @return returns the routes short description
     */
    public String getShortDesc() {
        return shortDesc;
    }

    /**
     * Sets the short description of the Route
     * @param shortDesc The description for the Route
     */
    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    /**
     * Gets the Route's distance time in minutes
     * @return returns the Route's time in minutes
     */
    public int getMinuteDuration() {
        return minuteDuration;
    }

    /**
     * Sets the Route's duration time in minutes
     * @param minuteDuration The amount of minutes the route takes
     */
    public void setMinuteDuration(int minuteDuration) {
        this.minuteDuration = minuteDuration;
    }

}
