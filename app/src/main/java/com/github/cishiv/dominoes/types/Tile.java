package com.github.cishiv.dominoes.types;

public class Tile {
    private Side sideA;
    private Side sideB;
    private Orientation orientation;

    public Tile(int valueA, int valueB) {
        this.sideA = new Side(valueA);
        this.sideB = new Side(valueB);
        // This is immutable
        this.orientation = Orientation.DEFAULT;
    }

    public String prettyPrint(Orientation orientation) {
        if(orientation.equals(Orientation.DEFAULT)) return String.format("<%d,%d>", sideA.getValue(), sideB.getValue());
        return String.format("<%d:%d>", sideA.getValue(), sideB.getValue());
    }

    public String toString() {
        return prettyPrint(this.orientation);
    }
}
