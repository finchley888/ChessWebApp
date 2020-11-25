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
    public int[] giveValidMoves(Board board, Square current){
        // TODO: Finish Bishop move validation
        return new int[]{0};
    }
}
