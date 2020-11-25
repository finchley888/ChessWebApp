package models;

import java.util.ArrayList;
import java.util.List;

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
    public int[] giveValidMoves(Board board, Square current){
        // TODO: Fix this, it is currently broken
        List<Integer> validMoves = new ArrayList<Integer>();

        int currentRow = current.getRow();
        int currentColumn = current.getColumn();

        for (int row = -1; row < 1; row++) {
            for (int column = -1; column < 1; column++) {
                int tempCol = currentColumn + column;
                int tempRow = currentRow + row;
                // check bounds
                if(tempRow < 9 && tempRow > 0 && tempCol < 9 && tempCol > 0 ){
                    if(board.getSymbolOnSquare(board.accessElement(tempCol,tempRow)) == ""){
                        validMoves.add(board.accessElement(tempCol,tempRow));
                    }
                }
            }
        }
        return validMoves.stream().mapToInt(i->i).toArray();
    }
}
