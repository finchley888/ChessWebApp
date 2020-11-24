package models;

public class Board {
    private Square[] board = initialise();

    public Board() {}

    /**
     * Function to initialise the board to normal starting playing positions.
     */
    public Square[] initialise(){
        Square[] board = new Square[8*8];
        for (int row = 1; row < 9; row++) {
            for (int column = 1; column < 9; column++) {
                if(row == 2){                                                               // white pawns
                    board[accessElement(column, row)] = new Square( new Pawn(Colour.WHITE));
                } else if(row == 7){                                                        // black pawns
                    board[accessElement(column, row)] = new Square( new Pawn(Colour.BLACK));
                } else if (row == 1 && ( column == 1 || column == 8)){                      // white rooks
                    board[accessElement(column, row)] = new Square( new Rook(Colour.WHITE));
                } else if (row == 8 && ( column == 1 || column == 8)){                      // black rooks
                    board[accessElement(column, row)] = new Square( new Rook(Colour.BLACK));
                } else if (row == 1 && ( column == 2 || column == 7)){                      // white knights
                    board[accessElement(column, row)] = new Square( new Knight(Colour.WHITE));
                } else if (row == 8 && ( column == 2 || column == 7)){                      // black knights
                    board[accessElement(column, row)] = new Square( new Knight(Colour.BLACK));
                } else if (row == 1 && ( column == 3 || column == 6)){                      // white bishops
                    board[accessElement(column, row)] = new Square( new Bishop(Colour.WHITE));
                } else if (row == 8 && ( column == 3 || column == 6)){                      // black bishops
                    board[accessElement(column, row)] = new Square( new Bishop(Colour.BLACK));
                } else if (row == 1 && ( column == 4 )){                                    // white queen
                    board[accessElement(column, row)] = new Square( new Queen(Colour.WHITE));
                } else if (row == 1 && column == 5 ){                                       // white king
                    board[accessElement(column, row)] = new Square( new King(Colour.WHITE));
                } else if (row == 8 && ( column == 4 )){                                    // black queen
                    board[accessElement(column, row)] = new Square( new Queen(Colour.BLACK));
                } else if (row == 8 && column == 5){                                        // black king
                    board[accessElement(column, row)] = new Square( new King(Colour.BLACK));
                } else {
                    board[accessElement(column, row)] = new Square( null);
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

    public String getSymbolOnSquare(int index) {
        return board[index].getSquareSymbol();
    }

    public void move(int xInitial, int yInitial, int xFinal, int yFinal){
        Piece temp = board[accessElement(xInitial, yInitial)].getPiece();
        board[accessElement(xInitial, yInitial)].setPiece(null);
        board[accessElement(xFinal,yFinal)].setPiece(temp);
    }

    public void movePrepare(int xInitial, int yInitial){
        board[accessElement(xInitial, yInitial+1)].setPiece(new PlaceHolderPiece(Colour.BLACK));
        board[accessElement(xInitial, yInitial+2)].setPiece(new PlaceHolderPiece(Colour.BLACK));
    }

    public void clearPlaceHolders(){
        for (Square square :board) {
            if(square.getSquareSymbol() != "") {
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
