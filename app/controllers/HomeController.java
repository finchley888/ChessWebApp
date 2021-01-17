package controllers;

import models.Board;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Singleton;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
@Singleton
public class HomeController extends Controller {

    private Board board;
    private boolean moveMade = false;
    private int xInitial;
    private int yInitial;

    public HomeController(){
        this.board = new Board();
    }

    /**
     * Print the board action
     */
    public Result printBoard(Http.Request request){
        return ok(views.html.index.render(board, request));
    }

    public Result playMade(int i, int j){

        if(moveMade == false){
            xInitial = i;
            yInitial = j;
            moveMade = true;
            board.movePrepare(i,j);
        } else {
            moveMade = false;
            board.move(xInitial, yInitial,i,j);
            board.clearPlaceHolders();
        }

        return redirect(routes.HomeController.printBoard());
    }

    public Result resetBoard(){
        board.reset();
        moveMade = false;
        return redirect(routes.HomeController.printBoard());
    }
}
