package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class LoginActivity extends AppCompatActivity {
    EditText emailEditText;
    EditText passwordEditText;
    Button loginBtn;
    Button newUser;
    Button guestTxt;
    SignInButton google_btn;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    SharedPreferences sharedPreferences;
    public static final String myPreference = "mypref";
//    SharedPreferences.Editor editor = sharedPreferences.edit();



    Login login= new Login();
    CustId custId = new CustId();
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
        guestTxt = (Button) findViewById(R.id.guest_txt);
        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);






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

        // LOGIN BUTTON IMPLEMENTATION
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();



                if(email.length()!=0 && password.length()!=0){
                    login.setEmail(email);
                    login.setPassword(password);
                    login.setLoginType("customer");

                    Retrofit retrofit = RetrofitController.getRetrofit();
                    ApiInterface api = retrofit.create(ApiInterface.class);
                    Call<CustId> call = api.getCustId(login);
                    call.enqueue(new Callback<CustId>() {
                        @Override
                        public void onResponse(Call<CustId> call, Response<CustId> response) {
                            custId=response.body();
                            Toast.makeText(LoginActivity.this,custId.getResponse(),Toast.LENGTH_LONG).show();


                            if(custId.getResponse().equals("Not registered")){
                                Toast.makeText(LoginActivity.this, "Please Register First", Toast.LENGTH_SHORT).show();
                            }else if(custId.getResponse().equals("Wrong Password")){
                                Toast.makeText(LoginActivity.this, "wrong Password", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                String id = custId.getResponse().toString();
                                editor.putString("user_id",id);
                                editor.commit();
                                Toast.makeText(LoginActivity.this, "Successful!!", Toast.LENGTH_SHORT).show();
                                //SaveSharedPreference.setLoggedIn(getApplicationContext(), true);
                                sendToMain();
                            }







                        }

                        @Override
                        public void onFailure(Call<CustId> call, Throwable t) {

                        }
                    });
                }else {
                    Toast.makeText(LoginActivity.this, "Please enter credentials!!", Toast.LENGTH_SHORT).show();

                }





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
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("user_id",account.toString());
            editor.commit();

            Toast.makeText(this, "Sucessfull", Toast.LENGTH_SHORT).show();
            sendToMain();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("error", "signInResult:failed code=" + e.getStatusCode()+" "+e.getMessage());
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
        }
    }

    //for facebook code goes here




    private void sendToMain() {
        Intent main_intent = new Intent(LoginActivity.this,MainActivity.class);
        main_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(main_intent);
    }


    private void sendToRegister() {
        Intent reg_intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(reg_intent);
    }



}
