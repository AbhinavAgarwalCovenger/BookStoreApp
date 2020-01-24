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
    private ArrayList<String> genreList;

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

    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView genre_name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.genre_name = itemView.findViewById(R.id.book_name_gnr);
        }
    }
}
