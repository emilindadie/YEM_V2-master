package com.example.emili.friendbox.di.module;

import android.app.Application;
import android.content.Context;

import com.example.emili.friendbox.data.firebase.FirebaseHelper;
import com.example.emili.friendbox.data.pref.PreferencesHelper;
import com.example.emili.friendbox.data.pref.PreferencesHelperImpl;
import com.example.emili.friendbox.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emili on 19/11/2017.
 */

@Module
public class ApplicationModule {

    private final Application application;
    public ApplicationModule(Application application){
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
    return application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }

    @Provides
    PreferencesHelper providePreferencesHelper(PreferencesHelperImpl preferencesHelper){
        return preferencesHelper;
    }


    @Provides
    FirebaseHelper provideFirebaseHelper(){
        return new FirebaseHelper();
    }
}
