package models;

public abstract class Piece {
    private Colour colour;
    private boolean isAlive;
    private String Symbol;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

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

    public abstract boolean isValidMove(Board board, Square current, Square target);
}
