package models;

public class Square {

    private Piece piece;
    int row;
    int column;

    public Square(Piece piece, int row, int column){
        this.piece = piece;
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getSquareSymbol() {
        if(piece != null) {
            return piece.getSymbol();
        }
        return "";
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
