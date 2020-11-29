package com.github.cishiv.dominoes.game;

import com.github.cishiv.dominoes.types.game.Game;
import org.junit.Assert;

public class GameTest {

    // evaluate a complete line of play to make sure the game is executed as per the rules.
    // edge case - exhausted stock TODO
    public void testWhenAGameCompletesTheResultantGameBoardHasAdjacentPairsWithValuesThatAreEqual() {
        Game game = new Game("test_player_1", "test_player_2");
        game.start();
        String state = game.ticker();
        String[] adjacentValues = state.split("");
        for (int i = 2; i < adjacentValues.length - 1; i = i + 2) {
            if(i == adjacentValues.length - 1) break;
            Assert.assertEquals(adjacentValues[i-1], adjacentValues[i]);
        }
    }
}
