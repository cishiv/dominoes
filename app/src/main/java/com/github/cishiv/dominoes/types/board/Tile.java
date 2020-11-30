package com.github.cishiv.dominoes.types.board;

import com.github.cishiv.dominoes.types.game.Move;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private Side l;
    private Side r;
    private PrintOrientation printOrientation;

    public Tile(int l, int r) {
        this.l = new Side(l, true);
        this.r = new Side(r, true);
        this.printOrientation = PrintOrientation.DEFAULT;
    }


    public String prettyPrint(PrintOrientation printOrientation) {
        switch (printOrientation) {
            case DEFAULT -> {
                return String.format("<%d:%d>", l.getValue(), r.getValue());
            }
            case INVERSE -> {
                return String.format("<%d:%d>", r.getValue(), l.getValue());
            }
        }
        return null;
    }

    public boolean isLeftOpen() {
        return l.isOpen();
    }

    public boolean isRightOpen() {
        return r.isOpen();
    }

    public void toggleLeft() {
        l.setOpen(false);
    }

    public void toggleRight() {
        r.setOpen(false);
    }

    public Side getL() {
        return l;
    }

    public Side getR() {
        return r;
    }

    public void inverseOrientation() {
        this.printOrientation = PrintOrientation.INVERSE;
    }

    public void defaultOrientation() {
        this.printOrientation = PrintOrientation.DEFAULT;
    }

    public List<Move> getPossibleMoves(Tile that) {
        List<Move> possibleMoves = new ArrayList<>();

        // if its an edge domino (i.e. both sides are equal, don't support flip layouts
        // we 'hard-code' the side of the domino to join by enforcing a strict layout rule for edge dominoes.
        // this lets us bake the logic into this class, rather than bloating the core game code.
        // Dev Note: a section of code like this can probably be refactored and encapsulated into a 'MoveSpecification' utility class which runs
        // a matcher against 2 Tiles, to determine a set / list of viable moves.
        // i.e. given a search_tile and a target_tile, find the moves such that search_tile is playable against target_tile.
        if(this.getL() == this.getR()) {
            if (that.isRightOpen() && (that.getR().getValue() == this.getL().getValue() || (that.getR().getValue() == this.getR().getValue())))
                possibleMoves.add(new Move(this, that, Layout.LR, false));

            if (that.isLeftOpen() && (that.getL().getValue() == this.getR().getValue()) || that.getL().getValue() == this.getL().getValue()) {
                possibleMoves.add(new Move(this, that, Layout.RL, false));
            }
        } else {
            if (that.isLeftOpen() && (that.getL().getValue() == this.getL().getValue())) {
                possibleMoves.add(new Move(this, that, Layout.LL, false));
            }
            if (that.isRightOpen() && (that.getR().getValue() == this.getL().getValue()))
                possibleMoves.add(new Move(this, that, Layout.LR, false));

            if (that.isLeftOpen() && (that.getL().getValue() == this.getR().getValue()))
                possibleMoves.add(new Move(this, that, Layout.RL, false));

            if (that.isRightOpen() && (that.getR().getValue() == this.getR().getValue())) {
                possibleMoves.add(new Move(this, that, Layout.RR, false));
            }
        }
        return possibleMoves;
    }

    public PrintOrientation getOrientation() {
        return printOrientation;
    }

    @Override
    public String toString() {
        return prettyPrint(this.printOrientation);
    }
}
