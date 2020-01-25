package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bookstoreapp.pojo.Customer;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyProfileActivity extends AppCompatActivity {

private CircleImageView mProfilePic;
    Retrofit retrofit = RetrofitController.getRetrofit();
    ApiInterface api = retrofit.create(ApiInterface.class);
    SharedPreferences sharedPreferences;
    public static final String myPreference = "mypref";
    private Customer customer;

    private TextView mName;
    private TextView mEmail;
    private TextView mPhone;
    private TextView mLoginHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        mName = (TextView)findViewById(R.id.profile_name);
        mEmail = (TextView) findViewById(R.id.profile_email);
        mPhone = (TextView) findViewById(R.id.profile_phone);
        mLoginHistory = (TextView) findViewById(R.id.profile_login_history);

        sharedPreferences=getSharedPreferences(myPreference, Context.MODE_PRIVATE);
        String account = sharedPreferences.getString("user_id",null);

        customer=new Customer();

        Call<Customer> call = api.getCustomer(account);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                customer=response.body();
                Toast.makeText(getBaseContext(),customer.getEmail(),Toast.LENGTH_SHORT).show();
                mProfilePic = (CircleImageView) findViewById(R.id.profile_image);

                Glide.with(MyProfileActivity.this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_background))
                        .load("https://image.shutterstock.com/image-photo/beautiful-water-drop-on-dandelion-260nw-789676552.jpg")
                        .into(mProfilePic);

                //setting profile details in respective text fields
                    mName.setText(customer.getName());
                    mPhone.setText(customer.getPhoneNumber());
                    mEmail.setText(customer.getEmail());

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Login Required",Toast.LENGTH_SHORT).show();
            }
        });

//        mProfilePic = (CircleImageView) findViewById(R.id.profile_image);
//
//        Glide.with(this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_background))
//                .load("https://image.shutterstock.com/image-photo/beautiful-water-drop-on-dandelion-260nw-789676552.jpg")
//                .into(mProfilePic);


        mLoginHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);
        String account = sharedPreferences.getString("user_id", null);
        if (null!=account) {
            Intent intent = new Intent(MyProfileActivity.this, LoginHistoryActivity.class);
            intent.putExtra("id",account);
            startActivity(intent);
        }
        else {
            Toast.makeText(getBaseContext(),"Login Required",Toast.LENGTH_SHORT).show();
        }

            }
        });




    }
}
