package com.example.mybookstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class BookDetailsActivity extends AppCompatActivity {

    ImageView imageView;

    int SumQ = 0 ;

    int imageResId ;
    double bookPrice  , quantitybook ;
    Button ButtonAdd , ButtonDer  , AddtoCart;
    TextView titleView, authorView, descView, priceView, quantityView , textQ , totalpriceQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        imageView = findViewById(R.id.detailsImage);
        titleView = findViewById(R.id.detailsTitle);
        authorView = findViewById(R.id.detailsAuthor);
        descView = findViewById(R.id.detailsDesc);
        priceView = findViewById(R.id.detailsPrice);
        quantityView = findViewById(R.id.detailsQuantity);
        ButtonAdd = findViewById(R.id.buttonAdd);
        ButtonDer = findViewById(R.id.buttominus);
        textQ = findViewById(R.id.textQ);
        totalpriceQ = findViewById(R.id.totalQ);
        AddtoCart = findViewById(R.id.AddToCart);



        Intent intent = getIntent(); // الي جاي من BookAdapter
       bookPrice = intent.getDoubleExtra("price", 0.0);
       quantitybook = intent.getIntExtra("quantity" , 0);
       imageResId = getIntent().getIntExtra("image", 0);

        titleView.setText(intent.getStringExtra("title"));
            authorView.setText("By " + intent.getStringExtra("author"));
            descView.setText(intent.getStringExtra("desc"));
            priceView.setText("Price: " +  bookPrice );
            quantityView.setText("Available: "   + quantitybook);
            imageView.setImageResource(intent.getIntExtra("image" , 0 ));


        ButtonAdd.setOnClickListener(e -> {
            if (SumQ < quantitybook ) {
                SumQ += 1;
                textQ.setText(String.valueOf(SumQ));
                totalpriceQ.setText("the Total = $" + (SumQ * bookPrice));
            }
        });

        ButtonDer.setOnClickListener(e -> {
            if (SumQ > 0) {
                SumQ -= 1;
                textQ.setText(String.valueOf(SumQ));
                totalpriceQ.setText("the Total = $" + (SumQ * bookPrice));
            }
        });

        AddtoCart.setOnClickListener(e ->{
            AddtoCartbtn();

        });

    }



    public  void AddtoCartbtn(){
        CartItem cartItem = new CartItem((String) titleView.getText() ,  bookPrice  , SumQ ,      imageResId );
        addToCart(cartItem  );
        Toast.makeText(this, "successfully added to the cart.!", Toast.LENGTH_SHORT).show();


    }

    private void addToCart(CartItem item) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();

        // استرجاع القائمة القديمة
        String json = prefs.getString("CART", "");
        ArrayList<CartItem> cartList;

        if (!json.isEmpty()) { // هوم العملية على ال array من نوع cartitem اذا موجود بيانات ولا مش موجود رح نخزن ب array
            CartItem[] savedItems = gson.fromJson(json, CartItem[].class);
            cartList = new ArrayList<>(Arrays.asList(savedItems));
        } else {
            cartList = new ArrayList<>();
        }

        //  هون خزنا الكتاب ل arraycart
        cartList.add(item);

        // ,خزّن القائمة مرة أخر,هون بندخل ال array cartitem مع المخزنات قبل
        String updatedJson = gson.toJson(cartList);
        editor.putString("CART", updatedJson);
        editor.apply();
        //يعني هون صاو عنا array cartitrm فيها كل الكتب الي دخلناها على السلة
    }


}
