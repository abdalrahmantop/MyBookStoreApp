package com.example.mybookstore;

import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String DATA = "DATA";

    private Spinner spnCats;
    private Button btnShow , Searchbtn ;

    private EditText SearchEdit ;
    private RecyclerView recyclerView;
    private BookAdapter adapter;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private ImageButton Cartbtn;

    private ArrayList<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cartbtn = findViewById(R.id.btncart);
        Searchbtn = findViewById(R.id.searchButton);
        SearchEdit = findViewById(R.id.searchEditText);

        spnCats = findViewById(R.id.categorySpinner);
        btnShow = findViewById(R.id.filterButton);


        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        loadBooksFromSharedPrefs();

        //  إعداد الـ RecyclerView عشان نعرض على الواجهة الاولى تلقائي اول ما يفتح
        recyclerView = findViewById(R.id.book_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookAdapter(this, books);
        recyclerView.setAdapter(adapter);

        // إعداد الـ Spinner
        bindSpinner();


        btnShow.setOnClickListener(new View.OnClickListener() { // هون لما تكبس على زر filter
            @Override
            public void onClick(View view) {
                String selectedCategory = spnCats.getSelectedItem().toString();
                filterBooksByCategory(selectedCategory);
            }
        });


        Cartbtn.setOnClickListener(new View.OnClickListener() { // هون بروح على  السلة
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent); // بدء النشاط
            }// هون انتهي شغل ال cart
        });

        Searchbtn.setOnClickListener(e ->{
            String textSearch = String.valueOf(SearchEdit.getText());
            filterBooks(textSearch);
        });

    }

    private void filterBooksByCategory(String category) {
        DAFactory factory = new DAFactory();
        IBookDA data = factory.getInstance();

        ArrayList<Book> filteredBooks;

        if (category.equals("All")) {
            filteredBooks = books;  //  كل الكتب ، طبعا هون بس اوبحكت book ما اله دخل بالعرض لسا
        } else {
            filteredBooks = data.getBooksCategory(category); // بترجع الكتب حسب ال التصنيف
        }

        adapter = new BookAdapter(this, filteredBooks); //هون adapter محتلف بتعامل مع card و recycle
        recyclerView.setAdapter(adapter);
    }

    private void loadBooksFromSharedPrefs() {

//        editor.clear(); // هذا يقوم بحذف جميع البيانات المخزنة في الـ SharedPreferences
//        editor.apply();
// هون بتوصل لل data الي مخزمة داخل ال shr
        Gson gson = new Gson();
        String json = prefs.getString(DATA, "");

        if (!json.isEmpty()) { //اذا كانت موحودة بنخزنها بال array books
            Book[] bookArray = gson.fromJson(json, Book[].class);
            books = new ArrayList<>(Arrays.asList(bookArray));
        } else {
            BookDA bookDA = new BookDA(); // اذا لا بنجيب البدائيات الموجودة في الكلاس bookDA
            books = bookDA.getSampleBooks();
            saveBooksToSharedPrefs(books); //هاي بتخزن ال array داخل shr
        }
    }

    private void saveBooksToSharedPrefs(ArrayList<Book> books) {
        Gson gson = new Gson();
        String json = gson.toJson(books);
        editor.putString(DATA, json);
        editor.apply();// هون خلص حفظناها داخل shr
    }

    private void bindSpinner() {
        // هون بنجهز محتوى ال spinner
        DAFactory factory = new DAFactory();
        IBookDA data = factory.getInstance();

        String[] realCats = data.getCats();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, realCats);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCats.setAdapter(adapter); //
    }


   private void filterBooks(String text) {
       DAFactory factory = new DAFactory();
       IBookDA data = factory.getInstance();

       ArrayList<Book> filteredBooks;
       if ( text.isEmpty()) {
           filteredBooks = books;  //  كل الكتب ، طبعا هون بس اوبحكت book ما اله دخل بالعرض لسا
       } else {
           filteredBooks = data.getBooksSearch(text); // بترجع الكتب حسب ال التصنيف
       }

       adapter = new BookAdapter(this, filteredBooks); //هون adapter محتلف بتعامل مع card و recycle
       recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Gson gson = new Gson();
        String json = prefs.getString(DATA, "");

        if (!json.isEmpty()) {
            Book[] bookArray = gson.fromJson(json, Book[].class);
            ArrayList<Book> updatedBooks = new ArrayList<>(Arrays.asList(bookArray));

            books = updatedBooks;

            if (adapter != null) {
                adapter.setBooks(books);
                adapter.notifyDataSetChanged();
            }
        }
    }




}
