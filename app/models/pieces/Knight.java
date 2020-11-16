package models.pieces;

import models.Colour;
import models.board.Board;
import models.board.Square;

public class Knight extends Piece {
    public Knight(Colour colour) {
        super(colour);
    }

    @Override
    public boolean isValidMove(Board board, Square current, Square target) {
        return false;
    }
}
