package models;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Colour colour) {
        super(colour);
        if(colour == Colour.BLACK){
            super.setSymbol("♟︎");
        } else {
            super.setSymbol("♙");
        }
    }

    @Override
    public int[] giveValidMoves(Board board, Square current){
        List<Integer> validMoves = new ArrayList<Integer>();
        // TODO: Finish add enpassent properly
        if(super.getColour() == Colour.WHITE){
            if(super.getMoveCounter() == 0){
                //move forward
                if(board.getPieceOnSquare(current.getColumn(), current.getRow() + 1).getClass().equals(PlaceHolderPiece.class)){
                    validMoves.add(board.accessElement(current.getColumn(), current.getRow() + 1));
                    if(board.getPieceOnSquare(current.getColumn(), current.getRow() + 2).getClass().equals(PlaceHolderPiece.class)){
                        validMoves.add(board.accessElement(current.getColumn(), current.getRow() + 2));
                    }
                }
                if(current.getColumn() + 1 < 9) {
                    if (board.getPieceOnSquare(current.getColumn() + 1, current.getRow() + 1).getColour().equals(Colour.BLACK)) {
                        validMoves.add(board.accessElement(current.getColumn() + 1, current.getRow() + 1));
                    }

                }

                if(current.getColumn() - 1 > 0) {
                    if (board.getPieceOnSquare(current.getColumn() - 1, current.getRow() + 1).getColour().equals(Colour.BLACK)) {
                        validMoves.add(board.accessElement(current.getColumn() - 1, current.getRow() + 1));
                    }
                }

            } else {
                if(current.getRow()+1<9) {
                    if(board.getPieceOnSquare(current.getColumn(), current.getRow() + 1).getClass().equals(PlaceHolderPiece.class)){
                        validMoves.add(board.accessElement(current.getColumn(), current.getRow() + 1));
                    }

                    if(current.getColumn() + 1 < 9) {
                        if (board.getPieceOnSquare(current.getColumn() + 1, current.getRow() + 1).getColour().equals(Colour.BLACK)) {
                            validMoves.add(board.accessElement(current.getColumn() + 1, current.getRow() + 1));
                        }
                        if(current.getRow() == 5){
                            if(board.getPieceOnSquare(current.getColumn() + 1, current.getRow()).getClass().equals(Pawn.class) && board.getPieceOnSquare(current.getColumn() + 1, current.getRow()).getMoveCounter() == 1) {
                                validMoves.add(board.accessElement(current.getColumn() + 1, current.getRow() + 1));
                            }
                        }
                    }

                    if(current.getColumn() - 1 > 0) {
                        if (board.getPieceOnSquare(current.getColumn() - 1, current.getRow() + 1).getColour().equals(Colour.BLACK)) {
                            validMoves.add(board.accessElement(current.getColumn() - 1, current.getRow() + 1));
                        }
                        if(current.getRow() == 5){
                            if(board.getPieceOnSquare(current.getColumn() - 1, current.getRow()).getClass().equals(Pawn.class) && board.getPieceOnSquare(current.getColumn() - 1, current.getRow()).getMoveCounter() == 1) {
                                validMoves.add(board.accessElement(current.getColumn() - 1, current.getRow() + 1));
                            }
                        }
                    }
                }
            }
        } else {
            if(super.getMoveCounter() == 0){
                if(board.getPieceOnSquare(current.getColumn(), current.getRow() - 1).getClass().equals(PlaceHolderPiece.class)){
                    validMoves.add(board.accessElement(current.getColumn(), current.getRow() - 1));
                    if(board.getPieceOnSquare(current.getColumn(), current.getRow() - 2).getClass().equals(PlaceHolderPiece.class)){
                        validMoves.add(board.accessElement(current.getColumn(), current.getRow() - 2));
                    }
                }
                if(current.getColumn() + 1 < 9) {
                    if (board.getPieceOnSquare(current.getColumn() + 1, current.getRow() - 1).getColour().equals(Colour.WHITE)) {
                        validMoves.add(board.accessElement(current.getColumn() + 1, current.getRow() - 1));
                    }
                }

                if(current.getColumn() - 1 > 0) {
                    if (board.getPieceOnSquare(current.getColumn() - 1, current.getRow() + 1).getColour().equals(Colour.WHITE)) {
                        validMoves.add(board.accessElement(current.getColumn() - 1, current.getRow() - 1));
                    }
                }
            } else {
                if(current.getRow()-1 > 0 ) {
                    if(board.getPieceOnSquare(current.getColumn(), current.getRow() - 1).getClass().equals(PlaceHolderPiece.class)){
                        validMoves.add(board.accessElement(current.getColumn(), current.getRow() - 1));
                    }

                    if(current.getColumn() + 1 < 9) {
                        if (board.getPieceOnSquare(current.getColumn() + 1, current.getRow() - 1).getColour().equals(Colour.WHITE)) {
                            validMoves.add(board.accessElement(current.getColumn() + 1, current.getRow() - 1));
                        }
                        if(current.getRow() == 4){
                            if(board.getPieceOnSquare(current.getColumn() + 1, current.getRow()).getClass().equals(Pawn.class) && board.getPieceOnSquare(current.getColumn() + 1, current.getRow()).getMoveCounter() == 1) {
                                validMoves.add(board.accessElement(current.getColumn() + 1, current.getRow() - 1));
                            }
                        }

                    }

                    if(current.getColumn() - 1 > 0) {
                        if (board.getPieceOnSquare(current.getColumn() - 1, current.getRow() - 1).getColour().equals(Colour.WHITE)) {
                            validMoves.add(board.accessElement(current.getColumn() - 1, current.getRow() - 1));
                        }
                        if(current.getRow() == 4){
                            if(board.getPieceOnSquare(current.getColumn() - 1, current.getRow()).getClass().equals(Pawn.class) && board.getPieceOnSquare(current.getColumn() - 1, current.getRow()).getMoveCounter() == 1) {
                                validMoves.add(board.accessElement(current.getColumn() - 1, current.getRow() - 1));
                            }
                        }
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
