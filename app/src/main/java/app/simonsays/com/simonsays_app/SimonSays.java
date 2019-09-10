package app.simonsays.com.simonsays_app;

import android.app.Application;
import android.content.Context;
import android.view.View;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.internal.DaoConfig;
import android.database.sqlite.SQLiteDatabase;
import app.simonsays.com.simonsays_app.utils.FileHelper;

/**
 * Created by darielcruzhdez on 7/20/17.
 */

public class SimonSays extends Application {

    private AbstractDaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        FileHelper fh = FileHelper.getInstance();
        fh.init(this);
        fh.fillScoresList();
    }
}
