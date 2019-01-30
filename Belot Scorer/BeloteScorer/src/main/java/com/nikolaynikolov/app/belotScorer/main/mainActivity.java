package com.nikolaynikolov.app.belotScorer.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nikolaynikolov.app.belotScorer.R;
import com.nikolaynikolov.app.belotScorer.game.GameActivity;
import com.nikolaynikolov.app.belotScorer.game.gameComponents.EntryFragment;
import com.nikolaynikolov.app.belotScorer.helperClasses.ChangeLocale;
import com.nikolaynikolov.app.belotScorer.helperClasses.ToastMaker;
import com.nikolaynikolov.app.belotScorer.history.HistoryActivity;

import java.util.Locale;

public class mainActivity extends AppCompatActivity {

    private Button newGameButton;

    private boolean doubleBackToExitPressedOnce = false;

    private Button continueGameButton;

//    private ImageView infoButton;

    private ImageView ukFlag;
    private ImageView bgFlag;

    private boolean thereIsHistory;

    public Button getNewGameButton() {
        return newGameButton;
    }

    public void setNewGameButton(Button newGameButton) {
        this.newGameButton = newGameButton;
    }

    public Button getContinueGameButton() {
        return continueGameButton;
    }

    public void setContinueGameButton(Button continueGameButton) {
        this.continueGameButton = continueGameButton;
    }

//    public ImageView getInfoButton() {
//        return infoButton;
//    }
//
//    public void setInfoButton(ImageView infoButton) {
//        this.infoButton = infoButton;
//    }

    public ImageView getUkFlag() {
        return ukFlag;
    }

    public void setUkFlag(ImageView ukFlag) {
        this.ukFlag = ukFlag;
    }

    public ImageView getBgFlag() {
        return bgFlag;
    }

    public void setBgFlag(ImageView bgFlag) {
        this.bgFlag = bgFlag;
    }

    public boolean isThereIsHistory() {
        return thereIsHistory;
    }

    public void setThereIsHistory(boolean thereIsHistory) {
        this.thereIsHistory = thereIsHistory;
    }

    public boolean isDoubleBackToExitPressedOnce() {
        return doubleBackToExitPressedOnce;
    }

    public void setDoubleBackToExitPressedOnce(boolean doubleBackToExitPressedOnce) {
        this.doubleBackToExitPressedOnce = doubleBackToExitPressedOnce;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        setNewGameButton((Button) findViewById(R.id.newGameButton));
        setContinueGameButton((Button) findViewById(R.id.historyButton));

//        setInfoButton((ImageView) findViewById(R.id.infoButton));

        setUkFlag((ImageView) findViewById(R.id.ukFlag));
        setBgFlag((ImageView) findViewById(R.id.bgFlag));

//        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        SharedPreferences.Editor editor = preferences1.edit();
//        editor.remove("history");
//        editor.apply();


        setThereIsHistory(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).contains("history"));

        if(!isThereIsHistory()){
            getContinueGameButton().setEnabled(false);
            getContinueGameButton().setBackground(getResources().getDrawable(R.drawable.round_grey_button));
        }

        getNewGameButton().setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        EntryFragment.cleanTeamScores();

        getContinueGameButton().setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(mainActivity.this, HistoryActivity.class);
                    startActivity(intent);
            }
        });

//        getInfoButton().setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mainActivity.this, informationActivity.class);
//                ActivityChanger(intent);
//            }
//        });

        getUkFlag().setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(getResources().getConfiguration().locale.getLanguage().equals(new Locale("bg").getLanguage())) {
                    ChangeLocale.changeLocale(mainActivity.this, "en", "UK");
                }else{
                    ToastMaker.languageAlreadySelected(mainActivity.this);
                }
            }
        });

        getBgFlag().setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!getResources().getConfiguration().locale.getLanguage().equals(new Locale("bg").getLanguage())) {
                    ChangeLocale.changeLocale(mainActivity.this, "bg", "BG");
                }else{
                    ToastMaker.languageAlreadySelected(mainActivity.this);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (isDoubleBackToExitPressedOnce()) {
            super.onBackPressed();

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

        this.setDoubleBackToExitPressedOnce(true);

        ToastMaker.exitGame(this);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                setDoubleBackToExitPressedOnce(false);
            }
        }, 2000);
    }

}
