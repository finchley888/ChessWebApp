package models;

public class PlaceHolderPiece extends Piece {
    public PlaceHolderPiece(Colour colour) {
        super(colour);
        {
            super.setSymbol("•");
        }
    }

    @Override
    public int[] giveValidMoves(Board board, Square current){
        return new int[]{};
    }

    @Override
    public boolean isValidMove(Board board, Square current) {
        return false;
    }
}
