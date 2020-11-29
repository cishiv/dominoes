package com.github.cishiv.dominoes.game;

import com.github.cishiv.dominoes.types.board.Orientation;
import com.github.cishiv.dominoes.types.board.Tile;
import org.junit.Assert;
import org.junit.Test;

public class TileTest {

    @Test
    public void testTileInitializesCorrectly() {
        Tile tile = new Tile(0, 1);
        Assert.assertEquals(tile.getL().getValue(), 0);
        Assert.assertEquals(tile.getR().getValue(), 1);
        Assert.assertEquals(tile.getOrientation(), Orientation.DEFAULT);
    }

    @Test
    public void testTileDefaultOrientationPrintsCorrectly() {
        Tile tile = new Tile(0, 1);
        Assert.assertEquals(tile.prettyPrint(Orientation.DEFAULT), "<0:1>");
    }

    @Test
    public void testTileInverseOrientationPrintsCorrectly() {
        Tile tile = new Tile(0, 1);
        Assert.assertEquals(tile.prettyPrint(Orientation.INVERSE), "<1:0>");
    }
}
