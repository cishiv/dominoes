package com.github.cishiv.dominoes.types;

public class Side {
    private int value;
    private boolean open;

    public Side(int value) {
        this.value = value;
        this.open = true;
    }

    public int getValue() {
        return value;
    }
}
