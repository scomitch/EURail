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

    public void setPlacemarks(List<PlacemarkNode> placemarks) { this.placemarks = placemarks; }


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

    public RouteEdge hasEdge(PlacemarkNode pointA, PlacemarkNode pointB){
        for(RouteEdge edge : routes){
            if(edge.getPointA().getName().equals(pointA.getName()) && edge.getPointB().getName().equals(pointB.getName())){
                return edge;
            }
        }
        return null;
    }

    public int minDistance(int dist[], Boolean shortestPathSet[]){
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int i = 0; i < placemarks.size(); i++){
            if(!shortestPathSet[i] && dist[i] <= min){
                min = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public void dijkstra(PlacemarkNode source, String end){
        int dist[] = new int[placemarks.size()];

        Boolean sptSet[] = new Boolean[placemarks.size()];

        for(int i = 0; i < placemarks.size(); i++){
            if(placemarks.get(i) == source){
                dist[i] = 0;
            } else {
                dist[i] = Integer.MAX_VALUE;
            }
            sptSet[i] = false;
        }

        for(int count = 0; count < placemarks.size() - 1; count++){
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for(int v = 0; v < placemarks.size(); v++){
                boolean stop = true;
                PlacemarkNode pmA = placemarks.get(u);
                PlacemarkNode pmB = placemarks.get(v);
                RouteEdge e = hasEdge(pmA, pmB);
                if(!sptSet[v] && e != null && (dist[u] + e.getWeight()) < dist[v]){
                    dist[v] = dist[u] + e.getWeight();
                    if(pmA.getName().equals(end) || pmB.getName().equals(end)){
                        System.out.println("The path with the smallest weight between " + source.getName() + " and " + end + " is " + dist[v]);
                        break;
                    }
                }
            }
        }

        printSolution(dist, source);

    }

    public void printSolution(int[] dis, PlacemarkNode source) {
        System.out.println("Vertex distance from source " + source.getName());

        for (int i = 0; i < dis.length; i++) {
            System.out.println(i + " tt " + dis[i]);
            System.out.println(source.getName() + " to " + placemarks.get(i).getName() + " has travel time of " + dis[i] + " minutes...");
        }
        System.out.println();
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
