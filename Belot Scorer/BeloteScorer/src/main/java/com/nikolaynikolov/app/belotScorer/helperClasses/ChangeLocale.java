package com.nikolaynikolov.app.belotScorer.helperClasses;

import android.app.Activity;
import android.content.res.Configuration;

import com.nikolaynikolov.app.belotScorer.main.mainActivity;

import java.util.Locale;

public class ChangeLocale {
    public static void changeLocale(Activity activity, String language, String country) {
        Locale newLocale = new Locale(language, country);

        Locale.setDefault(newLocale);
        Configuration config = new Configuration();
        config.setLocale(newLocale);
        activity.getBaseContext().getResources().updateConfiguration(config,activity.getResources().getDisplayMetrics());

        ActivityChanger.startActivity(activity, mainActivity.class);

        ToastMaker.languageChanged(activity);
    }
}
