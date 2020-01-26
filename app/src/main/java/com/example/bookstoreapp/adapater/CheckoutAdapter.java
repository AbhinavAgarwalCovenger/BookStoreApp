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
import com.example.bookstoreapp.pojo.OrderDeatils;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<OrderDeatils> orderDeatils;


    private static final int FOOTER_VIEW = 1;
    private static final int HEADER_VIEW = 2;

    public CheckoutAdapter(List<OrderDeatils> list){
        this.orderDeatils=list;
    }



    // Define a ViewHolder for Header view
    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView prodId;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            prodId = (TextView) itemView.findViewById(R.id.checkout_header_product_id);
        }
        public void bindView(int position) {
        }
    }


    // Define a ViewHolder for Footer view
    public class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView prodCost;
        public FooterViewHolder(View itemView) {
            super(itemView);
            prodCost = (TextView) itemView.findViewById(R.id.cart_header_total_price);
        }

        public void bindView(int position) {

        }
    }
    // Define a Normal for Footer view
    public class NormalViewHolder extends ViewHolder {
        public NormalViewHolder(View itemView) {
            super(itemView);

        }

      
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == FOOTER_VIEW) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.cart_footer,parent,false);
            return new CheckoutAdapter.ViewHolder(listItem);
        } else if (viewType == HEADER_VIEW) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.checkout_header,parent,false);
            return new CheckoutAdapter.ViewHolder(listItem);
        }else{
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.checkout_list,parent,false);
            return new CheckoutAdapter.ViewHolder(listItem);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        try {
            if (holder instanceof NormalViewHolder) {
                NormalViewHolder vh = (NormalViewHolder) holder;
                vh.prodName.setText(orderDeatils.get(position).getProductName());
                vh.prodPrice.setText(orderDeatils.get(position).getPrice());
                vh.prodQuantity.setText(orderDeatils.get(position).getQuantity());
                vh.bindView(position);
            } else if (holder instanceof FooterViewHolder) {
                FooterViewHolder vh = (FooterViewHolder) holder;
                vh.prodCost.setText(orderDeatils.get(position).getCost());
                vh.bindView(position);
            } else if (holder instanceof HeaderViewHolder) {
                HeaderViewHolder vh = (HeaderViewHolder) holder;
                vh.prodId.setText(orderDeatils.get(position).getProductId());
                vh.bindView(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
            }





    }

    // Now define getItemViewType of your own.
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            // This is where we'll add the header.
            return HEADER_VIEW;
        } else if (position == orderDeatils.size() + 1) {
            // This is where we'll add footer.
            return FOOTER_VIEW;
        }

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {

        if (orderDeatils == null) {
            return 0;
        }

        if (orderDeatils.size() == 0) {
            // Return 1 here to show nothing
            return 1;
        }

        return orderDeatils.size() + 2;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prodName;
        TextView prodQuantity;
        TextView prodPrice;
        ImageView url;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.prodName = itemView.findViewById(R.id.order_product);
            this.prodQuantity =itemView.findViewById(R.id.order_quantity);
            this.prodPrice = itemView.findViewById(R.id.order_price);
            this.url = itemView.findViewById(R.id.order_url);
        }

        public void bindView(int position) {

        }
    }
}
