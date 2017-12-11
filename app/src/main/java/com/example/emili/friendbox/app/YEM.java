package com.example.emili.friendbox.app;

import android.app.Application;
import android.content.Context;

import com.example.emili.friendbox.di.component.ApplicationComponent;
import com.example.emili.friendbox.di.component.DaggerApplicationComponent;
import com.example.emili.friendbox.di.module.ApplicationModule;

/**
 * Created by emili on 19/11/2017.
 */

public class YEM extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public static YEM getApplication(Context context) {
        return (YEM) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent(){
        return this.applicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}
