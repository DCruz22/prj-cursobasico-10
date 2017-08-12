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

public class InfoDialog extends AlertDialog {

    private Context mContext;

    public InfoDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public AlertDialog OnCreateDialog() {
        Builder dialogBuilder = new Builder(mContext);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.info_dialog, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setPositiveButton(mContext.getString(R.string.ok), new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        dialogBuilder.setCancelable(true);
        AlertDialog b = dialogBuilder.create();
        return b;
    }
}
