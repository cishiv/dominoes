package com.github.cishiv.dominoes.types;

// Domino tiles can be oriented in an A:B format or a B:A format.
// By strongly-typing the orientation, we can bake a lot of the 'printf' magic into the object representations for Tile / Side.
public enum Orientation {
    DEFAULT, // A:B
    INVERSE; // B:A
}
