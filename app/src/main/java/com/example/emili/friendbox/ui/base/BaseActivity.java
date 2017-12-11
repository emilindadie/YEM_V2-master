package com.example.emili.friendbox.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.emili.friendbox.app.YEM;
import com.example.emili.friendbox.di.component.ActivityComponent;
import com.example.emili.friendbox.di.component.DaggerActivityComponent;
import com.example.emili.friendbox.di.module.ActivityModule;

/**
 * Created by emili on 19/11/2017.
 */

public class BaseActivity extends AppCompatActivity  {

    private ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent(){
        return activityComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(((YEM) getApplication()).getApplicationComponent())
                    .build();
        }
    }
}
