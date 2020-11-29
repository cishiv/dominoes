package com.github.cishiv.dominoes;

import com.github.cishiv.dominoes.types.game.Game;

public class Dominoes {
    public static void main(String[] args) {
        Game game = new Game("Alice", "Bob");
        game.start();
    }
}
