package com.nikolaynikolov.app.belotScorer.helperClasses;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

import com.nikolaynikolov.app.belotScorer.game.Game;

public class FilterInput {
    public static void setNameFilters(final Game game){

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) { // Accept only letter & digits ; otherwise just return
                        Toast.makeText(game.getActivity(),"Invalid Input",Toast.LENGTH_SHORT).show();
                        return source.toString().substring(start, i) + source.toString().substring(i + 1, end);
                    }
                }
                return null;
            }

        };

        game.getLeftTeamName().setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(3) });
        game.getRightTeamName().setFilters(new InputFilter[] { filter, new InputFilter.LengthFilter(3)});
    }
}
