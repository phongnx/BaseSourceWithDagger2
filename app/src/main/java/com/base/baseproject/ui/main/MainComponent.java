package com.base.baseproject.ui.main;

import com.base.baseproject.injection.ActivityScope;
import com.base.baseproject.injection.component.ApplicationComponent;
import com.base.baseproject.injection.module.ActivityModule;

import dagger.Component;

/**
 * Created by Phong on 2/27/2017.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
