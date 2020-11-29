package com.github.cishiv.dominoes.game;

import com.github.cishiv.dominoes.types.board.Tile;
import com.github.cishiv.dominoes.types.game.Game;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;

@Ignore
// Make this work
public class GameTest {

    @Test
    public void testWhenAGameCompletesTheResultantGameBoardHasRightEqualToLeftValuesInAdjacentEntries() {
        Game game = new Game("test_player_1", "test_player_2");
        game.start();
        LinkedList<Tile> board = game.getBoard();
        for(int i = 1; i < board.size(); i++) {
            Assert.assertEquals(board.get(i-1).getR().getValue(), board.get(i).getL().getValue());
        }
    }
}
