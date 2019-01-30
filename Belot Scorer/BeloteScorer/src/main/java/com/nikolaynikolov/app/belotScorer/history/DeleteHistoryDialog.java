package com.nikolaynikolov.app.belotScorer.history;


import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nikolaynikolov.app.belotScorer.R;
import com.nikolaynikolov.app.belotScorer.game.gameFunctions.GameSaver;
import com.nikolaynikolov.app.belotScorer.helperClasses.ActivityChanger;
import com.nikolaynikolov.app.belotScorer.helperClasses.ToastMaker;

public class DeleteHistoryDialog {

    public static void deleteHistoryDialog(Activity activity){

        buildDialog(activity);
    }

    private static void buildDialog(final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final AlertDialog dialog = builder.create();

        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.game_dialog_layout, null);

        TextView dialogTitle = view.findViewById(R.id.dialogTitle);
        TextView dialogMessage =view.findViewById(R.id.dialogMessage);

        dialogTitle.setText(R.string.deleteHistoryDialogTitle);
        dialogMessage.setText(R.string.deleteHistoryDialogMessage);

        Button dialogYesButton = view.findViewById(R.id.dialogYesButton);
        Button dialogNoButton = view.findViewById(R.id.dialogNoButton);

        dialogYesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                GameSaver.deleteHistory(activity);
                ActivityChanger.startMainActivity(activity);
                ToastMaker.historyDeleted(activity);
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
