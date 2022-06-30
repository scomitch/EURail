package controller;

import misc.Helpers;
import model.Placemark;
import model.Route;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Draw Controller handles all the directions to draw our Map onto a JPanel and map co-ordinates.
 */
public class DrawRoutesController extends JPanel {

    private BufferedImage europeMap;
    private List<Route> routes;

    public DrawRoutesController(List<Route> routes) throws IOException {
        this.europeMap = drawImage();
        this.routes = routes;
    }

    private BufferedImage drawImage() throws IOException {
        File file = new File("src/data/europe-map6.png");
        return ImageIO.read(file);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(europeMap, 0, 0, null);
        Graphics2D g2d = (Graphics2D)g;
        if(routes.isEmpty()) return;
        for(Route route : routes){
            //Let's first begin by plotting all of the lines.
            Point stationA = Helpers.latlonToPixel(route.getLocationA().getLat(), route.getLocationA().getLon());
            Point stationB = Helpers.latlonToPixel(route.getLocationB().getLat(), route.getLocationB().getLon());

            //Draw the line between x,y of station A to Station B
            g.drawLine(stationA.x, stationA.y, stationB.x, stationB.y);

            //We still want to draw the name of the route, so let's do that.
            //g.drawString(route.getShortDesc(), ((stationA.x + stationB.x) / 2) + 5, ((stationA.y + stationB.y) / 2));
//            Ellipse2D.Double circle = new Ellipse2D.Double(coords.x - 5, coords.y - 5, 10, 10);
//            g2d.fill(circle);
        }

    }

}
