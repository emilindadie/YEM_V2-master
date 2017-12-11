package com.example.emili.friendbox.ui.themeItemsActivity;

import android.content.Context;
import android.widget.Toast;

import com.example.emili.friendbox.data.model.Pictogramme;
import com.example.emili.friendbox.utils.PictogrammeUtils;

import java.util.List;

/**
 * Created by emili on 02/12/2017.
 */

public class ThemeItemsPresenterImpl implements ThemeItemsPresenter {

    static ThemeItemsView themeItemsView;
    static List<Pictogramme> list;

    public ThemeItemsPresenterImpl(){

    }

    @Override
    public void getItems(String famillyName) {

        switch(famillyName){
            case "Alimentation":
               themeItemsView.showFamillyItems(PictogrammeUtils.getAlimentaion());
            break;
        }
    }

    @Override
    public void setView(ThemeItemsView themeItemsView) {
        ThemeItemsPresenterImpl.themeItemsView = themeItemsView;
    }

}
