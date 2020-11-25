package models;

public class Queen extends Piece {
    public Queen(Colour colour) {
        super(colour);
        if (colour == Colour.BLACK) {
            super.setSymbol("♛");
        } else {
            super.setSymbol("♕");
        }
    }

    @Override
    public int[] giveValidMoves(Board board, Square current) {
        //TODO: Finish this move validation
        return new int[]{0};
    }

    @Override
    public boolean isValidMove(Board board, Square current) {
        return false;
    }
}
