package com.ironyard.data;

/**
 * Created by osmanidris on 1/25/17.
 */
public class Player {
    private String playerName;
    private int noOfGamePlayed;
    private int noOfGameWon;
    private int noOfGameLost;

    public Player(){
        noOfGamePlayed = 0;
        noOfGameWon = 0;
        noOfGameLost = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getNoOfGamePlayed() {
        return noOfGamePlayed;
    }

    public void setNoOfGamePlayed(int noOfGamePlayed) {
        this.noOfGamePlayed = noOfGamePlayed;
    }

    public int getNoOfGameWon() {
        return noOfGameWon;
    }

    public void setNoOfGameWon(int noOfGameWon) {
        this.noOfGameWon = noOfGameWon;
    }

    public int getNoOfGameLost() {
        return noOfGameLost;
    }

    public void setNoOfGameLost(int noOfGameLost) {
        this.noOfGameLost = noOfGameLost;
    }
}
