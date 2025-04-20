package com.example.mybookstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private ArrayList<Book> books;
    private Context context;

    public BookAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_book, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = books.get(position);
        CardView cardView = holder.cardView;

        ImageView imageView = cardView.findViewById(R.id.bookImage);
        imageView.setImageResource(book.getImageResource());

        TextView titleView = cardView.findViewById(R.id.bookTitle);
        titleView.setText(book.getName());

        TextView priceView = cardView.findViewById(R.id.bookPrice);
        priceView.setText("Price: $" + book.getPrice());


        holder.itemView.setOnClickListener(v -> { // خون لما تكبس على card
            Intent intent = new Intent(context, BookDetailsActivity.class);
            intent.putExtra("title", book.getName());
            intent.putExtra("author", book.getAuthor());
            intent.putExtra("desc", book.getDescription());
            intent.putExtra("price", book.getPrice());
            intent.putExtra("quantity", book.getQuantity());
            intent.putExtra("image", book.getImageResource());
            context.startActivity(intent); //ال context هو هو نفسه ال mainActivity
        });

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
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }


}

