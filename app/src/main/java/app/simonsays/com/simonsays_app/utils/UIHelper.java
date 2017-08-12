package app.simonsays.com.simonsays_app.utils;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.Button;

import app.simonsays.com.simonsays_app.R;

/**
 * Created by darielcruzhdez on 8/8/17.
 */

public class UIHelper {

    private static FileHelper fh = FileHelper.getInstance();

    private static Handler h = new Handler();
    public static int BUTTON_HIGHLIGHT_TIME = 1400 * fh.readIntPreferences(R.string.duration_preference, R.string.duration_preference, 1);
    public static final int BUTTON_BEFORE_HIGHLIGHT_TIME = 500;
    
    public static void highlightButton(final Button button, final boolean playSound, final MediaPlayer mp, int delay, @NonNull final HighlightButtonListener listener){

        h.postDelayed(new Runnable(){
            public void run(){
                if(playSound)
                    mp.start();

                button.setPressed(true);
            }
        }, (delay - BUTTON_HIGHLIGHT_TIME)+ BUTTON_BEFORE_HIGHLIGHT_TIME);
        h.postDelayed(new Runnable(){
            public void run(){
                if(mp.isPlaying()) {
                    mp.stop();
                    mp.release();
                }

                button.setPressed(false);
                listener.onButtonHighlighted();
            }
        }, delay);
    }

    public static void disableButtons(Button... buttons){
        for(Button b : buttons)
            b.setEnabled(false);
    }

    public static void enableButtons(Button... buttons){
        for(Button b : buttons)
            b.setEnabled(true);
    }

    public interface HighlightButtonListener {
        void onButtonHighlighted();
    }

}
