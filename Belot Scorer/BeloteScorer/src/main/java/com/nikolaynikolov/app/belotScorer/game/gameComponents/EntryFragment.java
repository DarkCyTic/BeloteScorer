package com.nikolaynikolov.app.belotScorer.game.gameComponents;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikolaynikolov.app.belotScorer.R;

import java.util.ArrayList;
import java.util.Locale;

public class EntryFragment extends Fragment {

    public InputField leftInputField;
    public InputField rightInputField;
    public boolean used = false;

    public static int leftTeamN = -1;
    public static int rightTeamN = 1;

    public static ArrayList<InputField> leftTeam;
    public static ArrayList<InputField> rightTeam;

    public EntryFragmentListener entryListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.entry_fragment_layout, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        leftInputField = view.findViewById(R.id.leftInputField);
        leftInputField.setTeam(-1);

        rightInputField = view.findViewById(R.id.rightInputField);
        rightInputField.setTeam(1);

        leftInputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                leftInputChange();
            }
        });
        rightInputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                rightInputChange();
            }
        });

        Bundle bundle = getArguments();
        if(bundle != null) {
            int leftTeamResult = bundle.getInt("leftTeamResult");
            int rightTeamResult = bundle.getInt("rightTeamResult");
            leftInputField.setText(String.format(Locale.getDefault(), "%d", leftTeamResult));
            rightInputField.setText(String.format(Locale.getDefault(), "%d", rightTeamResult));
        }
    }

    public static void cleanTeamScores(){
        if(leftTeam != null){
            EntryFragment.leftTeam.clear();
        }
        if(rightTeam != null){
            EntryFragment.rightTeam.clear();
        }
    }

    public void leftInputChange(){
        if(leftTeam == null){
            leftTeam = new ArrayList<>();
        }
        if(!leftInputField.isUsedInputField()){
            leftTeam.add(leftInputField);
            leftInputField.setUsedInputField(true);
        }

        updateResult(used, leftTeam, leftTeamN);

        if(!used){
            used = true;
        }
    }

    public void rightInputChange(){
        if(rightTeam == null){
            rightTeam = new ArrayList<>();
        }
        if(!rightInputField.isUsedInputField()){
            rightTeam.add(rightInputField);
            rightInputField.setUsedInputField(true);
        }

        updateResult(used, rightTeam, rightTeamN);

        if(!used){
            used = true;
        }
    }

    private void updateResult(boolean used, ArrayList<InputField> teamArray, int teamNumber) {
        entryListener.updateResult(used, teamArray, teamNumber);
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            entryListener = (EntryFragmentListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(e.toString());
        }
    }

    public interface EntryFragmentListener{
        void updateResult(boolean used, ArrayList<InputField> teamArray, int teamNumber);
    }
}
