package models.pieces;

import models.Colour;
import models.board.Board;
import models.board.Square;

public class Queen extends Piece{
    public Queen(Colour colour) {
        super(colour);
    }

    @Override
    public boolean isValidMove(Board board, Square current, Square target) {
        return false;
    }
}
