package app.simonsays.com.simonsays_app.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.ToggleButton;

import app.simonsays.com.simonsays_app.R;
import app.simonsays.com.simonsays_app.utils.FileHelper;

/**
 * Created by darielcruzhdez on 8/11/17.
 */

public class SettingsDialog extends AlertDialog {

    private SettingsOnClickListener mOnClickListener;
    private Context mContext;

    private FileHelper fh = FileHelper.getInstance();

    public SettingsDialog(@NonNull Context context, SettingsOnClickListener listener) {
        super(context);
        mContext = context;
        mOnClickListener = listener;
    }

    public AlertDialog OnCreateDialog() {
        Builder dialogBuilder = new Builder(mContext);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.settings_dialog, null);
        dialogBuilder.setView(dialogView);

        final ToggleButton soundToggleBtn = (ToggleButton) dialogView.findViewById(R.id.soundToggleBtn);
        boolean isPressed = fh.readIntPreferences(R.string.sound_preference, R.string.sound_preference, 1) == 1;
        soundToggleBtn.setChecked(isPressed);

        final NumberPicker durationNumberPicker = (NumberPicker) dialogView.findViewById(R.id.durationPicker);
        durationNumberPicker.setMinValue(1);
        durationNumberPicker.setMaxValue(5);
        durationNumberPicker.setValue(fh.readIntPreferences(R.string.duration_preference, R.string.duration_preference, 1));

        dialogBuilder.setPositiveButton(mContext.getString(R.string.save), new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mOnClickListener.onSaveSettings(durationNumberPicker, soundToggleBtn);
            }
        });
        dialogBuilder.setNegativeButton(mContext.getString(R.string.cancel), new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        dialogBuilder.setCancelable(false);
        AlertDialog b = dialogBuilder.create();
        return b;
    }

    public interface SettingsOnClickListener{
        void onSaveSettings(NumberPicker np, ToggleButton tb);
    }
}
