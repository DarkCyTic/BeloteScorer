package com.nikolaynikolov.app.belotScorer.helperClasses;

import android.app.Activity;
import android.widget.Toast;

import com.nikolaynikolov.app.belotScorer.R;

public class ToastMaker {

    private static int shortToast = Toast.LENGTH_SHORT;

    public static void teamWon(Activity activity, String teamName){

        String teamWon1 = activity.getString(R.string.teamWon1);
        String teamWon2 = activity.getString(R.string.teamWon2);

        String fullTeamWonText = teamWon1 + teamName + teamWon2;

        Toast.makeText(activity, fullTeamWonText, shortToast).show();
    }

    public static void exitGame(Activity activity) {

        String exitGameText = activity.getString(R.string.exitGame);

        Toast.makeText(activity, exitGameText, shortToast).show();
    }

    public static void languageChanged(Activity activity){

        String languageChanged = activity.getString(R.string.languageChanged);

        Toast.makeText(activity, languageChanged, shortToast).show();
    }

    public static void languageAlreadySelected(Activity activity){

        String languageAlreadySelected = activity.getString(R.string.languageAlreadySelected);

        Toast.makeText(activity, languageAlreadySelected, shortToast).show();
    }

    public static void historyDeleted(Activity activity) {

        String historyDeleted = activity.getResources().getString(R.string.historyDeleted);

        Toast.makeText(activity, historyDeleted, shortToast).show();
    }

    public static void emptyName(Activity activity) {

        String emptyName = activity.getString(R.string.emptyName);

        Toast.makeText(activity, emptyName, shortToast).show();
    }
}
