package models;

public class King extends Piece{
    public King(Colour colour) {
        super(colour);
        if(colour == Colour.BLACK){
            super.setSymbol("♚");
        } else {
            super.setSymbol("♔");
        }
    }

    @Override
    public boolean isValidMove(Board board, Square current, Square target) {
        return false;
    }
}
