package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Objects;

public class CartActivity extends AppCompatActivity {

    Button checkoutBtn;
    TextView userTxt;
    GoogleSignInClient mGoogleSignInClient;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        checkoutBtn = (Button) findViewById(R.id.checkout_btn);
        userTxt = (TextView) findViewById(R.id.user_txt_cart);

        //ActionBar
        //Objects.requireNonNull(getSupportActionBar()).setTitle("Cart");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentToCheckOut();
            }
        });

    }

    private void sentToCheckOut() {

        Intent checkout_intent = new Intent(CartActivity.this, CheckoutActivity.class);
        startActivity(checkout_intent);

    }

    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
        if(account!= null){
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

            userTxt.setText("cart: "+acct.getDisplayName());
        }else{
            userTxt.setText("cart: Guest");
        }
    }

}