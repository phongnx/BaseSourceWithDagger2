package com.base.baseproject.injection.component;

import android.app.Application;
import android.content.Context;

import com.base.baseproject.data.DataManager;
import com.base.baseproject.data.remote.RemoteApiService;
import com.base.baseproject.injection.module.ApplicationModule;
import com.base.baseproject.data.local.preference.PreferencesHelper;
import com.base.baseproject.data.local.realm.RealmHelper;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import retrofit2.Retrofit;

/**
 * Created by Phong on 11/9/2016.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context context();

    Application application();

    Retrofit retrofit();

    RemoteApiService remoteApiService();

    DataManager dataManager();

    Realm realm();

    RealmHelper realmHelper();

    PreferencesHelper preferencesHelper();

}
