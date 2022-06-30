package controller;

import misc.Helpers;
import model.Placemark;

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
public class DrawStationController extends JPanel {

    private BufferedImage europeMap;
    private List<Placemark> stations;

    public DrawStationController(List<Placemark> stations) throws IOException {
        this.europeMap = drawImage();
        this.stations = stations;
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
        if(stations.isEmpty()) return;
        for(Placemark station : stations){
            Point coords = Helpers.latlonToPixel(station.getLat(), station.getLon());
            g.drawString(station.getName(), coords.x - 8, coords.y - 6);
            Ellipse2D.Double circle = new Ellipse2D.Double(coords.x - 5, coords.y - 5, 10, 10);
            g2d.fill(circle);
        }
    }

}
