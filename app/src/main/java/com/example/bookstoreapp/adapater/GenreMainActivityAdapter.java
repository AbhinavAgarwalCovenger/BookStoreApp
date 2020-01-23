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
import com.example.bookstoreapp.pojo.Books;
import com.example.bookstoreapp.R;

import java.util.ArrayList;
import java.util.List;

public class GenreMainActivityAdapter  extends RecyclerView.Adapter<GenreMainActivityAdapter.ViewHolder> {


    public  GenreMainActivityAdapter(ArrayList<String> list){
        this.genreList=list;
    }
    private ArrayList<String> genreList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.genre_list,parent,false);
        return new GenreMainActivityAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.genre_name.setText(genreList.get(position));
//        holder.author.setText(booksList.get(position).getAuthor());
//        holder.price.setText(booksList.get(position).getPrice());
//        holder.publisher.setText(booksList.get(position).getAttributes().getPublisher());
//        Glide.with(holder.book_image.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
//                .load(booksList.get(position).getUrl()).into(holder.book_image);

    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView genre_name;
//        ImageView book_image;
//        TextView author;
//        TextView price;
//        TextView publisher;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            this.book_image = itemView.findViewById(R.id.book_image_gnr);
            this.genre_name = itemView.findViewById(R.id.book_name_gnr);
//            this.author = itemView.findViewById(R.id.author_gnr);
//            this.price = itemView.findViewById(R.id.price_gnr);
        //    this.publisher = itemView.findViewById(R.id.publisher_gnr);
        }
    }
}
