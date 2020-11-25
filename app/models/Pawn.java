package models;

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
        // TODO: Finish Pawn move validation
        if(super.getColour() == Colour.WHITE){
            if(super.getMoveCounter() == 0){
                return new int[]{board.accessElement(current.getColumn(), current.getRow()+1),
                        board.accessElement(current.getColumn(), current.getRow()+2)};
            } else {
                if(current.getRow()+1<9) {
                    return new int[]{board.accessElement(current.getColumn(), current.getRow() + 1)};
                } else{
                    return new int[]{};
                }
            }
        } else {
            if(super.getMoveCounter() == 0){
                return new int[]{board.accessElement(current.getColumn(), current.getRow()-1),
                        board.accessElement(current.getColumn(), current.getRow()-2)};
            } else {
                if(current.getRow()-1 > 0 ) {
                    return new int[]{board.accessElement(current.getColumn(), current.getRow() - 1)};
                } else {
                    return new int[]{};
                }
            }
        }
    }

    @Override
    public boolean isValidMove(Board board, Square current) {
        return false;
    }
}
