package com.nikolaynikolov.app.belotScorer.game.gameDialogs;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nikolaynikolov.app.belotScorer.R;
import com.nikolaynikolov.app.belotScorer.game.Game;
import com.nikolaynikolov.app.belotScorer.game.gameComponents.EntryFragment;
import com.nikolaynikolov.app.belotScorer.game.gameFunctions.GameInitializer;

public class GameEndedDialog {

//    Initializing fields
    private static String gameFinishedDialogMessage1;
    private static String gameFinishedDialogMessage2;

    private static String gameFinishedDialogTitle;

    private static String teamWon1;
    private static String teamWon2;

    private static String dialogYes;
    private static String dialogNo;
//    Getters and Setters

    public static String getGameFinishedDialogMessage1() {
        return gameFinishedDialogMessage1;
    }

    public static void setGameFinishedDialogMessage1(String gameFinishedDialogMessage1) {
        GameEndedDialog.gameFinishedDialogMessage1 = gameFinishedDialogMessage1;
    }

    public static String getGameFinishedDialogMessage2() {
        return gameFinishedDialogMessage2;
    }

    public static void setGameFinishedDialogMessage2(String gameFinishedDialogMessage2) {
        GameEndedDialog.gameFinishedDialogMessage2 = gameFinishedDialogMessage2;
    }

    public static String getGameFinishedDialogTitle() {
        return gameFinishedDialogTitle;
    }

    public static void setGameFinishedDialogTitle(String gameFinishedDialogTitle) {
        GameEndedDialog.gameFinishedDialogTitle = gameFinishedDialogTitle;
    }

    public static String getTeamWon1() {
        return teamWon1;
    }

    public static void setTeamWon1(String teamWon1) {
        GameEndedDialog.teamWon1 = teamWon1;
    }

    public static String getTeamWon2() {
        return teamWon2;
    }

    public static void setTeamWon2(String teamWon2) {
        GameEndedDialog.teamWon2 = teamWon2;
    }

    public static String getDialogYes() {
        return dialogYes;
    }

    public static void setDialogYes(String dialogYes) {
        GameEndedDialog.dialogYes = dialogYes;
    }

    public static String getDialogNo() {
        return dialogNo;
    }

    public static void setDialogNo(String dialogNo) {
        GameEndedDialog.dialogNo = dialogNo;
    }

    public static void gameEndedDialog(final int team, final Game game, final int updatedRounds){

        Activity activity = game.getActivity();

        setGameFinishedDialogMessage1(activity.getString(R.string.gameFinishedDialogMessage1));
        setGameFinishedDialogMessage2(activity.getString(R.string.gameFinishedDialogMessage2));
        setGameFinishedDialogTitle(activity.getString(R.string.gameFinishedDialogTitle));
        setTeamWon1(activity.getString(R.string.teamWon1));
        setTeamWon2(activity.getString(R.string.teamWon2));

        setDialogYes(activity.getString(R.string.dialogYes));
        setDialogNo(activity.getString(R.string.dialogNo));

        String teamName;

        if(team < 0){
            teamName = game.getLTN();
        }else{
            teamName = game.getRTN();
        }

        buildDialog(teamName, team, game, updatedRounds);
    }

    private static void buildDialog(String teamName, final int team, final Game game, final int updatedRounds) {
        AlertDialog.Builder builder = new AlertDialog.Builder(game.getActivity());
        final AlertDialog dialog = builder.create();

        LayoutInflater layoutInflater = LayoutInflater.from(game.getActivity());
        View view = layoutInflater.inflate(R.layout.game_dialog_layout, null);

        TextView dialogTitle = view.findViewById(R.id.dialogTitle);
        TextView dialogMessage = view.findViewById(R.id.dialogMessage);

        dialogTitle.setText(getGameFinishedDialogTitle());
        dialogMessage.setText(getGameFinishedDialogMessage1() + teamName + getGameFinishedDialogMessage2());

        Button dialogYesButton = view.findViewById(R.id.dialogYesButton);
        Button dialogNoButton = view.findViewById(R.id.dialogNoButton);

        dialogYesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EntryFragment.cleanTeamScores();
                GameInitializer.startFollowUpGame(team, game, updatedRounds);
            }
        });

        dialogNoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
