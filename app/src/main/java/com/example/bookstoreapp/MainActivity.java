package com.example.bookstoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

 private    Button loginBtn;
  private   Button searchBtn;
  private   Button cartBtn;
  private   Button signOut;
  private   TextView userTxt;

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    GoogleSignInClient mGoogleSignInClient;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        loginBtn = (Button) findViewById(R.id.login_btn);
        searchBtn = (Button) findViewById(R.id.search_btn);
        cartBtn = (Button) findViewById(R.id.cart_btn);
        signOut = (Button) findViewById(R.id.sign_out_btn);
        userTxt = (TextView) findViewById(R.id.user_txt);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
               R.string.openNavDrawer,
                R.string.closeNavDrawer

        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               sendToLogin();
               // Toast.makeText(MainActivity.this, "Login Activity will open", Toast.LENGTH_SHORT).show();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });


        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToCart();
                Toast.makeText(MainActivity.this, "Cart Activity will open", Toast.LENGTH_SHORT).show();
            }
        });


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_out_btn:
                        signOut();
                        break;
                }
            }
        });







    }

    private void sendToCart() {
        Intent cart_intent = new Intent(MainActivity.this,CartActivity.class);
        startActivity(cart_intent);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        sendToLogin();
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {



        if(account != null){
            //Information from google
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
          /*  if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
            }*/
            userTxt.setText("Welcome "+ acct.getDisplayName());
            loginBtn.setEnabled(false);
            Toast.makeText(this, ""+account, Toast.LENGTH_SHORT).show();

        }else{
           // sendToLogin();
            userTxt.setText("Welcome Guest");
            Toast.makeText(this, ""+account, Toast.LENGTH_SHORT).show();
        }
    }

    private void sendToLogin() {
        Intent login_intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(login_intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_my_cart:
                sendToCart();
                Toast.makeText(this, "cart clicked", Toast.LENGTH_SHORT).show();
                        break;
            case R.id.nav_logout:signOut();
            break;

            default: return true;

        }





        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
