package com.example.emili.friendbox.ui.editActivity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.app.YEM;
import com.example.emili.friendbox.di.component.ActivityComponent;
import com.example.emili.friendbox.di.component.DaggerActivityComponent;
import com.example.emili.friendbox.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by emili on 10/12/2017.
 */



public class LinkViewFragment extends Fragment implements LinkViewView, View.OnClickListener {


    LinkEditFragment.OnFragmentInteractionListener mListener;
    boolean isHasLinked = false;
    String emailLink;

    @Inject
    LinkViewPresenter linkViewPresenter;

    // TODO: Rename and change types of parameters
    private int page;
    private String title;

    private static final String PAGE = "page";
    private static final String TITLE = "title";

    // TODO: Rename and change types of parameters
    private static String userEmail;
    private static String userPassword;

    public static LinkViewFragment newInstance(int page, String title) {
        LinkViewFragment fragment = new LinkViewFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        fragment.setArguments(args);
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            page = getArguments().getInt(PAGE);
            title = getArguments().getString(TITLE);
        }
        getActivityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.link_view_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linkViewPresenter.setView(this);
        linkViewPresenter.loadLinkEmail();
        FloatingActionButton editFloatingButton = (FloatingActionButton) view.findViewById(R.id.editFloatingButton);
        editFloatingButton.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LinkEditFragment.OnFragmentInteractionListener) {
            mListener = (LinkEditFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.editFloatingButton:
                LinkEditFragment linkEditFragment = new LinkEditFragment();
                linkEditFragment.getParsedata(isHasLinked, emailLink);
                getFragmentManager().beginTransaction().replace(R.id.flcontainer, linkEditFragment).commit();
            break;
        }
    }

    @Override
    public void showEmailLink(boolean hasLink, String email) {
        this.isHasLinked = hasLink;
        this.emailLink = email;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String activityName, String email, String password);
    }
}