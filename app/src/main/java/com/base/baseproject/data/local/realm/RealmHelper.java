package com.base.baseproject.data.local.realm;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;

/**
 * Created by Phong on 11/9/2016.
 */

@Singleton
public class RealmHelper {
    private static final String TAG = "RealmHelper";
    private final Context context;
    private final Realm realm;

    @Inject
    public RealmHelper(Context context, Realm realm) {
        this.context = context;
        this.realm = realm;
    }

}
