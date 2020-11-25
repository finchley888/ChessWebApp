package models;

public class Knight extends Piece {
    public Knight(Colour colour) {
        super(colour);
        if(colour == Colour.BLACK){
            super.setSymbol("♞");
        } else {
            super.setSymbol("♘");
        }
    }

    @Override
    public int[] giveValidMoves(Board board, Square current){
        // TODO: Finish Knight move validation
        return new int[]{0};
    }

    @Override
    public boolean isValidMove(Board board, Square current) {
        return false;
    }
}
