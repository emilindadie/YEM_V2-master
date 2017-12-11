package com.example.emili.friendbox.ui.homeActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.adapter.PictogrammeAdapter;
import com.example.emili.friendbox.app.YEM;
import com.example.emili.friendbox.data.model.Pictogramme;
import com.example.emili.friendbox.di.component.ActivityComponent;
import com.example.emili.friendbox.di.component.DaggerActivityComponent;
import com.example.emili.friendbox.di.module.ActivityModule;
import com.example.emili.friendbox.ui.themeItemsActivity.ThemeItemsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by emili on 01/12/2017.
 */

public class ThemesFragment extends android.support.v4.app.Fragment implements ThemesView {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PictogrammeAdapter pictogrammeAdapter;
    List<Pictogramme> list;
    static Context context;

    @Inject
    ThemesPresenter themesPresenter;

    private static final String PAGE = "page";
    private static final String TITLE = "title";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static ThemesFragment newInstance(int page, String title, Context context) {
        ThemesFragment fragment = new ThemesFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        ThemesFragment.context = context;
        return fragment;
    }

    private ActivityComponent activityComponent;
    public ActivityComponent getActivityComponent(){
        if(activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(getActivity()))
                    .applicationComponent(((YEM) getActivity().getApplication()).getApplicationComponent())
                    .build();
        }
        return activityComponent;
    }

    public ThemesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.themes_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        themesPresenter.setView(this);
        themesPresenter.getItems();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        pictogrammeAdapter =new PictogrammeAdapter(getActivity(), list, new PictogrammeAdapter.ItemsClick() {
            @Override
            public void onClick(Pictogramme pictogramme, int position) {

                Intent intent = new Intent(context, ThemeItemsActivity.class);
                intent.putExtra("pictogrammeFamilly", pictogramme.getNom());
                getContext().startActivity(intent);
            }
        });
        recyclerView.setAdapter(pictogrammeAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showThemesFamilly(List<Pictogramme> pictogrammes) {
        setUpList(pictogrammes);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView(searchView);
        super.onCreateOptionsMenu(menu, inflater);

        searchView(searchView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:
                // Comportement du bouton "A Propos"
                return true;

            case R.id.action_call:
                // Comportement du bouton "A Propos"
                return false;

            default:
                break;
        }
        return false;
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


    void setUpList(List<Pictogramme> list){
        this.list = list;
    }
}
