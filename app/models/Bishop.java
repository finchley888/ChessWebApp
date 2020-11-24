package models;

public class Bishop extends Piece{
    public Bishop(Colour colour) {
        super(colour);
        if(colour == Colour.BLACK){
            super.setSymbol("♝");
        } else {
            super.setSymbol("♗");
        }
    }

    @Override
    public boolean isValidMove(Board board, Square current, Square target) {
        return false;
    }
}
