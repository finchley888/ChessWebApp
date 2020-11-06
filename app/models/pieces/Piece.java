package models.pieces;

import models.Colour;
import models.board.Board;

public abstract class Piece {
    private Colour colour;
    private boolean isAlive;

    public Piece (Colour colour){
        this.colour = colour;
        this.isAlive = true;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public abstract boolean isValidMove(Board board, Spot start, Spot end);
}
