package com.github.cishiv.dominoes.types;

import java.util.HashMap;

public class Player {
    private String name;
    public Player(String name) {
        this.name = name;
    }

    public void play(Tile selectedTile, Tile targetTile) {
        System.out.printf("%s plays %s to connect to tile %s on the board\n", name, selectedTile.toString(), targetTile.toString());
    }

}
