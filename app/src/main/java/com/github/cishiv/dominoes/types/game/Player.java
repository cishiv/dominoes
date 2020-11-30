package com.github.cishiv.dominoes.types.game;

import com.github.cishiv.dominoes.types.board.Stock;
import com.github.cishiv.dominoes.types.board.Tile;

import java.util.*;

public class Player {

    private static final int DRAW_SIZE = 1;

    private Random selector;
    private String playerName;
    private List<Tile> playerHand;

    public Player(String playerName) {
        this.playerHand = new LinkedList<>();
        this.playerName = playerName;
        this.selector = new Random();
    }

    // Since the game is played on 1-axis, there are only 2 viable tiles to play against at a time
    // A tile _can_ potentially have 2 open sides, but that condition only occurs on the first move
    public Optional<Move> play(Tile first, Tile last, Stock stock) {
        List<Move> moves = new ArrayList<>();
        if (first.equals(last)) {
            // first move i.e. we only care about 'first' and both of its sides are fair game
            playerHand.forEach(tile -> {
                // evaluate every move combination
               moves.addAll(tile.getPossibleMoves(first));
            });
        } else {
            // any other move, first or last, only 1 of each of their sides should be fair game.
            playerHand.forEach(tile -> {
                moves.addAll(tile.getPossibleMoves(first));
                moves.addAll(tile.getPossibleMoves(last));
            });
        }

        if(moves.size() > 0) {
            Move move = chooseRandom(moves);
            System.out.printf("%s plays %s to connect to tile %s on the board\n", playerName, move.getPlayerTile().toString(), move.getBoardTile().toString());
            playerHand.remove(move.getPlayerTile());
            if(playerHand.size() == 0) {
                move.setHandEmpty(true);
            }
            return Optional.of(move);
        } else {
            if(stock.size() > 0) {
                Tile drawn = draw(stock, DRAW_SIZE).get(0);
                System.out.printf("%s can't play, drawing tile %s\n", playerName, drawn.toString());
                return play(first, last, stock);
            } else {
                return Optional.empty();
            }
        }
    }

    public List<Tile> draw(Stock stock, int number) {
        List<Tile> drawn = stock.pop(number);
        playerHand.addAll(drawn);
        return drawn;
    }

    private Move chooseRandom(List<Move> moves) {
        return moves.get(selector.nextInt(moves.size()));
    }

    public String getPlayerName() {
        return playerName;
    }

    /** Visible for testing **/
    public void setPlayerHand(List<Tile> playerHand) {
        this.playerHand = playerHand;
    }
}
