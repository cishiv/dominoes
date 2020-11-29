package com.github.cishiv.dominoes.game;

import com.github.cishiv.dominoes.types.board.Stock;
import com.github.cishiv.dominoes.types.board.Tile;
import com.github.cishiv.dominoes.types.game.Move;
import com.github.cishiv.dominoes.types.game.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerTest {

    @Test
    public void testGivenTwoTilesWithOnePossibleMoveTheMoveIsChosenAndTheStockAndHandAreUpdatedCorrectly() {
        Stock stock = new Stock();
        Tile firstTile = new Tile(1, 6);
        Tile lastTile = new Tile(0, 4);
        Tile playerTile = new Tile(3, 6);
        List<Tile> playerHand = new ArrayList<>();
        playerHand.add(playerTile);
        Player player = new Player("test_player");
        player.setPlayerHand(playerHand);
        Optional<Move> move = player.play(firstTile, lastTile, stock);

        Assert.assertTrue(move.isPresent());
        Assert.assertEquals(firstTile, move.get().getBoardTile());
        Assert.assertEquals(playerTile, move.get().getPlayerTile());
    }

    @Test
    public void givenAStockAPlayerInstanceIsAbleToPlayThrough() {
        Stock stock = new Stock();
        Tile firstTile = stock.pop(1).get(0);
        Player player = new Player("test_player");
        player.draw(stock, 7);
        player.play(firstTile, firstTile, stock);
        Assert.assertNotEquals(28, stock.size());
    }
}
