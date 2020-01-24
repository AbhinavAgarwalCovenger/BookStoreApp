package com.example.bookstoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookstoreapp.adapater.CartAdapter;
import com.example.bookstoreapp.adapater.SearchAdapter;
import com.example.bookstoreapp.pojo.Books;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity {

    Button checkoutBtn;
    TextView userTxt;
//    GoogleSignInClient mGoogleSignInClient;
//    GoogleApiClient mGoogleApiClient;
    private BottomNavigationView mCartNav;
    SharedPreferences sharedPreferences;
    public static final String myPreference = "mypref";
    Retrofit retrofit = RetrofitController.getRetrofit();
    ApiInterface api = retrofit.create(ApiInterface.class);
    private List<Books> cartList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        checkoutBtn = (Button) findViewById(R.id.checkout_btn);
        userTxt = (TextView) findViewById(R.id.user_txt_cart);


        //bottom nav
        mCartNav = (BottomNavigationView) findViewById(R.id.bottom_nav_view_cart);

        mCartNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bottom_nav_back: finish();
                        return true;
                    case R.id.bottom_nav_cart:
                        return true;
                    case R.id.bottom_nav_home: sendToMain();
                    default:return false;
                }
            }
        });


        sharedPreferences=getSharedPreferences(myPreference, Context.MODE_PRIVATE);
        String account = sharedPreferences.getString("user_id",null);
        if(account==null){
            account = sharedPreferences.getString("guest_id",null);
        }

        Call<List<Books>> call = api.getCart(account);
        call.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                cartList =response.body();
                RecyclerView recyclerView = findViewById(R.id.cart_recycler);
                CartAdapter cartAdapter = new CartAdapter(cartList);
                recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
                recyclerView.setAdapter(cartAdapter);
            }

            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

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

    private void sendToMain() {
        Intent main_intent  = new Intent(CartActivity.this,MainActivity.class);
        startActivity(main_intent);
    }

    private void sentToCheckOut() {

        Intent checkout_intent = new Intent(CartActivity.this, CheckoutActivity.class);
        startActivity(checkout_intent);

    }

    protected void onStart() {
        super.onStart();
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        sharedPreferences=getSharedPreferences(myPreference, Context.MODE_PRIVATE);
        String account = sharedPreferences.getString("user_id",null);
        updateUI(account);
    }

    private void updateUI(String account) {
        if(account!= null){
//            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

            userTxt.setText("cart: "+account);
        }else{
            userTxt.setText("cart: Guest");
        }
    }

}