package models;

public class Rook extends Piece {

    public Rook(Colour colour) {
        super(colour);
        if (colour == Colour.BLACK){
            super.setSymbol("♜");
        } else {
            super.setSymbol("♖");
        }
    }

    @Override
    public int[] giveValidMoves(Board board, Square current){
        // TODO: Finish this move validation
        return new int[]{0};
    }


}
