package controllers;

import models.Board;
import models.Game;
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

    private Game game;

    public HomeController(){
        this.game = new Game();
    }

    /**
     * Print the board action
     */
    public Result printBoard(Http.Request request){
        return ok(views.html.index.render(game.getBoard(), game, request));
    }

    public Result playMade(int i, int j){
        game.playMade(i,j);
        return redirect(routes.HomeController.printBoard());
    }

    public Result resetBoard(){
        game.newGame();
        return redirect(routes.HomeController.printBoard());
    }
}
