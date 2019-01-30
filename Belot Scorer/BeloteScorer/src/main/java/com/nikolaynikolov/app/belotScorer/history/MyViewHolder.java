package com.nikolaynikolov.app.belotScorer.history;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nikolaynikolov.app.belotScorer.R;

class MyViewHolder {
    TextView dateTextView;
    TextView teamsTextView;
    TextView roundsTextView;
    ImageButton deleteEntryButton;

    MyViewHolder(View v){
        dateTextView = v.findViewById(R.id.dateTextView);
        teamsTextView = v.findViewById(R.id.teamsTextView);
        roundsTextView = v.findViewById(R.id.roundsTextView);
        deleteEntryButton = v.findViewById(R.id.deleteEntryButton);
        deleteEntryButton.setFocusable(false);
    }
}
