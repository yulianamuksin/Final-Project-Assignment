package com.uts.yulianamuksin2201800091;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class TopupData extends AppCompatActivity {
    private static final String PREF1 = "com.uts.yulianamuksin2201800091";
    private static final String PREF_TOTAL_KEY = "pref_total_key";

    public static void saveTopupBal(Context context, int tb) {
        SharedPreferences pref = context.getSharedPreferences(PREF1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_TOTAL_KEY, tb);
        editor.apply();
    }

    public static int getTopupBal(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF1, Context.MODE_PRIVATE);
        return pref.getInt(PREF_TOTAL_KEY, 0);
    }
}
