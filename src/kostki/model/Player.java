package kostki.model;

import kostki.model.Figure;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    public boolean schoolScoreOnceFlag = true;
    private String playerName;
    private int schoolScore = 0;
    private int uniScore = 0;
    private int overallScore = 0;
    private ArrayList<Figure> figureList = new ArrayList<>();
    public Player(String playerName) {
        this.playerName = playerName;
        figureList.addAll(Arrays.asList(Figure.values()));

    }
    public void removeFigure(Figure figure){
        this.figureList.remove(figure);
    }
    public ArrayList<Figure> getFigureList(){
        return figureList;
    }
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getSchoolScore() {
        return schoolScore;
    }

    public int getUniScore() {
        return uniScore;
    }

    public void addSchoolScore(int score) {
        schoolScore+=score;
    }
    public void verifySchoolScore() {
        if(schoolScore>=15){
            this.schoolScore += 50;
        }else if (schoolScore < 0){
            this.schoolScore -= 50;
        }
    }
    public void addUniScore(int score) {
        this.uniScore += score;
    }
    public void addOverallScore(int score) {
        this.overallScore += score;
    }
    public int getOverallScore() {
        return overallScore;
    }

    public void resetFigureList(){
        this.figureList.clear();
        this.figureList.addAll(Arrays.asList(Figure.values()));
    }

    public void setSchoolScore(int i) {
        this.schoolScore = i;
    }
    public void setUniScore(int i) {
        this.uniScore = i;
    }
}
