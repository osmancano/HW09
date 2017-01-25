package com.ironyard.data;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by osmanidris on 1/25/17.
 */
public class HighScore implements Comparable{
    private int highScore;
    private transient String filePath;

    public HighScore(){
        this.highScore = 0;
    }

    public HighScore(String jsonInString){
        Gson gson = new Gson();
        HighScore highScoreObj = gson.fromJson(jsonInString, HighScore.class);
        this.highScore = highScoreObj.highScore;
        this.filePath = filePath;
    }


    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void writeScoreToDisk(boolean flag){

        try (FileWriter fw = new FileWriter(filePath,flag)) {
            Gson gson = new Gson();
            gson.toJson(this, fw);
            fw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAsHighScore(){
        ArrayList<HighScore> highScore_list = this.getHighScores();
        if(highScore_list.isEmpty()){
            this.writeScoreToDisk(false);
            highScore_list.add(this);
        }else{
            if(highScore_list.size() < 5){
                this.writeScoreToDisk(true);
                highScore_list.add(this);
            }else {
                boolean flag = true;
                for (int i = 0; i < highScore_list.size(); i++) {
                    flag = true;
                    if (this.highScore < highScore_list.get(i).highScore) {
                        highScore_list.set(i, this);
                    }
                    if (i == 0) {
                        flag = false;
                    }
                    System.out.println("osman");
                    highScore_list.get(i).setFilePath(filePath);
                    highScore_list.get(i).writeScoreToDisk(flag);
                }
            }
        }
    }

    public ArrayList<HighScore> getHighScores(){
        ArrayList<HighScore> highScore_list = new ArrayList<HighScore>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                HighScore highScoreObj = new HighScore(line);
                highScore_list.add(highScoreObj);
            }
            Collections.sort(highScore_list);
        }catch (IOException e){
            e.printStackTrace();
        }
        return highScore_list;
    }

    public int getHighScore() {
        return highScore;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int compareTo(Object highScoreObj) {
        int highScoreVal=((HighScore)highScoreObj).getHighScore();
        /* For Ascending order*/
        return this.getHighScore()-highScoreVal;
    }
}
