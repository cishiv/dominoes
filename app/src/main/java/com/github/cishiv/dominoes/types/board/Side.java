package com.github.cishiv.dominoes.types.board;

public class Side {
    private int value;
    private boolean open;

    public Side(int value, boolean open) {
        this.value = value;
        this.open = open;
    }

    public int getValue() {
        return value;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
