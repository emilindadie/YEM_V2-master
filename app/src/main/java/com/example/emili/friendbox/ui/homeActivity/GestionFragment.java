package com.example.emili.friendbox.ui.homeActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.app.YEM;
import com.example.emili.friendbox.di.component.ActivityComponent;
import com.example.emili.friendbox.di.component.DaggerActivityComponent;
import com.example.emili.friendbox.di.module.ActivityModule;
import com.example.emili.friendbox.ui.editActivity.EditActivity;

/**
 * Created by emili on 03/12/2017.
 */

public class GestionFragment extends android.support.v4.app.Fragment implements View.OnClickListener{

    private static final String PAGE = "page";
    private static final String TITLE = "title";
    TextView linkTextView;
    static Context context;
    
    public static GestionFragment newInstance(int page, String title, Context context) {
        GestionFragment fragment = new GestionFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        GestionFragment.context = context;
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

    public GestionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivityComponent().inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.gestion_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linkTextView = (TextView) view.findViewById(R.id.linkTextView);
        linkTextView.setOnClickListener(this);
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
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.linkTextView:
                Toast.makeText(context, "cliquer ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("action_edit", "link");
                context.startActivity(intent);
                break;
        }

    }
}
