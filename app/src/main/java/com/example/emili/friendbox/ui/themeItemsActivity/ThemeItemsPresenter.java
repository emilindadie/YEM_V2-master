package com.example.emili.friendbox.ui.themeItemsActivity;

import android.content.Context;

/**
 * Created by emili on 02/12/2017.
 */

public interface ThemeItemsPresenter {
    void getItems(String famillyName);
    void setView(ThemeItemsView themeItemsView);
}
