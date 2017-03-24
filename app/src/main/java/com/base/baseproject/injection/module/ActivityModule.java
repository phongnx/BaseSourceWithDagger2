package com.base.baseproject.injection.module;

import android.app.Activity;
import android.content.Context;

import com.base.baseproject.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Phong on 11/9/2016.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    public Activity provideActivity(){
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideApplication(){
        return mActivity;
    }
}
