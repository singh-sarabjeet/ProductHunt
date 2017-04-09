package com.example.sarabjeet.producthunt;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.identity.*;


/**
 * Created by sarabjeet on 9/4/17.
 */

public class MainFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TwitterLoginButton mLoginButton;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
              /*  if(user !=null){
                    // User is signed in
                }else{
                    //User is signed out
                }
       */     }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        mLoginButton = (TwitterLoginButton) rootView.findViewById(R.id.twitter_login_button);
        return rootView;
    }

@Override
    public void onStart(){
    super.onStart();
    mAuth.addAuthStateListener(mAuthListener);
}

@Override
    public void onStop(){
    super.onStop();
    if(mAuthListener!= null){
        mAuth.removeAuthStateListener(mAuthListener);
    }
}

}
