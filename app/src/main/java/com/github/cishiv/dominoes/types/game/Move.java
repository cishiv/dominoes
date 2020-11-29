package com.github.cishiv.dominoes.types.game;

import com.github.cishiv.dominoes.types.board.Parity;
import com.github.cishiv.dominoes.types.board.Tile;

public class Move {
    private Tile playerTile;
    private Tile boardTile;
    private Parity parity;
    private boolean handEmpty;

    public Move(Tile playerTile, Tile boardTile, Parity parity, boolean handEmpty) {
        this.playerTile = playerTile;
        this.boardTile = boardTile;
        this.parity = parity;
        this.handEmpty = handEmpty;
    }


    public Tile getPlayerTile() {
        return playerTile;
    }

    public Parity getParity() {
        return parity;
    }

    public Tile getBoardTile() {
        return boardTile;
    }

    public boolean isHandEmpty() {
        return handEmpty;
    }

    public void setHandEmpty(boolean handEmpty) {
        this.handEmpty = handEmpty;
    }

    @Override
    public String toString() {
        return String.format("Possible move with %s to %s with direction %s", playerTile.toString(), boardTile.toString(), parity);
    }
}
