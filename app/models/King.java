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

        for (int row = -1; row < 2; row++) {
            for (int column = -1; column < 2; column++) {
                int tempCol = currentColumn + column;
                int tempRow = currentRow + row;
                // check bounds
                if(tempRow < 9 && tempRow > 0 && tempCol < 9 && tempCol > 0 ){
                    if(board.getSymbolOnSquare(board.accessElement(tempCol,tempRow)) == "" || !board.getPieceOnSquare(tempCol,tempRow).getColour().equals(this.getColour())){
                        validMoves.add(board.accessElement(tempCol,tempRow));
                    }
                }
            }
        }

        //castling
        if(super.getMoveCounter() == 0){
            if(board.getPieceOnSquare(currentColumn + 1,currentRow).getClass().equals(PlaceHolderPiece.class) &&
                    board.getPieceOnSquare(currentColumn + 2,currentRow).getClass().equals(PlaceHolderPiece.class) &&
                    board.getPieceOnSquare(currentColumn + 3,currentRow).getClass().equals(Rook.class) &&
                    board.getPieceOnSquare(currentColumn + 3,currentRow).getMoveCounter() == 0){
                validMoves.add(board.accessElement(currentColumn + 2,currentRow));
            }

            if(board.getPieceOnSquare(currentColumn - 1,currentRow).getClass().equals(PlaceHolderPiece.class) &&
                    board.getPieceOnSquare(currentColumn - 2,currentRow).getClass().equals(PlaceHolderPiece.class) &&
                    board.getPieceOnSquare(currentColumn - 3,currentRow).getClass().equals(PlaceHolderPiece.class) &&
                    board.getPieceOnSquare(currentColumn - 4,currentRow).getClass().equals(Rook.class) &&
                    board.getPieceOnSquare(currentColumn - 4,currentRow).getMoveCounter() == 0){
                validMoves.add(board.accessElement(currentColumn - 2,currentRow));
            }
        }

        return validMoves.stream().mapToInt(i->i).toArray();
    }

    @Override
    public boolean isValidMove(Board board, Square current) {
        return false;
    }
}
