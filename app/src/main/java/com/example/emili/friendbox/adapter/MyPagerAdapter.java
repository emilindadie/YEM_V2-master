package com.example.emili.friendbox.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.emili.friendbox.ui.homeActivity.CallMessageFragment;
import com.example.emili.friendbox.ui.homeActivity.GestionFragment;
import com.example.emili.friendbox.ui.homeActivity.NotificationFragment;
import com.example.emili.friendbox.ui.homeActivity.ThemesFragment;

/**
 * Created by emili on 01/12/2017.
 */

public class MyPagerAdapter extends FragmentPagerAdapter{


    int numItem = 4;
    Context context;

    private String tabTitles[] = new String[] { "Theme", "message", "Events", "Settings"};

    public MyPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return ThemesFragment.newInstance(1, "theme", context);

            case 1:
                return CallMessageFragment.newInstance(2, "callMessage");

            case 2:
                return NotificationFragment.newInstance(3, "events");

            case 3:
                return GestionFragment.newInstance(4, "settings", context);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numItem;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
