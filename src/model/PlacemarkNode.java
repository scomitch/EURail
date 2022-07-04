package model;

/**
 * Node classes hold the individual elements within a graph
 */
public class PlacemarkNode {
    private int n;
    private String name;
    private boolean visited;

    public PlacemarkNode(int n, String name){
        this.n = n;
        this.name = name;
        this.visited = false;
    }

    public boolean isVisited(){
        return visited;
    }

    public void setVisited(){
        this.visited = true;
    }

    public void setUnvisited(){
        this.visited = false;
    }

    public String getName(){
        return this.name;
    }

    public int getN() { return this.n; }
}
