package com.example.bookstoreapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

//    @GET("bins/aa0hm")
//    Call<List<Books>> getBooks();

//      @POST("getCustomer")
//      Call<Customer> getCustomer();

      @POST("login")
      Call<CustId> getCustId(@Body Login login);
}
