package com.ironyard.data;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by osmanidris on 1/24/17.
 */
public class GuessGame {
    private int target;
    private int minValue;
    private int maxValue;
    private int maxNoOfTries;
    private String scoreFile;
    private boolean finished = false;
    private String message;
    private int noOfguesses;
    private String playerName;
    public GuessGame(){
        Properties prop = new Properties();
        try {
            InputStream fis = new FileInputStream(Constants.PROPERTIES_FILENAME);
            prop.load(fis);
            fis.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
        this.target = Integer.parseInt(prop.getProperty(Constants.TARGET));
        this.maxNoOfTries= Integer.parseInt(prop.getProperty(Constants.MAX_NUMBER_OF_TRIES));
        this.minValue = Integer.parseInt(prop.getProperty(Constants.MIN_VALUE));
        this.maxValue = Integer.parseInt(prop.getProperty(Constants.MAX_VALUE));
        this.scoreFile = prop.getProperty(Constants.PLAYER_FILENAME);
        this.noOfguesses = 0;
    }
    public void play(int guessNum,Player player, HighScore highScore) {
        this.noOfguesses++;
        highScore.setFilePath(this.scoreFile);
        if (this.noOfguesses > this.maxNoOfTries) {
            this.message = "MAX_NO_OF_TRIES";
            finished = true;
            player.setNoOfGameLost(player.getNoOfGameLost()+1);
        } else {
            if (guessNum < this.minValue || guessNum > this.maxValue) {
                this.message = "OUT_OF_RANGE";
                finished = true;
                player.setNoOfGameLost(player.getNoOfGameLost()+1);
            } else {
                if (guessNum < this.target) {
                    this.message = "LOW";
                    finished = false;
                } else {
                    if (guessNum > this.target) {
                        this.message = "HIGH";
                        finished = false;
                    } else {
                        this.message = "MATCH";
                        finished = true;
                        player.setNoOfGameWon(player.getNoOfGameWon()+1);
                        highScore.setHighScore(this.noOfguesses);
                        highScore.setAsHighScore();
                    }
                }
            }
        }
    }

    public int getTarget() {
        return target;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getMessage() {
        return message;
    }

    public int getNoOfguesses() {
        return noOfguesses;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getScoreFile() {
        return scoreFile;
    }
}
