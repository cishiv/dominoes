package com.github.cishiv.dominoes.types.board;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Stock {

    private Random selector = new Random();
    private List<Tile> availableTiles = new LinkedList<>();

    public Stock() {
        this.init();
    }

    public List<Tile> pop(int number) {
        List<Tile> popped = new LinkedList<>();
        for (int i = 0; i < number; i++) {
            int idx = selector.nextInt(availableTiles.size());
            Tile selected = new Tile(availableTiles.get(idx).getL().getValue(), availableTiles.get(idx).getR().getValue());
            popped.add(selected);
            availableTiles.remove(idx);
        }

        return popped;
    }

    public int size() {
        return availableTiles.size();
    }

    private void init() {
        this.availableTiles = generateTiles();
    }

    /**
     * Generate the 28 tiles required to play a game of dominoes.
     * note: Although these tiles can be enumerated (since their values don't change), generating them _reads_ a bit better.
     * @return A shuffled [] of dominoes
     */
    private List<Tile> generateTiles() {
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
