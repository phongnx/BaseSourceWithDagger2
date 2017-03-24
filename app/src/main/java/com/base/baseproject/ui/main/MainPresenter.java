package com.base.baseproject.ui.main;

import com.base.baseproject.data.DataManager;
import com.base.baseproject.data.models.User;
import com.base.baseproject.ui.base.BasePresenter;
import com.base.baseproject.data.local.preference.PreferencesHelper;
import com.utility.DebugLog;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Phong on 2/2/2017.
 */

public class MainPresenter extends BasePresenter<MainMvpView> {
    private final PreferencesHelper preferencesHelper;
    private final DataManager dataManager;

    @Inject
    public MainPresenter(PreferencesHelper preferencesHelper, DataManager dataManager) {
        this.preferencesHelper = preferencesHelper;
        this.dataManager = dataManager;
    }

    public void initData(){
        dataManager.login("phongnx@gmail.com", "123456", "ABCDEF")
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, User>() {
                    @Override
                    public User apply(ResponseBody s) throws Exception {
                        User user = null;
                        DebugLog.loge(s);
                        return user;
                    }
                })
                .subscribe(new DisposableObserver<User>() {
                    @Override
                    public void onNext(User value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
