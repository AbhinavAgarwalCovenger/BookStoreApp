package com.example.bookstoreapp;

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

      @POST("login")
      Call<CustId> getCustId(@Body Login login);
}
