package com.example.bookstoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends AppCompatActivity {

    private List<Books> booksList;
    private BottomNavigationView mSearchNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        //bottom nav
        mSearchNav = (BottomNavigationView) findViewById(R.id.bottom_nav_view_search);

        mSearchNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bottom_nav_back: sendToMain();
                        return true;
                    case R.id.bottom_nav_cart:sendToCart();
                        return true;
                    case R.id.bottom_nav_home: sendToMain();
                    default:return false;
                }
            }
        });






        Retrofit retrofit=SearchController.getRetrofit();
        SearchInterface api = retrofit.create(SearchInterface.class);
        Call<List<Books>> call = api.getBooks();
        call.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                booksList = response.body();
                RecyclerView recyclerView = findViewById(R.id.recycle);
                SearchAdapter searchAdapter = new SearchAdapter(booksList);
                recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                Toast.makeText(SearchActivity.this,"Failed",Toast.LENGTH_LONG);
            }
        });
    }

    private void sendToCart() {

        Intent cart_intent  = new Intent(SearchActivity.this,CartActivity.class);
        startActivity(cart_intent);

    }

    private void sendToMain() {
        Intent main_intent  = new Intent(SearchActivity.this,MainActivity.class);
        startActivity(main_intent);
    }
}
