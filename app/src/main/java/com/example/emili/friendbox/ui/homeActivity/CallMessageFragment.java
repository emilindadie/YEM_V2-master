package com.example.emili.friendbox.ui.homeActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.app.YEM;
import com.example.emili.friendbox.data.model.Message;
import com.example.emili.friendbox.di.component.ActivityComponent;
import com.example.emili.friendbox.di.component.DaggerActivityComponent;
import com.example.emili.friendbox.di.module.ActivityModule;

/**
 * Created by emili on 04/12/2017.
 */

public class CallMessageFragment extends android.support.v4.app.Fragment implements CallMessageView{

    private static final String PAGE = "page";
    private static final String TITLE = "title";
    EditText editText;

    CallMessagePresenter callMessagePresenter;

    public static CallMessageFragment newInstance(int page, String title) {
        CallMessageFragment fragment = new CallMessageFragment();
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

    public CallMessageFragment() {

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
        return inflater.inflate(R.layout.call_message_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        callMessagePresenter.checkLinked();
       // callMessagePresenter.attachDataBaseReadListerner();

    }

    @Override
    public void onStart() {
        super.onStart();
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
    public void onStop() {
        super.onStop();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.call_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:
                // Comportement du bouton "A Propos"
                return false;

            case R.id.action_call:
                // Comportement du bouton "A Propos"
                return true;

            default:
                break;
        }
        return false;
    }

    @Override
    public void showMessageList(Message message) {

    }

    @Override
    public void showLinked(boolean linked) {

        if(linked){
            callMessagePresenter.attachDataBaseReadListerner();
        }
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showSuccessSending() {

    }

    @Override
    public void showErrorSending() {

    }

    @Override
    public void showSuccessLoading() {

    }

    @Override
    public void showErrorLoading() {

    }
}

