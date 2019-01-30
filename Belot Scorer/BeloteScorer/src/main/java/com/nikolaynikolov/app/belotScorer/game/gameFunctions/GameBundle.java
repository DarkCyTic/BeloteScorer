package com.nikolaynikolov.app.belotScorer.game.gameFunctions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.nikolaynikolov.app.belotScorer.game.Game;
import com.nikolaynikolov.app.belotScorer.game.GameActivity;

public class GameBundle {
    public static void sendGameBundle(Game game){

        Intent intent = new Intent(game.getActivity(), GameActivity.class);
        Bundle gameBundle = new Bundle();

        gameBundle.putInt("id", game.getCurrentGameId()); //ID
        gameBundle.putString("leftTeamName", game.getLTN()); //LTN
        gameBundle.putString("rightTeamName", game.getRTN()); //RTN
        gameBundle.putInt("leftTeamRounds", game.getLTR()); //RTR
        gameBundle.putInt("rightTeamRounds", game.getRTR()); //RTR

        gameBundle.putInt("leftTeamResult", 0);
        gameBundle.putInt("rightTeamResult", 0);

        intent.putExtras(gameBundle); //Put your game details to your next Intent

        game.getActivity().startActivity(intent);
    }
    public static void sendGameBundle(Activity activity, int position, String LTN, String RTN, int LTR, int RTR, int LTResult, int RTResult){

        Intent intent = new Intent(activity, GameActivity.class);
        Bundle gameBundle = new Bundle();

        gameBundle.putInt("id", position); //ID
        gameBundle.putString("leftTeamName", LTN); //LTN
        gameBundle.putString("rightTeamName", RTN); //RTN
        gameBundle.putInt("leftTeamRounds", LTR); //RTR
        gameBundle.putInt("rightTeamRounds", RTR); //RTR

        gameBundle.putInt("leftTeamResult", LTResult);
        gameBundle.putInt("rightTeamResult", RTResult);

        intent.putExtras(gameBundle); //Put your game details to your next Intent

        activity.startActivity(intent);
    }
}
