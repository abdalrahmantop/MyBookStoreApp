package com.example.mybookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<CartItem> CartItems;
    private Context context;


    public CartAdapter(Context context, ArrayList<CartItem> cartItems) {
        this.context = context;
        this.CartItems = cartItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CartItem cartitem = CartItems.get(position);
        CardView cardView = holder.cardView;

        ImageView imageView = cardView.findViewById(R.id.cart_item_image);
        imageView.setImageResource(cartitem.getImageResource());

        TextView titleView = cardView.findViewById(R.id.cart_item_name);
        titleView.setText(cartitem.getNameBook());

        TextView QuantityView = cardView.findViewById(R.id.cart_item_quantity);
        QuantityView.setText("Quantity: " + cartitem.getQuantity());

        TextView TotalView = cardView.findViewById(R.id.cart_item_totalprice);
        TotalView.setText(String.format("Total Price: $%.2f", cartitem.getTotalprice()));
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @Override
    public int getItemCount() {
        return CartItems.size();
    }


}


