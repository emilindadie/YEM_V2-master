package com.example.emili.friendbox.ui.themeItemsActivity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.adapter.PictogrammeAdapter;
import com.example.emili.friendbox.data.model.Pictogramme;
import com.example.emili.friendbox.di.component.ActivityComponent;
import com.example.emili.friendbox.di.component.ApplicationComponent;
import com.example.emili.friendbox.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ThemeItemsActivity extends BaseActivity implements ThemeItemsView {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PictogrammeAdapter pictogrammeAdapter;
    List<Pictogramme> list;

    @Inject
    ThemeItemsPresenter themeItemsPresenter;

    public ActivityComponent getActivityComponent(){
        return super.getActivityComponent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home_menu, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_items_activity);

        getActivityComponent().inject(this);

        themeItemsPresenter.setView(this);
        String pictogrameFamilly = getIntent().getStringExtra("pictogrammeFamilly");
        themeItemsPresenter.getItems(pictogrameFamilly);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        pictogrammeAdapter =new PictogrammeAdapter(this, list, new PictogrammeAdapter.ItemsClick() {
            @Override
            public void onClick(Pictogramme pictogramme, int position) {
                Toast.makeText(ThemeItemsActivity.this, "vous avez cliqué sur" +String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setAdapter(pictogrammeAdapter);

    }

    public void searchView(SearchView searchView){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pictogrammeAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }


    @Override
    public void showFamillyItems(List<Pictogramme> pictogrammes) {
        setupList(pictogrammes);
    }


    void setupList(List<Pictogramme> list){
        this.list = list;
    }

}
