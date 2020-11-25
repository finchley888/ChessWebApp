package models;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Colour colour) {
        super(colour);
        if(colour == Colour.BLACK){
            super.setSymbol("♞");
        } else {
            super.setSymbol("♘");
        }
    }

    @Override
    public int[] giveValidMoves(Board board, Square current){
        //TODO: fix this movement
        List<Integer> validMoves = new ArrayList<Integer>();

        int currentRow = current.getRow();
        int currentColumn = current.getColumn();

        for (int row = -8; row < 9; row++) {
            for (int column = -8; column < 9; column++) {
                int tempCol = currentColumn + column;
                int tempRow = currentRow + row;
                // check bounds
                if(tempRow < 9 && tempRow > 0 && tempCol < 9 && tempCol > 0 ){
                    if (board.getSymbolOnSquare(board.accessElement(tempCol, tempRow)) == "") {
                        validMoves.add(board.accessElement(tempCol, tempRow));
                    }
                }
            }
        }
        return validMoves.stream().mapToInt(i->i).toArray();
    }

    @Override
    public boolean isValidMove(Board board, Square current) {
        return false;
    }
}
