package com.example.bookstoreapp;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchController extends Application {
    static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if (retrofit==null){
            OkHttpClient client = new OkHttpClient.Builder().build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.myjson.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
