package models;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Board {
    private Square[] board = initialise();
    private int[] currentValidMoves;

    public Board() {}

    /**
     * Function to initialise the board to normal starting playing positions.
     */
    public Square[] initialise(){
        Square[] board = new Square[8*8];
        for (int row = 1; row < 9; row++) {
            for (int column = 1; column < 9; column++) {
                if(row == 2){                                                               // white pawns
                    board[accessElement(column, row)] = new Square( new Pawn(Colour.WHITE), row, column);
                } else if(row == 7){                                                        // black pawns
                    board[accessElement(column, row)] = new Square( new Pawn(Colour.BLACK), row, column);
                } else if (row == 1 && ( column == 1 || column == 8)){                      // white rooks
                    board[accessElement(column, row)] = new Square( new Rook(Colour.WHITE), row, column);
                } else if (row == 8 && ( column == 1 || column == 8)){                      // black rooks
                    board[accessElement(column, row)] = new Square( new Rook(Colour.BLACK), row, column);
                } else if (row == 1 && ( column == 2 || column == 7)){                      // white knights
                    board[accessElement(column, row)] = new Square( new Knight(Colour.WHITE), row, column);
                } else if (row == 8 && ( column == 2 || column == 7)){                      // black knights
                    board[accessElement(column, row)] = new Square( new Knight(Colour.BLACK), row, column);
                } else if (row == 1 && ( column == 3 || column == 6)){                      // white bishops
                    board[accessElement(column, row)] = new Square( new Bishop(Colour.WHITE), row, column);
                } else if (row == 8 && ( column == 3 || column == 6)){                      // black bishops
                    board[accessElement(column, row)] = new Square( new Bishop(Colour.BLACK), row, column);
                } else if (row == 1 && ( column == 4 )){                                    // white queen
                    board[accessElement(column, row)] = new Square( new Queen(Colour.WHITE), row, column);
                } else if (row == 1 && column == 5 ){                                       // white king
                    board[accessElement(column, row)] = new Square( new King(Colour.WHITE), row, column);
                } else if (row == 8 && ( column == 4 )){                                    // black queen
                    board[accessElement(column, row)] = new Square( new Queen(Colour.BLACK), row, column);
                } else if (row == 8 && column == 5){                                        // black king
                    board[accessElement(column, row)] = new Square( new King(Colour.BLACK), row, column);
                } else {
                    board[accessElement(column, row)] = new Square( null, row, column);
                }
            }
        }
        return board;
    }

    /**
     * Function to access the element from the 1d array (converts 2d to 1d), rank and file are 'flipped'
     * @param file column of the board
     * @param rank row of the board
     * @return the integer value of the board
     */
    public int accessElement(int file, int rank){
        return (8 * ( ( 9 - rank )  - 1 ) ) + ( ( 9 - file ) -1 );
    }

    public Piece getPieceOnSquare(int file, int rank){
        if(board[accessElement(file, rank)].getPiece() != null){
            return board[accessElement(file, rank)].getPiece();
        }
        return new PlaceHolderPiece(Colour.NILL);
    }

    public Piece getPieceOnSquare(int index){
        if(board[index].getPiece() != null){
            return board[index].getPiece();
        }
        return new PlaceHolderPiece(Colour.NILL);
    }

    public String getSymbolOnSquare(int index) {
        return board[index].getSquareSymbol();
    }

    public void move(int xInitial, int yInitial, int xFinal, int yFinal){
        if(IntStream.of(currentValidMoves).anyMatch(x -> x == accessElement(xFinal, yFinal))) {
            if(board[accessElement(xInitial, yInitial)].getPiece().getClass().equals(King.class) && xInitial + 2 == xFinal ){ //castling
                // move king
                Piece temp = board[accessElement(xInitial, yInitial)].getPiece();
                board[accessElement(xInitial, yInitial)].setPiece(null);
                board[accessElement(xFinal, yFinal)].setPiece(temp);
                board[accessElement(xFinal, yFinal)].getPiece().updateMoveCounter();

                // move rook
                Piece rook = board[accessElement(xInitial+3, yInitial)].getPiece();
                board[accessElement(xInitial+3, yInitial)].setPiece(null);
                board[accessElement(xInitial+1, yInitial)].setPiece(rook);

            } else if(board[accessElement(xInitial, yInitial)].getPiece().getClass().equals(King.class) && xInitial - 2 == xFinal){ //castling
                // move king
                Piece temp = board[accessElement(xInitial, yInitial)].getPiece();
                board[accessElement(xInitial, yInitial)].setPiece(null);
                board[accessElement(xFinal, yFinal)].setPiece(temp);
                board[accessElement(xFinal, yFinal)].getPiece().updateMoveCounter();

                // move rook
                Piece rook = board[accessElement(xInitial-4, yInitial)].getPiece();
                board[accessElement(xInitial-4, yInitial)].setPiece(null);
                board[accessElement(xInitial-1, yInitial)].setPiece(rook);

            }else {
                Piece temp = board[accessElement(xInitial, yInitial)].getPiece();
                board[accessElement(xInitial, yInitial)].setPiece(null);
                board[accessElement(xFinal, yFinal)].setPiece(temp);
                board[accessElement(xFinal, yFinal)].getPiece().updateMoveCounter();
            }
        }
    }

    public void movePrepare(int xInitial, int yInitial){
        int[] validMoves = board[accessElement(xInitial, yInitial)].getPiece().giveValidMoves(this, board[accessElement(xInitial, yInitial)]);

        for (int move:validMoves) {
            if(board[move].getPiece() == null) {
                board[move].setPiece(new PlaceHolderPiece(Colour.NILL));
            }
        }

        currentValidMoves = validMoves;
    }

    public void clearPlaceHolders(){
        for (Square square :board) {
            if(!square.getSquareSymbol().equals("")) {
                if (square.getPiece().getClass().equals(PlaceHolderPiece.class)) {
                    square.setPiece(null);
                }
            }
        }
    }

    public void reset(){
        board = initialise();
    }
}
