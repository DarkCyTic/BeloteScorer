package com.nikolaynikolov.app.belotScorer.history;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.nikolaynikolov.app.belotScorer.R;
import com.nikolaynikolov.app.belotScorer.game.gameFunctions.GameInitializer;
import com.nikolaynikolov.app.belotScorer.game.gameFunctions.GameSaver;
import com.nikolaynikolov.app.belotScorer.helperClasses.ActivityChanger;

public class HistoryActivity extends AppCompatActivity {

    private ListView historyList;
    private Button deleteHistoryButton;


    public ListView getHistoryList() {
        return historyList;
    }

    public void setHistoryList(ListView historyList) {
        this.historyList = historyList;
    }

    public Button getDeleteHistoryButton() {
        return deleteHistoryButton;
    }

    public void setDeleteHistoryButton(Button deleteHistoryButton) {
        this.deleteHistoryButton = deleteHistoryButton;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity_layout);

        setDeleteHistoryButton((Button) findViewById(R.id.deleteHistoryButton));

        String historyString = GameSaver.getHistoryString(this);

        if(historyString.equals("0")){
            ActivityChanger.startMainActivity(this);
            return;
        }

        setUpHistoryList();

        getHistoryList().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameInitializer.startSelectedGame(parent, position, HistoryActivity.this);
            }
        });

        getDeleteHistoryButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteHistoryDialog.deleteHistoryDialog(HistoryActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        ActivityChanger.startMainActivity(this);
        super.onBackPressed();
    }

    private void setUpHistoryList(){
        String historyString = GameSaver.getHistoryString(this);
        String [] historyArray = historyString.split(",");

        HistoryListAdapter historyListAdapter = new HistoryListAdapter(this, historyArray);
        setHistoryList((ListView) findViewById(R.id.historyList));
        getHistoryList().setAdapter(historyListAdapter);
    }
}
