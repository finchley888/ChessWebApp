package models;

import jdk.swing.interop.SwingInterOpUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Board {
    private Square[] board = initialise();
    private int[] currentValidMoves;
    private ArrayList<String> moves = new ArrayList<>();

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
        return (8 * ( ( 9 - rank ) - 1) ) + ( ( 9 - file ) -1 );
    }
    public int getXValueFromIndex(int index){ return 8 - (index % 8);}
    public int getYValueFromIndex(int index){ return 8 - (index / (int) 8) ;}

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

    public Square getSquare(int file, int rank){
        return board[accessElement(file, rank)];
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
        recordMove(xInitial, yInitial, xFinal, yFinal);
    }

    public boolean isValidMove(int xFinal, int yFinal){
        return IntStream.of(currentValidMoves).anyMatch(x -> x == accessElement(xFinal, yFinal));
    }

    public void movePrepare(int xInitial, int yInitial){
        int[] validMoves = board[accessElement(xInitial, yInitial)].getPiece().giveValidMoves(this, board[accessElement(xInitial, yInitial)]);


        ArrayList<Integer> validMovesAfterCheck = new ArrayList<>();

        for (int index:validMoves) {
            int x = getXValueFromIndex(index);
            int y = getYValueFromIndex(index);
            if(!willBeInCheck(this.getPieceOnSquare(xInitial,yInitial).getColour(),xInitial, yInitial,  x,  y)){
                validMovesAfterCheck.add(index);
            }
        }

        for (int move:validMovesAfterCheck) {
            if(board[move].getPiece() == null) {
                board[move].setPiece(new PlaceHolderPiece(Colour.NILL));
            }
        }

        currentValidMoves = validMovesAfterCheck.stream().mapToInt(i -> i).toArray();
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
        moves = new ArrayList<>();
    }

    private void recordMove(int xInitial, int yInitial, int xFinal, int yFinal){
        // generate empty array to be added to the array list
        String xInitValue = Character.toString((char) xInitial+96);
        String xFinalValue = Character.toString((char) xFinal+96);
        moves.add( xInitValue + yInitial + " - > " + xFinalValue + yFinal);
    }

    public ArrayList<String> getMoves(){
        return moves;
    }

    public boolean isInCheck(Colour kingsColour){
        ArrayList<Integer> allowedOfOppositeColour = new ArrayList<>();
        //loop over each of the pieces of opposite colour, if the pieces can take the king then he is in check
        for (Square square:board) {
            // if the opposite colour of the piece on the square is the kings colour
            if(square.getPiece() != null) {
                if (square.getPiece().getColour().updateColour() == kingsColour) {
                    int[] moves = square.getPiece().giveValidMoves(this, square);
                    for (int move : moves) {
                        allowedOfOppositeColour.add(move);
                    }
                }
            }
        }

        for (Integer index: allowedOfOppositeColour) {
            if(getPieceOnSquare(index).getClass().equals(King.class)){
                return true;
            }
        }
        return false;
    }

    public boolean willBeInCheck(Colour kingsColour, int xInitial, int yInitial, int xFinal, int yFinal) {
        //move the piece to the square, and see if the king is then in check
        // store the target piece away somewhere
        Piece piece = null;
        if (board[accessElement(xFinal, yFinal)].getPiece()!=null){
            piece = board[accessElement(xFinal, yFinal)].getPiece();
        }

        Piece temp = board[accessElement(xInitial, yInitial)].getPiece();
        board[accessElement(xInitial, yInitial)].setPiece(null);
        board[accessElement(xFinal, yFinal)].setPiece(temp);


        if(isInCheck(kingsColour)){
            // move the piece back
            board[accessElement(xInitial, yInitial)].setPiece(temp);

            // move the placeholder piece back

            board[accessElement(xFinal, yFinal)].setPiece(piece);
            // move the placeholder piece back
            if(piece != null){
                board[accessElement(xFinal, yFinal)].setPiece(piece);
            }
            return true;
        }
        // move the piece back

        board[accessElement(xInitial, yInitial)].setPiece(temp);

        // move the placeholder piece back

        board[accessElement(xFinal, yFinal)].setPiece(piece);


        return false;
    }

    public boolean isInCheckMate(Colour kingsColour){
        for (int index = 0; index < board.length; index++) {
            if(this.getPieceOnSquare(index).getColour().equals(kingsColour)){
                int[] validMoves = board[index].getPiece().giveValidMoves(this, board[index]);

                for (int move :validMoves) {
                    int xinitial = getXValueFromIndex(index);
                    int yinitial = getYValueFromIndex(index);
                    int xFinal = getXValueFromIndex(move);
                    int yFinal = getYValueFromIndex(move);
                    if(!willBeInCheck(kingsColour,xinitial,yinitial,xFinal,yFinal)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isKingsSquare(int row, int column, Colour kingsColour){
        return this.getPieceOnSquare(column, row).getClass().equals(King.class) && this.getPieceOnSquare(column,row).getColour().equals(kingsColour);
    }

}
