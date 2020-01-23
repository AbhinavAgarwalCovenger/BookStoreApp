package com.example.bookstoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookstoreapp.adapater.GenreMainActivityAdapter;
import com.example.bookstoreapp.adapater.TopPicksAdapter;
import com.example.bookstoreapp.pojo.Books;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TopPicksAdapter.clickProduct {

    private Button searchBtn;
    private TextView userTxt;

    private Toolbar toolbar;
    private ImageView cartBtn;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ArrayList<String> genreList;
    private List<Books> topBooksList;

    SharedPreferences sharedPreferences;
    public static final String myPreference = "mypref";

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //genre recycler view

        Retrofit retrofit= RetrofitController.getRetrofit();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<ArrayList<String>> call = api.getGenre();
        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                genreList = response.body();
                RecyclerView recyclerView = findViewById(R.id.genre_recycler);
                GenreMainActivityAdapter genreMainActivityAdapter = new GenreMainActivityAdapter(genreList);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,true));
                recyclerView.setAdapter(genreMainActivityAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
        });

        Call<List<Books>> topBooks = api.getTopBooks();
        topBooks.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                topBooksList = response.body();
                RecyclerView recyclerView = findViewById(R.id.top_product_recycler);
                TopPicksAdapter topPicksAdapter = new TopPicksAdapter(topBooksList,MainActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,true));
                recyclerView.setAdapter(topPicksAdapter);
            }

            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
        });



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        cartBtn = (ImageView) findViewById(R.id.cart_btn);
        searchBtn = (Button) findViewById(R.id.search_btn);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
       // getActionBar().setTitle("BookStore");
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

cartBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sendToCart();
    }
});


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });


    }

    private void sendToCart() {
        Intent cart_intent = new Intent(MainActivity.this, CartActivity.class);
        startActivity(cart_intent);
    }

    private void signOut() {
        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        sendToLogin();
                    }
                });
        editor.clear();
        editor.commit();
        updateUI(null);

    }


    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);
        String account = sharedPreferences.getString("user_id", null);
        updateUI(account);
    }

    private void updateUI(String account) {


        if (account != null) {

            //change drawer logout/login to logout

            Menu menu = navigationView.getMenu();

            // find MenuItem you want to change
            MenuItem nav_logout = menu.findItem(R.id.nav_logout);

            // set new title to the MenuItem
            nav_logout.setTitle("Logout");

            Toast.makeText(this, "" + account, Toast.LENGTH_SHORT).show();

        } else {
            // sendToLogin();
            Toast.makeText(this, "" + account, Toast.LENGTH_SHORT).show();
        }
    }

    private void sendToLogin() {
        Intent login_intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(login_intent);
    }

    private void sendToProfile() {
        Intent profile_intent = new Intent(MainActivity.this, MyProfileActivity.class);
        startActivity(profile_intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_my_cart:

                sendToCart();
                Toast.makeText(this, "cart clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout: {
                signOut();
                sendToLogin();
            }
            break;

            case R.id.nav_my_profile:
                sendToProfile();
                Toast.makeText(this, "profile clicked", Toast.LENGTH_SHORT).show();

                break;

            default:
                return true;

        }


        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(Books book) {
        String book_id = book.getProductId();
        Intent intent = new Intent(MainActivity.this, ProductActivity.class);

        intent.putExtra("id",book_id);

        startActivity(intent);
    }
}
