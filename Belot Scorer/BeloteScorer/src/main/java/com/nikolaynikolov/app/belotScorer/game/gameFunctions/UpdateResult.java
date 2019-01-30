package com.nikolaynikolov.app.belotScorer.game.gameFunctions;

import com.nikolaynikolov.app.belotScorer.game.Game;
import com.nikolaynikolov.app.belotScorer.game.gameComponents.InputField;

import java.util.ArrayList;
import java.util.Locale;

public class UpdateResult {

    public static void updateResult(Game game, boolean used, ArrayList<InputField> teamArray, int teamNumber) {
        updateTeamResult(game, teamArray, teamNumber);

        if(!used) {
            game.addNewEntry();
        }

    }

    private static void updateTeamResult(Game game, ArrayList<InputField> teamArray, int teamNumber) {

        int teamPoints = 0;

        for(InputField inputField : teamArray){
            String currentString = inputField.getText().toString();
            if(currentString.equals("")) {
                currentString = "0";
            }
            teamPoints += Integer.parseInt(currentString);
        }

        updateUIResult(game, teamPoints, teamNumber);

        if(teamPoints >= 151) {
            CheckGameStatus.checkIfGameEnded(game);
        }
    }

    private static void updateUIResult(Game game, int teamPoints, int teamNumber) {
        if(teamNumber < 0){
            game.getLeftTeamResult().setText(String.format(Locale.getDefault(), "%d", teamPoints));
            game.setLTResult(teamPoints);
        }else{
            game.getRightTeamResult().setText(String.format(Locale.getDefault(), "%d", teamPoints));
            game.setRTResult(teamPoints);
        }
    }

}
