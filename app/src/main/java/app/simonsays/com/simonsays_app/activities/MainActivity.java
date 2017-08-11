package app.simonsays.com.simonsays_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.simonsays.com.simonsays_app.R;
import app.simonsays.com.simonsays_app.Utils.FileHelper;

public class MainActivity extends AppCompatActivity {

    private Button mStartGameBtn;

    private FileHelper fileHelper = FileHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileHelper.fillScoresList(this);

        mStartGameBtn = (Button) findViewById(R.id.startGameBtn);
        mStartGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);
            }
        });
    }
}
