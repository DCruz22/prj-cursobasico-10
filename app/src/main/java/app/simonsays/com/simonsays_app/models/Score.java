package app.simonsays.com.simonsays_app.models;

import java.util.Date;

/**
 * Created by darielcruzhdez on 8/10/17.
 */

public class Score {
    private String playerName;
    private int score;
    private String date;

    public Score(String playerName, int score, String date) {
        this.playerName = playerName;
        this.score = score;
        this.date = date;
    }
    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }
    /**
     * @param playerName the playerName to set
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }
    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }
    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
}