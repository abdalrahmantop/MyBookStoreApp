package com.example.mybookstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;

    private Button Buybtn;
    private ArrayList<CartItem> cartItemsArray = new ArrayList<>();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        loadCartShr();

        Buybtn = findViewById(R.id.buybtn);
        recyclerView = findViewById(R.id.cart_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(this, cartItemsArray);
        recyclerView.setAdapter(cartAdapter);


        TextView totalPriceText = findViewById(R.id.total_price_text);
        double totalPrice = 0.0;

        for (CartItem item : cartItemsArray) {
            totalPrice += item.getTotalprice();
        }

        totalPriceText.setText(String.format("Total: $%.2f", totalPrice));

        Buybtn.setOnClickListener(e ->{
            BuyAction();
            totalPriceText.setText(String.format("Total: $%.2f",  0.00));
            Toast.makeText(this, "buyed completed successfully!", Toast.LENGTH_SHORT).show();
        });

    }

    public  void BuyAction(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();


        Gson gson = new Gson();
        String Bookjson = prefs.getString("DATA" , "");
        if (!Bookjson.isEmpty()){
             Book [] bookArray = gson.fromJson(Bookjson , Book[].class);
            ArrayList<Book> books = new ArrayList<>(Arrays.asList(bookArray));
            for (CartItem cartItem : cartItemsArray){
                for (Book book : books){
                    if (book.getName().equals(cartItem.getNameBook())){
                        int newQ = book.getQuantity() - cartItem.getQuantity();
                        book.setQuantity(newQ);
                        break;
                    }
                }
                
            }
            String updatearrayBooks = gson.toJson(books);
            editor.putString("DATA" , updatearrayBooks);

            cartItemsArray.clear();
            editor.putString("CART" , gson.toJson(cartItemsArray));
            editor.apply();
            cartAdapter.notifyDataSetChanged();
        }
    }

    public void loadCartShr() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String json = prefs.getString("CART", "");


        Gson gson = new Gson();


        if (!json.isEmpty()) {
            try {
                CartItem[] itemsArray = gson.fromJson(json, CartItem[].class);
                cartItemsArray = new ArrayList<>(Arrays.asList(itemsArray));
            } catch (Exception e) {

                cartItemsArray = new ArrayList<>();
            }
        } else {
            cartItemsArray = new ArrayList<>(); // لو مافي بيانات محفوظة، أنشئ قائمة فاضية
        }
    }

}
