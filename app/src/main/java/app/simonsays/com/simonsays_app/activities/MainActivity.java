package app.simonsays.com.simonsays_app.activities;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.simonsays.com.simonsays_app.R;
import app.simonsays.com.simonsays_app.models.Score;
import app.simonsays.com.simonsays_app.utils.FileHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FileHelper fh = FileHelper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.startGameBtn)).setOnClickListener(this);
        (findViewById(R.id.scoreBtn)).setOnClickListener(this);
        (findViewById(R.id.exitGameBtn)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startGameBtn:
                Intent startIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(startIntent);
                break;
            case R.id.scoreBtn:
                Intent scoreIntent = new Intent(MainActivity.this, ScoresActivity.class);
                startActivity(scoreIntent);
                break;
            case R.id.exitGameBtn:
                fh.saveScore();
                System.exit(0);
                break;
        }
    }
}
