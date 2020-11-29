package com.github.cishiv.dominoes.types.board;

import com.github.cishiv.dominoes.types.game.Move;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private Side l;
    private Side r;
    private Orientation orientation;

    public Tile(int l, int r) {
        this.l = new Side(l, true);
        this.r = new Side(r, true);
        this.orientation = Orientation.DEFAULT;
    }


    public String prettyPrint(Orientation orientation) {
        switch (orientation) {
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
        this.orientation = Orientation.INVERSE;
    }

    public void invert() {
        Side newR = new Side(r.getValue(), r.isOpen());
        r.setOpen(l.isOpen());
        r.setValue(l.getValue());
        l.setOpen(newR.isOpen());
        l.setValue(newR.getValue());
    }

    public void defaultOrientation() {
        this.orientation = Orientation.DEFAULT;
    }

    public List<Move> getPossibleMoves(Tile that) {
        List<Move> possibleMoves = new ArrayList<>();

        // if its an edge domino (i.e. both sides are equal, don't support flip layouts)
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

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return prettyPrint(this.orientation);
    }
}
