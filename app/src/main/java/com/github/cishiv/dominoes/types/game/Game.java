package com.github.cishiv.dominoes.types.game;

import com.github.cishiv.dominoes.types.board.Stock;
import com.github.cishiv.dominoes.types.board.Tile;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Game {

    private static final int INITIAL_HAND_SIZE = 7;
    private Player playerA;
    private Player playerB;

    private LinkedList<Tile> inPlay;
    private Stock stock;
    private List<Tile> availableMoves;
    private boolean ongoing;

    public Game(String playerAName, String playerBName) {
        this.playerA = new Player(playerAName);
        this.playerB = new Player(playerBName);
        this.inPlay = new LinkedList<>();
        this.stock = new Stock();
        this.ongoing = false;
        this.availableMoves = new LinkedList<>();
    }

    public void addToPlay(Tile addition) {
        inPlay.add(addition);
    }

    public void start() {
        // game set up
        setup();
        while (ongoing) {
            Optional<Move> playerAMove = playerA.play(inPlay.getFirst(), inPlay.getLast(), stock);
            if(playerAMove.isPresent()) {
                applyMove(playerAMove.get());
                System.out.printf("%s\n", formattedBoardOutput());
                if(playerAMove.get().isHandEmpty()) {
                    System.out.printf("Player %s has won!", playerA.getPlayerName());
                    break;
                }
            } else {
                ongoing = false;
                System.out.printf("Stock exhausted, %s wins by elimination, player %s was unable to play!", playerB.getPlayerName(), playerA.getPlayerName());
                break;
            }

            Optional<Move> playerBMove = playerB.play(inPlay.getFirst(), inPlay.getLast(), stock);
            if(playerBMove.isPresent()) {
                applyMove(playerBMove.get());
                System.out.printf("%s\n", formattedBoardOutput());
                if(playerBMove.get().isHandEmpty()) {
                    System.out.printf("Player %s has won!", playerB.getPlayerName());
                    break;
                }
            } else {
                ongoing = false;
                System.out.printf("Stock exhausted, %s wins by elimination, player %s was unable to play!", playerA.getPlayerName(), playerB.getPlayerName());
                break;
            }

        }
    }

    private void applyMove(Move playerMove) {
        int boardIndex = inPlay.indexOf(playerMove.getBoardTile());
        switch (playerMove.getParity()) {
            case RR -> {
                playerMove.getPlayerTile().toggleRight();
                inPlay.get(boardIndex).toggleRight();
            }
            case RL -> {
                playerMove.getPlayerTile().toggleRight();
                inPlay.get(boardIndex).toggleLeft();
            }
            case LL -> {
                playerMove.getPlayerTile().toggleLeft();
                playerMove.getPlayerTile().inverseOrientation();
                inPlay.get(boardIndex).toggleLeft();
            }
            case LR -> {
                playerMove.getPlayerTile().toggleLeft();
                playerMove.getPlayerTile().inverseOrientation();
                inPlay.get(boardIndex).toggleRight();
            }
        }
        placeCorrectly(playerMove);
    }

    private void placeCorrectly(Move playerMove) {
        if(inPlay.getFirst().equals(playerMove.getBoardTile())) {
            inPlay.addFirst(playerMove.getPlayerTile());
        } else {
            inPlay.addLast(playerMove.getPlayerTile());
        }
    }

    private void setup() {
        ongoing = true;
        playerA.draw(stock, INITIAL_HAND_SIZE);
        playerB.draw(stock, INITIAL_HAND_SIZE);
        Tile first = stock.pop(1).get(0);
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
