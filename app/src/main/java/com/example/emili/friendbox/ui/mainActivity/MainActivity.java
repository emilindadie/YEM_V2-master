package com.example.emili.friendbox.ui.mainActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.di.component.ActivityComponent;
import com.example.emili.friendbox.service.GetPokemonService;
import com.example.emili.friendbox.ui.base.BaseActivity;
import com.example.emili.friendbox.ui.homeActivity.HomeActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements GeneralSignUpReservalFragment.OnFragmentInteractionListener,
        SignInFragment.OnFragmentInteractionListener, PersonalSignUpInformationFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.flcontainer, new SignInFragment()).commit();
        }
    }

    @Override
    public void onFragmentInteraction(String activityName, String email, String password) {

        switch (activityName){
            case "general_inscription":
                GeneralSignUpReservalFragment generalSignUpReservalFragment = new GeneralSignUpReservalFragment();
                getFragmentManager().beginTransaction().replace(R.id.flcontainer, generalSignUpReservalFragment).commit();
            break;

            case "personal_inscription":
                PersonalSignUpInformationFragment personalSignUpInformationFragment = new PersonalSignUpInformationFragment();
                getFragmentManager().beginTransaction().replace(R.id.flcontainer, personalSignUpInformationFragment).commit();
                personalSignUpInformationFragment.setUserGeneralReserval(email, password);
            break;

            case "connexion":
                SignInFragment signInFragment = new SignInFragment();
                getFragmentManager().beginTransaction().replace(R.id.flcontainer, signInFragment).commit();
            break;

            case "test":
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
