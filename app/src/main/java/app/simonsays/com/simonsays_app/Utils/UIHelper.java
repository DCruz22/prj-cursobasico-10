package app.simonsays.com.simonsays_app.Utils;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.Button;

/**
 * Created by darielcruzhdez on 8/8/17.
 */

public class UIHelper {

    private static Handler h = new Handler();
    public static final int BUTTON_HIGHLIGHT_TIME = 1400;

    public static void highlightButton(final Button button, int delay, @NonNull final HighlightButtonListener listener){

        h.postDelayed(new Runnable(){
            public void run(){
                button.setPressed(true);
            }
        }, delay - BUTTON_HIGHLIGHT_TIME);
        h.postDelayed(new Runnable(){
            public void run(){
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
