package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Objects;

public class CheckoutActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //ActionBar
       // Objects.requireNonNull(getSupportActionBar()).setTitle("Checkout");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //maintaining signin state in checkout

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {

        if(account != null){
            Toast.makeText(this, ""+account, Toast.LENGTH_SHORT).show();

        }else{
            sendToLogin();
            Toast.makeText(this, "Please Login First"+account, Toast.LENGTH_SHORT).show();
        }
    }

    private void sendToLogin() {

        Intent login_intent = new Intent(CheckoutActivity.this,LoginActivity.class);
        startActivity(login_intent);

    }





}