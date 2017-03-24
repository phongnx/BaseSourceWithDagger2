package com.base.baseproject.data.local.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Phong on 11/9/2016.
 */

@Singleton
public class PreferencesHelper {
    private Context context;
    private SharedPreferences sharedPreferences;

    @Inject
    public PreferencesHelper(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


}
