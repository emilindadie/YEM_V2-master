package com.example.emili.friendbox.ui.mainActivity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import javax.inject.Inject;

/**
 * Created by emili on 03/12/2017.
 */


public class SignInFragment extends Fragment implements SignInView , View.OnClickListener {


    OnFragmentInteractionListener mListener;

    EditText email, password;
    Button signUpActivityButton, signInButton;
    @Inject SignInPresenter signInPresenter;

    // TODO: Rename and change types of parameters
    private int page;
    private String title;

    private static final String PAGE = "page";
    private static final String TITLE = "title";

    private ActivityComponent activityComponent;


    // TODO: Rename and change types of parameters
    private static String userEmail;
    private static String userPassword;

    public static SignInFragment newInstance(int page, String title) {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

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
        return inflater.inflate(R.layout.sign_in_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signInPresenter.setView(this);

        email = (EditText) view.findViewById(R.id.emailEdit);
        password = (EditText) view.findViewById(R.id.passwordEdit);
        signInButton = (Button) view.findViewById(R.id.signInButton);
        signUpActivityButton = (Button) view.findViewById(R.id.signUpActivityButton);

        signInButton.setOnClickListener(this);
        signUpActivityButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.signInButton:

                if(formIsNotEmpy()){

                   signInPresenter.signInUser(email.getText().toString(), password.getText().toString());
                }
                else {
                    Toast.makeText(getActivity(), "Tous les champs doivent etre remplis", Toast.LENGTH_LONG).show();
                }
            break;

            case R.id.signUpActivityButton:

                getActivity().getFragmentManager().beginTransaction().replace(R.id.flcontainer, new GeneralSignUpReservalFragment()).commit();

                //mListener.onFragmentInteraction("general_inscription", null, null);
            break;
        }
    }

    public boolean formIsNotEmpy(){
        return !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public void goToHomeActivity(boolean isDisabled) {

        if(isDisabled){
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        }
        else{
            Toast.makeText(getActivity(), " Tierce personne",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showErrorSignIn(String errorMessage) {
    Toast.makeText(getActivity(), "Impossible de se connecter : "+errorMessage, Toast.LENGTH_LONG).show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String activityName, String email, String password);
    }
}

