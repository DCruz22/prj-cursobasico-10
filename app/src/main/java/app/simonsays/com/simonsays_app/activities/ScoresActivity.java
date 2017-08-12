package app.simonsays.com.simonsays_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import app.simonsays.com.simonsays_app.R;
import app.simonsays.com.simonsays_app.adapters.ScoresAdapter;
import app.simonsays.com.simonsays_app.models.Score;
import app.simonsays.com.simonsays_app.utils.FileHelper;

public class ScoresActivity extends AppCompatActivity {

    FileHelper fh = FileHelper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        ListView lv = (ListView) findViewById(R.id.scoresLV);

        ScoresAdapter adapter = new ScoresAdapter(this, fh.getScores());
        lv.setAdapter(adapter);
    }
}
