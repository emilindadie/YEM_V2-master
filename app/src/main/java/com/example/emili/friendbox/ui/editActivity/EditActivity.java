package com.example.emili.friendbox.ui.editActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.emili.friendbox.R;

public class EditActivity extends AppCompatActivity implements LinkViewFragment.OnFragmentInteractionListener, LinkEditFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        if(intent.getStringExtra("action_edit").equals("link")){
            getFragmentManager().beginTransaction().add(R.id.flcontainer, new LinkViewFragment()).commit();
        }
    }

    @Override
    public void onFragmentInteraction(String activityName, String email, String password) {

    }
}
