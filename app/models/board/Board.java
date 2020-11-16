package models.board;

import models.Colour;
import models.pieces.*;

public class Board {
    Square[] board;

    public Board() {
        // initialise to an empty array of squares
        board = new Square[8 * 8];
    }

    /**
     * Function to initialise the board to normal starting playing positions.
     */
    public void initialise(){
        for (int row = 1; row < 9; row++) {
            for (int column = 1; column < 9; column++) {
                if(row == 2){                                                               // white pawns
                    board[accessElement(column, row)].setPiece( new Pawn(Colour.WHITE));
                } else if(row == 7){                                                        // black pawns
                    board[accessElement(column, row)].setPiece( new Pawn(Colour.BLACK));
                } else if (row == 1 && ( column == 1 || column == 8)){                      // white rooks
                    board[accessElement(column, row)].setPiece( new Rook(Colour.WHITE));
                } else if (row == 8 && ( column == 1 || column == 8)){                      // black rooks
                    board[accessElement(column, row)].setPiece( new Rook(Colour.BLACK));
                } else if (row == 1 && ( column == 2 || column == 7)){                      // white knights
                    board[accessElement(column, row)].setPiece( new Knight(Colour.WHITE));
                } else if (row == 8 && ( column == 2 || column == 7)){                      // black knights
                    board[accessElement(column, row)].setPiece( new Knight(Colour.BLACK));
                } else if (row == 1 && ( column == 3 || column == 6)){                      // white bishops
                    board[accessElement(column, row)].setPiece( new Bishop(Colour.WHITE));
                } else if (row == 8 && ( column == 3 || column == 6)){                      // black bishops
                    board[accessElement(column, row)].setPiece( new Bishop(Colour.BLACK));
                } else if (row == 1 && ( column == 4 )){                                    // white queen
                    board[accessElement(column, row)].setPiece( new Queen(Colour.WHITE));
                } else if (row == 1 && ( column == 5 )){                                    // white king
                    board[accessElement(column, row)].setPiece( new King(Colour.WHITE));
                } else if (row == 8 && ( column == 4 )){                                    // black queen
                    board[accessElement(column, row)].setPiece( new Queen(Colour.BLACK));
                } else if (row == 8 && ( column == 5 )){                                    // black king
                    board[accessElement(column, row)].setPiece( new King(Colour.BLACK));
                }
            }
        }
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
}
