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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.app.YEM;
import com.example.emili.friendbox.di.component.ActivityComponent;
import com.example.emili.friendbox.di.component.DaggerActivityComponent;
import com.example.emili.friendbox.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by emili on 02/12/2017.
 */


public class PersonalSignUpInformationFragment extends Fragment implements SignUpView, View.OnClickListener {


    OnFragmentInteractionListener mListener;

    EditText firstName, lastName, adress, postalCode, number;
    Button signInActivityButton, signUpButton;
    RadioGroup profilRadioGroup;
    int profilCheckedRadio = R.id.handicapProfilRadio;

    @Inject
    SignUpPresenter signUpPresenter;

    // TODO: Rename and change types of parameters
    private int page;
    private String title;

    private static final String PAGE = "page";
    private static final String TITLE = "title";

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


    // TODO: Rename and change types of parameters
    private static String userEmail;
    private static String userPassword;

    public static PersonalSignUpInformationFragment newInstance(int page, String title) {
        PersonalSignUpInformationFragment fragment = new PersonalSignUpInformationFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE, page);
        args.putString(TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    public void setUserGeneralReserval(String userEmail, String userPassword){
        PersonalSignUpInformationFragment.userEmail = userEmail;
        PersonalSignUpInformationFragment.userPassword = userPassword;
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
        return inflater.inflate(R.layout.personal_sign_up_information_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signUpPresenter.setView(this);

        firstName = (EditText) view.findViewById(R.id.firstNameEdit);
        lastName = (EditText) view.findViewById(R.id.lastNameEdit);
        adress = (EditText) view.findViewById(R.id.adressEdit);
        postalCode = (EditText) view.findViewById(R.id.postalCodeEdit);
        number = (EditText) view.findViewById(R.id.numberEdit);
        signInActivityButton = (Button) view.findViewById(R.id.signInActivityButton);
        signUpButton = (Button) view.findViewById(R.id.signUpButton);
        profilRadioGroup = (RadioGroup) view.findViewById(R.id.profilRadioGroup);

        profilRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                profilCheckedRadio = checkedId;
                Toast.makeText(getActivity(), "Checked button changed " + checkedId, Toast.LENGTH_LONG).show();
            }
        });

        signUpButton.setOnClickListener(this);
        signInActivityButton.setOnClickListener(this);

        Toast.makeText(getActivity(), "user" +userEmail + " "+userPassword, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.signUpButton:
                if(formIsNotEmpy()){
                    if(profilCheckedRadio == R.id.handicapProfilRadio){
                        signUpPresenter.signUpUser(firstName.getText().toString(), lastName.getText().toString(), userEmail, userPassword, adress.getText().toString(), Integer.valueOf(postalCode.getText().toString()), Long.valueOf(number.getText().toString()), true, false);
                    }
                    else{
                        signUpPresenter.signUpUser(firstName.getText().toString(), lastName.getText().toString(), userEmail, userPassword, adress.getText().toString(), Integer.parseInt(
                                postalCode.getText().toString()), Long.parseLong(number.getText().toString()), false, false);
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Tous les champs doivent etre remplis", Toast.LENGTH_LONG).show();
                }
            break;

            case R.id.signInActivityButton:
               // mListener.onFragmentInteraction("connexion", null, null);

                getActivity().getFragmentManager().beginTransaction().replace(R.id.flcontainer, new SignInFragment()).commit();

                break;
        }
    }

    public boolean formIsNotEmpy(){
        return !firstName.getText().toString().isEmpty() && !lastName.getText().toString().isEmpty() && !adress.getText().toString().isEmpty()
                &&  !postalCode.getText().toString().isEmpty() && !number.getText().toString().isEmpty();
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
    public void showSuccessSignUp() {
        Toast.makeText(getActivity(), "Inscription reussite", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorSignUp(String errorMessage) {
        Toast.makeText(getActivity(), "Inscription échoué : "+errorMessage, Toast.LENGTH_LONG).show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String activityName, String email, String password);
    }
}
