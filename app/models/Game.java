package models;

public class Game {
    private Board board;
    private boolean moveMade;
    private int xInitial;
    private int yInitial;
    private Colour currentTurn;

    public Game() {
        this.currentTurn = Colour.WHITE;
        moveMade = false;
        this.board = new Board();
    }

    public void newGame(){
        board.reset();
        currentTurn = Colour.WHITE;
        moveMade = false;
    }

    public void move(int i, int j){
        if(moveMade == false){
            xInitial = i;
            yInitial = j;
            moveMade = true;
            board.movePrepare(i,j);
        } else if (board.getPieceOnSquare(i,j).getColour() == currentTurn && !(i == xInitial && j == yInitial)){
            board.clearPlaceHolders();
            xInitial = i;
            yInitial = j;
            moveMade = true;
            board.movePrepare(i,j);
        } else if (i == xInitial && j == yInitial || !board.isValidMove(i,j) ){
            board.clearPlaceHolders();
            moveMade = false;
        } else {
            moveMade = false;
            board.move(xInitial, yInitial,i,j);
            board.clearPlaceHolders();
            currentTurn = currentTurn.updateColour();
        }
    }

    public void playMade(int i, int j){
        if(!getMoveMade()) {
            if (board.getPieceOnSquare(i, j).getColour() == this.currentTurn) {
                move(i, j);
            }
        } else {
            move(i, j);
        }
    }

    public boolean getMoveMade(){
        return moveMade;
    }

    public Colour getCurrentTurn(){
        return currentTurn;
    }

    public Board getBoard() {
        return this.board;
    }

    public String getCurrentPlayer(){
        return currentTurn == Colour.WHITE ? "White" : "Black";
    }

}
