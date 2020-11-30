package com.github.cishiv.dominoes.types.game;

import com.github.cishiv.dominoes.types.board.Layout;
import com.github.cishiv.dominoes.types.board.Stock;
import com.github.cishiv.dominoes.types.board.Tile;

import java.util.LinkedList;
import java.util.Optional;

public class Game {

    private static final int INITIAL_HAND_SIZE = 7;
    private Player playerA;
    private Player playerB;

    private LinkedList<Tile> inPlay;
    private Stock stock;
    private boolean valid;

    /**
     * For testing purposes
     * Provides a slice of game-state at the end of a run, which lets us verify everything hangs together through a unit test.
     * Values on the ticker are appended in the exact order they are played in the line of play.
     */
    private String ticker;

    public Game(String playerAName, String playerBName) {
        this.playerA = new Player(playerAName);
        this.playerB = new Player(playerBName);
        this.inPlay = new LinkedList<>();
        this.stock = new Stock();
        this.valid = false;
        this.ticker = new String();
    }

    public void addToPlay(Tile addition) {
        inPlay.add(addition);
    }

    public void start() {
        setup();
        while (valid) {
            Optional<Move> playerAMove = playerA.play(inPlay.getFirst(), inPlay.getLast(), stock);
            if (playerAMove.isPresent()) {
                applyMove(playerAMove.get());
                System.out.printf("%s\n", formattedBoardOutput());
                if (playerAMove.get().isHandEmpty()) {
                    System.out.printf("Player %s has won!", playerA.getPlayerName());
                    break;
                }
            } else {
                valid = false;
                System.out.printf("Stock exhausted, %s wins by elimination, player %s was unable to play!", playerB.getPlayerName(), playerA.getPlayerName());
                break;
            }

            Optional<Move> playerBMove = playerB.play(inPlay.getFirst(), inPlay.getLast(), stock);
            if (playerBMove.isPresent()) {
                applyMove(playerBMove.get());
                System.out.printf("%s\n", formattedBoardOutput());
                if (playerBMove.get().isHandEmpty()) {
                    System.out.printf("Player %s has won!", playerB.getPlayerName());
                    break;
                }
            } else {
                valid = false;
                System.out.printf("Stock exhausted, %s wins by elimination, player %s was unable to play!", playerA.getPlayerName(), playerB.getPlayerName());
                break;
            }

        }
    }

    // simplification for unit testing
    public String ticker() {
        return ticker.toString();
    }

    public void appendTicker(Move move, boolean before) {
        if (before) ticker = move.getPlayerTile().toString().replaceAll("[^0-9.]", "") + ticker;
        else ticker = ticker + move.getPlayerTile().toString().replaceAll("[^0-9.]", "");
    }

    // an invalid game is one where the stock has been exhausted i.e. a full playthrough did not occur, and a player won by default.
    public boolean isValid() {
        return valid;
    }

    private void applyMove(Move playerMove) {
        int boardIndex = inPlay.indexOf(playerMove.getBoardTile());
        switch (playerMove.getParity()) {
            case RR -> {
                playerMove.getPlayerTile().toggleRight();
                inPlay.get(boardIndex).toggleRight();
                placeRightCorrectly(playerMove);
                break;
            }
            case RL -> {
                playerMove.getPlayerTile().toggleRight();
                inPlay.get(boardIndex).toggleLeft();
                placeRightCorrectly(playerMove);
                break;
            }
            case LL -> {
                playerMove.getPlayerTile().toggleLeft();
                inPlay.get(boardIndex).toggleLeft();
                placeLeftCorrectly(playerMove);
                break;
            }
            case LR -> {
                playerMove.getPlayerTile().toggleLeft();
                inPlay.get(boardIndex).toggleRight();
                placeLeftCorrectly(playerMove);
                break;
            }
        }
    }

    private void placeLeftCorrectly(Move playerMove) {
        // Similar to a right alignment, a left-right layout on the first move, but abide by a specific set of rules - the first tile must be placed to the RIGHT
        // of the starting tile, if the layout is left-right.
        if (inPlay.size() == 1) {
            if (playerMove.getParity().equals(Layout.LR)) {
                inPlay.addLast(playerMove.getPlayerTile());
                appendTicker(playerMove, false);
            }
        } else {
            if (inPlay.getFirst().equals(playerMove.getBoardTile())) {
                playerMove.getPlayerTile().inverseOrientation();
                inPlay.addFirst(playerMove.getPlayerTile());
                appendTicker(playerMove, true);
            } else {
                inPlay.addLast(playerMove.getPlayerTile());
                appendTicker(playerMove, false);
            }
        }
    }

    private void placeRightCorrectly(Move playerMove) {
        // In the case of the first tile placement, a right-left layout, means that the player tile should be placed 'as is' IN FRONT of the pre-existing starting tile
        // in any other case for a right-x layout - the pointer of the board-tile we are playing against, determines the resulting layout.
        if (inPlay.size() == 1) {
            if (playerMove.getParity().equals(Layout.RL)) {
                inPlay.addFirst(playerMove.getPlayerTile());
                appendTicker(playerMove, true);
            }
        } else {
            if (inPlay.getFirst().equals(playerMove.getBoardTile())) {
                inPlay.addFirst(playerMove.getPlayerTile());
                appendTicker(playerMove, true);
            } else {
                playerMove.getPlayerTile().inverseOrientation();
                inPlay.addLast(playerMove.getPlayerTile());
                appendTicker(playerMove, false);
            }
        }
    }

    private void setup() {
        valid = true;
        playerA.draw(stock, INITIAL_HAND_SIZE);
        playerB.draw(stock, INITIAL_HAND_SIZE);
        Tile first = stock.pop(1).get(0);
        ticker = first.toString().replaceAll("[^0-9.]", "");
        System.out.printf("Game starting with first tile: %s\n", first.toString());
        addToPlay(first);
    }

    private String formattedBoardOutput() {
        StringBuilder builder = new StringBuilder("Board is now:\s");
        inPlay.forEach(tile -> {
            builder.append(tile.toString() + "\s");
        });
        return builder.toString();
    }


}
