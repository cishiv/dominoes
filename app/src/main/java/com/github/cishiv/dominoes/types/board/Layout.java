package com.github.cishiv.dominoes.types.board;

/**
 * Defines the layout of 2 tiles relative to one another.
 * ex. LR
 * L - left side of tile 1 is connected to R - right side of tile 2
 */
public enum Layout {
    LR,
    RL,
    RR,
    LL;
}
