package app.simonsays.com.simonsays_app.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.simonsays.com.simonsays_app.R;

/**
 * Created by darielcruzhdez on 8/11/17.
 */

public class GameOverDialog extends AlertDialog {

    private GameOverOnClickListener mOnClickListener;
    private Context mContext;

    public GameOverDialog(@NonNull Context context, GameOverOnClickListener listener) {
        super(context);
        mContext = context;
        mOnClickListener = listener;
    }

    public AlertDialog OnCreateDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.game_over_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText nameEd = (EditText) dialogView.findViewById(R.id.userNameEditText);

        dialogBuilder.setPositiveButton(mContext.getString(R.string.save), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mOnClickListener.onSaveScore(nameEd);
            }
        });
        dialogBuilder.setNegativeButton(mContext.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        dialogBuilder.setCancelable(false);
        AlertDialog b = dialogBuilder.create();
        return b;
    }

    public interface GameOverOnClickListener{
        void onSaveScore(TextView tv);
    }
}
