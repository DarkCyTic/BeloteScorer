package com.nikolaynikolov.app.belotScorer.history;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nikolaynikolov.app.belotScorer.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HistoryListAdapter extends ArrayAdapter <String> {

    private MyViewHolder holder;

    HistoryListAdapter(Context context, String[] resource) {
        super(context, R.layout.history_list_entry_layout ,resource);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.history_list_entry_layout, parent, false);
            holder = new MyViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }

        String singleHistoryEntryString = getItem(position);

        if (singleHistoryEntryString != null && !singleHistoryEntryString.isEmpty()) {
            setUpEntry(singleHistoryEntryString);
        }

        holder.deleteEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteHistoryEntryDialog.deleteHistoryDialog((HistoryActivity) getContext(), position);
            }
        });

        return view;
    }

    private void setUpEntry(String singleHistoryEntryString){
        String[] singleHistoryEntryArray = singleHistoryEntryString.split("-");

//            int id = Integer.parseInt(singleHistoryEntryArray[0]);
        long dateInMillis = Long.parseLong(singleHistoryEntryArray[0]);
        String leftTeamName = singleHistoryEntryArray[1];
        String rightTeamName = singleHistoryEntryArray[2];
        int leftTeamRounds = Integer.parseInt(singleHistoryEntryArray[3]);
        int rightTeamRounds = Integer.parseInt(singleHistoryEntryArray[4]);

        Date date = new Date();
        date.setTime(dateInMillis);
        String formattedDate = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(date);

        holder.dateTextView.setText(formattedDate);
        holder.teamsTextView.setText((leftTeamName + " - " + rightTeamName));
        holder.roundsTextView.setText((leftTeamRounds + "-" + rightTeamRounds));
    }
}
