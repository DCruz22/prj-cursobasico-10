package app.simonsays.com.simonsays_app.utils;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import app.simonsays.com.simonsays_app.models.Score;

/**
 * Created by darielcruzhdez on 8/10/17.
 */

public class JSONParser {

    private static Gson gson = new Gson();

    public static List<Score> getScoresFromJson(String json) {

        Log.d("json", json);

        if(!json.isEmpty())
           return Arrays.asList(gson.fromJson(json, Score[].class));

        return null;
    }

    public static String getJsonFromScores(List<Score> scores){
        return gson.toJson(scores);
    }
}
