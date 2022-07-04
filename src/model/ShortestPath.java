package model;

public class ShortestPath {


    public ShortestPath(){

    }
    //Tochange
    public static final int NODES = 9;

    public int minDistance(int dist[], Boolean shortestPathSet[]){
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int i = 0; i < NODES; i++){
            if(!shortestPathSet[i] && dist[i] <= min){
                min = dist[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public void printSolution(int dist[], int n){
        System.out.println("Node distance from source");
        for(int i = 0; i < NODES; i++){
            System.out.println(i + " travel time " + dist[i]);
        }
    }
}
