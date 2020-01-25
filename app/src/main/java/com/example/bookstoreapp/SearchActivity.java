package com.example.bookstoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.bookstoreapp.adapater.SearchAdapter;
import com.example.bookstoreapp.pojo.Books;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.clickProduct {

    private List<Books> booksList;
    private Books books;
    private BottomNavigationView mSearchNav;
    Retrofit retrofit= RetrofitController.getRetrofit();
    ApiInterface api = retrofit.create(ApiInterface.class);
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button button = findViewById(R.id.search_button);
        final EditText search = findViewById(R.id.search_bar);

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

        Intent intent = getIntent();
        String genre = intent.getStringExtra("genre");
        Call<List<Books>> booksByGenre = api.getBooksByGenre(genre);
        booksByGenre.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                booksList=response.body();
                recyclerView = findViewById(R.id.recycle);
                searchAdapter = new SearchAdapter(booksList,SearchActivity.this);
                int no_of_coloumns = 2;
                recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this,no_of_coloumns));
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                Toast.makeText(SearchActivity.this,"Failed",Toast.LENGTH_LONG);
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = search.getText().toString();
                Call<List<Books>> call = api.getSearch(text);
                call.enqueue(new Callback<List<Books>>() {
                    @Override
                    public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                        booksList = response.body();
                        recyclerView = findViewById(R.id.recycle);
                        searchAdapter = new SearchAdapter(booksList,SearchActivity.this);
                        int no_of_coloumns = 2;
                        recyclerView.setLayoutManager(new GridLayoutManager(SearchActivity.this,no_of_coloumns));
                        recyclerView.setAdapter(searchAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Books>> call, Throwable t) {
                        Toast.makeText(SearchActivity.this,"Failed",Toast.LENGTH_LONG);
                    }
                });
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

    @Override
    public void onClick(Books book) {
        String book_id = book.getProductId();
        Intent intent = new Intent(SearchActivity.this, ProductActivity.class);

        intent.putExtra("id",book_id);

        startActivity(intent);
    }

}
