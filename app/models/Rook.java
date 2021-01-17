package models;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(Colour colour) {
        super(colour);
        if (colour == Colour.BLACK){
            super.setSymbol("♜");
        } else {
            super.setSymbol("♖");
        }
    }

    @Override
    public int[] giveValidMoves(Board board, Square current){
        List<Integer> validMoves = new ArrayList<Integer>();

        int currentRow = current.getRow();
        int currentColumn = current.getColumn();
        boolean alreadyHitOpposingColour = false;
        // four different loops, break when we hit a piece
        for (int positiveRow = 1; positiveRow < 9; positiveRow++) {

            int tempRow = currentRow + positiveRow;
            if(tempRow < 1 || tempRow > 8){
                break;
            }
            if (board.getSymbolOnSquare(board.accessElement(currentColumn, tempRow)).equals("") && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(currentColumn, tempRow));
            } else if(!board.getPieceOnSquare(currentColumn,tempRow).getColour().equals(this.getColour()) && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(currentColumn, tempRow));
                alreadyHitOpposingColour = true;
            } else {
                break;
            }
        }
        alreadyHitOpposingColour = false;
        for (int negativeRow = 1; negativeRow < 9; negativeRow++) {
            int tempRow = currentRow - negativeRow;

            if(tempRow < 1 || tempRow > 8){
                break;
            }

            if (board.getSymbolOnSquare(board.accessElement(currentColumn, tempRow)).equals("") && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(currentColumn, tempRow));
            } else if(!board.getPieceOnSquare(currentColumn,tempRow).getColour().equals(this.getColour()) && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(currentColumn, tempRow));
                alreadyHitOpposingColour = true;
            } else {
                break;
            }
        }
        alreadyHitOpposingColour = false;
        // four different loops, break when we hit a piece
        for (int positiveCol = 1; positiveCol < 9; positiveCol++) {
            int tempCol = currentColumn + positiveCol;
            if(tempCol < 1 || tempCol > 8){
                break;
            }

            if (board.getSymbolOnSquare(board.accessElement(tempCol, currentRow)).equals("") && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(tempCol, currentRow));
            } else if(!board.getPieceOnSquare(tempCol,currentRow).getColour().equals(this.getColour()) && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(tempCol, currentRow));
                alreadyHitOpposingColour = true;
            }else {
                break;
            }
        }
        alreadyHitOpposingColour = false;
        // four different loops, break when we hit a piece
        for (int positiveCol = 1; positiveCol < 9; positiveCol++) {
            int tempCol = currentColumn - positiveCol;
            if(tempCol < 1 || tempCol > 8){
                break;
            }

            if (board.getSymbolOnSquare(board.accessElement(tempCol, currentRow)).equals("") && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(tempCol, currentRow));
            } else if(!board.getPieceOnSquare(tempCol,currentRow).getColour().equals(this.getColour()) && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(tempCol, currentRow));
                alreadyHitOpposingColour = true;
            } else {
                break;
            }
        }

        return validMoves.stream().mapToInt(i->i).toArray();
    }

    @Override
    public boolean isValidMove(Board board, Square current) {
        return false;
    }
}
