package com.example.bookstoreapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface SignUpApi {

    @POST("customer/signup")
    Call<ResponseBody>  createUser(
            @Body Customer customer
//      @Field("name") String name,
//      @Field("email") String email,
//      @Field("phone_number") String phone_number,
//      @Field("address") String address,
//      @Field("password") String password,
//      @Field("pincode") String pincode,
//      @Field("Cust_or_Merc") Boolean Cust_or_Merc
    );


}