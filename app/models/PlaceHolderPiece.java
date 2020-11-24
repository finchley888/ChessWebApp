package models;

public class PlaceHolderPiece extends Piece {
    public PlaceHolderPiece(Colour colour) {
        super(colour);
        {
            super.setSymbol("•");
        }
    }

    @Override
    public boolean isValidMove(Board board, Square current, Square target) {
        return false;
    }
}
