package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class NetworkGraph {
    private HashMap<PlacemarkNode, LinkedList<PlacemarkNode>> connectionMap;
    // Note no implementation of directed. Our implementation is solely directed.
    private List<PlacemarkNode> placemarks;
    private List<RouteEdge> routes;

    public NetworkGraph(){
        this.placemarks = new ArrayList<>();
        this.routes = new ArrayList<>();
    }

    public void addRoute(PlacemarkNode pointA, PlacemarkNode pointB, int mins){
        addPlacemark(pointA);
        addPlacemark(pointB);

        //No checks for directed, as graph is undirected.
        routes.add(new RouteEdge(pointA, pointB, mins));
        routes.add(new RouteEdge(pointB, pointA, mins));
    }

    public void addPlacemark(PlacemarkNode placemark){
        if(!placemarks.contains(placemark)){
            placemarks.add(placemark);
        }
    }

    public PlacemarkNode removePlacemark(PlacemarkNode node){
        if(placemarks.contains(node)){
            placemarks.remove(node);
            return node;
        }
        return null;
    }

    public void removeRoute(RouteEdge route){
        routes.remove(route);
    }

    public List<RouteEdge> getRoutes(){
        return this.routes;
    }

    public List<PlacemarkNode> getPlacemarks(){
        return this.placemarks;
    }


    public void printEdges(){
        // Loop through nodes in the connectionsMap.
        for(PlacemarkNode node : connectionMap.keySet()){
            System.out.print(node.getName() + " links to ");
            for(PlacemarkNode connection : connectionMap.get(node)){
                System.out.print(connection.getName());
            }
            System.out.println();
        }
    }

    public boolean hasEdge(PlacemarkNode pointA, PlacemarkNode pointB){
        return connectionMap.containsKey(pointA) && connectionMap.get(pointA).contains(pointB);
    }

    public void depthFirstSearch(PlacemarkNode target){
        target.setVisited();
        System.out.print(target.getName() + " ");

        LinkedList<PlacemarkNode> allNeighbours = connectionMap.get(target);
        if(allNeighbours == null){
            return;
        }

        for(PlacemarkNode neighbour : allNeighbours){
            if(!neighbour.isVisited()){
                depthFirstSearch(neighbour);
            }
        }
    }

    public void breadthFirstSearch(PlacemarkNode node){
        if(node == null) return;

        LinkedList<PlacemarkNode> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()){
            PlacemarkNode current = queue.removeFirst();

            if(current.isVisited()) {
                continue;
            }

            current.setVisited();
            System.out.print(current.getName() + " ");

            LinkedList<PlacemarkNode> allNeighbours = connectionMap.get(current);
            if(allNeighbours == null) continue;

            for(PlacemarkNode neighbour : allNeighbours){
                if(!neighbour.isVisited()){
                    queue.add(neighbour);
                }
            }
        }
        System.out.println();
    }

}
