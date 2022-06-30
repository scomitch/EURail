package model;

public class RouteEdge {

    private PlacemarkNode pointA;
    private PlacemarkNode pointB;
    private int weight;

    public RouteEdge(PlacemarkNode pointA, PlacemarkNode pointB, int weight){
        this.pointA = pointA;
        this.pointB = pointB;
        this.weight = weight;
    }

    public PlacemarkNode getPointA() {
        return pointA;
    }

    public void setPointA(PlacemarkNode pointA) {
        this.pointA = pointA;
    }

    public PlacemarkNode getPointB() {
        return pointB;
    }

    public void setPointB(PlacemarkNode pointB) {
        this.pointB = pointB;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
