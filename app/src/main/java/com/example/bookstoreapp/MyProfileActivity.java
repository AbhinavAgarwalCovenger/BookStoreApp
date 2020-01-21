package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyProfileActivity extends AppCompatActivity {

    Button order_history;
    Button login_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        order_history =(Button) findViewById(R.id.cust_order_history);
        login_history = (Button) findViewById(R.id.cust_login_history);

        order_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyProfileActivity.this, OrderHistoryActivity.class);
                startActivity(intent);
            }
        });

        login_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this,LoginHistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
