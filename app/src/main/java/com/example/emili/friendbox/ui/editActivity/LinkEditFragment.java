package com.example.emili.friendbox.ui.editActivity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.app.YEM;
import com.example.emili.friendbox.di.component.ActivityComponent;
import com.example.emili.friendbox.di.component.DaggerActivityComponent;
import com.example.emili.friendbox.di.module.ActivityModule;
import com.example.emili.friendbox.ui.homeActivity.HomeActivity;
import com.example.emili.friendbox.ui.mainActivity.GeneralSignUpReservalFragment;
import com.example.emili.friendbox.ui.mainActivity.SignInPresenter;
import com.example.emili.friendbox.ui.mainActivity.SignInView;

import javax.inject.Inject;

/**
 * Created by asus on 07/12/2017.
 */


public class LinkEditFragment extends Fragment implements LinkEditView, View.OnClickListener {


    LinkEditFragment.OnFragmentInteractionListener mListener;
    EditText emailLinkEdit;
    boolean isHasLinked = false;
    String emailLink;

    @Inject
    LinkEditPresenter linkEditPresenter;

    // TODO: Rename and change types of parameters
    private int page;
    private String title;

    private static final String PAGE = "page";
    private static final String TITLE = "title";

    // TODO: Rename and change types of parameters
    private static String userEmail;
    private static String userPassword;

    public static LinkEditFragment newInstance(int page, String title) {
       LinkEditFragment fragment = new LinkEditFragment();
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
        return inflater.inflate(R.layout.link_edit, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        linkEditPresenter.setView(this);
        emailLinkEdit = (EditText) view.findViewById(R.id.editLink);
        FloatingActionButton saveFloatingButton = (FloatingActionButton) view.findViewById(R.id.saveFloatingButton);
        saveFloatingButton.setOnClickListener(this);
    }

    public void getParsedata(boolean isHasLinked, String emailLink){
        this.isHasLinked = isHasLinked;
        this.emailLink = emailLink;
    }

    public boolean formIsNotEmpy(){
        return !emailLinkEdit.getText().toString().isEmpty();
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
    public void showOnSuccesAskSending() {

    }

    @Override
    public void showOnErrorEmailNotFound() {

    }

    @Override
    public void showAskLinkEmailResult(boolean emailLinkExist, boolean recipientHasLink, boolean isProfilEquals, boolean heSendMeNotification, boolean isAlreadyNotified, boolean isNotified) {

        if(!emailLinkExist){
            Toast.makeText(getActivity(), "Impossible d'envoyer la demande, l'email n'exise pas", Toast.LENGTH_LONG).show();
        }
        else if(recipientHasLink){
            Toast.makeText(getActivity(), "Impossible d'envoyer la demande, l'email renseigné a déja un lien", Toast.LENGTH_LONG).show();
        }
        else if(isProfilEquals){
            Toast.makeText(getActivity(), "Impossible d'envoyer la demande, il a le même status que vous", Toast.LENGTH_LONG).show();
        }
        else if(heSendMeNotification){
            Toast.makeText(getActivity(), "Impossible d'envoyer la demande, il vous a deja envoyé une demande", Toast.LENGTH_LONG).show();
        }
        else if(isAlreadyNotified){
            Toast.makeText(getActivity(), "Impossible d'envoyer la demande, il a deja  une demande en cours", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getActivity(), "Votre demande a été envoyé avec success", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.saveFloatingButton:
                if(formIsNotEmpy()){
                    if(!isHasLinked){
                        linkEditPresenter.sendAskLinkEmailRequest(emailLinkEdit.getText().toString());
                    }
                }
        break;
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String activityName, String email, String password);
    }
}


