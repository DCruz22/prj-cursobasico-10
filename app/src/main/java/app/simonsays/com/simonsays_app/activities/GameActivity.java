package app.simonsays.com.simonsays_app.activities;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import app.simonsays.com.simonsays_app.R;
import app.simonsays.com.simonsays_app.dialogs.GameOverDialog;
import app.simonsays.com.simonsays_app.dialogs.InfoDialog;
import app.simonsays.com.simonsays_app.dialogs.SettingsDialog;
import app.simonsays.com.simonsays_app.models.Score;
import app.simonsays.com.simonsays_app.utils.FileHelper;
import app.simonsays.com.simonsays_app.utils.UIHelper;
import app.simonsays.com.simonsays_app.models.Shapes;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, UIHelper.HighlightButtonListener, GameOverDialog.GameOverOnClickListener, SettingsDialog.SettingsOnClickListener {

    private static List<Shapes> mShapeSequence;
    private static int mPositionInSequence;
    private static int mCurrentScore = 0;
    private static int mHighScore;
    private int mButtonsHighlighted;
    private boolean withSound;

    private Random generator = new Random();
    private FileHelper fh = FileHelper.getInstance();

    private Button mGreenBtn;
    private Button mRedBtn;
    private Button mBlueBtn;
    private Button mYellowBtn;

    private TextView mGameStatusTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mBlueBtn = (Button) findViewById(R.id.blueBtn);
        mRedBtn = (Button) findViewById(R.id.redBtn);
        mGreenBtn = (Button) findViewById(R.id.greenBtn);
        mYellowBtn = (Button) findViewById(R.id.yellowBtn);

        mGameStatusTv = (TextView) findViewById(R.id.gameStatusTv);

        mHighScore = fh.readIntPreferences(R.string.highscore_preference, R.string.highscore_preference, 0);
        withSound = fh.readIntPreferences(R.string.sound_preference, R.string.sound_preference, 1) == 1;

        updateScoreTv(mHighScore, R.id.highScoreTv, R.string.high_score);
        updateScoreTv(mCurrentScore, R.id.currenScoreTv, R.string.current_score);

        //Disabling color buttons prior to the start of the game
        UIHelper.disableButtons(mBlueBtn, mRedBtn, mGreenBtn, mYellowBtn);

        mBlueBtn.setOnClickListener(this);
        mRedBtn.setOnClickListener(this);
        mGreenBtn.setOnClickListener(this);
        mYellowBtn.setOnClickListener(this);
        findViewById(R.id.startBtn).setOnClickListener(this);
        findViewById(R.id.settingsBtn).setOnClickListener(this);
        findViewById(R.id.infoBtn).setOnClickListener(this);

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
            case R.id.settingsBtn:
                new SettingsDialog(this, this).OnCreateDialog().show();
                break;
            case R.id.infoBtn:
                new InfoDialog(this).OnCreateDialog().show();
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
        mGameStatusTv.setText(R.string.press_start);
        UIHelper.disableButtons(mBlueBtn, mRedBtn, mGreenBtn, mYellowBtn);
        new GameOverDialog(this, this).OnCreateDialog().show();
    }

    private void displaySequence(List<Shapes> shapes, @NonNull UIHelper.HighlightButtonListener listener){
        int count = 1;
        for(Shapes s : shapes){
            switch (s){
                case BLUE:
                    UIHelper.highlightButton(mBlueBtn, withSound, MediaPlayer.create(this, R.raw.g), (UIHelper.BUTTON_HIGHLIGHT_TIME * count), listener);
                    break;
                case GREEN:
                    UIHelper.highlightButton(mGreenBtn, withSound, MediaPlayer.create(this, R.raw.a), (UIHelper.BUTTON_HIGHLIGHT_TIME * count),listener);
                    break;
                case YELLOW:
                    UIHelper.highlightButton(mYellowBtn, withSound, MediaPlayer.create(this, R.raw.d), (UIHelper.BUTTON_HIGHLIGHT_TIME * count), listener);
                    break;
                case RED:
                    UIHelper.highlightButton(mRedBtn, withSound, MediaPlayer.create(this, R.raw.c), (UIHelper.BUTTON_HIGHLIGHT_TIME * count), listener);
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
            fh.writeIntPreferences(R.string.highscore_preference, R.string.highscore_preference, mHighScore);
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

    @Override
    public void onSaveScore(TextView tv) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDateAndTime = sdf.format(new Date());
        String name = tv.getText().toString().equals("") ? getString(R.string.anonymous) : tv.getText().toString();
        fh.addScore(new Score(name, mCurrentScore, currentDateAndTime));
        fh.saveScore();
    }

    @Override
    public void onSaveSettings(NumberPicker np, ToggleButton tb) {
        fh.writeIntPreferences(R.string.duration_preference, R.string.duration_preference, np.getValue());
        fh.writeIntPreferences(R.string.sound_preference, R.string.sound_preference, tb.isChecked() ? 1 : 0);
        UIHelper.BUTTON_HIGHLIGHT_TIME = 1400 * np.getValue();
        withSound = tb.isChecked();
    }
}
