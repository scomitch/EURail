package misc;

import java.awt.*;

public class Helpers {

    public static Point latlonToPixel(double lat, double lon){

        //Vars
        int mapWidth = 796;
        int mapHeight = 877;
        double mapLonL = -14.0625;
        double mapLonR = 41.8359375;
        double mapLatB = 34.5970415;
        double mapLatBottomRad = mapLatB * Math.PI / 180;
        double latitudeRad = (lat * Math.PI) / 180;

        double mapLonDelta = mapLonR - mapLonL;
        double worldMapWidth = (mapWidth / mapLonDelta * 360) / (2 * Math.PI);
        double mapOffsetY = worldMapWidth / 2 * Math.log((1 + Math.sin(mapLatBottomRad)) / (1 - Math.sin(mapLatBottomRad)));

        double x = (lon - mapLonL) * (mapWidth / mapLonDelta);
        double y = mapHeight - ((worldMapWidth / 2 * Math.log((1 + Math.sin(latitudeRad)) / (1 - Math.sin(latitudeRad)))) - mapOffsetY);

        return new Point((int) x, (int) y);

    }

}
