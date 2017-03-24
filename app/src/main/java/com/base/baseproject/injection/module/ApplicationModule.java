package com.base.baseproject.injection.module;

import android.app.Application;
import android.content.Context;

import com.base.baseproject.data.local.preference.PreferencesHelper;
import com.base.baseproject.data.local.realm.RealmHelper;
import com.base.baseproject.data.remote.RemoteApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit2.Retrofit;

/**
 * Created by Phong on 11/9/2016.
 */

@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    RemoteApiService provideRemoteApiService(Retrofit retrofit) {
        return retrofit.create(RemoteApiService.class);
    }

    @Provides
    @Singleton
    RealmHelper provideRealmHelper(Context context, Realm realm) {
        return new RealmHelper(context, realm);
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(Context context) {
        return new PreferencesHelper(context);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitInstance() {
        return RemoteApiService.Creator.newRetrofitInstance();
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
}
