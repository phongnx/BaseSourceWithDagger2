package com.base.baseproject.data;


import com.base.baseproject.data.local.preference.PreferencesHelper;
import com.base.baseproject.data.local.realm.RealmHelper;
import com.base.baseproject.data.models.User;
import com.base.baseproject.data.remote.RemoteApiService;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Phong on 11/9/2016.
 */

@Singleton
public class DataManager {
    private RemoteApiService remoteApiService;
    private PreferencesHelper preferencesHelper;
    private RealmHelper realmHelper;

    @Inject
    public DataManager(RemoteApiService remoteApiService, PreferencesHelper preferencesHelper, RealmHelper realmHelper) {
        this.remoteApiService = remoteApiService;
        this.preferencesHelper = preferencesHelper;
        this.realmHelper = realmHelper;
    }

    public Observable<User> login(String email, String password, String android_push_key) {
        return remoteApiService.login(email, password, android_push_key);
    }

    public Observable<User> register(String full_name, String email, String password, String android_push_key, File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        return remoteApiService.register(full_name, email, password, android_push_key, multipartBody);
    }
}
