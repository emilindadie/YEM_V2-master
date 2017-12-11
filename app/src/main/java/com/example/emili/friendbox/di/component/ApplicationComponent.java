package com.example.emili.friendbox.di.component;

import android.app.Application;
import android.content.Context;

import com.example.emili.friendbox.app.YEM;
import com.example.emili.friendbox.di.ApplicationContext;
import com.example.emili.friendbox.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by emili on 19/11/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(YEM YEM);
    @ApplicationContext
    Context getContext();
    Application getApplication();
}