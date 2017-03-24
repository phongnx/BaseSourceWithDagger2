package com.base.baseproject.ui.main;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.base.baseproject.R;
import com.base.baseproject.ui.base.BaseActivity;
import com.base.baseproject.utils.CheckPermissions;
import com.base.baseproject.utils.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.base.baseproject.utils.CheckPermissions.RequestCodePermission.REQUEST_CODE_GRANT_STORAGE_PERMISSIONS;

public class MainActivity extends BaseActivity implements MainMvpView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    CheckPermissions checkPermissions;
    @Inject
    MainPresenter mainPresenter;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        // --
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mainPresenter.attachView(this);

        // --
        initView();
        checkPermissions();
    }

    // Check permission in android 6.0 and above
    public void checkPermissions() {
        if (checkPermissions.checkAccessStoragePermission(context)) {
//            mainPresenter.initData();
        }
    }

    public void initView() {
        setSupportActionBar(toolbar);
    }

    public void closeMenu() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @OnClick(R.id.fab)
    public void getData() {
        mainPresenter.initData();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        mainPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int grantResult = 0;
        try {
            grantResult = grantResults[0];
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), String.valueOf(e));
        }
        switch (requestCode) {
            case REQUEST_CODE_GRANT_STORAGE_PERMISSIONS:
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    mainPresenter.initData();
                } else {
                    // Permission Denied
                    Utils.showToast(context, getApplicationContext().getString(R.string.lbl_alert_storage_permission_denied));
                    finish();
                }
                break;

            default:
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
