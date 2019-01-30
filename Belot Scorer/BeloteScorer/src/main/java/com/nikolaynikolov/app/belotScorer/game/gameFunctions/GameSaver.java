package com.nikolaynikolov.app.belotScorer.game.gameFunctions;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.nikolaynikolov.app.belotScorer.R;
import com.nikolaynikolov.app.belotScorer.game.Game;
import com.nikolaynikolov.app.belotScorer.game.gameComponents.EntryFragment;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GameSaver {

    public static void saveGame(Game game) {
        boolean gameShouldBeSaved = (game.getLTR() > 0 || game.getRTR() > 0 || game.getLTResult() > 0 || game.getRTResult() > 0);
        boolean thereIsHistory = PreferenceManager.getDefaultSharedPreferences(game.getActivity().getApplicationContext()).contains("history");

        if (gameShouldBeSaved) {
            String toBeSavedGame = System.currentTimeMillis() + "-" + game.getLTN() + "-" + game.getRTN() + "-" + game.getLTR() + "-" + game.getRTR() + "-" + game.getLTResult() + "-" + game.getRTResult() + ",";

            GameSaver.saveGameToDevice(game, thereIsHistory, toBeSavedGame);
        } else {
            Toast.makeText(game.getActivity(), game.getActivity().getString(R.string.gameNotSaved), Toast.LENGTH_SHORT).show();
        }

        EntryFragment.cleanTeamScores();
    }

    private static void saveGameToDevice(Game game, boolean thereIsHistory, String toBeSavedGame){
        String newHistory;

        if(thereIsHistory){
            newHistory = saveToExistingHistory(game, toBeSavedGame);
        }else{
            newHistory = toBeSavedGame;
        }

        saveStringToHistory(newHistory, game.getActivity());
    }

    private static String saveToExistingHistory(Game game, String toBeSavedGame){
        String historyString = getHistoryString(game.getActivity());
        String[] historyArray = getHistoryArray(game.getActivity());

        if(game.isOldGame()) {
            historyArray[game.getCurrentGameId()] = toBeSavedGame.substring(0, toBeSavedGame.length() - 1);
            historyString = TextUtils.join(",", historyArray);
        }else{
            if(historyArray.length >= 20){
                historyArray = trimHistory(historyArray);
            }
            historyString = toBeSavedGame + TextUtils.join(",", historyArray);
            game.setCurrentGameId(0);
        }

        return historyString;
    }

    private static String[] trimHistory(String[] allGames){
        return Arrays.copyOfRange(allGames, 0, allGames.length - 1);
    }

    public static String getHistoryString(Activity activity){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        return preferences.getString("history", "0");
    }

    public static String[] getHistoryArray(Activity activity){
        return getHistoryString(activity).split(",");
    }

    public static void saveArrayToHistory(String [] historyArray, Activity activity){
        String historyString = TextUtils.join(",", historyArray);
        saveStringToHistory(historyString, activity);
    }

    private static void saveStringToHistory(String historyString, Activity activity) {
        if(historyString.isEmpty() || historyString.equals("")){
            deleteHistory(activity);
            return;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("history", historyString);
        editor.apply();
    }

    public static void deleteHistory(Activity activity) {
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = preferences1.edit();
        editor.remove("history");
        editor.apply();
    }

    public static void deleteSelectedEntry(Activity activity, int position) {
        String[] historyArray = GameSaver.returnUpdatedHistoryArray(activity, position);

        GameSaver.saveArrayToHistory(historyArray, activity);
    }

    public static String[] returnUpdatedHistoryArray(Activity activity, int position) {

        String[] historyArray = getHistoryArray(activity);

        String[] arrayBeforeEntry = Arrays.copyOfRange(historyArray, 0, position);
        String[] arrayAfterEntry = Arrays.copyOfRange(historyArray, position + 1, historyArray.length);

        int arrayBeforeEntryLength = arrayBeforeEntry.length;
        int arrayAfterEntryLength = arrayAfterEntry.length;

        @SuppressWarnings("unchecked")
        String[] updatedHistoryArray = (String[]) Array.newInstance(String.class, arrayBeforeEntryLength + arrayAfterEntryLength);
        System.arraycopy(arrayBeforeEntry, 0, updatedHistoryArray, 0, arrayBeforeEntryLength);
        System.arraycopy(arrayAfterEntry, 0, updatedHistoryArray, arrayBeforeEntryLength, arrayAfterEntryLength);

        return updatedHistoryArray;
    }
}
