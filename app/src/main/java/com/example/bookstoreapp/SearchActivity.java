package com.example.bookstoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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




        //books = new Books();

        Call<List<Books>> call = api.getBooksByGenre("fiction");
        call.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                booksList = response.body();
                RecyclerView recyclerView = findViewById(R.id.recycle);
                SearchAdapter searchAdapter = new SearchAdapter(booksList,SearchActivity.this);
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

    @Override
    public void onClick(Books book) {
        String book_id = book.getProductId();

        Call<Books> call =api.getProductById(book_id);
        call.enqueue(new Callback<Books>() {
              @Override
              public void onResponse(Call<Books> call, Response<Books> response) {
                  books=response.body();
                  Toast.makeText(SearchActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
              }

              @Override
              public void onFailure(Call<Books> call, Throwable t) {
                  Toast.makeText(SearchActivity.this,"Failed",Toast.LENGTH_LONG);
              }
        });

//        Toast.makeText(getBaseContext(),books.getAuthor(),Toast.LENGTH_LONG).show();
        if(books!=null) {
            String bookName = books.getProductName();
            String img = books.getUrl();
            String author = books.getAuthor();
            String price = books.getPrice();
//        String publisher = books.getAttributes().getPublisher();
            String isbn = books.getIsbn();
            String genre = books.getGenre();
            String rating = books.getRating();
            String description = books.getDescription();
//        String year = books.getAttributes().getYear();
//        String binding = books.getAttributes().getBinding();
//        String pages = books.getAttributes().getNoofpages();


            Intent intent = new Intent(SearchActivity.this, ProductActivity.class);

            intent.putExtra("id", book_id);
            intent.putExtra("name", bookName);
            intent.putExtra("url", img);
            intent.putExtra("author", author);
            intent.putExtra("price", price);
//        intent.putExtra("publisher",publisher);
            intent.putExtra("isbn", isbn);
            intent.putExtra("genre", genre);
            intent.putExtra("rating", rating);
            intent.putExtra("desc", description);
//        intent.putExtra("year",year);
//        intent.putExtra("bind",binding);
//        intent.putExtra("pages",pages);

            startActivity(intent);
        }

        else {
            Toast.makeText(getBaseContext(),"Sorry for inconvenience",Toast.LENGTH_LONG).show();
        }
    }
}
