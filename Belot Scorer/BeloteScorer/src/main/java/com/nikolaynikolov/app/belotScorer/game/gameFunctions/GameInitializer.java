package com.nikolaynikolov.app.belotScorer.game.gameFunctions;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.AdapterView;

import com.nikolaynikolov.app.belotScorer.R;
import com.nikolaynikolov.app.belotScorer.game.Game;
import com.nikolaynikolov.app.belotScorer.game.GameActivity;
import com.nikolaynikolov.app.belotScorer.helperClasses.ToastMaker;

import java.util.Locale;

public class GameInitializer {

    public static void startNewGame(GameActivity gameActivity) {
        Bundle b = gameActivity.getIntent().getExtras();
        FragmentTransaction ft = gameActivity.getFragmentManager().beginTransaction();
        gameActivity.setFirstGame(new Game());
        gameActivity.getFirstGame().setArguments(b);
        ft.add(R.id.gameActivityLinearLayout, gameActivity.getFirstGame());
        gameActivity.setNewGameFragmentId(gameActivity.getFirstGame().getId());
        ft.commit();
    }

    public static void configureOldGame(Bundle infoAboutGame, Game game) {

        game.setOldGame(true);

        game.setCurrentGameId(infoAboutGame.getInt("id"));
        game.setLTN(infoAboutGame.getString("leftTeamName"));
        game.setRTN(infoAboutGame.getString("rightTeamName"));
        game.setLTR(infoAboutGame.getInt("leftTeamRounds"));
        game.setRTR(infoAboutGame.getInt("rightTeamRounds"));

        int leftTeamResult = infoAboutGame.getInt("leftTeamResult");
        int rightTeamResult = infoAboutGame.getInt("rightTeamResult");

        if(leftTeamResult > 0 || rightTeamResult > 0){
            game.setLTResult(leftTeamResult);
            game.setRTResult(rightTeamResult);
            game.addNewFilledEntry(leftTeamResult, rightTeamResult);
        }else{
            game.addNewEntry();
        }

        game.getLeftTeamRounds().setText(String.format(Locale.getDefault(), "%d", game.getLTR()));
        game.getRightTeamRounds().setText(String.format(Locale.getDefault(), "%d", game.getRTR()));

        game.getLeftTeamName().setText(String.format(Locale.getDefault(), "%s", game.getLTN()));
        game.getRightTeamName().setText(String.format(Locale.getDefault(), "%s", game.getRTN()));

    }

    public static void startFollowUpGame (int team, Game game, int updatedRounds){
        int newLTR = game.getLTR();
        int newRTR = game.getRTR();
        String teamName;

        CheckGameStatus.checkGameNames(game);

        if(team < 0){
            newLTR = updatedRounds;
            teamName = game.getLTN();
            game.setLTR(newLTR);
            game.getLeftTeamRounds().setText(String.format(Locale.getDefault(), "%d", newLTR));
        }else{
            newRTR = updatedRounds;
            teamName = game.getRTN();
            game.setRTR(newRTR);
            game.getRightTeamRounds().setText(String.format(Locale.getDefault(), "%d", newRTR));
        }

        ToastMaker.teamWon(game.getActivity(), teamName);

        GameSaver.saveGame(game);

        GameBundle.sendGameBundle(game);
    }

    public static void startSelectedGame(AdapterView<?> parent, int position, Activity activity){

        String[] singleHistoryEntryArray = getEntryAtPosition(parent, position);

//                position is the currentGameId of the game
        String LTN = singleHistoryEntryArray[1];
        String RTN = singleHistoryEntryArray[2];
        int LTR = Integer.parseInt(singleHistoryEntryArray[3]);
        int RTR = Integer.parseInt(singleHistoryEntryArray[4]);
        int LTResult = Integer.parseInt(singleHistoryEntryArray[5]);
        int RTResult = Integer.parseInt(singleHistoryEntryArray[6]);

        GameBundle.sendGameBundle(activity, position, LTN, RTN, LTR, RTR, LTResult, RTResult);
    }
    private static String[] getEntryAtPosition(AdapterView<?> parent, int position){
        String currentGameEntry = String.valueOf(parent.getItemAtPosition(position));

        return currentGameEntry.split("-");
    }
}
