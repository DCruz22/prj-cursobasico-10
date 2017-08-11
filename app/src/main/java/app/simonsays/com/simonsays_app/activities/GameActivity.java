package app.simonsays.com.simonsays_app.activities;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.simonsays.com.simonsays_app.R;
import app.simonsays.com.simonsays_app.Utils.FileHelper;
import app.simonsays.com.simonsays_app.Utils.UIHelper;
import app.simonsays.com.simonsays_app.models.Shapes;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, UIHelper.HighlightButtonListener {

    private static List<Shapes> mShapeSequence;
    private static int mPositionInSequence;
    private static int mCurrentScore = 0;
    private static int mHighScore;

    private Random generator = new Random();

    private Button mGreenBtn;
    private Button mRedBtn;
    private Button mBlueBtn;
    private Button mYellowBtn;

    private TextView mGameStatusTv;
    private int mButtonsHighlighted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mBlueBtn = (Button) findViewById(R.id.blueBtn);
        mRedBtn = (Button) findViewById(R.id.redBtn);
        mGreenBtn = (Button) findViewById(R.id.greenBtn);
        mYellowBtn = (Button) findViewById(R.id.yellowBtn);
        Button mStartBtn = (Button) findViewById(R.id.startBtn);

        mGameStatusTv = (TextView) findViewById(R.id.gameStatusTv);

        mHighScore = FileHelper.readIntPreferences(this, R.string.highscore_preference, R.string.highscore_preference, 0);
        updateScoreTv(mHighScore, R.id.highScoreTv, R.string.high_score);
        updateScoreTv(mCurrentScore, R.id.currenScoreTv, R.string.current_score);

        //Disabling color buttons prior to the start of the game
        UIHelper.disableButtons(mBlueBtn, mRedBtn, mGreenBtn, mYellowBtn);

        mBlueBtn.setOnClickListener(this);
        mRedBtn.setOnClickListener(this);
        mGreenBtn.setOnClickListener(this);
        mYellowBtn.setOnClickListener(this);
        mStartBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startBtn:
                startGame();
                break;
            case R.id.greenBtn:
                verifySequence(Shapes.GREEN);
                break;
            case R.id.blueBtn:
                verifySequence(Shapes.BLUE);
                break;
            case R.id.yellowBtn:
                verifySequence(Shapes.YELLOW);
                break;
            case R.id.redBtn:
                verifySequence(Shapes.RED);
                break;
        }
    }

    private void startGame(){
        mShapeSequence = new ArrayList<>();
        mCurrentScore = 0;
        updateScoreTv(mCurrentScore, R.id.currenScoreTv, R.string.current_score);
        continueGame();
    }

    private void continueGame(){
        mShapeSequence.add(Shapes.values()[generator.nextInt(4)]);
        UIHelper.disableButtons(mBlueBtn, mRedBtn, mGreenBtn, mYellowBtn);
        mGameStatusTv.setText(getText(R.string.simon_turn));

        displaySequence(mShapeSequence, this);
    }

    private void gameOver(){
        mShapeSequence = null;
        Toast.makeText(this, "Usted ha perdido", Toast.LENGTH_SHORT).show();
        mGameStatusTv.setText(R.string.press_start);
        UIHelper.disableButtons(mBlueBtn, mRedBtn, mGreenBtn, mYellowBtn);
    }

    private void displaySequence(List<Shapes> shapes, @NonNull UIHelper.HighlightButtonListener listener){
        int count = 1;
        for(Shapes s : shapes){
            switch (s){
                case BLUE:
                    UIHelper.highlightButton(mBlueBtn, (UIHelper.BUTTON_HIGHLIGHT_TIME * count), listener);
                    break;
                case GREEN:
                    UIHelper.highlightButton(mGreenBtn, (UIHelper.BUTTON_HIGHLIGHT_TIME * count),listener);
                    break;
                case YELLOW:
                    UIHelper.highlightButton(mYellowBtn, (UIHelper.BUTTON_HIGHLIGHT_TIME * count), listener);
                    break;
                case RED:
                    UIHelper.highlightButton(mRedBtn, (UIHelper.BUTTON_HIGHLIGHT_TIME * count), listener);
                    break;
            }

            count++;
        }
    }

    private void verifySequence(Shapes shape){
        mPositionInSequence++;

        if(mShapeSequence.get(mPositionInSequence) == shape){

            if(mShapeSequence.size() - 1 == mPositionInSequence) {
                mCurrentScore++;
                updateScoreTv(mCurrentScore, R.id.currenScoreTv, R.string.current_score);
                verifyHighScore();
                continueGame();
            }
        }else{
            gameOver();
        }
    }

    private void verifyHighScore(){
        if(mCurrentScore > mHighScore){
            mHighScore = mCurrentScore;
            updateScoreTv(mHighScore, R.id.highScoreTv, R.string.high_score);
            FileHelper.writeIntPreferences(this, R.string.highscore_preference, R.string.highscore_preference, mHighScore);
        }
    }

    private void updateScoreTv(int score, int resource, int textResource) {
        ((TextView) findViewById(resource)).setText(getString(textResource, score));
    }

    @Override
    public void onButtonHighlighted() {
        mButtonsHighlighted = mButtonsHighlighted + 1;
        if(mButtonsHighlighted == mShapeSequence.size()) {
            mButtonsHighlighted = 0;
            mPositionInSequence = -1;
            UIHelper.enableButtons(mBlueBtn, mRedBtn, mGreenBtn, mYellowBtn);
            mGameStatusTv.setText(getText(R.string.your_turn));
        }
    }
}
