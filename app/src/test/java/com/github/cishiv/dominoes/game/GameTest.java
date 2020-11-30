package com.github.cishiv.dominoes.game;

import com.carrotsearch.junitbenchmarks.AbstractBenchmark;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.github.cishiv.dominoes.types.game.Game;
import org.junit.Assert;
import org.junit.Test;

public class GameTest extends AbstractBenchmark {

    // evaluate a complete line of play to make sure the game is executed as per the rules.
    // 1000 test runs _just_ to make sure we don't miss an edge case.
    // Dev Note: Although logic is not great in unit tests, we need a little here just to cater for the different EXIT conditions our game has
    @BenchmarkOptions(benchmarkRounds = 1000, warmupRounds = 0)
    @Test
    public void testWhenAGameCompletesTheResultantGameBoardHasAdjacentPairsWithValuesThatAreEqual() {
        Game game = new Game("test_player_1", "test_player_2");
        game.start();
        String state = game.ticker();
        String[] adjacentValues = state.split("");
        if(game.isValid()) {
            for (int i = 2; i < adjacentValues.length - 1; i = i + 2) {
                if(i == adjacentValues.length - 1) break;
                Assert.assertEquals(adjacentValues[i-1], adjacentValues[i]);
            }
        }
        Assert.assertNotNull(adjacentValues);
    }
}
