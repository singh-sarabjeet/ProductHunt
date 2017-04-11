package com.example.sarabjeet.producthunt.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarabjeet.producthunt.*;
import com.example.sarabjeet.producthunt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.identity.*;

import io.fabric.sdk.android.Fabric;


/**
 * Created by sarabjeet on 9/4/17.
 */

public class MainFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TwitterLoginButton mLoginButton;
    private TextView mStatusTextView;
    private TextView mDetailTextView;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(com.example.sarabjeet.producthunt.R.string.twitter_consumer_key), getString(R.string.twitter_consumer_secret));
        Fabric.with(getActivity(), new Twitter(authConfig));
        mAuthListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if(user !=null){
                    // User is signed in
                }else{
                    //User is signed out
                }
           }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        mLoginButton = (TwitterLoginButton) rootView.findViewById(R.id.twitter_login_button);
        mLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                handleTwitterSession(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                updateUI(null);
            }
        });

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    private void handleTwitterSession(TwitterSession session) {

      // showProgressDialog();

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {

                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_twitter]

    private void signOut() {
        mAuth.signOut();
        Twitter.logOut();

        updateUI(null);
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            mStatusTextView.setText(getString(R.string.twitter_status_fmt, user.getDisplayName()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.button_twitter_login).setVisibility(View.GONE);
            findViewById(R.id.button_twitter_signout).setVisibility(View.VISIBLE);
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.button_twitter_login).setVisibility(View.VISIBLE);
            findViewById(R.id.button_twitter_signout).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_twitter_signout) {
            signOut();
        }
    }
}

}
