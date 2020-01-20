package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent intent = getIntent();
        String bookName = intent.getStringExtra("name");
        String img = intent.getStringExtra("url");
        String author = intent.getStringExtra("author");
        String price = intent.getStringExtra("price");
        String publisher = intent.getStringExtra("publisher");
        ImageView image = findViewById(R.id.book_img);
        TextView nameText = findViewById(R.id.book_name);
        TextView authorText = findViewById(R.id.author);
        TextView priceText = findViewById(R.id.price);
        TextView publisherText = findViewById(R.id.publisher);
        Glide.with(getBaseContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                .load(img)
                .into(image);
        nameText.setText(bookName);
        authorText.setText(author);
        priceText.setText(price);
        publisherText.setText(publisher);


    }
}
