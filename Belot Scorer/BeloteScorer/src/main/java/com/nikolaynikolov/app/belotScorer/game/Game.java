package com.nikolaynikolov.app.belotScorer.game;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nikolaynikolov.app.belotScorer.R;
import com.nikolaynikolov.app.belotScorer.game.gameComponents.EntryFragment;
import com.nikolaynikolov.app.belotScorer.game.gameComponents.InputField;
import com.nikolaynikolov.app.belotScorer.game.gameFunctions.GameInitializer;
import com.nikolaynikolov.app.belotScorer.game.gameFunctions.UpdateResult;
import com.nikolaynikolov.app.belotScorer.helperClasses.FilterInput;

import java.util.ArrayList;

public class Game extends Fragment implements EntryFragment.EntryFragmentListener{

    // Initializing fields
    private TextView leftTeamRounds;
    private TextView rightTeamRounds;

    private TextView leftTeamResult;
    private TextView rightTeamResult;

    private EditText leftTeamName;
    private EditText rightTeamName;

    private LinearLayout gameLinearLayout;

    private int currentGameId = 0;

    private String LTN = "";
    private String RTN = "";

    private int LTR;
    private int RTR;

    private int LTResult;
    private int RTResult;

    private Bundle infoAboutGame;

    private boolean oldGame = false;

//    Getters and Setters
    public TextView getLeftTeamRounds() {
        return leftTeamRounds;
    }

    public void setLeftTeamRounds(TextView leftTeamRounds) {
        this.leftTeamRounds = leftTeamRounds;
    }

    public TextView getRightTeamRounds() {
        return rightTeamRounds;
    }

    public void setRightTeamRounds(TextView rightTeamRounds) {
        this.rightTeamRounds = rightTeamRounds;
    }

    public TextView getLeftTeamResult() {
        return leftTeamResult;
    }

    public void setLeftTeamResult(TextView leftTeamResult) {
        this.leftTeamResult = leftTeamResult;
    }

    public TextView getRightTeamResult() {
        return rightTeamResult;
    }

    public void setRightTeamResult(TextView rightTeamResult) {
        this.rightTeamResult = rightTeamResult;
    }

    public EditText getLeftTeamName() {
        return leftTeamName;
    }

    public void setLeftTeamName(EditText leftTeamName) {
        this.leftTeamName = leftTeamName;
    }

    public EditText getRightTeamName() {
        return rightTeamName;
    }

    public void setRightTeamName(EditText rightTeamName) {
        this.rightTeamName = rightTeamName;
    }

    public LinearLayout getGameLinearLayout() {
        return gameLinearLayout;
    }

    public void setGameLinearLayout(LinearLayout gameLinearLayout) {
        this.gameLinearLayout = gameLinearLayout;
    }

    public int getCurrentGameId() {
        return currentGameId;
    }

    public void setCurrentGameId(int currentGameId) {
        this.currentGameId = currentGameId;
    }

    public String getLTN() {
        return LTN;
    }

    public void setLTN(String LTN) {
        this.LTN = LTN;
    }

    public String getRTN() {
        return RTN;
    }

    public void setRTN(String RTN) {
        this.RTN = RTN;
    }

    public int getLTR() {
        return LTR;
    }

    public void setLTR(int LTR) {
        this.LTR = LTR;
    }

    public int getRTR() {
        return RTR;
    }

    public void setRTR(int RTR) {
        this.RTR = RTR;
    }

    public boolean isOldGame() {
        return oldGame;
    }

    public void setOldGame(boolean oldGame) {
        this.oldGame = oldGame;
    }

    public Bundle getInfoAboutGame() {
        return infoAboutGame;
    }

    public void setInfoAboutGame(Bundle infoAboutGame) {
        this.infoAboutGame = infoAboutGame;
    }

    public int getLTResult() {
        return LTResult;
    }

    public void setLTResult(int LTResult) {
        this.LTResult = LTResult;
    }

    public int getRTResult() {
        return RTResult;
    }

    public void setRTResult(int RTResult) {
        this.RTResult = RTResult;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_fragment_layout, parent, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setLeftTeamRounds((TextView) view.findViewById(R.id.leftTeamRounds));
        setRightTeamRounds((TextView) view.findViewById(R.id.rightTeamRounds));

        setLeftTeamResult((TextView) view.findViewById(R.id.leftTeamResult));
        setRightTeamResult((TextView) view.findViewById(R.id.rightTeamResult));

        setLeftTeamName((EditText) view.findViewById(R.id.leftTeamName));
        setRightTeamName((EditText) view.findViewById(R.id.rightTeamName));

        setLTN(getLeftTeamName().getText().toString());
        setRTN(getRightTeamName().getText().toString());

        setGameLinearLayout((LinearLayout) view.findViewById(R.id.gameLinearLayout));

        setLTR(0);
        setRTR(0);

        setOldGame(false);

        setInfoAboutGame(this.getArguments());

        if(getInfoAboutGame() != null) {
            GameInitializer.configureOldGame(getInfoAboutGame(), this);
        }else{
            addNewEntry();
        }

        FilterInput.setNameFilters(this);

        getLeftTeamName().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String newText = s.toString();
                if(s.toString().length() > 3){
                    newText = s.toString().substring(0,3);
                }
                setLTN(newText);
            }
        });
        getRightTeamName().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String newText = s.toString();
                if(s.toString().length() > 3){
                    newText = s.toString().substring(0,3);
                }
                setRTN(newText);
            }
        });

    }

    @Override
    public void updateResult(boolean used, ArrayList<InputField> teamArray, int teamNumber) {
        UpdateResult.updateResult(this, used, teamArray, teamNumber);
    }

    public void addNewEntry(){
        // Begin the transaction
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.add(R.id.gameLinearLayout, new EntryFragment());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }

    public void addNewFilledEntry(int leftTeamResult, int rightTeamResult) {
        EntryFragment entry = new EntryFragment();
        // Begin the transaction
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("leftTeamResult", leftTeamResult);
        bundle.putInt("rightTeamResult", rightTeamResult);
        entry.setArguments(bundle);
        // Replace the contents of the container with the new fragment
        ft.add(R.id.gameLinearLayout, entry);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }
}
