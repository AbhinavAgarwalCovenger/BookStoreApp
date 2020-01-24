package com.example.bookstoreapp;

import com.example.bookstoreapp.pojo.Books;
import com.example.bookstoreapp.pojo.Cart;
import com.example.bookstoreapp.pojo.CustId;
import com.example.bookstoreapp.pojo.Customer;
import com.example.bookstoreapp.pojo.Login;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

      @GET("getProductByGenre/{genre}")
      Call<List<Books>> getBooksByGenre(@Path("genre") String genre);

      @GET("getCustomerById/{id}")
      Call<Customer> getCustomer(@Path("id") String id);

      @GET("getTopProducts")
      Call<List<Books>> getTopBooks();

      @GET("getGenreList")
      Call<ArrayList<String>> getGenre();

      @GET("search/")
      Call<List<Books>> getSearch(
              @Query("keyword") String keyword
      );

      @GET("getProductByProductId/{id}")
      Call<Books> getProductById(@Path("id") String id);

      @POST("login")
      Call<CustId> getCustId(@Body Login login);

      @POST("addToCart")
      Call<ResponseBody> addToCart(@Body Cart cart);

      @GET("getFromCart/{id}")
      Call<List<Books>> getCart(@Path("id") String id);
}
