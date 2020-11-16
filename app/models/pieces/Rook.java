package models.pieces;

import models.Colour;
import models.board.Board;
import models.board.Square;

public class Rook extends Piece {

    public Rook(Colour colour) {
        super(colour);
    }

    @Override
    public boolean isValidMove(Board board, Square current, Square target) {
        return true;
    }
}
