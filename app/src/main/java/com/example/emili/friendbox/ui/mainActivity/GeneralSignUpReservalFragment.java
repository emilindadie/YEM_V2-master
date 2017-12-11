package com.example.emili.friendbox.ui.mainActivity;

import android.app.Fragment;
import android.content.Context;
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

/**
 * Created by emili on 02/12/2017.
 */


public class GeneralSignUpReservalFragment extends Fragment implements View.OnClickListener {

    OnFragmentInteractionListener mListener;
    EditText email, password,validePassword;
    Button nextFormButton, signInActivityButton;
    private static final String PAGE = "page";
    private static final String TITLE = "title";

    // TODO: Rename and change types of parameters
    private int page;
    private String title;

    public static GeneralSignUpReservalFragment newInstance(int page, String title) {
        GeneralSignUpReservalFragment fragment = new GeneralSignUpReservalFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public GeneralSignUpReservalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            page = getArguments().getInt(PAGE);
            title = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.general_sign_up_reversal_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        validePassword = (EditText) view.findViewById(R.id.validePassword);
        nextFormButton = (Button) view.findViewById(R.id.nextFormButton);
        signInActivityButton = (Button) view.findViewById(R.id.signInActivityButton);

        nextFormButton.setOnClickListener(this);
        signInActivityButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.nextFormButton:

                if(formIsNotEmpy() && passwordIsEquals()){

                    PersonalSignUpInformationFragment personalSignUpInformationFragment = new PersonalSignUpInformationFragment();
                    personalSignUpInformationFragment.setUserGeneralReserval(email.getText().toString(), password.getText().toString());

                    getActivity().getFragmentManager().beginTransaction().replace(R.id.flcontainer, personalSignUpInformationFragment).commit();

                   //mListener.onFragmentInteraction("personal_inscription", email.getText().toString(), password.getText().toString());
                }
                else if(!formIsNotEmpy()){
                    Toast.makeText(getActivity(), "Tous les champs doivent etre remplis", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(), "Les deux mots de passe ne correspondent pas", Toast.LENGTH_LONG).show();
                }
            break;

            case R.id.signInActivityButton:
               //mListener.onFragmentInteraction("connexion", null, null);
                getActivity().getFragmentManager().beginTransaction().replace(R.id.flcontainer, new SignInFragment()).commit();
                break;
        }
    }

    public boolean formIsNotEmpy(){
        return !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !validePassword.getText().toString().isEmpty();
    }

    public boolean passwordIsEquals(){
        return password.getText().toString().equals(validePassword.getText().toString());
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



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String activityName, String email, String password);
    }
}
