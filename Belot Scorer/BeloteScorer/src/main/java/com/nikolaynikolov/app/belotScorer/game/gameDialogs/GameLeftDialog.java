package com.nikolaynikolov.app.belotScorer.game.gameDialogs;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nikolaynikolov.app.belotScorer.R;
import com.nikolaynikolov.app.belotScorer.helperClasses.ActivityChanger;
import com.nikolaynikolov.app.belotScorer.main.mainActivity;

public class GameLeftDialog {

    //    Initializing fields
    private static String gameLeftDialogMessage;

    private static String gameLeftDialogTitle;

    private static String dialogYes;
    private static String dialogNo;

    //    Getters and Setters
    public static String getGameLeftDialogMessage() {
        return gameLeftDialogMessage;
    }

    public static void setGameLeftDialogMessage(String gameLeftDialogMessage) {
        GameLeftDialog.gameLeftDialogMessage = gameLeftDialogMessage;
    }

    public static String getGameLeftDialogTitle() {
        return gameLeftDialogTitle;
    }

    public static void setGameLeftDialogTitle(String gameLeftDialogTitle) {
        GameLeftDialog.gameLeftDialogTitle = gameLeftDialogTitle;
    }

    public static String getDialogYes() {
        return dialogYes;
    }

    public static void setDialogYes(String dialogYes) {
        GameLeftDialog.dialogYes = dialogYes;
    }

    public static String getDialogNo() {
        return dialogNo;
    }

    public static void setDialogNo(String dialogNo) {
        GameLeftDialog.dialogNo = dialogNo;
    }

    public static void gameLeftDialog(Activity activity){

        setGameLeftDialogMessage(activity.getString(R.string.gameLeftDialogMessage));
        setGameLeftDialogTitle(activity.getString(R.string.gameLeftDialogTitle));

        setDialogYes(activity.getString(R.string.dialogYes));
        setDialogNo(activity.getString(R.string.dialogNo));

        buildDialog(activity);
    }

    private static void buildDialog(final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final AlertDialog dialog = builder.create();

        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.game_dialog_layout, null);

        TextView dialogTitle = view.findViewById(R.id.dialogTitle);
        TextView dialogMessage =view.findViewById(R.id.dialogMessage);

        dialogTitle.setText(R.string.gameLeftDialogTitle);
        dialogMessage.setText(R.string.gameLeftDialogMessage);

        Button dialogYesButton = view.findViewById(R.id.dialogYesButton);
        Button dialogNoButton = view.findViewById(R.id.dialogNoButton);

        dialogYesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ActivityChanger.startActivity(activity, mainActivity.class);
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
