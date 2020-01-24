package com.example.bookstoreapp.adapater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.pojo.Books;
import com.example.bookstoreapp.pojo.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {


    private List<Books> booksList;

    public CartAdapter(List<Books> books){
        this.booksList=books;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cart_list,parent,false);
        return new CartAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.book_name.setText(booksList.get(position).getProductName());
        holder.author.setText(booksList.get(position).getAuthor());
        holder.price.setText(booksList.get(position).getPrice());
        Glide.with(holder.book_image.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                .load(booksList.get(position).getUrl()).into(holder.book_image);
        holder.quantity.setText(booksList.get(position).getQuantity());


    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView book_name;
        ImageView book_image;
        TextView author;
        TextView price;
        TextView quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.book_image = itemView.findViewById(R.id.book_image_cart);
            this.book_name = itemView.findViewById(R.id.book_name_cart);
            this.author = itemView.findViewById(R.id.author_cart);
            this.price = itemView.findViewById(R.id.price_cart);
            this.quantity = itemView.findViewById(R.id.quantity);
        }
    }
}
