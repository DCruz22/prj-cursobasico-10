package app.simonsays.com.simonsays_app;

import android.app.Application;
import android.content.Context;
import android.view.View;

import app.simonsays.com.simonsays_app.utils.FileHelper;

/**
 * Created by darielcruzhdez on 7/20/17.
 */

public class SimonSays extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FileHelper fh = FileHelper.getInstance();
        fh.init(this);
        fh.fillScoresList();
    }
}
