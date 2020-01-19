package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends AppCompatActivity {

    private List<Books> booksList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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
}
