package com.example.bookstoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bookstoreapp.pojo.Books;
import com.example.bookstoreapp.pojo.Cart;

import java.nio.file.Path;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductActivity extends AppCompatActivity {

    private List<Books> booksList;
    Button add_to_cart_btn;
    Books books;
    Cart cart;
    Retrofit retrofit= RetrofitController.getRetrofit();
    ApiInterface api = retrofit.create(ApiInterface.class);

    SharedPreferences sharedPreferences;
    public static final String myPreference = "mypref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        add_to_cart_btn = (Button) findViewById(R.id.add_to_cart);


        add_to_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<String> call = api.addToCart(cart);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String res = response.body().toString();
                        Toast.makeText(getBaseContext(),res,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        books = new Books();
        cart = new Cart();
        Intent intent = getIntent();

        final String book_id = intent.getStringExtra("id");

        Call<Books> call =api.getProductById(book_id);
        call.enqueue(new Callback<Books>() {
            @Override
            public void onResponse(Call<Books> call, Response<Books> response) {
                books=response.body();
                if (books!=null) {
                    String bookName = books.getProductName();
                    String img = books.getUrl();
                    String author = books.getAuthor();
                    String price = books.getPrice();
                    String publisher = books.getAttributes().get("publisher");
                    String isbn = books.getIsbn();
                    String genre = books.getGenre();
                    String rating = books.getRating();
                    String description = books.getDescription();
                    String year = books.getAttributes().get("year");
                    String binding = books.getAttributes().get("binding");
                    String pages = books.getAttributes().get("noofpages");
                    String mid = books.getMerchantId();
//                    String pid = books.getProductId();
                    sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);
                    String user_id = sharedPreferences.getString("user_id", null);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("quantity",books.getQuantity());
//                    editor.commit();
                    cart.setProductId(book_id);
                    cart.setMerchantId(mid);
                    cart.setQuantity("1");
                    cart.setCartId(user_id);

                    Toast.makeText(getBaseContext(),mid,Toast.LENGTH_LONG).show();

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
                else {
                    Toast.makeText(getBaseContext(),"Failed",Toast.LENGTH_LONG).show();
                }

                Toast.makeText(ProductActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Books> call, Throwable t) {
                Toast.makeText(ProductActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }



}
