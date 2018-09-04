package app.simonsays.com.simonsays_app.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import app.simonsays.com.simonsays_app.R;
import app.simonsays.com.simonsays_app.models.Score;

/**
 * Created by darielcruzhdez on 8/9/17.
 */

public class FileHelper {

    private static FileHelper instance = new FileHelper();

    private Context mContext;

    private FileHelper(){}

    public void init(Context appContext) {
        mContext = appContext;
    }

    public static FileHelper getInstance(){
        return instance;
    }

    private static List<Score> scores = new ArrayList<>();

    public Score getScore(int index){
        return scores.get(0);
    }

    public List<Score> getScores(){
        return scores;
    }

    public void addScore(Score score){
        scores.add(score);
    }

    public void fillScoresList(){
        String json = readStringPreferences(R.string.scores_preference, R.string.scores_preference, "");

        List<Score> savedScores = JSONParser.getScoresFromJson(json);

        if(savedScores != null) {
            for (Score s : savedScores) {
                addScore(s);
            }
        }
    }

    public void saveScore(){
        String json = JSONParser.getJsonFromScores(scores);
        writeStringPreferences(R.string.scores_preference, R.string.scores_preference, json);
    }

    public void writeIntPreferences(int preferenceResource, int key, int value){
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(preferenceResource), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(mContext.getString(key), value);
        editor.commit();
    }

    public int readIntPreferences(int preferenceResource, int key, int defaultValue){
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(preferenceResource), Context.MODE_PRIVATE);
        return sharedPref.getInt(mContext.getString(key), defaultValue);
    }


    public void writeStringPreferences(int preferenceResource, int key, String value){
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(preferenceResource), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(mContext.getString(key), value);
        editor.commit();
    }

    public String readStringPreferences(int preferenceResource, int key, String defaultValue){
        SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(preferenceResource), Context.MODE_PRIVATE);
        return sharedPref.getString(mContext.getString(key), defaultValue);
    }
}
