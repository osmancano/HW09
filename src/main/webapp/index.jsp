<%@ page import="com.ironyard.data.GuessGame" %>
<%@ page import="com.ironyard.data.Player" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ironyard.data.HighScore" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>TIY, HW06</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .table th td {
            white-space: nowrap;
            width: 1%;
        }
        html,body{
            height: 100%
        }
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar, .modal-footer, .modal-header {
            margin-bottom: 0;
            border-radius: 0;
            background-color: #095b77;
        }
        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {height: 450px}
        /* Set black background color, white text and some padding */
        footer {
            background-color: #095b77;
            color: white;
            padding: 15px;
        }
        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height:auto;}
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="home.jsp"><img src="imgs/TIY.png" style="width:40px;"></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="index.jsp">Home</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-5 col-sm-offset-1 text-left">
            <h3>Welcome to number guessing game:</h3>
                <div class="row">
                <%
                    GuessGame myGame = (GuessGame) request.getSession().getAttribute("myGame");
                    if(myGame == null || !myGame.isFinished()){%>
                            <p>I am thinking of a number in the range 1 to 100, what is the number?</p>
                            <form class="form" action="/guessGame" id="myForm" method="post">
                                <div class="form-group">
                                    <label for="guessedNumber">Enter your number:</label>
                                    <input type="number" class="form-control" id="guessedNumber" name="txtGuessedNumber" min="1" max="100" required>
                                </div>
                                <button type="submit" class="btn btn-info">Guess</button>
                            </form>
                <%
                    }
                %>
                     <%if(myGame != null && myGame.isFinished()){
                        if(myGame.getMessage()=="MAX_NO_OF_TRIES"){%>
                            <div class="alert alert-success">
                                Sorry you reached the maximum number of guesses, please click <a href="/guessGame" class="btn btn-danger">here</a> to play again.
                            </div>
                        <%}if(myGame.getMessage()=="OUT_OF_RANGE"){%>
                        <div class="alert alert-success">
                            Sorry You Entered number out of range, please click <a href="/guessGame" class="btn btn-danger">here</a> to play again.
                        </div>
                        <%}if(myGame.getMessage()=="MATCH"){%>
                            <div class="alert alert-success">
                                Congrats you won, you guessed the number which is <strong><%=myGame.getTarget()%></strong> after <strong><%=myGame.getNoOfguesses()%></strong> guess(s)
                                <p>please click <a href="/guessGame" class="btn btn-danger">here</a> to play again.</p>
                            </div>
                        <%}}%>
                        </div>
                <br/>
                <div class="row">
                    <%if(myGame != null && !myGame.isFinished()){
                            if(myGame.getMessage()=="LOW"){%>
                            <div class="alert alert-success">
                                Your guess is <strong>low</strong> please guess again.
                            </div>
                                <%}if(myGame.getMessage()=="HIGH"){%>
                            <div class="alert alert-success">
                                Your guess is <strong>High</strong> please guess again.
                            </div>
                    <%}}%>
                </div>
        </div>
        <div class="col-sm-2"></div>
        <div class="col-sm-3">
            <br/>
            <br/>
            <div class="row">
                <%
                    Player player = (Player) request.getSession().getAttribute("player");
                    if(player != null){%>
                <table>
                    <tr>
                        <td>Played games:</td>
                        <td><span class="label label-primary"><%=player.getNoOfGamePlayed()%></span></td>
                    </tr>
                    <tr>
                        <td>Won games:</td>
                        <td><span class="label label-primary"><%=player.getNoOfGameWon()%></span></td>
                    </tr>
                    <tr>
                        <td>Lost games:</td>
                        <td><span class="label label-primary"><%=player.getNoOfGameLost()%></span></td>
                    </tr>
                </table>
                <%}%>
            </div>
            <div class="row text-left">
                <%
                    ArrayList<HighScore> highScoresList = (ArrayList<HighScore>) request.getSession().getAttribute("highScores_list");
                    if(highScoresList != null) {
                    }else {
                        GuessGame game = new GuessGame();
                        HighScore highScore = new HighScore();
                        highScore.setFilePath(game.getScoreFile());
                        highScoresList = highScore.getHighScores();
                    }%>
                    <h4>High Score Leaderboard:</h4>
                    <div class="alert alert-info">
                        <table class="table">
                        <%for(int i=0; i < highScoresList.size();i++){%>

                            <tr>
                                <td>
                                    High Score <%=i+1%>:
                                </td>
                                <td>
                                    <%=highScoresList.get(i).getHighScore()%>
                                </td>
                            </tr>
                         <%}%>
                        </table>
                    </div>
            </div>


        </div>
    </div>

</div>
<br/>
<footer class="container-fluid text-center">
    <p> &copy; Copyright 2015-2017 - The Iron Yard</p>
</footer>

</body>
</html>