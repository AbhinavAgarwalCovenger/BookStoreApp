package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookstoreapp.adapater.CurrentOrderAdapter;
import com.example.bookstoreapp.pojo.OrderDeatils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderDetailsActivity extends AppCompatActivity {

    private List<OrderDeatils> orderDeatils;
    ImageButton done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        done = (ImageButton) findViewById(R.id.doneButton);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToMain();
            }
        });
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Please Wait...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);

        Intent intent = getIntent();
        String orderId = intent.getStringExtra("orderId");

        Retrofit retrofit = RetrofitController.getRetrofit();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<List<OrderDeatils>> call = api.getOrderDetails(orderId);
        progressBar.show();
        call.enqueue(new Callback<List<OrderDeatils>>() {
            @Override
            public void onResponse(Call<List<OrderDeatils>> call, Response<List<OrderDeatils>> response) {
                orderDeatils=response.body();
                totalCost(orderDeatils);
                progressBar.dismiss();
                RecyclerView recyclerView = findViewById(R.id.orderRecyclerView);
                recyclerView.scrollToPosition(1);
                CurrentOrderAdapter currentOrderAdapter = new CurrentOrderAdapter(orderDeatils);
                recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailsActivity.this,LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(currentOrderAdapter);

            }

            @Override
            public void onFailure(Call<List<OrderDeatils>> call, Throwable t) {
                progressBar.dismiss();
                Toast.makeText(OrderDetailsActivity.this,"Failer to Fetch",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendToMain(){
        Intent intent = new Intent(OrderDetailsActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void totalCost(List<OrderDeatils> orderDeatils){
        double cost=0;
        for(OrderDeatils orders: orderDeatils){
            double price = Double.parseDouble(orders.getCost());
            cost+=price;

        }
        String tot = String.valueOf(cost);
        TextView total = findViewById(R.id.price);

        total.setText(tot);
    }
}
