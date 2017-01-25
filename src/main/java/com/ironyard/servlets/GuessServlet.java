package com.ironyard.servlets;

import com.ironyard.data.GuessGame;
import com.ironyard.data.HighScore;
import com.ironyard.data.Player;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by osmanidris on 1/24/17.
 */
@WebServlet(name = "GuessServlet", urlPatterns = "/guessGame")
public class GuessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int guessedNumber = Integer.parseInt(request.getParameter("txtGuessedNumber"));
        Player player = (Player) request.getSession().getAttribute("player");
        if(player == null){
            player = new Player();
            request.getSession().setAttribute("player",player);
        }
        GuessGame myGame = (GuessGame) request.getSession().getAttribute("myGame");
        if(myGame == null){
            myGame = new GuessGame();
            player.setNoOfGamePlayed(player.getNoOfGamePlayed()+1);
            request.getSession().setAttribute("myGame",myGame);
        }
        HighScore highScore = new HighScore();
        myGame.play(guessedNumber, player,highScore);
        ArrayList<HighScore> highScores_list = highScore.getHighScores();
        request.getSession().setAttribute("highScores_list",highScores_list);
        String nextJSP = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("myGame");

        Player player = (Player) request.getSession().getAttribute("player");
        if(player == null){
            player = new Player();
            request.getSession().setAttribute("player",player);
        }

        HighScore highScore = new HighScore();
        GuessGame myGame = new GuessGame();
        System.out.println(myGame.getScoreFile());
        highScore.setFilePath(myGame.getScoreFile());
        ArrayList<HighScore> highScores_list = highScore.getHighScores();
        request.getSession().setAttribute("highScores_list",highScores_list);
        request.getSession().setAttribute("highScores_list",highScores_list);
        request.getSession().setAttribute("player",player);
        String nextJSP = "/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }
}
