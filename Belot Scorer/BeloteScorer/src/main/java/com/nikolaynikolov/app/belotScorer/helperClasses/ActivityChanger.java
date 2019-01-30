package com.nikolaynikolov.app.belotScorer.helperClasses;

import android.app.Activity;
import android.content.Intent;

import com.nikolaynikolov.app.belotScorer.main.mainActivity;

public class ActivityChanger {
    public static void startActivity(Activity activity, Class<?> cls){
        Intent intent = new Intent(activity, cls);

        if(activity.getClass().equals(cls)){
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        activity.startActivity(intent);
        activity.finish();
    }

    public static void startMainActivity(Activity activity) {
        startActivity(activity, mainActivity.class);
    }

    public static void refresh(Activity activity){
        Intent intent = new Intent(activity, activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }
}
