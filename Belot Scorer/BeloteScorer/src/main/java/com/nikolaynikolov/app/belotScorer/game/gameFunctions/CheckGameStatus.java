package com.nikolaynikolov.app.belotScorer.game.gameFunctions;

import android.widget.TextView;

import com.nikolaynikolov.app.belotScorer.R;
import com.nikolaynikolov.app.belotScorer.game.Game;
import com.nikolaynikolov.app.belotScorer.game.gameDialogs.GameEndedDialog;
import com.nikolaynikolov.app.belotScorer.helperClasses.ToastMaker;

public class CheckGameStatus {
    public static void checkIfGameEnded(Game game) {
        int leftTeamPoints = Integer.parseInt(game.getLeftTeamResult().getText().toString());
        int rightTeamPoints = Integer.parseInt(game.getRightTeamResult().getText().toString());

        if(leftTeamPoints >= 151 && leftTeamPoints > rightTeamPoints){

            int updatedRounds = Integer.parseInt(game.getLeftTeamRounds().getText().toString()) + 1;

            GameEndedDialog.gameEndedDialog(-1, game, updatedRounds);
        }else if(rightTeamPoints >= 151 && rightTeamPoints > leftTeamPoints ){
            int updatedRounds = Integer.parseInt(game.getRightTeamRounds().getText().toString()) + 1;
            GameEndedDialog.gameEndedDialog(1, game, updatedRounds);
        }
    }

    public static boolean checkIfDialogNeeded(TextView leftTeamResult, TextView rightTeamResult) {
        if(leftTeamResult.getText().toString().equals("0") && rightTeamResult.getText().toString().equals("0")){
            return false;
        }
        return true;
    }

    public static void checkGameNames(Game game) {
        if(game.getLTN().isEmpty() || game.getLTN() == null){
            game.setLTN(game.getActivity().getResources().getString(R.string.weTeam));
            ToastMaker.emptyName(game.getActivity());
        }
        if(game.getRTN().isEmpty() || game.getRTN() == null){
            game.setRTN(game.getActivity().getResources().getString(R.string.youTeam));
            ToastMaker.emptyName(game.getActivity());
        }
    }
}
