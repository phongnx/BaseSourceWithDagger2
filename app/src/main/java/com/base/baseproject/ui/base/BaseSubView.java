package com.base.baseproject.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Phong on 3/24/2017.
 */

public abstract class BaseSubView extends FrameLayout implements SubMvpView {

    private BaseActivity mActivity;

    private MvpView mParentMvpView;

    public BaseSubView(@NonNull Context context) {
        super(context);
    }

    public BaseSubView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseSubView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseSubView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    @Override
    public void showLoading() {
        if (mActivity != null) {
            mActivity.showLoading();
        }
    }

    @Override
    public void showLoading(String message) {
        mActivity.showLoading(message);
    }

    @Override
    public void hideLoading() {
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void showAlertDialog(String message) {
        if (mActivity != null) {
            mActivity.showAlertDialog(message);
        }
    }

    @Override
    public void hideAlertDialog() {
        if (mActivity != null) {
            mActivity.hideAlertDialog();
        }
    }

    @Override
    public void onDestroy() {
        if (mActivity != null) {
            mActivity.hideAlertDialog();
        }
        if (mActivity != null) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void attachParentMvpView(MvpView mvpView) {
        mParentMvpView = mvpView;
    }

    protected abstract void init();
}
