package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    EditText emailEditText;
    EditText passwordEditText;
    Button loginBtn;
    Button newUser;
    TextView guestTxt;
    SignInButton google_btn;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;

    Customer customer = new Customer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing all the views
        newUser = (Button) findViewById(R.id.register_btn);
        loginBtn = (Button) findViewById(R.id.login_btn_1);
        emailEditText = (EditText) findViewById(R.id.email_edit_txt);
        passwordEditText = (EditText) findViewById(R.id.password_edit_txt);
        google_btn = (SignInButton) findViewById(R.id.google_btn);
        guestTxt = (TextView) findViewById(R.id.guest_txt);


        //Go as guest
        guestTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToMain();
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "reg btn clkd!", Toast.LENGTH_SHORT).show();
                sendToRegister();

            }
        });


        //-------------------------------********** GOOGLE **********-------------------------------

        //GOOGLE SIGN-IN
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //GOOGLE LOGIN BUTTON IMPLEMENTATION
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                customer.setEmail(email);
                customer.setPassword(password);

                Retrofit retrofit = RetrofitController.getRetrofit();
                ApiInterface api = retrofit.create(ApiInterface.class);
                Call<Customer> call = api.getCustId(customer);
                call.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        customer=response.body();
                        Toast.makeText(LoginActivity.this,customer.getCustomerId(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {

                    }
                });
//                Toast.makeText(LoginActivity.this, "Email: "+email+"password: "+password, Toast.LENGTH_SHORT).show();

            }
        });


        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.google_btn:
                        signIn();
                        break;

                }
            }
        });


        //Information from google
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
        }





    }//END-OnCreate




    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

//for google
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Toast.makeText(this, "Sucessfull", Toast.LENGTH_SHORT).show();
            sendToMain();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("error", "signInResult:failed code=" + e.getStatusCode()+" "+e.getMessage());
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
    }

    //for facebook



   /* @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {
        if(account != null){
            sendToMain();
        }else {
            Toast.makeText(this, "please sign in.", Toast.LENGTH_SHORT).show();
        }
    }*/

    private void sendToMain() {
        Intent main_intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(main_intent);
    }


    private void sendToRegister() {
        Intent reg_intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(reg_intent);
    }



}
