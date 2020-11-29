package com.github.cishiv.dominoes;

import com.github.cishiv.dominoes.types.Tile;

import java.util.*;

public class Dominoes {

    // edge case: draw (no tiles remaining, and no plays)
    public static void main(String[] args) {
    }

    /**
     * Generate the 28 tiles required to play a game of dominoes.
     * note: Although these tiles can be enumerated (since their values don't change), generating them _reads_ a bit better.
     * @return A shuffled [] of dominoes
     */
    public static List<Tile> generateTiles() {
        List<Tile> tiles = new LinkedList<>();
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < i + 1; j ++) {
                tiles.add(new Tile(i, j));
            }
        }
        Collections.shuffle(
                tiles,
                new Random()
        );
        return tiles;
    }
}
