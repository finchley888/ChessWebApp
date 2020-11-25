package models;

public abstract class Piece {
    private Colour colour;
    private boolean isAlive;
    private String Symbol;
    private int moveCounter;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public Piece (Colour colour){
        this.colour = colour;
        this.isAlive = true;
        this.moveCounter = 0;
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

    public int getMoveCounter() {
        return moveCounter;
    }

    public void updateMoveCounter(){
        this.moveCounter ++;
    }

    public abstract int[] giveValidMoves(Board board, Square current);
}
