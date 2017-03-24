package com.base.baseproject.injection.component;


import com.base.baseproject.injection.module.ActivityModule;
import com.base.baseproject.ui.main.MainActivity;
import com.base.baseproject.injection.ActivityScope;

import dagger.Component;

/**
 * Created by Phong on 11/9/2016.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    // Activities

    void inject(MainActivity mainActivity);


    // Views

}
