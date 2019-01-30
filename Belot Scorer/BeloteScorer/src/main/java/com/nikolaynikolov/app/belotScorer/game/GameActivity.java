package com.nikolaynikolov.app.belotScorer.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nikolaynikolov.app.belotScorer.R;
import com.nikolaynikolov.app.belotScorer.game.gameComponents.EntryFragment;
import com.nikolaynikolov.app.belotScorer.game.gameComponents.InputField;
import com.nikolaynikolov.app.belotScorer.game.gameFunctions.GameInitializer;
import com.nikolaynikolov.app.belotScorer.game.gameFunctions.GameSaver;
import com.nikolaynikolov.app.belotScorer.helperClasses.ActivityChanger;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements EntryFragment.EntryFragmentListener {

    private int newGameFragmentId = 0;

    private Game firstGame;

    public int getNewGameFragmentId() {
        return newGameFragmentId;
    }

    public void setNewGameFragmentId(int newGameFragmentId) {
        this.newGameFragmentId = newGameFragmentId;
    }

    public Game getFirstGame() {
        return firstGame;
    }

    public void setFirstGame(Game firstGame) {
        this.firstGame = firstGame;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity_layout);

        GameInitializer.startNewGame(this);
    }

    @Override
    public void updateResult(boolean used, ArrayList<InputField> teamArray, int teamNumber) {
        Game frag = (Game) getFragmentManager().findFragmentById(getNewGameFragmentId());
        frag.updateResult(used, teamArray, teamNumber);
    }

    @Override
    public void onBackPressed() {
//        boolean dialogNeeded = CheckGameStatus.checkIfDialogNeeded(firstGame.getLeftTeamResult(), firstGame.getRightTeamResult());
//        if (dialogNeeded) {
//            GameLeftDialog.gameLeftDialog(this);
//        } else {
//            ActivityChanger.startMainActivity(this);
//        }
//        EntryFragment.cleanTeamScores();
        GameSaver.saveGame(firstGame);
        ActivityChanger.startMainActivity(this);
    }
}
