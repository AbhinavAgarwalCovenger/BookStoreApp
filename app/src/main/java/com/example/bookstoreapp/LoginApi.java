package com.example.bookstoreapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



 /*String name;
         String email;
         String phone_number;
         String address;
         String password;
         String pincode;
         String Cust_or_Merc;*/


public interface LoginApi {

    @FormUrlEncoded
    @POST("createuser")
    Call<ResponseBody>  createUser(

      @Field("email") String email,
      @Field("phone_number") String phone_number,
      @Field("address") String address,
      @Field("password") String password,
      @Field("pincode") String pincode,
      @Field("Cust_or_Merc") String Cust_or_Merc



    );


}
