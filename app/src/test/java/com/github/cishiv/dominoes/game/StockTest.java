package com.github.cishiv.dominoes.game;

import com.github.cishiv.dominoes.types.board.Stock;
import com.github.cishiv.dominoes.types.board.Tile;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StockTest {

    @Test
    public void testStockInitializationIsCorrectSize() {
        Stock stock = new Stock();
        Assert.assertEquals(28, stock.size());
    }

    @Test
    public void testPoppingATileFromTheStockResultsInReducedSizeAndReturnsAListOfPoppedTiles() {
        Stock stock = new Stock();
        List<Tile> popped = stock.pop(1);
        Assert.assertEquals(27, stock.size());
        Assert.assertEquals(1, popped.size());
    }
}
