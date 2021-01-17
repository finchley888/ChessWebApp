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
        List<Integer> positionModifiers = new ArrayList<>();
        positionModifiers.add(1);
        positionModifiers.add(2);
        positionModifiers.add(-1);
        positionModifiers.add(-2);
        int currentRow = current.getRow();
        int currentColumn = current.getColumn();

        for (int rowPosition: positionModifiers) {
            for (int colPosition:positionModifiers) {
                int tempRow = currentRow+rowPosition;
                int tempCol = currentColumn+colPosition;
                if(Math.abs(rowPosition) != Math.abs(colPosition) && tempRow > 0 && tempRow < 9 && tempCol > 0 && tempCol < 9 && !board.getPieceOnSquare(board.accessElement(tempCol,tempRow)).getColour().equals(this.getColour())){
                    validMoves.add(board.accessElement(tempCol,tempRow));
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
