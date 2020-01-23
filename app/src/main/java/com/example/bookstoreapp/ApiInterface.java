package com.example.bookstoreapp;

import com.example.bookstoreapp.pojo.Books;
import com.example.bookstoreapp.pojo.CustId;
import com.example.bookstoreapp.pojo.Login;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

      @GET("getProductByGenre/{genre}")
      Call<List<Books>> getBooksByGenre(@Path("genre") String genre);

//      @POST("getCustomer")
//      Call<Customer> getCustomer();
      @GET("getTopProducts")
      Call<List<Books>> getTopBooks();

      @GET("getGenreList")
      Call<ArrayList<String>> getGenre();

      @GET("getProductByProductId/{id}")
      Call<Books> getProductById(@Path("id") String id);

      @POST("login")
      Call<CustId> getCustId(@Body Login login);
}
