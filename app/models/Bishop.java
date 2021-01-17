package models;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    public Bishop(Colour colour) {
        super(colour);
        if(colour == Colour.BLACK){
            super.setSymbol("♝");
        } else {
            super.setSymbol("♗");
        }
    }

    @Override
    public int[] giveValidMoves(Board board, Square current){
        List<Integer> validMoves = new ArrayList<Integer>();

        int currentRow = current.getRow();
        int currentColumn = current.getColumn();


        boolean alreadyHitOpposingColour = false;
        // four different loops, break when we hit a piece
        for (int NE = 1; NE < 9; NE++) {

            int tempRow = currentRow + NE;
            int tempCol = currentColumn + NE;
            if(tempRow < 1 || tempRow > 8 || tempCol < 1 || tempCol > 8){
                break;
            }
            if (board.getSymbolOnSquare(board.accessElement(tempCol, tempRow)).equals("") && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(tempCol, tempRow));
            } else if(!board.getPieceOnSquare(tempCol,tempRow).getColour().equals(this.getColour()) && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(tempCol, tempRow));
                alreadyHitOpposingColour = true;
            } else {
                break;
            }
        }

        alreadyHitOpposingColour = false;
        // four different loops, break when we hit a piece
        for (int SE = 1; SE < 9; SE++) {

            int tempRow = currentRow - SE;
            int tempCol = currentColumn + SE;
            if(tempRow < 1 || tempRow > 8 || tempCol < 1 || tempCol > 8){
                break;
            }
            if (board.getSymbolOnSquare(board.accessElement(tempCol, tempRow)).equals("") && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(tempCol, tempRow));
            } else if(!board.getPieceOnSquare(tempCol,tempRow).getColour().equals(this.getColour()) && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(tempCol, tempRow));
                alreadyHitOpposingColour = true;
            } else {
                break;
            }
        }

        alreadyHitOpposingColour = false;
        // four different loops, break when we hit a piece
        for (int SW = 1; SW < 9; SW++) {

            int tempRow = currentRow - SW;
            int tempCol = currentColumn - SW;
            if(tempRow < 1 || tempRow > 8 || tempCol < 1 || tempCol > 8){
                break;
            }
            if (board.getSymbolOnSquare(board.accessElement(tempCol, tempRow)).equals("") && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(tempCol, tempRow));
            } else if(!board.getPieceOnSquare(tempCol,tempRow).getColour().equals(this.getColour()) && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(tempCol, tempRow));
                alreadyHitOpposingColour = true;
            } else {
                break;
            }
        }

        alreadyHitOpposingColour = false;
        // four different loops, break when we hit a piece
        for (int NW = 1; NW < 9; NW++) {

            int tempRow = currentRow + NW;
            int tempCol = currentColumn - NW;
            if(tempRow < 1 || tempRow > 8 || tempCol < 1 || tempCol > 8){
                break;
            }
            if (board.getSymbolOnSquare(board.accessElement(tempCol, tempRow)).equals("") && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(tempCol, tempRow));
            } else if(!board.getPieceOnSquare(tempCol,tempRow).getColour().equals(this.getColour()) && !alreadyHitOpposingColour) {
                validMoves.add(board.accessElement(tempCol, tempRow));
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
