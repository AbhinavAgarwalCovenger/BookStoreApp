package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ProductActivity extends AppCompatActivity {

    int minteger = 0;

    private List<Books> booksList;
    Button add_to_cart_btn;
    Books books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

         books = new Books();
        add_to_cart_btn = (Button) findViewById(R.id.add_to_cart);


        add_to_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToList();
            }
        });

        Intent intent = getIntent();
        String bookName = intent.getStringExtra("name");
        String img = intent.getStringExtra("url");
        String author = intent.getStringExtra("author");
        String price = intent.getStringExtra("price");
     //   String publisher = intent.getStringExtra("publisher");
        ImageView image = findViewById(R.id.book_img);
        TextView nameText = findViewById(R.id.book_name);
        TextView authorText = findViewById(R.id.author);
        TextView priceText = findViewById(R.id.price);
   //     TextView publisherText = findViewById(R.id.publisher);
        Glide.with(getBaseContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                .load(img)
                .into(image);
        nameText.setText(bookName);
        authorText.setText(author);
        priceText.setText(price);
 //       publisherText.setText(publisher);





    }

    private void addToList() {



    }


}
