package com.example.bookstoreapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    public SearchAdapter(List<Books> list){
        this.booksList=list;
    }
    private List<Books> booksList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.book_list,parent,false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.book_name.setText(booksList.get(position).getName());
        holder.author.setText(booksList.get(position).getAuthor());
        holder.price.setText(booksList.get(position).getPrice());
        holder.publisher.setText(booksList.get(position).getPublisher());
        Glide.with(holder.book_image.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                .load(booksList.get(position).getUrl()).into(holder.book_image);
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView book_name;
        ImageView book_image;
        TextView author;
        TextView price;
        TextView publisher;

        ViewHolder(View itemView){
            super(itemView);
            this.book_image = itemView.findViewById(R.id.book_image);
            this.book_name = itemView.findViewById(R.id.book_name);
            this.author = itemView.findViewById(R.id.author);
            this.price = itemView.findViewById(R.id.price);
            this.publisher = itemView.findViewById(R.id.publisher);
        }

    }
}
