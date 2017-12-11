package com.example.emili.friendbox.ui.homeActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.adapter.NotificationAdapter;
import com.example.emili.friendbox.app.YEM;
import com.example.emili.friendbox.data.model.Notification;
import com.example.emili.friendbox.di.component.ActivityComponent;
import com.example.emili.friendbox.di.component.DaggerActivityComponent;
import com.example.emili.friendbox.di.module.ActivityModule;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by emili on 09/12/2017.
 */

public class NotificationFragment extends android.support.v4.app.Fragment implements NotificationView , View.OnClickListener{

    private static final String PAGE = "page";
    private static final String TITLE = "title";
    EditText editText;
    NotificationAdapter notificationAdapter;

    @Inject
    NotificationPresenter notificationPresenter;

    public static NotificationFragment newInstance(int page, String title) {
        NotificationFragment fragment = new NotificationFragment();
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

    public NotificationFragment() {

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
        return inflater.inflate(R.layout.notification_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notificationPresenter.setView(this);
        notificationPresenter.loadNotification();

        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getActivity());
        View sheetView = view.findViewById(R.id.fragment_history_menu_bottom);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);

        notificationAdapter = new NotificationAdapter(getActivity(), new ArrayList<Notification>(), new NotificationAdapter.ItemsClick() {
            @Override
            public void onClick(Notification notification, int position) {

                setupNotifiction(notification);
                mBottomSheetDialog.show();
            }
        });

//        callMessagePresenter.checkLinked();
        // callMessagePresenter.attachDataBaseReadListerner();


        LinearLayout acceptLink = (LinearLayout) sheetView.findViewById(R.id.fragment_history_bottom_sheet_edit);
        LinearLayout declineLink = (LinearLayout) sheetView.findViewById(R.id.fragment_history_bottom_sheet_delete);

        acceptLink.setOnClickListener(this);
        declineLink.setOnClickListener(this);
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
    public void showNotification(Notification notification) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            

        }
    }

    private void setupNotifiction(Notification notification){
        notificationAdapter.addNotification(notification);
    }
}
