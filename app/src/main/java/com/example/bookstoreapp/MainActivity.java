package com.example.bookstoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  private   Button searchBtn;
  private   TextView userTxt;

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private List<Books> booksList;
    private BottomNavigationView mMainNav;

    GoogleSignInClient mGoogleSignInClient;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Bottom navigation view

            mMainNav = (BottomNavigationView) findViewById(R.id.bottom_nav_view);

            mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.bottom_nav_back: finish();
                        return true;
                        case R.id.bottom_nav_cart: sendToCart();
                        return true;
                        case R.id.bottom_nav_home: return true;
                        default:return false;
                    }
                }
            });


        //genre recycler view

//        Retrofit retrofit= RetrofitController.getRetrofit();
//        SearchInterface api = retrofit.create(SearchInterface.class);
//        Call<List<Books>> call = api.getBooks();
//        call.enqueue(new Callback<List<Books>>() {
//            @Override
//            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
//                booksList = response.body();
//                RecyclerView recyclerView = findViewById(R.id.genre_recycler);
//                GenreMainActivityAdapter genreMainActivityAdapter = new GenreMainActivityAdapter(booksList);
//                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,true));
//                recyclerView.setAdapter(genreMainActivityAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Books>> call, Throwable t) {
//                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG);
//            }
//        });





        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        searchBtn = (Button) findViewById(R.id.search_btn);
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




//        searchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(MainActivity.this,SearchActivity.class);
//                startActivity(intent);
//            }
//        });



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
            userTxt.setText("Welcome "+ acct.getDisplayName());
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

    private void sendToProfile(){
        Intent profile_intent = new Intent(MainActivity.this,MyProfileActivity.class);
        startActivity(profile_intent);
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

            case R.id.nav_my_profile:
                sendToProfile();
                Toast.makeText(this, "profile clicked", Toast.LENGTH_SHORT).show();

                break;

            default: return true;

        }





        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
