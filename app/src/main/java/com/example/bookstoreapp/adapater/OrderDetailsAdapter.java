package com.example.bookstoreapp.adapater;

import android.R.layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.pojo.OrderDeatils;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {
    private List<OrderDeatils> orderDeatils;

    public OrderDetailsAdapter(List<OrderDeatils> list){
        this.orderDeatils=list;
    }



    @NonNull
    @Override
    public OrderDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.order_details_list,parent,false);
        return new OrderDetailsAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsAdapter.ViewHolder holder, int position) {

        holder.prodName.setText(orderDeatils.get(position).getProductName());
        holder.prodQuantity.setText(orderDeatils.get(position).getQuantity());
        holder.prodPrice.setText(orderDeatils.get(position).getCost());
        Glide.with(holder.url.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground))
                .load(orderDeatils.get(position).getUrl()).into(holder.url);

        Integer[] items = new Integer[]{1,2,3,4,5};
      //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.order_details_list,items);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(holder.merchantSpinner.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        holder.orderSpinner.setAdapter(adapter);
        holder.merchantSpinner.setAdapter(adapter);


    }

    @Override
    public int getItemCount() {
        return orderDeatils.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prodName;
        TextView prodQuantity;
        TextView prodPrice;
        ImageView url;
        Button merchantRatingButton;
        Button orderRatingButton;
        Spinner merchantSpinner;
        Spinner orderSpinner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.prodName = itemView.findViewById(R.id.order_product);
            this.prodQuantity =itemView.findViewById(R.id.order_quantity);
            this.prodPrice = itemView.findViewById(R.id.order_price);
            this.url = itemView.findViewById(R.id.order_url);
            this.merchantRatingButton = itemView.findViewById(R.id.btn_merchant);
            this.orderRatingButton = itemView.findViewById(R.id.btn_order);
            this.merchantSpinner = itemView.findViewById(R.id.spinner_merchant);
            this.orderSpinner = itemView.findViewById(R.id.spinner_product);
        }
    }
}
