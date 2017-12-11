package com.example.emili.friendbox.ui.homeActivity;

import com.example.emili.friendbox.utils.PictogrammeUtils;

/**
 * Created by emili on 02/12/2017.
 */

public class ThemesPresenterImpl implements ThemesPresenter {

    private static ThemesView themesView;

    public ThemesPresenterImpl(){
    }

    @Override
    public void getItems() {
        themesView.showThemesFamilly(PictogrammeUtils.allItems);
    }

    @Override
    public void setView(ThemesView themesView) {
        ThemesPresenterImpl.themesView = themesView;
    }
}
