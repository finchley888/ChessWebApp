package models;

public class Square {

    private Piece piece;

    public Square(Piece piece){
        this.piece = piece;
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
