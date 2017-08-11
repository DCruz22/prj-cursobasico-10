package app.simonsays.com.simonsays_app.Utils;

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

    //https://nfrolov.wordpress.com/2014/07/12/android-using-context-statically-and-in-singletons/
    private static List<Score> scores = new ArrayList<>();

    public static Score getScore(int index){
        return scores.get(0);
    }

    public static List<Score> getScore(){
        return scores;
    }

    public static void addScore(Score score){
        scores.add(score);
    }

    public static void fillScoresList(Context c){
        String json = readStringPreferences(c, R.string.scores_preference, R.string.scores_preference, "");

        List<Score> savedScores = JSONParser.getScoresFromJson(json);

        if(savedScores != null) {
            for (Score s : savedScores) {
                addScore(s);
            }
        }
    }

    public static void saveScore(Context c){
        String json = JSONParser.getJsonFromScores(scores);
        writeStringPreferences(c, R.string.scores_preference, R.string.scores_preference, json);
    }

    public static void writeIntPreferences(Context c, int preferenceResource, int key, int value){
        SharedPreferences sharedPref = c.getSharedPreferences(c.getString(preferenceResource), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(c.getString(key), value);
        editor.commit();
    }

    public static int readIntPreferences(Context c, int preferenceResource, int key, int defaultValue){
        SharedPreferences sharedPref = c.getSharedPreferences(c.getString(preferenceResource), Context.MODE_PRIVATE);
        return sharedPref.getInt(c.getString(key), defaultValue);
    }


    public static void writeStringPreferences(Context c, int preferenceResource, int key, String value){
        SharedPreferences sharedPref = c.getSharedPreferences(c.getString(preferenceResource), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(c.getString(key), value);
        editor.commit();
    }

    public static String readStringPreferences(Context c, int preferenceResource, int key, String defaultValue){
        SharedPreferences sharedPref = c.getSharedPreferences(c.getString(preferenceResource), Context.MODE_PRIVATE);
        return sharedPref.getString(c.getString(key), defaultValue);
    }
}
