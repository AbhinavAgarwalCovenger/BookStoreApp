package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookstoreapp.adapater.CurrentOrderAdapter;
import com.example.bookstoreapp.pojo.OrderDeatils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CheckoutActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String myPreference = "mypref";
    private List<OrderDeatils> orderDeatils;
    Button done;
    TextView orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        orderId = findViewById(R.id.orderId);
        done = findViewById(R.id.doneButton);

        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);
        String account = sharedPreferences.getString("user_id", null);

        updateUI(account);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToMain();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        }

    private void updateUI(String account) {

        if (account != null) {
            Retrofit retrofit= RetrofitController.getRetrofit();
            ApiInterface api = retrofit.create(ApiInterface.class);
            Call<List<OrderDeatils>> call = api.getCurrentOrder(account);
            call.enqueue(new Callback<List<OrderDeatils>>() {
                @Override
                public void onResponse(Call<List<OrderDeatils>> call, Response<List<OrderDeatils>> response) {
                    orderDeatils = response.body();
                    orderId.setText(orderDeatils.get(0).getOrderId());
                    RecyclerView recyclerView = findViewById(R.id.orderRecyclerView);
                    recyclerView.scrollToPosition(1);
                    CurrentOrderAdapter currentOrderAdapter = new CurrentOrderAdapter(orderDeatils);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CheckoutActivity.this,LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(currentOrderAdapter);
                }

                @Override
                public void onFailure(Call<List<OrderDeatils>> call, Throwable t) {
                    Toast.makeText(CheckoutActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

                }
            });

        } else {
            sendToLogin();
            Toast.makeText(this, "Please Login First", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendToLogin() {

        Intent login_intent = new Intent(CheckoutActivity.this, LoginActivity.class);
        startActivity(login_intent);
    }

    private void sendToMain(){
        Intent intent = new Intent(CheckoutActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
