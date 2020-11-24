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
    public boolean isValidMove(Board board, Square current, Square target) {
        return true;
    }


}
