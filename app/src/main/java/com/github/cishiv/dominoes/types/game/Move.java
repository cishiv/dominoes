package com.github.cishiv.dominoes.types.game;

import com.github.cishiv.dominoes.types.board.Layout;
import com.github.cishiv.dominoes.types.board.Tile;

public class Move {

    private Tile playerTile;
    private Tile boardTile;
    private Layout layout;
    private boolean handEmpty;

    public Move(Tile playerTile, Tile boardTile, Layout layout, boolean handEmpty) {
        this.playerTile = playerTile;
        this.boardTile = boardTile;
        this.layout = layout;
        this.handEmpty = handEmpty;
    }

    public Tile getPlayerTile() {
        return playerTile;
    }

    public Layout getParity() {
        return layout;
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
        return String.format("Possible move with %s to %s with direction %s", playerTile.toString(), boardTile.toString(), layout);
    }
}
