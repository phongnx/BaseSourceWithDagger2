package com.base.baseproject;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.base.baseproject.injection.component.DaggerApplicationComponent;
import com.base.baseproject.injection.module.ApplicationModule;
import com.base.baseproject.injection.component.ApplicationComponent;
import com.base.baseproject.utils.Utils;
import com.utility.DebugLog;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Phong on 11/9/2016.
 */

public class BaseApplication extends Application {
    private static ApplicationComponent mApplicationComponent;
    private static BaseApplication mInstants;

    public static BaseApplication get(Context context){
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstants = this;
        DebugLog.DEBUG = Utils.isDebuggable();
        initAppComponents();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public synchronized ApplicationComponent getComponent(){
        if (mApplicationComponent == null) {
            initAppComponents();
        }
        return mApplicationComponent;
    }

    public void initAppComponents() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
