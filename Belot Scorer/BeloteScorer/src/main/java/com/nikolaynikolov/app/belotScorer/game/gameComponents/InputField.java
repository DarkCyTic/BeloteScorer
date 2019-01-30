package com.nikolaynikolov.app.belotScorer.game.gameComponents;

import android.content.Context;
import android.util.AttributeSet;

public class InputField extends android.support.v7.widget.AppCompatEditText {

    private int team;
    private boolean usedInputField = false;

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public boolean isUsedInputField() {
        return usedInputField;
    }

    public void setUsedInputField(boolean usedInputField) {
        this.usedInputField = usedInputField;
    }

    public InputField(Context context) {
        super(context);
    }

    public InputField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InputField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
